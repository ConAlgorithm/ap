package engine.rule.test.creator.app;

import java.util.Map;
import engine.rule.model.BaseForm;
import engine.rule.model.in.app.DynamicApplicationInForm;
import engine.rule.test.creator.TestModelCreator;

public class DynamicApplicationModelCreator extends TestModelCreator {

	public DynamicApplicationModelCreator(Map<String, String> columnNameValueMappings) {
		super(DynamicApplicationInForm.class, "in_DynamicApp", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
