package network.relationship.service;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import network.relationship.CatfishNeo4jConfiguration;
import network.relationship.domain.IDomain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;

public abstract class AbstractDomainService<T extends IDomain> implements IDomainService<T> {

    private static int pathMaxLength;

    private static String appQuery;

    private static String userQuery;

    static {

        pathMaxLength = StartupConfig.getAsInt("catfish.graph.pathlength.max");
        appQuery = String.format("match (a:Application) -[*1..%d]- "
                        + "(b:Application) where a.appId = {appId} return distinct b limit 500;",
                pathMaxLength);
        userQuery = String.format("match (a:Application) -[*1..%d]- "
                        + "(u:User) where a.appId = {appId} return distinct u limit 300;",
                pathMaxLength);
    }

    protected abstract GraphRepository<T> getRepository();

    protected abstract String getExistsQuery();

    protected abstract Map<String, Object> buildParams(T domainObject);

    @Autowired
    private CatfishNeo4jConfiguration config;

    @Override
    public abstract T createIfNotExists(T domainObject);

    @Override
    public boolean exists(T domainObject) {
        domainObject = collectionQuery(domainObject, getExistsQuery(), buildParams(domainObject));
        return domainObject != null;
    }

    @Override
    public T find(T domainObject) {
        T result = collectionQuery(domainObject, getExistsQuery(), buildParams(domainObject));

        if (result != null) {
            return result;
        }

        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public T collectionQuery(T domainObject, String query, Map<String, Object> params) {
        T res = null;
        try {
            Iterable<T> result = config.neo4jTemplate().queryForObjects((Class<T>) domainObject.getClass(), query,
                params);
            if( result.iterator().hasNext()){
                res = result.iterator().next();
            }
        } catch (Exception e) {
            Logger.get().warn("neo4j query failed :" + e);
            return null;
        }
        return res;
    }

    @Override
    public Iterable<T> queryAppNodes(String appId,T domainObject){
        Iterable<T> result = null;
        try{
            result = config.neo4jTemplate().queryForObjects((Class<T>) domainObject.getClass(),appQuery,CollectionUtils
                    .<String, Object> newMapBuilder()
                    .add("appId", appId).build());
        } catch (Exception ex) {
            Logger.get().warn("neo4j query app list failed : " + ex);
        }
        return result;
    }

    @Override
    public Iterable<T> queryUserNodes(String appId,T domainObject){
        Iterable<T> result = null;
        try{
            result = config.neo4jTemplate().queryForObjects((Class<T>) domainObject.getClass(),userQuery,CollectionUtils
                    .<String, Object> newMapBuilder()
                    .add("appId", appId).build());
        } catch (Exception ex) {
            Logger.get().warn("neo4j query app list failed : " + ex);
        }
        return result;
    }

}
