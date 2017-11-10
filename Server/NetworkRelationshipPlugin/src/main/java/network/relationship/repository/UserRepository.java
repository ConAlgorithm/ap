package network.relationship.repository;

import network.relationship.domain.User;

import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<User>{

}
