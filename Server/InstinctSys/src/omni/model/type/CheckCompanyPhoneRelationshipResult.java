package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckCompanyPhoneRelationshipResult implements CatfishEnum<Integer>
{

    @Description(text = "领导")
	Leader(10),

    @Description(text = "同事")
	Colleague(20),

	@Description(text = "父母")
	Parent(40),

    @Description(text = "配偶")
	Spouse(50),

    @Description(text = "亲属")
	Kinsfolk(60),

    @Description(text = "朋友")
	Friend(70),

    @Description(text = "无法判断")
	None(80);

	private int value;

    private static final Map<Integer, CheckCompanyPhoneRelationshipResult> typesByValue = new HashMap<Integer, CheckCompanyPhoneRelationshipResult>();

    static {
        for (CheckCompanyPhoneRelationshipResult type : CheckCompanyPhoneRelationshipResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckCompanyPhoneRelationshipResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckCompanyPhoneRelationshipResult(int val)
	{
		this.value = val;
	}
}
