package engine.rule.test.creator.cashloan;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.cashloan.InvestigationInForm;
import engine.rule.test.creator.TestModelCreator;

public class InvestigationModelCreator extends TestModelCreator {

	public InvestigationModelCreator(Map<String, String> columnNameValueMappings) {
		super(InvestigationInForm.class, "in_Investigation", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
