package network.relationship.domain;

import com.google.gson.Gson;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "MOBILE_CALLS")
public class MobileCallRelation implements IDomain{

	@GraphId
    Long id;

    //@Fetch
    @StartNode
    Mobile caller;
    
    //@Fetch
    @EndNode
    Mobile callee;

    public MobileCallRelation(Mobile caller, Mobile callee) {
        super();
        this.caller = caller;
        this.callee = callee;
    }
    
    public MobileCallRelation() {
        
    }
    
    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }

    @Override
    public boolean isValid() {
        return caller != null 
                && callee != null
                && caller.isValid()
                && callee.isValid();
    }
}
