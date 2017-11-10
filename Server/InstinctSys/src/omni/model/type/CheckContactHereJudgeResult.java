package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckContactHereJudgeResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),

    @Description(text = "在分期现场")
	ContactIsHere(10),

    @Description(text = "不在分期现场")
	ContactIsNotHere(20),

    @Description(text = "无法判断")
	CannotJudge(30);
	
    private int value;

    private static final Map<Integer, CheckContactHereJudgeResult> typesByValue = new HashMap<Integer, CheckContactHereJudgeResult>();

    static {
        for (CheckContactHereJudgeResult type : CheckContactHereJudgeResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckContactHereJudgeResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckContactHereJudgeResult(int val)
	{
		this.value = val;
	}
}
