package network.relationship.domain;

import com.google.gson.Gson;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "APPLICATION_HAS_MOBILE")
public class ApplicationHasMobileRelation implements IDomain{

	@GraphId
    Long id;

    //@Fetch
    @StartNode
    Application app;
    
    //@Fetch
    @EndNode
    Mobile mobile;

    public ApplicationHasMobileRelation(Application app, Mobile mobile) {
        super();
        this.app = app;
        this.mobile = mobile;
    }
    
    public ApplicationHasMobileRelation() {
        
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }
    
    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }

    @Override
    public boolean isValid() {
        return app != null 
                && mobile != null 
                && app.isValid() 
                && mobile.isValid();
    }
}
