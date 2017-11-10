package catfish.flowcontroller.activities.weixin;

import java.util.Map;

import catfish.flowcontroller.activities.ActivityFactoryBase;

public class ClassifyActivityFactory extends ActivityFactoryBase<ClassifyActivity>{
	@Override
    protected void setProperties(ClassifyActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object ruleQueueNameObj = activityConfig.get("ruleQueueName");
        if(ruleQueueNameObj != null){
            activity.ruleQueueName = ruleQueueNameObj.toString();
        }
        Object classifyQueueNameObj = activityConfig.get("classifyQueueName");
        if(classifyQueueNameObj != null){
        	activity.classifyQueueName = classifyQueueNameObj.toString();
        }
        Object setImageUploadFirstStageReadyQueueNameObj = activityConfig.get("setImageUploadFirstStageReadyQueueName");
        if(setImageUploadFirstStageReadyQueueNameObj != null)
        {
        	activity.setImageUploadFirstStageReadyQueueName = setImageUploadFirstStageReadyQueueNameObj.toString();
        }
        activity.job = activityConfig.get("job").toString();
    }
}