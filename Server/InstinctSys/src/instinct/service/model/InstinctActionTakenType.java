package instinct.service.model;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

/**
 * This class enumerates the action taken attribute in the asynchronized response from Instinct server. 
 * 
 * @author guoqing
 *
 */
public enum InstinctActionTakenType implements CatfishEnum<String>
{

	@Description(text = "No Action Taken")
	Null(""),

	@Description(text = "Known Fraud")
	KnownFraud("K"),

	@Description(text = "Suspicious")
	Suspicious("S"),
    
	@Description(text = "Under Investigation")
	UnderInvestigation("U"),
	
	@Description(text = "False Positive")
	FalsePositive("F");

    private final String value;
    
    private static final Map<String, InstinctActionTakenType> typesByValue = new HashMap<String, InstinctActionTakenType>();

    static {
        for (InstinctActionTakenType type : InstinctActionTakenType.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static InstinctActionTakenType forValue(String value) 
    {
        return typesByValue.get(value);
    }
    
    public String getValue()
    {
    	return this.value;
    }
    
    InstinctActionTakenType(String val)
    {
    	this.value = val;
    }
}
