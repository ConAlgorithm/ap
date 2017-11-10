package omni.database.catfish.dao;

import omni.database.catfish.object.MerchantUserContactObject;

public interface MerchantUserContactObjectDao 
{
	
	MerchantUserContactObject getMerchantUserContactObjectById(String merchantUserId);
	
	String getQQNumberByMerchantUserId(String merchantUserId);

	String getMobileByMerchantUserId(String merchantUserId);
}
