package catfish.flowcontroller.activities.app;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DomainConsts;
import catfish.flowcontroller.activities.ApprovalEvidenceCheckBaseActivity;
import catfish.flowcontroller.activities.MessagerHelper;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class LoanCheckActivity extends ApprovalEvidenceCheckBaseActivity{
	
	@Override
	protected void init(Application application, IServiceProvider sp) {
		super.init(application, sp);	
		
		MessagerHelper.findQueueMessager(this, application, CheckIOU);
		MessagerHelper.findQueueMessager(this, application, CheckBuckle);
		this.initialLastSearchIndex(this.lastSearchIndex);
	}
	
	@Override
	protected void onIOUSubmitted(Application application, IServiceProvider sp) {
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue checkIOUQueue = queueService.getQueue(this.checkIOUQueueName, "FlowController");
		
		if(notSentJobs.contains(CheckIOU))
		{
			QueueMessager data = new QueueMessager(application.appId, CheckIOU);
			checkIOUQueue.sendMessage(CheckIOU, data);
    		notSentJobs.remove(CheckIOU);
		}	 
	}

	@Override
	protected void onBuckleSubmitted(Application application,
			IServiceProvider sp) {
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue checkBuckleQueue = queueService.getQueue(this.checkBuckleQueueName, "FlowController");
		
		if(notSentJobs.contains(CheckBuckle))
		{
			QueueMessager data = new QueueMessager(application.appId, CheckBuckle);
			checkBuckleQueue.sendMessage(CheckBuckle, data);
    		notSentJobs.remove(CheckBuckle);
		}	
	}

	@Override
	protected void onCheckIOUDone(Application application, IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onCheckBuckleDone(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onAllCheckEvidenceJobsDone(Application application,
			IServiceProvider sp) {
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue ruleQueue = queueService.getQueue(ruleQueueName, "FlowController");
		QueueMessager data = new QueueMessager(application.appId, job, evidenceCheckCount++);
    	ruleQueue.sendMessage(job, data);
    	this.clearFlags();
	}

	@Override
	protected void onNeedReUploadEvidenceResponse(Application application,
			IServiceProvider sp) {
		IMessageService messageService = sp.getService(IMessageService.class);
	    messageService.sendMessage(DomainConsts.UploadFiles, application.appId, String.valueOf(needUploadStatus | DomainConsts.BIT_REUPLOAD));	
	}

}
