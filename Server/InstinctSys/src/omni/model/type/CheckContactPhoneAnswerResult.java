package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckContactPhoneAnswerResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),
    
    @Description(text = "1.通话人承认是联系人")
	RightPerson(10),
	
    @Description(text = "非本人")
	DifferentPerson(20),
	
    @Description(text = "2.代接，通话人=联系人亲属、朋友、同事等")
	RelatedPersonAnswer(25),
	
    @Description(text = "3.代接，不能确认通话人身份")
	NotRelatedPersonAnswer(30),
    
	@Description(text = "4.通话人为客户本人")
	HimSelf(40),

	@Description(text = "5.通话人为门店人员")
	ShopPerson(60),

	@Description(text = "6.通话人为不相干陌生人")
	UnknownPerson(70),

	@Description(text = "语言障碍，无法沟通")
	DifferentLanguage(80),

	@Description(text = "8.接听人不回应身份")
	HideIdentity(90),

	@Description(text = "9.接听人无语直接挂机")
	JustHangUp(100),

	@Description(text = "接听人为申请人父母")
	ParentsOfApplicant(200),

	@Description(text = "接听人为其他人员")
	OtherPeople(300),

	@Description(text = "接听人不配合/不回应身份/无语挂机")
	IncooperativeOrHideIdentityOrJustHangUp(400);

    private final Integer value;
    
    private static final Map<Integer, CheckContactPhoneAnswerResult> typesByValue = new HashMap<Integer, CheckContactPhoneAnswerResult>();

    static {
        for (CheckContactPhoneAnswerResult type : CheckContactPhoneAnswerResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckContactPhoneAnswerResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    CheckContactPhoneAnswerResult(Integer val)
    {
    	this.value = val;
    }
}
