package catfish.flowcontroller.activities.weixin;

import java.util.Map;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DomainConsts;
import catfish.flowcontroller.activities.PhotoCheckBaseActivity;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class WeixinReCheckActivity extends PhotoCheckBaseActivity{
	
	public String queueName;
	public String job = "ReCheck";
		
	@Override
	public void reset()
	{
		super.reset();
	}
	
	@Override
    public void saveState(Map<String, Object> dataMap){
		super.saveState(dataMap);	
	}
	
	@Override
	public void loadState(Map<String, Object> dataMap){
		super.loadState(dataMap);
	}
		
	@Override
	protected void init(Application application, IServiceProvider sp) {
		super.init(application, sp);	
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		super.process(application, sp); 
	}

	@Override
	protected void onHeadPhotoSubmitted(Application application,
			IServiceProvider sp) {
	}

	@Override
	protected void onIdPhotoSubmitted(Application application,
			IServiceProvider sp) {
	}

	/*@Override
	protected void onBankCardSubmitted(Application application,
			IServiceProvider sp) {
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue recheckBankCardQueue = queueService.getQueue(this.reCheckBankCardQueueName, "FlowController");
		
		if(notSentJobs.contains(RecheckBankCard))
		{
			QueueMessager data = new QueueMessager(application.appId, RecheckBankCard);
    		recheckBankCardQueue.sendMessage(RecheckBankCard, data);
    		notSentJobs.remove(RecheckBankCard);
		}	 
	}*/


	@Override
	protected void onRecheckBankCardDone(Application application,
			IServiceProvider sp) {
	}

	@Override
	protected void onAllReCheckPhotoJobsDone(Application application,
			IServiceProvider sp) {    	
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue recheckQueue = queueService.getQueue(ruleQueueName, "FlowController");
		
		QueueMessager data = new QueueMessager(application.appId, job);
    	recheckQueue.sendMessage(job, data);
    	photoCheckCount ++;
    	this.clearFlags();
	}
	
	@Override
	protected void onNeedReUploadPhotoResponse(Application application,
			IServiceProvider sp) {
		IMessageService messageService = sp.getService(IMessageService.class);
	    messageService.sendMessage(DomainConsts.ReuploadFiles, application.appId, String.valueOf(needUploadStatus));	   
	}

	@Override
	protected void onBankCardSubmitted(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onRecheckHeadPhotoDone(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onRecheckIDCardPhotoDone(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

}

