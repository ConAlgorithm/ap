package engine.rule.test.creator.pdl;

import java.util.Map;
import org.joda.time.DateTime;
import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.ApplicationInForm;
import engine.rule.test.creator.TestModelCreator;

public class ApplicationModelCreator extends TestModelCreator {

	public ApplicationModelCreator(Map<String, String> columnNameValueMappings) {
		super(ApplicationInForm.class, "in_Application", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		boolean result = false;
        ApplicationInForm applicationInForm = (ApplicationInForm)form;
		
		//"的客户在<天数>内被拒绝过"
		String key = "isIdCardNumberBeenRejectedInSixMonth";
		if(this.columnNameValueMappings.containsKey(key)){
			if(new Boolean(this.columnNameValueMappings.get(key)))
				applicationInForm.setIdCardNumberLastRejectedDate(DateTime.now().toDate());
			else
				applicationInForm.setIdCardNumberLastRejectedDate(null);
			
			result = true;
		}
		
		return result;
	}
}
