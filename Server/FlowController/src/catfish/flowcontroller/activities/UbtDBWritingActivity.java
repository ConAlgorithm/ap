package catfish.flowcontroller.activities;

import java.util.Map;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class UbtDBWritingActivity extends QueueActivity {

	public UbtDBWritingActivity() {
		this.waitOne = true;
	}
	
	@Override
	protected boolean checkReady(Application application, IServiceProvider sp) {
		
		Predicate<QueueMessager> conditionPredicate =
				new Predicate<QueueMessager>() {
					@Override
					public boolean test(QueueMessager messager) {
						return !messager.getJobName().equals("ManualFirstCheckDone")
							   || (messager.getJobName().equals("ManualFirstCheckDone") && messager.getJobDataInt() == 0);
					}
				};

		if(prerequisiteJobs != null && prerequisiteJobs.length>0){
            if( !MessagerHelper.checkExist(application.messages, prerequisiteJobs, conditionPredicate) ){
                return false;
            }
        }
        if(prerequisiteActivities != null && prerequisiteActivities.length>0){
            if(application.finishedActivities == null || application.finishedActivities.size() == 0){
                return false;
            }
            for(String activityName: prerequisiteActivities){
                if(activityName == null || activityName.length() ==0){
                    continue;
                }

                if(!application.finishedActivities.containsKey(activityName)){
                    return false;
                } else if(waitOne){
                    return true;
                }
            }
        }
        
        return true;
	}
}

class UbtDBWritingActivityFactory extends ActivityFactoryBase<UbtDBWritingActivity> {
    
    @Override
    protected void setProperties(UbtDBWritingActivity activity, Map<String, Object> activityConfig) {
        super.setProperties(activity, activityConfig);

        Object queueNameObj = activityConfig.get("queueName");
        if (queueNameObj != null) {
            activity.queueName = queueNameObj.toString();
        }
        activity.jobs = loadAray(activityConfig.get("jobs"));
        activity.optionalJobs = loadAray(activityConfig.get("optionalJobs"));
    }
}
