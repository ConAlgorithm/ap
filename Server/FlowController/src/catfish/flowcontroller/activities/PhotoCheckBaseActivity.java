package catfish.flowcontroller.activities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import catfish.base.StartupConfig;
import catfish.base.business.common.JobStatus;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DomainConsts;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class PhotoCheckBaseActivity extends Activity{

	public String ruleQueueName = "TopRulesDecisionJobRequestQueue";
	public String checkHeadPhotoQueueName = "HeadPhotoQueueV2";
	public String checkIDCardPhotoQueueName = "IDCardPhotoQueueV2";
	public String checkBankCardQueueName = "BankCardQueueV2";
	public String checkWorkPermitQueueName = "WorkPermitQueueV2";
	public String checkNoticeFormQueueName = "NoticeFormQueueV2";
	public String checkGroupPhotoQueueName = "GroupPhotoQueueV2";
	public String checkBuckleQueueName = "BuckleQueueV2";
	
	public String job;
		
	/*********************************************************************/
	public String CheckHeadPhoto = "CheckHeadPhoto";
	public String CheckIDCardPhoto = "CheckIDCardPhoto";
	public String CheckBankCard = "CheckBankCard";	
	//检查工作证
	public String CheckWorkPermit = "CheckWorkPermit";
	//检查电子告知书
	public String CheckNoticeForm = "CheckNoticeForm";
	//检查客户合影
	public String CheckGroupPhoto = "CheckGroupPhoto";
	//检查代扣协议
	public String CheckBuckle = "CheckBuckle";
	/*********************************************************************/
	
	public static final String HeadPhotoSubmitted = "HeadPhotoSubmitted";
	public static final String IdPhotoSubmitted = "IdPhotoSubmitted";
	public static final String BankCardPhotoSubmitted = "BankCardPhotoSubmitted";
	
	public static final String WorkPermitSubmitted = "WorkPermitSubmitted";
	public static final String NoticeFormSubmitted = "NoticeFormSubmitted";
	public static final String GroupPhotoSubmitted = "GroupPhotoSubmitted";
	public static final String BuckleSubmitted = "BuckleSubmitted";
	/********************************************************************/
		
	public static final String AlreadyUploadedStatus = "alreadyUploadedStatus";
	public static final String CheckedPhotoStatus = "checkedPhotoStatus";
	public static final String CheckPhotoLastSearchIndex = "checkPhotoLastSearchIndex";
	
	public static final String NeedUploadStatus = "needUploadStatus";
	protected static final String UploadLastSearchIndex = "uploadLastSearchIndex";
	
	public static final String CheckPhotoAllFinishedFlag = "checkPhotoAllFinishedFlag";
		
	public static final String PhotoCheckCount = "photoCheckCount";
	
	public static final String NotSentJobs = "notSentJobs";
	
	public static final String jobQueuename =  
			 StartupConfig.get("catfish.flowcontroller.jobqueue", "JobStatusQueue");
	protected int needUploadStatus = 0;
	protected int alreadyUploadedStatus = 0;
	private int checkedPhotoStatus = 0;
	private int checkPhotoAllFinishedFlag = 0;
	

	protected int photoCheckCount = 0;
	//尚未发送的job
	protected static Set<String> notSentJobs = new HashSet<>(); 
	//使用此结构是为了支持假设HeadPhotoSubmitted,IdPhotoSubmitted,BankCardPhotoSubmitted,SetImageUploadFirstStageReady四个消息的接收顺序是不定的情况，所以他们需要维护自身的最后查找位置
	protected Map<String, Integer> uploadLastSearchIndexMap = new HashMap<>();
	
	public static Map<String, Integer> PhotoSubmittedStatus = new HashMap<>();

	private Map<String, Integer> checkPhotoLastSearchIndexMap = new HashMap<>();
		
	static{
		PhotoSubmittedStatus.put(HeadPhotoSubmitted, 1);
		PhotoSubmittedStatus.put(IdPhotoSubmitted, 2);
		PhotoSubmittedStatus.put(BankCardPhotoSubmitted, 4);
		
		PhotoSubmittedStatus.put(BuckleSubmitted, 32);
		PhotoSubmittedStatus.put(WorkPermitSubmitted, 64);
		PhotoSubmittedStatus.put(GroupPhotoSubmitted, 128);
		PhotoSubmittedStatus.put(NoticeFormSubmitted, 256);
		
	}
	
	public PhotoCheckBaseActivity()
	{
		initialLastSearchIndex(lastSearchIndex);
	}
	
	@Override
	public void reset()
	{
		super.reset();
		needUploadStatus =0;
		alreadyUploadedStatus = 0;		
		checkedPhotoStatus = 0;
		checkPhotoAllFinishedFlag = 0;
		photoCheckCount = 0;
	    uploadLastSearchIndexMap = new HashMap<>();
		notSentJobs = new HashSet<>();
		checkPhotoLastSearchIndexMap = new HashMap<>();
	}
	
	protected void clearFlags()
	{
		needUploadStatus = 0;
		alreadyUploadedStatus = 0;		
		checkedPhotoStatus = 0;
		checkPhotoAllFinishedFlag = 0;
		notSentJobs = new HashSet<>();
	}
	
	protected void initialLastSearchIndex(int lastSearchIndex)
	{
		uploadLastSearchIndexMap.put(HeadPhotoSubmitted, lastSearchIndex);
		uploadLastSearchIndexMap.put(IdPhotoSubmitted, lastSearchIndex);
		uploadLastSearchIndexMap.put(BankCardPhotoSubmitted, lastSearchIndex);
		
		uploadLastSearchIndexMap.put(WorkPermitSubmitted, lastSearchIndex);
		uploadLastSearchIndexMap.put(GroupPhotoSubmitted, lastSearchIndex);
		uploadLastSearchIndexMap.put(NoticeFormSubmitted, lastSearchIndex);
		uploadLastSearchIndexMap.put(BuckleSubmitted, lastSearchIndex);
		
		for(String item : PhotoSubmittedStatus.keySet())
		{
			checkPhotoLastSearchIndexMap.put(item, lastSearchIndex);
		}
	}
	
	@Override
    public void saveState(Map<String, Object> dataMap){
		super.saveState(dataMap);
		StateHelper.save(dataMap, AlreadyUploadedStatus, alreadyUploadedStatus);
		StateHelper.save(dataMap, NeedUploadStatus, needUploadStatus);
		StateHelper.save(dataMap, UploadLastSearchIndex, uploadLastSearchIndexMap); 
		StateHelper.save(dataMap, NotSentJobs, notSentJobs);
		StateHelper.save(dataMap, CheckedPhotoStatus, checkedPhotoStatus);
		StateHelper.save(dataMap, CheckPhotoLastSearchIndex, checkPhotoLastSearchIndexMap);
		StateHelper.save(dataMap, CheckPhotoAllFinishedFlag, checkPhotoAllFinishedFlag);
		StateHelper.save(dataMap, PhotoCheckCount, photoCheckCount);
	}
	
	@Override
	public void loadState(Map<String, Object> dataMap){
		super.loadState(dataMap);
		needUploadStatus = StateHelper.loadInt(dataMap, NeedUploadStatus, 0);
		alreadyUploadedStatus = StateHelper.loadInt(dataMap, AlreadyUploadedStatus, 0);
		uploadLastSearchIndexMap = StateHelper.loadStringIntMap(dataMap, UploadLastSearchIndex);
		notSentJobs = StateHelper.loadSet(dataMap, NotSentJobs);
		checkedPhotoStatus = StateHelper.loadInt(dataMap, CheckedPhotoStatus, 0);
		checkPhotoLastSearchIndexMap = StateHelper.loadStringIntMap(dataMap, CheckPhotoLastSearchIndex);
		checkPhotoAllFinishedFlag = StateHelper.loadInt(dataMap, CheckPhotoAllFinishedFlag, 0);
		photoCheckCount = StateHelper.loadInt(dataMap, PhotoCheckCount, 0);
	}	
	
	private void initAllFinishedFlag(int needUploadStatus)
	{
		if((needUploadStatus & PhotoSubmittedStatus.get(HeadPhotoSubmitted)) > 0)
        {
        	checkPhotoAllFinishedFlag |= PhotoSubmittedStatus.get(HeadPhotoSubmitted);
        	notSentJobs.add(CheckHeadPhoto);
        }
        if((needUploadStatus & PhotoSubmittedStatus.get(IdPhotoSubmitted)) > 0)
        {
        	checkPhotoAllFinishedFlag |= PhotoSubmittedStatus.get(IdPhotoSubmitted);
        	notSentJobs.add(CheckIDCardPhoto);
        }
        if((needUploadStatus & PhotoSubmittedStatus.get(BankCardPhotoSubmitted)) > 0)
        {
        	checkPhotoAllFinishedFlag |= PhotoSubmittedStatus.get(BankCardPhotoSubmitted);
        	notSentJobs.add(CheckBankCard);
        }
        if((needUploadStatus & PhotoSubmittedStatus.get(WorkPermitSubmitted)) > 0)
        {
        	checkPhotoAllFinishedFlag |= PhotoSubmittedStatus.get(WorkPermitSubmitted);
        	notSentJobs.add(CheckWorkPermit);
        }
        if((needUploadStatus & PhotoSubmittedStatus.get(NoticeFormSubmitted)) > 0)
        {
        	checkPhotoAllFinishedFlag |= PhotoSubmittedStatus.get(NoticeFormSubmitted);
        	notSentJobs.add(CheckNoticeForm);
        }
        if((needUploadStatus & PhotoSubmittedStatus.get(GroupPhotoSubmitted)) > 0)
        {
        	checkPhotoAllFinishedFlag |= PhotoSubmittedStatus.get(GroupPhotoSubmitted);
        	notSentJobs.add(CheckGroupPhoto);
        }
        if((needUploadStatus & PhotoSubmittedStatus.get(BuckleSubmitted)) > 0)
        {
        	checkPhotoAllFinishedFlag |= PhotoSubmittedStatus.get(BuckleSubmitted);
        	notSentJobs.add(CheckBuckle);
        }
	}
	private boolean initUploadStatus(Application application, IServiceProvider sp)
	{
		QueueMessager expected = MessagerHelper.findQueueMessager(this, application, job);
	    if(expected != null)
	    {	   
			String status = expected.getJobStatus();
			switch(status)
			{
			case JobStatus.Approved:
				this.done = true;
				return true;
			case JobStatus.Rejected:
				this.done = true;
				return true;
			case JobStatus.RecheckingRequired:					
				needUploadStatus = expected.getJobDataInt();
				initAllFinishedFlag(needUploadStatus);
				onNeedReUploadPhotoResponse(application, sp);
				return false;
			default:
				this.terminate();
				return true;
			}	        	
	    }
	    return false;
	}
	
	@Override
    protected void init(Application application, IServiceProvider sp)
    {
		
    }
    
	@Override
    protected void process(Application application, IServiceProvider sp)
    {	
		if(initUploadStatus(application, sp))
			return;
		
    	if((needUploadStatus & PhotoSubmittedStatus.get(HeadPhotoSubmitted)) > 0 &&  MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, HeadPhotoSubmitted) != null)
    	{
    		alreadyUploadedStatus |= PhotoSubmittedStatus.get(HeadPhotoSubmitted);
    		onHeadPhotoSubmitted(application, sp);
    	}
    		
    	if((needUploadStatus & PhotoSubmittedStatus.get(IdPhotoSubmitted)) > 0 && MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, IdPhotoSubmitted) != null)
    	{
    		alreadyUploadedStatus |= PhotoSubmittedStatus.get(IdPhotoSubmitted);
    		onIdPhotoSubmitted(application, sp);
    	}  		    	    	  	
	    if((needUploadStatus & PhotoSubmittedStatus.get(BankCardPhotoSubmitted)) > 0 && MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, BankCardPhotoSubmitted) != null)
	    {   	
    		alreadyUploadedStatus |= PhotoSubmittedStatus.get(BankCardPhotoSubmitted);
    		onBankCardSubmitted(application, sp);	    	   		
	    }
	    if((needUploadStatus & PhotoSubmittedStatus.get(WorkPermitSubmitted)) > 0 && MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, WorkPermitSubmitted) != null)
	    {   	
    		alreadyUploadedStatus |= PhotoSubmittedStatus.get(WorkPermitSubmitted);
    		onWorkPermitSubmitted(application, sp);	    	   		
	    }
	    if((needUploadStatus & PhotoSubmittedStatus.get(NoticeFormSubmitted)) > 0 && MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, NoticeFormSubmitted) != null)
	    {   	
    		alreadyUploadedStatus |= PhotoSubmittedStatus.get(NoticeFormSubmitted);
    		onNoticeFormSubmitted(application, sp);	    	   		
	    }
	    if((needUploadStatus & PhotoSubmittedStatus.get(GroupPhotoSubmitted)) > 0 && MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, GroupPhotoSubmitted) != null)
	    {   	
    		alreadyUploadedStatus |= PhotoSubmittedStatus.get(GroupPhotoSubmitted);
    		onGroupPhotoSubmitted(application, sp);	    	   		
	    }
	    if((needUploadStatus & PhotoSubmittedStatus.get(BuckleSubmitted)) > 0 && MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, BuckleSubmitted) != null)
	    {   	
    		alreadyUploadedStatus |= PhotoSubmittedStatus.get(BuckleSubmitted);
    		onBuckleSubmitted(application, sp);	    	   		
	    }
	    
	    QueueMessager recheckHeadPhotoMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, CheckHeadPhoto);
	    if(recheckHeadPhotoMsg != null && (needUploadStatus & PhotoSubmittedStatus.get(HeadPhotoSubmitted)) > 0)
	    {
	    	checkedPhotoStatus |= PhotoSubmittedStatus.get(HeadPhotoSubmitted);
	    	onRecheckHeadPhotoDone(application, sp);
	    }
	    
	    QueueMessager recheckIDCardPhotoMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, CheckIDCardPhoto);
	    if(recheckIDCardPhotoMsg != null && (needUploadStatus & PhotoSubmittedStatus.get(IdPhotoSubmitted)) > 0)
	    {
	    	checkedPhotoStatus |= PhotoSubmittedStatus.get(IdPhotoSubmitted);
	    	onRecheckIDCardPhotoDone(application, sp);
	    }
	    
	    QueueMessager recheckBankCardMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, CheckBankCard);
	    if(recheckBankCardMsg != null && (needUploadStatus &  PhotoSubmittedStatus.get(BankCardPhotoSubmitted)) > 0)
	    {
	    	checkedPhotoStatus |= PhotoSubmittedStatus.get(BankCardPhotoSubmitted);
	    	onRecheckBankCardDone(application, sp);
	    }
	    
	    QueueMessager recheckWorkPermitMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, CheckWorkPermit);
	    if(recheckWorkPermitMsg != null && (needUploadStatus &  PhotoSubmittedStatus.get(WorkPermitSubmitted)) > 0)
	    {
	    	checkedPhotoStatus |= PhotoSubmittedStatus.get(WorkPermitSubmitted);
	    	onRecheckWorkPermitDone(application, sp);
	    }
	    
	    QueueMessager recheckNoticeFormMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, CheckNoticeForm);
	    if(recheckNoticeFormMsg != null && (needUploadStatus &  PhotoSubmittedStatus.get(NoticeFormSubmitted)) > 0)
	    {
	    	checkedPhotoStatus |= PhotoSubmittedStatus.get(NoticeFormSubmitted);
	    	onRecheckNoticeFormDone(application, sp);
	    }
	    
	    QueueMessager recheckGroupPhotoMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, CheckGroupPhoto);
	    if(recheckGroupPhotoMsg != null && (needUploadStatus &  PhotoSubmittedStatus.get(GroupPhotoSubmitted)) > 0)
	    {
	    	checkedPhotoStatus |= PhotoSubmittedStatus.get(GroupPhotoSubmitted);
	    	onRecheckGroupPhotoDone(application, sp);
	    }
	    
	    QueueMessager recheckBuckleMsg = MessagerHelper.findQueueMessager(checkPhotoLastSearchIndexMap, application, CheckBuckle);
	    if(recheckBuckleMsg != null && (needUploadStatus &  PhotoSubmittedStatus.get(BuckleSubmitted)) > 0)
	    {
	    	checkedPhotoStatus |= PhotoSubmittedStatus.get(BuckleSubmitted);
	    	onRecheckBuckleDone(application, sp);
	    }
	    
	    
	    if(checkedPhotoStatus == checkPhotoAllFinishedFlag)
	    {
	    	onAllReCheckPhotoJobsDone(application, sp);
	    }
    }
	
	protected void onNeedReUploadPhotoResponse(Application application, IServiceProvider sp){}
	protected void onHeadPhotoSubmitted(Application application, IServiceProvider sp){}
	protected void onIdPhotoSubmitted(Application application, IServiceProvider sp){}
	protected void onBankCardSubmitted(Application application, IServiceProvider sp){}
	protected void onWorkPermitSubmitted(Application application, IServiceProvider sp){}
	protected void onNoticeFormSubmitted(Application application, IServiceProvider sp){}
	protected void onGroupPhotoSubmitted(Application application, IServiceProvider sp){}
	protected void onBuckleSubmitted(Application application, IServiceProvider sp){}
	protected void onRecheckHeadPhotoDone(Application application, IServiceProvider sp){}
	protected void onRecheckIDCardPhotoDone(Application application, IServiceProvider sp){}
	protected void onRecheckBankCardDone(Application application, IServiceProvider sp){}
	protected void onRecheckWorkPermitDone(Application application, IServiceProvider sp){}
	protected void onRecheckNoticeFormDone(Application application, IServiceProvider sp){}
	protected void onRecheckGroupPhotoDone(Application application, IServiceProvider sp){}
	protected void onRecheckBuckleDone(Application application, IServiceProvider sp){}
	protected void onAllReCheckPhotoJobsDone(Application application, IServiceProvider sp){}
	
	protected void sendJob(Application application, IServiceProvider sp, String queueName, String jobName)
	{
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue recheckBankCardQueue = queueService.getQueue(queueName, "FlowController");
		
		if(notSentJobs.contains(jobName))
		{
			QueueMessager data = new QueueMessager(application.appId, jobName);
			data.setCallbackQueue(jobQueuename);
    		recheckBankCardQueue.sendMessage(jobName, data);
    		notSentJobs.remove(jobName);
		}	 
	}
}
