package catfish.flowcontroller.activities.test;

import java.util.*;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.test.Application;
import catfish.flowcontroller.test.MessageInfo;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

public class QueueActivity extends Activity{
    public String job;
    public QueueMessager message;
    public String jobQueue = "JobStatusQueue";
    public String[] expectedJobs;
    public boolean responseExpectedJob = true;
    public String expectedQueue = "JobRequestQueue";
    private List<String> remainJobs = new ArrayList<String>();
    
    
    public void run(Application app, IServiceProvider sp){
        if(job!=null){
            IQueueService queueService = sp.getService(IQueueService.class);
            IQueue queue = queueService.getQueue(jobQueue, "FlowController.test");
            QueueMessager data = message;
            if(data!= null){
                data.setAppId(app.appId);
                data.setJobName(job);
            }else{
                data = new QueueMessager(app.appId, job);
            }
            queue.sendMessage(job, data);
        }
        if(expectedJobs != null && expectedJobs.length >0){
            for(String ej: expectedJobs){
                remainJobs.add(ej);
            }
        }
    }
    
    public boolean complete(Application app, IServiceProvider sp){
        if(expectedJobs == null || expectedJobs.length==0){
            pass = true;
            return true;
        }

        IQueueService queueService = sp.getService(IQueueService.class);
        IQueue queue = queueService.getQueue(jobQueue, "FlowController.test");
        for(int i = remainJobs.size()-1; i>=0; i--){
            String remainJob = remainJobs.get(i);
            for(int index=app.messages.size()-1; index>=0; index--){
                MessageInfo messageInfo = app.messages.get(index);
                String doneJob = messageInfo.jobName;
                if(doneJob.equals(remainJob)){
                    app.messages.remove(index);
                    if(!messageInfo.queueName.equals(expectedQueue)){
                        pass = false;
                        Logger.get().error(this.name + " Queue activity failed: unexpected queue " + messageInfo.queueName);
                        return true;
                    }
                    remainJobs.remove(i);
                    if(responseExpectedJob){
                        QueueMessager data = new QueueMessager(app.appId, remainJob, "Continuable");
                        queue.sendMessage(remainJob, data);
                    }
                    break;
                }
            }
        }

        pass = remainJobs.size() == 0;
        return pass;
    }
}
