package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum YLZH_BankCardMobileMatch implements CatfishEnum<String>
{

	@Description(text = "验证成功")
	VerifySuccess("1"),
	
	@Description(text = "验证失败")
	VerifyFailed("2"),
	
	@Description(text = "数据异常")
	DataError("3"),
	
	@Description(text = "系统错误")
	ServerError("4"),
	
	@Description(text = "数据为空")
	EmptyData("-1");

    private final String value;
    
    private static final Map<String, YLZH_BankCardMobileMatch> typesByValue = new HashMap<String, YLZH_BankCardMobileMatch>();

    static {
        for (YLZH_BankCardMobileMatch type : YLZH_BankCardMobileMatch.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static YLZH_BankCardMobileMatch forValue(String value) 
    {
        return typesByValue.get(value);
    }
    
    public String getValue()
    {
    	return this.value;
    }
    
    YLZH_BankCardMobileMatch(String val)
    {
    	this.value = val;
    }
}
