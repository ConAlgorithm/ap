package omni.model.type;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum RelationshipType implements CatfishEnum<Integer>
{

	  @Description(text = "无")
      None(-1),
      @Description(text = "父母")
      Parent(0),      
      @Description(text = "配偶")
      Spouse(1),
      @Description(text = "亲属")
      Relative(2),
      @Description(text = "同事")
      Colleague(3),
      @Description(text = "朋友")
      Friend(4),
      @Description(text = "兄弟姐妹")
      BrothersAndSisters(5),
      @Description(text = "爸爸")
      Father(6),
      @Description(text = "妈妈")
      Mother(7),
      @Description(text = "老公")
      Husband(8),
      @Description(text = "老婆")
      Wife(9),

      /*********added.*************/
      @Description(text = "儿子")
      Son(10),

      @Description(text = "女儿")
      Daughter(11),

      @Description(text = "兄弟")
      Brother(12),

      @Description(text = "姐妹")
      Sister(13);

      /***************************/
      //@Description(text = "请选择")
      //NoSeleted(-1);
	
    private final Integer value;
    
    private static final Map<Integer, RelationshipType> typesByValue = new HashMap<Integer, RelationshipType>();

    static {
        for (RelationshipType type : RelationshipType.values()) 
        {
            typesByValue.put(type.value, type);
        }
    }
    
    public static RelationshipType forValue(int value) 
    {
        return typesByValue.get(value);
    }
    
    public Integer getValue()
    {
    	return this.value;
    }
    RelationshipType(Integer val)
    {
    	this.value = val;
    }
}
