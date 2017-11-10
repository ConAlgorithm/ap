package catfish.flowcontroller.activities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DefaultWorkflowProvider;
import catfish.flowcontroller.activities.app.AppCheckBuckleActivityDescriptor;
import catfish.flowcontroller.activities.app.AppCheckIOUActivityDescriptor;
import catfish.flowcontroller.activities.app.AppCheckPhotoActivityDescriptor;
import catfish.flowcontroller.activities.app.AppCollectCheckActivityDescriptor;
import catfish.flowcontroller.activities.app.AppPreCheckActivityDescriptor;
import catfish.flowcontroller.activities.app.LoanCheckActivityDescriptor;
import catfish.flowcontroller.activities.paydayloan.PDLCheckBuckleActivityDescriptor;
import catfish.flowcontroller.activities.paydayloan.PDLCollectCheckActivityDescriptor;
import catfish.flowcontroller.activities.paydayloan.PDLPaymentActivityDescriptor;
import catfish.flowcontroller.models.Application;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ActivityDescriptorManager {
    private static final String UTF_8 = "UTF-8";
    private static final String ResourceProperties = "resources.properties";
    
    public static ActivityDescriptorManager instance = new ActivityDescriptorManager();

    private Map<String, ActivityDescriptor> activityDescriptorMap = new HashMap<String, ActivityDescriptor>();
    private Properties resources;
    
    private ActivityDescriptorManager(){
        initResources();
        activityDescriptorMap.put("PreCheck", new AppPreCheckActivityDescriptor());
        
        activityDescriptorMap.put("CheckHeadPhoto", new AppCheckPhotoActivityDescriptor());
        activityDescriptorMap.put("CheckIDCardPhoto", new AppCheckPhotoActivityDescriptor());
        activityDescriptorMap.put("CheckBankCard", new AppCheckPhotoActivityDescriptor());
        activityDescriptorMap.put("CollectCheck", new AppCollectCheckActivityDescriptor("UploadFile"));
        activityDescriptorMap.put("ReCheck", new AppCollectCheckActivityDescriptor("Reupload"));
        activityDescriptorMap.put("CheckUser", new ActivityDescriptor("CheckUser"));
        activityDescriptorMap.put("CheckMerchant", new ActivityDescriptor("CheckMerchant"));
        activityDescriptorMap.put("CheckD1FeedbackPass", new ActivityDescriptor("D1Feedback"));
        
        activityDescriptorMap.put("CheckBuckle", new AppCheckBuckleActivityDescriptor());
        activityDescriptorMap.put("CheckBuckleInBoth", new AppCheckBuckleActivityDescriptor());
        
        activityDescriptorMap.put("CheckIOU", new AppCheckIOUActivityDescriptor());
        activityDescriptorMap.put("CheckIOUInBoth", new AppCheckIOUActivityDescriptor());
        
        activityDescriptorMap.put("LoanCheck", new LoanCheckActivityDescriptor());
        activityDescriptorMap.put("Payment", new ActivityDescriptor("DoPayment"));
        //activityDescriptorMap.put("DoManualPayment", new ActivityDescriptor("DoPayment"));
        
        //Only for PDL
        activityDescriptorMap.put("CheckBuckleForPDL", new PDLCheckBuckleActivityDescriptor());
        activityDescriptorMap.put("PDLOpenCardSuccess", new PDLPaymentActivityDescriptor());
        activityDescriptorMap.put("CollectWorkEvidence", new PDLCollectCheckActivityDescriptor("UploadFile"));
        activityDescriptorMap.put("PdlReCheck", new PDLCollectCheckActivityDescriptor("Reupload"));
    }

    private void initResources(){
        if(resources == null){
            resources = new Properties();
            InputStream stream = null;
            try {
                stream = new FileInputStream(ResourceProperties);
                resources.load(new InputStreamReader(stream, UTF_8));
              } catch (FileNotFoundException e) {
                  Logger.get().error("resource file not found: "+ResourceProperties, e);
              }catch (IOException e) {
                  Logger.get().error("IO Exception when loading resource file: "+ResourceProperties, e);
              }finally{
                  try {
                      if(stream != null){
                          stream.close();
                      }
                } catch (IOException e) {
                    Logger.get().error("fail to close resource stream", e);
                }
              }
        }
    }
    
    private String getDescription(String activity, String stateJson, List<QueueMessager> messages){
        ActivityDescriptor descriptor = null;
        if(activityDescriptorMap.containsKey(activity)){
            descriptor = activityDescriptorMap.get(activity);
        } else {
            return null;
        }
        Map<String, Object> stateMap = null;
        if(stateJson !=null){
            try{
                stateMap = new Gson().fromJson(stateJson, new TypeToken<HashMap<String, Object>>() { }.getType());
            } catch (JsonSyntaxException e) {
                Logger.get().error("Can't parse activity state for json" + stateJson, e);
            }
        }
        return descriptor.getDescription(activity, stateMap, resources, messages);
    }
    
    public String getDescription(Application application){
        if(application ==null){
            Logger.get().warn("Application data is null");
            return "";
        }
        
        if(DefaultWorkflowProvider.instance
                .getByAppId(application.appId)
                .isApplicationCompleted(application)) {
            return "";
        }
        
        List<String> activities = new ArrayList<String>(application.getState().keySet());
        if(activities.size() == 0){
            Logger.get().warn("Application activities is null");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(String activity:activities){
            String state = application.getState(activity);
            String description = this.getDescription(activity, state, application.messages);
            if(description!=null){
                if(sb.length()>0){
                    sb.append(",");
                }
                sb.append(description);
            }
        }
        if(sb.length() > 0){
            return sb.toString();
        } else {
            return resources.getProperty("CheckInformation");
        }
    }
    
    public Map<String, Long> getWaitingTimes(Application application){
        if(application ==null){
            Logger.get().warn("Application data is null");
            return null;
        }
        
        Map<String, String> state = application.getState();
        if(state == null || state.size()==0){
            Logger.get().warn("Application state is empty");
            return null;
        }
        
        ActivityDescriptor ad = new ActivityDescriptor(null);
        Set<String> keys= state.keySet();
        Map<String, Long> result = new HashMap<String, Long>();
        for(String activity : keys){
            String activityStates = state.get(activity);
            if(activityStates ==null){
                continue;
            }
            try{
                Map<String, Object> stateMap = new Gson().fromJson(activityStates, new TypeToken<HashMap<String, Object>>() { }.getType());
                long waitingTime =ad.getWaitingTime(activity, stateMap, application.messages);
                result.put(activity, waitingTime);
            } catch (JsonSyntaxException e) {
                Logger.get().error("Can't parse activity state for json" + activityStates, e);
            }
        }
        return result;
    }
}
