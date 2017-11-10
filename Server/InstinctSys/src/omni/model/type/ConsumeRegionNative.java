package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum ConsumeRegionNative implements CatfishEnum<Integer>
{

	@Description(text = "是本地")
	IsNative(1),
	
	@Description(text = "非本地")
	NotNative(0),
	
	@Description(text = "数据为空")
	EmptyData(-1);
	
	private int value;

    private static final Map<Integer, ConsumeRegionNative> typesByValue = new HashMap<Integer, ConsumeRegionNative>();

    static {
        for (ConsumeRegionNative type : ConsumeRegionNative.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static ConsumeRegionNative forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	private ConsumeRegionNative(int val)
	{
	    this.value = val;
	}

	@Override
	public Integer getValue() 
	{
	    return this.value;
	}
}
