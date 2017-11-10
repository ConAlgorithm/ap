package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckIOURingFinger implements CatfishEnum<Integer>
{

    @Description(text = "无")
	None(0),
    
    @Description(text = "戴了")
//  IncludeBoth(10), C#/Catfish bug
    WithRingOn(10),

    @Description(text = "没戴")
//    NotIOU(20),
    WithoutRing(20),
    
    @Description(text = "看不清")
//    WithoutHeadPhoto(30);
    CannotTell(30);
    
    private int value;

    private static final Map<Integer, CheckIOURingFinger> typesByValue = new HashMap<Integer, CheckIOURingFinger>();

    static {
        for (CheckIOURingFinger type : CheckIOURingFinger.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckIOURingFinger forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckIOURingFinger(int val)
	{
		this.value = val;
	}
}
