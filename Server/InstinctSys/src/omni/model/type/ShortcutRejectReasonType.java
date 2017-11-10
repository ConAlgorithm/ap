package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum ShortcutRejectReasonType implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),

    @Description(text = "信用不良")
	BadCredit(10),

    @Description(text = "申请信息不真实")
	UnrealApplicationInfo(20),

    @Description(text = "伪冒申请")
	FabricatedApplication(30),

	@Description(text = "不良中介")
	BadIntermediary(40),
	
    @Description(text = "套现")
	Arbitrage(50),
	
    @Description(text = "其他")
	Others(1000);

	private int value;

    private static final Map<Integer, ShortcutRejectReasonType> typesByValue = new HashMap<Integer, ShortcutRejectReasonType>();

    static {
        for (ShortcutRejectReasonType type : ShortcutRejectReasonType.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static ShortcutRejectReasonType forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    ShortcutRejectReasonType(int val)
	{
		this.value = val;
	}
}
