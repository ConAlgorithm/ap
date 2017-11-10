package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckMerchantCustomerOnSpot implements CatfishEnum<Integer>
{

	@Description(text = "无")
	None(0),

	@Description(text = "是")
	Yes(10),

//	@Description(text = "否")
//	No(20),
    
	@Description(text = "暂时不在（出去吃饭/取钱/去洗手间等等）")
    TemporaryAbsent(20),
	
    @Description(text = "客户离开，且明确称不办理")
    CustomerLeftAndWillNotApply(30);

    private int value;

    private static final Map<Integer, CheckMerchantCustomerOnSpot> typesByValue = new HashMap<Integer, CheckMerchantCustomerOnSpot>();

    static {
        for (CheckMerchantCustomerOnSpot type : CheckMerchantCustomerOnSpot.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckMerchantCustomerOnSpot forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckMerchantCustomerOnSpot(int val)
	{
		this.value = val;
	}
}
