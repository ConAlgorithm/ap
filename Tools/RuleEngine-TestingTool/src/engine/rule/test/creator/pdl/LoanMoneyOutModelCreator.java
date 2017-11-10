package engine.rule.test.creator.pdl;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.HistoricalPerformanceInForm;
import engine.rule.model.inout.pdl.LoanMoneyDecisionOutForm;
import engine.rule.test.creator.TestModelCreator;

public class LoanMoneyOutModelCreator extends TestModelCreator {

	public LoanMoneyOutModelCreator(Map<String, String> columnNameValueMappings) {
		super(LoanMoneyDecisionOutForm.class, LoanMoneyDecisionOutForm.Key, columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
