package engine.rule.test.creator.pdl;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.FInfoForm;
import engine.rule.test.creator.TestModelCreator;

public class FInfoModelCreator extends TestModelCreator {

    public FInfoModelCreator(Map<String, String> columnNameValueMappings) {
        super(FInfoForm.class, "in_FInfo", columnNameValueMappings);
    }

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}
}
