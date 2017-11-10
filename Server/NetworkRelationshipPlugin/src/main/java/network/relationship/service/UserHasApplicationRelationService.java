package network.relationship.service;

import java.util.Map;

import network.relationship.domain.CatfishRelationshipType;
import network.relationship.domain.UserHasApplicationRelation;
import network.relationship.repository.UserHasApplicationRelationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;

import catfish.base.CollectionUtils;

public class UserHasApplicationRelationService extends
        AbstractRelationshipService<UserHasApplicationRelation> {

    private static String existsQuery = 
            "match (u:User {idName : {idName}, idNumber : {idNumber}}) "
            + "-[r:USER_HAS_APPLICATION]-> (a:Application {appId : {appId}})  " 
            + "return r";

    @Autowired
    private UserHasApplicationRelationRepository repository;

    @Override
    protected String getRelationType() {
        return CatfishRelationshipType.USER_HAS_APPLICATION.name();
    }

    @Override
    protected Object getStartNode(UserHasApplicationRelation domainObject) {
        if (domainObject == null) {
            return null;
        }
        return domainObject.getUser();
    }

    @Override
    protected Object getEndNode(UserHasApplicationRelation domainObject) {
        if (domainObject == null) {
            return null;
        }
        return domainObject.getApp();
    }

    @Override
    protected GraphRepository<UserHasApplicationRelation> getRepository() {
        return repository;
    }

    @Override
    protected String getExistsQuery() {
        return existsQuery;
    }

    @Override
    protected Map<String, Object> buildParams(
            UserHasApplicationRelation domainObject) {
        Map<String, Object> params = CollectionUtils
                .<String, Object> newMapBuilder()
                .add("idName", domainObject.getUser().getIdName())
                .add("idNumber", domainObject.getUser().getIdNumber())
                .add("appId", domainObject.getApp().getAppId())
                .build();

        return params;
    }
}
