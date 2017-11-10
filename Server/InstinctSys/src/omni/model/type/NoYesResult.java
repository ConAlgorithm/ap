package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum NoYesResult implements CatfishEnum<Integer> 
{
	@Description(text = "无")
	None(0),

	@Description(text = "否")
	No(10),

	@Description(text = "是")
	Yes(20);

    private static final Map<Integer, NoYesResult> typesByValue = new HashMap<Integer, NoYesResult>();

    static {
        for (NoYesResult type : NoYesResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static NoYesResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	@Override
	public Integer getValue() 
	{
		return value;
	}
	private int value;

	NoYesResult(int val)
	{
		this.value = val;
	}
}
