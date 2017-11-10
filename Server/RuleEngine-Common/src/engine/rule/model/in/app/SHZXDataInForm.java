/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.in.app;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import catfish.base.business.util.AppDerivativeVariableNames;
import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

/**
 * 〈上海资信数据源〉
 *
 * @author zhujx
 * @version SHZXDataInForm.java, V1.0 2017年3月16日 下午3:11:26
 */
@ModelInstance(description = "上海资信")
public class SHZXDataInForm extends BaseForm{
    /************
     * 上海资信
     ***************/
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.SHZX_LoanNumsTotal)
    @ModelField(name = "贷款笔数")
    public int loanNumsTotal = 0;
    
    @DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableNames.SHZX_FirstLoanDateTime)
    @ModelField(name = "首贷日")
    public String firstLoanDateTime = "";
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.SHZX_MaxCreditLineTotal)
    @ModelField(name = "最大授信额度")
    public double maxCreditLineTotal = 0;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.SHZX_LoanMoneyTotal)
    @ModelField(name = "历史贷款总额")
    public double loanMoneyTotal = 0;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.SHZX_RemainMoneyTotal)
    @ModelField(name = "历史贷款余额")
    public double remainMoneyTotal = 0;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.SHZX_MounthRepayTotal)
    @ModelField(name = "历史协定月还款")
    public double mounthRepayTotal = 0;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.SHZX_RepayOverdueMoneyTotal)
    @ModelField(name = "历史当前逾期总额")
    public double repayOverdueMoneyTotal = 0;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.SHZX_RepayOverdueMaxMoney)
    @ModelField(name = "历史最高逾期金额")
    public double repayOverdueMaxMoney = 0;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.SHZX_RepayOverdueMaxNums)
    @ModelField(name = "历史最高逾期期数")
    public int repayOverdueMaxNums = 0;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.SHZX_LoanGuarantRecordCount)
    @ModelField(name = "历史担保次数")
    public int loanGuarantRecordCount = 0;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.SHZX_MaxMoneyLoanGuarantRecord)
    @ModelField(name = "历史最大担保金额")
    public double maxMoneyLoanGuarantRecord = 0;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.SHZX_LoanSpecialRecordCount)
    @ModelField(name = "历史特殊交易次数")
    public int loanSpecialRecordCount = 0;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.SHZX_MaxMoneyLoanspecialRecord)
    @ModelField(name = "历史最大特殊交易金额")
    public double maxMoneyLoanspecialRecord = 0;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.SHZX_LoanReportQueryCount)
    @ModelField(name = "借款机构查询次数")
    public int loanReportQueryCount = 0;

	public int getLoanNumsTotal() {
		return loanNumsTotal;
	}

	public void setLoanNumsTotal(int loanNumsTotal) {
		this.loanNumsTotal = loanNumsTotal;
	}

	public String getFirstLoanDateTime() {
		return firstLoanDateTime;
	}

	public void setFirstLoanDateTime(String firstLoanDateTime) {
		this.firstLoanDateTime = firstLoanDateTime;
	}

	public double getMaxCreditLineTotal() {
		return maxCreditLineTotal;
	}

	public void setMaxCreditLineTotal(double maxCreditLineTotal) {
		this.maxCreditLineTotal = maxCreditLineTotal;
	}

	public double getLoanMoneyTotal() {
		return loanMoneyTotal;
	}

	public void setLoanMoneyTotal(double loanMoneyTotal) {
		this.loanMoneyTotal = loanMoneyTotal;
	}

	public double getRemainMoneyTotal() {
		return remainMoneyTotal;
	}

	public void setRemainMoneyTotal(double remainMoneyTotal) {
		this.remainMoneyTotal = remainMoneyTotal;
	}

	public double getMounthRepayTotal() {
		return mounthRepayTotal;
	}

	public void setMounthRepayTotal(double mounthRepayTotal) {
		this.mounthRepayTotal = mounthRepayTotal;
	}

	public double getRepayOverdueMoneyTotal() {
		return repayOverdueMoneyTotal;
	}

	public void setRepayOverdueMoneyTotal(double repayOverdueMoneyTotal) {
		this.repayOverdueMoneyTotal = repayOverdueMoneyTotal;
	}

	public double getRepayOverdueMaxMoney() {
		return repayOverdueMaxMoney;
	}

	public void setRepayOverdueMaxMoney(double repayOverdueMaxMoney) {
		this.repayOverdueMaxMoney = repayOverdueMaxMoney;
	}

	public int getRepayOverdueMaxNums() {
		return repayOverdueMaxNums;
	}

	public void setRepayOverdueMaxNums(int repayOverdueMaxNums) {
		this.repayOverdueMaxNums = repayOverdueMaxNums;
	}

	public int getLoanGuarantRecordCount() {
		return loanGuarantRecordCount;
	}

	public void setLoanGuarantRecordCount(int loanGuarantRecordCount) {
		this.loanGuarantRecordCount = loanGuarantRecordCount;
	}

	public double getMaxMoneyLoanGuarantRecord() {
		return maxMoneyLoanGuarantRecord;
	}

	public void setMaxMoneyLoanGuarantRecord(double maxMoneyLoanGuarantRecord) {
		this.maxMoneyLoanGuarantRecord = maxMoneyLoanGuarantRecord;
	}

	public int getLoanSpecialRecordCount() {
		return loanSpecialRecordCount;
	}

	public void setLoanSpecialRecordCount(int loanSpecialRecordCount) {
		this.loanSpecialRecordCount = loanSpecialRecordCount;
	}

	public double getMaxMoneyLoanspecialRecord() {
		return maxMoneyLoanspecialRecord;
	}

	public void setMaxMoneyLoanspecialRecord(double maxMoneyLoanspecialRecord) {
		this.maxMoneyLoanspecialRecord = maxMoneyLoanspecialRecord;
	}

	public int getLoanReportQueryCount() {
		return loanReportQueryCount;
	}

	public void setLoanReportQueryCount(int loanReportQueryCount) {
		this.loanReportQueryCount = loanReportQueryCount;
	}
    
}
