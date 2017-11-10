package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum EducationalBackground implements CatfishEnum<Integer>
{

	@Description(text = "无")
    None(0),
    
	@Description(text = "小学")
    ElementarySchool(1),

    @Description(text = "初中")
    MiddleSchool(2),

    @Description(text = "高中")
    HighSchool(3),

    @Description(text = "中专")
    TechnicalSecondarySchool(4),

    @Description(text = "技校")
    TechnicalSchool(5),

    @Description(text = "职高")
    VocationalHighSchool(6),

    @Description(text = "大专")
    JuniorCollege(10),

   /* @Description(text = "本科")
    @OptionStatusAttribute(text("Obsolete")
    Undergraduates = 20),*/

    @Description(text = "本科及以上")
    AboveUndergraduates(25);

    /*@Description(text("研究生")
    @OptionStatusAttribute(text = "Obsolete")
    Graduates(30),*/

    /*@Description(text = "其他")
    @OptionStatusAttribute(text("Obsolete")
    Others = 0);*/

    /*@Description(text("请选择")
    NoSeleted = -1*/
		        
    private final Integer value;
    
    private static final Map<Integer, EducationalBackground> typesByValue = new HashMap<Integer, EducationalBackground>();

    static {
        for (EducationalBackground type : EducationalBackground.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static EducationalBackground forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    
    EducationalBackground(Integer val)
    {
    	this.value = val;
    }
}