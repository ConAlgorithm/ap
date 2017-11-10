package network.relationship.domain;

import org.neo4j.graphdb.Node;


import catfish.base.StringUtils;

import com.google.gson.Gson;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class User implements IDomain{
    @GraphId
    private Long id;

    //@Indexed
    private String idName;

    //@Indexed
    private String idNumber;

    public User() {
    }

    public User(String idName, String idNumber) {
        this.idNumber = idNumber;
        this.idName = idName;
    }
    
    public User(Node node){
    	this.idName = (String)node.getProperty("idName");
    	this.idNumber = (String)node.getProperty("idNumber");
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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
        return !StringUtils.isNullOrWhiteSpaces(idName) 
                && !StringUtils.isNullOrWhiteSpaces(idNumber);
    }
}
