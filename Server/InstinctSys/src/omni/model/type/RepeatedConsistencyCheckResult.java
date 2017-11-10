package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum RepeatedConsistencyCheckResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
	None(0),

	@Description(text = "一致")
	Consistent(10),

	@Description(text = "不一致")
	InConsistent(20),
    
	@Description(text = "经反复确认后一致")
	ConsistentAfterRepeatedConfirmation(30);
	
	private int value;

    private static final Map<Integer, RepeatedConsistencyCheckResult> typesByValue = new HashMap<Integer, RepeatedConsistencyCheckResult>();

    static {
        for (RepeatedConsistencyCheckResult type : RepeatedConsistencyCheckResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static RepeatedConsistencyCheckResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    RepeatedConsistencyCheckResult(int val)
	{
		this.value = val;
	}
}
