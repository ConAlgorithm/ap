package network.relationship.service;

import java.util.Map;

import network.relationship.domain.CatfishRelationshipType;
import network.relationship.domain.UserHasMobileRelation;
import network.relationship.repository.UserHasMobileRelattionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;

import catfish.base.CollectionUtils;

public class UserHasMobileRelationService extends
        AbstractRelationshipService<UserHasMobileRelation> {

    @Autowired
    private UserHasMobileRelattionRepository repository;

    private static String existsQuery = "match (u:User {idName : {idName}, idNumber : {idNumber}}) "
            + "-[r: USER_HAS_MOBILE]-> "
            + "(m:Mobile {mobile : {mobile}}) "
            + "return r";

    @Override
    protected String getRelationType() {
        // TODO Auto-generated method stub
        return CatfishRelationshipType.USER_HAS_MOBILE.name();
    }

    @Override
    protected Object getStartNode(UserHasMobileRelation domainObject) {
        if (domainObject == null) {
            return null;
        }
        return domainObject.getUser();
    }

    @Override
    protected Object getEndNode(UserHasMobileRelation domainObject) {
        if (domainObject == null) {
            return null;
        }
        return domainObject.getMobile();
    }

    @Override
    protected GraphRepository<UserHasMobileRelation> getRepository() {
        return repository;
    }

    @Override
    protected String getExistsQuery() {
        return existsQuery;
    }

    @Override
    protected Map<String, Object> buildParams(UserHasMobileRelation domainObject) {
        Map<String, Object> params = CollectionUtils
                .<String, Object> newMapBuilder()
                .add("idName", domainObject.getUser().getIdName())
                .add("idNumber", domainObject.getUser().getIdNumber())
                .add("mobile", domainObject.getMobile().getMobile()).build();

        return params;
    }

}
