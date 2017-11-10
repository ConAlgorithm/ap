package engine.rule.test.validator.app;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.rule.test.validator.ResultValidator;

public class SegmentationResultValidator implements ResultValidator{

	private String expectedSegCode;
	private String expectedStrategyCode;
	
	public void initDefaultValue()
	{
		if(expectedSegCode == null || expectedSegCode.equals(NULL))
			expectedSegCode = "F1";
		if(expectedStrategyCode == null || expectedStrategyCode.equals(NULL))
			expectedStrategyCode = "";
	}

	@Override
	public boolean validate(BaseForm form, Map<String, String> columnValueMappings) {
		expectedSegCode = columnValueMappings.get(SEGMENTATIONCODE);
        expectedStrategyCode = columnValueMappings.get(STRATEGYCODE);
        
        initDefaultValue();
        
        DecisionInOutForm outForm = (DecisionInOutForm)form;
        
        String realSegCode = outForm.getSegmentationCode();
        String realStrategyCode = outForm.getStrategyCode();
        
        boolean segCodeResult = true, strategyResult = true;
        
        if(!realSegCode.equals(expectedSegCode))
        {
        	segCodeResult = false;
        }  
        if(!realStrategyCode.equals(expectedStrategyCode))
        {
        	strategyResult = false;
        }
        return segCodeResult & strategyResult;
	}
}
