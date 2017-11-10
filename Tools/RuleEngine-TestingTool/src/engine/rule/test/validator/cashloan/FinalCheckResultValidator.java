package engine.rule.test.validator.cashloan;

import java.util.Map;

import catfish.base.business.common.RuleEngineDecisionResult;
import engine.rule.model.BaseForm;
import engine.rule.model.inout.cashloan.DecisionInOutForm;

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

        boolean childResult = true, strategyResult = true;

        expectedDecision = columnValueMappings.get(DECISION);
        expectedTMRequired = columnValueMappings.get(TMREQUIRED);
        expectedStrategyCode = columnValueMappings.get(STRATEGYCODE);

        boolean superResult = super.validate(form, columnValueMappings);

        String realDecision = decisionInOutForm.getDecisionResult().toString();
        String realTMRequired = decisionInOutForm.isTransactionMonitorRequired() ? "TRUE" : "FALSE";
        String realStrategyCode = decisionInOutForm.getStrategyCode();

        if(decisionInOutForm.getDecisionResult() == RuleEngineDecisionResult.Approved.getValue()
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
