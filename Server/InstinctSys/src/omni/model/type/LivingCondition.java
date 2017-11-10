package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum LivingCondition implements CatfishEnum<Integer>
{

	@Description(text = "与父母同住")
    WithParents(0),

    @Description(text = "与他人合租")
    RentWithOthers(1),

    @Description(text = "单人租房")
    RentAlone(2),

    @Description(text = "自有房有贷款")
    OwnHouseWithLoan(3),

    @Description(text = "自有房无贷款")
    OwnHouseWithoutLoan(4),

    @Description(text = "单位集体宿舍")
    CompanyDormitory(5),

    @Description(text = "学校宿舍")
    SchoolDormitory(6),

    @Description(text = "其他")
    Others(7);

    /*@Description(text = "请选择")
    NoSeleted(-1)*/
	
    private final Integer value;
    
    private static final Map<Integer, LivingCondition> typesByValue = new HashMap<Integer, LivingCondition>();

    static {
        for (LivingCondition type : LivingCondition.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static LivingCondition forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    LivingCondition(Integer val)
    {
    	this.value = val;
    }
}
