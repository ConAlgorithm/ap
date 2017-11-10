package instinct.model;

import util.InstinctizeUtil;

public class User2 
{
    /** Text    1   Must be “O” */
	public final String category = "O";	//	Text	1	Must be “O”.
	/** Text   100 公司电话审核申请人核对(checkCompanyApplicantCheckResult) */
	public String user_Field1;		//	Text   100 公司电话审核申请人核对(checkCompanyApplicantCheckResult)
	/** Text   100 公司电话审核与联系人关系(checkCompanyPhoneRelationshipResult) */
	public String user_Field2;		//	Text	100	公司电话审核与联系人关系(checkCompanyPhoneRelationshipResult)
	/** Text   100 审核法院被执行记录(checkCourtExecuted) */
	public String user_Field3;		//	Text	100	审核法院被执行记录(checkCourtExecuted)
	/** Text   100 头像照审核头像现场照片表情(checkImageHeadPhotoFaceExpression) */
	public String user_Field4;		//	Text	100	头像照审核头像现场照片表情(checkImageHeadPhotoFaceExpression)
	/** Text   100 电子借条审核客户是否有笑容(checkIOUIsSmile) */
	public String user_Field5;		//	Text	100	电子借条审核客户是否有笑容(checkIOUIsSmile)
	/** Text   100 电子借条审核检查客户电子借条的手机外壳颜色(checkIOUMobileColor) */
	public String user_Field6;		//	Text	100	电子借条审核检查客户电子借条的手机外壳颜色(checkIOUMobileColor)
	/** Text   100 电子借条审核检查客户是否左手无名指戴戒指(checkIOURingFinger) */
	public String user_Field7;		//	Text	100	电子借条审核检查客户是否左手无名指戴戒指(checkIOURingFinger)
	/** Text   100 商户电话审核客户是否在现场(checkMerchantCustomerOnSpot) */
	public String user_Field8;		//	Text	100	商户电话审核客户是否在现场(checkMerchantCustomerOnSpot)
	/** Text   100 商户电话审核电话是否正常接听(checkMerchantPhoneCallResult) */
	public String user_Field9;		//	Text	100	商户电话审核电话是否正常接听(checkMerchantPhoneCallResult)
	/** Text   100 客户电话审核身份证住址确认(checkUserIDCardAddress) */
	public String user_Field10;	//	Text	100	客户电话审核身份证住址确认(checkUserIDCardAddress)
	/** Text   100 客户电话审核身份确认(checkUserIdentification) */
	public String user_Field11;	//	Text	100	客户电话审核身份确认(checkUserIdentification)
	/** Text   100 客户电话审核客户是否取消申请(checkUserIsCancelApplication) */
	public String user_Field12;	//	Text	100	客户电话审核客户是否取消申请(checkUserIsCancelApplication)
	/** Text   100 客户电话审核聚信立信息收集(checkUserJXLInforCrawlStatus) */
	public String user_Field13;	//	Text	100	客户电话审核聚信立信息收集(checkUserJXLInforCrawlStatus)
	/** Text   100 客户电话审核通话人接听情况(checkUserPhoneAnswererResult) */
	public String user_Field14;	//	Text	100	客户电话审核通话人接听情况(checkUserPhoneAnswererResult)
	/** Text   100 客户电话审核电话是否正常接听(checkUserPhoneCallResult) */
	public String user_Field15;	//	Text	100	客户电话审核电话是否正常接听(checkUserPhoneCallResult)
	/** Text   100 客户电话审核申请人声音和背景音与常识推断(checkUserSound) */
	public String user_Field16;	//	Text	100	客户电话审核申请人声音和背景音与常识推断(checkUserSound)
	/** Text   100 客户电话审核生肖确认(checkUserSymbolicAnimal) */
	public String user_Field17;	//	Text	100	客户电话审核生肖确认(checkUserSymbolicAnimal)
	/** Text   100 住址与单位地址比较(CompletenessCheckResidenceAndCompanyAddressComparisonResult) */
	public String user_Field18;	//	Text	100	住址与单位地址比较(CompletenessCheckResidenceAndCompanyAddressComparisonResult)
	/** Text   100 App交易监控_拒绝原因(transactionMonitorRejectReason) */
	public String user_Field19;	//	Text	100	App交易监控_拒绝原因(transactionMonitorRejectReason)
	/** Text   100 App交易监控_拒绝原因(transactionMonitorRejectReason) */
	public String user_Field20;	//	Text	100	App交易监控_判断结果(transactionMonitorResult)
	/** Text   100 App交易监控_判断结果(transactionMonitorResult) */
	public String user_Field21;	//	Text	100	JXL申请是否提交成功(x_JXL_GetReportStatusResult)
	/** Text   100 JXL申请是否提交成功(x_JXL_GetReportStatusResult) */
	public String user_Field22;	//	Text	100	JXL是否有报告(x_JXL_ReportData_Success)
	/** Text   100 JXL是否有报告(x_JXL_ReportData_Success) */
	public String user_Field23;	//	Text	100	银联智惠中银行卡和预留电话是否匹配(默认值数据为空)(bankcardMobileMatch)
	/** Text   100 银联智惠中银行卡和预留电话是否匹配(默认值数据为空)(bankcardMobileMatch) */
	public String user_Field24;	//	Text	100	银联智慧最大消费金额城市是否本地(办理分期的城市)(isMaxConsumptionAmountNative)
	/** Text   100 银联智慧最大消费金额城市是否本地(办理分期的城市)(isMaxConsumptionAmountNative) */
	public String user_Field25;	//	Text	100	银联智慧最大消费次数城市是否本地(办理分期的城市(isMaxConsumptionCountNative)
	/** Text   100 银联智慧最大消费次数城市是否本地(办理分期的城市(isMaxConsumptionCountNative) */
	public String user_Field26;	//	Text	100	银联智慧消费金额总数(默认值-1)(totalConsumeAmount)
	/** Text   100 银联智慧消费金额总数(默认值-1)(totalConsumeAmount) */
	public String user_Field27;	//	Text	100	银联智慧消费次数总数(默认-1)(totalConsumeCount)
	/** Text   100 银联智慧消费次数总数(默认-1)(totalConsumeCount) */
	public String user_Field28;	//	Text	100	银联智慧消费城市总数(默认值-1)(totalConsumeRegionCount)
	/** Text   100 同盾_借款手机号命中全局失信证据库(tdRule33638Hit) */
	public String user_Field29;	//	Text	100	同盾_借款手机号命中全局失信证据库(tdRule33638Hit)
	/** Text   100 同盾_借款身份证命中全局失信证据库(tdRule33640Hit) */
	public String user_Field30;	//	Text	100	同盾_借款身份证命中全局失信证据库(tdRule33640Hit)
	/** Text   200 同盾_借款人身份证命中法院执行证据库(tdRule33642Hit) */
	public String user_Field31;	//	Text	200	同盾_借款人身份证命中法院执行证据库(tdRule33642Hit)
	/** Text   200    同盾_借款人手机号命中虚假号码证据库(tdRule33652Hit) */
	public String user_Field32;	//	Text	200	同盾_借款人手机号命中虚假号码证据库(tdRule33652Hit)
	/** Text   200    同盾_借款人手机号命中通信小号证据库(tdRule33654Hit) */
	public String user_Field33;	//	Text	200	同盾_借款人手机号命中通信小号证据库(tdRule33654Hit)
	/** Text   200 骏聿身份证与现场照相似度（jyHeadPhotoCheckSimilarity）(默认值101,表示空) */
	public String user_Field34;	//	Text	200	骏聿身份证与现场照相似度（jyHeadPhotoCheckSimilarity）(默认值101,表示空)
	/** Text   200    骏聿身份证照和公安部照对比相似度(jyIDCardPhotoCheckSimilarity)(默认值101,表示空) */
	public String user_Field35;	//	Text	200	骏聿身份证照和公安部照对比相似度(jyIDCardPhotoCheckSimilarity)(默认值101,表示空)
	/** Numeric    8   小视科技身份证与现场照相似度(xsHeadPhotoCheckSimilarity) */
	public String user_Field36;	//	Numeric	8	小视科技身份证与现场照相似度（xsHeadPhotoCheckSimilarity）
	/** Numeric    8   小视科技身份证与公安部照相似度(xsIDCardPhotoCheckSimilarity) */
	public String user_Field37;	//	Numeric	8	小视科技身份证与公安部照相似度（xsIDCardPhotoCheckSimilarity）
//	public double User_Field38;	//	Numeric	8	
//	public double User_Field39;	//	Numeric	8	
//	public double User_Field40;	//	Numeric	8	
	/** Numeric    8   同盾3个月内手机在本平台外的借款申请次数  */
	public String user_Field41;	//	Numeric	8	
	/** Numeric    8   同盾3个月内身份证在本平台外的借款申请次数 */
	public String user_Field42;	//	Numeric	8	
	/** Numeric    8   同盾3个月内第一联系人身份证在本平台外的借款申请次数 */
	public String user_Field43;	//	Numeric	8	
	/** Numeric    8   同盾3个月内第二联系人身份证在本平台外的借款申请次数 */
	public String user_Field44;	//	Numeric	8	
//	public double User_Field45;	//	Numeric	8	
//	public String User_Field46;	//	Date	8	
//	public String User_Field47;	//	Date	8	
//	public String User_Field48;	//	Date	8	
//	public String User_Field49;	//	Date	8	
//	public String User_Field50;	//	Date	8	
	
	public User2(omni.model.User2 posusr)
	{
		this.user_Field1 = InstinctizeUtil.string(posusr.getX_CheckCompanyApplicantCheckResult());
		this.user_Field2 = InstinctizeUtil.string(posusr.getX_CheckCompanyPhoneRelationshipResult());
		this.user_Field3 = InstinctizeUtil.string(posusr.getX_CheckCourtExecuted());
		this.user_Field4 = InstinctizeUtil.string(posusr.getX_CheckImageHeadPhotoFaceExpression());
		this.user_Field5 = InstinctizeUtil.string(posusr.getX_CheckIOUIsSmile());
		this.user_Field6 = InstinctizeUtil.string(posusr.getX_CheckIOUMobileColor());
		this.user_Field7 = InstinctizeUtil.string(posusr.getX_CheckIOURingFinger());
		this.user_Field8 = InstinctizeUtil.string(posusr.getX_CheckMerchantCustomerOnSpot());
		this.user_Field9 = InstinctizeUtil.string(posusr.getX_CheckMerchantPhoneCallResult());
		this.user_Field10 = InstinctizeUtil.string(posusr.getX_CheckUserIDCardAddress());
		this.user_Field11 = InstinctizeUtil.string(posusr.getX_CheckUserIdentification());
		this.user_Field12 = InstinctizeUtil.string(posusr.getX_CheckUserIsCancelApplication());
		this.user_Field13 = InstinctizeUtil.string(posusr.getX_CheckUserJXLInforCrawlStatus());
		this.user_Field14 = InstinctizeUtil.string(posusr.getX_CheckUserPhoneAnswererResult());
		String userField15tmp1 = InstinctizeUtil.string(posusr.getX_CheckUserPhoneCallResult());
		String userField15tmp2 = InstinctizeUtil.string(posusr.getX_CheckUserPhoneCallResultForPDL());
		this.user_Field15 = "".equalsIgnoreCase(userField15tmp1) ? userField15tmp2 : userField15tmp1;
		this.user_Field16 = InstinctizeUtil.string(posusr.getX_CheckUserSound());
		this.user_Field17 = InstinctizeUtil.string(posusr.getX_CheckUserSymbolicAnimal());
		this.user_Field18 = InstinctizeUtil.string(posusr.getX_CompletenessCheckResidenceAndCompanyAddressComparisonResult()); 
		this.user_Field19 = InstinctizeUtil.string(posusr.getX_TransactionMonitorRejectReason());
		this.user_Field20 = InstinctizeUtil.string(posusr.getX_TransactionMonitorResult());
		this.user_Field21 = InstinctizeUtil.string(posusr.getx_JXL_GetReportStatusResult());
		this.user_Field22 = InstinctizeUtil.string(posusr.getX_JXL_ReportData_Success());
		this.user_Field23 = InstinctizeUtil.string(posusr.getX_YLZH_BankCardMobileMatch());
		this.user_Field24 = InstinctizeUtil.string(posusr.getX_YLZH_IsMaxConsumptionAmountNative());
		this.user_Field25 = InstinctizeUtil.string(posusr.getX_YLZH_IsMaxConsumptionCountNative());
		this.user_Field26 = InstinctizeUtil.string(posusr.getX_YLZH_ConsumptionTotalAmount());
		this.user_Field27 = InstinctizeUtil.string(posusr.getX_YLZH_ConsumptionTotalCount());
		this.user_Field28 = InstinctizeUtil.string(posusr.getX_YLZH_ConsumptionTotalRegionCount());
		this.user_Field29 = InstinctizeUtil.string(posusr.getX_TD_Rule_33638());
		this.user_Field30 = InstinctizeUtil.string(posusr.getX_TD_Rule_33640());
		this.user_Field31 = InstinctizeUtil.string(posusr.getX_TD_Rule_33642());
		this.user_Field32 = InstinctizeUtil.string(posusr.getX_TD_Rule_33652());
		this.user_Field33 = InstinctizeUtil.string(posusr.getX_TD_Rule_33654());
		this.user_Field34 = InstinctizeUtil.string(posusr.getJyHeadPhotoCheckSimilarity());
		this.user_Field35 = InstinctizeUtil.string(posusr.getJyIDCardPhotoCheckSimilarity());
		this.user_Field36 = InstinctizeUtil.string(posusr.getXsHeadPhotoCheckSimilarity());
		this.user_Field37 = InstinctizeUtil.string(posusr.getXsIDCardPhotoCheckSimilarity());
		// v20160809 add start
		this.user_Field41 = InstinctizeUtil.string(posusr.getX_TD_Rule_33674_OuterPlatformLoanAmount());
		this.user_Field42 = InstinctizeUtil.string(posusr.getX_TD_Rule_33676_OuterPlatformLoanAmount());
		this.user_Field43 = InstinctizeUtil.string(posusr.getX_TD_FirstContact_Rule33674Hit_PlatformAmount());
		this.user_Field44 = InstinctizeUtil.string(posusr.getX_TD_SecondContact_Rule33674Hit_PlatformAmount());
        // v20160809 add end
	}

	public User2() 
	{
		// TODO Auto-generated constructor stub
	}
}
