package catfish.flowcontroller.activities;

import java.util.Map;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.flowcontroller.activities.ActivityConsts;

public class OnsNotificationActivity extends Activity{

	public String extraInfo;
	public String message;
	public String key;
	
	@Override
	protected void init(Application application, IServiceProvider sp) {
		String extraInfo = null;
		
		if(this.prerequisiteJobs != null && this.prerequisiteJobs.length > 0)
		{
			QueueMessager expected = MessagerHelper.findQueueMessager(this, application, this.prerequisiteJobs[0]);
			if(expected != null)
			{
				if(key == null || key.equals(ActivityConsts.StatusKey))
					extraInfo = expected.getJobStatus();
				else if(key.equals(ActivityConsts.DataIntKey))
					extraInfo = expected.getJobDataInt() + "";
			}
				
		}
		if(this.extraInfo != null)
		{
			extraInfo = this.extraInfo;
		}
			
	    IMessageService messageService = sp.getService(IMessageService.class);
	    messageService.sendMessage(message, application.appId, extraInfo);
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		this.done = true;
	}

}

class OnsNotificationActivityFactory extends ActivityFactoryBase<OnsNotificationActivity>
{
	@Override
    protected void setProperties(OnsNotificationActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        activity.message = activityConfig.get("message").toString();
        
        Object extraInfoObj = activityConfig.get("extraInfo");
        if(extraInfoObj != null)
        	activity.extraInfo = extraInfoObj.toString();
        
        Object keyObj = activityConfig.get(ActivityConsts.KeyWord);
        if(keyObj != null)
        	activity.key = keyObj.toString();
    }
}
