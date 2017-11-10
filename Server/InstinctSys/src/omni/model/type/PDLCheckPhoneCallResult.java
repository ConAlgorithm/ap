package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum PDLCheckPhoneCallResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),
    
    @Description(text = "1.正常接听")
	Normal(10),
	
    @Description(text = "3.空号/错号")
	NotExist(20),
	
	@Description(text = "停机/欠费/呼入限制/过期电话")
	ArrearOrStoppedOrCallInLimitedOrObsoleteNumber(30),
    
	@Description(text = "关机/无法接通/占线/无人接听")
	PoweredOffOrNotReachableOrNotAnswered(40);

    private final Integer value;
    
    private static final Map<Integer, PDLCheckPhoneCallResult> typesByValue = new HashMap<Integer, PDLCheckPhoneCallResult>();

    static {
        for (PDLCheckPhoneCallResult type : PDLCheckPhoneCallResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static PDLCheckPhoneCallResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    PDLCheckPhoneCallResult(Integer val)
    {
    	this.value = val;
    }
}
