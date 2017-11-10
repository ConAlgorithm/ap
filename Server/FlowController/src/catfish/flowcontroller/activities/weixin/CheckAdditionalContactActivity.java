package catfish.flowcontroller.activities.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import catfish.base.business.common.JobStatus;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.MessagerHelper;
import catfish.flowcontroller.activities.StateHelper;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.util.JobUtils;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class CheckAdditionalContactActivity extends Activity{

	private static final String NeedCheck = "needCheck";

	
	public int retry = 0;
	public String queueName = "ContactPhoneQueueV2";
	private int additionalContactCount = 0;
	
    private static final String CHECK_THIRD_CONTACT = "CheckThirdContact";
    private static final String CHECK_ADDITIONAL_CONTACT = "CheckAdditionalContact";  
	
    //用于存储拨打次数
    private Map<String, Integer> checkContactMap = new HashMap<>();
       
    public void reset(){
    	super.reset();
    	checkContactMap = new HashMap<>();
    }
    //以位与方式记录当前还有哪些联系人没有检查完毕，用于统计和展示等
    public void saveState(Map<String, Object> dataMap){
    	super.saveState(dataMap);
    	
    	dataMap.put(NeedCheck, checkContactMap);
    }
    
    public void loadState(Map<String, Object> dataMap){
        super.loadState(dataMap);
        checkContactMap = StateHelper.loadStringIntMap(dataMap, NeedCheck);
    }
    
    private void sendJob(Application application, IServiceProvider sp, String[] jobs, int delaySeconds){
        IQueueService queueService = sp.getService(IQueueService.class);
        IQueue queue = queueService.getQueue(queueName, "FlowController");
        if(delaySeconds != 0)        
             MessagerHelper.sendMessages(queue, application.appId, jobs, QueueConfig.QUEUE_PRIORITY_NORMAL, delaySeconds);
        else
        	 MessagerHelper.sendMessages(queue, application.appId, jobs);
    }
    
	@Override
	protected void init(Application application, IServiceProvider sp) {
        
        QueueMessager messager = MessagerHelper.findQueueMessager(this, application, this.prerequisiteJobs[0]);
        
		//获取额外联系人数量
		additionalContactCount = messager == null ? 0 : messager.getJobDataInt();
		
		if(additionalContactCount == 0)
		{
			this.done = true;			
		}
		else {
			checkContactMap.put(CHECK_THIRD_CONTACT, 0);
			sendJob(application, sp, new String[]{CHECK_THIRD_CONTACT}, 0);	
			
			if(additionalContactCount == 1) return;
			
			List<String> additionalList = new ArrayList<>();
			for(int i = 0; i < additionalContactCount -1; i ++)
			{
				String checkAdditional = CHECK_ADDITIONAL_CONTACT+i;
				additionalList.add(checkAdditional);
				checkContactMap.put(checkAdditional, 0);
			}
			sendJob(application, sp, additionalList.toArray(new String[]{}), 0);
		}
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		this.done = false;
			      
        boolean lastSearchIndexSettedFlag = false;
        
        Set<String> needRecheckJobs = new HashSet<>();
        
        for(int i=application.messages.size()-1; i>lastSearchIndex; i--){
            QueueMessager messager = application.messages.get(i);
            if(checkContactMap.containsKey(messager.getJobName())){
            	if(! lastSearchIndexSettedFlag)
            	{
            		lastSearchIndex = i;
            		lastSearchIndexSettedFlag = true;
            	}
                if(! JobStatus.RecheckingRequired.equals(messager.getJobStatus()))
                {
                	checkContactMap.remove(messager.getJobName());
                }else{
                	int retry = checkContactMap.get(messager.getJobName());
                	if(++retry < JobUtils.getMaxCheck())
                	{
                		checkContactMap.put(messager.getJobName(), retry);
                		needRecheckJobs.add(messager.getJobName());
                	}else{
                		checkContactMap.remove(messager.getJobName());
                	}                       	
                }
            }
        }
        if(checkContactMap.size() == 0)
        {
        	this.done = true;
        	return;
        }
        sendJob(application, sp, needRecheckJobs.toArray(new String[]{}), JobUtils.getRecheckDelay());
	}	
}

