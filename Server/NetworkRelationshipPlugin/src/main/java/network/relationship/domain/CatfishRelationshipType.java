package network.relationship.domain;

import org.neo4j.graphdb.RelationshipType;

public enum CatfishRelationshipType implements RelationshipType {

    USER_HAS_MOBILE,
    
    USER_HAS_APPLICATION,
    
    APPLICATION_HAS_MOBILE,
    
    MOBILE_CALLS
    
}
