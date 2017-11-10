package network.relationship.repository;

import network.relationship.domain.UserHasMobileRelation;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserHasMobileRelattionRepository extends
        GraphRepository<UserHasMobileRelation> {

}
