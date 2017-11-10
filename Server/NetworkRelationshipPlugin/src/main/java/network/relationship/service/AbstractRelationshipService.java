package network.relationship.service;

import network.relationship.domain.IDomain;

import catfish.base.Logger;

public abstract class AbstractRelationshipService<T extends IDomain> extends AbstractDomainService<T> {
    
    protected abstract String getRelationType();
    
    protected abstract Object getStartNode(T domainObject);
    
    protected abstract Object getEndNode(T domainObject);
    
    @SuppressWarnings("unchecked")
    @Override
    public T createIfNotExists(T domainObject) {
        if (domainObject == null || !domainObject.isValid()) {
            Logger.get().info("Invalid Relation : " + domainObject);
            return null;
        }

        T result = find(domainObject);

        if (result != null) {
            Logger.get().info("Relation already exists : " + domainObject);
            return result;
        }
        
        try {
            return getRepository().save(domainObject);
        }catch (Exception e) {
            Logger.get().warn("neo4j relationship save failed : " + e);
        }

        return null;
    }
}
