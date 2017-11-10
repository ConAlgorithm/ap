package catfish.flowcontroller.activities.app;

import java.util.Map;

import catfish.flowcontroller.activities.ActivityFactoryBase;

public class AppPhotoEvidenceCollectCheckActivityFactory extends ActivityFactoryBase<AppPhotoEvidenceCollectCheckActivity>{

	@Override
    protected void setProperties(AppPhotoEvidenceCollectCheckActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);
        
        Map<String, String> jobQueueMap = this.loadMap(activityConfig.get("jobQueueMap"));
        activity.setJobQueueMap(jobQueueMap);
        
        Map<String, String> eventJobMap = this.loadMap(activityConfig.get("eventJobMap"));
        activity.setEventJobMap(eventJobMap);
        
        activity.setStatusJob(activityConfig.get("statusJob").toString());
    }
}
