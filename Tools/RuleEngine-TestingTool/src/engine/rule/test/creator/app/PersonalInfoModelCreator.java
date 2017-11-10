package engine.rule.test.creator.app;

import java.util.Map;
import engine.rule.model.BaseForm;
import engine.rule.model.in.app.PersonalInfoInForm;
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
