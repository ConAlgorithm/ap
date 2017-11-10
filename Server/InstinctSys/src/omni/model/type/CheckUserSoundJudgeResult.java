package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckUserSoundJudgeResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),

    @Description(text = "声音背景正常")
	BackgroundSoundNormal(1),

    @Description(text = "性别明显不一致")
	GenderExeception(2),

    @Description(text = "明显不在现场")
	BackgroundSoundExeception(4),

	@Description(text = "明显有人提示")
	SomeoneElseHint(8),
	
    @Description(text = "过程中有人提示（商量/讨论）")
	Discuss(16),

    @Description(text = "无法判断是否在现场")
	UnableJudge(32),

	@Description(text = "明显说谎")
	Lie(64);
	
    private int value;

    private static final Map<Integer, CheckUserSoundJudgeResult> typesByValue = new HashMap<Integer, CheckUserSoundJudgeResult>();

    static {
        for (CheckUserSoundJudgeResult type : CheckUserSoundJudgeResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckUserSoundJudgeResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckUserSoundJudgeResult(int val)
	{
		this.value = val;
	}
}
