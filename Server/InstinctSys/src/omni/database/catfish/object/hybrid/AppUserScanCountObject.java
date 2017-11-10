package omni.database.catfish.object.hybrid;

import catfish.base.business.object.BaseObject;

public class AppUserScanCountObject extends BaseObject
{
    /** 申请单号 */
    public String appId;
    
    /** 用户扫描次数 */
    public Integer scannedCountInHistory;
    
    /** 用户一个月内扫描次数 */
    public Integer scannedCountInPM1;
    
    /** 首次扫描二维码时间 */
    public String firstScanDateTime;
    
    /** 首次扫描二维码时间距今天数 */
    public Integer daysSinceFirstScan;
}
