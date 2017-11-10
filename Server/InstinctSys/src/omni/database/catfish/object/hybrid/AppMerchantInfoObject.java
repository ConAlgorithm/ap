package omni.database.catfish.object.hybrid;

import catfish.base.business.object.BaseObject;

public class AppMerchantInfoObject extends BaseObject 
{
  
	public String appId;

	public String mobile;	// ContactObjects

//	public String qqNumber;	// ContactObjects

	public String bankAccount; // PaymentObject

	public String posName;	// MerchantStoreObject ->CBA
	
	public String s1Name;	// MerchantUserObject
	
	public String d1Name;	// DealerUserObject
	
	public String bd2Name;	// BusinessDevelopUserObject
	
	/** D1手机号 */
	public String d1Mobile;
	
	/** D2姓名 */
    public String d2Name;
    
    /** 门店种类 */
    public Integer storeCategory;

}
