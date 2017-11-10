package omni.database.catfish.object.hybrid;

import catfish.base.business.object.BaseObject;

public class AppOtherInfoObject extends BaseObject 
{
  
	public String appId;

	public String nickName; // NickName WeixinUserObject->Applicant

	public String bankAccount;	// PaymentObjects ->Applicant

	public String headImageUrl;	// WeixinUserObject->CBB
					
	public Boolean isReported;	// AffiliateOperationObjects->CBB

	public Boolean isRecommended;	// AffiliateOperationObjects->CBB

	public Boolean isOutOfTouch;	// EndUserOutOfTouchRecordObjects->CBB

}
