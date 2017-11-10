package catfish.flowcontroller.activities.app;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.ActivityDescriptor;

public class AppPreCheckActivityDescriptor extends ActivityDescriptor{

    private static final String APPLICATION_AGREED_MESSAGE = "ApplicationAgreed"; 
    
    public AppPreCheckActivityDescriptor() {
        super("CheckInformation");
    }
    
    @Override
    public String getDescription(String activity, Map<String, Object> state, Properties resources, List<QueueMessager> messages){
        
        boolean isApplicationSubmitted = false;
        for(QueueMessager message : messages) {
            String jobName = message.getJobName();
            if (jobName != null && jobName.equalsIgnoreCase(APPLICATION_AGREED_MESSAGE)) {
                isApplicationSubmitted = true;
                break;
            }
        }
        
        if (isApplicationSubmitted)
        {
            return resources.getProperty("CheckInformation");
        }
        else{
            return resources.getProperty("ApplicationStarted");
        }
    }

}
