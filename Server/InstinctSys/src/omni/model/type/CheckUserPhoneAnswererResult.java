package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckUserPhoneAnswererResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),
    
    @Description(text = "1.通话人承认是申请人")
	User(10),
	
    @Description(text = "2.代接，通话人=联系人亲属、朋友、同事等")
	RelatedPersonAnswer(20),
	
    @Description(text = "3.代接，不能确认通话人身份")
	NotRelatedPersonAnswer(30),
    
	@Description(text = "4.通话人为门店人员")
	ShopPerson(40),

	@Description(text = "5.通话人为不相干陌生人")
	UnknownPerson(50),

	@Description(text = "6.语言障碍，无法沟通")
	DifferentLanguage(60),

	@Description(text = "7.接听人不回应身份")
	HideIdentity(70),

	@Description(text = "8.接听人无语直接挂机")
	JustHangUp(80),

	@Description(text = "9.接听人为其他人员")
	OtherPersonAnswer(90),

	@Description(text = "10.接听人不配合/不回应身份/无语挂机")
	HideIdentityAndJustHangUp(100);

    private final Integer value;
    
    private static final Map<Integer, CheckUserPhoneAnswererResult> typesByValue = new HashMap<Integer, CheckUserPhoneAnswererResult>();

    static {
        for (CheckUserPhoneAnswererResult type : CheckUserPhoneAnswererResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckUserPhoneAnswererResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    CheckUserPhoneAnswererResult(Integer val)
    {
    	this.value = val;
    }
}
