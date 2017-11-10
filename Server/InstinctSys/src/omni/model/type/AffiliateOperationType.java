package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum AffiliateOperationType implements CatfishEnum<Integer>
{

	@Description(text = "无")
	None(0),
	
	@Description(text =  "推荐") 
	Recommend(10),

    @Description(text = "报告") 
	Report(20);
    
    private int value;
    
    private static final Map<Integer, AffiliateOperationType> typesByValue = new HashMap<Integer, AffiliateOperationType>();

    static {
        for (AffiliateOperationType type : AffiliateOperationType.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static AffiliateOperationType forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	AffiliateOperationType(int val)
	{
		this.value = val;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
