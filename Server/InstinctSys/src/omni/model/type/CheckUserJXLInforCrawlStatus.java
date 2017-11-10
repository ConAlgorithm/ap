package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckUserJXLInforCrawlStatus implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),
    
    @Description(text = "成功")
	Success(10),
	
    @Description(text = "失败")
	Failure(20),
	
    @Description(text = "客户不合作")
	CustomerDoesNotCooperate(30);
    
    private final Integer value;
    
    private static final Map<Integer, CheckUserJXLInforCrawlStatus> typesByValue = new HashMap<Integer, CheckUserJXLInforCrawlStatus>();

    static {
        for (CheckUserJXLInforCrawlStatus type : CheckUserJXLInforCrawlStatus.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckUserJXLInforCrawlStatus forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    CheckUserJXLInforCrawlStatus(Integer val)
    {
    	this.value = val;
    }
}
