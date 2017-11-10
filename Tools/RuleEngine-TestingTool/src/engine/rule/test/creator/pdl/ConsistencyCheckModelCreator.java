package engine.rule.test.creator.pdl;

import java.util.Map;
import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.ConsistencyCheckInForm;
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
		String key = "idCardIdentificationResult";
		if(this.columnNameValueMappings.containsKey(key)){	
			inform.setIdCardIdentificationResult(columnNameValueMappings.get(key));
			result = true;
		}
		
		//(this)的身份证校验码为假
		key = "isIdCardIsChecksumInvalid";
		if(this.columnNameValueMappings.containsKey(key)){
			inform.setIdCardIsChecksumValid(new Boolean(this.columnNameValueMappings.get(key)));
			result = true;
		}
		
		return result;
	}

}
