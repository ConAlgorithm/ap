package engine.rule.test.creator.pdl;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.pdl.FraudCheckInForm;
import engine.rule.test.creator.TestModelCreator;

public class FraudCheckModelCreator extends TestModelCreator {

	public FraudCheckModelCreator(Map<String, String> columnNameValueMappings) {
		super(FraudCheckInForm.class, "in_Fraud", columnNameValueMappings);		
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		boolean result = false;
		
        FraudCheckInForm inform = (FraudCheckInForm)form;
		
		String key = "isManualBackFraudCheckHit";
		if(new Boolean(this.columnNameValueMappings.containsKey(key))){
			if(new Boolean(this.columnNameValueMappings.get(key)))
				inform.addManualCatfishAdminCheckResultSet("D");
			else
				inform.getManualCatfishAdminCheckResultSet().clear();
			
			result = true;
		}
		        
		key = "isManualFrontFraudCheckHit";
		if(new Boolean(this.columnNameValueMappings.containsKey(key))){
			if(new Boolean(this.columnNameValueMappings.get(key)))
				inform.addManualWeixinFraudCheckResultSet(2047);
			else
				inform.getManualWeixinFraudCheckResultSet().clear();
			
			result = true;
		}
		
		return result;
	}
}
