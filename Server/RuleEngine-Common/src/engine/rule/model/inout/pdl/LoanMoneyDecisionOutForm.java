package engine.rule.model.inout.pdl;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.rule.model.BaseForm;

@ModelInstance(description = "借款决策结果")
public class LoanMoneyDecisionOutForm extends BaseForm {
	
	public final static String Key = "out_LoanMoney";

	@ModelField(name = "冷冻天数(默认为0)")
	private Integer frozenDays = 0;

	@ModelField(name = "冷冻原因(默认为空)")
	private String frozenReason = "";

	public Integer getFrozenDays() {
		return frozenDays;
	}

	public void setFrozenDays(Integer frozenDays) {
		this.frozenDays = frozenDays;
	}

	public String getFrozenReason() {
		return frozenReason;
	}

	public void setFrozenReason(String frozenReason) {
		this.frozenReason = frozenReason;
	}
}
