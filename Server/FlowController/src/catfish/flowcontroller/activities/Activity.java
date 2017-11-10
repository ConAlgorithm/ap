package catfish.flowcontroller.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public abstract class Activity {
    static final String StatusKey = "status";
    private static final String LastSearchIndex = "lastSearchIndex";
   
    static final String StartTime ="startTime";
    static final String EndTime ="endTime";
    
    public static final String LinkedActivities = "linkedActivities";
    //将多个activitylink在一起，当最后一个done或terminated之后，会将link中所有activity改为done或terminate，使用时需要特别小心，如果link中包含了环，则会导致死锁！！
    public List<String> linkedActivities = new ArrayList<>();
    
    public String[] prerequisiteJobs;
    public String[] prerequisiteActivities;
    public String name;
    public boolean waitOne = false;
    
    //state
    public boolean done = false;
    //整个流程是否终止标志
    public boolean terminated = false;
    public int status = -1;
    public long startTime = -1;
    public long endTime = -1;
    
    //记录上次查找的结束位置,再后续收到消息后只查找至该位置
    public int lastSearchIndex = -1;
    //标记为一次性，表示这个activity只会被执行一次
    public boolean oneOff = false;
    
    public void reset(){
    	terminated = false;
        done = false;
        status = -1;
        lastSearchIndex = -1;
        startTime = -1;
        endTime = -1;

    }
    
    public final void execute(Application application, IServiceProvider sp){
        onExecute(application, sp);
    }
    
    protected Map<String, Object> buildLinkedActivityMap(List<String> links)
    {
    	Map<String, Object> linkMap = new HashMap<>();
    	linkMap.put(LinkedActivities, links);
    	return linkMap;
    }
    
    protected void addLinkedActivity(String activityName)
    {
    	linkedActivities.add(activityName);
    }
    
    public void saveState(Map<String, Object> dataMap){
        StateHelper.save(dataMap, StatusKey, status);
        StateHelper.save(dataMap, LastSearchIndex, lastSearchIndex);
        StateHelper.save(dataMap, StartTime, startTime);
        StateHelper.save(dataMap, EndTime, endTime);
        StateHelper.save(dataMap, buildLinkedActivityMap(linkedActivities));
    }
    
    public void loadState(Map<String, Object> dataMap){
        status = StateHelper.loadInt(dataMap, StatusKey, -1);
        lastSearchIndex = StateHelper.loadInt(dataMap, LastSearchIndex, -1);
        startTime = StateHelper.loadLong(dataMap, StartTime, -1);
        endTime = StateHelper.loadLong(dataMap, EndTime, -1);
        linkedActivities = StateHelper.loadArray(dataMap, LinkedActivities);
    }
    
    protected void onExecute(Application application, IServiceProvider sp){
        if(status == -1){
            if( this.checkReady(application, sp) ){
                this.init(application, sp);
                startTime = new Date().getTime();
                status = 0;
            }
        }
        if(status == 0){
            process(application, sp);
            if(this.done){
            	onDone();
            }
        }
    }
    
    public void onDone()
    {
    	status = 1;
        this.endTime = new Date().getTime();
    }
    
    //结束整个流程
    public void terminate()
    {
    	status = 2;
    	this.terminated = true;
    	this.endTime = new Date().getTime();
    }
    
    protected boolean checkReady(Application application, IServiceProvider sp){
        if(prerequisiteJobs != null && prerequisiteJobs.length>0){
            if( !MessagerHelper.checkExist(application.messages, prerequisiteJobs) ){
                return false;
            }
        }
        if(prerequisiteActivities != null && prerequisiteActivities.length>0){
            if(application.finishedActivities == null || application.finishedActivities.size() == 0){
                return false;
            }
            int count = 0;
            for(String activityName: prerequisiteActivities){
                if(activityName == null || activityName.length() ==0){
                    continue;
                }

                if(!application.finishedActivities.containsKey(activityName)){
                	if(!waitOne) return false;
                } else if(waitOne){
                    return true;
                } else{
                	count ++;
                }
            }
            if(count != prerequisiteActivities.length)
            	return false;
        }
        
        return true;
    }
    
    protected abstract void init(Application application, IServiceProvider sp);
    
    protected abstract void process(Application application, IServiceProvider sp);
}
