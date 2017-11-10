package engine.rule.model.in.cashloan;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import catfish.base.business.util.AppDerivativeVariableNames;
import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "判断是否增信所需数据信息")
public class CreditCheckInForm extends BaseForm{

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

	/*handler获取，creator注入*/
	@ModelField(name = "CL_信用评分")
	private int clusercreditrating = 0;
	/*handler获取，creator注入*/
	@ModelField(name = "CL_成功申请次数")
	private int clappsuccesstimes = 0;

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

	public int getClusercreditrating() {
		return clusercreditrating;
	}

	public void setClusercreditrating(int clusercreditrating) {
		this.clusercreditrating = clusercreditrating;
	}

	public int getClappsuccesstimes() {
		return clappsuccesstimes;
	}

	public void setClappsuccesstimes(int clappsuccesstimes) {
		this.clappsuccesstimes = clappsuccesstimes;
	}

}
