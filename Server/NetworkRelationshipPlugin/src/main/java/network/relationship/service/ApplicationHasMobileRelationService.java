package network.relationship.service;

import java.util.Map;

import network.relationship.domain.ApplicationHasMobileRelation;
import network.relationship.domain.CatfishRelationshipType;
import network.relationship.repository.ApplicationHasMobileRelationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;

import catfish.base.CollectionUtils;

public class ApplicationHasMobileRelationService extends
        AbstractRelationshipService<ApplicationHasMobileRelation> {

    @Autowired
    private ApplicationHasMobileRelationRepository repository;

    private static String existsQuery = 
            "match (a:Application {appId : {appId}}) "
            + "-[r:APPLICATION_HAS_MOBILE]-> "
            + "(m:Mobile {mobile : {mobile}}) " 
            + "return r";

    @Override
    protected String getRelationType() {
        return CatfishRelationshipType.APPLICATION_HAS_MOBILE.name();
    }

    @Override
    protected Object getStartNode(ApplicationHasMobileRelation domainObject) {
        if (domainObject == null) {
            return null;
        }
        return domainObject.getApp();
    }

    @Override
    protected Object getEndNode(ApplicationHasMobileRelation domainObject) {
        if (domainObject == null) {
            return null;
        }
        return domainObject.getMobile();
    }

    @Override
    protected GraphRepository<ApplicationHasMobileRelation> getRepository() {
        return repository;
    }

    @Override
    protected String getExistsQuery() {
        return existsQuery;
    }

    @Override
    protected Map<String, Object> buildParams(
            ApplicationHasMobileRelation domainObject) {
        Map<String, Object> params = CollectionUtils
                .<String, Object> newMapBuilder()
                .add("appId", domainObject.getApp().getAppId())
                .add("mobile", domainObject.getMobile().getMobile())
                .build();
        return params;
    }

}
