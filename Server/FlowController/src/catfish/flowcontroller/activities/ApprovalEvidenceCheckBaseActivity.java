package catfish.flowcontroller.activities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import catfish.base.business.common.JobStatus;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public abstract class ApprovalEvidenceCheckBaseActivity extends Activity{

	public static final int BIT_IOU = 1 << 4;
	public static final int BIT_BUCKLE = 1 << 5;
	  
	public String ruleQueueName = "TopRulesDecisionJobRequestQueue";
    public String checkIOUQueueName = "IOUQueueV2";
    public String checkBuckleQueueName = "BuckleQueueV2";
    public String job = "PostApprovalEvidenceCheck";
    
    public static final String AlreadyUploadedStatus = "alreadyUploadedStatus";
	public static final String CheckedEvidenceStatus = "checkedEvidenceStatus";
	public static final String CheckEvidenceLastSearchIndex = "checkEvidenceLastSearchIndexMap";
	
	public static final String NeedUploadStatus = "needUploadStatus";
	protected static final String UploadLastSearchIndex = "uploadLastSearchIndex";
	
	public static final String CheckEvidenceAllFinishedFlag = "checkEvidenceAllFinishedFlag";
		
	public static final String EvidenceCheckCount = "evidenceCheckCount";
	
	public static final String NotSentJobs = "notSentJobs";
	
    public static String IOUSubmitted = "IOUSubmitted";
    public static String BuckleSubmitted = "BuckleSubmitted";
    
    public static String CheckIOU = "CheckIOUV3";
    public static String CheckBuckle = "CheckBuckle";
    public static String ApprovalEvidenceRequirementCheck = "ApprovalEvidenceRequirementCheck";
    
    protected int evidenceCheckCount = 0;
    
    protected int needUploadStatus = 0;
	protected int alreadyUploadedStatus = 0;
	private int checkedEvidenceStatus = 0;
	private int checkEvidenceAllFinishedFlag = 0;
	
	protected static Set<String> notSentJobs = new HashSet<>(); 
	
    protected Map<String, Integer> uploadLastSearchIndexMap = new HashMap<>();
	
	private static Map<String, Integer> CheckEvidenceStatus = new HashMap<>();

	private Map<String, Integer> checkEvidenceLastSearchIndexMap = new HashMap<>();
	
	static{
		CheckEvidenceStatus.put(CheckIOU, 1);
		CheckEvidenceStatus.put(CheckBuckle, 2);
	}
	
	public ApprovalEvidenceCheckBaseActivity()
	{
		initialLastSearchIndex(lastSearchIndex);
	}
	
	@Override
	public void reset()
	{
		super.reset();
		needUploadStatus =0;
		alreadyUploadedStatus = 0;		
		checkedEvidenceStatus = 0;
		checkEvidenceAllFinishedFlag = 0;
		evidenceCheckCount = 0;
	    uploadLastSearchIndexMap = new HashMap<>();
		notSentJobs = new HashSet<>();
		checkEvidenceLastSearchIndexMap = new HashMap<>();
	}
	
	protected void clearFlags()
	{
		needUploadStatus = 0;
		alreadyUploadedStatus = 0;		
		checkedEvidenceStatus = 0;
		checkEvidenceAllFinishedFlag = 0;
		notSentJobs = new HashSet<>();
	}
	
	protected void initialLastSearchIndex(int lastSearchIndex)
	{
		uploadLastSearchIndexMap.put(IOUSubmitted, lastSearchIndex);
		uploadLastSearchIndexMap.put(BuckleSubmitted, lastSearchIndex);
		for(String item : CheckEvidenceStatus.keySet())
		{
			checkEvidenceLastSearchIndexMap.put(item, lastSearchIndex);
		}
	}
	
	@Override
    public void saveState(Map<String, Object> dataMap){
		super.saveState(dataMap);
		StateHelper.save(dataMap, AlreadyUploadedStatus, alreadyUploadedStatus);
		StateHelper.save(dataMap, NeedUploadStatus, needUploadStatus);
		StateHelper.save(dataMap, UploadLastSearchIndex, uploadLastSearchIndexMap); 
		StateHelper.save(dataMap, NotSentJobs, notSentJobs);
		StateHelper.save(dataMap, CheckedEvidenceStatus, checkedEvidenceStatus);
		StateHelper.save(dataMap, CheckEvidenceLastSearchIndex, checkEvidenceLastSearchIndexMap);
		StateHelper.save(dataMap, CheckEvidenceAllFinishedFlag, checkEvidenceAllFinishedFlag);
		StateHelper.save(dataMap, EvidenceCheckCount, evidenceCheckCount);
	}
	
	@Override
	public void loadState(Map<String, Object> dataMap){
		super.loadState(dataMap);
		needUploadStatus = StateHelper.loadInt(dataMap, NeedUploadStatus, 0);
		alreadyUploadedStatus = StateHelper.loadInt(dataMap, AlreadyUploadedStatus, 0);
		uploadLastSearchIndexMap = StateHelper.loadStringIntMap(dataMap, UploadLastSearchIndex);
		notSentJobs = StateHelper.loadSet(dataMap, NotSentJobs);
		checkedEvidenceStatus = StateHelper.loadInt(dataMap, CheckedEvidenceStatus, 0);
		checkEvidenceLastSearchIndexMap = StateHelper.loadStringIntMap(dataMap, CheckEvidenceLastSearchIndex);
		checkEvidenceAllFinishedFlag = StateHelper.loadInt(dataMap, CheckEvidenceAllFinishedFlag, 0);
		evidenceCheckCount = StateHelper.loadInt(dataMap, EvidenceCheckCount, 0);
	}
	
	@Override
	protected void init(Application application, IServiceProvider sp) {
	}

	private void initAllFinishedFlag(int needUploadStatus)
	{
		if((needUploadStatus & BIT_IOU) > 0)
		{
			checkEvidenceAllFinishedFlag |= CheckEvidenceStatus.get(CheckIOU);
			notSentJobs.add(CheckIOU);
		}
		if((needUploadStatus & BIT_BUCKLE) > 0)
		{
			checkEvidenceAllFinishedFlag |= CheckEvidenceStatus.get(CheckBuckle);
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
				onNeedReUploadEvidenceResponse(application, sp);
				return false;
			default:
				this.terminate();
				return true;
			}		
		}
		return false;
	}
	@Override
	protected void process(Application application, IServiceProvider sp) {
		if(initUploadStatus(application, sp))
			return;
		
		if((needUploadStatus & BIT_IOU) > 0)
		{
			QueueMessager expected = MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, IOUSubmitted);
			if(expected != null)
			{
				alreadyUploadedStatus |= BIT_IOU;
				onIOUSubmitted(application, sp);
			}
		}
		if((needUploadStatus & BIT_BUCKLE) > 0)
		{
			QueueMessager expected = MessagerHelper.findQueueMessager(uploadLastSearchIndexMap, application, BuckleSubmitted);
			if(expected != null)
			{
				alreadyUploadedStatus |= BIT_BUCKLE;
				onBuckleSubmitted(application, sp);
			}
		}
		
		QueueMessager checkIOUMsg = MessagerHelper.findQueueMessager(checkEvidenceLastSearchIndexMap, application, CheckIOU);
		if(checkIOUMsg != null && (needUploadStatus & BIT_IOU) > 0)
		{
			checkedEvidenceStatus |= CheckEvidenceStatus.get(CheckIOU);
			onCheckIOUDone(application, sp);
		}
		
		QueueMessager checkBuckleMsg = MessagerHelper.findQueueMessager(checkEvidenceLastSearchIndexMap, application, CheckBuckle);
		if(checkBuckleMsg != null && (needUploadStatus & BIT_BUCKLE) > 0)
		{
			checkedEvidenceStatus |= CheckEvidenceStatus.get(CheckBuckle);
			onCheckBuckleDone(application, sp);
		}
		
		if(checkedEvidenceStatus == checkEvidenceAllFinishedFlag)
		{
			onAllCheckEvidenceJobsDone(application, sp);
		}
	}

	protected abstract void onNeedReUploadEvidenceResponse(Application application, IServiceProvider sp);
	protected abstract void onIOUSubmitted(Application application, IServiceProvider sp);
	protected abstract void onBuckleSubmitted(Application application, IServiceProvider sp);
	protected abstract void onCheckIOUDone(Application application, IServiceProvider sp);
	protected abstract void onCheckBuckleDone(Application application, IServiceProvider sp);
	protected abstract void onAllCheckEvidenceJobsDone(Application application, IServiceProvider sp);
}
