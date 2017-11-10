package instinct.service.model;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

/**
 * This class enumerates the fraud alert attribute in the asynchronized response from Instinct server. 
 * 
 * @author guoqing
 *
 */
public enum InstinctFraudAlertType implements CatfishEnum<String>
{

	@Description(text = "High Fraud Potential")
	HFP("H"),

	@Description(text = "Suspect")
	Suspect("S"),
    
	@Description(text = "Clean")
	Clean("C");

    private final String value;
    
    private static final Map<String, InstinctFraudAlertType> typesByValue = new HashMap<String, InstinctFraudAlertType>();

    static {
        for (InstinctFraudAlertType type : InstinctFraudAlertType.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static InstinctFraudAlertType forValue(String value) 
    {
        return typesByValue.get(value);
    }
    
    public String getValue()
    {
    	return this.value;
    }
    
    InstinctFraudAlertType(String val)
    {
    	this.value = val;
    }
}
