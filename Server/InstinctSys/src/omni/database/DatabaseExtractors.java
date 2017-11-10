package omni.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.InstalmentApplicationSnapObject;
import omni.database.catfish.object.BlackListObject;
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

/**
 * This class defines object extractors from Catfish database using {@link RowMapper}.
 * 
 * @author guoqing
 *
 */
public class DatabaseExtractors extends catfish.base.database.DatabaseExtractors 
{
	
	public static RowMapper<BlackListObject> BLACKLISTOBJECT_EXTRACTOR = new RowMapper<BlackListObject>() 
	{
		@Override
		public BlackListObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			BlackListObject blkObj = new BlackListObject();
			blkObj.Id = resultSet.getString("Id");
			blkObj.content = resultSet.getString("Content");
			blkObj.type = resultSet.getInt("Type");
			blkObj.name = resultSet.getString("Name");
			blkObj.source = resultSet.getString("Source");
			blkObj.reason = resultSet.getString("Reason");
			return blkObj;
		}
	};
	
	public static RowMapper<InstallmentApplicationObject> INSTALLMENTAPPLICATIONOBJECT_EXTRACTOR = new RowMapper<InstallmentApplicationObject>() {
		@Override
		public InstallmentApplicationObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			InstallmentApplicationObject appObj = new InstallmentApplicationObject();
			appObj.Id = resultSet.getString("Id");
			appObj.LicenseAgreed = resultSet.getBoolean("LicenseAgreed");
			appObj.ItemPrice = resultSet.getBigDecimal("ItemPrice");
			appObj.Downpayment = resultSet.getBigDecimal("Downpayment");
			appObj.Principal = resultSet.getBigDecimal("Principal");
			appObj.Repayments = resultSet.getInt("Repayments");
			appObj.APR = resultSet.getDouble("APR"); 
			appObj.MonthlyPay = resultSet.getBigDecimal("MonthlyPay");
			appObj.InstallmentStartedOn = resultSet.getDate("InstallmentStartedOn");
			appObj.Status = resultSet.getInt("Status");
			appObj.UserId = resultSet.getString("UserId");
			appObj.ProductName = resultSet.getString("ProductName");
			appObj.License = resultSet.getString("License");
			appObj.PrincipalInMonthlyPay = resultSet.getBigDecimal("PrincipalInMonthlyPay");
			appObj.InterestsInMonthlyPay = resultSet.getBigDecimal("InterestsInMonthlyPay");
			appObj.FeeInMonthlyPay = resultSet.getBigDecimal("FeeInMonthlyPay");
			appObj.FirstPaybackDate = resultSet.getDate("FirstPaybackDate");
			appObj.MonthlyPaybackDay = resultSet.getInt("MonthlyPaybackDay");
			appObj.LicenseAgreedOn = resultSet.getDate("LicenseAgreedOn");
			appObj.RejectedReason = resultSet.getString("RejectedReason");
			appObj.MerchantStoreId = resultSet.getString("MerchantStoreId");
			appObj.PreApprovedOn = resultSet.getDate("PreApprovedOn");
			appObj.ApprovedOn = resultSet.getDate("ApprovedOn");
			appObj.MoneyTransferredOn = resultSet.getDate("MoneyTransferredOn");
			appObj.RejectedOn = resultSet.getDate("RejectedOn");
			appObj.SwearVoicePath = resultSet.getString("SwearVoicePath");
			appObj.UploadStatus = resultSet.getInt("UploadStatus");
			appObj.MerchantUserId = resultSet.getString("MerchantUserId");
			appObj.RejectedType = resultSet.getInt("RejectedType");
			appObj.AllFileSubmitedDateTime = resultSet.getDate("AllFileSubmitedDateTime");
			appObj.CanceledDateTime = resultSet.getDate("CanceledDateTime");
			appObj.FundId = resultSet.getString("FundId");
			appObj.ContextId = resultSet.getString("ContextId");
			appObj.InstalmentPurposeId = resultSet.getString("InstalmentPurposeId");
			appObj.InstalmentChannel = resultSet.getInt("InstalmentChannel");
			appObj.ProductSelectedTime = resultSet.getDate("ProductSelectedTime");
			return appObj;
		}
	};
	
	public static RowMapper<AppPurposeObject> APPPURPOSEOBJECT_EXTRACTOR = new RowMapper<AppPurposeObject>() {
		@Override
		public AppPurposeObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppPurposeObject purposeObj = new AppPurposeObject();
			purposeObj.Id = resultSet.getString("Id");
			purposeObj.appId = resultSet.getString("AppId");
			purposeObj.message = resultSet.getString("Message");
			purposeObj.isActive = resultSet.getBoolean("IsActive");
			purposeObj.title = resultSet.getString("Title");	
			return purposeObj;
		}
	};
	
	public static RowMapper<AppJobInfoObject> APPJOBINFOOBJECT_EXTRACTOR = new RowMapper<AppJobInfoObject>() {
		@Override
		public AppJobInfoObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppJobInfoObject jobObj = new AppJobInfoObject();
			jobObj.appId = resultSet.getString("AppId");
			jobObj.CompanyAddress = resultSet.getString("CompanyAddress");
			jobObj.CompanyName = resultSet.getString("CompanyName");
			jobObj.DepartmentName = resultSet.getString("DepartmentName");
			jobObj.CompanyTelId = resultSet.getString("CompanyTelId");
			jobObj.JobType = resultSet.getInt("JobType");
			jobObj.Payday = resultSet.getInt("Payday");
			jobObj.MonthlyIncome = resultSet.getBigDecimal("MonthlyIncome");
			jobObj.NthJob = resultSet.getInt("NthJob");
			jobObj.JobLength = resultSet.getInt("JobLength");
			jobObj.ApplicationId = resultSet.getString("ApplicationId");
			jobObj.IsCurrent = resultSet.getBoolean("IsCurrent");
			jobObj.JobPositionType = resultSet.getInt("JobPositionType");
			jobObj.DateJoined = resultSet.getDate("DateJoined");
			return jobObj;
		}
	};
	
	public static RowMapper<AppPersonalInfoObject> APPPERSONALINFOOBJECT_EXTRACTOR = new RowMapper<AppPersonalInfoObject>() {
		@Override
		public AppPersonalInfoObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppPersonalInfoObject personObj = new AppPersonalInfoObject();
			personObj.appId = resultSet.getString("AppId");
			personObj.AppId = resultSet.getString("AppId");
			personObj.MarriageStatus = resultSet.getInt("MarriageStatus");
			personObj.LivingAddress = resultSet.getString("LivingAddress");
			personObj.LivingCondition = resultSet.getInt("LivingCondition");
			personObj.QQContactId = resultSet.getString("QQContactId");
			return personObj;
		}
	};
	
	public static RowMapper<AppUserObject> APPUSEROBJECT_EXTRACTOR = new RowMapper<AppUserObject>() {
		@Override
		public AppUserObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppUserObject usrObj = new AppUserObject();
			usrObj.appId = resultSet.getString("AppId");
			usrObj.IsRegistered = resultSet.getBoolean("IsRegistered");
		    usrObj.WeiXinAccountFollowed = resultSet.getInt("WeiXinAccountFollowed");
		    usrObj.UserName = resultSet.getString("UserName");
		    usrObj.UserNameLower = resultSet.getString("UserNameLower");
		    usrObj.Password = resultSet.getString("Password");
		    usrObj.PasswordSalt = resultSet.getString("PasswordSalt");
		    usrObj.LastLogInDate = resultSet.getDate("LastLogInDate");
		    usrObj.FailedPasswordAttemptCount = resultSet.getInt("FailedPasswordAttemptCount");
		    usrObj.FailedPasswordAttemptTime = resultSet.getDate("FailedPasswordAttemptTime");
		    usrObj.MobileContactId = resultSet.getString("MobileContactId");
		    usrObj.MobileValidatedOn = resultSet.getDate("MobileValidatedOn");
		    usrObj.WeiXinUserId = resultSet.getString("WeiXinUserId");
			return usrObj;
		}
	};
	
	public static RowMapper<AppEndUserExtensionObject> APPENDUSEREXTENSIONOBJECT_EXTRACTOR = new RowMapper<AppEndUserExtensionObject>() {
		@Override
		public AppEndUserExtensionObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppEndUserExtensionObject endUsrObj = new AppEndUserExtensionObject();
			endUsrObj.appId = resultSet.getString("AppId");
			endUsrObj.id = resultSet.getString("Id");
			endUsrObj.userType = resultSet.getInt("UserType");
		    endUsrObj.userAccountStatus = resultSet.getInt("UserAccountStatus");
		    endUsrObj.isStudent = resultSet.getBoolean("IsStudent");
		    endUsrObj.education = resultSet.getInt("Education");
		    endUsrObj.personalInfoFilledOn = resultSet.getDate("PersonalInfoFilledOn");
		    endUsrObj.merchantStoreId = resultSet.getString("MerchantStoreId");
		    endUsrObj.idResultId = resultSet.getString("IdResultId");
//			endUsrObj.dateAdded = resultSet.getDate("DateAdded");
//			endUsrObj.lastModified = resultSet.getDate("LastModified");
		    endUsrObj.idName = resultSet.getString("IdName");
		    endUsrObj.realNameFilledOn = resultSet.getDate("RealNameFilledOn");
		    endUsrObj.idNumber = resultSet.getString("IdNumber");
		    endUsrObj.merchantUserId = resultSet.getString("MerchantUserId");
		    endUsrObj.userCreditLevel = resultSet.getInt("UserCreditLevel");
		    endUsrObj.isAvailable = resultSet.getBoolean("IsAvailable");
			return endUsrObj;
		}
	};
	
	public static RowMapper<InstalmentApplicationSnapObject> INSTALMENTAPPLICATIONSNAPOBJECT_EXTRACTOR = 
			new RowMapper<InstalmentApplicationSnapObject>() {
		@Override
		public InstalmentApplicationSnapObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			InstalmentApplicationSnapObject appSnapObj = new InstalmentApplicationSnapObject();
			appSnapObj.Id = resultSet.getString("Id");
			appSnapObj.InstalmentAppId = resultSet.getString("InstalmentAppId");
			appSnapObj.S1Id = resultSet.getString("S1Id");
			appSnapObj.S2Id = resultSet.getString("S2Id");
			appSnapObj.S3Id = resultSet.getString("S3Id");
			appSnapObj.D1Id = resultSet.getString("D1Id");
			appSnapObj.D2Id = resultSet.getString("D2Id");
			appSnapObj.D3Id = resultSet.getString("D3Id");
			appSnapObj.BD1Id = resultSet.getString("BD1Id");
			appSnapObj.BD2Id = resultSet.getString("BD2Id");
			appSnapObj.BD3Id = resultSet.getString("BD3Id");
			appSnapObj.BD4Id = resultSet.getString("BD4Id");
			appSnapObj.POSId = resultSet.getString("POSId");
			appSnapObj.SellerId = resultSet.getString("SellerId");
			appSnapObj.CommissionConfigId = resultSet.getString("CommissionConfigId");
			appSnapObj.CommissionId = resultSet.getString("CommissionId");
			appSnapObj.MerchantStoreRiskControlId = resultSet.getString("MerchantStoreRiskControlId");
			return appSnapObj;
		}
	};
	
	public static RowMapper<AppRuleEngineScoreResultObject> APPRULEENGINESCORERESULTOBJECT_EXTRACTOR = 
			new RowMapper<AppRuleEngineScoreResultObject>() {
		@Override
		public AppRuleEngineScoreResultObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppRuleEngineScoreResultObject ruleEngineResObj = new AppRuleEngineScoreResultObject();
			ruleEngineResObj.appId = resultSet.getString("AppId");
			ruleEngineResObj.Id = resultSet.getString("Id");
			ruleEngineResObj.ApplicationScore = resultSet.getDouble("ApplicationScore");
		    ruleEngineResObj.BehaviorScore = resultSet.getDouble("BehaviorScore");
		    ruleEngineResObj.ConsistencyCheckScore = resultSet.getDouble("ConsistencyCheckScore");
		    ruleEngineResObj.CreditReferenceScore = resultSet.getDouble("CreditReferenceScore");
		    ruleEngineResObj.FraudCheckScore = resultSet.getDouble("FraudCheckScore");
		    ruleEngineResObj.InvestigationScore = resultSet.getDouble("InvestigationScore");
		    ruleEngineResObj.PersonalInfoScore = resultSet.getDouble("PersonalInfoScore");
		    ruleEngineResObj.SocialRelationScore = resultSet.getDouble("SocialRelationScore");
		    ruleEngineResObj.StoreLevel = resultSet.getString("StoreLevel");
		    ruleEngineResObj.RuleEngineResultId = resultSet.getString("RuleEngineResultId");
			return ruleEngineResObj;
		}
	};
	
	public static RowMapper<AppContactPersonObject> APPCONTACTPERSONOBJECT_EXTRACTOR = 
			new RowMapper<AppContactPersonObject>() {
		@Override
		public AppContactPersonObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppContactPersonObject conPerObj = new AppContactPersonObject();
			conPerObj.Id = resultSet.getString("Id");
			conPerObj.Name = resultSet.getString("Name");
			conPerObj.Relationship = resultSet.getInt("Relationship");
			conPerObj.ContactPersonType = resultSet.getInt("ContactPersonType");
			conPerObj.MobileContactId = resultSet.getString("MobileContactId");
			conPerObj.mobile = resultSet.getString("Mobile");
			conPerObj.AppId = resultSet.getString("AppId");
			return conPerObj;
		}
	};
	
	public static RowMapper<AppContactObject> APPCONTACTOBJECT_EXTRACTOR = 
			new RowMapper<AppContactObject>() {
		@Override
		public AppContactObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppContactObject conObj = new AppContactObject();
//			conObj.Id = resultSet.getString("Id");
			conObj.appId = resultSet.getString("AppId");
			conObj.mobile = resultSet.getString("Mobile");
			conObj.qqNumber = resultSet.getString("QQNumber");
			conObj.companyPhone = resultSet.getString("CompanyPhone");
			if (conObj.mobile == null && conObj.qqNumber == null && conObj.companyPhone == null)
			{
				conObj.Content = null;
			}
			else if (conObj.mobile != null)	
			{
				conObj.Content = conObj.mobile;
				conObj.ContactType = 2;
			}
			else if (conObj.qqNumber != null)	
			{
				conObj.Content = conObj.qqNumber;
				conObj.ContactType = 1;
			}
			else if (conObj.companyPhone != null)	
			{
				conObj.Content = conObj.companyPhone;
				conObj.ContactType = 4;
			}
			return conObj;
		}
	};
	
	public static RowMapper<AppOtherInfoObject> APPOTHERINFOOBJECT_EXTRACTOR = 
			new RowMapper<AppOtherInfoObject>() {
		@Override
		public AppOtherInfoObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppOtherInfoObject otherObj = new AppOtherInfoObject();
			otherObj.appId = resultSet.getString("AppId");
			otherObj.nickName = resultSet.getString("NickName"); 
			otherObj.bankAccount = resultSet.getString("BankAccount");	
			otherObj.headImageUrl = resultSet.getString("HeadImageUrl");	
			otherObj.isReported = resultSet.getInt("IsReported") == 1 ? true : false;	
			otherObj.isRecommended = resultSet.getInt("IsRecommended") == 1 ? true : false;	
			otherObj.isOutOfTouch = resultSet.getBoolean("IsOutOfTouch");	
			return otherObj;
		}
	};
	
	public static RowMapper<AppFuserObject> APPFUSEROBJECT_EXTRACTOR = 
			new RowMapper<AppFuserObject>() {
		@Override
		public AppFuserObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppFuserObject fuserObj = new AppFuserObject();
			fuserObj.Id = resultSet.getString("Id");
			fuserObj.appId = resultSet.getString("AppId");
			fuserObj.mobile = resultSet.getString("Mobile");	
			fuserObj.idName = resultSet.getString("IdName");	
			fuserObj.userId = resultSet.getString("UserId");	
//			fuserObj.idNumber = resultSet.getString("IdNumber");	
			//fuserObj.bankAccount = resultSet.getString("BankAccount");	
			return fuserObj;
		}
	};
	
	public static RowMapper<AppMerchantInfoObject> APPMERCHANTINFOOBJECT_EXTRACTOR = 
			new RowMapper<AppMerchantInfoObject>() {
		@Override
		public AppMerchantInfoObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
		{
			AppMerchantInfoObject merchantInfoObj = new AppMerchantInfoObject();
			merchantInfoObj.appId = resultSet.getString("AppId");
			merchantInfoObj.mobile = resultSet.getString("Mobile");	
//			merchantInfoObj.qqNumber = resultSet.getString("QQNumber");
			merchantInfoObj.bankAccount = resultSet.getString("BankAccount");
			merchantInfoObj.posName = resultSet.getString("PosName");
			merchantInfoObj.s1Name = resultSet.getString("S1Name");	
			merchantInfoObj.d1Name = resultSet.getString("D1Name");	
			merchantInfoObj.bd2Name = resultSet.getString("BD2Name");		
			return merchantInfoObj;
		}
	};
	
	/**
	 * 金融产品信息Data2Object
	 */
	public static RowMapper<AppProductObject> PRODUCTOBJECT_EXTRACTOR = 
            new RowMapper<AppProductObject>() {
        @Override
        public AppProductObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppProductObject productObj = new AppProductObject();
            productObj.appId = resultSet.getString("AppId");
            productObj.name = resultSet.getString("Name");
            return productObj;
        }
    };
    
    /**
     * D1信息Data2Object
     */
    public static RowMapper<AppMerchantInfoObject> APPD1OBJECT_EXTRACTOR = 
            new RowMapper<AppMerchantInfoObject>() {
        @Override
        public AppMerchantInfoObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppMerchantInfoObject obj = new AppMerchantInfoObject();
            obj.appId = resultSet.getString("AppId");
            obj.d1Mobile = resultSet.getString("D1Mobile");
            return obj;
        }
    };
    
    /**
     * D2信息Data2Object
     */
    public static RowMapper<AppMerchantInfoObject> APPD2OBJECT_EXTRACTOR = 
            new RowMapper<AppMerchantInfoObject>() {
        @Override
        public AppMerchantInfoObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppMerchantInfoObject obj = new AppMerchantInfoObject();
            obj.appId = resultSet.getString("AppId");
            obj.d2Name = resultSet.getString("D2Name");
            return obj;
        }
    };
    
    /**
     * 门店信息Data2Object
     */
    public static RowMapper<AppMerchantInfoObject> APPMERCHANT_EXTRACTOR = 
            new RowMapper<AppMerchantInfoObject>() {
        @Override
        public AppMerchantInfoObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppMerchantInfoObject obj = new AppMerchantInfoObject();
            obj.appId = resultSet.getString("AppId");
            obj.storeCategory = resultSet.getInt("StoreCategory");
            return obj;
        }
    };
    
    /**
     * 用户扫码记录信息Data2Object
     */
    public static RowMapper<AppUserScanRecordObject> APPUSERSCANRECORD_EXTRACTOR = 
            new RowMapper<AppUserScanRecordObject>() {
        @Override
        public AppUserScanRecordObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppUserScanRecordObject obj = new AppUserScanRecordObject();
            obj.appId = resultSet.getString("AppId");
            obj.scanDateTime = resultSet.getString("ScanDateTime");
            obj.merchantStoreId = resultSet.getString("MerchantStoreId");
            obj.merchantStoreName = resultSet.getString("MerchantStoreName");
            obj.merchantUserId = resultSet.getString("MerchantUserId");
            obj.merchantUserName = resultSet.getString("MerchantUserName");
            return obj;
        }
    };
    
    /**
     * 批核数、拒绝数、批核率信息Data2Object
     */
    public static RowMapper<AppCountObject> APPCOUNT_EXTRACTOR = 
            new RowMapper<AppCountObject>() {
        @Override
        public AppCountObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppCountObject obj = new AppCountObject();
            obj.appId = resultSet.getString("AppId");
            obj.totalCountInHistory = resultSet.getInt("TotalCountInHistory");
            obj.approvedCountInHistory = resultSet.getInt("ApprovedCountInHistory");
            obj.rejectedCountInHistory = resultSet.getInt("RejectedCountInHistory");
            obj.approvedRateInHistory = resultSet.getBigDecimal("ApprovedRateInHistory");
            return obj;
        }
    };
    
    /**
     * 用户总申请次数、用户总申请批准次数、用户总申请拒绝次数、用户总申请取消次数信息Data2Object
     */
    public static RowMapper<AppCountObject> APPUSERCOUNT_EXTRACTOR = 
            new RowMapper<AppCountObject>() {
        @Override
        public AppCountObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppCountObject obj = new AppCountObject();
            obj.appId = resultSet.getString("AppId");
            obj.totalCountInHistory = resultSet.getInt("TotalCountInHistory");
            obj.approvedCountInHistory = resultSet.getInt("ApprovedCountInHistory");
            obj.rejectedCountInHistory = resultSet.getInt("RejectedCountInHistory");
            obj.canceledCountInHistory = resultSet.getInt("CanceledCountInHistory");
            obj.totalCountInPM1 = resultSet.getInt("TotalCountInPM1");
            obj.approvedCountInPM1 = resultSet.getInt("ApprovedCountInPM1");
            obj.rejectedCountInPM1 = resultSet.getInt("RejectedCountInPM1");
            obj.canceledCountInPM1 = resultSet.getInt("CanceledCountInPM1");
            return obj;
        }
    };
    
    /**
     * 用户扫码次数、时间信息Data2Object
     */
    public static RowMapper<AppUserScanCountObject> APPUSERSCANCOUNT_EXTRACTOR = 
            new RowMapper<AppUserScanCountObject>() {
        @Override
        public AppUserScanCountObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppUserScanCountObject obj = new AppUserScanCountObject();
            obj.appId = resultSet.getString("AppId");
            obj.scannedCountInHistory = resultSet.getInt("ScannedCountInHistory");
            obj.scannedCountInPM1 = resultSet.getInt("ScannedCountInPM1");
            return obj;
        }
    };
    
    /**
     * 用户扫码次数、时间信息Data2Object
     */
    public static RowMapper<AppUserScanCountObject> APPUSERFIRSTSCAN_EXTRACTOR = 
            new RowMapper<AppUserScanCountObject>() {
        @Override
        public AppUserScanCountObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppUserScanCountObject obj = new AppUserScanCountObject();
            obj.appId = resultSet.getString("AppId");
            obj.firstScanDateTime = resultSet.getString("FirstScanDateTime");
            obj.daysSinceFirstScan = resultSet.getInt("DaysSinceFirstScan");
            return obj;
        }
    };
    
    /**
     * D2FPD7%信息Data2Object
     */
    public static RowMapper<AppCountObject> APPD2FPD7RATE_EXTRACTOR = 
            new RowMapper<AppCountObject>() {
        @Override
        public AppCountObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppCountObject obj = new AppCountObject();
            obj.appId = resultSet.getString("AppId");
            obj.d2FPD7Rate = resultSet.getBigDecimal("D2FPD7Rate");
            return obj;
        }
    };
    
    /**
     * S/D关联App信息Data2Object
     */
    public static RowMapper<AppSDAssociatedAppObject> APPASSOCIATEDAPP_EXTRACTOR = 
            new RowMapper<AppSDAssociatedAppObject>() {
        @Override
        public AppSDAssociatedAppObject mapRow(ResultSet resultSet, int rowIndex) throws SQLException 
        {
            AppSDAssociatedAppObject obj = new AppSDAssociatedAppObject();
            obj.appId = resultSet.getString("AppId");
            obj.associatedAppId = resultSet.getString("AssociatedAppId");
            return obj;
        }
    };
}
