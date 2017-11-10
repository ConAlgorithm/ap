package omni.database.catfish.object;

import catfish.base.ForeignKey;
import catfish.base.business.object.BaseObject;

public class PaymentApplicationObject extends BaseObject 
{
  
	@ForeignKey("catfish.base.business.object.InstallmentApplicationObjects")
	public String applicationId;
  
	@ForeignKey("catfish.base.business.object.PaymentObjects")
	public String paymentId;

}
