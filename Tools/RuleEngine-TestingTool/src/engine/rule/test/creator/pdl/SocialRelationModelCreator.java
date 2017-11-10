package engine.rule.test.creator.pdl;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.SocialRelationInForm;
import engine.rule.test.creator.TestModelCreator;


public class SocialRelationModelCreator extends TestModelCreator {

	public SocialRelationModelCreator(Map<String, String> columnNameValueMappings){
		super(SocialRelationInForm.class, "in_Social", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}

}
