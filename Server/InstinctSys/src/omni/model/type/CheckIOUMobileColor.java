package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckIOUMobileColor implements CatfishEnum<Integer>
{

    @Description(text = "无")
	None(0),
    
    @Description(text = "黑灰色")
    BlackOrGray(10),

    @Description(text = "银白色")
    SliverOrWhite(20),

    @Description(text = "金黄色")
    Golden(30),

    @Description(text = "花色")
    Mixed(40),
 
    @Description(text = "其他色")
    Others(50);

    private int value;

    private static final Map<Integer, CheckIOUMobileColor> typesByValue = new HashMap<Integer, CheckIOUMobileColor>();

    static {
        for (CheckIOUMobileColor type : CheckIOUMobileColor.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckIOUMobileColor forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    CheckIOUMobileColor(int val)
	{
		this.value = val;
	}
}
