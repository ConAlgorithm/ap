package catfish.flowcontroller.activities.weixin;

import java.util.Map;

import catfish.flowcontroller.activities.ActivityFactoryBase;

public class CheckAdditionalContactActivityFactory extends ActivityFactoryBase<CheckAdditionalContactActivity>{

	@Override
    protected void setProperties(CheckAdditionalContactActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object queueNameObj = activityConfig.get("queueName");
        if(queueNameObj != null){
            activity.queueName = queueNameObj.toString();
        }        
    }
}