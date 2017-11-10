package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CheckContactIdentificationResult implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),
    
    @Description(text = "与填写关系一致")
	IsTheRightRelation(10),
	
    @Description(text = "不是（含性别不一致）")
	NotRightRelationIncludingGender(20),
	
    @Description(text = "不认识")
	DonotKnow(30),
    
	@Description(text = "不配合")
	NotCooperateWith(40);

    private final Integer value;
    
    private static final Map<Integer, CheckContactIdentificationResult> typesByValue = new HashMap<Integer, CheckContactIdentificationResult>();

    static {
        for (CheckContactIdentificationResult type : CheckContactIdentificationResult.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static CheckContactIdentificationResult forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    CheckContactIdentificationResult(Integer val)
    {
    	this.value = val;
    }
}
