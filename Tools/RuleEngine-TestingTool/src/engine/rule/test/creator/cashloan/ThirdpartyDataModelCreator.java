package engine.rule.test.creator.cashloan;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.cashloan.ThirdpartyDataInForm;
import engine.rule.test.creator.TestModelCreator;

public class ThirdpartyDataModelCreator extends TestModelCreator{

	public ThirdpartyDataModelCreator(Map<String, String> columnNameValueMappings){
		super(ThirdpartyDataInForm.class, "in_Thirdparty", columnNameValueMappings);
	}
	
	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		// TODO Auto-generated method stub
		return false;
	}

}
