package omni.database.catfish.object;

import catfish.base.ForeignKey;
import catfish.base.business.object.BaseObject;

public class MerchantUserPaymentObject extends BaseObject 
{
  
	@ForeignKey("catfish.base.business.object.PaymentObjects")
	public String paymentId;
  
	@ForeignKey("catfish.base.business.object.MerchantUserObjects")
	public String merchantUserId;

}
