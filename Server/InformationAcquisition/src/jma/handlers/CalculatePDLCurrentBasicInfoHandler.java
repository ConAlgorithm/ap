package jma.handlers;

import java.util.Calendar;
import java.util.Date;

import grasscarp.application.model.PDLApplication;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PDLAppService;

public class CalculatePDLCurrentBasicInfoHandler extends JobHandler{

    @Override
    public void execute(String appId) throws RetryRequiredException {
        try{
            PDLApplication app = PDLAppService.getPDLModel(appId);
            
            if(app != null) {
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_ThisLoanAmount", 
                                app.getPrincipal()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_ThisLoanLimitAmount", 
                                app.getMaxPrincipal()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_ThisLoanDays", 
                                app.getLoanDay()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_ThisLoanTime", 
                                app.getProductSelectedTime()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_MonthlyPayDay", 
                                app.getPayDay()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_ThisLoanDayAwayFromPayDayDays", 
                                daysFromLoanDayToNextPayDay(
                                        app.getInstallmentStartedOn(), 
                                        app.getPayDay())));
            }
            
        }catch(Exception e){
            Logger.get().error(String.format("CalculatePDLCurrentBasicInfo error of %s, the result will be ignored!",appId));
        }
    }
    
    private int daysFromLoanDayToNextPayDay(Date loanDay, int payDay) {
        
        Date startDate = new Date();
        startDate.setTime(loanDay.getTime());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        if(payDay >= calendar.get(Calendar.DAY_OF_MONTH)) {
            return payDay - calendar.get(Calendar.DAY_OF_MONTH);
        }
        
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, payDay);
        
        return (int) ((calendar.getTime().getTime() - loanDay.getTime())/(1000*60*60*24));
    }
}
