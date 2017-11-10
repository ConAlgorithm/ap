package catfish.plugins.finance.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import catfish.base.Logger;
import catfish.base.collections.MapBuilder;
import catfish.base.database.DatabaseApi;
import catfish.plugins.finance.object.JsonDataBuilder;
import catfish.plugins.finance.object.Status;

public class LastPaybackDateHandler implements IHandler{
    private String userId ;
    
    private static final String QUERY_LATEST_COMPLETED_APPID = 
            "select TOP 1 id from InstallmentApplicationObjects " +
            "where userId=:userId and status>=500  and instalmentChannel = 3 order by dateadded desc";
    
    private static final String QUERY_LAST_PAYBACK_DATE = 
            "SELECT max(DatePay) FROM InstalmentObjects WHERE AppId=:AppId ";

    public LastPaybackDateHandler(String userId) {
        super();
        this.userId = userId;
    }

    @Override
    public String handle() {
        String latestCompletedAppId = getLatestCompletedAppId(this.userId);
        Date lastPaybackDate = getLastPaybackDateByAppId(latestCompletedAppId);
        return JsonDataBuilder.build(Status.SUCCESS, "success", lastPaybackDate.getTime()).toString();
    } 
    
    private String getLatestCompletedAppId(String userId) {
        Map<String, Object> params = new MapBuilder<String, Object>().add("userId", userId).build();
        return DatabaseApi.querySingleString(QUERY_LATEST_COMPLETED_APPID, params);
    }
    
    private Date getLastPaybackDateByAppId(String appId) {
        Map<String, Object> params = new MapBuilder<String, Object>().add("AppId", appId).build();
        String dateString = DatabaseApi.querySingleString(QUERY_LAST_PAYBACK_DATE, params);
        
        Date result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            result = sdf.parse(dateString);
        } catch (ParseException e) {
            Logger.get().warn("parse date string error : " + dateString, e);
        }
        
        return result;
    }
}
