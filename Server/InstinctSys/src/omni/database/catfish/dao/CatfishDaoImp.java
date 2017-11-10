package omni.database.catfish.dao;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.ContactPersonObject;
import catfish.base.business.object.DealerUserObject;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.InstalmentApplicationSnapObject;
import catfish.base.business.object.JobInfoObject;
import catfish.base.business.object.MerchantUserObject;
import catfish.base.business.object.PaymentObject;
import catfish.base.business.object.PersonInfoObject;
import catfish.base.business.object.RuleEngineScoreResultObject;
import catfish.base.business.object.UserObject;
import catfish.base.business.object.WeiXinUserObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.database.DatabaseExtractors;
import catfish.base.httpclient.HttpClientApi;
import omni.database.catfish.object.BlackListObject;
import omni.database.catfish.object.BusinessDevelopUserObject;
import omni.database.catfish.object.EndUserExtensionObject;
import omni.database.catfish.object.EndUserOutOfTouchRecordObject;
import omni.database.catfish.object.InstalmentPurposeObject;
import omni.database.catfish.object.MerchantStoreObject;
import omni.database.catfish.object.MerchantUserContactObject;
import omni.database.catfish.object.MerchantUserPaymentObject;
import omni.database.catfish.object.PaymentApplicationObject;
import omni.database.catfish.object.hybrid.AppContactObject;
import omni.database.catfish.object.hybrid.AppContactPersonObject;
import omni.database.catfish.object.hybrid.AppCountObject;
import omni.database.catfish.object.hybrid.AppEndUserExtensionObject;
import omni.database.catfish.object.hybrid.AppFuserObject;
import omni.database.catfish.object.hybrid.AppJobInfoObject;
import omni.database.catfish.object.hybrid.AppMerchantInfoObject;
import omni.database.catfish.object.hybrid.AppOtherInfoObject;
import omni.database.catfish.object.hybrid.AppPersonalInfoObject;
import omni.database.catfish.object.hybrid.AppProductObject;
import omni.database.catfish.object.hybrid.AppPurposeObject;
import omni.database.catfish.object.hybrid.AppRuleEngineScoreResultObject;
import omni.database.catfish.object.hybrid.AppSDAssociatedAppObject;
import omni.database.catfish.object.hybrid.AppUserObject;
import omni.database.catfish.object.hybrid.AppUserScanCountObject;
import omni.database.catfish.object.hybrid.AppUserScanRecordObject;
import omni.database.catfish.object.sales.GeneralResponse;
import omni.database.catfish.object.sales.UserGetUserInfoReqModel;
import omni.database.catfish.object.sales.UserGetUserInfoRespModel;

/**
 * This class provides all possible database queries used in this system interacting with catfish.
 * 
 * @author guoqing
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class CatfishDaoImp implements CatfishDao 
{
    public static String salesUrl = StartupConfig.get("sales.admin.url");
	@Override
	public final ContactObject getContactObjectById(String contactId) 
	{
		Dao<ContactObject> contactDao = Dao.create(
			ContactObject.class, DatabaseApi.database);
		return contactDao.getSingle(getContactByIdSql, CollectionUtils.mapOf("Id", contactId));
	}
	private static final String getContactByIdSql = "SELECT * FROM [ContactObjects] WHERE Id = :Id";

	@Override
	public final JobInfoObject getJobInfoObjectById(String appId) 
	{
		Dao<JobInfoObject> contactDao = Dao.create(
	    		JobInfoObject.class, DatabaseApi.database);
	    return contactDao.getSingle(getJobInfoByAppIdSql, CollectionUtils.mapOf("ApplicationId", appId));
	}
	private static final String getJobInfoByAppIdSql = "SELECT * FROM [JobInfoObjects] WHERE ApplicationId = :ApplicationId";

	@Override
	public final UserObject getUserObjectById(String userId) 
	{
		Dao<UserObject> userDao = Dao.create(
	        UserObject.class, DatabaseApi.database);
	    return userDao.getSingle(getUserByUserIdSql, CollectionUtils.mapOf("Id", userId));
	}
	private static final String getUserByUserIdSql = "SELECT * FROM [UserObjects] WHERE Id = :Id";
	
	@Override
	public final WeiXinUserObject getWeiXinUserObjectById(String weiXinUserId) 
	{
		Dao<WeiXinUserObject> contactDao = Dao.create(
	    	WeiXinUserObject.class, DatabaseApi.database);
	    return contactDao.getSingle(getWeiXinUserByIdSql, CollectionUtils.mapOf("Id", weiXinUserId));
	}
	private static final String getWeiXinUserByIdSql = "SELECT * FROM [WeiXinUserObjects] WHERE Id = :Id";

	@Override
	public final String getPaymentId(String appId) 
	{
		Dao<PaymentApplicationObject> paymentAppDao = Dao.create(
				PaymentApplicationObject.class, DatabaseApi.database);
		PaymentApplicationObject queryResult = paymentAppDao.getSingle(getPaymentAppSql, CollectionUtils.mapOf("ApplicationId", appId));
		return (queryResult == null) ? null : queryResult.paymentId;
	}
	private static final String getPaymentAppSql = "SELECT TOP 1 * FROM [PaymentApplicationObjects] WHERE ApplicationId = :ApplicationId order by LastModified DESC";

	@Override
	public final String getBankAccount(String paymentId) 
	{
	    try {
	        Dao<PaymentObject> paymentDao = Dao.create(
	            PaymentObject.class, DatabaseApi.database);
	        PaymentObject queryResult = paymentDao.getSingle(getPaymentSql, CollectionUtils.mapOf("Id", paymentId));
	        return (queryResult == null) ? null : queryResult.BankAccount;            
        } catch (Exception e) {
            Logger.get().error("getBankAccount function error,paymentId is " +paymentId, e);         
        }
	    return null;
	}
	private static final String getPaymentSql = "SELECT * FROM [PaymentObjects] WHERE Id = :Id";

	@Override
	public final InstallmentApplicationObject getInstallmentApplicationObjectById(String appId) 
	{  
	    try {
	        Dao<InstallmentApplicationObject> instalmentDao = Dao.create(
	            InstallmentApplicationObject.class, DatabaseApi.database);
	        return instalmentDao.getSingle(getInstalmentSql, CollectionUtils.mapOf("Id", appId));            
        } catch (Exception e) {
            Logger.get().error("getInstallmentApplicationObjectById function error,appId is " +appId, e);
        }
	    return null;
	}
	private static final String getInstalmentSql = "SELECT * FROM [InstallmentApplicationObjects] WHERE Id = :Id";

	@Override
	public final InstalmentApplicationSnapObject getInstalmentApplicationSnapObjectByAppId(String appId) 
	{
	    try {
	        Dao<InstalmentApplicationSnapObject> instalmentSnapDao = Dao.create(
	            InstalmentApplicationSnapObject.class, DatabaseApi.database);
	        return instalmentSnapDao.getSingle(getInstalmentSnapSql, CollectionUtils.mapOf("AppId", appId));            
        } catch (Exception e) {
            Logger.get().error("getInstalmentApplicationSnapObjectByAppId function error,appId is " +appId, e);
        }
	    return null;
	}
	private static final String getInstalmentSnapSql = "SELECT * FROM [InstalmentApplicationSnapObjects] WHERE InstalmentAppId = :AppId";

	@Override
	public final InstalmentPurposeObject getInstalmentPurposeObjectById(String id) 
	{
		Dao<InstalmentPurposeObject> purposeDao = Dao.create(
				InstalmentPurposeObject.class, DatabaseApi.database);
	    return purposeDao.getSingle(getPurposeSql, CollectionUtils.mapOf("Id", id));
	}
	private static final String getPurposeSql = "SELECT * FROM [InstalmentPurposeObjects] WHERE Id = :Id";

	@Override
	public final MerchantUserObject getMerchantUserObjectById(String merchantUserId) 
	{
	    try {
	        Dao<MerchantUserObject> paymentDao = Dao.create(
	            MerchantUserObject.class, DatabaseApi.database);
	        return paymentDao.getSingle(getS1UserSql, CollectionUtils.mapOf("Id", merchantUserId));            
        } catch (Exception e) {
            Logger.get().error("getMerchantUserObjectById function error,merchantUserId is " +merchantUserId, e);
        }
	    return null;
	}
	private static final String getS1UserSql = "SELECT * FROM [MerchantUserObjects] WHERE Id = :Id";

	@Override
	public final MerchantUserContactObject getMerchantUserContactObjectById(String merchantUserId) 
	{
		Dao<MerchantUserContactObject> paymentDao = Dao.create(
				MerchantUserContactObject.class, DatabaseApi.database);
	    return paymentDao.getSingle(getS1ContactSql, CollectionUtils.mapOf("MerchantUserId", merchantUserId));
	}
	private static final String getS1ContactSql = "SELECT * FROM [MerchantUserContactObjects] WHERE MerchantUserId = :MerchantUserId";

	@Override
	public final MerchantUserPaymentObject getMerchantUserPaymentId(String merchantUserId) 
	{
	    try {
	        Dao<MerchantUserPaymentObject> paymentDao = Dao.create(
	            MerchantUserPaymentObject.class, DatabaseApi.database);
	        return paymentDao.getSingle(getS1PaymentIdSql, CollectionUtils.mapOf("MerchantUserId", merchantUserId));            
        } catch (Exception e) {
            Logger.get().error("getMerchantUserPaymentId function error,merchantUserId is " +merchantUserId, e);
        }
	    return null;
	}
	private static final String getS1PaymentIdSql = "SELECT * FROM [MerchantUserPaymentObjects] WHERE MerchantUserId = :MerchantUserId";

	@Override
	public final RuleEngineScoreResultObject getRuleEngineScoreResultObjectByResultId(String ruleEngineResultId) 
	{
		Dao<RuleEngineScoreResultObject> ruleEngineScoreDao = Dao.create(
				RuleEngineScoreResultObject.class, DatabaseApi.database);
	    return ruleEngineScoreDao.getSingle(getRuleEngineScoreResultObjectSql, CollectionUtils.mapOf("RuleEngineResultId", ruleEngineResultId));
	}
	private static final String getRuleEngineScoreResultObjectSql = "SELECT * FROM [RuleEngineScoreResultObjects] WHERE RuleEngineResultId = :RuleEngineResultId";

	@Override
	public final String getRuleEngineResultId(String appId) 
	{
		 return DatabaseApi.querySingleStringOrDefault(getRuleEngineResultIdSql,
					CollectionUtils.mapOf("AppId", appId), null);
	}
	private static final String getRuleEngineResultIdSql = "SELECT Id FROM [RuleEngineResultObjects] WHERE AppId = :AppId";

	@Override
	public final String getRuleEngineFinalCheckResultId(String appId) 
	{
		 String queryResult = DatabaseApi.querySingleStringOrDefault(getRuleEngineFinalCheckResultIdSql,
					CollectionUtils.mapOf("AppId", appId), null);
		 return queryResult == null ? null : queryResult;
	}
	private static final String getRuleEngineFinalCheckResultIdSql = "SELECT Id FROM [RuleEngineResultObjects] WHERE AppId = :AppId AND DecisionJobName='FinalCheck'";

	@Override
	public final String getStoreLevel(String ruleEngineResultId) 
	{
		 return DatabaseApi.querySingleStringOrDefault(getStoreLevelSql,
					CollectionUtils.mapOf("RuleEngineResultId", ruleEngineResultId), "");
	}
	private static final String getStoreLevelSql = "SELECT StoreLevel FROM [RuleEngineScoreResultObjects] WHERE RuleEngineResultId = :RuleEngineResultId";

	@Override
	public final String getQQNumberByMerchantUserId(String merchantUserId) 
	{
		String qq = null;
		try
		{
			qq = DatabaseApi.querySingleString(getQQNumberByMerchantUserIdsql,
					CollectionUtils.mapOf("MerchantUserId", merchantUserId));
		}
		catch (NullPointerException e)
		{
			return null;
		}
		return qq == null ? null : qq;
	}
	private static final String getQQNumberByMerchantUserIdsql = "SELECT Content FROM [ContactObjects] WHERE Id IN ( "
			+ "SELECT ContactID FROM [MerchantUserContactObjects] WHERE MerchantUserId = :MerchantUserId ) AND ContactType=1 ORDER BY LastModified DESC";

	@Override
	public final String getMobileByMerchantUserId(String merchantUserId) 
	{
		String mobile = null;
		try
		{
			mobile = DatabaseApi.querySingleString(getMobileByMerchantUserIdsql,
					CollectionUtils.mapOf("MerchantUserId", merchantUserId));
		}
		catch (NullPointerException e)
		{
			return null;
		}
		return mobile == null ? null : mobile;
	}
	private static final String getMobileByMerchantUserIdsql = "SELECT Content FROM [ContactObjects] WHERE Id IN ( "
			+ "SELECT ContactID FROM [MerchantUserContactObjects] WHERE MerchantUserId = :MerchantUserId ) AND ContactType=2 ORDER BY LastModified DESC";

	@Override
	public final String getContactPersonTypeById(String contactPersonId) 
	{
		 return DatabaseApi.querySingleString(getContactPersonTypeSql,
					CollectionUtils.mapOf("Id", contactPersonId));
	}
	private static final String getContactPersonTypeSql = "SELECT ContactPersonType FROM [ContactPersonObjects] WHERE Id = :Id";

	@Override
	public final ContactPersonObject getContactPersonObjectById(String contactPersonId) 
	{
		Dao<ContactPersonObject> contactPersonDao = Dao.create(
				ContactPersonObject.class, DatabaseApi.database);
	    return contactPersonDao.getSingle(getContactPersonSql, CollectionUtils.mapOf("Id", contactPersonId));
	}
	private static final String getContactPersonSql = "SELECT * FROM [ContactPersonObjects] WHERE Id = :Id";

//	@Override
//	public final int getNumOfContactByAppId(String appId) {
//		 return DatabaseApi.querySingleInteger(getNumOfContactSql,
//					CollectionUtils.mapOf("AppId", appId));
//	}
//	private static final String getNumOfContactSql = "SELECT count(*) FROM [ContactPersonObjects] WHERE AppId = :AppId";

	@Override
	public final List<String> getListOfContactPersonIdByAppId(String appId) 
	{
		 return DatabaseApi.queryMultipleResults(getListOfContactPersonIdSql,
					CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.STRING_EXTRACTOR);
	}
	private static final String getListOfContactPersonIdSql = "SELECT Id FROM [ContactPersonObjects] WHERE AppId = :AppId";

	@Override
	public final Float getPersonalInfoScore(String ruleEngineResultId) 
	{
		 return DatabaseApi.querySingleResult(getPersonalInfoScoreSql,
					CollectionUtils.mapOf("RuleEngineResultId", ruleEngineResultId), DatabaseExtractors.DECIMAL_EXTRACTOR).floatValue();
	}
	private static final String getPersonalInfoScoreSql = "SELECT PersonalInfoScore FROM [RuleEngineScoreResultObjects] WHERE RuleEngineResultId = :RuleEngineResultId";

	@Override
	public final Float getInvestigationScore(String ruleEngineResultId) 
	{
		 return DatabaseApi.querySingleResult(getInvestigationScoreSql,
					CollectionUtils.mapOf("RuleEngineResultId", ruleEngineResultId), DatabaseExtractors.DECIMAL_EXTRACTOR).floatValue();
	}
	private static final String getInvestigationScoreSql = "SELECT InvestigationScore FROM [RuleEngineScoreResultObjects] WHERE RuleEngineResultId = :RuleEngineResultId";

	@Override
	public final Float getCreditReferenceScore(String ruleEngineResultId) 
	{
		 return DatabaseApi.querySingleResult(getCreditReferenceScoreSql,
					CollectionUtils.mapOf("RuleEngineResultId", ruleEngineResultId), DatabaseExtractors.DECIMAL_EXTRACTOR).floatValue();
	}
	private static final String getCreditReferenceScoreSql = "SELECT CreditReferenceScore FROM [RuleEngineScoreResultObjects] WHERE RuleEngineResultId = :RuleEngineResultId";

	@Override
	public final Float getFraudCheckScore(String ruleEngineResultId) 
	{
		 return DatabaseApi.querySingleResult(getFraudCheckScoreSql,
					CollectionUtils.mapOf("RuleEngineResultId", ruleEngineResultId), DatabaseExtractors.DECIMAL_EXTRACTOR).floatValue();
	}
	private static final String getFraudCheckScoreSql = "SELECT FraudCheckScore FROM [RuleEngineScoreResultObjects] WHERE RuleEngineResultId = :RuleEngineResultId";

	@Override
	public final Float getApplicationScore(String ruleEngineResultId) 
	{
		 return DatabaseApi.querySingleResult(getApplicationScoreSql,
					CollectionUtils.mapOf("RuleEngineResultId", ruleEngineResultId), DatabaseExtractors.DECIMAL_EXTRACTOR).floatValue();
	}
	private static final String getApplicationScoreSql = "SELECT ApplicationScore FROM [RuleEngineScoreResultObjects] WHERE RuleEngineResultId = :RuleEngineResultId";

	@Override
	public final Float getBehaviorScore(String ruleEngineResultId) 
	{
		 return DatabaseApi.querySingleResult(getBehaviorScoreSql,
					CollectionUtils.mapOf("RuleEngineResultId", ruleEngineResultId), DatabaseExtractors.DECIMAL_EXTRACTOR).floatValue();
	}
	private static final String getBehaviorScoreSql = "SELECT BehaviorScore FROM [RuleEngineScoreResultObjects] WHERE RuleEngineResultId = :RuleEngineResultId";

	@Override
	public final ArrayList<BlackListObject> getBlackListObject(int numOfList) 
	{
		String getBlackListObjSql = "SELECT TOP " + numOfList + " * FROM [BlackListObjects] WHERE SOURCE=\'IA\'";
		return (ArrayList<BlackListObject>) DatabaseApi.queryMultipleResults(getBlackListObjSql,
					null, omni.database.DatabaseExtractors.BLACKLISTOBJECT_EXTRACTOR);
	}

	@Override
	public final List<String> getPOSAppIds(int yearOfData, int monthOfData) 
	{
		return DatabaseApi.queryMultipleResults(getPOSAppIdByDateSql,
				CollectionUtils.mapOf("Year", yearOfData, "Month", monthOfData), DatabaseExtractors.STRING_EXTRACTOR);
	}
	private static final String getPOSAppIdByDateSql = "SELECT Id FROM [InstallmentApplicationObjects] WHERE (InstalmentChannel=0 OR InstalmentChannel=1) AND "
			+ "YEAR(InstallmentStartedOn)= :Year and MONTH(InstallmentStartedOn)= :Month ORDER BY InstallmentStartedOn DESC"; //0&1 POS 3 PDL

	@Override
	public final List<String> getPOSAppIds(int numOfApps) 
	{
		String getAppIdsSql = "SELECT TOP " + numOfApps + " Id FROM [InstallmentApplicationObjects] WHERE InstalmentChannel=0 OR InstalmentChannel=1"; //0&1 POS 3 PDL
		return DatabaseApi.queryMultipleResults(getAppIdsSql,
					null, DatabaseExtractors.STRING_EXTRACTOR);
	}
	
	@Override
	public final List<String> getAppIds(int numOfApps) 
	{
		String getAppIdsSql = "SELECT TOP " + numOfApps + " Id FROM [InstallmentApplicationObjects]"; //0&1 POS 3 PDL
		return DatabaseApi.queryMultipleResults(getAppIdsSql,
					null, DatabaseExtractors.STRING_EXTRACTOR);
	}

	@Override
	public final List<String> getAppIds(int yearOfData, int monthOfData) 
	{
		return DatabaseApi.queryMultipleResults(getAppIdByDateSql,
				CollectionUtils.mapOf("Year", yearOfData, "Month", monthOfData), DatabaseExtractors.STRING_EXTRACTOR);
	}
	private static final String getAppIdByDateSql = "SELECT Id FROM [InstallmentApplicationObjects] "
			+ "WHERE YEAR(InstallmentStartedOn)= :Year and MONTH(InstallmentStartedOn)= :Month ORDER BY InstallmentStartedOn DESC"; //0&1 POS 3 PDL

	
	@Override
	public final ArrayList<BlackListObject> getAllIABlackListObject() 
	{
		String getBlackListObjSql = "SELECT * FROM [BlackListObjects] WHERE SOURCE=\'IA\'";
		return (ArrayList<BlackListObject>) DatabaseApi.queryMultipleResults(getBlackListObjSql,
					null, omni.database.DatabaseExtractors.BLACKLISTOBJECT_EXTRACTOR);
	}

	@Override
	public final int getNumOfIABlackListObject() 
	{
		String getBlackListObjSql = "SELECT COUNT(*) FROM [BlackListObjects] WHERE SOURCE=\'IA\'";
		return DatabaseApi.querySingleInteger(getBlackListObjSql, null);
	}

	@Override
	public final MerchantStoreObject getMerchantStoreObjectById(String merchantStoreId) 
	{
	    try {
	        Dao<MerchantStoreObject> merchantStoreDao = Dao.create(
	            MerchantStoreObject.class, DatabaseApi.database);
	        return merchantStoreDao.getSingle(getMerchantStoreSql, CollectionUtils.mapOf("Id", merchantStoreId));            
        } catch (Exception e) {
            Logger.get().error("getMerchantStoreObjectById function error,merchantStoreId is " +merchantStoreId, e);
        }
	    return null;
	}
	private static final String getMerchantStoreSql = "SELECT * FROM [MerchantStoreObjects] WHERE Id = :Id";

	@Override
	public final EndUserExtensionObject getEndUserExtensionObjectByUserId(String userId) 
	{
		Dao<EndUserExtensionObject> endUserDao = Dao.create(
				EndUserExtensionObject.class, DatabaseApi.database);
	    return endUserDao.getSingle(getEndUserSql, CollectionUtils.mapOf("UserId", userId));
	}
	private static final String getEndUserSql = "SELECT * FROM [EndUserExtensionObjects] WHERE Id = :UserId";

	@Override
	public final EndUserOutOfTouchRecordObject getEndUserOutOfTouchRecordObjectByUserId(String endUserId) 
	{
		Dao<EndUserOutOfTouchRecordObject> endUserDao = Dao.create(
				EndUserOutOfTouchRecordObject.class, DatabaseApi.database);
	    return endUserDao.getSingle(getEndUserRecordSql, CollectionUtils.mapOf("EndUserId", endUserId));
	}
	private static final String getEndUserRecordSql = "SELECT * FROM [EndUserOutOfTouchRecordObjects] WHERE EndUserId = :EndUserId";

	@Override
	public final DealerUserObject getDealerUserObjectById(String dealerUserId) 
	{
		Dao<DealerUserObject> dUserDao = Dao.create(
				DealerUserObject.class, DatabaseApi.database);
	    return dUserDao.getSingle(getDealerUserSql, CollectionUtils.mapOf("Id", dealerUserId));
	}
	private static final String getDealerUserSql = "SELECT * FROM [DealerUserObjects] WHERE Id = :Id";

	@Override
	public final BusinessDevelopUserObject getBusinessDevelopUserObjectById(String businessDevelopUserId) 
	{
		Dao<BusinessDevelopUserObject> bdUserDao = Dao.create(
				BusinessDevelopUserObject.class, DatabaseApi.database);
	    return bdUserDao.getSingle(getBDUserSql, CollectionUtils.mapOf("Id", businessDevelopUserId));
	}
	private static final String getBDUserSql = "SELECT * FROM [BusinessDevelopUserObjects] WHERE Id = :Id";

	@Override
	public final PersonInfoObject getPersonalInfoObjectById(String appId) 
	{
		Dao<PersonInfoObject> perDao = Dao.create(
				PersonInfoObject.class, DatabaseApi.database);
	    return perDao.getSingle(getPerInfoSql, CollectionUtils.mapOf("AppId", appId));
	}
	private static final String getPerInfoSql = "SELECT * FROM [PersonalInfoObjects] WHERE AppId = :AppId";

//	@Override
//	public final AffiliateOperationType getAffiliateOperationTypebyAppId(String appId) {
//		 Integer tag=DatabaseApi.querySingleResult(getAffiliateOperationTypeSql,
//					CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.INTEGER_EXTRACTOR);
//		 return tag==null?AffiliateOperationType.forValue(0):
//			 AffiliateOperationType.forValue(tag);
//	}
//	private static final String getAffiliateOperationTypeSql = "SELECT [Tag] FROM [catfish].[dbo].[AffiliateOperationObjects] WHERE AppId= :AppId";

	@Override
	public final Boolean getIsRecommendedbyAppId(String appId) 
	{
		Integer tag = null;
		try
		{
			tag = DatabaseApi.querySingleResult(getIsRecommendedSql,
					CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.INTEGER_EXTRACTOR);
		}
		catch (NullPointerException e)
		{
			return false;
		}
		return tag == null ? false : true;
	}
	private static final String getIsRecommendedSql = "SELECT [Tag] FROM [catfish].[dbo].[AffiliateOperationObjects] WHERE AppId= :AppId and Tag=10";

	@Override
	public final Boolean getIsReportedbyAppId(String appId) 
	{
		Integer tag = null;
		try
		{
			tag = DatabaseApi.querySingleResult(getIsReportedSql,
				CollectionUtils.mapOf("AppId", appId), DatabaseExtractors.INTEGER_EXTRACTOR);
		}
		catch (NullPointerException e)
		{
			return false;
		}
		return tag == null ? false : true;
	}
	private static final String getIsReportedSql = "SELECT [Tag] FROM [catfish].[dbo].[AffiliateOperationObjects] WHERE AppId= :AppId and Tag=20";

	/**
     * <p>通过AppId列表获取分期申请表数据集合</p>
     * <p>table：分期申请表</p>
     * @param appIds
     * @return
     */
	@Override
	public final HashMap<String, InstallmentApplicationObject> getMassiveInstallmentApplicationById(List<String> appIds) 
	{
		String getInstallmentApplicationObjectSql = "SELECT * FROM [InstallmentApplicationObjects] WHERE ID IN ({0}{1}{2})";
		getInstallmentApplicationObjectSql = MessageFormat.format(getInstallmentApplicationObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<InstallmentApplicationObject> applist = 
				(ArrayList<InstallmentApplicationObject>) DatabaseApi.queryMultipleResults(getInstallmentApplicationObjectSql,
					null, omni.database.DatabaseExtractors.INSTALLMENTAPPLICATIONOBJECT_EXTRACTOR);
			
		HashMap<String, InstallmentApplicationObject> res = new HashMap<>();
		applist.forEach(app ->
		{
			res.put(app.Id, app);
		});
		return res;
	}

	@Override
	public final HashMap<String, AppPurposeObject> getMassiveAppPurposeById(List<String> appIds) 
	{
		String getAppPurposeObjectSql = 
				"SELECT T2.AppID, T1.Id, T1.Message, T1.IsActive, T1.Title FROM [InstalmentPurposeObjects] T1,"
				+ "(SELECT ID AS \"AppID\", InstalmentPurposeId FROM [InstallmentApplicationObjects] WHERE ID IN ({0}{1}{2}) ) T2 "
				+ "WHERE T1.ID=T2.InstalmentPurposeId";
		getAppPurposeObjectSql = MessageFormat.format(getAppPurposeObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppPurposeObject> purposelist = 
				(ArrayList<AppPurposeObject>) DatabaseApi.queryMultipleResults(getAppPurposeObjectSql,
					null, omni.database.DatabaseExtractors.APPPURPOSEOBJECT_EXTRACTOR);
			
		HashMap<String, AppPurposeObject> res = new HashMap<>();
		purposelist.forEach(purpose ->
		{
			res.put(purpose.appId, purpose);
		});
		return res;
	}

	@Override
	public final HashMap<String, AppJobInfoObject> getMassiveAppJobInfoById(List<String> appIds) 
	{
		String getAppJobInfoObjectSql = 
			"SELECT ApplicationId AS \"AppID\", Id, CompanyAddress, CompanyName, JobType, Payday, NthJob, JobLength, IsCurrent,"
			+ " ApplicationId, DepartmentName, CompanyTelId, MonthlyIncome, JobPositionType, DateJoined"
			+ " FROM [JobInfoObjects] WHERE ApplicationId IN ({0}{1}{2}) ";
		getAppJobInfoObjectSql = MessageFormat.format(getAppJobInfoObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppJobInfoObject> jobInfoList = 
				(ArrayList<AppJobInfoObject>) DatabaseApi.queryMultipleResults(getAppJobInfoObjectSql,
					null, omni.database.DatabaseExtractors.APPJOBINFOOBJECT_EXTRACTOR);

		HashMap<String, AppJobInfoObject> res = new HashMap<>();
		jobInfoList.forEach(jobInfo ->
		{
			res.put(jobInfo.appId, jobInfo);
		});
		return res;
	}

	@Override
	public final HashMap<String, AppUserObject> getMassiveAppUserById(List<String> appIds) 
	{
		String getAppUserObjectSql = 
				"SELECT T2.AppID, T1.Id, T1.IsRegistered, T1.UserName, T1.UserNameLower, T1.Password, T1.PasswordSalt,"
				+ " T1.LastLogInDate, T1.FailedPasswordAttemptCount, T1.FailedPasswordAttemptTime, T1.MobileValidatedOn,"
				+ " T1.WeiXinUserId, T1.WeiXinAccountFollowed, T1.MobileContactId"
				+ " FROM [UserObjects] T1,"
				+ " (SELECT ID AS \"AppID\", UserId FROM [InstallmentApplicationObjects] WHERE ID IN ({0}{1}{2}) ) T2 "
				+ " WHERE T1.Id=T2.UserId";
		getAppUserObjectSql = MessageFormat.format(getAppUserObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppUserObject> usrList = 
				(ArrayList<AppUserObject>) DatabaseApi.queryMultipleResults(getAppUserObjectSql,
					null, omni.database.DatabaseExtractors.APPUSEROBJECT_EXTRACTOR);

		HashMap<String, AppUserObject> res = new HashMap<>();
		usrList.forEach(usr ->
		{
			res.put(usr.appId, usr);
		});
		return res;
	}

	@Override
	public final HashMap<String, AppEndUserExtensionObject> getMassiveAppEndUserExtensionById(List<String> appIds) 
	{
		String getAppEndUserExtensionObjectSql = 
				"SELECT T2.AppID, T1.Id, T1.UserType, T1.UserAccountStatus, T1.IsStudent, T1.Education, T1.PersonalInfoFilledOn,"
				+ " T1.MerchantStoreId, T1.IdResultId, T1.IdName, T1.RealNameFilledOn,"
				+ " T1.IdNumber, T1.MerchantUserId, T1.UserCreditLevel, T1.IsAvailable"
				+ " FROM [EndUserExtensionObjects] T1,"
				+ " (SELECT ID AS \"AppID\", UserId FROM [InstallmentApplicationObjects] WHERE ID IN ({0}{1}{2}) ) T2 "
				+ " WHERE T1.Id=T2.UserId";
		getAppEndUserExtensionObjectSql = MessageFormat.format(getAppEndUserExtensionObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppEndUserExtensionObject> endUsrList = 
				(ArrayList<AppEndUserExtensionObject>) DatabaseApi.queryMultipleResults(getAppEndUserExtensionObjectSql,
					null, omni.database.DatabaseExtractors.APPENDUSEREXTENSIONOBJECT_EXTRACTOR);

		HashMap<String, AppEndUserExtensionObject> res = new HashMap<>();
		endUsrList.forEach(endUsr ->
		{
			res.put(endUsr.appId, endUsr);
		});
		return res;
	}

	@Override
	public final HashMap<String, AppPersonalInfoObject> getMassiveAppPersonalInfoById(List<String> appIds) 
	{
		String getAppPersonalInfoObjectSql = 		
//				"SELECT AppId AS \"AppID\", Id, MarriageStatus, LivingAddress, LivingCondition, QQContactId"
//				+ " FROM [PersonalInfoObjects] WHERE AppId IN ({0}{1}{2}) ORDER BY LastModified DESC";
				" SELECT T.AppId AS AppId, T.Id, T.MarriageStatus, T.LivingAddress, T.LivingCondition, T.QQContactId, T.LastModified "
		+ " FROM (SELECT T.AppId, T.Id, T.MarriageStatus, T.LivingAddress, T.LivingCondition, T.QQContactId, T.LastModified, ROW_NUMBER()"
		+ " OVER (PARTITION BY T.AppId ORDER BY T.LastModified DESC) RN"
		+ " FROM [PersonalInfoObjects] T"
		+ " WHERE T.AppId IN ({0}{1}{2})) T WHERE T.RN=1";
		getAppPersonalInfoObjectSql = MessageFormat.format(getAppPersonalInfoObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppPersonalInfoObject> personInfoList = 
				(ArrayList<AppPersonalInfoObject>) DatabaseApi.queryMultipleResults(getAppPersonalInfoObjectSql,
					null, omni.database.DatabaseExtractors.APPPERSONALINFOOBJECT_EXTRACTOR);

		HashMap<String, AppPersonalInfoObject> res = new HashMap<>();
		personInfoList.forEach(personInfo ->
		{
			res.put(personInfo.appId, personInfo);
		});
		return res;
	}

	@Override
	public final HashMap<String, InstalmentApplicationSnapObject> getMassiveInstalmentApplicationSnapById(List<String> appIds) 
	{
		String getInstalmentApplicationSnapObjectSql = "SELECT * FROM [InstalmentApplicationSnapObjects] WHERE InstalmentAppId IN ({0}{1}{2})";
		getInstalmentApplicationSnapObjectSql = MessageFormat.format(getInstalmentApplicationSnapObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<InstalmentApplicationSnapObject> appSnaplist = 
				(ArrayList<InstalmentApplicationSnapObject>) DatabaseApi.queryMultipleResults(getInstalmentApplicationSnapObjectSql,
					null, omni.database.DatabaseExtractors.INSTALMENTAPPLICATIONSNAPOBJECT_EXTRACTOR);
			
		HashMap<String, InstalmentApplicationSnapObject> res = new HashMap<>();
		appSnaplist.forEach(appSnap ->
		{
			res.put(appSnap.InstalmentAppId, appSnap);
		});
		return res;
	}

	@Override
	public final HashMap<String, AppRuleEngineScoreResultObject> getMassiveAppRuleEngineScoreResultById(List<String> appIds) 
	{
		String getAppRuleEngineScoreResultObjectSql = 
				"SELECT T2.AppID, T1.Id, T1.RuleEngineResultId, T1.ApplicationScore, T1.BehaviorScore, T1.ConsistencyCheckScore,"
				+ " T1.CreditReferenceScore, T1.FraudCheckScore, T1.InvestigationScore, T1.PersonalInfoScore,"
				+ " T1.SocialRelationScore, T1.StoreLevel"
				+ " FROM [RuleEngineScoreResultObjects] T1,"
				+ " (SELECT AppId AS \"AppID\", Id AS \"RuleEngineFinalCheckResId\" FROM [RuleEngineResultObjects] WHERE DecisionJobName={0}FinalCheck{1} AND AppID IN ({2}{3}{4}) ) T2 "
				+ " WHERE T1.RuleEngineResultId=T2.RuleEngineFinalCheckResId";
		getAppRuleEngineScoreResultObjectSql = MessageFormat.format(getAppRuleEngineScoreResultObjectSql, "\'", "\'", "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppRuleEngineScoreResultObject> ruleEngineReslist = 
				(ArrayList<AppRuleEngineScoreResultObject>) DatabaseApi.queryMultipleResults(getAppRuleEngineScoreResultObjectSql,
					null, omni.database.DatabaseExtractors.APPRULEENGINESCORERESULTOBJECT_EXTRACTOR);
			
		HashMap<String, AppRuleEngineScoreResultObject> res = new HashMap<>();
		ruleEngineReslist.forEach(ruleEngineRes ->
		{
			res.put(ruleEngineRes.appId, ruleEngineRes);
		});
		return res;
	}

	@Override
	public final HashMap<String, List<AppContactPersonObject>> getMassiveAppContactPersonById(List<String> appIds) 
	{
		String getContactPersonObjectSql = 
				"SELECT T1.Content AS Mobile, T2.* FROM ContactObjects T1,"
				+ " (SELECT * FROM ContactPersonObjects WHERE"
				+ " AppId IN ({0}{1}{2}) ) T2"
				+ " WHERE T1.Id=T2.MobileContactId";
		getContactPersonObjectSql = MessageFormat.format(getContactPersonObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppContactPersonObject> contactPersonList = 
				(ArrayList<AppContactPersonObject>) DatabaseApi.queryMultipleResults(getContactPersonObjectSql,
					null, omni.database.DatabaseExtractors.APPCONTACTPERSONOBJECT_EXTRACTOR);

		HashMap<String, List<AppContactPersonObject>> res = new HashMap<>();
		appIds.forEach(appId->
		{
			List<AppContactPersonObject> tmp = new ArrayList<>(); 
			Iterator<AppContactPersonObject> ite = contactPersonList.iterator();
			while (ite.hasNext())
			{
				AppContactPersonObject tmpCon = ite.next();
				if (tmpCon.AppId.equals(appId))
				{
					tmp.add(tmpCon);
				}
			}
//			contactPersonList.forEach(contactPerson ->{
//				if(contactPerson.AppId.equals(appId))
//					tmp.add(contactPerson);
//			});
			res.put(appId, tmp);
		});
		return res;
	}

	@Override
	public final HashMap<String, AppContactObject> getMassiveAppContactById(List<String> appIds) 
	{
		String getAppContactObjectSql = 
				"SELECT T2.AppId, NULL AS Mobile, T1.Content AS QQNumber, NULL AS CompanyPhone FROM ContactObjects T1,"
				+ " (SELECT QQContactId, AppId FROM PersonalInfoObjects WHERE AppId IN ({0}{1}{2})) T2"
				+ " WHERE T1.Id=T2.QQContactId"
				+ " UNION"
				+ " SELECT T2.AppId, NULL AS Mobile, NULL AS QQNumber, T1.Content AS CompanyPhone FROM ContactObjects T1,"
				+ " (SELECT CompanyTelId , ApplicationId AS AppId FROM JobInfoObjects WHERE ApplicationId IN ({3}{4}{5})) T2"
				+ " WHERE T1.Id=T2.CompanyTelId"
				+ " UNION"
				+ " SELECT T2.AppId, T1.Content AS Mobile, NULL AS QQNumber, NULL AS CompanyPhone FROM ContactObjects T1,"
				+ " (SELECT A1.MobileContactId, A2.Id AS AppId FROM UserObjects A1,"
				+ " (SELECT Id, UserId FROM InstallmentApplicationObjects WHERE Id IN ({6}{7}{8})) A2"
				+ " WHERE A1.Id=A2.UserId ) T2 "
				+ " WHERE T1.Id=T2.MobileContactId";
		getAppContactObjectSql = MessageFormat.format(getAppContactObjectSql, "\'", String.join("\',\'", appIds), "\'",
				"\'", String.join("\',\'", appIds), "\'", "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppContactObject> contactlist = 
				(ArrayList<AppContactObject>) DatabaseApi.queryMultipleResults(getAppContactObjectSql,
					null, omni.database.DatabaseExtractors.APPCONTACTOBJECT_EXTRACTOR);
			
		HashMap<String, AppContactObject> res = new HashMap<>();
		appIds.forEach(appId->
		{
			AppContactObject tmp = new AppContactObject(); 
			Iterator<AppContactObject> ite = contactlist.iterator();
			while (ite.hasNext())
			{
				AppContactObject tmpCon = ite.next();
				if (tmpCon.appId.equals(appId))
				{
					switch (tmpCon.ContactType)
					{
						case 1: tmp.qqNumber = tmpCon.Content; break;
						case 2:	tmp.mobile = tmpCon.Content; break;
						case 4: tmp.companyPhone = tmpCon.Content; break;
						default: break;
					}
				}
			}
			res.put(appId, tmp);
		});
//		contactlist.forEach(contact ->{
//			res.put(contact.appId, contact);
//		});
		return res;
	}

	@Override
	public final HashMap<String, AppOtherInfoObject> getMassiveAppOtherInfoById(List<String> appIds) 
	{
		String getAppOtherInfoObjectSql = 
				"SELECT AppId= "
				+ " CASE WHEN TA5.AppId is NULL THEN TA6.AppId ELSE TA5.AppId END,"
				+ " TA5.NickName, TA5.HeadImageUrl, TA5.IsOutOfTouch, TA5.isRecommended, TA5.isReported, TA6.BankAccount FROM"
					+ " (SELECT AppId="
					+ " CASE WHEN TA3.AppId is NULL THEN TA4.AppId ELSE TA3.AppId END,"
					+ " TA3.NickName, TA3.HeadImageUrl, TA3.IsOutOfTouch, TA4.isRecommended, TA4.isReported FROM"
						+ " (SELECT AppId="
						+ " CASE WHEN TA1.AppId is NULL THEN TA2.AppId ELSE TA1.AppId END,"
						+ " TA1.NickName, TA1.HeadImageUrl, TA2.IsOutOfTouch FROM"
							+ " (SELECT T2.AppId, T1.NickName, T1.HeadImageUrl FROM WeiXinUserObjects T1,"
							+ " (SELECT A2.Id AS AppId, A1.WeiXinUserId FROM UserObjects A1,"
							+ " (SELECT Id, UserId FROM InstallmentApplicationObjects WHERE Id IN ({0}{1}{2}) ) A2"
							+ " WHERE A1.Id = A2.UserId) T2 WHERE T1.Id = T2.WeiXinUserId) TA1"
							+ " FULL OUTER JOIN"
							+ " (SELECT T2.Id AS AppId, T1.IsOutOfTouch FROM [EndUserOutOfTouchRecordObjects] T1,"
							+ " (SELECT Id, UserId FROM InstallmentApplicationObjects WHERE Id IN ({3}{4}{5})) T2 "
						+ " WHERE T1.EndUserId = T2.UserId) TA2 ON TA1.AppId=TA2.AppId) TA3"
						+ " FULL OUTER JOIN"
						+ " (SELECT AppId, isRecommended = CASE Tag WHEN 10 THEN 1 END, isReported = CASE Tag WHEN 20 THEN 1 END"
						+ " FROM AffiliateOperationObjects"
					+ " WHERE AppId IN ({6}{7}{8})) TA4 ON TA3.AppId=TA4.AppId) TA5"
					+ " FULL OUTER JOIN"
					+ " (SELECT T2.AppId, T1.BankAccount FROM [PaymentObjects] T1,"
					+ " (SELECT T.ApplicationId AS AppId, T.PaymentId, T.LastModified "
					+ " FROM(SELECT T.ApplicationId, T.PaymentId, T.LastModified,ROW_NUMBER() OVER(PARTITION BY T.ApplicationId ORDER BY T.LastModified DESC) RN"
					+ " FROM [PaymentApplicationObjects] T"
					+ " WHERE T.ApplicationId IN ({9}{10}{11})) T WHERE T.RN=1) T2 WHERE T1.ID=T2.PaymentId) TA6 "
				+ " ON TA5.AppId=TA6.AppId;";
		getAppOtherInfoObjectSql = MessageFormat.format(getAppOtherInfoObjectSql, "\'", String.join("\',\'", appIds), "\'",
				"\'", String.join("\',\'", appIds), "\'", "\'", String.join("\',\'", appIds), "\'", "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppOtherInfoObject> otherInfoList = 
				(ArrayList<AppOtherInfoObject>) DatabaseApi.queryMultipleResults(getAppOtherInfoObjectSql,
					null, omni.database.DatabaseExtractors.APPOTHERINFOOBJECT_EXTRACTOR);
			
		HashMap<String, AppOtherInfoObject> res = new HashMap<>();
		otherInfoList.forEach(otherInfo ->
		{
			res.put(otherInfo.appId, otherInfo);
		});
		return res;
	}

	@Override
	public final HashMap<String, AppMerchantInfoObject> getMassiveAppMerchantInfoById(List<String> appIds) 
	{
		String getAppMerchantInfoObjectSql = 
				"SELECT AppId = CASE WHEN TA10.AppId IS NULL THEN TA11.AppId ELSE TA10.AppId END,"
				+ " TA10.Mobile, TA10.PosName, TA10.S1Name, TA10.D1Name, TA10.BD2Name, TA11.BankAccount FROM "
				+ " ( SELECT AppId = CASE WHEN TA8.AppId IS NULL THEN TA9.AppId ELSE TA8.AppId END,"
				+ " TA8.Mobile, TA8.PosName, TA8.S1Name, TA8.D1Name, TA9.BD2Name FROM"
				+ " (SELECT AppId = CASE WHEN TA6.AppId IS NULL THEN TA7.AppId ELSE TA6.AppId END,"
				+ " TA6.Mobile, TA6.PosName, TA6.S1Name, TA7.D1Name FROM"
				+ " (SELECT AppId = CASE WHEN TA3.AppId IS NULL THEN TA4.AppId ELSE TA3.AppId END,"
				+ " TA3.Mobile, TA3.PosName, TA4.S1Name FROM"
				+ " (SELECT AppId = CASE WHEN TA1.AppId IS NULL THEN TA2.AppId ELSE TA1.AppId END,"
				+ " TA1.Mobile, TA2.PosName FROM"
				+ " (SELECT T11.Appid, T11.Mobile, T12.QQNumber FROM"
				+ " (SELECT T2.Appid, T1.Content AS Mobile FROM [ContactObjects] T1,"
				+ " (SELECT A2.Appid, A1.ContactID FROM [MerchantUserContactObjects] A1,"
				+ " (SELECT InstalmentAppId AS Appid, S1Id FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId IN"
				+ " ({0}{1}{2}) ) A2 WHERE A1.MerchantUserId=A2.S1Id) T2 WHERE T1.Id=T2.ContactId AND T1.ContactType=2) T11"
				+ " FULL OUTER JOIN"
				+ " (SELECT T2.AppId, T1.Content AS QQNumber FROM [ContactObjects] T1,"
				+ " (SELECT A2.AppId, A1.ContactID FROM [MerchantUserContactObjects] A1,"
				+ " (SELECT InstalmentAppId AS AppId, S1Id FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId IN"
				+ " ({3}{4}{5})) A2 WHERE A1.MerchantUserId=A2.S1Id ) T2 WHERE T1.Id=T2.ContactId AND T1.ContactType=1) T12"
				+ " ON T11.AppId=T12.AppId ) TA1"
				+ " FULL OUTER JOIN"
				+ " (SELECT T2.AppId, T1.Name AS PosName FROM MerchantStoreObjects T1,"
				+ " (SELECT InstalmentAppId AS AppId, PosId FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId IN"
				+ " ({6}{7}{8}) ) T2 WHERE T1.Id=T2.PosId) TA2 ON TA1.AppId=TA2.AppId) TA3"
				+ " FULL OUTER JOIN"
				+ " (SELECT T2.AppId, T1.IdName AS S1Name FROM MerchantUserObjects T1,"
				+ " (SELECT InstalmentAppId AS AppId, S1Id FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId IN"
				+ " ({9}{10}{11})) T2 WHERE T1.Id=T2.S1Id) TA4 ON TA3.AppId=TA4.AppId) TA6"
				+ " FULL OUTER JOIN"
				+ " (SELECT T2.AppId, T1.IdName AS D1Name FROM DealerUserObjects T1,"
				+ " (SELECT InstalmentAppId AS AppId, D1Id FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId IN"
				+ " ({12}{13}{14})) T2 WHERE T1.Id=T2.D1Id)TA7 ON TA6.AppId=TA7.AppId)TA8"
				+ " FULL OUTER JOIN"
				+ " (SELECT T2.AppId, T1.IdName AS BD2Name FROM BusinessDevelopUserObjects T1,"
				+ " (SELECT InstalmentAppId AS AppId, BD2Id FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId IN"
				+ " ({15}{16}{17})) T2 WHERE T1.Id=T2.BD2Id )TA9 ON TA8.AppId=TA9.AppId) TA10"
				+ " FULL OUTER JOIN"
				+ " (SELECT T2.AppId, T1.BankAccount FROM [PaymentObjects] T1,"
				+ " (SELECT A2.AppId, A1.PaymentId FROM MerchantUserPaymentObjects A1,"
				+ " (SELECT InstalmentAppId AS AppId, S1Id FROM InstalmentApplicationSnapObjects WHERE InstalmentAppId IN"
				+ " ({18}{19}{20})) A2 WHERE A1.MerchantUserId=A2.S1Id) T2 WHERE T1.Id=T2.PaymentId)TA11"
				+ " ON TA10.AppId=TA11.AppId";
		getAppMerchantInfoObjectSql = MessageFormat.format(getAppMerchantInfoObjectSql, 
						"\'", String.join("\',\'", appIds), "\'", "\'", String.join("\',\'", appIds), "\'",
						"\'", String.join("\',\'", appIds), "\'", "\'", String.join("\',\'", appIds), "\'",
						"\'", String.join("\',\'", appIds), "\'",
						"\'", String.join("\',\'", appIds), "\'", "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppMerchantInfoObject> merchantInfoList = 
				(ArrayList<AppMerchantInfoObject>) DatabaseApi.queryMultipleResults(getAppMerchantInfoObjectSql,
					null, omni.database.DatabaseExtractors.APPMERCHANTINFOOBJECT_EXTRACTOR);
			
		HashMap<String, AppMerchantInfoObject> res = new HashMap<>();
		merchantInfoList.forEach(merchantInfo ->
		{
			res.put(merchantInfo.appId, merchantInfo);
		});
		return res;
	}

	@Override
	public final Map<String, AppFuserObject> getMassiveAppFuserById(List<String> appIds) 
	{
		String getAppFuserObjectSql = 
				"SELECT AppId, Id, UserId, IdName, Mobile FROM FUserObjects TA1,"
				+ " (Select T3.Content AS Mobile, T4.AppId, T4.FUserId FROM ContactObjects T3,"
				+ " (Select T2.AppId, T1.ContactId, T1.FUserId FROM FUserContactObjects T1, FApplicationRelationObjects T2"
				+ " WHERE T1.FUserId=T2.FUserId AND T2.AppId IN ({0}{1}{2}) ) T4"
				+ " WHERE T3.Id = T4.ContactId AND T3.ContactType=2) TA2 WHERE TA1.Id=TA2.FUserId";
		getAppFuserObjectSql = MessageFormat.format(getAppFuserObjectSql, "\'", String.join("\',\'", appIds), "\'");
		ArrayList<AppFuserObject> appFuserReslist = 
				(ArrayList<AppFuserObject>) DatabaseApi.queryMultipleResults(getAppFuserObjectSql,
					null, omni.database.DatabaseExtractors.APPFUSEROBJECT_EXTRACTOR);
			
		HashMap<String, AppFuserObject> res = new HashMap<>();
		appFuserReslist.forEach(appFuserRes ->
		{
			res.put(appFuserRes.appId, appFuserRes);
		});
		return res;
	}

	/**
	 * <p>通过AppId列表获取金融产品信息数据集合</p>
     * <p>table：分期申请关联产品表、产品详细信息表</p>
	 * @param appIds
	 * @return
	 */
    public final Map<String, AppProductObject> getMassiveAppProductById(List<String> appIds) 
    {
        String sql = 
                "SELECT pao.AppId, po.Name"
                + " FROM ProductApplicationObjects pao"
                + " Inner Join ProductObjects po On pao.ProductId=po.Id"
                + " WHERE pao.AppId IN ({0}{1}{2})";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppProductObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.PRODUCTOBJECT_EXTRACTOR);
            
        HashMap<String, AppProductObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取D1信息数据集合</p>
     * <p>table：分期申请关联产品表、申请快照表、D/S/联系方式表、联系方式表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppMerchantInfoObject> getMassiveAppD1InfoById(List<String> appIds) 
    {
        String sql = 
                "SELECT iao.Id AS AppId, co.Content AS D1Mobile"
                + " FROM InstallmentApplicationObjects iao"
                + " Inner Join InstalmentApplicationSnapObjects iaso On iao.Id = iaso.InstalmentAppId"
                + " Inner Join MerchantUserContactObjects muco On muco.MerchantUserId = iaso.D1Id"
                + " Inner Join ContactObjects co On co.Id = muco.ContactId and co.ContactType = 2"
                + " WHERE iao.Id IN ({0}{1}{2})";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppMerchantInfoObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPD1OBJECT_EXTRACTOR);
            
        HashMap<String, AppMerchantInfoObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取D2信息数据集合</p>
     * <p>table：分期申请关联产品表、申请快照表、Dealer用户信息表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppMerchantInfoObject> getMassiveAppD2InfoById(List<String> appIds) 
    {
        String sql = 
                "SELECT iao.Id AS AppId, duo.IdName AS D2Name"
                + " FROM InstallmentApplicationObjects iao"
                + " Inner Join InstalmentApplicationSnapObjects iaso On iao.Id = iaso.InstalmentAppId"
                + " Inner Join DealerUserObjects duo On duo.Id = iaso.D2Id"
                + " WHERE iao.Id IN ({0}{1}{2})";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppMerchantInfoObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPD2OBJECT_EXTRACTOR);
            
        HashMap<String, AppMerchantInfoObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取门店信息数据集合</p>
     * <p>table：分期申请关联产品表、门店信息表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppMerchantInfoObject> getMassiveAppMerchantById(List<String> appIds)
    {
        String sql = 
                "SELECT iao.Id AS AppId, mso.StoreCategory"
                + " FROM InstallmentApplicationObjects iao"
                + " Inner Join MerchantStoreObjects mso On iao.MerchantStoreId=mso.Id"
                + " WHERE iao.Id IN ({0}{1}{2})";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppMerchantInfoObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPMERCHANT_EXTRACTOR);
            
        HashMap<String, AppMerchantInfoObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取用户扫码记录信息数据集合(时间倒叙前3条)</p>
     * <p>table：分期申请关联产品表、用户扫码记录表、门店信息表、门店店员信息表</p>
     * @param appIds
     * @return
     */
    public final Map<String, List<AppUserScanRecordObject>> getMassiveAppUserScanRecordById(List<String> appIds) 
    {
        String sql = 
                "SELECT T1.*"
                + " FROM ("
                + " SELECT iao.Id AS AppId, CONVERT(VARCHAR(19), usro.DateAdded, 120) AS ScanDateTime,"
                + " usro.MerchantStoreId, mso.Name AS MerchantStoreName,"
                + " usro.MerchantUserId, muo.IdName AS MerchantUserName,"
                + " ROW_NUMBER() OVER (PARTITION BY iao.Id ORDER BY usro.DateAdded DESC) RN"
                + " FROM InstallmentApplicationObjects iao"
                + " Inner Join UserScanRecordObjects usro On iao.UserId=usro.UserId"
                + " AND usro.DateAdded < iao.DateAdded"
                + " Inner Join MerchantStoreObjects mso On usro.MerchantStoreId=mso.Id"
                + " Inner Join MerchantUserObjects muo On usro.MerchantUserId=muo.Id"
                + " WHERE iao.Id IN ({0}{1}{2})"
                + " ) T1"
                + " WHERE RN<=3";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppUserScanRecordObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPUSERSCANRECORD_EXTRACTOR);

        HashMap<String, List<AppUserScanRecordObject>> resMap = new HashMap<>();
        appIds.forEach(appId->
        {
            List<AppUserScanRecordObject> tmpList = new ArrayList<>(); 
            Iterator<AppUserScanRecordObject> ite = reslist.iterator();
            while (ite.hasNext())
            {
                AppUserScanRecordObject tmpObj = ite.next();
                if (tmpObj.appId.equals(appId))
                {
                    tmpList.add(tmpObj);
                }
            }
            resMap.put(appId, tmpList);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取POS批核数、拒绝数、批核率信息数据集合</p>
     * <p>table：分期申请表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppCountObject> getMassiveAppPosCountById(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                + " T.AppId"
                + " ,T.TotalCount TotalCountInHistory"
                + " ,T.ApprovedCount ApprovedCountInHistory"
                + " ,T.RejectedCount RejectedCountInHistory"
                + " ,CASE WHEN T.TotalCount = 0 THEN 0 ELSE CONVERT(DECIMAL(5,2),"
                + " (T.ApprovedCount+0.0) * 100 / T.TotalCount) END ApprovedRateInHistory"
                + " FROM"
                + " ("
                + " SELECT"
                + " iaoCondition.Id AppId"
                + " ,SUM(CASE WHEN iao.Status >= 40 THEN 1 ELSE 0 END) TotalCount"
                + " ,SUM(CASE WHEN iao.Status >= 100 THEN 1 ELSE 0 END) ApprovedCount"
                + " ,SUM(CASE WHEN iao.Status = 40 THEN 1 ELSE 0 END) RejectedCount"
                + " FROM"
                + " ("
                + " SELECT"
                + " iao.Id, iao.MerchantStoreId, iao.DateAdded"
                + " FROM"
                + " InstallmentApplicationObjects iao"
                + " WHERE"
                + " iao.Id IN ({0}{1}{2})"
                + " ) iaoCondition"
                + " INNER JOIN InstallmentApplicationObjects iao"
                + " ON iaoCondition.MerchantStoreId = iao.MerchantStoreId"
                + " AND iaoCondition.DateAdded > iao.DateAdded"
                + " GROUP BY iaoCondition.Id"
                + " ) T";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppCountObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPCOUNT_EXTRACTOR);

        HashMap<String, AppCountObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取D1批核数、拒绝数、批核率信息数据集合</p>
     * <p>table：分期申请表、申请快照表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppCountObject> getMassiveAppD1CountByIdOld(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                + " T.AppId"
                + " ,T.TotalCount TotalCountInHistory"
                + " ,T.ApprovedCount ApprovedCountInHistory"
                + " ,T.RejectedCount RejectedCountInHistory"
                + " ,CASE WHEN T.TotalCount = 0 THEN 0 ELSE CONVERT(DECIMAL(5,2),"
                + " (T.ApprovedCount+0.0) * 100 / T.TotalCount) END ApprovedRateInHistory"
                + " FROM"
                + " ("
                + " SELECT"
                + " iaoCondition.Id AppId"
                + " ,SUM(CASE WHEN iao.Status >= 40 THEN 1 ELSE 0 END) TotalCount"
                + " ,SUM(CASE WHEN iao.Status >= 100 THEN 1 ELSE 0 END) ApprovedCount"
                + " ,SUM(CASE WHEN iao.Status = 40 THEN 1 ELSE 0 END) RejectedCount"
                + " FROM"
                + " ("
                + " SELECT"
                + " iao.Id, iaso.D1Id, iao.DateAdded"
                + " FROM"
                + " InstallmentApplicationObjects iao"
                + " INNER JOIN InstalmentApplicationSnapObjects iaso ON iao.Id = iaso.InstalmentAppId"
                + " WHERE"
                + " iao.Id IN ({0}{1}{2})"
                + " ) iaoCondition"
                + " INNER JOIN InstalmentApplicationSnapObjects iaso"
                + " ON iaoCondition.D1Id = iaso.D1Id"
                + " INNER JOIN InstallmentApplicationObjects iao"
                + " ON iaso.InstalmentAppId = iao.Id"
                + " AND iaoCondition.DateAdded > iao.DateAdded"
                + " GROUP BY iaoCondition.Id"
                + " ) T";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppCountObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPCOUNT_EXTRACTOR);

        HashMap<String, AppCountObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取D1批核数、拒绝数、批核率信息数据集合</p>
     * <p>table：分期申请表、申请快照表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppCountObject> getMassiveAppD1CountById(List<String> appIds) 
    {
    	HashMap<String, AppCountObject> resMap = new HashMap<>();
    	if(appIds!=null && appIds.size()>0){
    		String sql ="select iao.Status"
        			+" from InstallmentApplicationObjects iao"
        			+" INNER JOIN InstalmentApplicationSnapObjects iaso"
        			+" ON iaso.InstalmentAppId = iao.Id and iaso.D1Id = :D1Id"
        			+" where :DateAdded > iao.DateAdded";
            for (String appId : appIds) {
            	try {
            		InstalmentApplicationSnapObject object = this.getInstalmentApplicationSnapObjectByAppId(appId);
            		if(object != null ){
            			List<Integer> reslist = DatabaseApi.queryMultipleResults(sql,
                				CollectionUtils.mapOf("D1Id", object.D1Id, "DateAdded", object.DateAdded), DatabaseExtractors.INTEGER_EXTRACTOR);
            			AppCountObject AppCount = new AppCountObject();
            			AppCount.totalCountInHistory = (int) reslist.stream().filter(x -> x > 39).count();
            			AppCount.approvedCountInHistory = (int) reslist.stream().filter(x -> x > 99).count();
            			AppCount.rejectedCountInHistory = (int) reslist.stream().filter(x -> x == 40).count();
            			if(AppCount.totalCountInHistory > 0){
            				AppCount.approvedRateInHistory = new BigDecimal(AppCount.approvedCountInHistory)
            						.divide(new BigDecimal(AppCount.totalCountInHistory),4,BigDecimal.ROUND_HALF_UP)
            						.multiply(new BigDecimal(100));
            			}else{
            				AppCount.approvedRateInHistory = new BigDecimal(0);
            			}
            			resMap.put(appId, AppCount);
            		}
            	}catch(Exception e){
            		Logger.get().error("getMassiveAppD1CountById function error,appId is " +appId, e);
            	}
            }
    	}
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取S1批核数、拒绝数、批核率信息数据集合</p>
     * <p>table：分期申请表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppCountObject> getMassiveAppS1CountById(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                + " T.AppId"
                + " ,T.TotalCount TotalCountInHistory"
                + " ,T.ApprovedCount ApprovedCountInHistory"
                + " ,T.RejectedCount RejectedCountInHistory"
                + " ,CASE WHEN T.TotalCount = 0 THEN 0 ELSE CONVERT(DECIMAL(5,2),"
                + " (T.ApprovedCount+0.0) * 100 / T.TotalCount) END ApprovedRateInHistory"
                + " FROM"
                + " ("
                + " SELECT"
                + " iaoCondition.Id AppId"
                + " ,SUM(CASE WHEN iao.Status >= 40 THEN 1 ELSE 0 END) TotalCount"
                + " ,SUM(CASE WHEN iao.Status >= 100 THEN 1 ELSE 0 END) ApprovedCount"
                + " ,SUM(CASE WHEN iao.Status = 40 THEN 1 ELSE 0 END) RejectedCount"
                + " FROM"
                + " ("
                + " SELECT"
                + " iao.Id, iao.MerchantUserId, iao.DateAdded"
                + " FROM"
                + " InstallmentApplicationObjects iao"
                + " WHERE"
                + " iao.Id IN ({0}{1}{2})"
                + " ) iaoCondition"
                + " INNER JOIN InstallmentApplicationObjects iao"
                + " ON iaoCondition.MerchantUserId = iao.MerchantUserId"
                + " AND iaoCondition.DateAdded > iao.DateAdded"
                + " GROUP BY iaoCondition.Id"
                + " ) T";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppCountObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPCOUNT_EXTRACTOR);

        HashMap<String, AppCountObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取用户总申请次数、用户总申请批准次数、用户总申请拒绝次数、用户总申请取消次数信息数据集合</p>
     * <p>table：分期申请表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppCountObject> getMassiveAppUserCountById(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                + " iaoCondition.Id AppId"
                + " ,SUM(1) TotalCountInHistory"
                + " ,SUM(CASE WHEN iao.Status >= 100 THEN 1 ELSE 0 END) ApprovedCountInHistory"
                + " ,SUM(CASE WHEN iao.Status = 40 THEN 1 ELSE 0 END) RejectedCountInHistory"
                + " ,SUM(CASE WHEN iao.Status = -1 THEN 1 ELSE 0 END) CanceledCountInHistory"
                + " ,SUM(CASE WHEN iao.DateAdded > DateAdd(MONTH, -1,"
                + " iaoCondition.DateAdded) THEN 1 ELSE 0 END) TotalCountInPM1"
                + " ,SUM(CASE WHEN iao.Status >= 100 AND iao.DateAdded > DateAdd(MONTH, -1,"
                + " iaoCondition.DateAdded) THEN 1 ELSE 0 END) ApprovedCountInPM1"
                + " ,SUM(CASE WHEN iao.Status = 40 AND iao.DateAdded > DateAdd(MONTH, -1,"
                + " iaoCondition.DateAdded) THEN 1 ELSE 0 END) RejectedCountInPM1"
                + " ,SUM(CASE WHEN iao.Status = -1 AND iao.DateAdded > DateAdd(MONTH, -1,"
                + " iaoCondition.DateAdded) THEN 1 ELSE 0 END) CanceledCountInPM1"
                + " FROM"
                + " ("
                + " SELECT"
                + " iao.Id, iao.UserId, iao.DateAdded"
                + " FROM"
                + " InstallmentApplicationObjects iao"
                + " WHERE"
                + " iao.Id IN ({0}{1}{2})"
                + " ) iaoCondition"
                + " INNER JOIN InstallmentApplicationObjects iao"
                + " ON iaoCondition.UserId = iao.UserId"
                + " AND iaoCondition.DateAdded > iao.DateAdded"
                + " GROUP BY iaoCondition.Id";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppCountObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPUSERCOUNT_EXTRACTOR);

        HashMap<String, AppCountObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取用户扫描POS门店次数信息数据集合</p>
     * <p>table：分期申请表、用户扫码记录表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppUserScanCountObject> getMassiveAppUserScanPOSCountById(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                + " iao.Id AppId"
                + " ,SUM(1) ScannedCountInHistory"
                + " ,SUM(CASE WHEN usro.DateAdded > DateAdd(MONTH, -1, iao.DateAdded) THEN 1 ELSE 0 END) ScannedCountInPM1"
                + " FROM"
                + " InstallmentApplicationObjects iao"
                + " INNER JOIN UserScanRecordObjects usro On iao.UserId=usro.UserId"
                + " AND usro.MerchantStoreId = iao.MerchantStoreId"
                + " AND usro.DateAdded < iao.DateAdded"
                + " WHERE"
                + " iao.Id IN ({0}{1}{2})"
                + " GROUP BY iao.Id";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppUserScanCountObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPUSERSCANCOUNT_EXTRACTOR);

        HashMap<String, AppUserScanCountObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取用户扫描S码次数信息数据集合</p>
     * <p>table：分期申请表、用户扫码记录表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppUserScanCountObject> getMassiveAppUserScanS1CountById(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                + " iao.Id AppId"
                + " ,SUM(1) ScannedCountInHistory"
                + " ,SUM(CASE WHEN usro.DateAdded > DateAdd(MONTH, -1, iao.DateAdded) THEN 1 ELSE 0 END) ScannedCountInPM1"
                + " FROM"
                + " InstallmentApplicationObjects iao"
                + " INNER JOIN UserScanRecordObjects usro On iao.UserId=usro.UserId"
                + " AND usro.MerchantUserId = iao.MerchantUserId"
                + " AND usro.DateAdded < iao.DateAdded"
                + " WHERE"
                + " iao.Id IN ({0}{1}{2})"
                + " GROUP BY iao.Id";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppUserScanCountObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPUSERSCANCOUNT_EXTRACTOR);

        HashMap<String, AppUserScanCountObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取用户首次扫码信息数据集合</p>
     * <p>table：分期申请表、用户扫码记录表</p>
     * @param appIds
     * @return
     */
    public final Map<String, AppUserScanCountObject> getMassiveAppUserFirstScanInfoById(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                + " iao.Id AppId"
                + " ,CONVERT(VARCHAR(19), MIN(usro.DateAdded), 120) FirstScanDateTime"
                + " ,DATEDIFF(DAY, MIN(usro.DateAdded), MIN(iao.DateAdded)) DaysSinceFirstScan"
                + " FROM InstallmentApplicationObjects iao"
                + " Inner Join UserScanRecordObjects usro On iao.UserId=usro.UserId"
                + " WHERE iao.Id IN ({0}{1}{2})"
                + " GROUP BY iao.Id";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppUserScanCountObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPUSERFIRSTSCAN_EXTRACTOR);

        HashMap<String, AppUserScanCountObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>(*****废弃*****)</p>
     * <p>通过AppId列表获取D2FPD7%信息数据集合</p>
     * <p>table：分期申请表、申请快照表、还款计划表</p>
     * @param appIds
     * @return
     */
    @Deprecated
    public final Map<String, AppCountObject> getMassiveAppD2FPD7RateInfoById(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                        + " T.AppId"
                        + " ,T.D2ApprovedCount"
                        + " ,COUNT(T.FPD) D2FPD7Count"
                        + " ,CASE WHEN T.D2ApprovedCount = 0 THEN 0 ELSE CONVERT(DECIMAL(5,2), (COUNT(T.FPD)+0.0) * 100 / T.D2ApprovedCount) END D2FPD7Rate"
                        + " FROM"
                        + " ("
                        + " SELECT"
                        + " D2App.AppId,D2App.D2AppId,D2App.D2ApprovedCount"
                        + " ,COUNT(io.Id) FPD"
                        + " FROM"
                        + " ("
                        + " SELECT"
                        + " iaoCondition.Id AppId"
                        + " ,iao.Id D2AppId"
                        + " ,COUNT(iao.Id) OVER (partition BY iaoCondition.Id) D2ApprovedCount"
                        + " FROM"
                        + " ("
                        + " SELECT"
                        + " iao.Id, iaso.D2Id, iao.DateAdded"
                        + " FROM"
                        + " InstallmentApplicationObjects iao"
                        + " INNER JOIN InstalmentApplicationSnapObjects iaso ON iao.Id = iaso.InstalmentAppId"
                        + " WHERE"
                        + " iao.Id IN ({0}{1}{2})"
                        + " ) iaoCondition"
                        + " INNER JOIN InstalmentApplicationSnapObjects iaso"
                        + " ON iaoCondition.D2Id = iaso.D2Id"
                        + " INNER JOIN InstallmentApplicationObjects iao"
                        + " ON iaso.InstalmentAppId = iao.Id"
                        + " AND iao.Status >= 100"
                        + " AND iaoCondition.DateAdded > iao.DateAdded"
                        + " ) D2App"
                        + " INNER JOIN InstalmentObjects io "
                        + " ON io.NumInstalment = 1"
                        + " AND io.InstalmentType = 150"
                        + " AND io.AppId = D2App.D2AppId"
                        + " GROUP BY D2App.AppId,D2App.D2AppId,D2App.D2ApprovedCount"
                        + " HAVING COUNT(io.Id) > 7"
                        + " ) T"
                        + " GROUP BY T.AppId, T.D2ApprovedCount";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppCountObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPD2FPD7RATE_EXTRACTOR);

        HashMap<String, AppCountObject> resMap = new HashMap<>();
        reslist.forEach(res ->
        {
            resMap.put(res.appId, res);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取D2批核件信息数据集合</p>
     * <p>table：分期申请表、申请快照表、还款计划表</p>
     * @param appIds
     * @return
     */
    public final Map<String, List<String>> getMassiveD2AppInfoByIdOld(List<String> appIds) 
    {
        String sql = 
                "SELECT"
                + " iaoCondition.Id AppId"
                + " ,iao.Id AssociatedAppId"
                + " ,COUNT(iao.Id) OVER (partition BY iaoCondition.Id) D2ApprovedCount"
                + " FROM"
                + " ("
                + " SELECT"
                + " iao.Id, iaso.D2Id, iao.DateAdded"
                + " FROM"
                + " InstallmentApplicationObjects iao"
                + " INNER JOIN InstalmentApplicationSnapObjects iaso ON iao.Id = iaso.InstalmentAppId"
                + " WHERE"
                + " iao.Id IN ({0}{1}{2})"
                + " ) iaoCondition"
                + " INNER JOIN InstalmentApplicationSnapObjects iaso"
                + " ON iaoCondition.D2Id = iaso.D2Id"
                + " INNER JOIN InstallmentApplicationObjects iao"
                + " ON iaso.InstalmentAppId = iao.Id"
                + " AND iao.Status >= 100"
                + " AND iaoCondition.DateAdded > iao.DateAdded";
        sql = MessageFormat.format(sql, "\'", String.join("\',\'", appIds), "\'");
        List<AppSDAssociatedAppObject> reslist = DatabaseApi.queryMultipleResults(sql,
                    null, omni.database.DatabaseExtractors.APPASSOCIATEDAPP_EXTRACTOR);
        
        HashMap<String, List<String>> resMap = new HashMap<>();
        appIds.forEach(appId->
        {
            List<String> tmpList = new ArrayList<>(); 
            Iterator<AppSDAssociatedAppObject> ite = reslist.iterator();
            while (ite.hasNext())
            {
                AppSDAssociatedAppObject tmpObj = ite.next();
                if (tmpObj.appId.equals(appId))
                {
                    tmpList.add(tmpObj.associatedAppId);
                }
            }
            resMap.put(appId, tmpList);
        });
        return resMap;
    }
    
    /**
     * <p>通过AppId列表获取D2批核件信息数据集合</p>
     * <p>table：分期申请表、申请快照表、还款计划表</p>
     * @param appIds
     * @return
     */
    public final Map<String, List<String>> getMassiveD2AppInfoById(List<String> appIds) 
    {
        HashMap<String, List<String>> resMap = new HashMap<>();
        if(appIds!=null && appIds.size()>0){
    		String sql ="select iao.Id"
        			+" from InstallmentApplicationObjects iao"
        			+" INNER JOIN InstalmentApplicationSnapObjects iaso"
        			+" ON iaso.InstalmentAppId = iao.Id and iaso.D2Id = :D2Id"
        			+" where iao.Status > 99 and :DateAdded > iao.DateAdded";
            for (String appId : appIds) {
            	try {
            		InstalmentApplicationSnapObject object = this.getInstalmentApplicationSnapObjectByAppId(appId);
            		if(object != null ){
            			List<String> reslist = DatabaseApi.queryMultipleResults(sql,
                				CollectionUtils.mapOf("D2Id", object.D2Id, "DateAdded", object.DateAdded), DatabaseExtractors.STRING_EXTRACTOR);
            			if(reslist != null && !reslist.isEmpty()){
            				resMap.put(appId, reslist);
            			}
            		}
        		}catch(Exception e){
            		Logger.get().error("getMassiveD2AppInfoById function error,appId is " +appId, e);
            	}
            }
        }
        return resMap;
    }

    @Override
    public InstallmentApplicationObject getLastInstallmentApplicationObjectsById(String appId) {
        try {
            String sql="select top 1 Id,InstallmentStartedOn from InstallmentApplicationObjects b  where b.UserId=( "
                    + "select a.UserId from InstallmentApplicationObjects a where a.Id= :AppId ) and (b.Status=500 or b.Status=600) "
                    + "order by InstallmentStartedOn  desc ";
            Dao<InstallmentApplicationObject> instalmentDao = Dao.create(
                InstallmentApplicationObject.class, DatabaseApi.database);
            return instalmentDao.getSingle(sql, CollectionUtils.mapOf("AppId", appId));            
        } catch (Exception e) {
            Logger.get().error("getLastInstallmentApplicationObjectsById function error", e);
            return null;
        }
    }
    
    /**
     * <p>〈获取AppMerchantInfoObject信息〉</p>
     * 
     * @param appIds
     * @return
     */
    public Map<String , AppMerchantInfoObject> getAppMerchantInfoObject(List<String> appIds){
        Map<String , AppMerchantInfoObject> map = new HashMap<>();        
        if(appIds!=null&&appIds.size()>0){
            for (String appId : appIds) {
                AppMerchantInfoObject appMerchantInfoObject = new AppMerchantInfoObject();
                appMerchantInfoObject.appId=appId;
                try {
                    InstallmentApplicationObject installmentApplicationObject = this.getInstallmentApplicationObjectById(appId);
                    if(installmentApplicationObject!=null){
                       String merchantUserId = installmentApplicationObject.getMerchantUserId();
                       String merchantStoreId = installmentApplicationObject.getMerchantStoreId();
                       if(merchantUserId!=null&&!"".equals(merchantUserId)){
                           MerchantUserObject merchantUserObject = this.getMerchantUserObjectById(merchantUserId);
                           appMerchantInfoObject.s1Name = merchantUserObject.getIdName();
                           MerchantUserPaymentObject merchantUserPaymentObject = this.getMerchantUserPaymentId(merchantUserId);
                           if(merchantUserPaymentObject!=null&&merchantUserPaymentObject.paymentId!=null){
                               appMerchantInfoObject.bankAccount = this.getBankAccount(merchantUserPaymentObject.paymentId);
                           }
                           appMerchantInfoObject.mobile = this.getMobileByMerchantUserId(merchantUserId);
                       }
                       if(merchantStoreId!=null&&!"".equals(merchantStoreId)){
                           MerchantStoreObject merchantStoreObject = this.getMerchantStoreObjectById(merchantStoreId);
                           if(merchantStoreObject!=null){
                               appMerchantInfoObject.posName = merchantStoreObject.name;
                               appMerchantInfoObject.storeCategory = merchantStoreObject.storeCategory;
                           }
                       }
                    }
                    InstalmentApplicationSnapObject instalmentApplicationSnapObject = this.getInstalmentApplicationSnapObjectByAppId(appId);
                    if(instalmentApplicationSnapObject != null ){
                        String d1Id = instalmentApplicationSnapObject.D1Id;
                        String d2Id = instalmentApplicationSnapObject.D2Id;
                        String bd2Id = instalmentApplicationSnapObject.BD2Id;
                        UserGetUserInfoRespModel d1model = this.getUserGetUserInfoRespModel(d1Id);
                        if(d1model!=null){
                            appMerchantInfoObject.d1Name = d1model.getIdname();
                            appMerchantInfoObject.d1Mobile = d1model.getMobile();
                        }
                        UserGetUserInfoRespModel d2model = this.getUserGetUserInfoRespModel(d2Id);
                        if(d2model!=null){
                            appMerchantInfoObject.d2Name = d2model.getIdname();
                        }
                        UserGetUserInfoRespModel bd2model = this.getUserGetUserInfoRespModel(bd2Id);
                        if(bd2model!=null){
                            appMerchantInfoObject.bd2Name = bd2model.getIdname();
                        }
                    }
                    map.put(appId, appMerchantInfoObject);
                } catch (Exception e) {
                    Logger.get().error("getAppMerchantInfoObject function error,appId is " +appId, e);
                }
            }
        }
        return map;        
    }
    
    /**
     * <p>〈获取销售相关信息〉</p>
     * 
     * @param Id
     * @return
     */
    public UserGetUserInfoRespModel getUserGetUserInfoRespModel(String Id){
        int retryCount = 2;
        if ("".equals(salesUrl) || salesUrl == null) {
            Logger.get().warn("salesUrl is null,please add salesUrl in configuration file ! ");
            return null;
        }
        Map<String, String> header = CollectionUtils.mapOf("content-type", "application/json");
        UserGetUserInfoReqModel request = new UserGetUserInfoReqModel();
        List<String> busilineCodeList = new ArrayList<>();
        busilineCodeList.add("PSL");
        request.setUserId(Id);
        request.setBusilineCodeList(busilineCodeList);
        for (int i = 0; i < retryCount; i++) {
            try {
                String json = HttpClientApi.postString(salesUrl + "/public/common/user/getUserInfo", new Gson().toJson(request), header);
                GeneralResponse<List<UserGetUserInfoRespModel>> response = new Gson().fromJson(json, new TypeToken<GeneralResponse<List<UserGetUserInfoRespModel>>>() {
                }.getType());
                Logger.get().info("getUserGetUserInfoRespModel success ,  sales id is " + Id);
                if (response != null && response.getCode() == 0) {
                    List<UserGetUserInfoRespModel> list = response.getValue();
                    if (list != null && list.size() > 0) {
                        UserGetUserInfoRespModel model = list.get(0);
                        return model;
                    }
                }
                return null;
            } catch (Exception e) {
                if(i == 1){
                    Logger.get().error("getUserGetUserInfoRespModel function error,Id is " + Id, e);
                    return null; 
                }
                Logger.get().warn("getUserGetUserInfoRespModel need retry,Id is " + Id);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    Logger.get().error("getUserGetUserInfoRespModel timeUnit has error",e1);
                }
            }
        }
        return null;
    }
    
}
