package catfish.fundcontroller.util;

import catfish.base.queue.QueueMessager;
import catfish.framework.queue.IQueue;

public class MessagerHelper {
    
    public static void sendMessages(IQueue queue,String appId,String jobName,int jobDataInt,int delaySeconds,int priority)
    {
    	QueueMessager data=new QueueMessager(appId,jobName,jobDataInt);
    	queue.sendMessage(jobName, data, priority, delaySeconds);
    }
}
