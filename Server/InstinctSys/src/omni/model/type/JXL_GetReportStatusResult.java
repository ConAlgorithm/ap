package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum JXL_GetReportStatusResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
	None(0),
	
	@Description(text = "超时")
	TimeOut(10),
	
	@Description(text = "出错")
	Error(20),
	
	@Description(text = "成功")
	Success(30);
    
    private int value;
    
    private static final Map<Integer, JXL_GetReportStatusResult> typesByValue = new HashMap<Integer, JXL_GetReportStatusResult>();

    static {
        for (JXL_GetReportStatusResult type : JXL_GetReportStatusResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static JXL_GetReportStatusResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	JXL_GetReportStatusResult(int val)
	{
		this.value = val;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
