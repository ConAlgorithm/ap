package instinct.model;

import util.InstinctizeUtil;

public class CBB 
{
    /** Text    1   Must be “K” */
	public final String category = "K";	//	Text	1	Must be “K”. CBB
	/** Text   60  是否中内部黑名单 (UserInfoInBlackList) */
	public String id_Number1;		//	Text	60	是否中内部黑名单 (UserInfoInBlackList)
	/** Text   60  是否取消(Iscanceled) */
	public String id_Number2;		//	Text	60	是否取消(Iscanceled)
	/** Text   60  是否推荐(Isrecommend) */
	public String id_Number3;		//	Text	60	是否推荐(Isrecommend)
	/** Text   300 是否拉警报(Isreported) */
	public String surname;			//	Text	300	是否拉警报(Isreported)
	/** Text   300 是否失联(IsOutOfTouch) */
	public String first_Name;		//	300	是否失联(IsOutOfTouch)
	/** Text   300 是否首次逾期30天(ISFPD30) */
	public String middle_Name;		//	200	是否首次逾期30天ISFPD30
	/** Text   70 用户总申请次数(TotalCountInHistory) */
    public String home_Address1;
    /** Text   70 用户总申请批准次数(ApprovedCountInHistory) */
    public String home_Address2;
    /** Text   70 用户总申请拒绝次数(RejectedCountInHistory) */
    public String home_Address3;
    /** Text   70 用户总申请取消次数(CanceledCountInHistory) */
    public String home_Address4;
    /** Text   70 用户总申请扫描门店次数(ScannedPOSCountInHistory) */
    public String home_Address5;
    /** Text   70 用户总申请扫描S码次数(ScannedAffiliateCountInHistory) */
    public String home_Address6;
    /** Text   32 用户1个月内总申请次数(TotalCountInPM1) */
    public String home_Phone_Number;
    /** Text   32 用户1个月内总申请批准次数(ApprovedCountInPM1) */
    public String office_Phone_Number;
    /** Text   32 用户1个月内总申请拒绝次数(RejectedCountInPM1) */
    public String mobile_Phone_Number;
    /** Text   32 用户1个月内总申请取消次数(CanceledCountInPM1) */
    public String other_Phone_Number;
    /** Text   40 特征评分(PersonalInfoScore) */
	public String user_Field2;		//	Text	40	特征评分(PersonalInfoScore)
	/** Text   60  重传次数(ReuploadCount) */
	public String user_Field3;		//	Text	60	重传次数(ReuploadCount)
	/** Text   100 调查评分(InvestigationScore) */
	public String user_Field4;		//	Text	100	调查评分(InvestigationScore)
	/** Text   50  征信评分(CreditReferenceScore) */
	public String user_Field5;		//	Text	50	征信评分(CreditReferenceScore)
	/** Text   50  欺诈信息评分(FraudCheckScore) */
	public String user_Field6;		//	Text	50	欺诈信息评分(FraudCheckScore)
	/** Text   50  业务申请评分(ApplicationScore) */
	public String user_Field7;		//	Text	50	业务申请评分(ApplicationScore)
	/** Text   50  行为评分(BehaviorScore) */
	public String user_Field8;		//	Text	50	行为评分(BehaviorScore)
	/** Text   60  客户当前所有申请中处于‘已逾期’状态的数目(DelayedCount) */
	public String user_Field9;		//	Text	60	客户当前所有申请中处于‘已逾期’状态的数目(DelayedCount)
	/** Text   60  客户当前所有申请中处于‘还款中’状态的数目(RepayingCount) */
	public String user_Field10;		//	Text	60	客户当前所有申请中处于‘还款中’状态的数目(RepayingCount)
	/** Text   60  身份证号最近被拒绝时间(IdCardNumberLastRejectedDate) */
	public String user_Field11;		//	Text	60	身份证号最近被拒绝时间(IdCardNumberLastRejectedDate)
	/** Text   80  用户身份证在最高法院黑名单中(IdInSupremeCourtBlackList) */
	public String user_Field12;		//	Text	80	用户身份证在最高法院黑名单中(IdInSupremeCourtBlackList)
	/** Text   80  放款转账失败原因(loanMoneyFailureReason) */
	public String user_Field13;		//	Text	80	放款转账失败原因(loanMoneyFailureReason)
	/** Text   80  放款转账情况(loanMoneyResult) */
	public String user_Field14;		//	Text	80	放款转账情况(loanMoneyResult)
	/** Text   80  加黑原因(BlacklistReason) */
	public String user_Field15;		//	Text	80	加黑原因(BlacklistReason)
	/** Text   80  黑名单来源(BlackliStsource) */
	public String user_Field16;		//	Text	80	黑名单来源(BlackliStsource)
	/** Numeric    8   团内申请总数(默认值-1)(AppTotalCount) */
	public String user_Field17;		//	Numeric	8	团内申请总数(默认值-1)(AppTotalCount)
	/** Numeric    8   团内用户总数(默认值-1)(UserTotalCount) */
	public String user_Field18;		//	Numeric	8	团内用户总数(默认值-1)(UserTotalCount)
    /** Numeric    8   用户1个月扫描门店总数(ScannedPOSCountInPM1) */
    public String user_Field25;
    /** Numeric    8   用户1个月扫描S码总数(ScannedAffiliateCountInPM1) */
    public String user_Field26;
    /** Numeric    8   首次扫描二维码时间距今天数(DaysSinceFirstScan) */
    public String user_Field28;
	/** Text   max 微信头像(HeadImage) */
	public String user_Field29;		//	Text	max	微信头像(HeadImage)
	/** Text   max 首次扫描二维码时间(FirstScanDateTime) */
    public String user_Field32;
    
	public CBB(omni.model.CBB posval)
	{
		this.id_Number1 = InstinctizeUtil.string(posval.getX_IsUserInfoInBlacklist());
		this.id_Number2 = InstinctizeUtil.string(posval.getIsCanceled());
		this.id_Number3 = InstinctizeUtil.string(posval.getIsRecommended());
		this.surname = InstinctizeUtil.string(posval.getIsReported());
		this.first_Name = InstinctizeUtil.string(posval.getIsOutOfTouch());
		this.middle_Name = InstinctizeUtil.string(posval.getISFPD30());
		// v20160830 add start
		this.home_Address1 = InstinctizeUtil.string(posval.getTotalCountInHistory());
	    this.home_Address2 = InstinctizeUtil.string(posval.getApprovedCountInHistory());
	    this.home_Address3 = InstinctizeUtil.string(posval.getRejectedCountInHistory());
	    this.home_Address4 = InstinctizeUtil.string(posval.getCanceledCountInHistory());
	    this.home_Address5 = InstinctizeUtil.string(posval.getScannedPOSCountInHistory());
	    this.home_Address6 = InstinctizeUtil.string(posval.getScannedAffiliateCountInHistory());
	    this.home_Phone_Number = InstinctizeUtil.string(posval.getTotalCountInPM1());
        this.office_Phone_Number = InstinctizeUtil.string(posval.getApprovedCountInPM1());
        this.mobile_Phone_Number = InstinctizeUtil.string(posval.getRejectedCountInPM1());
        this.other_Phone_Number = InstinctizeUtil.string(posval.getCanceledCountInPM1());
		// v20160830 add end
		this.user_Field2 = InstinctizeUtil.string(posval.getPersonalInfoScore());
		this.user_Field3 = InstinctizeUtil.string(posval.getReuploadCount());
		this.user_Field4 = InstinctizeUtil.string(posval.getInvestigationScore());
		this.user_Field5 = InstinctizeUtil.string(posval.getCreditReferenceScore());	
		this.user_Field6 = InstinctizeUtil.string(posval.getFraudCheckScore());
		this.user_Field7 = InstinctizeUtil.string(posval.getApplicationScore());
		this.user_Field8 = InstinctizeUtil.string(posval.getBehaviorScore());
		this.user_Field9 = InstinctizeUtil.string(posval.getX_IdCardNumberDelayedCount());
		this.user_Field10 = InstinctizeUtil.string(posval.getX_IdCardNumberRepayingCount());
		this.user_Field11 = InstinctizeUtil.string(posval.getX_IdCardNumberLastRejectedDate());
		this.user_Field12 = InstinctizeUtil.string(posval.getX_IsIdInSupremeCourtBlacklist());	
		this.user_Field13 = InstinctizeUtil.string(posval.getX_LoanMoneyFailureReason());
		this.user_Field14 = InstinctizeUtil.string(posval.getX_LoanMoneyResult());		
		this.user_Field15 = InstinctizeUtil.string(posval.getBlacklistReason());
		this.user_Field16 = InstinctizeUtil.string(posval.getBlacklistSource());
		this.user_Field17 = InstinctizeUtil.string(posval.getX_GroupInfoAppTotalCount());
		this.user_Field18 = InstinctizeUtil.string(posval.getX_GroupInfoUserTotalCount());
		// v20160830 add start
		this.user_Field25 = InstinctizeUtil.string(posval.getScannedPOSCountInPM1());
	    this.user_Field26 = InstinctizeUtil.string(posval.getScannedAffiliateCountInPM1());
	    this.user_Field28 = InstinctizeUtil.string(posval.getDaysSinceFirstScan());
		// v20160830 add end
		this.user_Field29 = InstinctizeUtil.string(posval.getHeadImageUrl());
		// v20160830 add start
	    this.user_Field32 = InstinctizeUtil.string(posval.getFirstScanDateTime());
	    // v20160830 add end
	}

	public CBB() 
	{
		// TODO Auto-generated constructor stub
	}
}
