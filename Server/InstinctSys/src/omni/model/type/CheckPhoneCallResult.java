package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckPhoneCallResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),
    
    @Description(text = "1.正常接听")
	Normal(10),
	
    @Description(text = "2.关机")
	PoweredOff(20),
	
    @Description(text = "3.空号/错号")
	NotExist(30),
    
	@Description(text = "4.停机/欠费")
	ArrearOrStopped(40),

	@Description(text = "5.无法接通")
	NotReachable(50),

	@Description(text = "6.无人接听")
	NotAnswered(60),

	@Description(text = "关机/无法接通/占线/无人接听")
	PoweredOffOrNotReachableOrNotAnswered(200),

	@Description(text = "停机/欠费/呼入限制/过期电话")
	ArrearOrStoppedOrCallInLimitedOrObsoleteNumber(400);

    private final Integer value;
    
    private static final Map<Integer, CheckPhoneCallResult> typesByValue = new HashMap<Integer, CheckPhoneCallResult>();

    static {
        for (CheckPhoneCallResult type : CheckPhoneCallResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckPhoneCallResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    CheckPhoneCallResult(Integer val)
    {
    	this.value = val;
    }
}
