package catfish.flowcontroller.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.WorkFlow;
import catfish.flowcontroller.WorkflowProvider;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.StateHelper;

public class Application {
    public String _id;
    public String appId;
    public String workflow;
    public long startTime = -1;
    public long endTime = -1;
    public List<QueueMessager> messages = new ArrayList<QueueMessager>();
    public Map<String, String> finishedActivities = new HashMap<String, String>();
    public WorkflowProvider workflowProvider;
    private Map<String, String> states = new HashMap<String, String>();
    public Date update_time;
    public Date create_time;
    
    public void addMessager(QueueMessager messager){
        messages.add(messager);
    }
    
    public void addFinishedActivity(String activity, String state){
        finishedActivities.put(activity, state);
    }
    
    public boolean isEmpty(){
        return (states.size() ==0);
    }
    
    public void setState(String activity, String state){
        states.put(activity, state);
        if(state != null)
        {
        	Map<String, Object> stateMap = StateHelper.loadMap(state);
        	if(stateMap != null && stateMap.containsKey(Activity.LinkedActivities))
        	{
        		WorkFlow flow = workflowProvider.get(workflow);
            	flow.getExecutionPlan().updateLinkPlan(activity, StateHelper.loadArray(stateMap, Activity.LinkedActivities));
        	}
        }
    }
    
    public boolean isUnfinishedActivityExist(String activity)
    {
    	return states.containsKey(activity);
    }
    
    public Map<String, String> getState()
    {
    	return states;
    }
    
    public String getState(String activity){
        return states.get(activity);
    }
    
    public void removeState(String activity){
        states.remove(activity);
    }
}
