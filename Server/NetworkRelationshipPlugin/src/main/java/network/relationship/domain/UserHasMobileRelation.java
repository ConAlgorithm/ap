package network.relationship.domain;

import com.google.gson.Gson;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "USER_HAS_MOBILE")
public class UserHasMobileRelation implements IDomain{

	@GraphId
    Long id;
	
    //@Fetch
    @StartNode
    User user;
    
    //@Fetch
    @EndNode
    Mobile mobile;

    public UserHasMobileRelation(User user, Mobile mobile) {
        super();
        this.user = user;
        this.mobile = mobile;
    }
    
    public UserHasMobileRelation() {
        
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return user != null
                && mobile != null
                && user.isValid()
                && mobile.isValid();
    }
    
}
