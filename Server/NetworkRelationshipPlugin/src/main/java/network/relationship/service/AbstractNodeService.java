package network.relationship.service;

import network.relationship.domain.IDomain;
import catfish.base.Logger;

public abstract class AbstractNodeService<T extends IDomain> extends AbstractDomainService<T> {
    
    protected abstract String getMergeQuery(T domainObject);
    
    @Override
    public T createIfNotExists(T domainObject) {
        if (domainObject == null || !domainObject.isValid()) {
            Logger.get().info("Invalid Node : " + domainObject);
            return null;
        }
        
        T result = find(domainObject);
        
        if (result != null) {
            Logger.get().info("Node already exists : " + domainObject);
            return result;
        }

        try {
            return getRepository().save(domainObject);
        }catch (Exception e) {
            Logger.get().warn("neo4j node save failed : " + e);
        }

        return null;

    }
}
