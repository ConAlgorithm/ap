package catfish.flowcontroller.activities;

import java.util.Map;

import catfish.base.business.common.JobStatus;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class CheckD1FeedbackActivity extends Activity{

	public String message;
	
	@Override
	protected void init(Application application, IServiceProvider sp) {
			
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		QueueMessager feedbackpassMsg = MessagerHelper.findQueueMessager(this, application, message);
		if(feedbackpassMsg != null)
		{
			if(JobStatus.D1Feedbacked.equalsIgnoreCase(feedbackpassMsg.getJobStatus()))
			{
			     this.done = true;
			     return;
			}
		}
	}
}

class CheckD1FeedbackActivityFactory extends ActivityFactoryBase<CheckD1FeedbackActivity>{
	@Override
    protected void setProperties(CheckD1FeedbackActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);
                    
        Object message = activityConfig.get("message");
        if(message != null){
        	activity.message = message.toString();
        }
    }
}
