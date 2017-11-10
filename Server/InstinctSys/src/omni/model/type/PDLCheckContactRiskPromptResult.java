package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum PDLCheckContactRiskPromptResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),

    @Description(text = "无风险提示")
    NoRiskPrompt(1),

    @Description(text = "债务缠身")
    ALotOfDebt(2),

    @Description(text = "离职或即将离职")
	LeaveOfficeOrPlanToLeaveOffice(4),

    @Description(text = "恶习：赌博/吸毒")
    GamblingAndDrug(8),

    @Description(text = "大病")
	SeriousIllness(16),

    @Description(text = "其它风险提示")
    OtherRiskPrompt(32);
    
    private int value;
    
    private static final Map<Integer, PDLCheckContactRiskPromptResult> typesByValue = new HashMap<Integer, PDLCheckContactRiskPromptResult>();

    static {
        for (PDLCheckContactRiskPromptResult type : PDLCheckContactRiskPromptResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static PDLCheckContactRiskPromptResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
    PDLCheckContactRiskPromptResult(int val)
	{
		this.value = val;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
