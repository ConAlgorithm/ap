package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum TransactionMonitorResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),

    @Description(text = "通过")
	Pass(10),

    @Description(text = "取消申请")
	Canceled(20),

    @Description(text = "拒绝")
	Rejected(30);

	private int value;

    private static final Map<Integer, TransactionMonitorResult> typesByValue = new HashMap<Integer, TransactionMonitorResult>();

    static {
        for (TransactionMonitorResult type : TransactionMonitorResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static TransactionMonitorResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    TransactionMonitorResult(int val)
	{
		this.value = val;
	}
}
