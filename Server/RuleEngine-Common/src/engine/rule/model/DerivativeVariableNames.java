/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model;

/**
 * 〈衍生变量名称〉
 *
 * @author hwei
 * @version DerivativeVariableNames.java, V1.0 2017年5月18日 上午11:35:49
 */
public class DerivativeVariableNames {
    /** 是否为二次客户 */
    public static final String ISSECONDTIMEUSER = "X_Loan_IsSecondTimeUser";
    /** 上一次已完成借款最大逾期天数 */
    public static final String LASTAPPMAXDELAYEDDAYS = "X_Loan_LastAppMaxDelayedDays";
    /** 距离上一次已完成借款最后还款日期时间 */
    public static final String LASTAPPLICATIONINTERVAL = "X_Loan_LastApplicationInterval";
    /**上一次借款申请状态 */
    public static final String LASTLOANSTATUS = "X_Loan_LastLoanStatus";
    /** 上一次借款提前还款天数*/
    public static final String LASTLOANPREPAYMENTDAYS = "X_Loan_LastLoanPrepaymentdays";
    
    /****************同盾V3 begin*******************/
    /** 同盾V3-风险评估结果，取所有策略中分数最高的结果，结果有三种：Accept无风险，通过；Review低风险，审查；Reject高风险，拒绝*/
    public static final String FINALDECISION = "X_TDV3_FinalDecision";
    /** 同盾V3-身份证命中法院失信名单*/
    public static final String IDCARDISCOURTDISHONESTY = "X_TDV3_IdCardIsCourtDishonesty";
    /** 同盾V3-身份证命中法院执行名单*/
    public static final String IDCARDISCOURTEXECUTED = "X_TDV3_IdCardIsCourtExecuted";
    /** 同盾V3-身份证命中法院结案名单*/
    public static final String IDCARDISCOURTCLOSED = "X_TDV3_IdCardIsCourtClosed";
    /** 同盾V3-身份证命中犯罪通缉名单*/
    public static final String IDCARDISCRIMEWANTED = "X_TDV3_IdCardIsCrimeWanted";
    /** 同盾V3-身份证号对应人存在助学贷款逾期历史*/
    public static final String IDCARDISSTUDENTLOAN = "X_TDV3_IdCardIsStudentLoan";
    /** 同盾V3-身份证命中信贷逾期名单*/
    public static final String IDCARDISLOANOVERDUE = "X_TDV3_IdCardIsLoanOverdue";
    /** 同盾V3-身份证命中信贷逾期后还款名单*/
    public static final String IDCARDISLOANOVERDUEREPAY = "X_TDV3_IdCardIsLoanOverdueRepay";
    /** 同盾V3-手机号命中虚假号码库*/
    public static final String MOBILEISFAKE = "X_TDV3_MobileIsFake";
    /** 同盾V3-手机号命中通信小号库*/
    public static final String MOBILEISSMALL = "X_TDV3_MobileIsSmall";
    /** 同盾V3-手机号命中诈骗骚扰库*/
    public static final String MOBILEISBILK = "X_TDV3_MobileIsBilk";
    /** 同盾V3-手机号命中贷款黑中介库*/
    public static final String MOBILEISLOANBLACKMEDIUM = "X_TDV3_MobileIsLoanBlackMedium";
    /** 同盾V3-手机号命中信贷逾期名单*/
    public static final String MOBILEISLOANOVERDUE = "X_TDV3_MobileIsLoanOverdue";
    /** 同盾V3-手机号命中车辆租赁违约名单*/
    public static final String MOBILEISVEHICLEHIRE = "X_TDV3_MobileIsVehicleHire";
    /** 同盾V3-手机号命中欠款公司法人名单*/
    public static final String MOBILEISOVERDRAFTCORPORATE = "X_TDV3_MobileIsOverdraftCorporate";
    /** 同盾V3-手机号命中失联名单*/
    public static final String MOBILEISLOST = "X_TDV3_MobileIsLost";
    /** 同盾V3-手机号命中信贷逾期后还款名单*/
    public static final String MOBILEISLOANOVERDUEREPAY = "X_TDV3_MobileIsLoanOverdueRepay";
    /** 同盾V3-3个月内身份证关联多个申请信息*/
    public static final String IDCARDAPPLYS3MONTH = "X_TDV3_IdCardApplys3Month";
    /** 同盾V3-3个月内申请信息关联多个身份证*/
    public static final String APPLYIDCARDS3MONTH = "X_TDV3_ApplyIdCards3Month";
    /** 同盾V3-3个月内银行卡_姓名关联多个身份证*/
    public static final String BANKNAMEIDCARDS3MONTH = "X_TDV3_BankNameIdCards3Month";
    /** 同盾V3-3个月内申请人身份证作为联系人身份证出现的次数大于等于2*/
    public static final String IDCARDASCONTACTNUM2 = "X_TDV3_IdCardAsContactNum2";
    /** 同盾V3-3个月内申请人手机号作为联系人手机号出现的次数大于等于2*/
    public static final String MOBILEASCONTACTNUM2 = "X_TDV3_MobileAsContactNum2";
    /** 同盾V3-7天内申请人在多个平台申请借款*/
    public static final String APPLYLOANONPLATS7CNT = "X_TDV3_ApplyLoanOnPlats7Cnt";
    /** 同盾V3-30天内申请人在多个平台申请借款-个数*/
    public static final String APPLYLOANONPLATS30CNT = "X_TDV3_ApplyLoanOnPlats30Cnt";
    /** 同盾V3-3个月内申请人在多个平台申请借款-个数*/
    public static final String APPLYLOANONPLATS3MONTHCNT = "X_TDV3_ApplyLoanOnPlats3MonthCnt";
    /** 同盾V3-6个月内申请人在多个平台申请借款-个数*/
    public static final String APPLYLOANONPLATS6MONTHCNT = "X_TDV3_ApplyLoanOnPlats6MonthCnt";
    /** 同盾V3-12个月内申请人在多个平台申请借款-个数*/
    public static final String APPLYLOANONPLATS12MONTHCNT = "X_TDV3_ApplyLoanOnPlats12MonthCnt";
    /****************同盾V3 end*******************/
    
    /** QQ长度 **/
    public static final String QQ_LENGHT = "X_QQ_Length";
    /** 还款期数**/
    public static final String REPAYMENTS = "X_REPAYMENTS";
    /**	微信头像 **/
    public static final String WECHAT_HEAD = "X_WECHAT_HEAD";
    /** 微信名 **/
    public static final String NICK_NAME = "X_NICK_NAME";
    /** 申请次数 **/
    public static final String APPLY_NUMBER = "X_APPLY_NUMBER";
    /** 名义利率 **/
    public static final String INITPROD_RATE = "X_INITPROD_RATE";
    
    /** 通讯录是否在黑中介名单中 */
    public static final String ISINAGENCY_BLACKLISTOBJECTS = "X_IsInAgencyBlacklistObjects";
    
    /** 公司名称是否命中移动黑名单**/
    public static final String BLACK_COMPANY_NAME = "X_IsMobileBlackCompanyName";
    
  //泰融小额信贷--start
    /** 泰融小额信贷-具体评分**/
    public static final String SCORE="X_TAIRONG_Score";
    /** 泰融小额信贷-平均收入额度**/
    public static final String MONTHLYINCOME="X_TAIRONG_Monthlyincome";
    /** 泰融小额信贷-工作稳定性**/
    public static final String JOBSTABILITY="X_TAIRONG_Jobstability";
    /** 泰融小额信贷-银行卡套现综合评分**/
    public static final String CASHSCORE="X_TAIRONG_Cashscore";
    /** 泰融小额信贷-银行卡套现综合模型分类**/
    public static final String CASHMODELCLASSIFICATION="X_TAIRONG_Cashmodelclassification";
    /** 泰融小额信贷-消费趋势得分**/
    public static final String CONSUMERTRENDS="X_TAIRONG_Consumertrends";
    /** 泰融小额信贷-消费能力得分**/
    public static final String SPENDINGPOWER="X_TAIRONG_Spendingpower";
    /** 泰融小额信贷-客户综合价值得分**/
    public static final String CUSTOMERVALUE="X_TAIRONG_Customervalue";
    /** 泰融小额信贷-消费富余度综合评分**/
    public static final String CONSUMPTIONSURPLUS="X_TAIRONG_Consumptionsurplus";
    /** 泰融小额信贷-消费富余度综合分类**/
    public static final String CONSUMPTIONSURPLUSCLASS="X_TAIRONG_Consumptionsurplusclass";
    /** 泰融小额信贷-消费偏好综合得分**/
    public static final String CONSUMPTIONPREFERENCES="X_TAIRONG_Consumptionpreferences";
    /** 泰融小额信贷-消费交易金额稳定性**/
    public static final String CONSUMPTIONSTABILITY="X_TAIRONG_Consumptionstability";
    /** 泰融小额信贷-疑似养卡标识**/
    public static final String KEEPCARD="X_TAIRONG_Keepcard";
    /** 泰融小额信贷-手机通话稳定性**/
    public static final String CALLSTABILITY="X_TAIRONG_Callstability";
  //泰融小额信贷--end
    
 // 手机在网时长及三要素-start
    /** 手机在网时长返回结果**/
    public static final String MOBILENETWORK_RESULT="X_BUZTYPE_MOBILENETWORK_Result";
    /** 手机在网时长返回结果描述**/
    public static final String MOBILENETWORK_MESSAGE="X_BUZTYPE_MOBILENETWORK_Message";
    /** 手机三要素返回结果**/
    public static final String MOBILETHREE_RESULT="X_BUZTYPE_MOBILETHREE_Result";
    /** 手机三要素返回结果描述**/
    public static final String MOBILETHREE_MESSAGE="X_BUZTYPE_MOBILETHREE_Message";
 // 手机在网时长及三要素-start
    
    /**数维黑名单特殊名单等级**/
    public static final String SHUWEIRISK_SPECIALLEVEL="X_SHUWEIRISK_SPECIALLEVEL";
    
    /**白骑士最终决定**/
    public static final String BQS_FINALDECISION ="X_BQS_FINALDECISION";
    
    /**商品类别信息 */
   	public static final String PRODUCT_CATEGORY= "X_PRODUCT_CATEGORY";
   	
   	/**芝麻信用分 */
	public static final String ZMXY_SCORE= "X_ZMXY_SCORE";
    
    //ltv电销记录字段
    /**是否接通 **/
    public static final String LTVCALLSTATUS="X_LTV_CallStatus";
    /**是否空号错号 **/
    public static final String LTVCALLWRONG="X_LTV_CallWrong";
    /**是否拒绝 **/
    public static final String LTVCALLREJECT="X_LTV_CallReject";
    
    //富数黑名单
    /** 富数黑名单命中条件**/
    public static final String FUSHU_HIT_CONDITION="X_FUSHU_HIT_CONDITION";
    /** 富数是否在黑名单**/
    public static final String FUSHU_INBLACKLIST="X_FUSHU_INBLACKLIST";
    /** 富数是否在法院黑名单**/
    public static final String FUSHU_INBLACKLIST_COURT="X_FUSHU_INBLACKLIST_COURT";
    /** 富数是否在金融机构黑名单**/
    public static final String FUSHU_INBLACKLIST_FINANCE="X_FUSHU_INBLACKLIST_FINANCE";
    /** 富数黑名单来源**/
    public static final String FUSHU_TYPE="X_FUSHU_TYPE";
    /** 富数法院黑名单记录详情**/
    public static final String FUSHU_DUTY="X_FUSHU_DUTY";
    /** 富数金融机构黑名单**/
    public static final String FUSHU_REGISTER_DATE="X_FUSHU_REGISTER_DATE";
    /** 富数逾期日期**/
    public static final String FUSHU_DATE="X_FUSHU_DATE";
    /** 富数黑名单等级**/
    public static final String FUSHU_LEVEL="X_FUSHU_LEVEL";
    /** 富数黑名单详情**/
    public static final String FUSHU_DETAIL="X_FUSHU_DETAIL";
    /** 富数更新时间**/
    public static final String FUSHU_UPDATETIME="X_FUSHU_UPDATETIME";
    
    //考拉黑名单
    /** 考拉借款日期**/
    public static final String KAOLA_BORROW_DATE="X_KAOLA_BORROW_DATE";
    /** 考拉借款金额**/
    public static final String KAOLA_BORROW_AMOUNT="X_KAOLA_BORROW_AMOUNT";
    /** 考拉借款期限**/
    public static final String KAOLA_BORROW_PERIOD="X_KAOLA_BORROW_PERIOD";
    /** 考拉逾期日期**/
    public static final String KAOLA_OVERDUE_DATE="X_KAOLA_OVERDUE_DATE";
    /** 考拉逾期级别 每 30 天 1M，分为(M1,M2,M3,M4,M5,M6,M6+)**/
    public static final String KAOLA_OVERDUE_LEVER="X_KAOLA_OVERDUE_LEVER";
    /** 考拉逾期金额**/
    public static final String KAOLA_OVERDUE_AMOUNT="X_KAOLA_OVERDUE_AMOUNT";

  //考拉个人身份回查
    /** 考拉个人身份回查身份证姓名**/
    public static final String KAOLA_IDENTIRY_IDCARD_NAME="X_KAOLA_IDENTIRY_IDCARD_NAME";
    /** 考拉个人身份回查身份证号码**/
    public static final String KAOLA_IDENTIRY_IDCARD_CODE="X_KAOLA_IDENTIRY_IDCARD_CODE";
    /** 考拉个人身份回查时间范围**/
    public static final String KAOLA_IDENTIRY_TIME_INTERVAL="X_KAOLA_IDENTIRY_TIME_INTERVAL";
    /** 考拉个人身份回查被查询次数**/
    public static final String KAOLA_IDENTIRY_TOTAL_TRANSNUM="X_KAOLA_IDENTIRY_TOTAL_TRANSNUM";
    /** 考拉个人身份回查最后一次查询时间**/
    public static final String KAOLA_IDENTIRY_LAST_TRANSTIME="X_KAOLA_IDENTIRY_LAST_TRANSTIME";
    /** 考拉个人身份回查最后一次查询企业所属行业**/
    public static final String KAOLA_IDENTIRY_LAST_INDUSTRY="X_KAOLA_IDENTIRY_LAST_INDUSTRY";
    /** 考拉个人身份回查最后一次查询产品类别**/
    public static final String KAOLA_IDENTIRY_LAST_PRDGRP_NAME="X_KAOLA_IDENTIRY_LAST_PRDGRP_NAME";
    /** 考拉个人身份回查被查询类别次数**/
    public static final String KAOLA_IDENTIRY_TOTAL_PRDGRP_NUM="X_KAOLA_IDENTIRY_TOTAL_PRDGRP_NUM";
    
    
    /** ltv金额**/
    public static final String LTV_PRINCIPAL="X_LTV_PRINCIPAL";
    /** ltv收入**/
    public static final String LTV_INCOME="X_LTV_INCOME";
    /** ltv期数**/
    public static final String LTV_REPAYMENTS="X_LTV_REPAYMENTS";
    /** ltv性别**/
    public static final String LTV_GENDER="X_LTV_GENDER";
    /** ltv年龄**/
    public static final String LTV_AGE="X_LTV_AGE";
    /** ltv运营商**/
    public static final String LTV_PROVIDER="X_LTV_PROVIDER";
    
}
