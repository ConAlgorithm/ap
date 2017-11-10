package network.relationship.service;

import java.util.Map;

import network.relationship.domain.Application;
import network.relationship.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;

import catfish.base.CollectionUtils;

public class ApplicationService extends AbstractNodeService<Application>{

	@Autowired ApplicationRepository repository;
	
	private static String existsQuery = 
	        "match (a:Application) where a.appId = {appId} return a";
	
	private static String mergeQuery = 
	        "merge (a:Application {appId : {appId}}) on create set a:_Application return a";
	
    @Override
    protected String getMergeQuery(Application domainObject) {
        return mergeQuery;
    }

    @Override
    protected GraphRepository<Application> getRepository() {
        return repository;
    }

    @Override
    protected String getExistsQuery() {
        return existsQuery;
    }

    @Override
    protected Map<String, Object> buildParams(Application domainObject) {
        Map<String, Object> params = CollectionUtils
                .<String, Object>newMapBuilder()
                .add("appId", domainObject.appId)
                .build();
        
        return params;
    }
}
