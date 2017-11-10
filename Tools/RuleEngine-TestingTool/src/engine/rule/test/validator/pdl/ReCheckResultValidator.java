package engine.rule.test.validator.pdl;

import java.util.Map;

import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.util.CommonUtil;
import engine.rule.model.BaseForm;
import engine.rule.model.inout.pdl.DecisionInOutForm;

public class ReCheckResultValidator extends DecisionResultValidator{

	@Override
	public boolean validate(BaseForm form, Map<String, String> columnValueMappings) {
		
    	DecisionInOutForm decisionInOutForm = (DecisionInOutForm)form;
    	
		boolean result = super.validate(form, columnValueMappings);
		if(!result)
		{
			return false;
		}else if(decisionInOutForm.getDecisionResult() == RuleEngineDecisionResult.RecheckingRequired.getValue()){
			
			if(columnValueMappings.containsKey(REUPLOAD)){
				String reupload = columnValueMappings.get(REUPLOAD);
				try{
					int realSum = CommonUtil.composeBinarySum(decisionInOutForm.getNeedReUploadFlagSet());
					if(!reupload.equalsIgnoreCase(NULL) && !reupload.equals(realSum+""))
					{
						return false;
					}else if(reupload.equalsIgnoreCase(NULL) && realSum != 0){
						return false;
					}else{
						return true;
					}
				}		
				catch(Exception e)
				{
					return false;
				}
			}
		}
		return true;
	}
}
