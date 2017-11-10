package catfish.flowcontroller.activities;

import java.util.List;
import java.util.Map;

import catfish.base.StartupConfig;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.DomainConsts;
import catfish.flowcontroller.models.Application;
import catfish.framework.queue.IQueue;

public class MessagerHelper {
	public static final String jobQueuename =  
			 StartupConfig.get("catfish.flowcontroller.jobqueue", "JobStatusQueue");
    public static void sendMessages(IQueue queue, String appId, String[] jobs){
        sendMessages(queue, appId, jobs, null);
    }

    public static void sendMessages(IQueue queue, String appId, String[] jobs, int priority, int delaySeconds)
    {
    	for(String jobName: jobs){
            QueueMessager data = new QueueMessager(appId, jobName);
            data.setCallbackQueue(jobQueuename);
            queue.sendMessage(jobName, data, priority, delaySeconds);
        }
    }

    public static void sendMessages(IQueue queue, String appId, String[] jobs, Map<String, String> jobStatus)
    {
    	sendMessages(queue, appId, jobs, jobStatus, 0, 0, null);
    }

    public static void sendMessages(
        IQueue queue, String appId, String[] jobs, Map<String, String> jobStatus, int priority, int delaySeconds, String channel)
    {
    	for(String jobName: jobs){
    		QueueMessager data = null;
    		if(jobStatus != null)
    		{
    			if(jobStatus.containsKey(jobName))
    			    data = new QueueMessager(appId, jobName, jobStatus.get(jobName));
    			else
    				data = new QueueMessager(appId, jobName);
    		}
    		else{
    			data = new QueueMessager(appId, jobName);
    		}

    		if (channel != null) {
    		    data.setChannel(channel);
    		}
    		data.setCallbackQueue(jobQueuename);
    		if(delaySeconds > 0)
    		{
    			queue.sendMessage(jobName, data, priority, delaySeconds);
    		}else{
    			queue.sendMessage(jobName, data);
    		}
    	}
    }

    public static void sendMessages(IQueue queue, String appId, String job)
    {
    	sendMessages(queue, appId, job, null);
    }

    public static void sendMessages(IQueue queue, String appId, String job, Map<String, String> jobStatus)
    {
    	sendMessages(queue, appId, job, jobStatus, 0, 0, null);
    }

    public static void sendMessages(
        IQueue queue, String appId, String job, Map<String, String> jobStatus, int priority, int delaySeconds, String channel)
    {
    	QueueMessager data = null;
    	if(jobStatus != null && jobStatus.containsKey(job))
    	{
    		data = new QueueMessager(appId, job, jobStatus.get(job));
    	}else{
    		data = new QueueMessager(appId, job);
    	}

    	if (channel != null) {
    	    data.setChannel(channel);
    	}
    	data.setCallbackQueue(jobQueuename);
    	if(delaySeconds > 0)
		{
			queue.sendMessage(job, data, priority, delaySeconds);
		}else{
			queue.sendMessage(job, data);
		}
    }

    public static boolean checkExist(List<QueueMessager> messagers, String job, Predicate<QueueMessager> condition){
        for(QueueMessager messager:messagers){
            if(job.equals(messager.getJobName()) && (condition == null || condition.test(messager))){
                return true;
            }
        }
        return false;
    }

    public static boolean checkExist(List<QueueMessager> messagers, String[] jobs, Predicate<QueueMessager> condition){
        if(jobs == null || jobs.length==0){
            return true;
        }
        for(String jobName: jobs){
            boolean found = checkExist(messagers, jobName, condition);
            if(!found){
                return false;
            }
        }
        return true;
    }

    public static boolean checkExist(List<QueueMessager> messagers, String[] jobs){
    	return checkExist(messagers, jobs, null);
    }

  //当查找到job后更新activity中对应job项的lastSearchIndex
    public static QueueMessager findQueueMessager(Activity activity, Application application, String jobName)
    {
    	QueueMessager expected = null;
    	for(int i=application.messages.size()-1; i > activity.lastSearchIndex; i--){
            QueueMessager messager = application.messages.get(i);
            if(jobName.equals(messager.getJobName())){
            	activity.lastSearchIndex = i;
                expected = messager;
                break;
            }
        }
    	return expected;
    }

    //当查找到job后更新lastSearchIndexMap中对应job项的最后查找位置
    public static QueueMessager findQueueMessager(Map<String, Integer> lastSearchIndexMap, Application application, String jobName)
    {
    	QueueMessager expected = null;
    	int lastSearchIndex = (lastSearchIndexMap.get(jobName) == null ? -1 : lastSearchIndexMap.get(jobName));

    	for(int i=application.messages.size()-1; i > lastSearchIndex; i--){
            QueueMessager messager = application.messages.get(i);
            if(jobName.equals(messager.getJobName())){
            	lastSearchIndexMap.put(jobName, i);
                expected = messager;
                break;
            }
        }
    	return expected;
    }
}
