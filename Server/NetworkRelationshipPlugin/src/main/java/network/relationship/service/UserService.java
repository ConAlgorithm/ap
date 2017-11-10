package network.relationship.service;

import java.util.Map;

import network.relationship.domain.User;
import network.relationship.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;

import catfish.base.CollectionUtils;

public class UserService extends AbstractNodeService<User> {

    @Autowired
    private UserRepository userRepository;

    private static String existsQuery = 
    		"match (u:User) where u.idNumber={uppercaseIdNumber} or u.idNumber={lowercaseIdNumber} return u";

    private static String mergeQuery = 
            "merge (u:User {idName : {idName}, idNumber : {idNumber}}) " + 
             "on create set u:_User return u";

    @Override
    protected String getMergeQuery(User domainObject) {
        return mergeQuery;
    }

    @Override
    protected GraphRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    protected String getExistsQuery() {
        return existsQuery;
    }

    @Override
    protected Map<String, Object> buildParams(User domainObject) {
        Map<String, Object> params = CollectionUtils
                .<String, Object> newMapBuilder()
                .add("idName", domainObject.getIdName())
                .add("idNumber", domainObject.getIdNumber())
                .add("uppercaseIdNumber", domainObject.getIdNumber().toUpperCase())
                .add("lowercaseIdNumber", domainObject.getIdNumber().toLowerCase()).build();

        return params;
    }

}
