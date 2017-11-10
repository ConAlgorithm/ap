package engine.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum InOrNotResult implements CatfishEnum<Integer>{
    @Description(text = "无")
    NONE(-1),

    @Description(text = "不是")
    FALSE(0),

    @Description(text = "是")
    TRUE(1);

    private int value;
   InOrNotResult(int value){
       this.value = value;
   }
    @Override
    public Integer getValue() {
        return value;
    }

}
