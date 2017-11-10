package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckIOUIsSmile implements CatfishEnum<Integer>
{

    @Description(text = "有笑容")
    Smile(10),

    @Description(text = "无笑容")
	NoSmile(20),

    @Description(text = "分辨不清")
	None(30);

    private int value;

    private static final Map<Integer, CheckIOUIsSmile> typesByValue = new HashMap<Integer, CheckIOUIsSmile>();

    static {
        for (CheckIOUIsSmile type : CheckIOUIsSmile.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckIOUIsSmile forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckIOUIsSmile(int val)
	{
		this.value = val;
	}
}
