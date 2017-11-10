package omni.database.catfish.object.hybrid;

import catfish.base.business.object.BaseObject;

public class AppUserScanRecordObject extends BaseObject implements Comparable<AppUserScanRecordObject>
{
    /** 申请单号 */
	public String appId;
	
	/** 扫码时间 */
	public String scanDateTime;
	
	/** 门店ID */
    public String merchantStoreId;
    
    /** 门店名称 */
    public String merchantStoreName;
    
    /** 门店店员ID */
    public String merchantUserId;

    /** 门店店员名称 */
    public String merchantUserName;

    @Override
    public int compareTo(AppUserScanRecordObject anotherObj) {
        if (this.scanDateTime == null) {
            return -1;
        }
        if (anotherObj == null || anotherObj.scanDateTime == null) {
            return 1;
        }
            
        return this.scanDateTime.compareTo(anotherObj.scanDateTime) * -1;
    }
    
}
