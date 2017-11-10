package network.relationship.domain;

import com.google.gson.Gson;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "USER_HAS_APPLICATION")
public class UserHasApplicationRelation implements IDomain{

	@GraphId
    private Long id;
	
    //@Fetch
    @StartNode
    private User user;
    
    //@Fetch
    @EndNode
    private Application app;

    public UserHasApplicationRelation(User user, Application app) {
        super();
        this.user = user;
        this.app = app;
    }
    
    public UserHasApplicationRelation() {
        
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
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
                && user != null
                && app.isValid()
                && user.isValid();
    }
}
