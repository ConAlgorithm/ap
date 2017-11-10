package jma.handlers;

import java.util.Date;
import grasscarp.application.model.PDLApplication;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.FinanceService;
import jma.dataservice.PDLAppService;
import jma.models.FinanceServiceResponse;

public class CalculatePDLPreviousPaybackInfoHandler extends JobHandler{

    @Override
    public void execute(String appId) throws RetryRequiredException {
        try{
            PDLApplication app = PDLAppService.getPDLModel(appId);
            FinanceServiceResponse<Date> response = FinanceService.getLastPayBackDateByUserId(app.getUserId());
            
            if(response != null && response.getStatus() == 0) {
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_LastRepaymentTime", 
                                response.getData()));
            }
            
        }catch(Exception e){
            Logger.get().error(String.format("CalculatePDLPreviousBasicInfo error of %s, the result will be ignored!",appId));
        }
    }
}
