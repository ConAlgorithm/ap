package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import catfish.base.business.object.MerchantUserObject;
import omni.database.catfish.object.hybrid.AppMerchantInfoObject;

public interface MerchantUserObjectDao 
{
	
	MerchantUserObject getMerchantUserObjectById(String merchantUserId);
	
	HashMap<String, AppMerchantInfoObject> getMassiveAppMerchantInfoById(List<String> appIds);

}
