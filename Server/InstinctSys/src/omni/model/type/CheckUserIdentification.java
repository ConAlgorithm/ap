package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckUserIdentification implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),
    
    @Description(text = "正常")
	Normal(10),
	
    @Description(text = "不流畅")
	NotFluent(20),
	
    @Description(text = "重大异常")
	SevereException(30),
    
	@Description(text = "两项均对")
	BothCorrect(40),

	@Description(text = "对一项")
	OneCorrect(50),

	@Description(text = "均不对")
	BothIncorrect(60),

	@Description(text = "一致")
	Consistent(70),

	@Description(text = "不一致")
	InConsistent(80),

	@Description(text = "经反复确认后一致")
	ConsistentAfterRepeatedConfirmation(90);

    private final Integer value;
    
    private static final Map<Integer, CheckUserIdentification> typesByValue = new HashMap<Integer, CheckUserIdentification>();

    static {
        for (CheckUserIdentification type : CheckUserIdentification.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckUserIdentification forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    CheckUserIdentification(Integer val)
    {
    	this.value = val;
    }
}
