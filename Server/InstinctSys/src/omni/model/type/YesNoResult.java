package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum YesNoResult implements CatfishEnum<Integer> 
{
	@Description(text = "无")
	None(0),

	@Description(text = "是")
	Yes(10),

	@Description(text = "否")
	No(20);

    private static final Map<Integer, YesNoResult> typesByValue = new HashMap<Integer, YesNoResult>();

    static {
        for (YesNoResult type : YesNoResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static YesNoResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	@Override
	public Integer getValue() 
	{
		return value;
	}
	private int value;

	YesNoResult(int val)
	{
		this.value = val;
	}
}
