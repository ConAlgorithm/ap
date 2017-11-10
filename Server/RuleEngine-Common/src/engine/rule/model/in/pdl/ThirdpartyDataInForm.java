package engine.rule.model.in.pdl;

import java.math.BigDecimal;

import catfish.base.business.common.JOLengthOfNetwork;
import catfish.base.business.common.JORealAuthenticated2;
import catfish.base.business.common.JORealAuthenticated3;
import catfish.base.business.common.jxl.CheckPointResult;
import catfish.base.business.common.jxl.RequestStatus;
import catfish.base.business.common.ylzh.ConsumeRegionNative;
import catfish.base.business.common.ylzh.ResponseState;
import catfish.base.business.util.AppDerivativeVariableNames;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "第三方数据信息")
public class ThirdpartyDataInForm extends BaseForm{

	/********************聚信力***********************/
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
	private String realAuthencated  = CheckPointResult.NoData.getValue();
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_PROVIDER_INFO_MATCH)
	@ModelField(name = "运营商信息是否匹配(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
	private String providerInfoMatch  = CheckPointResult.NoData.getValue();
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_ALWAYS_POWEROFF)
	@ModelField(name = "是否长时间关机(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
	private String alwaysPowerOff  = CheckPointResult.NoData.getValue();
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JXL_REPORTDATA_IS_NEW_NUMBER)
	@ModelField(name = "绑定号码是否为新号(默认值无)", bindDomain = "engine.rule.domain.jxl.CheckPointResult")
	private String newNumber  = CheckPointResult.NoData.getValue();
	
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
	public boolean isJXLReportExist()
	{
		return this.JXLReportStatus == RequestStatus.Success.getValue() && this.jxlReportDataSuccess;
	}
	/********************************************/
	/************银联智惠***********************/
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.YLZH_BANKCARD_MOBILE_MATCH)
	@ModelField(name = "银联智惠中银行卡和预留电话是否匹配(默认值数据为空)", bindDomain = "engine.rule.domain.ResponseState")
	private String bankcardMobileMatch = ResponseState.EmptyData.getValue();

	//消费区域列表（城市）
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.YLZH_CONSUMEREGION_LIST)
	private String consumeRegionJsonArray;
	
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
	
	/****************同盾*******************/
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

	/*****************************************/	
	/**************REG007*********************/
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.REG007_WEBSITESCORE)
	@ModelField(name = "REG007_ 网站得分(默认值0)")
	private BigDecimal reg007WebsiteScore  = new BigDecimal(0);
	
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.REG007_ISBLACKLISTWEBSITE)
	@ModelField(name = "REG007_是否是黑名单网站")
	private boolean reg007IsBlacklistWebsite = false;
	/*****************************************/
	/************骏聿*************************/
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JY_IDPHOTO_SIMILARITY)
	@ModelField(name = "骏聿_身份证照和公安部照对比相似度(默认值101,表示空)")
	private int jyIDCardPhotoCheckSimilarity = 101;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.JY_HEADPHOTO_SIMILARITY)
	@ModelField(name = "骏聿_身份证照和现场照对比相似度(默认值101,表示空)")
	private int jyHeadPhotoCheckSimilarity = 101;
	/*****************************************/
	
	
	@DBField(fieldName = DerivativeVariableApi.StringValue , variableType = AppDerivativeVariableNames.JO_LengthOfNetwork)
	@ModelField(name = "集奥在网时长",  bindDomain = "engine.rule.domain.JOLengthOfNetworkResult")
	private String joLengthOfNetwork = JOLengthOfNetwork.NoneRecord.getValue();
	/*****************************************/
	/************骏聿*************************/
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsRealAuthenticated3)
	@ModelField(name = "集奥三要素验证",  bindDomain = "engine.rule.domain.JORealAuthenticated3Result")
	private String joIsRealAuthenticated3 = JORealAuthenticated3.NoneRecord.getValue();
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsRealAuthenticated2)
	@ModelField(name = "集奥二要素验证",  bindDomain = "engine.rule.domain.JORealAuthenticated2Result")
	private String joIsRealAuthenticated2 = JORealAuthenticated2.NoneRecord.getValue();

	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsFirstContactRealAuthenticated2)
	@ModelField(name = "集奥第一联系人二要素验证",  bindDomain = "engine.rule.domain.JORealAuthenticated2Result")
	private String joIsFirstContactRealAuthenticated2 = JORealAuthenticated2.NoneRecord.getValue();
	
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.JO_IsSecondContactRealAuthenticated2)
	@ModelField(name = "集奥第二联系人二要素验证",  bindDomain = "engine.rule.domain.JORealAuthenticated2Result")
	private String joIsSecondContactRealAuthenticated2 = JORealAuthenticated2.NoneRecord.getValue();
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.FirstContactType)
	@ModelField(name = "与第一联系人关系",  bindDomain = "engine.rule.domain.ContactPersonRelationResult")
	private int firstContactRelation = -1;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.IsFirstContactSameSunName)
	@ModelField(name = "与第一联系人姓氏是否相同")
	private boolean firstContactSunNameSame = false;
	
	
	public int getFirstContactRelation() {
		return firstContactRelation;
	}

	public void setFirstContactRelation(int firstContactRelation) {
		this.firstContactRelation = firstContactRelation;
	}

	public boolean isFirstContactSunNameSame() {
		return firstContactSunNameSame;
	}

	public void setFirstContactSunNameSame(boolean firstContactSunNameSame) {
		this.firstContactSunNameSame = firstContactSunNameSame;
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

	public void setTdRule33674OuterPlatformLoanAmount(
			int tdRule33674OuterPlatformLoanAmount) {
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

	public void setTdRule33676OuterPlatformLoanAmount(
			int tdRule33676OuterPlatformLoanAmount) {
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
	
	public void setTdFirstContactRule33674HitLoanAmount(
	  int tdFirstContactRule33674HitLoanAmount) {
		this.tdFirstContactRule33674HitLoanAmount = tdFirstContactRule33674HitLoanAmount;
	}
	
	public int getTdFirstContactRule33674HitPlatformAmount() {
		return tdFirstContactRule33674HitPlatformAmount;
	}
	
	public void setTdFirstContactRule33674HitPlatformAmount(
	  int tdFirstContactRule33674HitPlatformAmount) {
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
	
	public void setTdSecondContactRule33674HitLoanAmount(
	  int tdSecondContactRule33674HitLoanAmount) {
		this.tdSecondContactRule33674HitLoanAmount = tdSecondContactRule33674HitLoanAmount;
	}
	
	public int getTdSecondContactRule33674HitPlatformAmount() {
		return tdSecondContactRule33674HitPlatformAmount;
	}
	
	public void setTdSecondContactRule33674HitPlatformAmount(
	  int tdSecondContactRule33674HitPlatformAmount) {
		this.tdSecondContactRule33674HitPlatformAmount = tdSecondContactRule33674HitPlatformAmount;
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
	
}
