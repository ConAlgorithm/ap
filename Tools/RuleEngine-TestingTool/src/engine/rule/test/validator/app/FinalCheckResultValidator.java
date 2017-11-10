package engine.rule.test.validator.app;

import java.util.Map;

import com.google.gson.Gson;

import catfish.base.business.common.RuleEngineDecisionResult;
import engine.rule.model.BaseForm;
import engine.rule.model.inout.app.DecisionInOutForm;

public class FinalCheckResultValidator extends DecisionResultValidator{

    protected String expectedTMRequired;
    protected String expectedStrategyCode;
    
    @Override
    protected void initDefaultValue()
    {
        if(expectedTMRequired == null || expectedTMRequired.equalsIgnoreCase(NULL))
        {
            expectedTMRequired = "FALSE";
        }
        if(expectedStrategyCode == null || expectedStrategyCode.equals(NULL))
			expectedStrategyCode = "";
        super.initDefaultValue();
    }
    @Override
    public boolean validate(BaseForm form, Map<String, String> columnValueMappings) {
        
        boolean childResult = true, strategyResult = true;
        
        expectedDecision = columnValueMappings.get(DECISION);
        expectedTMRequired = columnValueMappings.get(TMREQUIRED);
        expectedStrategyCode = columnValueMappings.get(STRATEGYCODE);
        
        boolean superResult = super.validate(form, columnValueMappings);
        
        DecisionInOutForm outForm = (DecisionInOutForm)form;
        System.out.println(new Gson().toJson(outForm));
        String realDecision = outForm.getDecisionResult().toString();
        String realTMRequired = outForm.isTransactionMonitorRequired() ? "TRUE" : "FALSE";
        String realStrategyCode = outForm.getStrategyCode();
        
        if(outForm.getDecisionResult() == RuleEngineDecisionResult.Approved.getValue() 
                && realDecision.equals(expectedDecision)
                && !realTMRequired.equalsIgnoreCase(expectedTMRequired)){
            childResult = false;
        }
        
        if(!realStrategyCode.equals(expectedStrategyCode))
        {
        	strategyResult = false;
        }
        
        return superResult && childResult & strategyResult;
    }
}
