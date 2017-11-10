package catfish.flowcontroller.activities.paydayloan;

import java.util.Map;

import catfish.flowcontroller.activities.ActivityFactoryBase;

public class PDLWorkEvidenceCollollectCheckActivityFactory extends ActivityFactoryBase<PDLWorkEvidenceCollectCheckActivity>{

    @Override
    protected void setProperties(PDLWorkEvidenceCollectCheckActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);
        
        Map<String, String> jobQueueMap = this.loadMap(activityConfig.get("jobQueueMap"));
        activity.setJobQueueMap(jobQueueMap);
        
        Map<String, String> eventJobMap = this.loadMap(activityConfig.get("eventJobMap"));
        activity.setEventJobMap(eventJobMap);
        
        activity.setStatusJob(activityConfig.get("statusJob").toString());
    }
}
