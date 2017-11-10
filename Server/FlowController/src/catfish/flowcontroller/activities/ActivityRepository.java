package catfish.flowcontroller.activities;

import java.util.*;

import catfish.base.Logger;


public class ActivityRepository {
    private Map<String, Activity> map = new HashMap<String, Activity>();
    
    public void register(String name, Activity activity){
        if(name ==null ){
            Logger.get().warn("ActivityRepository.register: name is null");
            return;
        }
        if(activity ==null ){
            Logger.get().warn("ActivityRepository.register: activity is null");
            return;
        }
        map.put(name, activity);
    }
    
    public Activity getActivity(String name){
        if(name ==null ){
            Logger.get().warn("ActivityRepository.getActivity: name is null");
            return null;
        }
        if(map.containsKey(name)){
            return map.get(name);
        }
        return null;
    }
    
    public int size(){
        return map.size();
    }
    
    public Set<String> getAll(){
        return map.keySet();
    }
}
