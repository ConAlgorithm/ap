package jma.handlers;

import grasscarp.application.model.PDLApplication;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PDLAppService;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;

public class CalculatePDLPreviousBasicInfoHandler extends JobHandler{

    @Override
    public void execute(String appId) throws RetryRequiredException {
        try{
            PDLApplication previousApp = PDLAppService.getPreviousPDLModel(appId);
            
            if(previousApp != null) {
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_LastLoanAmount", 
                                previousApp.getPrincipal()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_LastLoanLimitAmount", 
                                previousApp.getMaxPrincipal()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_LastLoanDays", 
                                previousApp.getLoanDay()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_LastLoanTime", 
                                previousApp.getProductSelectedTime()));
            }
        }catch(Exception e){
        	Logger.get().warn(String.format("CalculatePDLPreviousBasicInfo error of %s, the result will be ignored!",appId), e);
        }
    }
    
}
