package catfish.flowcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.ActivityRepository;
import catfish.flowcontroller.activities.StateHelper;
import catfish.flowcontroller.activities.TerminateActivity;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

import com.google.gson.Gson;

public class WorkFlow {
    private String name;
    private ActivityRepository repository;
    private ExecutionPlan executionPlan;
    
    public WorkFlow(String name){
        this.name = name;
    }
    
    public void init(ExecutionPlan executionPlan, ActivityRepository repository){
        this.repository = repository;
        this.executionPlan = executionPlan;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getProgress(Application application){
        //check terminated
        if (isApplicationCompleted( application)) {
            return 100;
        }
        
        int total = repository.size();
        int finished = application.finishedActivities.size();
        return (finished * 100)/total;
    }
    
    public boolean isApplicationCompleted(Application application) {
        if(application.finishedActivities!=null && application.finishedActivities.size()>0){
            List<String> keys = new ArrayList<String>(application.finishedActivities.keySet());
            for(String activityName:keys){
                Activity activity = repository.getActivity(activityName);
                if(activity instanceof TerminateActivity){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void onChange(QueueMessager messager, Application application, IServiceProvider sp){
        //initActivities(messager, application);
        List<String> possibleActivities = executionPlan.jobDone(messager.getJobName());

        addActivities(possibleActivities, application);
        
        executeActivities(application, sp);
    }
    
    private boolean addActivities(List<String> possibleActivities, Application application){
        if(possibleActivities==null || possibleActivities.size() ==0){
            return false;
        }
        
        boolean result = false;
        
        for(String activityName:possibleActivities){
        	Activity activity = repository.getActivity(activityName);
        	if(activity != null && activity.oneOff && application.finishedActivities.containsKey(activityName))
        	{
        		continue;
        	}
        	
        	if(! application.isUnfinishedActivityExist(activityName))
        	{
        		result = true;
        	}
        	
            if(application.getState(activityName) == null){
                Logger.get().info(String.format("WorkFlow.onChange plan activity for %s is %s ", application.appId, activityName));
                application.setState(activityName, null);
            }
        }
        return result;
    }
    
    private void executeActivities(Application application, IServiceProvider sp){
        List<String> keys = new ArrayList<String>(application.getState().keySet());
        List<String> initKeys = keys;
        while(true){
            boolean needTriggerActivities = false;
            //必须先排序，按依赖关系执行
            Logger.get().info("!!!!!!!!!!!!!!!!!!!!!! not sorted activities: " + keys);
            keys = executionPlan.sortActivities(keys, application.getState());
            Logger.get().info("!!!!!!!!!!!!!!!!!!!!!! sorted activities: " + keys);
            for(String activityName: keys){
                if(!application.getState().containsKey(activityName)){
                    continue;
                }
                if( executeActivity(activityName, application, sp) ){
                    needTriggerActivities = true; 
                }
            }
            List<String> fullKeys = new ArrayList<String>(application.getState().keySet());
            if(needTriggerActivities){
                keys = fullKeys;
                initKeys = fullKeys;
                continue;
            }
            
            List<String> newKeys = new ArrayList<String>();
            for(String key: fullKeys){
                if(!initKeys.contains(key)){
                    newKeys.add(key);
                }
            }
            if(newKeys.size() ==0){
                return;
            } else {
                keys = fullKeys;
                initKeys = fullKeys;
            }
        }
    }
    
    private boolean executeActivity(String activityName, Application application, IServiceProvider sp){
        Activity activity = repository.getActivity(activityName);
        if(activity == null ){
            Logger.get().error("can not get activity:" + activityName);
            return false;
        }
        synchronized(activity){
            activity.reset();
            Map<String, Object> stateMap;
            String stateJson = application.getState(activityName);
            StateHelper.loadActivityStates(activity, stateJson);
            activity.execute(application, sp);
            stateMap = new HashMap<String, Object>();
            activity.saveState(stateMap);
            String newState = new Gson().toJson(stateMap);
            if(activity.done){
                application.removeState(activity.name);
                application.addFinishedActivity(activity.name, newState);
                Logger.get().info(String.format("WorkFlow.onChange %s activity done", activity.name));
                List<String> possibleActivities = executionPlan.activityDone(activity.name);
                               
                List<String> linkedActivities = doneLinkedActivities(application, activity.linkedActivities);
                if(possibleActivities != null)
                {
                	possibleActivities.addAll(linkedActivities);
                }else{
                	possibleActivities = linkedActivities;
                }
                               
                return addActivities(possibleActivities, application);
            } else if(activity.terminated){
            	application.endTime = new Date().getTime();
            	application.removeState(activity.name);
                application.addFinishedActivity(activity.name, newState);
                Logger.get().info(String.format("WorkFlow.onChange %s activity terminated", activity.name));
                terminalLinkedActivities(application, activity.linkedActivities);
                return false;
            } else{
               
                application.setState(activityName, newState);
                
                return false;
            }
        }
    }
    
    private void terminalLinkedActivities(Application application, List<String> linkedActivities)
    {
    	for(String name : linkedActivities)
    	{
    		if(application.getState(name) == null)
    		{
    			continue;
    		}  
    		Activity activity = repository.getActivity(name);
    		if(activity != null)
    		{
    			synchronized(activity){
    				Map<String, Object> newState = new HashMap<>();
    				activity.terminate();
    				
                    activity.saveState(newState);                  
                    application.removeState(activity.name);
                    application.addFinishedActivity(activity.name, new Gson().toJson(newState));
                    
                    Logger.get().info(String.format("Linked activity %s terminated", name));
                    
                    //TODO
                    //递归向上terminate所有的linkedActivities,此处潜在一个bug，就是如果link链有环，则会导致死锁！
                    terminalLinkedActivities(application, activity.linkedActivities);
    			}
    		}
    	}
    }
    private List<String> doneLinkedActivities(Application application, List<String> linkedActivities)
    {    	
    	List<String> possibleActivities = new ArrayList<>();

    	for(String name : linkedActivities)
    	{
    		if(application.getState(name) == null)
    		{
    			continue;
    		}  
    		
    		//如果activity还被其他activity链接，则不done
    		if(checkIfLinkedByOthers(application, name))
    		{
    			continue;
    		}
    		
    		Activity activity = repository.getActivity(name);
            if(activity != null ){
                synchronized(activity){
                	Map<String, Object> newState = new HashMap<>();
                    activity.done = true;
                    activity.onDone();
                    
                    activity.saveState(newState);
                    
                    application.removeState(activity.name);
                    application.addFinishedActivity(activity.name, new Gson().toJson(newState));
                    
                    Logger.get().info(String.format("Linked activity %s done", name));
                    List<String> follows = executionPlan.activityDone(activity.name);
                    if(follows != null)
                    	possibleActivities.addAll(follows);
                    //TODO
                    //递归向上done所有的linkedActivities,此处潜在一个bug，就是如果link链有环，则会导致死锁！
                    possibleActivities.addAll(doneLinkedActivities(application, activity.linkedActivities));
                }        	
            }
    	}
    	return possibleActivities;
    }
    //检查是否正在被其他activity link
    private boolean checkIfLinkedByOthers(Application application, String linkedActivityName)
    {
    	Set<String> linkDowns = null;
    	for(String activity : application.getState().keySet())
    	{
    		linkDowns = executionPlan.linkDown(linkedActivityName);
    		if(linkDowns!= null && linkDowns.contains(activity))
    			return true;
    	}
    	
    	return false;
    }

	public ExecutionPlan getExecutionPlan() {
		return executionPlan;
	}

	public void setExecutionPlan(ExecutionPlan executionPlan) {
		this.executionPlan = executionPlan;
	}
}
