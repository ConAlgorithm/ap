package omni.database.catfish.object.hybrid;

import java.math.BigDecimal;

import catfish.base.business.object.BaseObject;

public class AppCountObject extends BaseObject
{
    /** 申请单号 */
	public String appId;
	
	/** 申请数 */
	public Integer totalCountInHistory;
	
	/** 批核数 */
    public Integer approvedCountInHistory;
    
    /** 拒绝数 */
    public Integer rejectedCountInHistory;
    
    /** 取消数 */
    public Integer canceledCountInHistory;
    
    /** 批核率 */
    public BigDecimal approvedRateInHistory;
    
    /** 一个月内申请数 */
    public Integer totalCountInPM1;
    
    /** 一个月内批核数 */
    public Integer approvedCountInPM1;
    
    /** 一个月内拒绝数 */
    public Integer rejectedCountInPM1;
    
    /** 一个月内取消数 */
    public Integer canceledCountInPM1;
    
    /** D2FPD7% */
    public BigDecimal d2FPD7Rate;
    
}
