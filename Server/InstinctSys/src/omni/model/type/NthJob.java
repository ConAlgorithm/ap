package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum NthJob implements CatfishEnum<Integer>
{

	@Description(text = " 无")
    None(0),
	@Description(text = "第1份")
    First(100),
    @Description(text = "第2份")
    Second(200),
    @Description(text = "第3到第5份")
    ThirdToFifth(300),
    @Description(text = "第6份及以上")
    SixAndAbove(1000);
	
    private final Integer value;
    
    private static final Map<Integer, NthJob> typesByValue = new HashMap<Integer, NthJob>();

    static {
        for (NthJob type : NthJob.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static NthJob forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    NthJob(Integer val)
    {
    	this.value = val;
    }
}
