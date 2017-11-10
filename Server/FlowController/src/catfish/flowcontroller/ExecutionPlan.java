package catfish.flowcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import catfish.base.Logger;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.ActivityRepository;
import catfish.flowcontroller.activities.StateHelper;

public class ExecutionPlan {
    private ActivityRepository repository;
    private Map<String,List<String>> jobActivitiesMap = new HashMap<String,List<String>>();
    private Map<String,List<String>> activitiesMap = new HashMap<String,List<String>>();
    
    //记录link关系，value为link到key的activities's name
    private Map<String, Set<String>> linkDownActivitiesMap = new ConcurrentHashMap<String, Set<String>>();
    
    public ExecutionPlan(ActivityRepository repository){
        this.repository = repository;
        this.init();
    }
    
    private void init(){
        Set<String> activityNames = repository.getAll();
        for(String name: activityNames){
            Activity activity = repository.getActivity(name);
            String[] prerequisiteJobs = activity.prerequisiteJobs;
            if( prerequisiteJobs !=null && prerequisiteJobs.length >0){
                for(String job: prerequisiteJobs){
                    setJobPlan(job, name);
                }
            }
            String[] prerequisiteActivities = activity.prerequisiteActivities;
            if( prerequisiteActivities !=null && prerequisiteActivities.length >0){
                for(String pa: prerequisiteActivities){
                    setActivityPlan(pa, name);
                }
            }
            
            List<String> linkedActivities = activity.linkedActivities;
            setLinkPlan(name, linkedActivities);
        }
    }
    
    private void setJobPlan(String job, String activityName){
        if(!jobActivitiesMap.containsKey(job)){
            jobActivitiesMap.put(job, new ArrayList<String>());
        }
        
        jobActivitiesMap.get(job).add(activityName);
    }
    
    private void setActivityPlan(String pa, String activityName){
        if(!activitiesMap.containsKey(pa)){
            activitiesMap.put(pa, new ArrayList<String>());
        }
        
        activitiesMap.get(pa).add(activityName);
    }
    
    private synchronized void setLinkPlan(String activityName, List<String> linkedActivities)
    {
    	 if(linkedActivities != null && linkedActivities.size() > 0){
         	for(String link : linkedActivities){
         		if(!linkDownActivitiesMap.containsKey(link))
            	{
            		linkDownActivitiesMap.put(link, new HashSet<String>());
            	}
            	
            	linkDownActivitiesMap.get(link).add(activityName);
         	}
         }	
    }
       
    public void updateLinkPlan(String activityName, List<String> linkedActivities)
    {
    	this.setLinkPlan(activityName, linkedActivities);
    }
    
    public Set<String> linkDown(String activity)
    {
       return linkDownActivitiesMap.get(activity);	
    }
    
    public List<String> jobDone(String job){
        return jobActivitiesMap.get(job);
    }
    
    public List<String> activityDone(String activity){
        return activitiesMap.get(activity);
    }
    
    private <T> Map<String, T> initTempMap(List<String> srcList, T value)
    {
    	Map<String, T> temp = new HashMap<>();
    	for(String item : srcList)
    	{
    		temp.put(item, value);
    	}
    	return temp;
    }
  
    private Map<String, Integer> recursiveSortActivities(Map<String, Integer> oldStoreMap, Map<String, Integer> newStoreMap, Map<String, String> states)
    {
    	Map<String, Integer> currentStoreMap = new HashMap<>();
    	currentStoreMap.putAll(newStoreMap);
    	
    	boolean needRecursiveUpdate = false;
    	for(Entry<String, Integer> item : newStoreMap.entrySet())
    	{
    		String currentActivityName = item.getKey();
    		if(!item.getValue().equals(oldStoreMap.get(currentActivityName)))
    		{
    			List<String> followList = activityDone(currentActivityName);
    			if(followList == null) continue;
    			
    			needRecursiveUpdate = true;
    			
    			for(String followActivityName : followList)
        		{
        			if(currentStoreMap.containsKey(followActivityName))
        			{
        				currentStoreMap.put(followActivityName, currentStoreMap.get(followActivityName)+ newStoreMap.get(currentActivityName)-oldStoreMap.get(currentActivityName));
        				Activity activity = repository.getActivity(followActivityName);
        				if(activity != null)
        				{
        					synchronized(activity)
        					{
        						StateHelper.loadActivityStates(activity, states.get(followActivityName));
                				if(activity.linkedActivities != null && activity.linkedActivities.size() > 0)
                				{
                					for(String linkedActivityName : activity.linkedActivities)
                					{
                						if(currentStoreMap.containsKey(linkedActivityName))
                						{
                							currentStoreMap.put(linkedActivityName, currentStoreMap.get(linkedActivityName)+newStoreMap.get(currentActivityName)-oldStoreMap.get(currentActivityName));
                						}
                					}
                				}	
        					}
        				}				
        			}
        		}
    		}
    	}
    	if(needRecursiveUpdate)
    		return recursiveSortActivities(newStoreMap, currentStoreMap, states);
    	else
    		return currentStoreMap;
    }
    
    public List<String> sortActivities(List<String> srcList, Map<String, String> states)
    {
    	if(srcList.size() <= 1)
    		return srcList;

    	Map<String, Integer> finalStoreMap = recursiveSortActivities(initTempMap(srcList, 0), initTempMap(srcList, 1), states);
    	
    	List<Map.Entry<String, Integer>> sortList = new ArrayList<>(finalStoreMap.entrySet());
    	Logger.get().info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~sortedList: " + sortList);
    	Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>(){

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o1.getValue() - o2.getValue(); 
			}
    		
    	});
    	
    	List<String> result = new ArrayList<>();
    	for(Map.Entry<String, Integer> item : sortList)
    	{
    		result.add(item.getKey());
    	}
    	return result;
    }
}

