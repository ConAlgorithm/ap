package engine.rule.test.creator.cashloan;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.cashloan.CreditCheckInForm;
import engine.rule.test.creator.TestModelCreator;


public class CreditCheckModelCreator extends TestModelCreator {

	public CreditCheckModelCreator(Map<String, String> columnNameValueMappings) {
		super(CreditCheckInForm.class, "in_CreditCheck", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
