package instinct.model;

import util.InstinctizeUtil;
import util.UuidUtil;

import java.util.Date;

public class Application 
{
    /** OMN */
	public final String organisation = "OMN";		//	Set to “OMN”
	/** CN */
	public final String country_Code = "CN";		//	CN
	/** Null */
	public final String group_Member_Code = "Null"; 	// 	Null
	/** Text   50  AppId */
	public String appId;	//	Text	50	AppId	TODO: generate for Instinct database
	/** Text   8   AppType申请类型Set to “LOAN” */
	public String application_Type;	//	Text	8	AppType申请类型Set to “LOAN”
	/** Date   8   ApplicationTimeStamp */
	public String application_Date;	//	Date	8	ApplicationTimeStamp
	/** Date   8   Expiry_Date */
	public String expiry_Date;			//	Date	8	Expiry_Date
	/** Numeric   8   额度(CreditLine) */
	public String amount_Limit;		//	Numeric	8	额度（CreditLine）
//	public String branch;				//	Text	16	进件门店（POSName）
	/** Text   20  拒绝类型(RejectedType) */
	public final String decision = "";			//	Text	20	拒绝类型（RejectedType）
	/** Text   200 拒绝原因(RejectedReason) */
	public final String decision_Reason = "";		//	Text	200	拒绝原因（RejectedReason）
	/** Date   8   决定时间 */
	public String decision_Date;		//	Date	8	决定时间
	/** Text   100 借款用途 */
	public String user_Field1;			//	Text	100	借款用途
	/** Text   100 产品类型 */
	public String user_Field2;			//	Text	100	产品类型
	/** Text   100 贷款产品 */
	public String user_Field3;			//	Text	100	贷款产品
	/** Text   100 申请时手机型号 */
	public String user_Field4;			//	Text	100	申请时手机型号
	/** Text   100 客户输入时间(UserInputTime) */
	public String user_Field5;			//	Text	100	客户输入时间（UserInputTime）
	/** Text   100 交易监控在线数量(transactionMonitorJobCount)(默认值-1) */
	public String user_Field6;			//	Text	100	交易监控在线数量（transactionMonitorJobCount）(默认值-1)
	/** Text   100 总时长（秒）(TotallySpentSeconds) */
	public String user_Field7;			//	Text	100	总时长（秒）(TotallySpentSeconds)
	/** Text   100 最终状态(Status) */
	public String user_Field8;			//	Text	100	最终状态（Status）
	/** Text   100 Instinct调用次数 */
	public String user_Field9;			//	Text	100 Instinct调用次数
	/** Text   100 application_Number */
	public String user_Field10;			//	Text	100	application_Number
	/** Text   100 拒绝类型(RejectedType) */
	public String user_Field11;			//	Text	100	拒绝类型（RejectedType）
	/** Text   100 拒绝原因(RejectedReason) */
	public String user_Field12;			//	Text	100	拒绝原因（RejectedReason）
	/** Text   100 PDL进件渠(PDLInstalmentChannel暂未收集，送空) */
	public String user_Field13;			//  Text 	100	PDL进件渠(PDLInstalmentChannel暂未收集，送空)
	/** Text   100 PDL行业分类(PDLIndustry Classification暂未收集，送空) */
	public String user_Field14;			//	Text	100	PDL行业分类(PDLIndustry Classification暂未收集，送空)
	/** Text   100 贷款期数(Repayments) */
    public String user_Field15;         //  Text    100 贷款期数(Repayments)
    /** Text   100 产品价格(ItemPrice) */
    public String user_Field16;         //  Text    100 产品价格(ItemPrice)
    /** Text   100 首付(Downpayment) */
    public String user_Field17;         //  Text    100 首付(Downpayment)
    /** Text   100 金融产品(ProductObjects.Name) */
    public String user_Field18;         //  Text    100 金融产品(ProductObjects.Name)
	/** Text   100 是否为二次客户(IsSecondTimeUser) */
	public String user_Field19;         //  Text    100 是否为二次客户(IsSecondTimeUser)
	/** Text   100 上一次已完成借款最大逾期天数(LastAppMaxDelayedDays) */
	public String user_Field20;         //  Text    100 上一次已完成借款最大逾期天数(LastAppMaxDelayedDays)
	/** Text   100 距离上一次已完成借款最后还款日期时间(LastApplicationInterval) */
	public String user_Field21;         //  Text    100 距离上一次已完成借款最后还款日期时间(LastApplicationInterval)
	/** Text   100 上一次借款申请状态(LastLoanStatus) */
	public String user_Field22;         //  Text    100 上一次借款申请状态(LastLoanStatus)
	/** Text   100 上一次借款提前还款天数(LastLoanPrepaymentdays) */
	public String user_Field23;         //  Text    100 一次借款提前还款天数(LastLoanPrepaymentdays)

	
	public Application(omni.model.Application posapp)
	{
//		appId = ShortUUID.uuid(posapp.getAppId());
		appId = UuidUtil.compress(posapp.getAppId());
		application_Type = InstinctizeUtil.string(posapp.getAppType());
		application_Date  = InstinctizeUtil.date(posapp.getInstallmentStartedOn());
		expiry_Date  = InstinctizeUtil.date(posapp.getExpireDate());
		amount_Limit  = InstinctizeUtil.string(posapp.getPrincipal());
//		branch  = InstinctizeUtil.string(posapp.getPOSName());
//		decision  = InstinctizeUtil.string(posapp.getRejectedType());
//		decision_Reason  = InstinctizeUtil.string(posapp.getRejectedReason());
		decision_Date  = InstinctizeUtil.date(posapp.getApprovedOn());
		user_Field1  = InstinctizeUtil.string(posapp.getPurpose());
		user_Field2  = InstinctizeUtil.string(posapp.getProductType());
		user_Field3  = InstinctizeUtil.string(posapp.getProductName());
		user_Field4  = InstinctizeUtil.string(posapp.getX_UbtPhoneType_Latest());
		user_Field5  = InstinctizeUtil.string(posapp.getUserInputTime());
		user_Field6  = InstinctizeUtil.string(posapp.getX_TransactionMonitorJobCount());
		user_Field7  = InstinctizeUtil.string(posapp.getX_UbtTotallySpentSeconds_Summation());
		user_Field8  = InstinctizeUtil.string(posapp.getStatus());
		user_Field9  = InstinctizeUtil.string(posapp.getInstinctCall());
		user_Field10   = InstinctizeUtil.string(posapp.getAppId());
		user_Field11  = InstinctizeUtil.string(posapp.getRejectedType());
		user_Field12  = InstinctizeUtil.string(posapp.getRejectedReason());
		user_Field13  = InstinctizeUtil.string(posapp.getPDLInstalmentChannel());
		user_Field14  = InstinctizeUtil.string(posapp.getPDLIndustryClassification());
		// v20160802 add start
		user_Field15  = InstinctizeUtil.string(posapp.getRepayments());
		user_Field16 = InstinctizeUtil.string(posapp.getItemPrice());
	    user_Field17 = InstinctizeUtil.string(posapp.getDownpayment());
	    user_Field18 = InstinctizeUtil.string(posapp.getFinancialProductName());
		user_Field19 = InstinctizeUtil.string(posapp.getIsSecondTimeUser());
		user_Field20 = InstinctizeUtil.string(posapp.getLastAppMaxDelayedDays());
		user_Field21 = InstinctizeUtil.string(posapp.getLastApplicationInterval());
		user_Field22 = InstinctizeUtil.string(posapp.getLastLoanStatus());
		user_Field23 = InstinctizeUtil.string(posapp.getLastLoanPrepaymentdays());
		// v20160802 add end
	}

	/**
	 * Generate appId for black list.
	 */
	public Application() 
	{
		this.appId = UuidUtil.compressedUuid(); 
		this.application_Date = InstinctizeUtil.date(new Date());
		this.application_Type = "PSL";
	}
}
