package engine.rule.test.creator.pdl;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.PersonalInfoInForm;
import engine.rule.test.creator.TestModelCreator;

public class PersonalInfoModelCreator extends TestModelCreator {
	
	public PersonalInfoModelCreator(Map<String, String> columnNameValueMappings){
		super(PersonalInfoInForm.class, "in_Personal", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
