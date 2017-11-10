package engine.rule.model.in.app;

import java.math.BigDecimal;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import catfish.base.business.common.JOLengthOfNetwork;
import catfish.base.business.common.JORealAuthenticated2;
import catfish.base.business.common.JORealAuthenticated3;
import catfish.base.business.common.jxl.CheckPointResult;
import catfish.base.business.common.jxl.RequestStatus;
import catfish.base.business.common.ylzh.ConsumeRegionNative;
import catfish.base.business.common.ylzh.ResponseState;
import catfish.base.business.util.AppDerivativeVariableNames;
import engine.databaseapi.DerivativeVariableApi;
import engine.rule.domain.BQSFinalDecision;
import engine.rule.domain.KaolaOverdueLevel;
import engine.rule.model.BaseForm;
import engine.rule.model.DerivativeVariableNames;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "第三方数据信息")
public class ThirdpartyDataInForm extends BaseForm {

    /************
     * 依图
     ***************/
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.YT_Similarity)
    @ModelField(name = "依图_比对相似度")
    private String ytSimilarity = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.YT_SimilarityQueryDatabase)
    @ModelField(name = "依图_查询照对比公安部照相似度")
    private String ytSimilarityQueryDataBase = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.YT_SimilarityQueryIdcard)
    @ModelField(name = "依图_查询照对比翻拍照相似度")
    private String ytSimilarityQueryIdcard = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.YT_SimilarityIdcardDatabase)
    @ModelField(name = "依图_翻拍照对比公安部照相似度")
    private String ytSimilarityIdcardDataBase = "";

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.YT_IsPass)
    @ModelField(name = "依图_比对是否通过")
    private boolean ytSimilarityIsPass = false;

    /********************
     * 聚信力
     ***********************/
    @DBField(fieldName = DerivativeVariableApi.StringValue,variableType = "X_JXL_isJXLReprotExist")
    @ModelField(name = "是否存在聚信立报告")
    private  boolean jxlReprotExist;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_GETREPORTSTATUS_RESULT)
    private Integer JXLReportStatus = RequestStatus.Error.getValue();

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPROTDATA_SUCCESS_FLAG)
    private boolean jxlReportDataSuccess = false;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_REGION_COUNT)
    @ModelField(name = "联系地区数量(默认值-1)")
    private Integer contactRegionCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_COUNT)
    @ModelField(name = "联系人数量(默认值-1)")
    private Integer contactCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_LENGTH)
    @ModelField(name = "呼出通话总时长(默认值-1)")
    private int callOutLength = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_COUNT)
    @ModelField(name = "呼出通话总数量(默认值-1)")
    private int callOutCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_LENGTH)
    @ModelField(name = "呼入通话总时长(默认值-1)")
    private int callInLength = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_COUNT)
    @ModelField(name = "呼入通话总数量(默认值-1)")
    private int callInCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_DATA_EXIST_MONTH_COUNT)
    @ModelField(name = "有数据月数(默认值-1)")
    private int dataExistMonthCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_NUMBER_COUNT)
    @ModelField(name = "联系号码数量(默认值-1)")
    private int contactNumberCount = -1;

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_REAL_AUTHENTICATED)
    @ModelField(name = "是否实名认证(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
    private String realAuthencated = CheckPointResult.NoData.getValue();

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_PROVIDER_INFO_MATCH)
    @ModelField(name = "运营商信息是否匹配(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
    private String providerInfoMatch = CheckPointResult.NoData.getValue();

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_ALWAYS_POWEROFF)
    @ModelField(name = "是否长时间关机(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
    private String alwaysPowerOff = CheckPointResult.NoData.getValue();

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_NEW_NUMBER)
    @ModelField(name = "绑定号码是否为新号(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
    private String newNumber = CheckPointResult.NoData.getValue();

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALLlOANPHONE)
    @ModelField(name = "是否拨打含‘贷款’的电话(默认值否)")
    private boolean hasCallLoanPhone = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALLFINANCEPHONE)
    @ModelField(name = "是否拨打含‘金融’的电话(默认值否)")
    private boolean hasCallFinancePhone = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_CALLJIEXINPHONE)
    @ModelField(name = "是否拨打含‘捷信’的电话(默认值否)")
    private boolean hasCallJiexinPhone = false;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_REG_CONTACTPHONE_IN_JXL_NUM)
    @ModelField(name = "用户所填联系人电话在聚信力中出现个数(默认值0)")
    private int contactPhoneNumberInJxlCount = 0;

    @ModelMethod(name = "(this)是否收集到聚信力报告")
    public boolean isJXLReportExist() {
        return this.JXLReportStatus == RequestStatus.Success.getValue() && this.jxlReportDataSuccess;
    }

    /********************************************/
    /************
     * 银联智惠
     ***********************/
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.YLZH_BANKCARD_MOBILE_MATCH)
    @ModelField(name = "银联智惠中银行卡和预留电话是否匹配(默认值数据为空)", bindDomain = "engine.rule.domain.ResponseState")
    private String bankcardMobileMatch = ResponseState.EmptyData.getValue();

    // 消费区域列表（城市）
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.YLZH_CONSUMEREGION_LIST)
    private String consumeRegionJsonArray = "";

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.YLZH_CONSUMPTION_TOTAL_REGION_COUNT)
    @ModelField(name = "银联智慧消费城市总数(默认值-1)")
    private int totalConsumeRegionCount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.YLZH_CONSUMPTION_TOTAL_COUNT)
    @ModelField(name = "银联智慧消费次数总数(默认值-1)")
    private int totalConsumeCount = -1;

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.YLZH_CONSUMPTION_TOTAL_AMOUNT)
    @ModelField(name = "银联智慧消费金额总数(默认值-1)")
    private BigDecimal totalConsumeAmount = new BigDecimal(-1);

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.YLZH_IS_MAX_CONSUMPTION_COUNT_NATIVE)
    @ModelField(name = "银联智慧最大消费次数城市是否本地(办理分期的城市,默认值数据为空)", bindDomain = "engine.rule.domain.ConsumeRegionNative")
    private int isMaxConsumptionCountNative = ConsumeRegionNative.EmptyData.getValue();

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.YLZH_IS_MAX_CONSUMPTION_AMOUNT_NATIVE)
    @ModelField(name = "银联智慧最大消费金额城市是否本地(办理分期的城市,默认值数据为空)", bindDomain = "engine.rule.domain.ConsumeRegionNative")
    private int isMaxConsumptionAmountNative = ConsumeRegionNative.EmptyData.getValue();

    /*****************************************/

    /****************
     * 同盾
     *******************/
    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33638)
    @ModelField(name = "同盾_借款手机号命中全局失信证据库")
    private boolean tdRule33638Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33640)
    @ModelField(name = "同盾_借款身份证命中全局失信证据库")
    private boolean tdRule33640Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33642)
    @ModelField(name = "同盾_借款人身份证命中法院执行证据库")
    private boolean tdRule33642Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33652)
    @ModelField(name = "同盾_借款人手机号命中虚假号码证据库")
    private boolean tdRule33652Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33654)
    @ModelField(name = "同盾_借款人手机号命中通信小号证据库")
    private boolean tdRule33654Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33674)
    @ModelField(name = "同盾_3个月内手机在多个平台进行借款")
    private boolean tdRule33674Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_33676)
    @ModelField(name = "同盾_3个月内身份证在多个平台进行借款")
    private boolean tdRule33676Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_57284)
    @ModelField(name = "同盾_借款人身份证命中法院失信证据库")
    private boolean tdRule57284Hit = false;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_RULE_33674_OUTER_PLATFORM_LOAN_AMOUNT)
    @ModelField(name = "同盾_3个月内手机在本平台外的借款申请次数")
    private int tdRule33674OuterPlatformLoanAmount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_RULE_33674_LOAN_AMOUNT)
    @ModelField(name = "同盾_3个月内手机多平台借款平台数")
    private int tdRule33674LoanAmount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_RULE_33676_OUTER_PLATFORM_LOAN_AMOUNT)
    @ModelField(name = "同盾_3个月内身份证在本平台外的借款申请次数")
    private int tdRule33676OuterPlatformLoanAmount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_RULE_33676_LOAN_AMOUNT)
    @ModelField(name = "同盾_3个月内身份证多平台借款平台数")
    private int tdRule33676LoanAmount = -1;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_FIRST_CONTACT_RULE_33638_HIT)
    @ModelField(name = "同盾_联系人1_借款手机号命中全局失信证据库")
    private boolean tdFirstContactRule33638Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_FIRST_CONTACT_RULE_33652_HIT)
    @ModelField(name = "同盾_联系人1_借款人手机号命中虚假号码证据库")
    private boolean tdFirstContactRule33652Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_FIRST_CONTACT_RULE_33654_HIT)
    @ModelField(name = "同盾_联系人1_借款人手机号命中通信小号证据库")
    private boolean tdFirstContactRule33654Hit = false;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_FIRST_CONTACT_RULE_33674_HIT_LOAN_AMOUNT)
    @ModelField(name = "同盾_联系人1_3个月内手机在本平台外的借款申请次数")
    private int tdFirstContactRule33674HitLoanAmount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_FIRST_CONTACT_RULE_33674_HIT_PLATFORM_AMOUNT)
    @ModelField(name = "同盾_联系人1_3个月内手机多平台借款平台数")
    private int tdFirstContactRule33674HitPlatformAmount = -1;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_SECOND_CONTACT_RULE_33638_HIT)
    @ModelField(name = "同盾_联系人2_借款手机号命中全局失信证据库")
    private boolean tdSecondContactRule33638Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_SECOND_CONTACT_RULE_33652_HIT)
    @ModelField(name = "同盾_联系人2_借款人手机号命中虚假号码证据库")
    private boolean tdSecondContactRule33652Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_SECOND_CONTACT_RULE_33654_HIT)
    @ModelField(name = "同盾_联系人2_借款人手机号命中通信小号证据库")
    private boolean tdSecondContactRule33654Hit = false;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_SECOND_CONTACT_RULE_33674_HIT_LOAN_AMOUNT)
    @ModelField(name = "同盾_联系人2_3个月内手机在本平台外的借款申请次数")
    private int tdSecondContactRule33674HitLoanAmount = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.TD_SECOND_CONTACT_RULE_33674_HIT_PLATFORM_AMOUNT)
    @ModelField(name = "同盾_联系人2_3个月内手机多平台借款平台数")
    private int tdSecondContactRule33674HitPlatformAmount = -1;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_811950)
    @ModelField(name = "同盾_借款人身份证命中法院结案证据库")
    private boolean tdRule811950Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_811952)
    @ModelField(name = "同盾_借款人身份证命中失联证据库")
    private boolean tdRule811952Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_811954)
    @ModelField(name = "同盾_借款人手机号命中失联证据库")
    private boolean tdRule811954Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_RULE_811956)
    @ModelField(name = "同盾_借款人身份证命中犯罪通缉名单库")
    private boolean tdRule811956Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_FIRST_CONTACT_RULE_811954_HIT)
    @ModelField(name = "同盾_联系人1_借款人手机号命中失联证据库")
    private boolean tdFirstContactRule811954Hit = false;

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.TD_SECOND_CONTACT_RULE_811954_HIT)
    @ModelField(name = "同盾_联系人2_借款人手机号命中失联证据库")
    private boolean tdSecondContactRule811954Hit = false;

    /******************************************************/

    /*********************
     * 鹏元
     *******************************/

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.PY_JUSTICE_CASE_INFO_NUMBER)
    @ModelField(name = "鹏元_司法案例信息（条）")
    private int pyJusticeCaseInfoNumber = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.PY_JUSTICE_EXECUTE_INFO_NUMBER)
    @ModelField(name = "鹏元_司法执行信息（条）")
    private int pyJusticeExecuteInfoNumber = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.PY_JUSTICE_LOSE_TRACK_INFO_NUMBER)
    @ModelField(name = "鹏元_司法失信信息（条）")
    private int pyJusticeLoseTrackInfoNumber = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.PY_TAX_ADMINISTRATION_LAW_INFO_NUMBER)
    @ModelField(name = "鹏元_税务行政执法信息（条）")
    private int pyTaxAdministrationLawInfoNumber = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.PY_COLLECTION_NOTICE_INFO_NUMBER)
    @ModelField(name = "鹏元_催欠公告信息（条）")
    private int pyCollectionNoticeInfoNumber = -1;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.PY_INTERNET_LOAN_OVERDUE_INFO_NUMBER)
    @ModelField(name = "鹏元_网贷逾期信息（条）")
    private int pyInternetLoanOverdueInfoNumber = -1;

    /*****************************************/

    /**************
     * REG007
     *********************/
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.REG007_WEBSITESCORE)
    @ModelField(name = "REG007_ 网站得分(默认值0)")
    private BigDecimal reg007WebsiteScore = new BigDecimal(0);

    @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.REG007_ISBLACKLISTWEBSITE)
    @ModelField(name = "REG007_是否是黑名单网站")
    private boolean reg007IsBlacklistWebsite = false;
    /*****************************************/
    /************
     * 骏聿
     *************************/
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JY_IDPHOTO_SIMILARITY)
    @ModelField(name = "骏聿_身份证照和公安部照对比相似度(默认值101,表示空)")
    private int jyIDCardPhotoCheckSimilarity = 101;

    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JY_HEADPHOTO_SIMILARITY)
    @ModelField(name = "骏聿_身份证照和现场照对比相似度(默认值101,表示空)")
    private int jyHeadPhotoCheckSimilarity = 101;

    /*****************************************/
    /******** 前海 ******************************/
    // @DBField(fieldName = DerivativeVariableApi.StringValue, variableType =
    // AppDerivativeVariableNames.QHZX_DATA_LATEST_BUILDTIME)
    // @ModelField(name = "最近的黑名单创建时间(默认当前时间)")
    // private String qhzx_LatestDataBuildTime = "";
    //
    // @DBField(fieldName = DerivativeVariableApi.StringValue, variableType =
    // AppDerivativeVariableNames.QHZX_HASBLACKLIST)
    // @ModelField(name = "历史上是否有征信记录")
    // private String qhzx_HasBlackList = "";
    //
    // @DBField(fieldName = DerivativeVariableApi.StringValue, variableType =
    // AppDerivativeVariableNames.QHZX_HASBEENEXECUTED)
    // @ModelField(name = "历史是否是被执行人")
    // private String qhzx_HasBeenExcuted = "";
    //
    // @DBField(fieldName = DerivativeVariableApi.StringValue, variableType =
    // AppDerivativeVariableNames.QHZX_BREAKPROMISE)
    // @ModelField(name = "历史是否有违约")
    // private String qhzx_BreakPromise = "";
    //
    // @DBField(fieldName = DerivativeVariableApi.StringValue, variableType =
    // AppDerivativeVariableNames.QHZX_BREAKPROMISEBEENEXECUTED)
    // @ModelField(name = "历史是否是失信被执行人")
    // private String qhzx_BreakPromiseBeenExecuted = "";
    //
    // @DBField(fieldName = DerivativeVariableApi.StringValue, variableType =
    // AppDerivativeVariableNames.QHZX_MAXIMIZEOVERDUEDAY)
    // @ModelField(name = "历史最严重逾期时间")
    // private String qhzx_MaximizeOverdueDay = "";
    //
    // @DBField(fieldName = DerivativeVariableApi.StringValue, variableType =
    // AppDerivativeVariableNames.QHZX_MAXIMIZEMONEYBOUND)
    // @ModelField(name = "历史最高金额涉足范围(0-5)")
    // private String qhzx_MoneyBound = "";
    //
    // @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType =
    // AppDerivativeVariableNames.QHZX_HAS_CREDIT_OVERDUE_RISK)
    // @ModelField(name = "是否信贷逾期风险(默认false)")
    // private Boolean qhzx_HasCreditOverdueRisk = false;
    //
    // @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType =
    // AppDerivativeVariableNames.QHZX_HAS_ADMINISTRATION_NEGATIVE_RISK)
    // @ModelField(name = "是否行政负面风险(默认false)")
    // private Boolean qhzx_HasAdministrationNegativeRisk = false;
    //
    // @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType =
    // AppDerivativeVariableNames.QHZX_HAS_FRAUD_RISK)
    // @ModelField(name = "是否欺诈风险(默认false)")
    // private Boolean qhzx_HasFraudRisk = false;
    //
    // @DBField(fieldName = DerivativeVariableApi.IntValue, variableType =
    // AppDerivativeVariableNames.QHZX_RISK_SCORE)
    // @ModelField(name = "风险得分(10-45)(默认-1)")
    // private Integer qhzx_RiskScore = -1;
    //
    // @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType =
    // AppDerivativeVariableNames.QHZX_HAS_SERIOUS_TRAFFIC_VIOLATION_RISK)
    // @ModelField(name = "是否交通严重违章(默认false)")
    // private Boolean qhzx_HasSeriousTrafficViolationRisk = false;
    //
    // @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType =
    // AppDerivativeVariableNames.QHZX_HAS_MOBILE_FRAUD_RISK)
    // @ModelField(name = "是否手机号存在欺诈风险(默认false)")
    // private Boolean qhzx_HasMobileFraudRisk = false;
    //
    // @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType =
    // AppDerivativeVariableNames.QHZX_HAS_BANK_CARD_FRAUD_RISK)
    // @ModelField(name = "是否卡号存在欺诈风险(默认false)")
    // private Boolean qhzx_HasBankCardFraudRisk = false;
    //
    // @DBField(fieldName = DerivativeVariableApi.BoolValue, variableType =
    // AppDerivativeVariableNames.QHZX_HAS_ID_CARD_FRAUD_RISK)
    // @ModelField(name = "是否身份证号存在欺诈风险(默认false)")
    // private Boolean qhzx_HasIdCardFraudRisk = false;
    //
    // @DBField(fieldName = DerivativeVariableApi.StringValue, variableType =
    // AppDerivativeVariableNames.QHZX_DATA_STATUS)
    // @ModelField(name = "数据状态(99-权限不足,1-已验证,2-待验证,3-异议中)")
    // private String qhzx_DataStatus = "";

    /*****************************************/
    /******** 百融 ******************************/

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_BAD)
    @ModelField(name = "通过身份证查询银行不良,0-直接命中;1-第一层关系命中;2-第二层关系命中")
    private String br_SpecialListIdBankBad = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_OVERDUE)
    @ModelField(name = "通过身份证查询银行短时逾期")
    private String br_SpecialListIdBankOverdue = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_FRAUD)
    @ModelField(name = "通过身份证查询银行欺诈")
    private String br_SpecialListIdBankFraud = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_LOST)
    @ModelField(name = "通过身份证查询银行失联")
    private String br_SpecialListIdBankLost = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_REFUSE)
    @ModelField(name = "通过身份证查询银行拒绝")
    private String br_SpecialListIdBankRefuse = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_CREDIT_BAD)
    @ModelField(name = "通过身份证查询资信不良")
    private String br_SpecialListIdCreditBad = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_BAD)
    @ModelField(name = "通过身份证查询小贷或P2P不良")
    private String br_SpecialListIdP2PBad = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_OVERDUE)
    @ModelField(name = "通过身份证查询小贷或P2P短时逾期")
    private String br_SpecialListIdP2POverdue = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_FRAUD)
    @ModelField(name = "通过身份证查询小贷或P2P欺诈")
    private String br_SpecialListIdP2PFraud = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_LOST)
    @ModelField(name = "通过身份证查询小贷或P2P失联")
    private String br_SpecialListIdP2PLost = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_REFUSE)
    @ModelField(name = "通过身份证查询小贷或P2P拒绝")
    private String br_SpecialListIdP2PRefuse = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_PHONE_OVERDUE)
    @ModelField(name = "通过身份证查询电信欠费")
    private String br_SpecialListIdPhoneOverdue = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_INS_FRAUD)
    @ModelField(name = "通过身份证查询保险骗保")
    private String br_SpecialListIdInsFraud = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_COURT_BAD)
    @ModelField(name = "通过身份证查询法院失信人")
    private String br_SpecialListIdCourtBad = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_ID_COURT_EXECUTED)
    @ModelField(name = "通过身份证查询法院被执行人")
    private String br_SpecialListIdCourtExecuted = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_BAD)
    @ModelField(name = "通过手机查询银行不良")
    private String br_SpecialListCellBankBad = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_OVERDUE)
    @ModelField(name = "通过手机查询银行短时逾期")
    private String br_SpecialListCellBankOverdue = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_FRAUD)
    @ModelField(name = "通过手机查询银行欺诈")
    private String br_SpecialListCellBankFraud = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_LOST)
    @ModelField(name = "通过手机查询银行失联")
    private String br_SpecialListCellBankLost = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_REFUSE)
    @ModelField(name = "通过手机查询银行拒绝")
    private String br_SpecialListCellBankRefuse = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_BAD)
    @ModelField(name = "通过手机查询小贷或P2P不良")
    private String br_SpecialListCellP2PBad = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_OVERDUE)
    @ModelField(name = "通过手机查询小贷或P2P短时逾期")
    private String br_SpecialListCellP2POverdue = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_FRAUD)
    @ModelField(name = "通过手机查询小贷或P2P欺诈")
    private String br_SpecialListCellP2PFraud = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_LOST)
    @ModelField(name = "通过手机查询小贷或P2P失联")
    private String br_SpecialListCellP2PLost = "";
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_REFUSE)
    @ModelField(name = "通过手机查询小贷或P2P拒绝")
    private String br_SpecialListCellP2PRefuse = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_PHONE_OVERDUE)
    @ModelField(name = "通过手机查询电信欠费")
    private String br_SpecialListCellPhoneOverdue = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_SPECIALLIST_CELL_INS_FRAUD)
    @ModelField(name = "通过手机查询保险骗保")
    private String br_SpecialListCellInsFraud = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_RJ_Rule)
    @ModelField(name = "风险规则:命中取值为1,未命中取值为空")
    private String br_RJ_Rule = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_sl_id)
    @ModelField(name = "身份证特殊名单校验:命中取值为1,未命中取值为空")
    private String br_sl_id = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.BR_sl_cell)
    @ModelField(name = "手机特殊名单校验,命中取值为1,未命中取值为空")
    private String br_sl_cell = "";

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_LengthOfNetwork)
    @ModelField(name = "集奥在网时长", bindDomain = "engine.rule.domain.JOLengthOfNetworkResult")
    private String joLengthOfNetwork = JOLengthOfNetwork.NoneRecord.getValue();
    /*****************************************/
    /************
     * 骏聿
     *************************/
    //集奥第一联系人二要素认证
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsFirstContactRealAuthenticated2)
    @ModelField(name = "集奥第一联系人二要素认证", bindDomain = "engine.rule.domain.JORealAuthenticated2Result")
    private String joIsFirstContactRealAuthenticated2 = JORealAuthenticated2.NoneRecord.getValue();

    //集奥第二联系人二要素认证
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsSecondContactRealAuthenticated2)
    @ModelField(name = "集奥第二联系人二要素认证", bindDomain = "engine.rule.domain.JORealAuthenticated2Result")
    private String joIsSecondContactRealAuthenticated2 = JORealAuthenticated2.NoneRecord.getValue();
  
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsRealAuthenticated3)
    @ModelField(name = "集奥三要素验证", bindDomain = "engine.rule.domain.JORealAuthenticated3Result")
    private String joIsRealAuthenticated3 = JORealAuthenticated3.NoneRecord.getValue();

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsRealAuthenticated2)
    @ModelField(name = "集奥二要素验证", bindDomain = "engine.rule.domain.JORealAuthenticated2Result")
    private String joIsRealAuthenticated2 = JORealAuthenticated2.NoneRecord.getValue();

    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.XBaiduScore_type)
    @ModelField(name = "百度评分是否匹配上百度ID(默认未匹配)", bindDomain = "engine.rule.domain.Blackexist")
    private String baiduScoreType = "0";

    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.XBaiduMdx_usmbl)
    @ModelField(name = "百度评分分值,区间:(0,1)(默认-1.0)")
    private Double baiduMdxUsmbl = -1.0;  
    
    /****************同盾V3*******************/
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FINALDECISION)
	@ModelField(name = "同盾V3_风险评估结果，取所有策略中分数最高的结果，结果有三种：Accept无风险，通过；Review低风险，审查；Reject高风险，拒绝")
	private String finalDecision = "";

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISCOURTDISHONESTY)
	@ModelField(name = "同盾V3_身份证命中法院失信名单")
	private boolean idCardIsCourtDishonesty = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISCOURTEXECUTED)
	@ModelField(name = "同盾V3_身份证命中法院执行名单")
	private boolean idCardIsCourtExecuted = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISCOURTCLOSED)
	@ModelField(name = "同盾V3_身份证命中法院结案名单")
	private boolean idCardIsCourtClosed = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISCRIMEWANTED)
	@ModelField(name = "同盾V3_身份证命中犯罪通缉名单")
	private boolean idCardIsCrimeWanted = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISSTUDENTLOAN)
	@ModelField(name = "同盾V3_身份证号对应人存在助学贷款逾期历史")
	private boolean idCardIsStudentLoan = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISLOANOVERDUE)
	@ModelField(name = "同盾V3_身份证命中信贷逾期名单")
	private boolean idCardIsLoanOverdue = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDISLOANOVERDUEREPAY)
	@ModelField(name = "同盾V3_身份证命中信贷逾期后还款名单")
	private boolean idCardIsLoanOverdueRepay = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISFAKE)
	@ModelField(name = "同盾V3_手机号命中虚假号码库")
	private boolean mobileIsFake = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISSMALL)
	@ModelField(name = "同盾V3_手机号命中通信小号库")
	private boolean mobileIsSmall = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISBILK)
	@ModelField(name = "同盾V3_手机号命中诈骗骚扰库")
	private boolean mobileIsBilk = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISLOANBLACKMEDIUM)
	@ModelField(name = "同盾V3_手机号命中贷款黑中介库")
	private boolean mobileIsLoanBlackMedium = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISLOANOVERDUE)
	@ModelField(name = "同盾V3_手机号命中信贷逾期名单")
	private boolean mobileIsLoanOverdue = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISVEHICLEHIRE)
	@ModelField(name = "同盾V3_手机号命中车辆租赁违约名单")
	private boolean mobileIsVehicleHire = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISOVERDRAFTCORPORATE)
	@ModelField(name = "同盾V3_手机号命中欠款公司法人名单")
	private boolean mobileIsOverdraftCorporate = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISLOST)
	@ModelField(name = "同盾V3_手机号命中失联名单")
	private boolean mobileIsLost = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEISLOANOVERDUEREPAY)
	@ModelField(name = "同盾V3_手机号命中信贷逾期后还款名单")
	private boolean mobileIsLoanOverdueRepay = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDAPPLYS3MONTH)
	@ModelField(name = "同盾V3_3个月内身份证关联多个申请信息")
	private boolean idCardApplys3Month = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.APPLYIDCARDS3MONTH)
	@ModelField(name = "同盾V3_3个月内申请信息关联多个身份证")
	private boolean applyIdCards3Month = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.BANKNAMEIDCARDS3MONTH)
	@ModelField(name = "同盾V3_3个月内银行卡_姓名关联多个身份证")
	private boolean bankNameIdCards3Month = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.IDCARDASCONTACTNUM2)
	@ModelField(name = "同盾V3_3个月内申请人身份证作为联系人身份证出现的次数大于等于2")
	private boolean idCardAsContactNum2 = false;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.MOBILEASCONTACTNUM2)
	@ModelField(name = "同盾V3_3个月内申请人手机号作为联系人手机号出现的次数大于等于2")
	private boolean mobileAsContactNum2 = false;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS7CNT)
	@ModelField(name = "同盾V3_7天内申请人在多个平台申请借款")
	private int applyLoanOnPlats7Cnt = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS30CNT)
	@ModelField(name = "同盾V3_30天内申请人在多个平台申请借款-个数")
	private int applyLoanOnPlats30Cnt = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS3MONTHCNT)
	@ModelField(name = "同盾V3_3个月内申请人在多个平台申请借款-个数")
	private int applyLoanOnPlats3MonthCnt = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS6MONTHCNT)
	@ModelField(name = "同盾V3_6个月内申请人在多个平台申请借款-个数")
	private int applyLoanOnPlats6MonthCnt = -1;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.APPLYLOANONPLATS12MONTHCNT)
	@ModelField(name = "同盾V3_12个月内申请人在多个平台申请借款-个数")
	private int applyLoanOnPlats12MonthCnt = -1;
	
	//泰融泰融小额信贷-start
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.SCORE)
	@ModelField(name = "泰融小额信贷-具体评分")
	private int score = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.MONTHLYINCOME)
	@ModelField(name = "泰融小额信贷-平均收入额度")
	private int monthlyIncome = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.JOBSTABILITY)
	@ModelField(name = "泰融小额信贷-工作稳定性")
	private int jobStability = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CASHSCORE)
	@ModelField(name = "泰融小额信贷-银行卡套现综合评分")
	private int cashScore = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CASHMODELCLASSIFICATION)
	@ModelField(name = "泰融小额信贷-银行卡套现综合模型分类")
	private int cashModelClassIfication = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CONSUMERTRENDS)
	@ModelField(name = "泰融小额信贷-消费趋势得分")
	private int consumerTrends = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.SPENDINGPOWER)
	@ModelField(name = "泰融小额信贷-消费能力得分")
	private int spendingPower = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CUSTOMERVALUE)
	@ModelField(name = "泰融小额信贷-客户综合价值得分")
	private int customerValue = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CONSUMPTIONSURPLUS)
	@ModelField(name = "泰融小额信贷-消费富余度综合评分")
	private int consumptionSurplus = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CONSUMPTIONSURPLUSCLASS)
	@ModelField(name = "泰融小额信贷-消费富余度综合分类")
	private int consumptionSurplusClass = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CONSUMPTIONPREFERENCES)
	@ModelField(name = "泰融小额信贷-消费偏好综合得分")
	private int consumptionPreferences = -1;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CONSUMPTIONSTABILITY)
	@ModelField(name = "泰融小额信贷-消费交易金额稳定性")
	private int consumptionStability = -1;
	
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.KEEPCARD)
	@ModelField(name = "泰融小额信贷-疑似养卡标识")
	private boolean keepCard =false;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.CALLSTABILITY)
	@ModelField(name = "泰融小额信贷-手机通话稳定性")
	private int callStability = -1;
	//泰融泰融小额信贷-end
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = DerivativeVariableNames.ZMXY_SCORE)
	@ModelField(name = "芝麻信用分")
	private int zmxyScore = -1;
	
	//富数start
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_HIT_CONDITION)
    @ModelField(name = "富数黑名单命中条件")
    private String fushuHitCondition = "";
	
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.FUSHU_INBLACKLIST)
	@ModelField(name = "富数是否在黑名单")
	private boolean fushuInBlackList = false;
	
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.FUSHU_INBLACKLIST_COURT)
	@ModelField(name = "富数是否在法院黑名单")
	private boolean fushuInBlackListOfCourt = false;
	
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = DerivativeVariableNames.FUSHU_INBLACKLIST_FINANCE)
	@ModelField(name = "富数是否在金融机构黑名单")
	private boolean fushuInBlackListOfFinance = false;
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_TYPE)
    @ModelField(name = "富数黑名单来源")
    private String fushuType = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_DUTY)
	@ModelField(name = "富数法院黑名单记录详情")
	private String fushuDuty = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_REGISTER_DATE)
	@ModelField(name = "富数金融机构黑名单")
	private String fushuRegisterDate = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_DATE)
	@ModelField(name = "富数逾期日期")
	private String fushuDate = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_LEVEL)
	@ModelField(name = "富数黑名单等级")
	private String fushuLevel = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_DETAIL)
	@ModelField(name = "富数黑名单详情")
	private String fushuDetail = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.FUSHU_UPDATETIME)
	@ModelField(name = "富数更新时间")
	private String fushuUpdatetime = "";
	
	//富数end
	
	//白骑士黑名单
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.BQS_FINALDECISION)
	@ModelField(name = "白骑士最终决定",bindDomain="engine.rule.domain.BQSFinalDecisionResult")
	private String BQS_FINALDECISION= BQSFinalDecision.NoneRecord.getValue();
	
	//考拉黑名单
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_BORROW_DATE)
	@ModelField(name = "考拉借款日期")
	private String kaolaBorrowDate = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_BORROW_AMOUNT)
	@ModelField(name = "考拉借款金额")
	private String kaolaBorrowAmount = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_BORROW_PERIOD)
	@ModelField(name = "考拉借款期限")
	private String kaolaBorrowPeriod = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_OVERDUE_DATE)
	@ModelField(name = "考拉逾期日期")
	private String kaolaOverdueDate = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_OVERDUE_AMOUNT)
	@ModelField(name = "考拉逾期金额")
	private String kaolaOverAmount = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_OVERDUE_LEVER)
	@ModelField(name = "考拉逾期级别",bindDomain="engine.rule.domain.KaolaOverdueLevelResult")
	private String kaolaOverLevel= KaolaOverdueLevel.NoneRecord.getValue();
	

	//考拉个人身份回查
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_IDENTIRY_IDCARD_NAME)
	@ModelField(name = "考拉个人身份回查身份证姓名")
	private String kaoLaIdentityIdCardName = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_IDENTIRY_IDCARD_CODE)
	@ModelField(name = "考拉个人身份回查身份证号码")
	private String kaoLaIdentityIdCardCode = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_IDENTIRY_TIME_INTERVAL)
	@ModelField(name = "考拉个人身份回查时间范围")
	private String kaoLaIdentityTimeInterval = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_IDENTIRY_TOTAL_TRANSNUM)
	@ModelField(name = "考拉个人身份回查被查询次数")
	private String kaoLaIdentityToTalTransNum = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_IDENTIRY_LAST_TRANSTIME)
	@ModelField(name = "考拉个人身份回查最后一次查询时间")
	private String kaoLaIdentityLastTransTime = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_IDENTIRY_LAST_INDUSTRY)
	@ModelField(name = "考拉个人身份回查最后一次查询企业所属行业")
	private String kaoLaIdentityLastIndustry = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_IDENTIRY_LAST_PRDGRP_NAME)
	@ModelField(name = "考拉个人身份回查最后一次查询产品类别")
	private String kaoLaIdentityLastPrdGrpName = "";
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = DerivativeVariableNames.KAOLA_IDENTIRY_TOTAL_PRDGRP_NUM)
	@ModelField(name = "考拉个人身份回查被查询类别次数")
	private String kaoLaIdentityToTalprdGrpNum = "";
	/******************************************************/
    
    public boolean isJxlReprotExist() {
        return jxlReprotExist;
    }

    public void setJxlReprotExist(boolean jxlReprotExist) {
        this.jxlReprotExist = jxlReprotExist;
    }

    public String getBaiduScoreType() {
        return baiduScoreType;
    }

    public void setBaiduScoreType(String baiduScoreType) {
        this.baiduScoreType = baiduScoreType;
    }

    public Double getBaiduMdxUsmbl() {
        return baiduMdxUsmbl;
    }

    public void setBaiduMdxUsmbl(Double baiduMdxUsmbl) {
        this.baiduMdxUsmbl = baiduMdxUsmbl;
    }

    public String getJoLengthOfNetwork() {
        return joLengthOfNetwork;
    }

    public void setJoLengthOfNetwork(String joLengthOfNetwork) {
        this.joLengthOfNetwork = joLengthOfNetwork;
    }

    public String getJoIsRealAuthenticated3() {
        return joIsRealAuthenticated3;
    }

    public void setJoIsRealAuthenticated3(String joIsRealAuthenticated3) {
        this.joIsRealAuthenticated3 = joIsRealAuthenticated3;
    }

    public String getJoIsRealAuthenticated2() {
        return joIsRealAuthenticated2;
    }

    public void setJoIsRealAuthenticated2(String joIsRealAuthenticated2) {
        this.joIsRealAuthenticated2 = joIsRealAuthenticated2;
    }

    public String getBr_SpecialListIdBankBad() {
        return br_SpecialListIdBankBad;
    }

    public void setBr_SpecialListIdBankBad(String br_SpecialListIdBankBad) {
        this.br_SpecialListIdBankBad = br_SpecialListIdBankBad;
    }

    public String getBr_SpecialListIdBankOverdue() {
        return br_SpecialListIdBankOverdue;
    }

    public void setBr_SpecialListIdBankOverdue(String br_SpecialListIdBankOverdue) {
        this.br_SpecialListIdBankOverdue = br_SpecialListIdBankOverdue;
    }

    public String getBr_SpecialListIdBankFraud() {
        return br_SpecialListIdBankFraud;
    }

    public void setBr_SpecialListIdBankFraud(String br_SpecialListIdBankFraud) {
        this.br_SpecialListIdBankFraud = br_SpecialListIdBankFraud;
    }

    public String getBr_SpecialListIdBankLost() {
        return br_SpecialListIdBankLost;
    }

    public void setBr_SpecialListIdBankLost(String br_SpecialListIdBankLost) {
        this.br_SpecialListIdBankLost = br_SpecialListIdBankLost;
    }

    public String getBr_SpecialListIdBankRefuse() {
        return br_SpecialListIdBankRefuse;
    }

    public void setBr_SpecialListIdBankRefuse(String br_SpecialListIdBankRefuse) {
        this.br_SpecialListIdBankRefuse = br_SpecialListIdBankRefuse;
    }

    public String getBr_SpecialListIdCreditBad() {
        return br_SpecialListIdCreditBad;
    }

    public void setBr_SpecialListIdCreditBad(String br_SpecialListIdCreditBad) {
        this.br_SpecialListIdCreditBad = br_SpecialListIdCreditBad;
    }

    public String getBr_SpecialListIdP2PBad() {
        return br_SpecialListIdP2PBad;
    }

    public void setBr_SpecialListIdP2PBad(String br_SpecialListIdP2PBad) {
        this.br_SpecialListIdP2PBad = br_SpecialListIdP2PBad;
    }

    public String getBr_SpecialListIdP2POverdue() {
        return br_SpecialListIdP2POverdue;
    }

    public void setBr_SpecialListIdP2POverdue(String br_SpecialListIdP2POverdue) {
        this.br_SpecialListIdP2POverdue = br_SpecialListIdP2POverdue;
    }

    public String getBr_SpecialListIdP2PFraud() {
        return br_SpecialListIdP2PFraud;
    }

    public void setBr_SpecialListIdP2PFraud(String br_SpecialListIdP2PFraud) {
        this.br_SpecialListIdP2PFraud = br_SpecialListIdP2PFraud;
    }

    public String getBr_SpecialListIdP2PLost() {
        return br_SpecialListIdP2PLost;
    }

    public void setBr_SpecialListIdP2PLost(String br_SpecialListIdP2PLost) {
        this.br_SpecialListIdP2PLost = br_SpecialListIdP2PLost;
    }

    public String getBr_SpecialListIdP2PRefuse() {
        return br_SpecialListIdP2PRefuse;
    }

    public void setBr_SpecialListIdP2PRefuse(String br_SpecialListIdP2PRefuse) {
        this.br_SpecialListIdP2PRefuse = br_SpecialListIdP2PRefuse;
    }

    public String getBr_SpecialListIdPhoneOverdue() {
        return br_SpecialListIdPhoneOverdue;
    }

    public void setBr_SpecialListIdPhoneOverdue(String br_SpecialListIdPhoneOverdue) {
        this.br_SpecialListIdPhoneOverdue = br_SpecialListIdPhoneOverdue;
    }

    public String getBr_SpecialListIdInsFraud() {
        return br_SpecialListIdInsFraud;
    }

    public void setBr_SpecialListIdInsFraud(String br_SpecialListIdInsFraud) {
        this.br_SpecialListIdInsFraud = br_SpecialListIdInsFraud;
    }

    public String getBr_SpecialListIdCourtBad() {
        return br_SpecialListIdCourtBad;
    }

    public void setBr_SpecialListIdCourtBad(String br_SpecialListIdCourtBad) {
        this.br_SpecialListIdCourtBad = br_SpecialListIdCourtBad;
    }

    public String getBr_SpecialListIdCourtExecuted() {
        return br_SpecialListIdCourtExecuted;
    }

    public void setBr_SpecialListIdCourtExecuted(String br_SpecialListIdCourtExecuted) {
        this.br_SpecialListIdCourtExecuted = br_SpecialListIdCourtExecuted;
    }

    public String getBr_SpecialListCellBankBad() {
        return br_SpecialListCellBankBad;
    }

    public void setBr_SpecialListCellBankBad(String br_SpecialListCellBankBad) {
        this.br_SpecialListCellBankBad = br_SpecialListCellBankBad;
    }

    public String getBr_SpecialListCellBankOverdue() {
        return br_SpecialListCellBankOverdue;
    }

    public void setBr_SpecialListCellBankOverdue(String br_SpecialListCellBankOverdue) {
        this.br_SpecialListCellBankOverdue = br_SpecialListCellBankOverdue;
    }

    public String getBr_SpecialListCellBankFraud() {
        return br_SpecialListCellBankFraud;
    }

    public void setBr_SpecialListCellBankFraud(String br_SpecialListCellBankFraud) {
        this.br_SpecialListCellBankFraud = br_SpecialListCellBankFraud;
    }

    public String getBr_SpecialListCellBankLost() {
        return br_SpecialListCellBankLost;
    }

    public void setBr_SpecialListCellBankLost(String br_SpecialListCellBankLost) {
        this.br_SpecialListCellBankLost = br_SpecialListCellBankLost;
    }

    public String getBr_SpecialListCellBankRefuse() {
        return br_SpecialListCellBankRefuse;
    }

    public void setBr_SpecialListCellBankRefuse(String br_SpecialListCellBankRefuse) {
        this.br_SpecialListCellBankRefuse = br_SpecialListCellBankRefuse;
    }

    public String getBr_SpecialListCellP2PBad() {
        return br_SpecialListCellP2PBad;
    }

    public void setBr_SpecialListCellP2PBad(String br_SpecialListCellP2PBad) {
        this.br_SpecialListCellP2PBad = br_SpecialListCellP2PBad;
    }

    public String getBr_SpecialListCellP2POverdue() {
        return br_SpecialListCellP2POverdue;
    }

    public void setBr_SpecialListCellP2POverdue(String br_SpecialListCellP2POverdue) {
        this.br_SpecialListCellP2POverdue = br_SpecialListCellP2POverdue;
    }

    public String getBr_SpecialListCellP2PFraud() {
        return br_SpecialListCellP2PFraud;
    }

    public void setBr_SpecialListCellP2PFraud(String br_SpecialListCellP2PFraud) {
        this.br_SpecialListCellP2PFraud = br_SpecialListCellP2PFraud;
    }

    public String getBr_SpecialListCellP2PLost() {
        return br_SpecialListCellP2PLost;
    }

    public void setBr_SpecialListCellP2PLost(String br_SpecialListCellP2PLost) {
        this.br_SpecialListCellP2PLost = br_SpecialListCellP2PLost;
    }

    public String getBr_SpecialListCellP2PRefuse() {
        return br_SpecialListCellP2PRefuse;
    }

    public void setBr_SpecialListCellP2PRefuse(String br_SpecialListCellP2PRefuse) {
        this.br_SpecialListCellP2PRefuse = br_SpecialListCellP2PRefuse;
    }

    public String getBr_SpecialListCellPhoneOverdue() {
        return br_SpecialListCellPhoneOverdue;
    }

    public void setBr_SpecialListCellPhoneOverdue(String br_SpecialListCellPhoneOverdue) {
        this.br_SpecialListCellPhoneOverdue = br_SpecialListCellPhoneOverdue;
    }

    public String getBr_SpecialListCellInsFraud() {
        return br_SpecialListCellInsFraud;
    }

    public void setBr_SpecialListCellInsFraud(String br_SpecialListCellInsFraud) {
        this.br_SpecialListCellInsFraud = br_SpecialListCellInsFraud;
    }

    public String getBr_RJ_Rule() {
        return br_RJ_Rule;
    }

    public void setBr_RJ_Rule(String br_RJ_Rule) {
        this.br_RJ_Rule = br_RJ_Rule;
    }

    public String getBr_sl_id() {
        return br_sl_id;
    }

    public void setBr_sl_id(String br_sl_id) {
        this.br_sl_id = br_sl_id;
    }

    public String getBr_sl_cell() {
        return br_sl_cell;
    }

    public void setBr_sl_cell(String br_sl_cell) {
        this.br_sl_cell = br_sl_cell;
    }

    public Integer getContactRegionCount() {
        return contactRegionCount;
    }

    public void setContactRegionCount(Integer contactRegionCount) {
        this.contactRegionCount = contactRegionCount;
    }

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    public int getCallOutCount() {
        return callOutCount;
    }

    public void setCallOutCount(int callOutCount) {
        this.callOutCount = callOutCount;
    }

    public int getCallInCount() {
        return callInCount;
    }

    public void setCallInCount(int callInCount) {
        this.callInCount = callInCount;
    }

    public int getDataExistMonthCount() {
        return dataExistMonthCount;
    }

    public void setDataExistMonthCount(int dataExistMonthCount) {
        this.dataExistMonthCount = dataExistMonthCount;
    }

    public int getContactNumberCount() {
        return contactNumberCount;
    }

    public void setContactNumberCount(int contactNumberCount) {
        this.contactNumberCount = contactNumberCount;
    }

    public String getRealAuthencated() {
        return realAuthencated;
    }

    public void setRealAuthencated(String realAuthencated) {
        this.realAuthencated = realAuthencated;
    }

    public String getProviderInfoMatch() {
        return providerInfoMatch;
    }

    public void setProviderInfoMatch(String providerInfoMatch) {
        this.providerInfoMatch = providerInfoMatch;
    }

    public String getAlwaysPowerOff() {
        return alwaysPowerOff;
    }

    public void setAlwaysPowerOff(String alwaysPowerOff) {
        this.alwaysPowerOff = alwaysPowerOff;
    }

    public String getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(String newNumber) {
        this.newNumber = newNumber;
    }

    public Integer getJXLReportStatus() {
        return JXLReportStatus;
    }

    public void setJXLReportStatus(Integer jXLReportStatus) {
        JXLReportStatus = jXLReportStatus;
    }

    public int getCallOutLength() {
        return callOutLength;
    }

    public void setCallOutLength(int callOutLength) {
        this.callOutLength = callOutLength;
    }

    public int getCallInLength() {
        return callInLength;
    }

    public void setCallInLength(int callInLength) {
        this.callInLength = callInLength;
    }

    public String getBankcardMobileMatch() {
        return bankcardMobileMatch;
    }

    public void setBankcardMobileMatch(String bankcardMobileMatch) {
        this.bankcardMobileMatch = bankcardMobileMatch;
    }

    public String getConsumeRegionJsonArray() {
        return consumeRegionJsonArray;
    }

    public void setConsumeRegionJsonArray(String consumeRegionJsonArray) {
        this.consumeRegionJsonArray = consumeRegionJsonArray;
    }

    public int getTotalConsumeRegionCount() {
        return totalConsumeRegionCount;
    }

    public void setTotalConsumeRegionCount(int totalConsumeRegionCount) {
        this.totalConsumeRegionCount = totalConsumeRegionCount;
    }

    public int getTotalConsumeCount() {
        return totalConsumeCount;
    }

    public void setTotalConsumeCount(int totalConsumeCount) {
        this.totalConsumeCount = totalConsumeCount;
    }

    public BigDecimal getTotalConsumeAmount() {
        return totalConsumeAmount;
    }

    public void setTotalConsumeAmount(BigDecimal totalConsumeAmount) {
        this.totalConsumeAmount = totalConsumeAmount;
    }

    public int getIsMaxConsumptionCountNative() {
        return isMaxConsumptionCountNative;
    }

    public void setIsMaxConsumptionCountNative(int isMaxConsumptionCountNative) {
        this.isMaxConsumptionCountNative = isMaxConsumptionCountNative;
    }

    public int getIsMaxConsumptionAmountNative() {
        return isMaxConsumptionAmountNative;
    }

    public void setIsMaxConsumptionAmountNative(int isMaxConsumptionAmountNative) {
        this.isMaxConsumptionAmountNative = isMaxConsumptionAmountNative;
    }

    public boolean isTdRule33638Hit() {
        return tdRule33638Hit;
    }

    public void setTdRule33638Hit(boolean tdRule33638Hit) {
        this.tdRule33638Hit = tdRule33638Hit;
    }

    public boolean isTdRule33640Hit() {
        return tdRule33640Hit;
    }

    public void setTdRule33640Hit(boolean tdRule33640Hit) {
        this.tdRule33640Hit = tdRule33640Hit;
    }

    public boolean isTdRule33642Hit() {
        return tdRule33642Hit;
    }

    public void setTdRule33642Hit(boolean tdRule33642Hit) {
        this.tdRule33642Hit = tdRule33642Hit;
    }

    public boolean isTdRule33652Hit() {
        return tdRule33652Hit;
    }

    public void setTdRule33652Hit(boolean tdRule33652Hit) {
        this.tdRule33652Hit = tdRule33652Hit;
    }

    public boolean isTdRule33654Hit() {
        return tdRule33654Hit;
    }

    public void setTdRule33654Hit(boolean tdRule33654Hit) {
        this.tdRule33654Hit = tdRule33654Hit;
    }

    public boolean isTdRule33674Hit() {
        return tdRule33674Hit;
    }

    public void setTdRule33674Hit(boolean tdRule33674Hit) {
        this.tdRule33674Hit = tdRule33674Hit;
    }

    public boolean isTdRule33676Hit() {
        return tdRule33676Hit;
    }

    public void setTdRule33676Hit(boolean tdRule33676Hit) {
        this.tdRule33676Hit = tdRule33676Hit;
    }

    public int getPyJusticeCaseInfoNumber() {
        return pyJusticeCaseInfoNumber;
    }

    public void setPyJusticeCaseInfoNumber(int pyJusticeCaseInfoNumber) {
        this.pyJusticeCaseInfoNumber = pyJusticeCaseInfoNumber;
    }

    public int getPyJusticeExecuteInfoNumber() {
        return pyJusticeExecuteInfoNumber;
    }

    public void setPyJusticeExecuteInfoNumber(int pyJusticeExecuteInfoNumber) {
        this.pyJusticeExecuteInfoNumber = pyJusticeExecuteInfoNumber;
    }

    public int getPyJusticeLoseTrackInfoNumber() {
        return pyJusticeLoseTrackInfoNumber;
    }

    public void setPyJusticeLoseTrackInfoNumber(int pyJusticeLoseTrackInfoNumber) {
        this.pyJusticeLoseTrackInfoNumber = pyJusticeLoseTrackInfoNumber;
    }

    public int getPyTaxAdministrationLawInfoNumber() {
        return pyTaxAdministrationLawInfoNumber;
    }

    public void setPyTaxAdministrationLawInfoNumber(int pyTaxAdministrationLawInfoNumber) {
        this.pyTaxAdministrationLawInfoNumber = pyTaxAdministrationLawInfoNumber;
    }

    public int getPyCollectionNoticeInfoNumber() {
        return pyCollectionNoticeInfoNumber;
    }

    public void setPyCollectionNoticeInfoNumber(int pyCollectionNoticeInfoNumber) {
        this.pyCollectionNoticeInfoNumber = pyCollectionNoticeInfoNumber;
    }

    public int getPyInternetLoanOverdueInfoNumber() {
        return pyInternetLoanOverdueInfoNumber;
    }

    public void setPyInternetLoanOverdueInfoNumber(int pyInternetLoanOverdueInfoNumber) {
        this.pyInternetLoanOverdueInfoNumber = pyInternetLoanOverdueInfoNumber;
    }

    public BigDecimal getReg007WebsiteScore() {
        return reg007WebsiteScore;
    }

    public void setReg007WebsiteScore(BigDecimal reg007WebsiteScore) {
        this.reg007WebsiteScore = reg007WebsiteScore;
    }

    public boolean isReg007IsBlacklistWebsite() {
        return reg007IsBlacklistWebsite;
    }

    public void setReg007IsBlacklistWebsite(boolean reg007IsBlacklistWebsite) {
        this.reg007IsBlacklistWebsite = reg007IsBlacklistWebsite;
    }

    public boolean isHasCallLoanPhone() {
        return hasCallLoanPhone;
    }

    public void setHasCallLoanPhone(boolean hasCallLoanPhone) {
        this.hasCallLoanPhone = hasCallLoanPhone;
    }

    public boolean isHasCallFinancePhone() {
        return hasCallFinancePhone;
    }

    public void setHasCallFinancePhone(boolean hasCallFinancePhone) {
        this.hasCallFinancePhone = hasCallFinancePhone;
    }

    public boolean isHasCallJiexinPhone() {
        return hasCallJiexinPhone;
    }

    public void setHasCallJiexinPhone(boolean hasCallJiexinPhone) {
        this.hasCallJiexinPhone = hasCallJiexinPhone;
    }

    public int getContactPhoneNumberInJxlCount() {
        return contactPhoneNumberInJxlCount;
    }

    public void setContactPhoneNumberInJxlCount(int contactPhoneNumberInJxlCount) {
        this.contactPhoneNumberInJxlCount = contactPhoneNumberInJxlCount;
    }

    public int getJyIDCardPhotoCheckSimilarity() {
        return jyIDCardPhotoCheckSimilarity;
    }

    public void setJyIDCardPhotoCheckSimilarity(int jyIDCardPhotoCheckSimilarity) {
        this.jyIDCardPhotoCheckSimilarity = jyIDCardPhotoCheckSimilarity;
    }

    public int getJyHeadPhotoCheckSimilarity() {
        return jyHeadPhotoCheckSimilarity;
    }

    public void setJyHeadPhotoCheckSimilarity(int jyHeadPhotoCheckSimilarity) {
        this.jyHeadPhotoCheckSimilarity = jyHeadPhotoCheckSimilarity;
    }

    public boolean isTdRule57284Hit() {
        return tdRule57284Hit;
    }

    public void setTdRule57284Hit(boolean tdRule57284Hit) {
        this.tdRule57284Hit = tdRule57284Hit;
    }

    public boolean isJxlReportDataSuccess() {
        return jxlReportDataSuccess;
    }

    public void setJxlReportDataSuccess(boolean jxlReportDataSuccess) {
        this.jxlReportDataSuccess = jxlReportDataSuccess;
    }

    public int getTdRule33674OuterPlatformLoanAmount() {
        return tdRule33674OuterPlatformLoanAmount;
    }

    public void setTdRule33674OuterPlatformLoanAmount(int tdRule33674OuterPlatformLoanAmount) {
        this.tdRule33674OuterPlatformLoanAmount = tdRule33674OuterPlatformLoanAmount;
    }

    public int getTdRule33674LoanAmount() {
        return tdRule33674LoanAmount;
    }

    public void setTdRule33674LoanAmount(int tdRule33674LoanAmount) {
        this.tdRule33674LoanAmount = tdRule33674LoanAmount;
    }

    public int getTdRule33676OuterPlatformLoanAmount() {
        return tdRule33676OuterPlatformLoanAmount;
    }

    public void setTdRule33676OuterPlatformLoanAmount(int tdRule33676OuterPlatformLoanAmount) {
        this.tdRule33676OuterPlatformLoanAmount = tdRule33676OuterPlatformLoanAmount;
    }

    public int getTdRule33676LoanAmount() {
        return tdRule33676LoanAmount;
    }

    public void setTdRule33676LoanAmount(int tdRule33676LoanAmount) {
        this.tdRule33676LoanAmount = tdRule33676LoanAmount;
    }

    public boolean isTdFirstContactRule33638Hit() {
        return tdFirstContactRule33638Hit;
    }

    public void setTdFirstContactRule33638Hit(boolean tdFirstContactRule33638Hit) {
        this.tdFirstContactRule33638Hit = tdFirstContactRule33638Hit;
    }

    public boolean isTdFirstContactRule33652Hit() {
        return tdFirstContactRule33652Hit;
    }

    public void setTdFirstContactRule33652Hit(boolean tdFirstContactRule33652Hit) {
        this.tdFirstContactRule33652Hit = tdFirstContactRule33652Hit;
    }

    public boolean isTdFirstContactRule33654Hit() {
        return tdFirstContactRule33654Hit;
    }

    public void setTdFirstContactRule33654Hit(boolean tdFirstContactRule33654Hit) {
        this.tdFirstContactRule33654Hit = tdFirstContactRule33654Hit;
    }

    public int getTdFirstContactRule33674HitLoanAmount() {
        return tdFirstContactRule33674HitLoanAmount;
    }

    public void setTdFirstContactRule33674HitLoanAmount(int tdFirstContactRule33674HitLoanAmount) {
        this.tdFirstContactRule33674HitLoanAmount = tdFirstContactRule33674HitLoanAmount;
    }

    public int getTdFirstContactRule33674HitPlatformAmount() {
        return tdFirstContactRule33674HitPlatformAmount;
    }

    public void setTdFirstContactRule33674HitPlatformAmount(int tdFirstContactRule33674HitPlatformAmount) {
        this.tdFirstContactRule33674HitPlatformAmount = tdFirstContactRule33674HitPlatformAmount;
    }

    public boolean isTdSecondContactRule33638Hit() {
        return tdSecondContactRule33638Hit;
    }

    public void setTdSecondContactRule33638Hit(boolean tdSecondContactRule33638Hit) {
        this.tdSecondContactRule33638Hit = tdSecondContactRule33638Hit;
    }

    public boolean isTdSecondContactRule33652Hit() {
        return tdSecondContactRule33652Hit;
    }

    public void setTdSecondContactRule33652Hit(boolean tdSecondContactRule33652Hit) {
        this.tdSecondContactRule33652Hit = tdSecondContactRule33652Hit;
    }

    public boolean isTdSecondContactRule33654Hit() {
        return tdSecondContactRule33654Hit;
    }

    public void setTdSecondContactRule33654Hit(boolean tdSecondContactRule33654Hit) {
        this.tdSecondContactRule33654Hit = tdSecondContactRule33654Hit;
    }

    public int getTdSecondContactRule33674HitLoanAmount() {
        return tdSecondContactRule33674HitLoanAmount;
    }

    public void setTdSecondContactRule33674HitLoanAmount(int tdSecondContactRule33674HitLoanAmount) {
        this.tdSecondContactRule33674HitLoanAmount = tdSecondContactRule33674HitLoanAmount;
    }

    public int getTdSecondContactRule33674HitPlatformAmount() {
        return tdSecondContactRule33674HitPlatformAmount;
    }

    public void setTdSecondContactRule33674HitPlatformAmount(int tdSecondContactRule33674HitPlatformAmount) {
        this.tdSecondContactRule33674HitPlatformAmount = tdSecondContactRule33674HitPlatformAmount;
    }

    public boolean isTdRule811950Hit() {
        return tdRule811950Hit;
    }

    public void setTdRule811950Hit(boolean tdRule811950Hit) {
        this.tdRule811950Hit = tdRule811950Hit;
    }

    public boolean isTdRule811952Hit() {
        return tdRule811952Hit;
    }

    public void setTdRule811952Hit(boolean tdRule811952Hit) {
        this.tdRule811952Hit = tdRule811952Hit;
    }

    public boolean isTdRule811954Hit() {
        return tdRule811954Hit;
    }

    public void setTdRule811954Hit(boolean tdRule811954Hit) {
        this.tdRule811954Hit = tdRule811954Hit;
    }

    public boolean isTdRule811956Hit() {
        return tdRule811956Hit;
    }

    public void setTdRule811956Hit(boolean tdRule811956Hit) {
        this.tdRule811956Hit = tdRule811956Hit;
    }

    public boolean isTdFirstContactRule811954Hit() {
        return tdFirstContactRule811954Hit;
    }

    public void setTdFirstContactRule811954Hit(boolean tdFirstContactRule811954Hit) {
        this.tdFirstContactRule811954Hit = tdFirstContactRule811954Hit;
    }

    public boolean isTdSecondContactRule811954Hit() {
        return tdSecondContactRule811954Hit;
    }

    public void setTdSecondContactRule811954Hit(boolean tdSecondContactRule811954Hit) {
        this.tdSecondContactRule811954Hit = tdSecondContactRule811954Hit;
    }

    public String getYtSimilarity() {
        return ytSimilarity;
    }

    public void setYtSimilarity(String ytSimilarity) {
        this.ytSimilarity = ytSimilarity;
    }

    public String getYtSimilarityQueryDataBase() {
        return ytSimilarityQueryDataBase;
    }

    public void setYtSimilarityQueryDataBase(String ytSimilarityQueryDataBase) {
        this.ytSimilarityQueryDataBase = ytSimilarityQueryDataBase;
    }

    public String getYtSimilarityQueryIdcard() {
        return ytSimilarityQueryIdcard;
    }

    public void setYtSimilarityQueryIdcard(String ytSimilarityQueryIdcard) {
        this.ytSimilarityQueryIdcard = ytSimilarityQueryIdcard;
    }

    public String getYtSimilarityIdcardDataBase() {
        return ytSimilarityIdcardDataBase;
    }

    public void setYtSimilarityIdcardDataBase(String ytSimilarityIdcardDataBase) {
        this.ytSimilarityIdcardDataBase = ytSimilarityIdcardDataBase;
    }

    public boolean isYtSimilarityIsPass() {
        return ytSimilarityIsPass;
    }

    public void setYtSimilarityIsPass(boolean ytSimilarityIsPass) {
        this.ytSimilarityIsPass = ytSimilarityIsPass;
    }

    public String getJoIsFirstContactRealAuthenticated2() {
        return joIsFirstContactRealAuthenticated2;
    }

    public void setJoIsFirstContactRealAuthenticated2(String joIsFirstContactRealAuthenticated2) {
        this.joIsFirstContactRealAuthenticated2 = joIsFirstContactRealAuthenticated2;
    }

    public String getJoIsSecondContactRealAuthenticated2() {
        return joIsSecondContactRealAuthenticated2;
    }

    public void setJoIsSecondContactRealAuthenticated2(String joIsSecondContactRealAuthenticated2) {
        this.joIsSecondContactRealAuthenticated2 = joIsSecondContactRealAuthenticated2;
    }

	public String getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}

	public boolean isIdCardIsCourtDishonesty() {
		return idCardIsCourtDishonesty;
	}

	public void setIdCardIsCourtDishonesty(boolean idCardIsCourtDishonesty) {
		this.idCardIsCourtDishonesty = idCardIsCourtDishonesty;
	}

	public boolean isIdCardIsCourtExecuted() {
		return idCardIsCourtExecuted;
	}

	public void setIdCardIsCourtExecuted(boolean idCardIsCourtExecuted) {
		this.idCardIsCourtExecuted = idCardIsCourtExecuted;
	}

	public boolean isIdCardIsCourtClosed() {
		return idCardIsCourtClosed;
	}

	public void setIdCardIsCourtClosed(boolean idCardIsCourtClosed) {
		this.idCardIsCourtClosed = idCardIsCourtClosed;
	}

	public boolean isIdCardIsCrimeWanted() {
		return idCardIsCrimeWanted;
	}

	public void setIdCardIsCrimeWanted(boolean idCardIsCrimeWanted) {
		this.idCardIsCrimeWanted = idCardIsCrimeWanted;
	}

	public boolean isIdCardIsStudentLoan() {
		return idCardIsStudentLoan;
	}

	public void setIdCardIsStudentLoan(boolean idCardIsStudentLoan) {
		this.idCardIsStudentLoan = idCardIsStudentLoan;
	}

	public boolean isIdCardIsLoanOverdue() {
		return idCardIsLoanOverdue;
	}

	public void setIdCardIsLoanOverdue(boolean idCardIsLoanOverdue) {
		this.idCardIsLoanOverdue = idCardIsLoanOverdue;
	}

	public boolean isIdCardIsLoanOverdueRepay() {
		return idCardIsLoanOverdueRepay;
	}

	public void setIdCardIsLoanOverdueRepay(boolean idCardIsLoanOverdueRepay) {
		this.idCardIsLoanOverdueRepay = idCardIsLoanOverdueRepay;
	}

	public boolean isMobileIsFake() {
		return mobileIsFake;
	}

	public void setMobileIsFake(boolean mobileIsFake) {
		this.mobileIsFake = mobileIsFake;
	}

	public boolean isMobileIsSmall() {
		return mobileIsSmall;
	}

	public void setMobileIsSmall(boolean mobileIsSmall) {
		this.mobileIsSmall = mobileIsSmall;
	}

	public boolean isMobileIsBilk() {
		return mobileIsBilk;
	}

	public void setMobileIsBilk(boolean mobileIsBilk) {
		this.mobileIsBilk = mobileIsBilk;
	}

	public boolean isMobileIsLoanBlackMedium() {
		return mobileIsLoanBlackMedium;
	}

	public void setMobileIsLoanBlackMedium(boolean mobileIsLoanBlackMedium) {
		this.mobileIsLoanBlackMedium = mobileIsLoanBlackMedium;
	}

	public boolean isMobileIsLoanOverdue() {
		return mobileIsLoanOverdue;
	}

	public void setMobileIsLoanOverdue(boolean mobileIsLoanOverdue) {
		this.mobileIsLoanOverdue = mobileIsLoanOverdue;
	}

	public boolean isMobileIsVehicleHire() {
		return mobileIsVehicleHire;
	}

	public void setMobileIsVehicleHire(boolean mobileIsVehicleHire) {
		this.mobileIsVehicleHire = mobileIsVehicleHire;
	}

	public boolean isMobileIsOverdraftCorporate() {
		return mobileIsOverdraftCorporate;
	}

	public void setMobileIsOverdraftCorporate(boolean mobileIsOverdraftCorporate) {
		this.mobileIsOverdraftCorporate = mobileIsOverdraftCorporate;
	}

	public boolean isMobileIsLost() {
		return mobileIsLost;
	}

	public void setMobileIsLost(boolean mobileIsLost) {
		this.mobileIsLost = mobileIsLost;
	}

	public boolean isMobileIsLoanOverdueRepay() {
		return mobileIsLoanOverdueRepay;
	}

	public void setMobileIsLoanOverdueRepay(boolean mobileIsLoanOverdueRepay) {
		this.mobileIsLoanOverdueRepay = mobileIsLoanOverdueRepay;
	}

	public boolean isIdCardApplys3Month() {
		return idCardApplys3Month;
	}

	public void setIdCardApplys3Month(boolean idCardApplys3Month) {
		this.idCardApplys3Month = idCardApplys3Month;
	}

	public boolean isApplyIdCards3Month() {
		return applyIdCards3Month;
	}

	public void setApplyIdCards3Month(boolean applyIdCards3Month) {
		this.applyIdCards3Month = applyIdCards3Month;
	}

	public boolean isBankNameIdCards3Month() {
		return bankNameIdCards3Month;
	}

	public void setBankNameIdCards3Month(boolean bankNameIdCards3Month) {
		this.bankNameIdCards3Month = bankNameIdCards3Month;
	}

	public boolean isIdCardAsContactNum2() {
		return idCardAsContactNum2;
	}

	public void setIdCardAsContactNum2(boolean idCardAsContactNum2) {
		this.idCardAsContactNum2 = idCardAsContactNum2;
	}

	public boolean isMobileAsContactNum2() {
		return mobileAsContactNum2;
	}

	public void setMobileAsContactNum2(boolean mobileAsContactNum2) {
		this.mobileAsContactNum2 = mobileAsContactNum2;
	}

	public int getApplyLoanOnPlats7Cnt() {
		return applyLoanOnPlats7Cnt;
	}

	public void setApplyLoanOnPlats7Cnt(int applyLoanOnPlats7Cnt) {
		this.applyLoanOnPlats7Cnt = applyLoanOnPlats7Cnt;
	}

	public int getApplyLoanOnPlats30Cnt() {
		return applyLoanOnPlats30Cnt;
	}

	public void setApplyLoanOnPlats30Cnt(int applyLoanOnPlats30Cnt) {
		this.applyLoanOnPlats30Cnt = applyLoanOnPlats30Cnt;
	}

	public int getApplyLoanOnPlats3MonthCnt() {
		return applyLoanOnPlats3MonthCnt;
	}

	public void setApplyLoanOnPlats3MonthCnt(int applyLoanOnPlats3MonthCnt) {
		this.applyLoanOnPlats3MonthCnt = applyLoanOnPlats3MonthCnt;
	}

	public int getApplyLoanOnPlats6MonthCnt() {
		return applyLoanOnPlats6MonthCnt;
	}

	public void setApplyLoanOnPlats6MonthCnt(int applyLoanOnPlats6MonthCnt) {
		this.applyLoanOnPlats6MonthCnt = applyLoanOnPlats6MonthCnt;
	}

	public int getApplyLoanOnPlats12MonthCnt() {
		return applyLoanOnPlats12MonthCnt;
	}

	public void setApplyLoanOnPlats12MonthCnt(int applyLoanOnPlats12MonthCnt) {
		this.applyLoanOnPlats12MonthCnt = applyLoanOnPlats12MonthCnt;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(int monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public int getJobStability() {
		return jobStability;
	}

	public void setJobStability(int jobStability) {
		this.jobStability = jobStability;
	}

	public int getCashScore() {
		return cashScore;
	}

	public void setCashScore(int cashScore) {
		this.cashScore = cashScore;
	}

	public int getCashModelClassIfication() {
		return cashModelClassIfication;
	}

	public void setCashModelClassIfication(int cashModelClassIfication) {
		this.cashModelClassIfication = cashModelClassIfication;
	}

	public int getConsumerTrends() {
		return consumerTrends;
	}

	public void setConsumerTrends(int consumerTrends) {
		this.consumerTrends = consumerTrends;
	}

	public int getSpendingPower() {
		return spendingPower;
	}

	public void setSpendingPower(int spendingPower) {
		this.spendingPower = spendingPower;
	}

	public int getCustomerValue() {
		return customerValue;
	}

	public void setCustomerValue(int customerValue) {
		this.customerValue = customerValue;
	}

	public int getConsumptionSurplus() {
		return consumptionSurplus;
	}

	public void setConsumptionSurplus(int consumptionSurplus) {
		this.consumptionSurplus = consumptionSurplus;
	}

	public int getConsumptionSurplusClass() {
		return consumptionSurplusClass;
	}

	public void setConsumptionSurplusClass(int consumptionSurplusClass) {
		this.consumptionSurplusClass = consumptionSurplusClass;
	}

	public int getConsumptionPreferences() {
		return consumptionPreferences;
	}

	public void setConsumptionPreferences(int consumptionPreferences) {
		this.consumptionPreferences = consumptionPreferences;
	}

	public int getConsumptionStability() {
		return consumptionStability;
	}

	public void setConsumptionStability(int consumptionStability) {
		this.consumptionStability = consumptionStability;
	}

	public boolean isKeepCard() {
		return keepCard;
	}

	public void setKeepCard(boolean keepCard) {
		this.keepCard = keepCard;
	}

	public int getCallStability() {
		return callStability;
	}

	public void setCallStability(int callStability) {
		this.callStability = callStability;
	}

	public int getZmxyScore() {
		return zmxyScore;
	}

	public void setZmxyScore(int zmxyScore) {
		this.zmxyScore = zmxyScore;
	}

	public String getFushuHitCondition() {
		return fushuHitCondition;
	}

	public void setFushuHitCondition(String fushuHitCondition) {
		this.fushuHitCondition = fushuHitCondition;
	}

	public boolean isFushuInBlackList() {
		return fushuInBlackList;
	}

	public void setFushuInBlackList(boolean fushuInBlackList) {
		this.fushuInBlackList = fushuInBlackList;
	}
	
	public boolean isFushuInBlackListOfCourt() {
		return fushuInBlackListOfCourt;
	}

	public void setFushuInBlackListOfCourt(boolean fushuInBlackListOfCourt) {
		this.fushuInBlackListOfCourt = fushuInBlackListOfCourt;
	}

	public boolean isFushuInBlackListOfFinance() {
		return fushuInBlackListOfFinance;
	}

	public void setFushuInBlackListOfFinance(boolean fushuInBlackListOfFinance) {
		this.fushuInBlackListOfFinance = fushuInBlackListOfFinance;
	}

	public String getFushuType() {
		return fushuType;
	}

	public void setFushuType(String fushuType) {
		this.fushuType = fushuType;
	}

	public String getFushuDuty() {
		return fushuDuty;
	}

	public void setFushuDuty(String fushuDuty) {
		this.fushuDuty = fushuDuty;
	}

	public String getFushuRegisterDate() {
		return fushuRegisterDate;
	}

	public void setFushuRegisterDate(String fushuRegisterDate) {
		this.fushuRegisterDate = fushuRegisterDate;
	}

	public String getFushuDate() {
		return fushuDate;
	}

	public void setFushuDate(String fushuDate) {
		this.fushuDate = fushuDate;
	}

	public String getFushuLevel() {
		return fushuLevel;
	}

	public void setFushuLevel(String fushuLevel) {
		this.fushuLevel = fushuLevel;
	}

	public String getFushuDetail() {
		return fushuDetail;
	}

	public void setFushuDetail(String fushuDetail) {
		this.fushuDetail = fushuDetail;
	}

	public String getFushuUpdatetime() {
		return fushuUpdatetime;
	}

	public void setFushuUpdatetime(String fushuUpdatetime) {
		this.fushuUpdatetime = fushuUpdatetime;
	}

	public String getBQS_FINALDECISION() {
		return BQS_FINALDECISION;
	}

	public void setBQS_FINALDECISION(String bQS_FINALDECISION) {
		BQS_FINALDECISION = bQS_FINALDECISION;
	}

	public String getKaolaBorrowDate() {
		return kaolaBorrowDate;
	}

	public void setKaolaBorrowDate(String kaolaBorrowDate) {
		this.kaolaBorrowDate = kaolaBorrowDate;
	}

	public String getKaolaBorrowAmount() {
		return kaolaBorrowAmount;
	}

	public void setKaolaBorrowAmount(String kaolaBorrowAmount) {
		this.kaolaBorrowAmount = kaolaBorrowAmount;
	}

	public String getKaolaBorrowPeriod() {
		return kaolaBorrowPeriod;
	}

	public void setKaolaBorrowPeriod(String kaolaBorrowPeriod) {
		this.kaolaBorrowPeriod = kaolaBorrowPeriod;
	}

	public String getKaolaOverdueDate() {
		return kaolaOverdueDate;
	}

	public void setKaolaOverdueDate(String kaolaOverdueDate) {
		this.kaolaOverdueDate = kaolaOverdueDate;
	}

	public String getKaolaOverAmount() {
		return kaolaOverAmount;
	}

	public void setKaolaOverAmount(String kaolaOverAmount) {
		this.kaolaOverAmount = kaolaOverAmount;
	}

	public String getKaolaOverLevel() {
		return kaolaOverLevel;
	}

	public void setKaolaOverLevel(String kaolaOverLevel) {
		this.kaolaOverLevel = kaolaOverLevel;
	}

	public String getKaoLaIdentityIdCardName() {
		return kaoLaIdentityIdCardName;
	}

	public void setKaoLaIdentityIdCardName(String kaoLaIdentityIdCardName) {
		this.kaoLaIdentityIdCardName = kaoLaIdentityIdCardName;
	}

	public String getKaoLaIdentityIdCardCode() {
		return kaoLaIdentityIdCardCode;
	}

	public void setKaoLaIdentityIdCardCode(String kaoLaIdentityIdCardCode) {
		this.kaoLaIdentityIdCardCode = kaoLaIdentityIdCardCode;
	}

	public String getKaoLaIdentityTimeInterval() {
		return kaoLaIdentityTimeInterval;
	}

	public void setKaoLaIdentityTimeInterval(String kaoLaIdentityTimeInterval) {
		this.kaoLaIdentityTimeInterval = kaoLaIdentityTimeInterval;
	}

	public String getKaoLaIdentityToTalTransNum() {
		return kaoLaIdentityToTalTransNum;
	}

	public void setKaoLaIdentityToTalTransNum(String kaoLaIdentityToTalTransNum) {
		this.kaoLaIdentityToTalTransNum = kaoLaIdentityToTalTransNum;
	}

	public String getKaoLaIdentityLastTransTime() {
		return kaoLaIdentityLastTransTime;
	}

	public void setKaoLaIdentityLastTransTime(String kaoLaIdentityLastTransTime) {
		this.kaoLaIdentityLastTransTime = kaoLaIdentityLastTransTime;
	}

	public String getKaoLaIdentityLastIndustry() {
		return kaoLaIdentityLastIndustry;
	}

	public void setKaoLaIdentityLastIndustry(String kaoLaIdentityLastIndustry) {
		this.kaoLaIdentityLastIndustry = kaoLaIdentityLastIndustry;
	}

	public String getKaoLaIdentityLastPrdGrpName() {
		return kaoLaIdentityLastPrdGrpName;
	}

	public void setKaoLaIdentityLastPrdGrpName(String kaoLaIdentityLastPrdGrpName) {
		this.kaoLaIdentityLastPrdGrpName = kaoLaIdentityLastPrdGrpName;
	}

	public String getKaoLaIdentityToTalprdGrpNum() {
		return kaoLaIdentityToTalprdGrpNum;
	}

	public void setKaoLaIdentityToTalprdGrpNum(String kaoLaIdentityToTalprdGrpNum) {
		this.kaoLaIdentityToTalprdGrpNum = kaoLaIdentityToTalprdGrpNum;
	}

	@Override
	public String toString() {
		return "ThirdpartyDataInForm [ytSimilarity=" + ytSimilarity + ", ytSimilarityQueryDataBase="
				+ ytSimilarityQueryDataBase + ", ytSimilarityQueryIdcard=" + ytSimilarityQueryIdcard
				+ ", ytSimilarityIdcardDataBase=" + ytSimilarityIdcardDataBase + ", ytSimilarityIsPass="
				+ ytSimilarityIsPass + ", jxlReprotExist=" + jxlReprotExist + ", JXLReportStatus=" + JXLReportStatus
				+ ", jxlReportDataSuccess=" + jxlReportDataSuccess + ", contactRegionCount=" + contactRegionCount
				+ ", contactCount=" + contactCount + ", callOutLength=" + callOutLength + ", callOutCount="
				+ callOutCount + ", callInLength=" + callInLength + ", callInCount=" + callInCount
				+ ", dataExistMonthCount=" + dataExistMonthCount + ", contactNumberCount=" + contactNumberCount
				+ ", realAuthencated=" + realAuthencated + ", providerInfoMatch=" + providerInfoMatch
				+ ", alwaysPowerOff=" + alwaysPowerOff + ", newNumber=" + newNumber + ", hasCallLoanPhone="
				+ hasCallLoanPhone + ", hasCallFinancePhone=" + hasCallFinancePhone + ", hasCallJiexinPhone="
				+ hasCallJiexinPhone + ", contactPhoneNumberInJxlCount=" + contactPhoneNumberInJxlCount
				+ ", bankcardMobileMatch=" + bankcardMobileMatch + ", consumeRegionJsonArray=" + consumeRegionJsonArray
				+ ", totalConsumeRegionCount=" + totalConsumeRegionCount + ", totalConsumeCount=" + totalConsumeCount
				+ ", totalConsumeAmount=" + totalConsumeAmount + ", isMaxConsumptionCountNative="
				+ isMaxConsumptionCountNative + ", isMaxConsumptionAmountNative=" + isMaxConsumptionAmountNative
				+ ", tdRule33638Hit=" + tdRule33638Hit + ", tdRule33640Hit=" + tdRule33640Hit + ", tdRule33642Hit="
				+ tdRule33642Hit + ", tdRule33652Hit=" + tdRule33652Hit + ", tdRule33654Hit=" + tdRule33654Hit
				+ ", tdRule33674Hit=" + tdRule33674Hit + ", tdRule33676Hit=" + tdRule33676Hit + ", tdRule57284Hit="
				+ tdRule57284Hit + ", tdRule33674OuterPlatformLoanAmount=" + tdRule33674OuterPlatformLoanAmount
				+ ", tdRule33674LoanAmount=" + tdRule33674LoanAmount + ", tdRule33676OuterPlatformLoanAmount="
				+ tdRule33676OuterPlatformLoanAmount + ", tdRule33676LoanAmount=" + tdRule33676LoanAmount
				+ ", tdFirstContactRule33638Hit=" + tdFirstContactRule33638Hit + ", tdFirstContactRule33652Hit="
				+ tdFirstContactRule33652Hit + ", tdFirstContactRule33654Hit=" + tdFirstContactRule33654Hit
				+ ", tdFirstContactRule33674HitLoanAmount=" + tdFirstContactRule33674HitLoanAmount
				+ ", tdFirstContactRule33674HitPlatformAmount=" + tdFirstContactRule33674HitPlatformAmount
				+ ", tdSecondContactRule33638Hit=" + tdSecondContactRule33638Hit + ", tdSecondContactRule33652Hit="
				+ tdSecondContactRule33652Hit + ", tdSecondContactRule33654Hit=" + tdSecondContactRule33654Hit
				+ ", tdSecondContactRule33674HitLoanAmount=" + tdSecondContactRule33674HitLoanAmount
				+ ", tdSecondContactRule33674HitPlatformAmount=" + tdSecondContactRule33674HitPlatformAmount
				+ ", tdRule811950Hit=" + tdRule811950Hit + ", tdRule811952Hit=" + tdRule811952Hit + ", tdRule811954Hit="
				+ tdRule811954Hit + ", tdRule811956Hit=" + tdRule811956Hit + ", tdFirstContactRule811954Hit="
				+ tdFirstContactRule811954Hit + ", tdSecondContactRule811954Hit=" + tdSecondContactRule811954Hit
				+ ", pyJusticeCaseInfoNumber=" + pyJusticeCaseInfoNumber + ", pyJusticeExecuteInfoNumber="
				+ pyJusticeExecuteInfoNumber + ", pyJusticeLoseTrackInfoNumber=" + pyJusticeLoseTrackInfoNumber
				+ ", pyTaxAdministrationLawInfoNumber=" + pyTaxAdministrationLawInfoNumber
				+ ", pyCollectionNoticeInfoNumber=" + pyCollectionNoticeInfoNumber
				+ ", pyInternetLoanOverdueInfoNumber=" + pyInternetLoanOverdueInfoNumber + ", reg007WebsiteScore="
				+ reg007WebsiteScore + ", reg007IsBlacklistWebsite=" + reg007IsBlacklistWebsite
				+ ", jyIDCardPhotoCheckSimilarity=" + jyIDCardPhotoCheckSimilarity + ", jyHeadPhotoCheckSimilarity="
				+ jyHeadPhotoCheckSimilarity + ", br_SpecialListIdBankBad=" + br_SpecialListIdBankBad
				+ ", br_SpecialListIdBankOverdue=" + br_SpecialListIdBankOverdue + ", br_SpecialListIdBankFraud="
				+ br_SpecialListIdBankFraud + ", br_SpecialListIdBankLost=" + br_SpecialListIdBankLost
				+ ", br_SpecialListIdBankRefuse=" + br_SpecialListIdBankRefuse + ", br_SpecialListIdCreditBad="
				+ br_SpecialListIdCreditBad + ", br_SpecialListIdP2PBad=" + br_SpecialListIdP2PBad
				+ ", br_SpecialListIdP2POverdue=" + br_SpecialListIdP2POverdue + ", br_SpecialListIdP2PFraud="
				+ br_SpecialListIdP2PFraud + ", br_SpecialListIdP2PLost=" + br_SpecialListIdP2PLost
				+ ", br_SpecialListIdP2PRefuse=" + br_SpecialListIdP2PRefuse + ", br_SpecialListIdPhoneOverdue="
				+ br_SpecialListIdPhoneOverdue + ", br_SpecialListIdInsFraud=" + br_SpecialListIdInsFraud
				+ ", br_SpecialListIdCourtBad=" + br_SpecialListIdCourtBad + ", br_SpecialListIdCourtExecuted="
				+ br_SpecialListIdCourtExecuted + ", br_SpecialListCellBankBad=" + br_SpecialListCellBankBad
				+ ", br_SpecialListCellBankOverdue=" + br_SpecialListCellBankOverdue + ", br_SpecialListCellBankFraud="
				+ br_SpecialListCellBankFraud + ", br_SpecialListCellBankLost=" + br_SpecialListCellBankLost
				+ ", br_SpecialListCellBankRefuse=" + br_SpecialListCellBankRefuse + ", br_SpecialListCellP2PBad="
				+ br_SpecialListCellP2PBad + ", br_SpecialListCellP2POverdue=" + br_SpecialListCellP2POverdue
				+ ", br_SpecialListCellP2PFraud=" + br_SpecialListCellP2PFraud + ", br_SpecialListCellP2PLost="
				+ br_SpecialListCellP2PLost + ", br_SpecialListCellP2PRefuse=" + br_SpecialListCellP2PRefuse
				+ ", br_SpecialListCellPhoneOverdue=" + br_SpecialListCellPhoneOverdue + ", br_SpecialListCellInsFraud="
				+ br_SpecialListCellInsFraud + ", br_RJ_Rule=" + br_RJ_Rule + ", br_sl_id=" + br_sl_id + ", br_sl_cell="
				+ br_sl_cell + ", joLengthOfNetwork=" + joLengthOfNetwork + ", joIsFirstContactRealAuthenticated2="
				+ joIsFirstContactRealAuthenticated2 + ", joIsSecondContactRealAuthenticated2="
				+ joIsSecondContactRealAuthenticated2 + ", joIsRealAuthenticated3=" + joIsRealAuthenticated3
				+ ", joIsRealAuthenticated2=" + joIsRealAuthenticated2 + ", baiduScoreType=" + baiduScoreType
				+ ", baiduMdxUsmbl=" + baiduMdxUsmbl + ", finalDecision=" + finalDecision + ", idCardIsCourtDishonesty="
				+ idCardIsCourtDishonesty + ", idCardIsCourtExecuted=" + idCardIsCourtExecuted
				+ ", idCardIsCourtClosed=" + idCardIsCourtClosed + ", idCardIsCrimeWanted=" + idCardIsCrimeWanted
				+ ", idCardIsStudentLoan=" + idCardIsStudentLoan + ", idCardIsLoanOverdue=" + idCardIsLoanOverdue
				+ ", idCardIsLoanOverdueRepay=" + idCardIsLoanOverdueRepay + ", mobileIsFake=" + mobileIsFake
				+ ", mobileIsSmall=" + mobileIsSmall + ", mobileIsBilk=" + mobileIsBilk + ", mobileIsLoanBlackMedium="
				+ mobileIsLoanBlackMedium + ", mobileIsLoanOverdue=" + mobileIsLoanOverdue + ", mobileIsVehicleHire="
				+ mobileIsVehicleHire + ", mobileIsOverdraftCorporate=" + mobileIsOverdraftCorporate + ", mobileIsLost="
				+ mobileIsLost + ", mobileIsLoanOverdueRepay=" + mobileIsLoanOverdueRepay + ", idCardApplys3Month="
				+ idCardApplys3Month + ", applyIdCards3Month=" + applyIdCards3Month + ", bankNameIdCards3Month="
				+ bankNameIdCards3Month + ", idCardAsContactNum2=" + idCardAsContactNum2 + ", mobileAsContactNum2="
				+ mobileAsContactNum2 + ", applyLoanOnPlats7Cnt=" + applyLoanOnPlats7Cnt + ", applyLoanOnPlats30Cnt="
				+ applyLoanOnPlats30Cnt + ", applyLoanOnPlats3MonthCnt=" + applyLoanOnPlats3MonthCnt
				+ ", applyLoanOnPlats6MonthCnt=" + applyLoanOnPlats6MonthCnt + ", applyLoanOnPlats12MonthCnt="
				+ applyLoanOnPlats12MonthCnt + ", score=" + score + ", monthlyIncome=" + monthlyIncome
				+ ", jobStability=" + jobStability + ", cashScore=" + cashScore + ", cashModelClassIfication="
				+ cashModelClassIfication + ", consumerTrends=" + consumerTrends + ", spendingPower=" + spendingPower
				+ ", customerValue=" + customerValue + ", consumptionSurplus=" + consumptionSurplus
				+ ", consumptionSurplusClass=" + consumptionSurplusClass + ", consumptionPreferences="
				+ consumptionPreferences + ", consumptionStability=" + consumptionStability + ", keepCard=" + keepCard
				+ ", callStability=" + callStability + ", zmxyScore=" + zmxyScore + ", fushuHitCondition="
				+ fushuHitCondition + ", fushuInBlackList=" + fushuInBlackList + ", fushuType=" + fushuType
				+ ", fushuDuty=" + fushuDuty + ", fushuRegisterDate=" + fushuRegisterDate + ", fushuDate=" + fushuDate
				+ ", fushuLevel=" + fushuLevel + ", fushuDetail=" + fushuDetail + ", fushuUpdatetime=" + fushuUpdatetime
				+ ", BQS_FINALDECISION=" + BQS_FINALDECISION + ", kaolaBorrowDate=" + kaolaBorrowDate
				+ ", kaolaBorrowAmount=" + kaolaBorrowAmount + ", kaolaBorrowPeriod=" + kaolaBorrowPeriod
				+ ", kaolaOverdueDate=" + kaolaOverdueDate + ", kaolaOverAmount=" + kaolaOverAmount
				+ ", kaolaOverLevel=" + kaolaOverLevel + ", kaoLaIdentityIdCardName=" + kaoLaIdentityIdCardName
				+ ", kaoLaIdentityIdCardCode=" + kaoLaIdentityIdCardCode + ", kaoLaIdentityTimeInterval="
				+ kaoLaIdentityTimeInterval + ", kaoLaIdentityToTalTransNum=" + kaoLaIdentityToTalTransNum
				+ ", kaoLaIdentityLastTransTime=" + kaoLaIdentityLastTransTime + ", kaoLaIdentityLastIndustry="
				+ kaoLaIdentityLastIndustry + ", kaoLaIdentityLastPrdGrpName=" + kaoLaIdentityLastPrdGrpName
				+ ", kaoLaIdentityToTalprdGrpNum=" + kaoLaIdentityToTalprdGrpNum + "]";
	}

}
