package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CourtExecutionResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
	None(0),

	@Description(text = "无被执行记录")
	NoRecords(10),

	@Description(text = "有1次以上（含）被执行记录")
	HasRecords(20),

	@Description(text = "官网宕机，无法查询")
	WebsiteDown(30);

	private final Integer value;

	private static final Map<Integer, CourtExecutionResult> typesByValue = new HashMap<Integer, CourtExecutionResult>();

	static {
		for (CourtExecutionResult type : CourtExecutionResult.values()) 
		{
			typesByValue.put(type.value, type);
		}
	}
  
	public static CourtExecutionResult forValue(int value) 
	{
		return typesByValue.get(value);
	}
  
	public Integer getValue() 
	{
		return this.value;
	}

	CourtExecutionResult(Integer val) 
	{
		this.value = val;
	}	
}