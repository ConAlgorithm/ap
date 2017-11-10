package engine.rule.model.in.pdl;

import java.util.Date;

import org.joda.time.DateTime;

import catfish.base.business.common.AppDerivativeVariableConsts;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "行为信息材料")
public class BehaviorInForm extends BaseForm {

	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.PageDurationApplicationPlan)
	@ModelField(name = "产品页面停留时长（秒）")
	private Double pageDurationApplicationPlan = -99.0;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.PageScrollCountAccountRegister)
	@ModelField(name = "注册页面滚动次数")
	private Integer pageScrollCountAccountRegister = -99;

	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.NameSpentSeconds)
	@ModelField(name = "姓名输入时长（秒）")
	private Double nameSpentSeconds = -99.0;

	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.CompanyPhoneSpentSeconds)
	@ModelField(name = "单位电话输入时长（秒）")
	private Double companyPhoneSpentSeconds = -99.0;

	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.PrincipalSpentSeconds)
	@ModelField(name = "商品价格调整时长（秒）")
	private Double principalSpentSeconds = -99.0;

	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.QQSpentSeconds)
	@ModelField(name = "QQ输入时长（秒）")
	private Double qqSpentSeconds = -99.0;

	/*
	 * @DBField(fieldName = DerivativeVariableApi.IntValue, variableType =
	 * AppDerivativeVariableConsts.PageScrollCountApplicationPlan)
	 * 
	 * @ModelField(name = "产品页面滚动次数") private Integer
	 * pageScrollCountApplicationPlan = -99;
	 */

	@DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableConsts.TotallySpentSeconds)
	@ModelField(name = "总时长（秒）")
	private Double totallySpentSeconds = -99.0;

	@ModelField(name = "申请开始时间")
	private Date applicationTimestamp = new Date();

	@ModelMethod(name = "(this)的申请开始时间的年")
	public Integer applicationTimeYear() {
		if (this.applicationTimestamp == null)
			return -99;
		return new DateTime(applicationTimestamp).getYear();
	}

	@ModelMethod(name = "(this)的申请开始时间的月")
	public Integer applicationTimeMonth() {
		if (this.applicationTimestamp == null)
			return -99;
		return new DateTime(applicationTimestamp).getMonthOfYear();
	}

	@ModelMethod(name = "(this)的申请开始时间的日(相对于月)")
	public Integer applicationTimeDayOfMonth() {
		if (this.applicationTimestamp == null)
			return -99;
		return new DateTime(applicationTimestamp).getDayOfMonth();
	}

	@ModelMethod(name = "(this)的申请开始时间的小时")
	public Integer applicationTimeHour() {
		if (this.applicationTimestamp == null)
			return -99;
		return new DateTime(applicationTimestamp).getHourOfDay();
	}

	@ModelMethod(name = "(this)的申请开始时间的分钟(相对于小时)")
	public Integer appliationMinute() {
		if (this.applicationTimestamp == null)
			return -99;
		return new DateTime(applicationTimestamp).getMinuteOfHour();
	}

	public Double getPageDurationApplicationPlan() {
		return pageDurationApplicationPlan;
	}

	public void setPageDurationApplicationPlan(
			Double pageDurationApplicationPlan) {
		this.pageDurationApplicationPlan = pageDurationApplicationPlan;
	}

	public Integer getPageScrollCountAccountRegister() {
		return pageScrollCountAccountRegister;
	}

	public void setPageScrollCountAccountRegister(
			Integer pageScrollCountAccountRegister) {
		this.pageScrollCountAccountRegister = pageScrollCountAccountRegister;
	}

	public Double getNameSpentSeconds() {
		return nameSpentSeconds;
	}

	public void setNameSpentSeconds(Double nameSpentSeconds) {
		this.nameSpentSeconds = nameSpentSeconds;
	}

	public Double getCompanyPhoneSpentSeconds() {
		return companyPhoneSpentSeconds;
	}

	public void setCompanyPhoneSpentSeconds(Double companyPhoneSpentSeconds) {
		this.companyPhoneSpentSeconds = companyPhoneSpentSeconds;
	}

	public Double getPrincipalSpentSeconds() {
		return principalSpentSeconds;
	}

	public void setPrincipalSpentSeconds(Double principalSpentSeconds) {
		this.principalSpentSeconds = principalSpentSeconds;
	}

	public Double getQqSpentSeconds() {
		return qqSpentSeconds;
	}

	public void setQqSpentSeconds(Double qqSpentSeconds) {
		this.qqSpentSeconds = qqSpentSeconds;
	}

	/*
	 * public Integer getPageScrollCountApplicationPlan() { return
	 * pageScrollCountApplicationPlan; }
	 * 
	 * public void setPageScrollCountApplicationPlan( Integer
	 * pageScrollCountApplicationPlan) { this.pageScrollCountApplicationPlan =
	 * pageScrollCountApplicationPlan; }
	 */

	public Double getTotallySpentSeconds() {
		return totallySpentSeconds;
	}

	public void setTotallySpentSeconds(Double totallySpentSeconds) {
		this.totallySpentSeconds = totallySpentSeconds;
	}

	public Date getApplicationTimestamp() {
		return applicationTimestamp;
	}

	public void setApplicationTimestamp(Date applicationTimestamp) {
		this.applicationTimestamp = applicationTimestamp;

		if (this.pageDurationApplicationPlan.compareTo(-99.0) == 0
				&& this.pageScrollCountAccountRegister == -99
				&& this.nameSpentSeconds.compareTo(-99.0) == 0
				&& this.companyPhoneSpentSeconds.compareTo(-99.0) == 0
				&& this.principalSpentSeconds.compareTo(-99.0) == 0
				&& this.qqSpentSeconds.compareTo(-99.0) == 0
				&& this.totallySpentSeconds.compareTo(-99.0) == 0)
			this.applicationTimestamp = null;
	}

}
