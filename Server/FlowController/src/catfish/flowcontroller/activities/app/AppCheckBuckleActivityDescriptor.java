package catfish.flowcontroller.activities.app;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.ActivityDescriptor;

import static catfish.flowcontroller.activities.ApprovalEvidenceCheckBaseActivity.BuckleSubmitted;

public class AppCheckBuckleActivityDescriptor extends ActivityDescriptor{
	
	public AppCheckBuckleActivityDescriptor() {
		super("CheckInformation");
	}

	@Override
    public String getDescription(String activity, Map<String, Object> state, Properties resources, List<QueueMessager> messages){
		
		boolean submitted = false;
		
		for(QueueMessager message : messages)
		{
			String jobName = message.getJobName();
			if(jobName != null && jobName.equalsIgnoreCase(BuckleSubmitted))
			{
				submitted = true;
			}
		}
		
		if(!submitted)
		{
			return resources.getProperty("UploadFile") + resources.getProperty("Buckle");
		}
		else{
			return null;
		}
	}
}
