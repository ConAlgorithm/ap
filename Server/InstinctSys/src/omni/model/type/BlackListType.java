package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum BlackListType implements CatfishEnum<Integer>
{

	@Description(text = "未知")
	Unknown(0),

	@Description(text = "电话")
	Phone(10),
    
	@Description(text = "QQ")
	QQ(20),
    
    @Description(text = "微信")
	Weixin(30),
	
	@Description(text = "身份证")
	IdNumber(40);

    private final Integer value;
    
    private static final Map<Integer, BlackListType> typesByValue = new HashMap<Integer, BlackListType>();

    static {
        for (BlackListType type : BlackListType.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static BlackListType forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    BlackListType(Integer val)
    {
    	this.value = val;
    }
}
