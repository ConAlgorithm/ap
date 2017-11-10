package network.relationship.businessdomain;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum PaymentTagType implements CatfishEnum<Integer> {
    @Description(text = "未表现")
    NoProference(100),
    
    @Description(text = "正常")
    Normal(200),
    
    @Description(text = "逾期")
    Overdue(300)
    ;

    private Integer value;
    
    private PaymentTagType(int Value) {
        this.value = Value;
    }
    @Override
    public Integer getValue() {
        return this.value;
    }

}
