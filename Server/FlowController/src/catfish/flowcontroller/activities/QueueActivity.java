package catfish.flowcontroller.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import catfish.base.DynamicConfig;
import catfish.base.queue.QueueConfig;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.util.CommonUtils;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class QueueActivity extends Activity{
    public String[] jobs;
    //为了支持一个Activity发送不同job到不同queue
    public Map<String, String> jobsMap;

    public String[] optionalJobs;
    public Map<String, String> optionalJobsMap;

    public String queueName = "JobRequestQueue";
    public Map<String, String> jobStatus;

    public String[] allJobs;
    public String[] allOptionalJobs;

    public int delay = 0;
    
    public String delayStr;
    //延迟是否启用动态配置
    public boolean dynamic = false;
    
    public String channel;

    @Override
    protected void init(Application application, IServiceProvider sp){
    	sendJob(application, sp);
    }

    @Override
    protected void process(Application application, IServiceProvider sp){
        if(allJobs!=null && allJobs.length>0){
            this.done = MessagerHelper.checkExist(application.messages, allJobs);
        } else {
            this.done = true;
        }
    }

    protected void sendJob(Application application, IServiceProvider sp){

    	if(dynamic)
    	{
    		delay = DynamicConfig.readAsInt(delayStr, 120);
    	}
        IQueueService queueService = sp.getService(IQueueService.class);
        IQueue queue = queueService.getQueue(queueName,"FlowController");

        String appId = application.appId;

        if(jobs!=null && jobs.length>0){
        	MessagerHelper.sendMessages(queue, appId, jobs, jobStatus, QueueConfig.QUEUE_PRIORITY_NORMAL, delay, channel);
        }

        if(jobsMap != null)
        {
        	for(Entry<String, String> item : jobsMap.entrySet())
            {
            	IQueue itemQueue = queueService.getQueue(item.getValue());
            	MessagerHelper.sendMessages(itemQueue, appId, item.getKey(), jobStatus, QueueConfig.QUEUE_PRIORITY_NORMAL, delay, channel);
            }
        }

        if(optionalJobs !=null && optionalJobs.length>0){
            MessagerHelper.sendMessages(queue, application.appId, optionalJobs, jobStatus, QueueConfig.QUEUE_PRIORITY_NORMAL, delay, channel);
        }

        if(optionalJobsMap != null)
        {
        	for(Entry<String, String> item : optionalJobsMap.entrySet())
        	{
        		IQueue itemQueue = queueService.getQueue(item.getValue());
            	MessagerHelper.sendMessages(itemQueue, appId, item.getKey(), jobStatus, QueueConfig.QUEUE_PRIORITY_NORMAL, delay, channel);
        	}
        }
    }
}

class QueueActivityFactory extends ActivityFactoryBase<QueueActivity>{
    @Override
    protected void setProperties(QueueActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object queueNameObj = activityConfig.get("queueName");
        if(queueNameObj != null){
            activity.queueName = queueNameObj.toString();
        }
        activity.jobs = loadAray(activityConfig.get("jobs"));
        activity.optionalJobs = loadAray(activityConfig.get("optionalJobs"));

        activity.jobsMap = loadMap(activityConfig.get("jobsMap"));
        if(activity.jobsMap != null || activity.jobs != null)
        {
        	if(activity.jobs == null)
        	{
        		activity.allJobs = activity.jobsMap.keySet().toArray(new String[]{});
        	}else if(activity.jobsMap == null){
        		activity.allJobs = activity.jobs;
        	}else{
        		List<String> temp = new ArrayList<>(Arrays.asList(activity.jobs));
            	temp.addAll(activity.jobsMap.keySet());
            	activity.allJobs = temp.toArray(new String[]{});
        	}
        }

        activity.optionalJobsMap = loadMap(activityConfig.get("optionalJobsMap"));
        if(activity.optionalJobs != null || activity.optionalJobsMap != null)
        {
        	if(activity.optionalJobs == null)
        	{
        		activity.allOptionalJobs = activity.optionalJobsMap.keySet().toArray(new String[]{});
        	}else if(activity.optionalJobsMap == null){
        		activity.allOptionalJobs = activity.optionalJobs;
        	}else{
        		List<String> temp = new ArrayList<>(Arrays.asList(activity.optionalJobs));
            	temp.addAll(activity.optionalJobsMap.keySet());
            	activity.allOptionalJobs = temp.toArray(new String[]{});
        	}
        }

        Object jobStatusMapObj = activityConfig.get("jobStatus");
        activity.jobStatus = loadMap(jobStatusMapObj);

        Object dynamicObject = activityConfig.get("dynamic");
        if(dynamicObject != null)
        {
        	activity.dynamic = Boolean.valueOf(dynamicObject.toString());
        }
        Object delaySecondsObj = activityConfig.get("delay");
        if(delaySecondsObj != null)
        {     	
        	if(activity.dynamic)
        	{
        		activity.delayStr =  delaySecondsObj.toString();
        		activity.delay = DynamicConfig.readAsInt(
        				delaySecondsObj.toString(), 120);
        	}else{
        		activity.delay = CommonUtils.getIntValue(delaySecondsObj);
        	}    	
        }
        Object channel = activityConfig.get("channel");
        if (channel != null) {
            activity.channel = (String) channel;
        }
    }
}
