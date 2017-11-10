package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum MarriageStatus implements CatfishEnum<Integer>
{

	/*@Description(text = "请选择")
    NoSeleted(-50),*/

    @Description(text = "单身")
    Single(-40),

    @Description(text = "恋爱中")
    InLove(-30),

    @Description(text = "已婚")
    Married(1),

    /*@Description(text = "未婚")
    @OptionStatusAttribute(text = "Obsolete")
    NotMarried = 2,*/

    @Description(text = "其他")
    Others(3);
	        
    private final Integer value;
    
    private static final Map<Integer, MarriageStatus> typesByValue = new HashMap<Integer, MarriageStatus>();

    static {
        for (MarriageStatus type : MarriageStatus.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static MarriageStatus forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    MarriageStatus(Integer val)
    {
    	this.value = val;
    }
}
