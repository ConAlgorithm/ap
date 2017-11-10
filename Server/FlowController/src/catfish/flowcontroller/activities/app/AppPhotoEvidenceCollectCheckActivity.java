package catfish.flowcontroller.activities.app;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.MessagerHelper;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class AppPhotoEvidenceCollectCheckActivity extends AppCollectCheckActivity{

	@Override
	protected void init(Application application, IServiceProvider sp) {
		tryToInit(application, sp);
	}
	
	protected boolean isDummyProcess(Application application, IServiceProvider sp)
    {
    	tryToInit(application, sp);
        return false;
    }
	
	protected void onAllChecked(Application application, IServiceProvider sp)
    {
    	this.done = true;
    }
	
    private void tryToInit(Application application, IServiceProvider sp) {
        
        QueueMessager requiredStatusMsg = MessagerHelper.findQueueMessager(this.uploadLastSearchIndexMap, application, statusJob);
        
        if(requiredStatusMsg != null) {
            applyRequiredStatus(Integer.parseInt(requiredStatusMsg.getJobStatus()));
        }
    }
}
