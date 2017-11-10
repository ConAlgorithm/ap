package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum AddressComparisonResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
	None(0),
	
	@Description(text =  "地址不同") 
	AddressDifferent(10),

    @Description(text = "地址相同或近似") 
	AddressIdenticalOrSimilar(20);
    
    private int value;
    
    private static final Map<Integer, AddressComparisonResult> typesByValue = new HashMap<Integer, AddressComparisonResult>();

    static {
        for (AddressComparisonResult type : AddressComparisonResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static AddressComparisonResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	AddressComparisonResult(int val)
	{
		this.value = val;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
