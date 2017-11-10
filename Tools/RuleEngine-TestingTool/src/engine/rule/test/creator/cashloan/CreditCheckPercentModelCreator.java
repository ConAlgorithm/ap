package engine.rule.test.creator.cashloan;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.cashloan.CreditCheckPercentInForm;
import engine.rule.test.creator.TestModelCreator;


public class CreditCheckPercentModelCreator extends TestModelCreator {

	public CreditCheckPercentModelCreator(Map<String, String> columnNameValueMappings) {
		super(CreditCheckPercentInForm.class, "in_CreditPcent", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
