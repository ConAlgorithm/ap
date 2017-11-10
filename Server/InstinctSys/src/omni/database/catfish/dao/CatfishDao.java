package omni.database.catfish.dao;

import java.util.HashMap;
import java.util.List;

import omni.database.catfish.object.hybrid.AppOtherInfoObject;

/**
 * This interface defines all possible catfish database query methods required.
 * 
 * @author guoqing
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public interface CatfishDao extends ContactObjectDao, JobInfoObjectDao, UserObjectDao, WeiXinUserObjectDao,
PaymentApplicationObjectDao, PaymentObjectDao, InstallmentApplicationObjectDao, InstallmentPurposeObjectDao,
MerchantUserObjectDao, MerchantUserContactObjectDao, MerchantUserPaymentObjectDao, ContactPersonObjectDao,
BlackListObjectDao, MerchantStoreObjectDao, EndUserExtensionObjectDao, EndUserOutOfTouchRecordObjectDao,
DealerUserObjectDao, BusinessDevelopUserObjectDao, PersonalInfoObjectDao, RuleEngineDao, AffiliateOperationObjectDao,
FuserObjectDao 
{

	HashMap<String, AppOtherInfoObject> getMassiveAppOtherInfoById(List<String> appIds);
	
}
