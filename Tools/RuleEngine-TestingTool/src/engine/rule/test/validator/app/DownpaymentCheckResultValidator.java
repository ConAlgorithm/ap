package engine.rule.test.validator.app;

import java.util.Map;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.business.common.RuleEngineDecisionResult;
import engine.rule.model.BaseForm;
import engine.rule.model.inout.app.DecisionInOutForm;

public class DownpaymentCheckResultValidator extends DecisionResultValidator{

    @Override
    public boolean validate(BaseForm form, Map<String, String> columnValueMappings) {
        Gson gson = new Gson();
        Logger.get().info(String.format("DownpaymentCheckResultValidator.from=%s,columnValueMappings=%s ",gson.toJson(form), gson.toJson(columnValueMappings)  ));

        boolean result = super.validate(form, columnValueMappings);
        DecisionInOutForm outForm = (DecisionInOutForm)form;
        Logger.get().info(String.format("result=%s, outFrom=%s", result, gson.toJson(outForm)));
        if(!result)
        {
            Logger.get().info(String.format("cause of result=%s", result));
            return false;
        }else if(outForm.getDecisionResult() != RuleEngineDecisionResult.NORewrite.getValue()){
            Logger.get().info(String.format("outForm.getDecisionResult=%s", outForm.getDecisionResult() ));
            return false;
        }
        
        return true;
    }
   
}
