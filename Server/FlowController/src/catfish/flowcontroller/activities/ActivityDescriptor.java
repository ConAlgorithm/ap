package catfish.flowcontroller.activities;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import catfish.base.queue.QueueMessager;

public class ActivityDescriptor {
    protected String resourceId;
    public ActivityDescriptor(String resourceId){
        this.resourceId = resourceId;
    }
    public String getDescription(
            String activity, 
            Map<String, Object> state, 
            Properties resources, 
            List<QueueMessager> messages){
        return resources.getProperty(this.resourceId);
    }
    
    public long getWaitingTime(String activity, 
            Map<String, Object> state, List<QueueMessager> messages){
        if(state ==null){
            return 0;
        }
        int status = StateHelper.loadInt(state, Activity.StatusKey, -1);
        if(status != 0){
            return 0;
        }
        long startTime = StateHelper.loadLong(state, Activity.StartTime, -1);
        long now = new Date().getTime();
        return (now - startTime);
    }
}
