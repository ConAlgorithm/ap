package catfish.flowcontroller.activities.app;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.ActivityDescriptor;

import static catfish.flowcontroller.activities.ApprovalEvidenceCheckBaseActivity.IOUSubmitted;

public class AppCheckIOUActivityDescriptor extends ActivityDescriptor{
	
	public AppCheckIOUActivityDescriptor() {
		super("CheckInformation");
	}

	@Override
    public String getDescription(String activity, Map<String, Object> state, Properties resources, List<QueueMessager> messages){
		
		boolean submitted = false;
		
		for(QueueMessager message : messages)
		{
			String jobName = message.getJobName();
			if(jobName != null && jobName.equalsIgnoreCase(IOUSubmitted))
			{
				submitted = true;
			}
		}
		
		if(!submitted)
		{
			return resources.getProperty("UploadFile") + resources.getProperty("IOU");
		}
		else{
			return null;
		}
	}
}