package engine.rule.model.in.cashloan;

import catfish.base.business.util.AppDerivativeVariableNames;

import java.math.BigDecimal;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "POS还款数据信息")
public class CurrentWhiteListInForm extends BaseForm{
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.CHECK_POS_OVERDUE_AMOUT)
    @ModelField(name = "CL_最近一笔POS逾期总额")
    private Double posOverDueAmount;
    
    @DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.CHECK_POS_OVERDUE_DAYS)
    @ModelField(name = "CL_最近一笔POS逾期天数")
    private int posOverDueDays;
    
    @DBField(fieldName = DerivativeVariableApi.DecimalValue, variableType = AppDerivativeVariableNames.CL_ApplicationCredit)
    @ModelField(name = "CL_借款金额")
    private BigDecimal applicationCredit=new BigDecimal(-1);

    public Double getPosOverDueAmount() {
        return posOverDueAmount;
    }

    public void setPosOverDueAmount(Double posOverDueAmount) {
        this.posOverDueAmount = posOverDueAmount;
    }

    public int getPosOverDueDays() {
        return posOverDueDays;
    }

    public void setPosOverDueDays(int posOverDueDays) {
        this.posOverDueDays = posOverDueDays;
    }

	public BigDecimal getApplicationCredit() {
		return applicationCredit;
	}

	public void setApplicationCredit(BigDecimal applicationCredit) {
		this.applicationCredit = applicationCredit;
	}

}
