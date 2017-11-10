package network.relationship.service;

import java.util.Map;

import network.relationship.domain.Mobile;
import network.relationship.repository.MobileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;

import catfish.base.CollectionUtils;

public class MobileService extends AbstractNodeService<Mobile> {

    @Autowired 
    private MobileRepository repository;
    
    private static String existsQuery = 
            "match (m:Mobile {mobile : {mobile}}) return m";
    
    private static String mergeQuery = 
            "merge (m:Mobile {mobile : {mobile}}) on create set m:_Mobile return m";
    
    @Override
    protected String getMergeQuery(Mobile domainObject) {
        return mergeQuery;
    }

    @Override
    protected GraphRepository<Mobile> getRepository() {
        return repository;
    }

    @Override
    protected String getExistsQuery() {
        return existsQuery;
    }

    @Override
    protected Map<String, Object> buildParams(Mobile domainObject) {
        Map<String, Object> params = CollectionUtils
                .<String, Object>newMapBuilder()
                .add("mobile", domainObject.getMobile())
                .build();
        
        return params;
    }

}
