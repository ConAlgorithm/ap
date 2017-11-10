package catfish.flowcontroller.activities.paydayloan;

import java.util.Map;

import catfish.flowcontroller.activities.ActivityFactoryBase;

public class PDLReCheckActivityFactory extends ActivityFactoryBase<PDLReCheckActivity>{

    @Override
    protected void setProperties(PDLReCheckActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);
        
        Map<String, String> jobQueueMap = this.loadMap(activityConfig.get("jobQueueMap"));
        activity.setJobQueueMap(jobQueueMap);
        
        Map<String, String> eventJobMap = this.loadMap(activityConfig.get("eventJobMap"));
        activity.setEventJobMap(eventJobMap);
        
        activity.setJob(activityConfig.get("job").toString());
    }
}
