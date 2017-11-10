package catfish.flowcontroller.activities;

import java.util.Map;

import catfish.base.StartupConfig;
import catfish.base.business.common.JobStatus;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DomainConsts;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.util.JobUtils;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class LoopActivity extends Activity{
    private final String Retry = "retry";
	public static final String jobQueuename =  
			 StartupConfig.get("catfish.flowcontroller.jobqueue", "JobStatusQueue");
    
    public String job;
    public String queueName;
    public int retry=0;
    
    @Override
    public void reset(){
        super.reset();
        retry=0;
    }
       
    @Override
    public void saveState(Map<String, Object> dataMap){
        super.saveState(dataMap);
        StateHelper.save(dataMap, Retry, retry);
    }
    @Override
    public void loadState(Map<String, Object> dataMap){
        super.loadState(dataMap);
        retry = StateHelper.loadInt(dataMap, Retry, 0);
    }
    
    @Override
    protected void init(Application application, IServiceProvider sp){
        sendJob(application, sp, 0);
    }

    private void sendJob(Application application, IServiceProvider sp, int delay){
        IQueueService queueService = sp.getService(IQueueService.class);
        IQueue queue = queueService.getQueue(queueName, "FlowController");
        
        String appId = application.appId;
        QueueMessager data = new QueueMessager(appId, job);
        data.setCallbackQueue(jobQueuename);
        queue.sendMessage(job, data, QueueConfig.QUEUE_PRIORITY_NORMAL, delay);
    }
    
    @Override
    protected void process(Application application, IServiceProvider sp){
        this.done = false;
        QueueMessager expected = MessagerHelper.findQueueMessager(this, application, job);
        
        if(expected == null){
            return;
        }
        
        if(++retry < JobUtils.getMaxCheck() && JobStatus.RecheckingRequired.equals(expected.getJobStatus())){
            sendJob(application, sp, JobUtils.getRecheckDelay());
        } else {
            this.done = true;
        }
    }
}

class LoopActivityFactory extends ActivityFactoryBase<LoopActivity>{
    @Override
    protected void setProperties(LoopActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object queueNameObj = activityConfig.get("queueName");
        if(queueNameObj != null){
            activity.queueName = queueNameObj.toString();
        }
                     
        Object job = activityConfig.get("job");
        if(job != null){
        	activity.job = job.toString();
        }
    }
}

