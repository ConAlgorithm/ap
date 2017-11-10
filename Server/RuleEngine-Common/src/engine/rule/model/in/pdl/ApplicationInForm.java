package engine.rule.model.in.pdl;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import catfish.base.business.common.AppDerivativeVariableConsts;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "业务申请材料")
public class ApplicationInForm extends BaseForm {

	@ModelField(name = "教育程度", bindDomain = "engine.rule.domain.EducationalBackground")
	private Integer education = -99;

	// finished
	// 在creator中手动填写
	@ModelField(name = "婚姻状况", bindDomain = "engine.rule.domain.MarriageStatus")
	private Integer marriage = -99;

	// finished
	// 在creator中手动填写
	@ModelField(name = "居住性质", bindDomain = "engine.rule.domain.LivingCondition")
	private Integer livingCondition = -99;

	// finished
	// 在creator中手动填写
	@ModelField(name = "第几份工作", bindDomain = "engine.rule.domain.NthJob")
	private int nthJob = -99;

	@DBField(bindAdapter = "engine.rule.model.adapter.PrincipalAdapter")
	@ModelField(name = "贷款额")
	private BigDecimal principal = new BigDecimal(-99);

	/************ for Precheck **********************/
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.IdCardNumberRepayingCount)
	@ModelField(name = "客户当前所有申请中处于‘还款中’状态的数目")
	private Integer repayingCount = 0;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.IdCardNumberDelayedCount)
	@ModelField(name = "客户当前所有申请中处于‘已逾期’状态的数目")
	private Integer delayedCount = 0;

	@DBField(fieldName = DerivativeVariableApi.DateTimeValue, variableType = AppDerivativeVariableConsts.IdCardNumberLastRejectedDate)
	@ModelField(name = "身份证号最近被拒绝时间")
	private Date idCardNumberLastRejectedDate = new Date(0);

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.TransactionMonitorJobCount)
	@ModelField(name = "交易监控在线数量(默认值-1)")
	private int transactionMonitorJobCount = -1;
	
	public Date getIdCardNumberLastRejectedDate() {
		return idCardNumberLastRejectedDate;
	}

	public void setIdCardNumberLastRejectedDate(
			Date idCardNumberLastRejectedDate) {
		this.idCardNumberLastRejectedDate = idCardNumberLastRejectedDate;
	}

	@ModelMethod(name = "(this)的客户在(#1,<天数>)内被拒绝过")
	public boolean isIdCardNumberBeenRejectedInSixMonth(int dayCount) {
		if (idCardNumberLastRejectedDate == null)
			return false;
		DateTime now = DateTime.now();
		DateTime rejectedDate = new DateTime(idCardNumberLastRejectedDate);
		long duration = new Duration(rejectedDate, now).getStandardDays();
		return (duration < dayCount);
	}

	/*********************************************/

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	public Integer getLivingCondition() {
		return livingCondition;
	}

	public void setLivingCondition(Integer livingCondition) {
		this.livingCondition = livingCondition;
	}

	public int getNthJob() {
		return nthJob;
	}

	public void setNthJob(int nthJob) {
		this.nthJob = nthJob;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public Integer getRepayingCount() {
		return repayingCount;
	}

	public void setRepayingCount(Integer repayingCount) {
		this.repayingCount = repayingCount;
	}

	public Integer getDelayedCount() {
		return delayedCount;
	}

	public void setDelayedCount(Integer delayedCount) {
		this.delayedCount = delayedCount;
	}

	public int getTransactionMonitorJobCount() {
		return transactionMonitorJobCount;
	}

	public void setTransactionMonitorJobCount(int transactionMonitorJobCount) {
		this.transactionMonitorJobCount = transactionMonitorJobCount;
	}
}
