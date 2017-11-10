package catfish.flowcontroller.activities;

import java.util.Map;

import catfish.base.business.common.JobStatus;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.util.JobUtils;
import catfish.framework.IServiceProvider;

public class CheckD1FeedbackPassActivity extends Activity{

	public String message;
	
	@Override
	protected void init(Application application, IServiceProvider sp) {
		
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		if (!JobUtils.readIsD1CheckNessesaryConfiguration())
		{
			this.done = true;
			return;
		}
   
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

class CheckD1FeedbackPassActivityFactory extends ActivityFactoryBase<CheckD1FeedbackPassActivity>
{
	@Override
    protected void setProperties(CheckD1FeedbackPassActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);
                    
        Object message = activityConfig.get("message");
        if(message != null){
        	activity.message = message.toString();
        }
    }
}