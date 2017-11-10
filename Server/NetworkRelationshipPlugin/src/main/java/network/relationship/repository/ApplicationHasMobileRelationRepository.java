package network.relationship.repository;

import network.relationship.domain.ApplicationHasMobileRelation;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface ApplicationHasMobileRelationRepository extends
        GraphRepository<ApplicationHasMobileRelation> {

}
