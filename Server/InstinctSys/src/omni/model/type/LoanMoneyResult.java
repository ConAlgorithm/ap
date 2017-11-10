package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum LoanMoneyResult implements CatfishEnum<Integer>
{

	@Description(text =  "无") 
    None(0),
    
	@Description(text =  "成功") 
    Succeeded(10),

    @Description(text = "失败") 
    Failed(20);
    
    private int value;
    
    private static final Map<Integer, LoanMoneyResult> typesByValue = new HashMap<Integer, LoanMoneyResult>();

    static {
        for (LoanMoneyResult type : LoanMoneyResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static LoanMoneyResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
	LoanMoneyResult(int val)
	{
		this.value = val;
	}
	
	public Integer getValue()
	{
		return this.value;
	}
}
