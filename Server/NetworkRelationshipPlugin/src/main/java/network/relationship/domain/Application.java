package network.relationship.domain;

import org.neo4j.graphdb.Node;

import catfish.base.StringUtils;

import com.google.gson.Gson;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Application implements IDomain{

	@GraphId
    Long id;

    //@Indexed
    public String appId;
    
    public Application() {
        
    }
    
    public Application(String appId) {
        this.appId = appId;
    }
    
    public Application(Node node){
    	this.appId = (String) node.getProperty("appId");
    }
    
    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isValid() {
        return !StringUtils.isNullOrWhiteSpaces(appId);
    }
}
