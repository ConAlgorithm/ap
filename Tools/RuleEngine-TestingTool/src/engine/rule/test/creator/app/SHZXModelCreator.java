package engine.rule.test.creator.app;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.app.SHZXDataInForm;
import engine.rule.test.creator.TestModelCreator;


public class SHZXModelCreator extends TestModelCreator {

	public SHZXModelCreator(Map<String, String> columnNameValueMappings) {
		super(SHZXDataInForm.class, "in_SHZX", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
