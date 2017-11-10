package engine.rule.test.validator.pdl;

import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.inout.pdl.LoanMoneyDecisionOutForm;
import engine.rule.test.validator.ResultValidator;

public class AntiFraudCheckResultValidator implements ResultValidator{
	
	protected String rawExpcetedDays;
	protected int expectedFrozenDays = 0;
	protected String expectedFrozenReason = "";
	
	protected void initDefaultValue()
	{
	    if(rawExpcetedDays == null || rawExpcetedDays.equals(NULL))
	    {
	    	this.expectedFrozenDays = 0;
	    }else{
	    	this.expectedFrozenDays = Integer.parseInt(rawExpcetedDays);
	    }
	    
	    if(expectedFrozenReason == null || expectedFrozenReason.equalsIgnoreCase(NULL))
	    	expectedFrozenReason = "";
	}
	
    @Override
    public boolean validate(BaseForm form,
            Map<String, String> columnValueMappings) {
    	
    	LoanMoneyDecisionOutForm loanMoneyDecisionOutForm = (LoanMoneyDecisionOutForm)form;
    	rawExpcetedDays = columnValueMappings.get(FROZENDAYS);
        expectedFrozenReason = columnValueMappings.get(FROZENREASON);
        initDefaultValue();
    	return loanMoneyDecisionOutForm.getFrozenDays() == expectedFrozenDays
    			&& loanMoneyDecisionOutForm.getFrozenReason().equalsIgnoreCase(expectedFrozenReason);
    }
}
