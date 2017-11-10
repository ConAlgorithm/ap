package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum PDLCheckWhetherCompanyNameAndDepartmentIsConsistent implements CatfishEnum<Integer>
{

    @Description(text = "一致")
	Consistent(10),

    @Description(text = "公司名不一致")
	CompanyNameInconsistent(20),

	@Description(text = "部门不一致")
	DepartmentInconsistent(30),

    @Description(text = "都不一致")
	BothInconsistent(40);

	private int value;

    private static final Map<Integer, PDLCheckWhetherCompanyNameAndDepartmentIsConsistent> typesByValue = new HashMap<Integer, PDLCheckWhetherCompanyNameAndDepartmentIsConsistent>();

    static {
        for (PDLCheckWhetherCompanyNameAndDepartmentIsConsistent type : PDLCheckWhetherCompanyNameAndDepartmentIsConsistent.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static PDLCheckWhetherCompanyNameAndDepartmentIsConsistent forValue(int value) 
    {
        return typesByValue.get(value);
    }
        
	@Override
	public Integer getValue() 
	{
		return value;
	}
    
    PDLCheckWhetherCompanyNameAndDepartmentIsConsistent(int val)
	{
		this.value = val;
	}
}
