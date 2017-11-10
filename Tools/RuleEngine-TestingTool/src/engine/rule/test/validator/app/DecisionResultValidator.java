package engine.rule.test.validator.app;

import java.util.Map;
import java.util.Set;

import catfish.base.business.common.RuleEngineDecisionResult;
import engine.rule.model.BaseForm;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.rule.test.validator.ResultValidator;

public class DecisionResultValidator implements ResultValidator{

	protected String expectedDecision;
	protected String expectedRejReason;
	
	protected void initDefaultValue()
	{
	    if(expectedDecision == null || expectedDecision.equalsIgnoreCase(NULL))
	    {
	    	expectedDecision = RuleEngineDecisionResult.Approved.getValue().toString();
	    }
	    if(expectedRejReason == null || !expectedRejReason.equalsIgnoreCase(NULL))
	    {
	    	expectedRejReason = "";
	    }
	}

	@Override
	public boolean validate(BaseForm form, Map<String, String> columnValueMappings) {
		expectedDecision = columnValueMappings.get(DECISION);
		expectedRejReason = columnValueMappings.get(REJREASONSET);
		
		initDefaultValue();
		
		DecisionInOutForm outForm = (DecisionInOutForm)form;
		String realDecision = outForm.getDecisionResult().toString();
		
		if(!realDecision.equals(expectedDecision))
		{
			return false;
		}
		
		//if result is approved, then ignore other status
		if(outForm.getDecisionResult() == RuleEngineDecisionResult.Approved.getValue() && realDecision.equals(expectedDecision)){
			return true;
		}
		
		Set<String> realRejReason = outForm.getDecisionRejectReasonSet();
		if(outForm.getDecisionResult() == RuleEngineDecisionResult.Canceled.getValue() && realDecision.equals(expectedDecision))
		{
			if(realRejReason.size() != 0 && !realRejReason.contains(expectedRejReason))
			{
				return false;
			}
			if(!expectedRejReason.equals(""))
			{
				return false;
			}
			return true;
		}
		
		return true;
	}

}
