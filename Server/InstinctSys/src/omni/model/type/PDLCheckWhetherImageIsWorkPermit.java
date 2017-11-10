package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum PDLCheckWhetherImageIsWorkPermit implements CatfishEnum<Integer>
{

    @Description(text = "是")
	Yes(10),

    @Description(text = "否")
	No(20),

	@Description(text = "工资单")
	Payroll(30),

    @Description(text = "工资到账短信")
	PayrollSMS(40),

    @Description(text = "社保卡")
	SocialSecurityCard(50),

    @Description(text = "健康证")
	HealthCard(60),

    @Description(text = "名片")
	NameCard(70),
	
	@Description(text = "其他")
	Others(80);

	private int value;

    private static final Map<Integer, PDLCheckWhetherImageIsWorkPermit> typesByValue = new HashMap<Integer, PDLCheckWhetherImageIsWorkPermit>();

    static {
        for (PDLCheckWhetherImageIsWorkPermit type : PDLCheckWhetherImageIsWorkPermit.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static PDLCheckWhetherImageIsWorkPermit forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    PDLCheckWhetherImageIsWorkPermit(int val)
	{
		this.value = val;
	}
}
