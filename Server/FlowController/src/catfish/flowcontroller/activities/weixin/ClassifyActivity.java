package catfish.flowcontroller.activities.weixin;

import java.util.HashMap;
import java.util.Map;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DomainConsts;
import catfish.flowcontroller.activities.MessagerHelper;
import catfish.flowcontroller.activities.PhotoCheckBaseActivity;
import catfish.flowcontroller.activities.StateHelper;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class ClassifyActivity extends PhotoCheckBaseActivity{

	//for Classify phto
    public static final String ClassifyHeadPhoto = "ClassifyPhoto1";
	public static final String ClassifyIdPhoto = "ClassifyPhoto2";
	public static final String ClassifyBankCardPhoto = "ClassifyPhoto4";
	public static final String SetImageUploadFirstStageReady = "SetImageUploadFirstStageReady";
	
	public static final String ClassifiedJobStatus = "classifiedJobStatus";
	public static final String PhotoCheckCount = "photoCheckCount";
	
	public static final String ClassifyLastSearchIndex = "classifyLastSearchIndex";
	public static final String ClassifyJobAllReadyFlag = "classifyJobAllReadyFlag"; 
		
	//已经分拣完成的job标志位集合
	public int classifiedJobStatus = 0;
	private int photoCheckCount = 0;
	//记录所有job做完的标志
	private int classifyJobAllReadyFlag = 0;
		
	private Map<String, Integer> classifyLastSearchIndexMap = new HashMap<>();
	
	public String job;
	
	public String ruleQueueName = "TopRulesDecisionJobRequestQueue";
	public String classifyQueueName = "PhotoClassifyQueueV2";
	public String setImageUploadFirstStageReadyQueueName = "JobRequestQueue";
			
	private static Map<String, Integer> ClassifyJobStatusMap = new HashMap<>();

	@Override
    public void reset(){
        super.reset();
        
        
        classifiedJobStatus = 0;
        photoCheckCount =0;
        classifyJobAllReadyFlag = 0;
        
        classifyLastSearchIndexMap = new HashMap<>();
        
        initialLastSearchIndex();
        
    }
	
	//初始化需要分拣的job
	static{
		ClassifyJobStatusMap.put(ClassifyHeadPhoto, 1);
		ClassifyJobStatusMap.put(ClassifyIdPhoto, 2);
		ClassifyJobStatusMap.put(ClassifyBankCardPhoto, 4);
		ClassifyJobStatusMap.put(SetImageUploadFirstStageReady, 8);	
	}
	
	public ClassifyActivity()
	{
		initialLastSearchIndex();
	}
	
	private void initialLastSearchIndex()
	{		
		for(String key : ClassifyJobStatusMap.keySet())
		{
			classifyLastSearchIndexMap.put(key, lastSearchIndex);
		}				
	}

	@Override
	protected void init(Application application, IServiceProvider sp) {
		super.init(application, sp);
		IQueueService queueService = sp.getService(IQueueService.class);
        IQueue queue = queueService.getQueue(ruleQueueName,"FlowController");
        
        if(job != null)
        {
        	String appId = application.appId;
            QueueMessager data = new QueueMessager(appId, job, photoCheckCount);
            queue.sendMessage(job, data);
        }
	}
	
	//以位与方式记录当前还有哪些照片需要上传，哪些照片需要分拣，以及照片检查次数，用于统计和展示等
    public void saveState(Map<String, Object> dataMap){
    	super.saveState(dataMap);
    	   	
    	StateHelper.save(dataMap, ClassifiedJobStatus, classifiedJobStatus);
    	StateHelper.save(dataMap, PhotoCheckCount, photoCheckCount);   	
    	StateHelper.save(dataMap, ClassifyLastSearchIndex, classifyLastSearchIndexMap); 
    	StateHelper.save(dataMap, ClassifyJobAllReadyFlag, classifyJobAllReadyFlag);
    }
    
    public void loadState(Map<String, Object> dataMap){
        super.loadState(dataMap);
        
        classifiedJobStatus = StateHelper.loadInt(dataMap, ClassifiedJobStatus, 0);
        photoCheckCount = StateHelper.loadInt(dataMap, PhotoCheckCount, 0);
        classifyJobAllReadyFlag = StateHelper.loadInt(dataMap, ClassifyJobAllReadyFlag, 0);
        
        classifyLastSearchIndexMap = StateHelper.loadStringIntMap(dataMap, ClassifyLastSearchIndex);        
    }
    
    @Override
    protected void process(Application application, IServiceProvider sp){
    	super.process(application, sp);
    	IQueueService queueService = sp.getService(IQueueService.class);
        IQueue setImageUploadFirstStageReadyQueue = queueService.getQueue(setImageUploadFirstStageReadyQueueName, "FlowController");
        
    	QueueMessager expected = MessagerHelper.findQueueMessager(this, application, job);
    	if(expected != null)
    	{
    		photoCheckCount ++;
    		needUploadStatus = Integer.parseInt(expected.getJobStatus());
    		
    		if(needUploadStatus != 0)
    		{
    			classifyJobAllReadyFlag = needUploadStatus;   		
    			if(photoCheckCount != 1)
    			{    				
    			    IMessageService messageService = sp.getService(IMessageService.class);
    			    messageService.sendMessage(DomainConsts.ReuploadFiles, application.appId, String.valueOf(needUploadStatus));
    			}		
    			else{
    				//如果是第一次photoCheck,则需要分拣的图片就是需要上传的图片+SetImageUploadFirstStageReady
    				classifyJobAllReadyFlag += ClassifyJobStatusMap.get(SetImageUploadFirstStageReady);
		        	QueueMessager data = new QueueMessager(application.appId, SetImageUploadFirstStageReady);
		        	setImageUploadFirstStageReadyQueue.sendMessage(SetImageUploadFirstStageReady, data);
    			}
    		}
    		else
        	{
        		this.done = true;
        		return;
        	}
    	}
    	//如果没有查找到PhotoCheck的反馈，则检查当前图片上传状态
    	else if(needUploadStatus != 0){
    		waitAndSendJobs(application, sp, photoCheckCount);
    		if(checkClassifyJobsAllDone(application, sp))
    		{
    			init(application, sp);
    		}
    	}
    }

    private void resetClassifiedJobStatus()
    {
    	this.classifiedJobStatus = 0;
    }
    
    private QueueMessager updateClassifiedJobStatus(Application application, String classifyJobName)
    {
    	QueueMessager classifyMsg = MessagerHelper.findQueueMessager(classifyLastSearchIndexMap, application, classifyJobName);
    	//只有当查找到相关job，并且是需要完成的job时才会更新状态
    	if(classifyMsg != null && (ClassifyJobStatusMap.get(classifyJobName) & classifyJobAllReadyFlag) != 0)
    		this.classifiedJobStatus |= ClassifyJobStatusMap.get(classifyJobName);
    	return classifyMsg;
    }
    
    private boolean checkClassifyJobsAllDone(Application application, IServiceProvider sp)
    {
    	updateClassifiedJobStatus(application, ClassifyHeadPhoto);
    	updateClassifiedJobStatus(application, ClassifyIdPhoto);
    	updateClassifiedJobStatus(application, ClassifyBankCardPhoto);   	
    	QueueMessager setImageUploadFirstStageReady = updateClassifiedJobStatus(application, SetImageUploadFirstStageReady);   	
    	if(setImageUploadFirstStageReady != null && photoCheckCount == 1)
    	{
    	    IMessageService messageService = sp.getService(IMessageService.class);
    	    messageService.sendMessage(DomainConsts.PreApproveAndUploadFiles, application.appId, String.valueOf(needUploadStatus));
    	}
    	if(classifiedJobStatus == classifyJobAllReadyFlag)
    	{
    		resetClassifiedJobStatus();
    		return true;
    	}  	
    	return false;
    }
    
	private void waitAndSendJobs(Application application, IServiceProvider sp, int count)
	{
		
		String appId = application.appId;
		
		IQueueService queueService = sp.getService(IQueueService.class);
        IQueue classifyQueue = queueService.getQueue(classifyQueueName,"FlowController");
        QueueMessager expected = null;
               
        if((needUploadStatus & 1) > 0)
        {
        	expected = MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, HeadPhotoSubmitted);
        	if(expected != null)
        	{    		
                QueueMessager data = new QueueMessager(appId, ClassifyHeadPhoto, expected.getJobStatus());
                classifyQueue.sendMessage(ClassifyHeadPhoto, data);
        	}
        }
        if((needUploadStatus & 2) > 0)
        {
        	expected = MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, IdPhotoSubmitted);
        	if(expected != null)
        	{    		
                QueueMessager data = new QueueMessager(appId, ClassifyIdPhoto, expected.getJobStatus());
                classifyQueue.sendMessage(ClassifyIdPhoto, data);
        	}
        }
        if((needUploadStatus & 4) > 0)
        {
        	expected = MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, BankCardPhotoSubmitted);
        	if(expected != null)
        	{    		
                QueueMessager data = new QueueMessager(appId, ClassifyBankCardPhoto, expected.getJobStatus());
                classifyQueue.sendMessage(ClassifyBankCardPhoto, data);
        	}
        }   
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
	protected void onNeedReUploadPhotoResponse(Application application,
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

