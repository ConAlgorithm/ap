package network.relationship.repository;

import network.relationship.domain.Application;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface ApplicationRepository extends GraphRepository<Application> {
    
}
