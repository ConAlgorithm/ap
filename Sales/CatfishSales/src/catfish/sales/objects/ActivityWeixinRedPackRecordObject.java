package catfish.sales.objects;

import java.math.BigDecimal;

import catfish.sales.enums.WeixinRedPackSourceType;
import catfish.sales.enums.WeixinRedPackStatus;

public class ActivityWeixinRedPackRecordObject extends BaseDataObject {

	public String AppId;
	
	public String MchBillNo ;

    public String ActivityId ;

    public String WXAppId ;

    public String WeixinOpenId ;

    public BigDecimal Reward ;

    public int Status ;

    public int SourceType ;
}
