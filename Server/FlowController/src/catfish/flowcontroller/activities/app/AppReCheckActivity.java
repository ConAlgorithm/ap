package catfish.flowcontroller.activities.app;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import catfish.base.business.common.JobStatus;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DomainConsts;
import catfish.flowcontroller.activities.MessagerHelper;
import catfish.flowcontroller.activities.StateHelper;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class AppReCheckActivity extends AppCollectCheckActivity{
	
    protected int photoCheckCount = 0;
    
    public static final String PhotoCheckCount = "photoCheckCount";
    
	public AppReCheckActivity()
	{		
	    initLastSearchIndex(lastSearchIndex);
	}
	
	@Override
	public void saveState(Map<String, Object> dataMap) {
	    super.saveState(dataMap);
	    StateHelper.save(dataMap, PhotoCheckCount, photoCheckCount);
	}
	
	@Override
	public void loadState(Map<String, Object> dataMap) {
	    super.loadState(dataMap);
	    photoCheckCount = StateHelper.loadInt(dataMap, PhotoCheckCount, 0);
	}
	
	@Override
	protected void init(Application application, IServiceProvider sp) {

	    for(Entry<String, String> entry : eventJobMap.entrySet()) {
	        MessagerHelper.findQueueMessager(this, application, entry.getValue());
	    }
	    
	    initLastSearchIndex(this.lastSearchIndex);
	}
	
    @Override
    protected void onAllChecked(Application application, IServiceProvider sp) {
        IQueueService queueService = sp.getService(IQueueService.class);
        IQueue jobQueue = queueService.getQueue(this.jobQueueMap.get(job), "FlowController");
        QueueMessager data = new QueueMessager(application.appId, job, photoCheckCount++);
        jobQueue.sendMessage(job, data);
        this.clearFlags();
    }
    
    @Override
    public void reset()
    {
        super.reset();
        this.photoCheckCount = 0;
    }
    
    @Override
    protected boolean isDummyProcess(Application application,
            IServiceProvider sp) {
        QueueMessager requiredStatusMsg = MessagerHelper.findQueueMessager(this, application, job);
        if(requiredStatusMsg != null)
        {      
            String status = requiredStatusMsg.getJobStatus();
            switch(status)
            {
            case JobStatus.Approved:
                this.done = true;
                return true;
            case JobStatus.Rejected:
                this.done = true;
                return true;
            case JobStatus.RecheckingRequired:
                
                applyRequiredStatus(requiredStatusMsg.getJobDataInt());
                
                initLastSearchIndex(this.lastSearchIndex);
                
                onReCollectNeeded(application, sp);
                
                return false;
            default:
                this.terminate();
                return true;
            }               
        }
        return false;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    
    private void clearFlags()
    {
        requiredStatus = 0;
        uploadedStatus = 0;
        checkedStatus = 0;
        targetStatus = 0;
        notSentJobs = new HashSet<>();
    }
    
    private void onReCollectNeeded(Application application,
            IServiceProvider sp) {
        IMessageService messageService = sp.getService(IMessageService.class);
        messageService.sendMessage(DomainConsts.UploadFiles, application.appId, String.valueOf(requiredStatus | DomainConsts.BIT_REUPLOAD));        
    }
}
