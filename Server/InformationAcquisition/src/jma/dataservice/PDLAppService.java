package jma.dataservice;

import grasscarp.application.model.PDLApplication;
import grasscarp.application.model.PDLCountModel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import jma.models.AppId;
import jma.models.GPSRecord;
import jma.util.DateUtils;
import jma.util.HttpUtils;
import jma.util.StatisticsUtil;
import catfish.base.CollectionUtils;

public class PDLAppService {
    
    private static final int MILLISECONDS_PER_MINUTE = 60*1000;  
	
    public static AppId getOpenAccountAppId(String appId) {     
        StringBuilder sb = new StringBuilder(HttpUtils.appServiceUrl);       
        sb.append(appId).append(HttpUtils.SYMBOL_PATH_SEPARATOR)
        .append("firstId");   
        return HttpUtils.invokeGet(sb.toString(), AppId.class);
    }
    
    public static PDLApplication getPDLModel(String appId) {       
        StringBuilder sb = new StringBuilder(HttpUtils.appServiceUrl);
        sb.append(appId);      
        return HttpUtils.invokeGet(sb.toString(), PDLApplication.class);
    }
    
    public static PDLApplication getPreviousPDLModel(String appId) {       
        StringBuilder sb = new StringBuilder(HttpUtils.appServiceUrl);
        sb.append(appId).append(HttpUtils.SYMBOL_PATH_SEPARATOR)
        .append("previous");      
        return HttpUtils.invokeGet(sb.toString(), PDLApplication.class);
    }
    
    public static List<PDLApplication> getHistoricalApprovedApps(String userId) {
        Map<String, String> params = CollectionUtils.mapOf("userId", userId, "type", "PaydayLoan");           
        StringBuilder sb = new StringBuilder(HttpUtils.appServiceUrl);
        sb.append("list").append(HttpUtils.SYMBOL_QUESTION_MARK)
        .append(HttpUtils.buildGetParamString(params));
        
        return HttpUtils.invokeGet(sb.toString(), new TypeToken<List<PDLApplication>>(){}.getType());
    }
    
    public static PDLCountModel getHistoricalStatistics(String userId) {
        Map<String, String> params = CollectionUtils.mapOf("userId", userId, "type", "PaydayLoan");           
        StringBuilder sb = new StringBuilder(HttpUtils.appServiceUrl);
        sb.append("success-count").append(HttpUtils.SYMBOL_QUESTION_MARK)
        .append(HttpUtils.buildGetParamString(params));
        
        return HttpUtils.invokeGet(sb.toString(), PDLCountModel.class);
    }
    
    public static GPSRecord getGPSRecord(String appId) {
        PDLApplication pdl = getPDLModel(appId);
        return getGPSRecord(pdl);
    }
    
    public static GPSRecord getGPSRecord(PDLApplication pdl) {
        if(pdl == null) {
            return null;
        }
        
        List<GPSRecord> recordList = new LinkedList<GPSRecord>();
        
        //tickets from 1970-01-01 UTC(GMT time)
        long productSelectedOn = DateUtils.toUTC(pdl.getProductSelectedTime()).getTime();
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", pdl.getUserId());
        params.put("start", Long.toString(productSelectedOn - MILLISECONDS_PER_MINUTE));
        params.put("end", Long.toString(productSelectedOn + MILLISECONDS_PER_MINUTE));
        
        StringBuilder sb = new StringBuilder();
        sb.append(HttpUtils.gpsServiceUrl).append(HttpUtils.SYMBOL_PATH_SEPARATOR)
        .append("gps").append(HttpUtils.SYMBOL_QUESTION_MARK)
        .append(HttpUtils.buildGetParamString(params));
        
        recordList = HttpUtils.invokeGet(sb.toString(), new TypeToken<List<GPSRecord>>(){}.getType());
        
        return getAverateGpsRecord(recordList);
    }
    
    private static GPSRecord getAverateGpsRecord(List<GPSRecord> recordList) {
        
        GPSRecord record = new GPSRecord();
        record.setLatituede(StatisticsUtil.avg(recordList, "latituede", Double.class));
        record.setLongitude(StatisticsUtil.avg(recordList, "longitude", Double.class));
        
        return record;
    }
    
    public static List<GPSRecord> getHistoricalGpsRecordsByOpenAccountApp(PDLApplication openAccountApp) {
        List<GPSRecord> records = new LinkedList<>();
        List<PDLApplication> apps = getHistoricalApprovedApps(openAccountApp.getUserId());
        for(PDLApplication app : apps) {
            
            if(app.getInstallmentStartedOn().before(openAccountApp.getInstallmentStartedOn())) {
                continue;
            }
            
            GPSRecord record = getGPSRecord(app);
            if(record != null) {
                records.add(record);
            }
        }
        
        return records;
    }
}
