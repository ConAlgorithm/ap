package catfish.flowcontroller.activities.app;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.ActivityDescriptor;

import static catfish.flowcontroller.activities.PhotoCheckBaseActivity.HeadPhotoSubmitted;
import static catfish.flowcontroller.activities.PhotoCheckBaseActivity.IdPhotoSubmitted;
import static catfish.flowcontroller.activities.PhotoCheckBaseActivity.BankCardPhotoSubmitted;

public class AppCheckPhotoActivityDescriptor extends ActivityDescriptor{
	
	public AppCheckPhotoActivityDescriptor() {
		super("CheckInformation");
	}

	@Override
    public String getDescription(String activity, Map<String, Object> state, Properties resources, List<QueueMessager> messages){
		
		boolean submitted = false;
		String resourceId = null;
		String eventName = null;
		if(activity.equals("CheckHeadPhoto"))
		{
			eventName = HeadPhotoSubmitted;
			resourceId = "HeadPhoto";
		}
		else if(activity.equals("CheckIDCardPhoto"))
		{
			eventName = IdPhotoSubmitted;
			resourceId = "IDCardPhoto";
		}
		else if(activity.equals("CheckBankCard"))
		{
			eventName = BankCardPhotoSubmitted;
			resourceId = "BankCard";
		}
		else
		{
			return null;
		}
		
		for(QueueMessager message : messages)
		{
			String jobName = message.getJobName();
			if(jobName != null && jobName.equalsIgnoreCase(eventName))
			{
				submitted = true;
			}
		}
		
		if(!submitted)
		{
			return " "+resources.getProperty("UploadFile") + resources.getProperty(resourceId);
		}
		else{
			return null;
		}
	}
}