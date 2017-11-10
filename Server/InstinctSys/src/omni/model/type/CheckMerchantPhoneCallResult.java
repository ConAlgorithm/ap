package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckMerchantPhoneCallResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
	None(0),

	@Description(text = "正常接听")
	Normal(10),

	@Description(text = "空号或错号")
	WrongNumber(20),
    
	@Description(text = "停机或呼入限制")
	ConnectionError(30),
	
    @Description(text = "关机")
	PowerOff(40),
	
    @Description(text = "占线或无人接听")
	BusyOrNoAnswerer(50);

	private int value;

    private static final Map<Integer, CheckMerchantPhoneCallResult> typesByValue = new HashMap<Integer, CheckMerchantPhoneCallResult>();

    static {
        for (CheckMerchantPhoneCallResult type : CheckMerchantPhoneCallResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckMerchantPhoneCallResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckMerchantPhoneCallResult(int val)
	{
		this.value = val;
	}
}
