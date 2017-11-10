package engine.rule.model.in.pdl;

import java.util.Date;

import catfish.base.business.common.BillStatusForFCResult;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "历史表现信息")
public class HistoricalPerformanceInForm extends BaseForm {
	
	public static final String Key = "in_HistPerform";
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_HistoricalLoanTotalCount")
	@ModelField(name = "历史借款总次数")
	private Integer historicalLoanTotalCount = -1;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_HistoricalFullLoanTotalCount")
	@ModelField(name = "历史满额借款总次数")
	private Integer historicalFullLoanTotalCount = -1;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_HistoricalContinuousFullLoanMaxCount")
	@ModelField(name = "历史全额还款总次数")
	private Integer historicalContinuousFullLoanMaxCount = -1;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_StoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount")
	@ModelField(name = "历史GPS定位距离申请地大于5km的总次数")
	private Integer historicalGPSDistanceFromApplicationPlaceOver5KMTotalCount = -1;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLoanTotalAmount")
	@ModelField(name = "历史借款总金额")
	private Double historicalLoanTotalAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLoanAverageAmount")
	@ModelField(name = "历史平均借款金额")
	private Double historicalLoanAverageAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLoanMaxAmount")
	@ModelField(name = "历史最大借款金额")
	private Double historicalLoanMaxAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_LastLoanAmount")
	@ModelField(name = "上次借款金额")
	private Double lastLoanAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_ThisLoanAmount")
	@ModelField(name = "本次借款金额")
	private Double thisLoanAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLoanTotalLimitAmount")
	@ModelField(name = "历史借款总额度")
	private Double historicalLoanTotalLimitAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLoanAverageLimitAmount")
	@ModelField(name = "历史平均额度")
	private Double historicalLoanAverageLimitAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLoanMaxLimitAmount")
	@ModelField(name = "历史最大额度")
	private Double historicalLoanMaxLimitAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_LastLoanLimitAmount")
	@ModelField(name = "上次借款额度")
	private Double lastLoanLimitAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_ThisLoanLimitAmount")
	@ModelField(name = "本次借款额度")
	private Double thisLoanLimitAmount = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLimitAmountUsageRate")
	@ModelField(name = "历史平均额度使用率")
	private Double historicalLimitAmountUsageRate = -1.0;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_HistoricalLoanMaxDays")
	@ModelField(name = "历史最大借款天数")
	private Integer historicalLoanMaxDays = -1;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLoanAverageDays")
	@ModelField(name = "历史平均借款天数")
	private Double historicalLoanAverageDays = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_CircleLoanAverageDays")
	@ModelField(name = "平均复借天数")
	private Double circleLoanAverageDays = -1.0;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_LastLoanDays")
	@ModelField(name = "上次借款天数")
	private Integer lastLoanDays = -1;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_ThisLoanDays")
	@ModelField(name = "本次借款天数")
	private Integer thisLoanDays = -1;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_HistoricalLoanDayAwayFromPayDayMaxDays")
	@ModelField(name = "历史借款日离发薪日最大天数")
	private Integer historicalLoanDayAwayFromPayDayMaxDays = -1;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_HistoricalLoanDayAwayFromPayDayMinDays")
	@ModelField(name = "历史借款日离发薪日最少天数")
	private Integer historicalLoanDayAwayFromPayDayMinDays = -1;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalLoanDayAwayFromPayDayAverageDays")
	@ModelField(name = "历史借款日离发薪日平均天数")
	private Double historicalLoanDayAwayFromPayDayAverageDays = -1.0;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_ThisLoanDayAwayFromPayDayDays")
	@ModelField(name = "本次借款日离发薪日天数")
	private Integer thisLoanDayAwayFromPayDayDays = -1;
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_MonthlyPayDay")
	@ModelField(name = "每月发薪日")
	private Integer monthlyPayDay = -1;
	@DBField(fieldName = DerivativeVariableApi.DateTimeValue, variableType = "X_LastLoanTime")
	@ModelField(name = "上次借款时间")
	private Date lastLoanTime = new Date(0);
	@DBField(fieldName = DerivativeVariableApi.DateTimeValue, variableType = "X_ThisLoanTime")
	@ModelField(name = "本次借款时间")
	private Date thisLoanTime = new Date(0);
	@DBField(fieldName = DerivativeVariableApi.DateTimeValue, variableType = "X_LastRepaymentTime")
	@ModelField(name = "上次还款时间")
	private Date lastRepaymentTime = new Date(0);
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_CurrentGPSAwayFromApplicationPostionDistance")
	@ModelField(name = "当前GPS定位距离申请时的距离(km)")
	private Double currentGPSAwayFromApplicationPostionDistance = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalGPSAwayFromApplicationPostionAverageDistance")
	@ModelField(name = "历史GPS定位距离申请时的平均距离(km)")
	private Double historicalGPSAwayFromApplicationPostionAverageDistance = -1.0;
	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = "X_HistoricalGPSAwayFromApplicationPostionMaxDistance")
	@ModelField(name = "历史GPS定位距离申请时的最大距离(km)")
	private Double historicalGPSAwayFromApplicationPostionMaxDistance = -1.0;
	
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_LastBillStatus")
	@ModelField(name = "上期账单状态", bindDomain = "engine.rule.domain.BillStatusForFCResult")
	private Integer lastBillStatus  = BillStatusForFCResult.NoBill.getValue();
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = "X_CurrentBillStatus")
	@ModelField(name = "当期账单状态", bindDomain = "engine.rule.domain.BillStatusForFCResult")
	private Integer currentBillStatus  = BillStatusForFCResult.NoBill.getValue();
	
	public Integer getLastBillStatus() {
		return lastBillStatus;
	}
	public void setLastBillStatus(Integer lastBillStatus) {
		this.lastBillStatus = lastBillStatus;
	}
	public Integer getCurrentBillStatus() {
		return currentBillStatus;
	}
	public void setCurrentBillStatus(Integer currentBillStatus) {
		this.currentBillStatus = currentBillStatus;
	}
	public Integer getHistoricalLoanTotalCount() {
		return historicalLoanTotalCount;
	}
	public void setHistoricalLoanTotalCount(Integer historicalLoanTotalCount) {
		this.historicalLoanTotalCount = historicalLoanTotalCount;
	}
	public Integer getHistoricalFullLoanTotalCount() {
		return historicalFullLoanTotalCount;
	}
	public void setHistoricalFullLoanTotalCount(Integer historicalFullLoanTotalCount) {
		this.historicalFullLoanTotalCount = historicalFullLoanTotalCount;
	}
	public Integer getHistoricalContinuousFullLoanMaxCount() {
		return historicalContinuousFullLoanMaxCount;
	}
	public void setHistoricalContinuousFullLoanMaxCount(
			Integer historicalContinuousFullLoanMaxCount) {
		this.historicalContinuousFullLoanMaxCount = historicalContinuousFullLoanMaxCount;
	}
	public Integer getHistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount() {
		return historicalGPSDistanceFromApplicationPlaceOver5KMTotalCount;
	}
	public void setHistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount(
			Integer historicalGPSDistanceFromApplicationPlaceOver5KMTotalCount) {
		this.historicalGPSDistanceFromApplicationPlaceOver5KMTotalCount = historicalGPSDistanceFromApplicationPlaceOver5KMTotalCount;
	}
	public Double getHistoricalLoanTotalAmount() {
		return historicalLoanTotalAmount;
	}
	public void setHistoricalLoanTotalAmount(Double historicalLoanTotalAmount) {
		this.historicalLoanTotalAmount = historicalLoanTotalAmount;
	}
	public Double getHistoricalLoanAverageAmount() {
		return historicalLoanAverageAmount;
	}
	public void setHistoricalLoanAverageAmount(Double historicalLoanAverageAmount) {
		this.historicalLoanAverageAmount = historicalLoanAverageAmount;
	}
	public Double getHistoricalLoanMaxAmount() {
		return historicalLoanMaxAmount;
	}
	public void setHistoricalLoanMaxAmount(Double historicalLoanMaxAmount) {
		this.historicalLoanMaxAmount = historicalLoanMaxAmount;
	}
	public Double getLastLoanAmount() {
		return lastLoanAmount;
	}
	public void setLastLoanAmount(Double lastLoanAmount) {
		this.lastLoanAmount = lastLoanAmount;
	}
	public Double getThisLoanAmount() {
		return thisLoanAmount;
	}
	public void setThisLoanAmount(Double thisLoanAmount) {
		this.thisLoanAmount = thisLoanAmount;
	}
	public Double getHistoricalLoanTotalLimitAmount() {
		return historicalLoanTotalLimitAmount;
	}
	public void setHistoricalLoanTotalLimitAmount(
			Double historicalLoanTotalLimitAmount) {
		this.historicalLoanTotalLimitAmount = historicalLoanTotalLimitAmount;
	}
	public Double getHistoricalLoanAverageLimitAmount() {
		return historicalLoanAverageLimitAmount;
	}
	public void setHistoricalLoanAverageLimitAmount(
			Double historicalLoanAverageLimitAmount) {
		this.historicalLoanAverageLimitAmount = historicalLoanAverageLimitAmount;
	}
	public Double getHistoricalLoanMaxLimitAmount() {
		return historicalLoanMaxLimitAmount;
	}
	public void setHistoricalLoanMaxLimitAmount(Double historicalLoanMaxLimitAmount) {
		this.historicalLoanMaxLimitAmount = historicalLoanMaxLimitAmount;
	}
	public Double getLastLoanLimitAmount() {
		return lastLoanLimitAmount;
	}
	public void setLastLoanLimitAmount(Double lastLoanLimitAmount) {
		this.lastLoanLimitAmount = lastLoanLimitAmount;
	}
	public Double getThisLoanLimitAmount() {
		return thisLoanLimitAmount;
	}
	public void setThisLoanLimitAmount(Double thisLoanLimitAmount) {
		this.thisLoanLimitAmount = thisLoanLimitAmount;
	}
	public Double getHistoricalLimitAmountUsageRate() {
		return historicalLimitAmountUsageRate;
	}
	public void setHistoricalLimitAmountUsageRate(
			Double historicalLimitAmountUsageRate) {
		this.historicalLimitAmountUsageRate = historicalLimitAmountUsageRate;
	}
	public Integer getHistoricalLoanMaxDays() {
		return historicalLoanMaxDays;
	}
	public void setHistoricalLoanMaxDays(Integer historicalLoanMaxDays) {
		this.historicalLoanMaxDays = historicalLoanMaxDays;
	}
	public Double getHistoricalLoanAverageDays() {
		return historicalLoanAverageDays;
	}
	public void setHistoricalLoanAverageDays(Double historicalLoanAverageDays) {
		this.historicalLoanAverageDays = historicalLoanAverageDays;
	}
	public Double getCircleLoanAverageDays() {
		return circleLoanAverageDays;
	}
	public void setCircleLoanAverageDays(Double circleLoanAverageDays) {
		this.circleLoanAverageDays = circleLoanAverageDays;
	}
	public Integer getLastLoanDays() {
		return lastLoanDays;
	}
	public void setLastLoanDays(Integer lastLoanDays) {
		this.lastLoanDays = lastLoanDays;
	}
	public Integer getThisLoanDays() {
		return thisLoanDays;
	}
	public void setThisLoanDays(Integer thisLoanDays) {
		this.thisLoanDays = thisLoanDays;
	}
	public Integer getHistoricalLoanDayAwayFromPayDayMaxDays() {
		return historicalLoanDayAwayFromPayDayMaxDays;
	}
	public void setHistoricalLoanDayAwayFromPayDayMaxDays(
			Integer historicalLoanDayAwayFromPayDayMaxDays) {
		this.historicalLoanDayAwayFromPayDayMaxDays = historicalLoanDayAwayFromPayDayMaxDays;
	}
	public Integer getHistoricalLoanDayAwayFromPayDayMinDays() {
		return historicalLoanDayAwayFromPayDayMinDays;
	}
	public void setHistoricalLoanDayAwayFromPayDayMinDays(
			Integer historicalLoanDayAwayFromPayDayMinDays) {
		this.historicalLoanDayAwayFromPayDayMinDays = historicalLoanDayAwayFromPayDayMinDays;
	}
	public Double getHistoricalLoanDayAwayFromPayDayAverageDays() {
		return historicalLoanDayAwayFromPayDayAverageDays;
	}
	public void setHistoricalLoanDayAwayFromPayDayAverageDays(
			Double historicalLoanDayAwayFromPayDayAverageDays) {
		this.historicalLoanDayAwayFromPayDayAverageDays = historicalLoanDayAwayFromPayDayAverageDays;
	}
	public Integer getThisLoanDayAwayFromPayDayDays() {
		return thisLoanDayAwayFromPayDayDays;
	}
	public void setThisLoanDayAwayFromPayDayDays(
			Integer thisLoanDayAwayFromPayDayDays) {
		this.thisLoanDayAwayFromPayDayDays = thisLoanDayAwayFromPayDayDays;
	}
	public Integer getMonthlyPayDay() {
		return monthlyPayDay;
	}
	public void setMonthlyPayDay(Integer monthlyPayDay) {
		this.monthlyPayDay = monthlyPayDay;
	}
	public Date getLastLoanTime() {
		return lastLoanTime;
	}
	public void setLastLoanTime(Date lastLoanTime) {
		this.lastLoanTime = lastLoanTime;
	}
	public Date getThisLoanTime() {
		return thisLoanTime;
	}
	public void setThisLoanTime(Date thisLoanTime) {
		this.thisLoanTime = thisLoanTime;
	}
	public Date getLastRepaymentTime() {
		return lastRepaymentTime;
	}
	public void setLastRepaymentTime(Date lastRepaymentTime) {
		this.lastRepaymentTime = lastRepaymentTime;
	}
	public Double getCurrentGPSAwayFromApplicationPostionDistance() {
		return currentGPSAwayFromApplicationPostionDistance;
	}
	public void setCurrentGPSAwayFromApplicationPostionDistance(
			Double currentGPSAwayFromApplicationPostionDistance) {
		this.currentGPSAwayFromApplicationPostionDistance = currentGPSAwayFromApplicationPostionDistance;
	}
	public Double getHistoricalGPSAwayFromApplicationPostionAverageDistance() {
		return historicalGPSAwayFromApplicationPostionAverageDistance;
	}
	public void setHistoricalGPSAwayFromApplicationPostionAverageDistance(
			Double historicalGPSAwayFromApplicationPostionAverageDistance) {
		this.historicalGPSAwayFromApplicationPostionAverageDistance = historicalGPSAwayFromApplicationPostionAverageDistance;
	}
	public Double getHistoricalGPSAwayFromApplicationPostionMaxDistance() {
		return historicalGPSAwayFromApplicationPostionMaxDistance;
	}
	public void setHistoricalGPSAwayFromApplicationPostionMaxDistance(
			Double historicalGPSAwayFromApplicationPostionMaxDistance) {
		this.historicalGPSAwayFromApplicationPostionMaxDistance = historicalGPSAwayFromApplicationPostionMaxDistance;
	}
	
	@ModelMethod(name = "(this)(#1,<日>)与(#2,<日>)的间隔天数")
	public int getDaysBetweenTwoDate(Date s1, Date s2) {
        
		return ((int) ((s1.getTime() - s2.getTime())/(1000 * 3600 *24)) + 1 );
	}

	
}