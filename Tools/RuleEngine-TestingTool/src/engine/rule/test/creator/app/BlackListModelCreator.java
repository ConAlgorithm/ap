package engine.rule.test.creator.app;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.app.BlackListInfoForm;
import engine.rule.test.creator.TestModelCreator;


public class BlackListModelCreator extends TestModelCreator {

	public BlackListModelCreator(Map<String, String> columnNameValueMappings) {
		super(BlackListInfoForm.class, "in_BlackList", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
