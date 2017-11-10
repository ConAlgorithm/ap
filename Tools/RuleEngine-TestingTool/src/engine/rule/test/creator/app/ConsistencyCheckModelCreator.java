package engine.rule.test.creator.app;

import java.util.Map;

import catfish.base.business.common.CheckNameIDCardResult;
import engine.rule.model.BaseForm;
import engine.rule.model.in.app.ConsistencyCheckInForm;
import engine.rule.test.creator.TestModelCreator;

public class ConsistencyCheckModelCreator extends TestModelCreator{

	public ConsistencyCheckModelCreator(Map<String, String> columnNameValueMappings) {
		super(ConsistencyCheckInForm.class, "in_Derived", columnNameValueMappings);		
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		boolean result = false;
        ConsistencyCheckInForm inform = (ConsistencyCheckInForm)form;		
		//(this)的身份证和姓名是否一致
		String key = "isIdCardIdentificationNameMatch";
		if(this.columnNameValueMappings.containsKey(key)){
			if(new Boolean(this.columnNameValueMappings.get(key)))
				inform.setIdCardIdentificationResult(CheckNameIDCardResult.Match.getValue());
			else
				inform.setIdCardIdentificationResult(CheckNameIDCardResult.NotMatch.getValue());
			
			result = true;
		}
		
		//的身份证和姓名是否一致
		key = "isIdCardIsChecksumInvalid";
		if(this.columnNameValueMappings.containsKey(key)){
			inform.setIdCardIsChecksumValid(new Boolean(this.columnNameValueMappings.get(key)));
			
			result = true;
		}
		
		return result;
	}

}
