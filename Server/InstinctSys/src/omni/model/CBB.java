package omni.model;

import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.RuleEngineScoreResultObject;
import omni.database.DataContainer;
import omni.database.catfish.object.hybrid.AppCountObject;
import omni.database.catfish.object.hybrid.AppOtherInfoObject;
import omni.database.catfish.object.hybrid.AppUserScanCountObject;
import omni.database.mongo.DerivativeVariables;
import omni.model.type.LoanMoneyFailureReasonType;
import omni.model.type.LoanMoneyResult;
import omni.model.type.MongoDate;

public class CBB 
{
	private Boolean x_IsUserInfoInBlacklist;			//	(Id_Number1)
	private Boolean isCanceled;		//	[catfish].[dbo].[InstallmentApplicationObjects] Status=-1	(Id_Number2)
	private Boolean isRecommended;	//	appId->AffiliateOperationObjects tag==AffiliateOperationType.Recommend	(Id_Number3)
	private Boolean isReported;		//	appId->AffiliateOperationObjects tag==AffiliateOperationType.Report	(Surname)
	private Boolean isOutOfTouch;	//	[catfish].[dbo].[EndUserExtensionObjects]->Id->
									//	EndUserId->[catfish].[dbo].[EndUserOutOfTouchRecordObjects]	(First_Name)
	// TODO: Computed by BatchJob in C#
	private final Double isFPD30 = null;	//	(middle_Name)

	private Double personalInfoScore;	//	[catfish].[dbo].[RuleEngineResultObjects]->id->[RuleEngineResultId]->
										//	[catfish].[dbo].[RuleEngineScoreResultObjects]		(User_Field2)
	
	private final Integer reuploadCount = null;	//	Null	(User_Field3)
	private Double investigationScore;		//	(User_Field4)
	private Double creditReferenceScore;	//	(User_Field5)
	private Double fraudCheckScore;		//	(User_Field6)
	private Double applicationScore;		//	(User_Field7)
	private Double behaviorScore;		//	(User_Field8)
	
	//	Mongodb:db.prod_derivativevariables.find({x_IdCardNumberDelayedCount:{$exists:true}});
	private Integer x_IdCardNumberDelayedCount;		//	(User_Field9)
	private Integer x_IdCardNumberRepayingCount;		//	(User_Field10)
	private String x_IdCardNumberLastRejectedDate;	//	(User_Field11)
	private Boolean x_IsIdInSupremeCourtBlacklist;	//	(User_Field12)
	private Integer x_LoanMoneyFailureReason;		//	(User_Field13)
	private Integer x_LoanMoneyResult;				//	(User_Field14)
	
	private final String blacklistReason = "";	//	(User_Field15)
	private final String blacklistSource = "";	//	(User_Field16)

	private Integer x_GroupInfoAppTotalCount;	//	(User_Field17)
	private Integer x_GroupInfoUserTotalCount;	//	(User_Field18)
	
	private String headImageUrl;	//	WeiXinUserId->[catfish].[dbo].[WeiXinUserObjects]	(Id_Number)	(User_Field29)
	
	/** 用户总申请次数 */
    private Integer totalCountInHistory;
    /** 用户总申请批准次数 */
    private Integer approvedCountInHistory;
    /** 用户总申请拒绝次数 */
    private Integer rejectedCountInHistory;
    /** 用户总申请取消次数 */
    private Integer canceledCountInHistory;
    /** 用户总申请扫描pos门店次数 */
    private Integer scannedPOSCountInHistory;
    /** 用户总申请扫描代理商次数 */
    private Integer scannedAffiliateCountInHistory;
    /** 用户1个月内总申请次数 */
    private Integer totalCountInPM1;
    /** 用户1个月内总申请批准次数 */
    private Integer approvedCountInPM1;
    /** 用户1个月内总申请拒绝次数 */
    private Integer rejectedCountInPM1;
    /** 用户1个月内总申请取消次数 */
    private Integer canceledCountInPM1;
    /** 用户1个月扫码门店总数 */
    private Integer scannedPOSCountInPM1;
    /** 用户1个月扫码相关S总数 */
    private Integer scannedAffiliateCountInPM1;
    /** 首次扫描二维码时间 */
    private String firstScanDateTime;
    /** 首次扫描二维码时间距今天数 */
    private Integer daysSinceFirstScan;
    
	public CBB(String appId)
	{
		this(appId, "");
	}

	public CBB(String appId, String instinctCall) 
	{
		if (!"precheck".equalsIgnoreCase(instinctCall)) 
		{
			this.initialize(appId);
		}
	}
	
	private void initialize(String appId)
	{
		InstallmentApplicationObject app = DataContainer.appObj.get(appId);
		RuleEngineScoreResultObject scoreObj = DataContainer.ruleEngineScoreObj.get(appId);
		AppOtherInfoObject otherInfoObj = DataContainer.otherInfoObj.get(appId); 
		DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
		// v20160830 add start
        AppCountObject userCount = DataContainer.userCountObj.get(appId);
        AppUserScanCountObject userScanPosCount = DataContainer.userScanPosCountObj.get(appId);
        AppUserScanCountObject userScanS1Count = DataContainer.userScanS1CountObj.get(appId);
        AppUserScanCountObject firstScan = DataContainer.firstScanObj.get(appId);
        // v20160830 add end
		
		if (scoreObj != null)
		{
			this.personalInfoScore = scoreObj.PersonalInfoScore;
			this.investigationScore = scoreObj.InvestigationScore;
			this.creditReferenceScore = scoreObj.CreditReferenceScore;
			this.fraudCheckScore = scoreObj.FraudCheckScore;
			this.applicationScore = scoreObj.ApplicationScore;
			this.behaviorScore = scoreObj.BehaviorScore;
		}
		
		if (otherInfoObj != null)
		{
			this.isRecommended = otherInfoObj.isRecommended;
			this.isReported = otherInfoObj.isReported;
			this.isOutOfTouch = otherInfoObj.isOutOfTouch;
			this.headImageUrl = otherInfoObj.headImageUrl;
		}
		
		Integer status = app.Status;
		isCanceled = status == -1 ? true : false;
		
		if (mongodv != null)
		{
			this.x_IdCardNumberDelayedCount = mongodv.X_IdCardNumberDelayedCount;
			this.x_IdCardNumberRepayingCount = mongodv.X_IdCardNumberRepayingCount;
			this.x_IdCardNumberLastRejectedDate = mongodv.X_IdCardNumberLastRejectedDate;
			this.x_IsIdInSupremeCourtBlacklist = mongodv.X_IsIdInSupremeCourtBlacklist;
			this.x_IsUserInfoInBlacklist = mongodv.X_IsUserInfoInBlacklist;
			this.x_LoanMoneyFailureReason = mongodv.X_LoanMoneyFailureReason == null
					? mongodv.X_LoanMoneyFailureReasonPDL : mongodv.X_LoanMoneyFailureReason;
			this.x_LoanMoneyResult = mongodv.X_LoanMoneyResult == null
					? mongodv.X_LoanMoneyResultForPDL : mongodv.X_LoanMoneyResult;
			this.x_GroupInfoAppTotalCount = mongodv.X_GroupInfoAppTotalCount;
			this.x_GroupInfoUserTotalCount = mongodv.X_GroupInfoUserTotalCount;
		}
		
		// v20160830 add start
		if (userCount != null) {
		    this.totalCountInHistory = userCount.totalCountInHistory;
		    this.approvedCountInHistory = userCount.approvedCountInHistory;
		    this.rejectedCountInHistory = userCount.rejectedCountInHistory;
		    this.canceledCountInHistory = userCount.canceledCountInHistory;
		    this.totalCountInPM1 = userCount.totalCountInPM1;
            this.approvedCountInPM1 = userCount.approvedCountInPM1;
            this.rejectedCountInPM1 = userCount.rejectedCountInPM1;
            this.canceledCountInPM1 = userCount.canceledCountInPM1;
		} else {
		    this.totalCountInHistory = 0;
            this.approvedCountInHistory = 0;
            this.rejectedCountInHistory = 0;
            this.canceledCountInHistory = 0;
            this.totalCountInPM1 = 0;
            this.approvedCountInPM1 = 0;
            this.rejectedCountInPM1 = 0;
            this.canceledCountInPM1 = 0;
		}
		if (userScanPosCount != null) {
            this.scannedPOSCountInHistory = userScanPosCount.scannedCountInHistory;
            this.scannedPOSCountInPM1 = userScanPosCount.scannedCountInPM1;
        } else {
            this.scannedPOSCountInHistory = 0;
            this.scannedPOSCountInPM1 = 0;
        }
		if (userScanS1Count != null) {
		    this.scannedAffiliateCountInHistory = userScanS1Count.scannedCountInHistory;
            this.scannedAffiliateCountInPM1 = userScanS1Count.scannedCountInPM1;
		} else {
		    this.scannedAffiliateCountInHistory = 0;
            this.scannedAffiliateCountInPM1 = 0;
		}
		if (firstScan != null) {
		    this.firstScanDateTime = firstScan.firstScanDateTime;
		    this.daysSinceFirstScan = firstScan.daysSinceFirstScan;
		} else {
	        this.daysSinceFirstScan = 0;
		}
		// v20160830 add end
	}

	public final Double getPersonalInfoScore()
	{
		return this.personalInfoScore;
	}
	
	public final Integer getReuploadCount()
	{
		return this.reuploadCount;
	}
	
	public final Double getInvestigationScore()
	{
		return this.investigationScore;
	}
	
	public final Double getCreditReferenceScore()
	{
		return this.creditReferenceScore;
	}

	public final Double getFraudCheckScore()
	{
		return this.fraudCheckScore;
	}
	
	public final Double getApplicationScore()
	{
		return this.applicationScore;
	}
	
	public final Double getBehaviorScore()
	{
		return this.behaviorScore;
	}
	
	public final Integer getX_IdCardNumberDelayedCount()
	{
		return this.x_IdCardNumberDelayedCount;
	}
	
	public final Integer getX_IdCardNumberRepayingCount()
	{
		return this.x_IdCardNumberRepayingCount;
	}
	
	public final String getX_IdCardNumberLastRejectedDate()
	{
		return this.x_IdCardNumberLastRejectedDate;
	}

	public final Boolean getX_IsIdInSupremeCourtBlacklist()
	{
		return this.x_IsIdInSupremeCourtBlacklist;
	}
	
	public final Boolean getX_IsUserInfoInBlacklist()
	{
		return this.x_IsUserInfoInBlacklist;
	}
	
	public final LoanMoneyFailureReasonType getX_LoanMoneyFailureReason()
	{
		return this.x_LoanMoneyFailureReason == null ? null
				: LoanMoneyFailureReasonType.forValue(this.x_LoanMoneyFailureReason);
	}
	
	public final LoanMoneyResult getX_LoanMoneyResult()
	{
		return this.x_LoanMoneyResult == null ? null
				: LoanMoneyResult.forValue(this.x_LoanMoneyResult);
	}
	
	public final Boolean getIsCanceled()
	{
		return this.isCanceled;
	}
	
	public final Boolean getIsRecommended()
	{
		return this.isRecommended;
	}
	
	public final Integer getX_GroupInfoAppTotalCount()
	{
		return this.x_GroupInfoAppTotalCount;
	}
	
	public final Integer getX_GroupInfoUserTotalCount()
	{
		return this.x_GroupInfoUserTotalCount;
	}
	
	public final Boolean getIsReported()
	{
		return this.isReported;
	}
	
	public final Boolean getIsOutOfTouch()
	{
		return this.isOutOfTouch;
	}
	
	public final String getBlacklistReason()
	{
		return this.blacklistReason;
	}
	
	public final String getBlacklistSource()
	{
		return this.blacklistSource;
	}
	
	public final Double getISFPD30()
	{
		return isFPD30;
	}

	public final String getHeadImageUrl()
	{
		return this.headImageUrl;
	}

    public Integer getTotalCountInHistory() {
        return totalCountInHistory;
    }

    public Integer getApprovedCountInHistory() {
        return approvedCountInHistory;
    }

    public Integer getRejectedCountInHistory() {
        return rejectedCountInHistory;
    }

    public Integer getCanceledCountInHistory() {
        return canceledCountInHistory;
    }

    public Integer getScannedPOSCountInHistory() {
        return scannedPOSCountInHistory;
    }

    public Integer getScannedAffiliateCountInHistory() {
        return scannedAffiliateCountInHistory;
    }

    public Integer getTotalCountInPM1() {
        return totalCountInPM1;
    }

    public Integer getApprovedCountInPM1() {
        return approvedCountInPM1;
    }

    public Integer getRejectedCountInPM1() {
        return rejectedCountInPM1;
    }

    public Integer getCanceledCountInPM1() {
        return canceledCountInPM1;
    }

    public Integer getScannedPOSCountInPM1() {
        return scannedPOSCountInPM1;
    }

    public Integer getScannedAffiliateCountInPM1() {
        return scannedAffiliateCountInPM1;
    }

    public String getFirstScanDateTime() {
        return firstScanDateTime;
    }

    public Integer getDaysSinceFirstScan() {
        return daysSinceFirstScan;
    }
}
