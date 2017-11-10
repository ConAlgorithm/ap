package catfish.flowcontroller.activities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import catfish.base.StartupConfig;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DomainConsts;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class ReCheckActivity extends PhotoCheckBaseActivity{

	public static final String RecheckImage = "RecheckImage";
	public static final String RecheckBankCard = "RecheckBankCard";
	
	public static final String AlreadyUploadedStatus = "alreadyUploadedStatus";
	public static final String CheckedPhotoStatus = "checkedPhotoStatus";
	public static final String CheckPhotoAllFinishedFlag = "checkPhotoAllFinishedFlag";
	public static final String CheckPhotoLastSearchIndex = "checkPhotoLastSearchIndex";
	public static final String NotSentJobs = "notSentJobs";
	public static final String jobQueuename =  
			 StartupConfig.get("catfish.flowcontroller.jobqueue", "JobStatusQueue");
	public String queueName;
	public String job = "ReCheck";
	
	private int alreadyUploadedStatus = 0;
	private int checkedPhotoStatus = 0;
	private int checkPhotoAllFinishedFlag = 0;
	
	public String ruleQueueName = "TopRulesDecisionJobRequestQueue";
	public String reCheckImageQueueName = "ImageQueueV2";
	public String reCheckBankCardQueueName = "BankCardQueueV2";
	
	public static Map<String, Integer> CheckPhotoStatus = new HashMap<>();
	//记录还没有发送出去的job
	public static Set<String> notSentJobs = new HashSet<>();
	public Map<String, Integer> checkPhotoLastSearchIndexMap = new HashMap<>();
	
	static{
		CheckPhotoStatus.put(RecheckImage, 1);
		CheckPhotoStatus.put(RecheckBankCard, 2);
	}
	
	public ReCheckActivity()
	{
		initialLastSearchIndex();
	}
	
	private void initialLastSearchIndex()
	{		
		for(String item : CheckPhotoStatus.keySet())
		{
			checkPhotoLastSearchIndexMap.put(item, lastSearchIndex);
		}
	}
	
	@Override
	public void reset()
	{
		super.reset();
		alreadyUploadedStatus = 0;
		checkedPhotoStatus = 0;
		checkPhotoAllFinishedFlag = 0;
		checkPhotoLastSearchIndexMap = new HashMap<>();
		notSentJobs = new HashSet<>();
	}
	
	@Override
    public void saveState(Map<String, Object> dataMap){
		super.saveState(dataMap);
		StateHelper.save(dataMap, AlreadyUploadedStatus, alreadyUploadedStatus);
		StateHelper.save(dataMap, CheckedPhotoStatus, checkedPhotoStatus);
		StateHelper.save(dataMap, CheckPhotoAllFinishedFlag, checkPhotoAllFinishedFlag);
		StateHelper.save(dataMap, CheckPhotoLastSearchIndex, checkPhotoLastSearchIndexMap);
		StateHelper.save(dataMap, NotSentJobs, notSentJobs);
	}
	
	@Override
	public void loadState(Map<String, Object> dataMap){
		super.loadState(dataMap);
		alreadyUploadedStatus = StateHelper.loadInt(dataMap, AlreadyUploadedStatus, 0);
		checkedPhotoStatus = StateHelper.loadInt(dataMap, CheckedPhotoStatus, 0);
		checkPhotoAllFinishedFlag = StateHelper.loadInt(dataMap, CheckPhotoAllFinishedFlag, 0);
		checkPhotoLastSearchIndexMap = StateHelper.loadStringIntMap(dataMap, CheckPhotoLastSearchIndex);
		notSentJobs = StateHelper.loadSet(dataMap, NotSentJobs);
	}
		
	@Override
	protected void init(Application application, IServiceProvider sp) {
		QueueMessager expected = MessagerHelper.findQueueMessager(this, application, this.linkedActivities.get(0));
	    if(expected != null)
	    {	    	
	    	this.needUploadStatus = expected.getJobDataInt();
	        if((needUploadStatus & 3) > 0)
	        {
	        	checkPhotoAllFinishedFlag |= CheckPhotoStatus.get(RecheckImage);
	        	notSentJobs.add(RecheckImage);
	        }
	        	
	        if((needUploadStatus & 4) > 0)
	        {
	        	checkPhotoAllFinishedFlag |= CheckPhotoStatus.get(RecheckBankCard);
	        	notSentJobs.add(RecheckBankCard);
	        }
	        	
	    }
	    IMessageService messageService = sp.getService(IMessageService.class);
	    //Logger.get().info("ReCheck Send ons Message: " + ReuploadFiles + ", needUploadStatus: " + needUploadStatus);
	    messageService.sendMessage(DomainConsts.ReuploadFiles, application.appId, String.valueOf(needUploadStatus));
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue recheckQueue = queueService.getQueue(ruleQueueName, "FlowController");
        IQueue recheckImageQueue = queueService.getQueue(this.reCheckImageQueueName,"FlowController");
        IQueue recheckBankCardQueue = queueService.getQueue(this.reCheckBankCardQueueName, "FlowController");
        
        String appId = application.appId;
        
	    if((needUploadStatus & 3) > 0)
	    {
	    	if(MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, HeadPhotoSubmitted) != null)
	    		alreadyUploadedStatus |= 1;
	    	if(MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, IdPhotoSubmitted) != null)
	    		alreadyUploadedStatus |= 2;
	    	
	    	if((alreadyUploadedStatus&3) == 3  && notSentJobs.contains(RecheckImage))
	    	{
	    		QueueMessager data = new QueueMessager(appId, RecheckImage);
	    		data.setCallbackQueue(jobQueuename);
	    		recheckImageQueue.sendMessage(RecheckImage, data);
	    		notSentJobs.remove(RecheckImage);
	    	}	    	
	    }
	    if((needUploadStatus & 4) > 0)
	    {
	    	if(MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, BankCardPhotoSubmitted) != null)
	    	{
	    		alreadyUploadedStatus |= 4;
	    		if(notSentJobs.contains(RecheckBankCard))
				{
	    			QueueMessager data = new QueueMessager(appId, RecheckBankCard);
	    			data.setCallbackQueue(jobQueuename);
		    		recheckBankCardQueue.sendMessage(RecheckBankCard, data);
		    		notSentJobs.remove(RecheckBankCard);
				}	    		
	    	}
	    }
	    
	    QueueMessager recheckImageMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, RecheckImage);
	    if(recheckImageMsg != null)
	    	checkedPhotoStatus |= CheckPhotoStatus.get(RecheckImage);
	    QueueMessager recheckBankCardMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, RecheckBankCard);
	    if(recheckBankCardMsg != null)
	    	checkedPhotoStatus |= CheckPhotoStatus.get(RecheckBankCard);
	    
	    if(checkedPhotoStatus == checkPhotoAllFinishedFlag)
	    {
	    	this.done = true;
	    	QueueMessager data = new QueueMessager(appId, job);
	    	data.setCallbackQueue(jobQueuename);
	    	recheckQueue.sendMessage(job, data);
	    }
	}

	@Override
	protected void onNeedReUploadPhotoResponse(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onHeadPhotoSubmitted(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onIdPhotoSubmitted(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onBankCardSubmitted(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onRecheckBankCardDone(Application application,
			IServiceProvider sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onAllReCheckPhotoJobsDone(Application application,
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

class RecheckActivityFactory extends ActivityFactoryBase<ReCheckActivity>{
	@Override
    protected void setProperties(ReCheckActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object ruleQueueNameObj = activityConfig.get("ruleQueueName");
        if(ruleQueueNameObj != null)
        {
        	activity.ruleQueueName = ruleQueueNameObj.toString();
        }
        
        Object reCheckImageQueueNameObj = activityConfig.get("reCheckImageQueueName");
        if(reCheckImageQueueNameObj != null)
        {
        	activity.reCheckImageQueueName = reCheckImageQueueNameObj.toString();
        }
        
        Object reCheckBankCardQueueNameObj = activityConfig.get("reCheckBankCardQueueName");
        if(reCheckBankCardQueueNameObj != null)
        {
        	activity.reCheckBankCardQueueName = reCheckBankCardQueueNameObj.toString();
        }
        
        Object jobObj = activityConfig.get("job");
        if(jobObj != null)
        {
        	activity.job = jobObj.toString();
        }
    }
}
