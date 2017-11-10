package engine.rule.test.validator.app;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.rule.test.validator.ResultValidator;

public class SigningCheckResultValidator implements ResultValidator{

	private String expectedSigningResultRaw;
	private boolean expectedSigningResult = false;
	
	public void initValue()
	{
		if(expectedSigningResultRaw == null || expectedSigningResultRaw.equals(NULL) || !Boolean.parseBoolean(expectedSigningResultRaw))
			expectedSigningResult = false;	
		else if(Boolean.parseBoolean(expectedSigningResultRaw)){
			expectedSigningResult = true;
		}
	}
	
	@Override
	public boolean validate(BaseForm form,
			Map<String, String> columnValueMappings) {
	
		expectedSigningResultRaw = columnValueMappings.get(COMPULSORYSIGNING);
		
		initValue();
		
		DecisionInOutForm outForm = (DecisionInOutForm)form;
		boolean realSigningResult = outForm.isCompulsorySigning();
		
		return (expectedSigningResult == realSigningResult);
	}

}
