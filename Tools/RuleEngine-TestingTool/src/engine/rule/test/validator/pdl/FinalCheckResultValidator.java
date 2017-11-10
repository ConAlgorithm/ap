package engine.rule.test.validator.pdl;

import java.lang.reflect.Field;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.RuleEngineDecisionResult;
import engine.rule.model.BaseForm;
import engine.rule.model.inout.pdl.DecisionInOutForm;

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

    	DecisionInOutForm decisionInOutForm = (DecisionInOutForm)form;
    	for(Field filed : decisionInOutForm.getClass().getDeclaredFields()){
    		filed.setAccessible(true);
    		try {
				Logger.get().info(filed.getName() + ":" + filed.get(decisionInOutForm));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
        boolean childResult = true, strategyResult = true;
        
        expectedDecision = columnValueMappings.get(DECISION);
        expectedTMRequired = columnValueMappings.get(TMREQUIRED);
        expectedStrategyCode = columnValueMappings.get(STRATEGYCODE);
        Logger.get().info("expectedDecision" + expectedDecision);
        Logger.get().info("expectedTMRequired" + expectedTMRequired);
        Logger.get().info("expectedStrategyCode" + expectedStrategyCode);
        boolean superResult = super.validate(form, columnValueMappings);
        Logger.get().info("superResult" + superResult);
        String realDecision = decisionInOutForm.getDecisionResult().toString();
        String realTMRequired = decisionInOutForm.isTransactionMonitorRequired() ? "TRUE" : "FALSE";
        String realStrategyCode = decisionInOutForm.getStrategyCode();
        Logger.get().info("realDecision" + realDecision);
        Logger.get().info("realTMRequired" + realTMRequired);
        Logger.get().info("realStrategyCode" + realStrategyCode);   
        Logger.get().info("getDecisionResult" + decisionInOutForm.getDecisionResult());  
        if(decisionInOutForm.getDecisionResult() == RuleEngineDecisionResult.Approved.getValue() 
                && realDecision.equals(expectedDecision)
                && !realTMRequired.equalsIgnoreCase(expectedTMRequired)){
            childResult = false;
        }
        
        if(!realStrategyCode.equals(expectedStrategyCode))
        {
        	strategyResult = false;
        }
        Logger.get().info("childResult" + childResult);
        Logger.get().info("strategyResult" + strategyResult);
        return superResult && childResult & strategyResult;
    }
}
