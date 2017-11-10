package engine.rule.test.creator.cashloan;

import java.util.Map;
import engine.rule.model.BaseForm;
import engine.rule.model.in.cashloan.CreditReferenceInForm;
import engine.rule.test.creator.TestModelCreator;

public class CreditReferenceModelCreator extends TestModelCreator {

	public CreditReferenceModelCreator(Map<String, String> columnNameValueMappings) {
		super(CreditReferenceInForm.class, "in_Bureau", columnNameValueMappings);
	}
	
	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}

}
