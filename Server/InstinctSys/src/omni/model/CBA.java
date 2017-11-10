package omni.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import catfish.base.business.common.StoreType;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.InstalmentApplicationSnapObject;
import omni.database.DataContainer;
import omni.database.catfish.object.hybrid.AppCountObject;
import omni.database.catfish.object.hybrid.AppFuserObject;
import omni.database.catfish.object.hybrid.AppMerchantInfoObject;
import omni.database.catfish.object.hybrid.AppRuleEngineScoreResultObject;
import omni.database.catfish.object.hybrid.AppUserScanRecordObject;
import omni.database.mongo.DerivativeVariables;
import util.EnumUtil;
import util.InstinctizeUtil;

public class CBA 
{
	private String s1QQ = null; 		//	S1Id(MerchantUserId)->[catfish].[dbo].[MerchantUserContactObjects]
								//	ContactId->catfish].[dbo].[ContactObjects]->content&contacttype=1	(Id_Number1)
	private final String d1QQ = null;	//	(Id_Number2)
	private String s1Name;			//	S1Id->[catfish].[dbo].[MerchantUserObjects]->IdName	(Surname)
	private String d1Name;			//	D1Id->[DealerUserObjects]->IdName	(First_Name)
	private String bd2Name;			//	BD2Id->[BusinessDevelopUserObjects]->IdName	(Middle_Name)

	private String x_MerchantProvince;		//	mongodb	(Home_Address1)
	private String x_MerchantCity;			//			(Home_Address2)
	private String x_MerchantDistrict;		//			(Home_Address3)
	
	private String s1Mobile;		//	S1Id(MerchantUserId)->[catfish].[dbo].[MerchantUserContactObjects]->
									//	ContactId->catfish].[dbo].[ContactObjects]->content&contacttype=2
									//	S1Id->[catfish].[dbo].[MerchantUserObjects]->UserId	(User_Field4)
									//	->[catfish].[dbo].[UserObjects]->MobileContactId->[catfish].[dbo].[ContactObjects]->content&type=2	
	private String storeLevel;		//	[catfish].[dbo].[RuleEngineResultObjects]->id->[RuleEngineResultId]->
									//	[catfish].[dbo].[RuleEngineScoreResultObjects]	(User_Field5)

	private Float apr;				//	InstallmentApplication	(User_Field6)
	private String s1BankAccount;		//	S1Id-> [catfish].[dbo].[MerchantUserPaymentObjects]->PaymentId	(User_Field7)
									//	->[catfish].[dbo].[PaymentObjects] order by lastModified desc;->BankAccount	
	private final String d1BankAccount = null;	//	Null	(User_Field8)
	
	//	Mangodb	
	public Integer	x_MerchantApplicationHistoryApproved;
	public Integer	x_MerchantApplicationHistoryDelayed;
	private Double posOverduerate;	// x_MerchantApplicationHistoryDelayed/x_MerchantApplicationHistoryApproved	(User_Field9)
	
	public Integer	x_MerchantCompanyApplicationHistoryApproved;
	public Integer	x_MerchantCompanyApplicationHistoryDelayed;
	private Double s3Overduerate; 	//	x_MerchantCompanyApplicationHistoryDelayed/x_MerchantCompanyApplicationHistoryApproved	(User_Field10)
	
	public Integer	x_MerchantUserApplicationHistoryApproved;
	public Integer	x_MerchantUserApplicationHistoryDelayed;
	private Double s1Overduerate;	//	x_MerchantUserApplicationHistoryDelayed/x_MerchantUserApplicationHistoryApproved	(User_Field11)

	private String s1Id;	//	instalmentAppid->InstalmentApplicationSnapObject	(User_Field12)
	private String d1Id;	//	instalmentAppid->InstalmentApplicationSnapObject	(User_Field13)
	private String bd2Id;	//	instalmentAppid->InstalmentApplicationSnapObject	(User_Field14)
	private String posName;	//	[catfish].[dbo].[MerchantStoreObjects] name	(User_Field29) 
	
	/** D1手机号 */
    private String d1Mobile;
    /** D2姓名 */
    private String d2Name;
    /** 门店种类 */
    private String storeCategory;
    /** 用户扫码记录1 */
    private String userScanRecord1;
    /** 用户扫码记录2 */
    private String userScanRecord2;
    /** 用户扫码记录3 */
    private String userScanRecord3;
    
    /** POS批核数 */
    private Integer posApprovedCount;
    /** POS拒绝数 */
    private Integer posRejectedCount;
    /** D1批核数 */
    private Integer d1ApprovedCount;
    /** D1拒绝数 */
    private Integer d1RejectedCount;
    /** D1批核率 */
    private BigDecimal d1ApprovedRate;
    /** S1批核数 */
    private Integer s1ApprovedCount;
    /** S1拒绝数 */
    private Integer s1RejectedCount;
    /** S1批核率 */
    private BigDecimal s1ApprovedRate;
    /** D2FPD7% */
    private BigDecimal d2FPD7Rate;
    
	public CBA(String appId)
	{
		this(appId, "");
	}

	public CBA(String appId, String instinctCall) 
	{
		if (!"precheck".equalsIgnoreCase(instinctCall)) 
		{
			Application omniApp = new Application(appId);
			this.initialize(appId, omniApp.getAppType());
		}
	}

	/**
	 * This class is for PDL's constructor with FuserId.
	 * 
	 * @param appId	application ID.
	 * @param appType supports "PDL", "PSL" and "CL".
	 * 
	 */
	private void initialize(String appId, String appType)
	{
		if ("PDL".equalsIgnoreCase(appType))
		{
			InstallmentApplicationObject app = DataContainer.appObj.get(appId); 
			AppFuserObject appFuserObj = DataContainer.fuserObj.get(appId); 
			AppRuleEngineScoreResultObject ruleEngineScoreObj = DataContainer.ruleEngineScoreObj.get(appId); 
			DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
			
			if (appFuserObj != null)
			{
				this.s1Id = appFuserObj.Id;
				this.s1Name = appFuserObj.idName;
				this.s1Mobile = appFuserObj.mobile;
			}
			
			this.d1Id = null;
			this.bd2Id = null;
			this.d1Name = null;
			this.bd2Name = null;
			this.s1BankAccount = null;
			this.posName = null; 

			this.storeLevel = ruleEngineScoreObj == null ? null : ruleEngineScoreObj.StoreLevel;
			this.apr = new Float(app.APR);

			if (mongodv != null)
			{
				this.x_MerchantProvince = mongodv.X_MerchantProvince;
				this.x_MerchantCity = mongodv.X_MerchantCity;
				this.x_MerchantDistrict = mongodv.X_MerchantDistrict;

				this.x_MerchantApplicationHistoryApproved = mongodv.X_MerchantApplicationHistoryApproved;
				this.x_MerchantApplicationHistoryDelayed = mongodv.X_MerchantApplicationHistoryDelayed;
				this.posOverduerate = 
					(x_MerchantApplicationHistoryApproved == null || x_MerchantApplicationHistoryApproved == 0 || x_MerchantApplicationHistoryDelayed == null) 
					? null : x_MerchantApplicationHistoryDelayed.doubleValue() / x_MerchantApplicationHistoryApproved.doubleValue();
				
				this.x_MerchantCompanyApplicationHistoryApproved = mongodv.X_MerchantCompanyApplicationHistoryApproved;
				this.x_MerchantCompanyApplicationHistoryDelayed = mongodv.X_MerchantCompanyApplicationHistoryDelayed;
				this.s3Overduerate = 
					(x_MerchantCompanyApplicationHistoryApproved == null || x_MerchantCompanyApplicationHistoryApproved == 0 || x_MerchantCompanyApplicationHistoryDelayed == null)
					? null : x_MerchantCompanyApplicationHistoryDelayed.doubleValue() / x_MerchantCompanyApplicationHistoryApproved.doubleValue();
				
				this.x_MerchantUserApplicationHistoryApproved = mongodv.X_MerchantUserApplicationHistoryApproved;
				this.x_MerchantUserApplicationHistoryDelayed = mongodv.X_MerchantUserApplicationHistoryDelayed;
				this.s1Overduerate = 
					(x_MerchantUserApplicationHistoryApproved == null || x_MerchantUserApplicationHistoryApproved == 0 || x_MerchantUserApplicationHistoryDelayed == null)
					? null : x_MerchantUserApplicationHistoryDelayed.doubleValue() / x_MerchantUserApplicationHistoryApproved.doubleValue();
			}		
		}
		else
		{
			InstallmentApplicationObject app = DataContainer.appObj.get(appId); 
			InstalmentApplicationSnapObject appSnap = DataContainer.appSnapObj.get(appId); 
			AppRuleEngineScoreResultObject ruleEngineScoreObj = DataContainer.ruleEngineScoreObj.get(appId); 
			AppMerchantInfoObject merchantInfoObj = DataContainer.merchantInfoObj.get(appId);
			DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
			// v20160809 add start
			List<AppUserScanRecordObject> userScanRecordList = DataContainer.userScanRecordObj.get(appId);
			// v20160809 add end
			// v20160830 add start
			AppCountObject posCount = DataContainer.posCountObj.get(appId);
			AppCountObject d1Count = DataContainer.d1CountObj.get(appId);
			AppCountObject s1Count = DataContainer.s1CountObj.get(appId);
			AppCountObject pd = DataContainer.pdObj.get(appId);
	        // v20160830 add end
			
			if (appSnap != null)
			{
				this.s1Id = appSnap.S1Id;
				this.d1Id = appSnap.D1Id;
				this.bd2Id = appSnap.BD2Id;
			}
			
			if (merchantInfoObj != null)
			{
				this.s1Name = merchantInfoObj.s1Name;
				this.d1Name = merchantInfoObj.d1Name;
				this.bd2Name = merchantInfoObj.bd2Name;
				this.s1Mobile = merchantInfoObj.mobile;
				this.s1BankAccount = merchantInfoObj.bankAccount;
				this.posName = merchantInfoObj.posName;
				// v20160809 add start
				this.d1Mobile = merchantInfoObj.d1Mobile;
				this.d2Name = merchantInfoObj.d2Name;
				this.storeCategory = EnumUtil.getText(StoreType.class, merchantInfoObj.storeCategory);
				// v20160809 add end
			}
			
			this.storeLevel = ruleEngineScoreObj == null ? null : ruleEngineScoreObj.StoreLevel;
			this.apr = new Float(app.APR);
			
			if (mongodv != null)
			{
				this.x_MerchantProvince = mongodv.X_MerchantProvince;
				this.x_MerchantCity = mongodv.X_MerchantCity;
				this.x_MerchantDistrict = mongodv.X_MerchantDistrict;

				this.x_MerchantApplicationHistoryApproved = mongodv.X_MerchantApplicationHistoryApproved;
				this.x_MerchantApplicationHistoryDelayed = mongodv.X_MerchantApplicationHistoryDelayed;
				this.posOverduerate = 
					(x_MerchantApplicationHistoryApproved == null || x_MerchantApplicationHistoryApproved == 0 || x_MerchantApplicationHistoryDelayed == null)
					? null : x_MerchantApplicationHistoryDelayed.doubleValue() / x_MerchantApplicationHistoryApproved.doubleValue();
				
				this.x_MerchantCompanyApplicationHistoryApproved = mongodv.X_MerchantCompanyApplicationHistoryApproved;
				this.x_MerchantCompanyApplicationHistoryDelayed = mongodv.X_MerchantCompanyApplicationHistoryDelayed;
				this.s3Overduerate = 
					(x_MerchantCompanyApplicationHistoryApproved == null || x_MerchantCompanyApplicationHistoryApproved == 0 || x_MerchantCompanyApplicationHistoryDelayed == null)
					? null : x_MerchantCompanyApplicationHistoryDelayed.doubleValue() / x_MerchantCompanyApplicationHistoryApproved.doubleValue();
				
				this.x_MerchantUserApplicationHistoryApproved = mongodv.X_MerchantUserApplicationHistoryApproved;
				this.x_MerchantUserApplicationHistoryDelayed = mongodv.X_MerchantUserApplicationHistoryDelayed;
				this.s1Overduerate = 
					(x_MerchantUserApplicationHistoryApproved == null || x_MerchantUserApplicationHistoryApproved == 0 || x_MerchantUserApplicationHistoryDelayed == null)
					? null : x_MerchantUserApplicationHistoryDelayed.doubleValue() / x_MerchantUserApplicationHistoryApproved.doubleValue();
			}
			
			// v20160809 add start
			if (userScanRecordList != null) {
			    // 排序
			    Collections.sort(userScanRecordList);
			    int size = userScanRecordList.size();
			    if (size > 0) {
			        this.userScanRecord1 = this.makeUserScanRecord(userScanRecordList.get(0));
			    }
			    if (size > 1) {
                    this.userScanRecord2 = this.makeUserScanRecord(userScanRecordList.get(1));
                }
			    if (size > 2) {
                    this.userScanRecord3 = this.makeUserScanRecord(userScanRecordList.get(2));
                }
			}
			// v20160809 add end
			// v20160830 add start
			if (posCount != null) {
			    this.posApprovedCount = posCount.approvedCountInHistory;
			    this.posRejectedCount = posCount.rejectedCountInHistory;
			} else {
			    this.posApprovedCount = 0;
                this.posRejectedCount = 0;
			}
			if (d1Count != null) {
                this.d1ApprovedCount = d1Count.approvedCountInHistory;
                this.d1RejectedCount = d1Count.rejectedCountInHistory;
                this.d1ApprovedRate = d1Count.approvedRateInHistory;
            } else {
                this.d1ApprovedCount = 0;
                this.d1RejectedCount = 0;
                this.d1ApprovedRate = BigDecimal.ZERO;
            }
			if (s1Count != null) {
                this.s1ApprovedCount = s1Count.approvedCountInHistory;
                this.s1RejectedCount = s1Count.rejectedCountInHistory;
                this.s1ApprovedRate = s1Count.approvedRateInHistory;
            } else {
                this.s1ApprovedCount = 0;
                this.s1RejectedCount = 0;
                this.s1ApprovedRate = BigDecimal.ZERO;
            }
			if (pd != null) {
			    this.d2FPD7Rate = pd.d2FPD7Rate;
			} else {
			    this.d2FPD7Rate = BigDecimal.ZERO;
			}
			// v20160830 add end
		}
	}
	
	/**
	 * 拼接用户扫码记录信息
	 * @return
	 */
	private String makeUserScanRecord(AppUserScanRecordObject obj) {
	    StringBuilder record = new StringBuilder();
	    record.append(obj.scanDateTime);
	    record.append(" 用户扫描了");
	    record.append(InstinctizeUtil.string(obj.merchantStoreName));
	    record.append(" POS下 ");
	    record.append(InstinctizeUtil.string(obj.merchantUserName));
	    record.append("的二维码");

	    return record.toString();
	}
	
	public final String getS1Id()
	{
		return this.s1Id;
	}
	
	public final String getD1Id()
	{
		return this.d1Id;
	}
	
	public final String getBD2Id()
	{
		return this.bd2Id;
	}
	
	public final String getS1Name()
	{
		return this.s1Name;
	}

	public final String getD1Name()
	{
		return this.d1Name;
	}
	
	public final String getBD2Name()
	{
		return this.bd2Name;
	}
	
	public final String getS1Mobile()
	{
		return this.s1Mobile;
	}
	
	public final String getStoreLevelResult()
	{
		return this.storeLevel;
	}
	
	public final String getX_MerchantProvince()
	{
		return this.x_MerchantProvince;
	}
	
	public final String getX_MerchantCity()
	{
		return this.x_MerchantCity;
	}
	
	public final String getX_MerchantDistrict()
	{
		return this.x_MerchantDistrict;
	}
	
	public final Float getAPR()
	{
		return this.apr;
	}
	
	public final String getS1QQ()
	{
		return this.s1QQ;
	}
	
	public final String getS1BankAccount()
	{
		return this.s1BankAccount;
	}
	
	public final String getD1QQ()
	{
		return this.d1QQ;
	}
	
	public final String getD1BankAccount()
	{
		return this.d1BankAccount;
	}
	
	public final Double getPOSOverduerate()
	{
//		POSoverduerate = x_MerchantApplicationHistoryDelayed/x_MerchantApplicationHistoryApproved;
		return this.posOverduerate;
	}
	
	public final Double getS3Overduerate()
	{
//		S3Overduerate = x_MerchantCompanyApplicationHistoryDelayed/x_MerchantCompanyApplicationHistoryApproved;
		return this.s3Overduerate;
	}
	
	public final Double getS1Overduerate()
	{
//		S1Overduerate = x_MerchantUserApplicationHistoryDelayed/x_MerchantUserApplicationHistoryApproved;
		return this.s1Overduerate;
	}

	public final String getPOSName()
	{
		return this.posName;
	}

    public String getD1Mobile() {
        return d1Mobile;
    }

    public String getD2Name() {
        return d2Name;
    }

    public String getStoreCategory() {
        return storeCategory;
    }

    public String getUserScanRecord1() {
        return userScanRecord1;
    }

    public String getUserScanRecord2() {
        return userScanRecord2;
    }

    public String getUserScanRecord3() {
        return userScanRecord3;
    }
    
    public Integer getPosApprovedCount() {
        return posApprovedCount;
    }

    public Integer getPosRejectedCount() {
        return posRejectedCount;
    }

    public Integer getD1ApprovedCount() {
        return d1ApprovedCount;
    }

    public Integer getD1RejectedCount() {
        return d1RejectedCount;
    }

    public BigDecimal getD1ApprovedRate() {
        return d1ApprovedRate;
    }

    public Integer getS1ApprovedCount() {
        return s1ApprovedCount;
    }

    public Integer getS1RejectedCount() {
        return s1RejectedCount;
    }

    public BigDecimal getS1ApprovedRate() {
        return s1ApprovedRate;
    }

    public BigDecimal getD2FPD7Rate() {
        return d2FPD7Rate;
    }
}
