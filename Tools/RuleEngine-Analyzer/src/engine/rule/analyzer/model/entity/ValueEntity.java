package engine.rule.analyzer.model.entity;

import org.json.JSONException;
import org.json.JSONObject;

import catfish.base.Logger;

public class ValueEntity extends Entity{

    private String value;
    private String description;
    
    public ValueEntity(String ruleName, String value, String valueDesc) {
        super(ruleName);
        this.value = value;
        this.description = valueDesc;
    }

    @Override
    public String template() {
        return null;
    }
    
    @Override
    public JSONObject toJson()
	{
		JSONObject obj = new JSONObject();	
		try {
			if(description != null)
				obj.put("name", description);
			if(value != null)
			obj.put("value", value);
		} catch (JSONException e) {
			Logger.get().error(e);
		}
		return obj;
	}
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }   
}
