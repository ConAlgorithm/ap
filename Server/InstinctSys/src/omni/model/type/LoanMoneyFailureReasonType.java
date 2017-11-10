package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum LoanMoneyFailureReasonType implements CatfishEnum<Integer>
{

	@Description(text =  "无") 
	None(0),
	
	@Description(text =  "银行系统问题") 
	CausedByBank(10),
    
	@Description(text =  "本人承认银行卡非本人") 
	AdmitNotOwnBankCard(20),

    @Description(text = "本人不承认银行卡非本人") 
	NotAdmitNotOwnBankCard(30);
    
    private int value;
    
    private static final Map<Integer, LoanMoneyFailureReasonType> typesByValue = new HashMap<Integer, LoanMoneyFailureReasonType>();

    static {
        for (LoanMoneyFailureReasonType type : LoanMoneyFailureReasonType.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static LoanMoneyFailureReasonType forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	LoanMoneyFailureReasonType(int val)
	{
		this.value = val;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
