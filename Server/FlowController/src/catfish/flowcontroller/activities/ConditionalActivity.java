package catfish.flowcontroller.activities;

import java.util.Arrays;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;

import com.google.gson.Gson;

public class ConditionalActivity extends Activity{
    private static final String Default = "default";
    public Map<String, String> choices;
    public String key;
    
    public String job = null;
    public String queueName;
    
    public void saveState(Map<String, Object> dataMap){
        super.saveState(dataMap);      
    }
    
    public void loadState(Map<String, Object> dataMap){
        super.loadState(dataMap);
    }
    
    @Override
    protected void init(Application application, IServiceProvider sp){
        
        
        if(job != null)
        {
        	sendJob(application, sp);
        }
        else{
        	if(this.prerequisiteJobs ==null || this.prerequisiteJobs.length ==0
                    || application.messages==null || application.messages.size()==0){
                return;
            }
        	int index = this.prerequisiteJobs.length-1;
            String preJob = this.prerequisiteJobs[index];
            QueueMessager expected = MessagerHelper.findQueueMessager(this, application, preJob);
            addChoiceJobs(application, expected, new String[]{preJob});
        }             
    }
    
    @Override
    protected void process(Application application, IServiceProvider sp){
    	if(job == null)
    	{
    		this.done = true;
    	}
    	else{
    		QueueMessager expected = MessagerHelper.findQueueMessager(this, application, job);
    		if(expected != null)
    		{    			
    			addChoiceJobs(application, expected, new String[]{this.name});
    		}
    	}    
    }
    
    private void addChoiceJobs(Application application, QueueMessager expected, String[] linkedActivities)
    {
    	String linkState = null;
    	if(linkedActivities != null)
    	{
    		linkState = new Gson().toJson(buildLinkedActivityMap(Arrays.asList(linkedActivities)));
    	}   		
    	if(expected != null)
        {
    		String condition = null;
    		if(key == null || key.equals(ActivityConsts.StatusKey))
    		{
    			condition = expected.getJobStatus();
    		}else if(key.equals(ActivityConsts.DataIntKey)){
    			condition = expected.getJobDataInt() + "";
    		}
        	//只有当states里面没有需要做的activity时才添加，否则会丢失状态
            
            if (choices.containsKey(condition)) {
                if(!application.getState().containsKey(choices.get(condition))) {
                    application.setState(choices.get(condition), linkState);
                }else{
                    Logger.get().warn(
                            String.format(
                                    "Ignore repeated condition %s for application %s, when choices : %s, linkState : %s "
                                    , condition
                                    , application.appId
                                    , new Gson().toJson(choices)
                                    , linkState));
                }
            }else if (choices.containsKey(Default)){
                if(!application.getState().containsKey(choices.get(Default))) {
                    application.setState(choices.get(Default), linkState);
                }else{
                    Logger.get().warn(
                            String.format(
                                    "Ignore repeated default condition for application %s, when choices : %s, linkState : %s "
                                    , application.appId
                                    , new Gson().toJson(choices)
                                    , linkState));
                }
            }else {
                this.done = true;
                Logger.get().warn(
                        String.format("Invalid condition %s received for application %s, when choices : %s, linkState : %s "
                                , condition
                                , application.appId
                                , new Gson().toJson(choices)
                                , linkState));
            }
        }   

    }
    protected void sendJob(Application application, IServiceProvider sp){
        
        IQueueService queueService = sp.getService(IQueueService.class);
        IQueue queue = queueService.getQueue(queueName,"FlowController");  
        
        MessagerHelper.sendMessages(queue, application.appId, new String[]{job});      
    }
}

class ConditionalActivityFactory extends ActivityFactoryBase<ConditionalActivity>{
	@Override
    protected void setProperties(ConditionalActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);

        Object queueNameObj = activityConfig.get("queueName");
        if(queueNameObj != null){
            activity.queueName = queueNameObj.toString();
        }
        
        Object choicesObj = activityConfig.get("choices");
        activity.choices = this.loadMap(choicesObj);
        
        Object keyObj = activityConfig.get(ActivityConsts.KeyWord);
        if(keyObj != null)
        {
        	activity.key = keyObj.toString();
        }
                
        Object jobObj = activityConfig.get("job");
        if(jobObj != null)
        {
        	activity.job = jobObj.toString();
        }
    }
}
