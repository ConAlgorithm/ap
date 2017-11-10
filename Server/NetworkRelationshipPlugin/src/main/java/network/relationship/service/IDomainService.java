package network.relationship.service;

import java.util.Map;

import network.relationship.domain.IDomain;
import org.springframework.transaction.annotation.Transactional;

public interface IDomainService<T extends IDomain> {
    @Transactional
    public T createIfNotExists(T domainObject);
    
    @Transactional
    public boolean exists(T domainObject);
    
    @Transactional
    public T find(T domainObject);

    @Transactional
    public T collectionQuery(T domainObject,String query, Map<String, Object> params);

    @Transactional
    public Iterable<T> queryAppNodes(String appId,T domainObject);

    @Transactional
    public Iterable<T> queryUserNodes(String appId,T domainObject);
}
