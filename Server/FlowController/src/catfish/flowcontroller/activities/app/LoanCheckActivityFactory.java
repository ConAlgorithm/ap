package catfish.flowcontroller.activities.app;

import java.util.Map;

import catfish.flowcontroller.activities.ActivityFactoryBase;

public class LoanCheckActivityFactory extends ActivityFactoryBase<LoanCheckActivity>{

	@Override
    protected void setProperties(LoanCheckActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object ruleQueueNameObj = activityConfig.get("ruleQueueName");
        if(ruleQueueNameObj != null)
        {
        	activity.ruleQueueName = ruleQueueNameObj.toString();
        }
        
        Object checkIOUQueueNameObj = activityConfig.get("checkIOUQueueName");
        if(checkIOUQueueNameObj != null)
        {
        	activity.checkIOUQueueName = checkIOUQueueNameObj.toString();
        }
        
        Object checkBucleQueueNameObj = activityConfig.get("checkBuckleQueueName");
        if(checkBucleQueueNameObj != null)
        {
        	activity.checkBuckleQueueName = checkBucleQueueNameObj.toString();
        }
        
        Object jobObj = activityConfig.get("job");
        if(jobObj != null)
        {
        	activity.job = jobObj.toString();
        }
    }
}
