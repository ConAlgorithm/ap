package jma.handlers;

import grasscarp.application.model.PDLApplication;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PDLAppService;
import jma.models.AppId;
import jma.models.GPSRecord;
import jma.util.GPSUtils;
import jma.util.StatisticsUtil;
import catfish.base.Logger;

public class CalculatePDLGPSInfoHandler extends JobHandler{
    
    private final static int METERS_PER_KILOMETER = 1000;
    
    private final static double DISTANCE_THRESHHOLD_METERS = 5*METERS_PER_KILOMETER;
    
    @Override
    public void execute(String appId) throws RetryRequiredException {
        
        try {
            PDLApplication currentApp = PDLAppService.getPDLModel(appId);
            GPSRecord currentGPSRecord = PDLAppService.getGPSRecord(currentApp);
            
            AppId openAccountAppId = PDLAppService.getOpenAccountAppId(appId);
            PDLApplication openAccountApp = PDLAppService.getPDLModel(openAccountAppId.getId());
            GPSRecord openAccountGPSRecord = PDLAppService.getGPSRecord(openAccountApp);        
            List<Double> distanceList = new LinkedList<>();
            List<GPSRecord> historicalGpsRecords = PDLAppService.getHistoricalGpsRecordsByOpenAccountApp(openAccountApp);
            for (int i = 0; i < historicalGpsRecords.size(); i++) {
                Double distance = GPSUtils.getDistance(openAccountGPSRecord, historicalGpsRecords.get(i));
                if(distance == null) {
                    continue;
                }
                distanceList.add(distance);
            }
            Double currentDistance = GPSUtils.getDistance(currentGPSRecord, openAccountGPSRecord);
            Double maxDistance = StatisticsUtil.max(distanceList);
            Double avgDistance = StatisticsUtil.avg(distanceList);
            int biggerThan5KmCount = StatisticsUtil.biggerCount(distanceList, DISTANCE_THRESHHOLD_METERS);
            
            if(currentDistance != null) {
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_CurrentGPSAwayFromApplicationPostionDistance", 
                                new BigDecimal(currentDistance.doubleValue()/METERS_PER_KILOMETER)));
            }
            
            if(maxDistance != null) {
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_HistoricalGPSAwayFromApplicationPostionMaxDistance", 
                                new BigDecimal(maxDistance.doubleValue()/METERS_PER_KILOMETER)));
            }
            
            if(avgDistance != null) {
                AppDerivativeVariableManager.addVariables(
                        new AppDerivativeVariable(appId, 
                                "X_HistoricalGPSAwayFromApplicationPostionAverageDistance", 
                                new BigDecimal(avgDistance.doubleValue()/METERS_PER_KILOMETER)));
            }
            
            AppDerivativeVariableManager.addVariables(
                    new AppDerivativeVariable(appId, 
                            "X_HistoricalGPSDistanceFromApplicationPlaceOver5KMTotalCount", 
                            biggerThan5KmCount)
                    );
            
        } catch (Exception ex) {
            Logger.get().warn(this.getClass().getName() + 
                    " error for application : " + appId, ex);
        }
    }
}
