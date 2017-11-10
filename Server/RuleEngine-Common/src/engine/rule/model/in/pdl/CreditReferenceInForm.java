package engine.rule.model.in.pdl;

import java.math.BigDecimal;
import java.util.Date;

import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.ylzh.ConsumeRegionNative;
import catfish.base.business.common.ylzh.ResponseState;
import catfish.base.business.util.AppDerivativeVariableNames;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "征信信息")
public class CreditReferenceInForm extends BaseForm {
	// finished
	// 在creator中手动填写
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.CheckBankReferenceResult)
	@ModelField(name = "人行征信结果", bindDomain = "engine.rule.domain.BankReferenceResult")
	private Integer bankReference = -99;

	/************* 征信评分卡 **************/
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.CreditReferenceQuestion)
	private String creditReferQuestions = "";
	/**********************************/

	@ModelField(name = "是否包含问题B")
	private boolean and_b;

	@ModelField(name = "是否包含问题H")
	private boolean and_h;

	@ModelField(name = "是否包含问题R")
	private boolean and_r;

	@ModelField(name = "是否包含问题EF")
	private boolean and_ef;

	@ModelField(name = "是否包含问题FHJ")
	private boolean and_fhj;

	@ModelField(name = "是否包含所有问题")
	private boolean or_all;

	@ModelField(name = "是否包含问题AGIKLMNOPTUVWX中的一个")
	private boolean or_agiklmnoptuvwx;

	@ModelField(name = "是否包含问题BHJQRS中的一个")
	private boolean or_bhjqrs;

	/************** 前海征信 **********************/
	@DBField(fieldName = DerivativeVariableApi.DateTimeValue, variableType = AppDerivativeVariableNames.QHZX_DATA_LATEST_BUILDTIME)
	@ModelField(name = "前海征信中最近的黑名单创建时间")
	private Date latestDataBuildTime;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HASBEENEXECUTED)
	@ModelField(name = "前海征信中是否是被执行人")
	private boolean hasBeenExecuted;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_BREAKPROMISE)
	@ModelField(name = "前海征信中是否有违约记录")
	private boolean breakPromise;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_BREAKPROMISEBEENEXECUTED)
	@ModelField(name = "前海征信中是否是失信被执行人")
	private boolean breakPromiseBeenExecuted;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.QHZX_MAXIMIZEOVERDUEDAY)
	@ModelField(name = "前海征信中最严重逾期时间", bindDomain = "engine.rule.domain.CheckUserRiskRevealResult")
	private int maximizeOverdueDay = -99;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.QHZX_MAXIMIZEMONEYBOUND)
	@ModelField(name = "前海征信中最高涉足金额范围")
	private int maximizeMoneyBound = -99;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HASBLACKLIST)
	@ModelField(name = "前海征信中是否有征信记录")
	private boolean hasBlackList;

	// v2.0接口返回中衍生
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HAS_CREDIT_OVERDUE_RISK)
	@ModelField(name = "前海征信中是否有信贷逾期风险")
	private boolean hasCreditOverdueRisk;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HAS_ADMINISTRATION_NEGATIVE_RISK)
	@ModelField(name = "前海征信中是否有行政负面风险")
	private boolean hasAdministrationNegativeRisk;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HAS_FRAUD_RISK)
	@ModelField(name = "前海征信中是否有欺诈风险")
	private boolean hasFraudRisk;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.QHZX_RISK_SCORE)
	@ModelField(name = "前海征信中风险得分(10-45)")
	private int riskScore;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HAS_SERIOUS_TRAFFIC_VIOLATION_RISK)
	@ModelField(name = "前海征信中是否有交通严重违章")
	private boolean hasSeriousTrafficViolationRisk;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HAS_MOBILE_FRAUD_RISK)
	@ModelField(name = "前海征信中是否有手机号存在欺诈风险")
	private boolean hasMobileFraudRisk;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HAS_BANK_CARD_FRAUD_RISK)
	@ModelField(name = "前海征信中是否有卡号存在欺诈风险")
	private boolean hasBankCardFraudRisk;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HAS_ID_CARD_FRAUD_RISK)
	@ModelField(name = "前海征信中是否有身份证号存在欺诈风险")
	private boolean hasIdCardFraudRisk;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HAS_IP_ADDRESS_FRAUD_RISK)
	@ModelField(name = "前海征信中是否有IP存在欺诈风险")
	private boolean hasIPAddressFraudRisk;

	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.QHZX_DATA_STATUS)
	@ModelField(name = "数据状态（99-权限不足，1-已验证，2-待验证，3-异议中）")
	private String dataStatus;

	/*******************************************/

	public Integer getBankReference() {
		return bankReference;
	}

	public void setBankReference(Integer bankReference) {
		this.bankReference = bankReference;
	}

	public String getCreditReferQuestions() {
		return creditReferQuestions;
	}

	public void setCreditReferQuestions(String creditReferQuestions) {
		this.creditReferQuestions = creditReferQuestions;
	}

	public boolean isAnd_b() {
		return and_b;
	}

	public void setAnd_b(boolean and_b) {
		this.and_b = and_b;
	}

	public boolean isAnd_h() {
		return and_h;
	}

	public void setAnd_h(boolean and_h) {
		this.and_h = and_h;
	}

	public boolean isAnd_r() {
		return and_r;
	}

	public void setAnd_r(boolean and_r) {
		this.and_r = and_r;
	}

	public boolean isAnd_ef() {
		return and_ef;
	}

	public void setAnd_ef(boolean and_ef) {
		this.and_ef = and_ef;
	}

	public boolean isAnd_fhj() {
		return and_fhj;
	}

	public void setAnd_fhj(boolean and_fhj) {
		this.and_fhj = and_fhj;
	}

	public boolean isOr_all() {
		return or_all;
	}

	public void setOr_all(boolean or_all) {
		this.or_all = or_all;
	}

	public boolean isOr_agiklmnoptuvwx() {
		return or_agiklmnoptuvwx;
	}

	public void setOr_agiklmnoptuvwx(boolean or_agiklmnoptuvwx) {
		this.or_agiklmnoptuvwx = or_agiklmnoptuvwx;
	}

	public boolean isOr_bhjqrs() {
		return or_bhjqrs;
	}

	public void setOr_bhjqrs(boolean or_bhjqrs) {
		this.or_bhjqrs = or_bhjqrs;
	}

	public Date getLatestDataBuildTime() {
		return latestDataBuildTime;
	}

	public void setLatestDataBuildTime(Date latestDataBuildTime) {
		this.latestDataBuildTime = latestDataBuildTime;
	}

	public boolean isHasBeenExecuted() {
		return hasBeenExecuted;
	}

	public void setHasBeenExecuted(boolean hasBeenExecuted) {
		this.hasBeenExecuted = hasBeenExecuted;
	}

	public boolean isBreakPromise() {
		return breakPromise;
	}

	public void setBreakPromise(boolean breakPromise) {
		this.breakPromise = breakPromise;
	}

	public boolean isBreakPromiseBeenExecuted() {
		return breakPromiseBeenExecuted;
	}

	public void setBreakPromiseBeenExecuted(boolean breakPromiseBeenExecuted) {
		this.breakPromiseBeenExecuted = breakPromiseBeenExecuted;
	}

	public int getMaximizeOverdueDay() {
		return maximizeOverdueDay;
	}

	public void setMaximizeOverdueDay(int maximizeOverdueDay) {
		this.maximizeOverdueDay = maximizeOverdueDay;
	}

	public int getMaximizeMoneyBound() {
		return maximizeMoneyBound;
	}

	public void setMaximizeMoneyBound(int maximizeMoneyBound) {
		this.maximizeMoneyBound = maximizeMoneyBound;
	}

	public boolean isHasBlackList() {
		return hasBlackList;
	}

	public void setHasBlackList(boolean hasBlackList) {
		this.hasBlackList = hasBlackList;
	}

	public boolean isHasCreditOverdueRisk() {
		return hasCreditOverdueRisk;
	}

	public void setHasCreditOverdueRisk(boolean hasCreditOverdueRisk) {
		this.hasCreditOverdueRisk = hasCreditOverdueRisk;
	}

	public boolean isHasAdministrationNegativeRisk() {
		return hasAdministrationNegativeRisk;
	}

	public void setHasAdministrationNegativeRisk(boolean hasAdministrationNegativeRisk) {
		this.hasAdministrationNegativeRisk = hasAdministrationNegativeRisk;
	}

	public boolean isHasFraudRisk() {
		return hasFraudRisk;
	}

	public void setHasFraudRisk(boolean hasFraudRisk) {
		this.hasFraudRisk = hasFraudRisk;
	}

	public int getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(int riskScore) {
		this.riskScore = riskScore;
	}

	public boolean isHasSeriousTrafficViolationRisk() {
		return hasSeriousTrafficViolationRisk;
	}

	public void setHasSeriousTrafficViolationRisk(boolean hasSeriousTrafficViolationRisk) {
		this.hasSeriousTrafficViolationRisk = hasSeriousTrafficViolationRisk;
	}

	public boolean isHasMobileFraudRisk() {
		return hasMobileFraudRisk;
	}

	public void setHasMobileFraudRisk(boolean hasMobileFraudRisk) {
		this.hasMobileFraudRisk = hasMobileFraudRisk;
	}

	public boolean isHasBankCardFraudRisk() {
		return hasBankCardFraudRisk;
	}

	public void setHasBankCardFraudRisk(boolean hasBankCardFraudRisk) {
		this.hasBankCardFraudRisk = hasBankCardFraudRisk;
	}

	public boolean isHasIdCardFraudRisk() {
		return hasIdCardFraudRisk;
	}

	public void setHasIdCardFraudRisk(boolean hasIdCardFraudRisk) {
		this.hasIdCardFraudRisk = hasIdCardFraudRisk;
	}

	public boolean isHasIPAddressFraudRisk() {
		return hasIPAddressFraudRisk;
	}

	public void setHasIPAddressFraudRisk(boolean hasIPAddressFraudRisk) {
		this.hasIPAddressFraudRisk = hasIPAddressFraudRisk;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
}
