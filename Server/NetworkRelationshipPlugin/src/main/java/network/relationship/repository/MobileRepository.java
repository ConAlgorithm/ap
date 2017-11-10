package network.relationship.repository;

import network.relationship.domain.Mobile;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface MobileRepository extends GraphRepository<Mobile> {

}
