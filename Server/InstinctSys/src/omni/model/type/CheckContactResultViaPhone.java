package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckContactResultViaPhone implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),

    @Description(text = "回答正确")
    CorrectAnswer(10),

    @Description(text = "回答不正确")
    IncorrectAnswer(20),

    @Description(text = "不愿回答")
    NotWillingToAnswer(30),

	@Description(text = "不知道")
	DonotKnow(40),
	
    @Description(text = "追问后挂机")
    HangUpWhenAskedAgain(50);

	private int value;

    private static final Map<Integer, CheckContactResultViaPhone> typesByValue = new HashMap<Integer, CheckContactResultViaPhone>();

    static {
        for (CheckContactResultViaPhone type : CheckContactResultViaPhone.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckContactResultViaPhone forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckContactResultViaPhone(int val)
	{
		this.value = val;
	}
}
