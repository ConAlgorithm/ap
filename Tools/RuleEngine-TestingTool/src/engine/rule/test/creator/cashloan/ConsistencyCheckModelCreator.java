package engine.rule.test.creator.cashloan;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.cashloan.ConsistencyCheckInForm;
import engine.rule.test.creator.TestModelCreator;

public class ConsistencyCheckModelCreator extends TestModelCreator{

	public ConsistencyCheckModelCreator(Map<String, String> columnNameValueMappings) {
		super(ConsistencyCheckInForm.class, "in_Derived", columnNameValueMappings);		
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}


}
