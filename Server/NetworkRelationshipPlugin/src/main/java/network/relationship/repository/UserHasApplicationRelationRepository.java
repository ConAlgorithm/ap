package network.relationship.repository;

import network.relationship.domain.UserHasApplicationRelation;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserHasApplicationRelationRepository extends
        GraphRepository<UserHasApplicationRelation> {

}
