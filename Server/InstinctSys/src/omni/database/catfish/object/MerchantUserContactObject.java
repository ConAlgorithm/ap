package omni.database.catfish.object;

import catfish.base.ForeignKey;
import catfish.base.business.object.BaseObject;

public class MerchantUserContactObject extends BaseObject 
{
  
	@ForeignKey("catfish.base.business.object.ContactObjects")
	public String contactId;
  
	@ForeignKey("catfish.base.business.object.MerchantUserObjects")
	public String merchantUserId;

}
