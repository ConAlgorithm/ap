package catfish.flowcontroller.activities.paydayloan;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.MessagerHelper;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class PDLWorkEvidenceCollectCheckActivity extends PDLCollectCheckActivity{
    
    protected String statusJob ;
    
    @Override
    protected void init(Application application, IServiceProvider sp) {
        tryToInit(application, sp);
    }

    @Override
    protected boolean isDummyProcess(Application application, IServiceProvider sp) {
        tryToInit(application, sp);
        return false;
    }

    @Override
    protected void onAllChecked(Application application, IServiceProvider sp) {
            this.done = true;
    }
    
    private void tryToInit(Application application, IServiceProvider sp) {
        
        QueueMessager requiredStatusMsg = MessagerHelper.findQueueMessager(this.uploadLastSearchIndexMap, application, statusJob);
        
        if(requiredStatusMsg != null) {
            applyRequiredStatus(requiredStatusMsg.getJobDataInt());
        }
    }

    public void setStatusJob(String statusJob) {
        this.statusJob = statusJob;
    }
    
    
}
