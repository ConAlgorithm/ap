package catfish.sales.objects;

import java.math.BigDecimal;
import java.util.Date;

import catfish.sales.enums.ActivityAreaType;

public class ActivityWeixinRedPackObject extends BaseDataObject {
    public String AreaIdentity ;

    public int AreaType ;

    public Date StartTime ;

    public Date EndTime ;

    public BigDecimal MinReward ;

    public BigDecimal MaxReward ;

    public BigDecimal AvgReward ;

    public BigDecimal Offset ;
}
