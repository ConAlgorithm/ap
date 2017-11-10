package engine.rule.test.creator.pdl;

import java.util.Map;
import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.BehaviorInForm;
import engine.rule.test.creator.TestModelCreator;

public class BehaviorModelCreator extends TestModelCreator {

	public BehaviorModelCreator(Map<String, String> columnNameValueMappings) {
		super(BehaviorInForm.class, "in_Behavior", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
