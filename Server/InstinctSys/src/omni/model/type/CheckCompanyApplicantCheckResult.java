package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckCompanyApplicantCheckResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),

    @Description(text = "单位有申请人")
	ApplicantExisted(10),

    @Description(text = "单位无申请人")
	ApplicantNotExisted(20),

    @Description(text = "申请人已离职/离职中/临时工")
	ApplicantDismissed(30),

	@Description(text = "不能确认/不了解有无申请人")
	CannotTell(40),
	
    @Description(text = "经提示新线索有申请人")
	ApplicantExistAfterHint(50),

    @Description(text = "经提示新线索无申请人")
	ApplicantNotExistAfterHint(60),

    @Description(text = "经提示新线索申请人已离职/离职中/临时工")
	ApplicantDismissedAfterHint(70),

    @Description(text = "经提示新线索不确定有无申请人")
	CannotTellAfterHint(80);

	private int value;

    private static final Map<Integer, CheckCompanyApplicantCheckResult> typesByValue = new HashMap<Integer, CheckCompanyApplicantCheckResult>();

    static {
        for (CheckCompanyApplicantCheckResult type : CheckCompanyApplicantCheckResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckCompanyApplicantCheckResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckCompanyApplicantCheckResult(int val)
	{
		this.value = val;
	}
}
