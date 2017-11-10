package catfish.flowcontroller.activities.weixin;

import java.util.Map;

import catfish.flowcontroller.activities.ActivityFactoryBase;

public class WeixinReCheckActivityFactory extends ActivityFactoryBase<WeixinReCheckActivity>{
	@Override
    protected void setProperties(WeixinReCheckActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object ruleQueueNameObj = activityConfig.get("ruleQueueName");
        if(ruleQueueNameObj != null)
        {
        	activity.ruleQueueName = ruleQueueNameObj.toString();
        }
        
      /*  Object reCheckImageQueueNameObj = activityConfig.get("reCheckImageQueueName");
        if(reCheckImageQueueNameObj != null)
        {
        	activity.reCheckImageQueueName = reCheckImageQueueNameObj.toString();
        }
        
        Object reCheckBankCardQueueNameObj = activityConfig.get("reCheckBankCardQueueName");
        if(reCheckBankCardQueueNameObj != null)
        {
        	activity.reCheckBankCardQueueName = reCheckBankCardQueueNameObj.toString();
        }*/
        
        Object jobObj = activityConfig.get("job");
        if(jobObj != null)
        {
        	activity.job = jobObj.toString();
        }
    }
}