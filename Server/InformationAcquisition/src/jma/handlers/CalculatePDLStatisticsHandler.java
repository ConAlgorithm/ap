package jma.handlers;

import grasscarp.application.model.CountModel;
import grasscarp.application.model.PDLApplication;
import grasscarp.application.model.PDLCountModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PDLAppService;
import jma.util.StatisticsUtil;

import org.joda.time.DateTime;
import org.joda.time.Days;

import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;

public class CalculatePDLStatisticsHandler extends JobHandler{

    @Override
    public void execute(String appId) throws RetryRequiredException {
        try{
            PDLApplication app = PDLAppService.getPDLModel(appId);
            
            if(app == null) {
                return ;
            }
            
            PDLCountModel pcm = PDLAppService.getHistoricalStatistics(app.getUserId());
            if(pcm != null) {
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_HistoricalLoanTotalCount", 
                                pcm.getTotalCount()));
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_HistoricalFullLoanTotalCount", 
                                pcm.getTotalMaxLoanCount()));
                
                savePrincipalCount(appId, pcm);
                
                saveLimitCount(appId, pcm);
                
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_HistoricalLimitAmountUsageRate", 
                                new BigDecimal(pcm.getMaxPrincipalUsed())));
                
                saveLoandaysCount(appId, pcm);
                
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_CircleLoanAverageDays", 
                                new BigDecimal(pcm.getReLoanRate())));
                
            }
   
            String userId = app.getUserId();
        	List<PDLApplication> pdlApps = PDLAppService.getHistoricalApprovedApps(userId);
        	
        	int historicalContinuousFullLoanMaxCount = calculateHistoricalContinuousFullLoanMaxCount(pdlApps);
        	
        	List<Integer> deltaDays = getLoanDaysAwayFromPayDay(pdlApps);
        	//计算历史借款日离发薪日最大天数
        	int historicalLoanDayAwayFromPayDayMaxDays = StatisticsUtil.max(deltaDays);
        	//计算历史借款日离发薪日最少天数
        	int historicalLoanDayAwayFromPayDayMinDays = StatisticsUtil.min(deltaDays);
        	//计算历史借款日离发薪日平均天数
        	BigDecimal historicalLoanDayAwayFromPayDayAverageDays = new BigDecimal(StatisticsUtil.avg(deltaDays));
        	
        	List<AppDerivativeVariable> variablesList = new ArrayList<>();
	    	variablesList.add(new AppDerivativeVariable(appId, "X_HistoricalContinuousFullLoanMaxCount", historicalContinuousFullLoanMaxCount));
	    	variablesList.add(new AppDerivativeVariable(appId, "X_HistoricalLoanDayAwayFromPayDayMaxDays", historicalLoanDayAwayFromPayDayMaxDays));
	    	variablesList.add(new AppDerivativeVariable(appId, "X_HistoricalLoanDayAwayFromPayDayMinDays", historicalLoanDayAwayFromPayDayMinDays));
	    	variablesList.add(new AppDerivativeVariable(appId, "X_HistoricalLoanDayAwayFromPayDayAverageDays", historicalLoanDayAwayFromPayDayAverageDays));
	    	AppDerivativeVariableManager.addVariables(variablesList);

        }catch(Exception e){
            Logger.get().error(String.format("CalculatePDLStatistics error of %s, the result will be ignored!",appId));
        }
    }
    
    private static void savePrincipalCount(String appId, PDLCountModel pcm) {
        CountModel principalCountModel = pcm.getPrincipal();
        if (principalCountModel != null) {
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalLoanTotalAmount", 
                            new BigDecimal(principalCountModel.getTotal())));
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalLoanAverageAmount", 
                            new BigDecimal(principalCountModel.getAverage())));
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalLoanMaxAmount", 
                            new BigDecimal(principalCountModel.getMax())));
        }
    }
    
    private static void saveLimitCount(String appId, PDLCountModel pcm) {
        CountModel limitCountModel = pcm.getMaxPrincipal();
        if (limitCountModel != null) {
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalLoanTotalLimitAmount", 
                            new BigDecimal(limitCountModel.getTotal())));
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalLoanAverageLimitAmount", 
                            new BigDecimal(limitCountModel.getAverage())));
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalLoanMaxLimitAmount", 
                            new BigDecimal(limitCountModel.getMax())));
        }
    }
    
    private static void saveLoandaysCount(String appId, PDLCountModel pcm) {
        CountModel loanDayModel = pcm.getLoanDay();
        if (loanDayModel != null) {
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalLoanMaxDays", 
                            (int)loanDayModel.getMax()));
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalLoanAverageDays", 
                            new BigDecimal(loanDayModel.getAverage())));
        }
    }
    
    //计算历史连续满额借款最大次数
    private int calculateHistoricalContinuousFullLoanMaxCount(List<PDLApplication> pdlApps)
    {
    	int max = 0, tempMax = 0;
    	boolean isContinous = true;
    	for(PDLApplication item : pdlApps)
    	{
    		if(item.getPrincipal().equals(item.getMaxPrincipal()) && isContinous)
    		{
    			tempMax ++;
    		}else if(!item.getPrincipal().equals(item.getMaxPrincipal())){
    			if(tempMax > max)
    				max = tempMax;
    			tempMax = 0;
    			isContinous = false;
    		}else{
    			tempMax ++;
    			isContinous = true;
    		}
    	}
    	return max > tempMax ? max : tempMax;
    }

    private List<Integer> getLoanDaysAwayFromPayDay(List<PDLApplication> pdlApps)
    {
    	List<Integer> result = new LinkedList<>();
    	DateTime instalTime = null;
    	int payDay = 0, instalDay = 0, delta = 0;
        for(PDLApplication item : pdlApps)
        {
        	instalTime = new DateTime(item.getInstallmentStartedOn());
        	payDay = item.getPayDay();
        	instalDay = instalTime.getDayOfMonth();
        	if(instalDay == payDay)
        	{
        		delta = 0;
        	}else if(instalDay < payDay){
        		delta = payDay - instalDay;        		
        	}else{
        		delta = Days.daysBetween(instalTime, instalTime.plusMonths(1).withDayOfMonth(payDay)).getDays();
        	}
        	result.add(delta);
        }
        return result;
    }
}
