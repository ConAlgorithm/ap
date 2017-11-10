package network.relationship.domain;

import catfish.base.StringUtils;

import com.google.gson.Gson;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Mobile implements IDomain{

	@GraphId
    Long id;
	
	//@Indexed
	private String mobile;
	
	public Mobile() {
	}

    public Mobile(String mobile) {
        super();
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }

    @Override
    public boolean isValid() {
        return !StringUtils.isNullOrWhiteSpaces(mobile);
    }
}
