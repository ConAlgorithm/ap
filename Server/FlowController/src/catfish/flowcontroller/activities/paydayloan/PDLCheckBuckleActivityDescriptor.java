package catfish.flowcontroller.activities.paydayloan;

import static catfish.flowcontroller.activities.ApprovalEvidenceCheckBaseActivity.BuckleSubmitted;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.ActivityDescriptor;

public class PDLCheckBuckleActivityDescriptor  extends ActivityDescriptor{
	
	private static final String NeedBuckleSubmitted = "NeedBuckleSubmitted";
	
    public PDLCheckBuckleActivityDescriptor(){
        super("CheckInformation");
    }
    
    @Override
    public String getDescription(String activity, Map<String, Object> state, Properties resources, List<QueueMessager> messages){
        boolean submitted = false;
		boolean needBuckle = false;
		for(QueueMessager message : messages)
		{
			String jobName = message.getJobName();
			if(jobName != null)
			{
				if(jobName.equalsIgnoreCase(BuckleSubmitted))
				{
					submitted = true;
				}
				if(jobName.equalsIgnoreCase(NeedBuckleSubmitted))
				{
					needBuckle = true;
				}
			}
			
		}
		
		if(!submitted && needBuckle)
		{
			return resources.getProperty("UploadFile") + resources.getProperty("Buckle");
		}
		else{
			return null;
		}
    }
}
