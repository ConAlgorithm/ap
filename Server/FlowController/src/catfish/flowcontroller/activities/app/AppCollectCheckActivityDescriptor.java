package catfish.flowcontroller.activities.app;

import catfish.flowcontroller.activities.ActivityDescriptor;
import catfish.flowcontroller.activities.StateHelper;
import catfish.flowcontroller.util.CommonUtils;


public class AppCollectCheckActivityDescriptor extends ActivityDescriptor{
    
    private String collectScenario;
    
    public AppCollectCheckActivityDescriptor(String collectScenario) {
        super("CheckInformation");
        this.collectScenario = collectScenario;
    }
      
    @Override
    public String getDescription(String activity, java.util.Map<String,Object> state, java.util.Properties resources, java.util.List<catfish.base.queue.QueueMessager> messages) {
        int requiredStatus = StateHelper.loadInt(state, AppCollectCheckActivity.RequiredStatus, 0);
        int uploadedStatus = StateHelper.loadInt(state, AppCollectCheckActivity.UploadedStatus, 0);
        
        int waitingStatus = requiredStatus ^ uploadedStatus;
        if(waitingStatus == 0) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        int currentPhoto = 1;
        for(int i=0; i<32; i++) {
            if((currentPhoto & waitingStatus) > 0) {
                if(CommonUtils.appValueResourceNameMap.containsKey(currentPhoto)) {
                    if(sb.length() > 0) {
                        sb.append(",");
                    }
                    String resourceName = CommonUtils.appValueResourceNameMap.get(currentPhoto);
                    sb.append(resources.getProperty(resourceName));
                }
            }
            currentPhoto = currentPhoto << 1;
        }
        
        if(sb.length()>0){
            return resources.getProperty(collectScenario) + sb.toString();
        }else{
            return null;
        }
    }

}
