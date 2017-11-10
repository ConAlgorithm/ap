package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckContactSoundJudgeResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),

    @Description(text = "声音未发现异常")
    NoSoundException(1),

    @Description(text = "声音明显过于年轻")
    SoundIsTooYoung(2),

    @Description(text = "声音明显性别不一致")
    SoundGenderIsWrong(4),

	@Description(text = "明显有人提示")
	SomeoneElseHint(8),
	
	// Followed is customized, no previous definition found in C#/Catfish project
    @Description(text = "明显说谎")
    Lie(16),

    @Description(text = "过程中有人提示（商量/讨论）")
	Discuss(32),

	@Description(text = "声音略显年轻")
	SoundIsABitYoung(64);
	
    private int value;

    private static final Map<Integer, CheckContactSoundJudgeResult> typesByValue = new HashMap<Integer, CheckContactSoundJudgeResult>();

    static {
        for (CheckContactSoundJudgeResult type : CheckContactSoundJudgeResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckContactSoundJudgeResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckContactSoundJudgeResult(int val)
	{
		this.value = val;
	}
}
