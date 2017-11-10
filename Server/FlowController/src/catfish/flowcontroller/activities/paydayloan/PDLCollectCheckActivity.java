package catfish.flowcontroller.activities.paydayloan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.util.EnumUtils;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.MessagerHelper;
import catfish.flowcontroller.activities.StateHelper;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public abstract class PDLCollectCheckActivity extends Activity{
    
    protected Map<String, String> jobQueueMap = new HashMap<String, String>();
    
    protected Map<String, String> eventJobMap = new HashMap<String, String>();
    
    protected String job ;
    
    protected static Map<String, Integer> eventWorkEvidenceMap = new HashMap<String, Integer>();

    static {
        
        eventWorkEvidenceMap.put("HeadPhotoSubmitted", UploadFileStatus.HeadPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("IdPhotoSubmitted", UploadFileStatus.IdPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("BankCardPhotoSubmitted", UploadFileStatus.BankPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("NoticeFormSubmitted", UploadFileStatus.ENoticeFormPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("GroupPhotoSubmitted", UploadFileStatus.GroupPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("BuckleSubmitted", UploadFileStatus.BuckleUploaded.getValue());
        
        eventWorkEvidenceMap.put("WorkPermitSubmitted", UploadFileStatus.WorkPermitUploaded.getValue());
        eventWorkEvidenceMap.put("ChestCardPhotoSubmitted", UploadFileStatus.ChestCardPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("UniformPhotoSubmitted", UploadFileStatus.UniformPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("HealthCertificatePhotoSubmitted", UploadFileStatus.HealthCertificatePhotoUploaded.getValue());
        eventWorkEvidenceMap.put("TimeCardPhotoSubmitted", UploadFileStatus.TimeCardPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("SocialSecurityCardPhotoSubmitted", UploadFileStatus.SocialSecurityCardPhotoUploaded.getValue());
        eventWorkEvidenceMap.put("WageCeritificatePhotoSubmitted", UploadFileStatus.WageCeritificatePhotoUploaded.getValue());
    }
    
    protected int requiredStatus = 0;
    protected int uploadedStatus = 0;
    protected int checkedStatus = 0;
    protected int targetStatus = 0;
    protected Map<String, Integer> uploadLastSearchIndexMap = new HashMap<>();
    protected Map<String, Integer> checkPhotoLastSearchIndexMap = new HashMap<>();
    protected Set<String> notSentJobs = new HashSet<>(); 
    
    public static final String RequiredStatus = "requiredStatus";
    public static final String UploadedStatus = "uploadedStatus";
    
    private static final String CheckedStatus = "checkedStatus";
    private static final String TargetStatus = "targetStatus";
    private static final String UploadLastSearchIndexMap = "uploadLastSearchIndexMap";
    private static final String CheckPhotoLastSearchIndexMap = "checkPhotoLastSearchIndexMap";
    private static final String NotSentJobs = "notSentJobs";
    
    
    protected abstract boolean isDummyProcess(Application application, IServiceProvider sp);
    
    protected abstract void onAllChecked(Application application, IServiceProvider sp);
    
    protected void initLastSearchIndex(int lastSearchIndex) {
        for(String event : eventWorkEvidenceMap.keySet())
        {
            uploadLastSearchIndexMap.put(event, lastSearchIndex);
            String job = eventJobMap.get(event);
            if(job != null) {
                checkPhotoLastSearchIndexMap.put(job, lastSearchIndex);
            }
        }
    }
    
    protected boolean isRequired(String event) {
        return (requiredStatus & eventWorkEvidenceMap.get(event)) > 0;
    }
    
    protected boolean hasHappened(Application application, Map<String, Integer> searchIndexMap, String event) {
        return MessagerHelper.findQueueMessager(searchIndexMap, application, event) != null;
    }
    
    protected void onCollected(Application application, IServiceProvider sp, String event, String job) {
        uploadedStatus |= eventWorkEvidenceMap.get(event);
        String jobToSend = eventJobMap.get(event);
        sendJob(application, sp, jobQueueMap.get(jobToSend), jobToSend);
    }
    
    protected void onChecked(Application application, IServiceProvider sp, String event, String job) {
        checkedStatus |= eventWorkEvidenceMap.get(event);
    }
    
    protected void sendJob(Application application, IServiceProvider sp, String queueName, String jobName)
    {
        if(notSentJobs.contains(jobName)) {
            IQueueService queueService = sp.getService(IQueueService.class);
            IQueue queue = queueService.getQueue(queueName, "FlowController");
            QueueMessager data = new QueueMessager(application.appId, jobName);
            queue.sendMessage(jobName, data);
            notSentJobs.remove(jobName);
        }
    }

    @Override
    protected void process(Application application, IServiceProvider sp) {
        
        if(isDummyProcess(application, sp)) {
            return ;
        }
        
        for(Entry<String, String> entry: eventJobMap.entrySet()) {
            String event = entry.getKey();
            String job = entry.getValue();
            
            if(isRequired(event) && hasHappened(application, uploadLastSearchIndexMap, event)) {
                onCollected(application, sp, event, job);
            }
            
            if(isRequired(event) && hasHappened(application, checkPhotoLastSearchIndexMap, job)) {
                onChecked(application, sp, event, job);
            }
            
        }
        
        if(targetStatus == checkedStatus) {
            onAllChecked(application, sp);
        }
    }
    
    @Override
    public void saveState(java.util.Map<String,Object> dataMap) {
        super.saveState(dataMap);
        StateHelper.save(dataMap, RequiredStatus, requiredStatus);
        StateHelper.save(dataMap, UploadedStatus, uploadedStatus);
        StateHelper.save(dataMap, CheckedStatus, checkedStatus);
        StateHelper.save(dataMap, TargetStatus, targetStatus);
        StateHelper.save(dataMap, UploadLastSearchIndexMap, uploadLastSearchIndexMap);
        StateHelper.save(dataMap, CheckPhotoLastSearchIndexMap, checkPhotoLastSearchIndexMap);
        StateHelper.save(dataMap, NotSentJobs, notSentJobs);
    };
    
    @Override
    public void loadState(Map<String, Object> dataMap){
        super.loadState(dataMap);
        requiredStatus = StateHelper.loadInt(dataMap, RequiredStatus, 0);
        uploadedStatus = StateHelper.loadInt(dataMap, UploadedStatus, 0);
        checkedStatus = StateHelper.loadInt(dataMap, CheckedStatus, 0);
        targetStatus = StateHelper.loadInt(dataMap, TargetStatus, 0);
        uploadLastSearchIndexMap = StateHelper.loadStringIntMap(dataMap, UploadLastSearchIndexMap);
        checkPhotoLastSearchIndexMap = StateHelper.loadStringIntMap(dataMap, CheckPhotoLastSearchIndexMap);
        notSentJobs = StateHelper.loadSet(dataMap, NotSentJobs);
    }
    
    @Override
    public void reset() {
        super.reset();
        requiredStatus = 0;
        uploadedStatus = 0;
        checkedStatus = 0;
        targetStatus = 0;
        uploadLastSearchIndexMap = new HashMap<>();
        checkPhotoLastSearchIndexMap = new HashMap<>();
        notSentJobs = new HashSet<>();
    };
    
    protected void applyRequiredStatus(Integer requiredStatus) {
        this.requiredStatus = requiredStatus;
        this.targetStatus = requiredStatus & (~UploadFileStatus.ReUploaded.getValue());
        for(Entry<String, String> entry : eventJobMap.entrySet()) {
            if(isRequired(entry.getKey())) {
                notSentJobs.add(entry.getValue());
            }
        }
    }
    
    public void setJobQueueMap(Map<String, String> jobQueueMap) {
        this.jobQueueMap = jobQueueMap;
    }

    public void setEventJobMap(Map<String, String> eventJobMap) {
        this.eventJobMap = eventJobMap;
    }

    public void setJob(String job) {
        this.job = job;
    }
    
}
