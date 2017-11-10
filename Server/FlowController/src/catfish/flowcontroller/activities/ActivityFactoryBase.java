package catfish.flowcontroller.activities;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import catfish.base.Logger;
import catfish.flowcontroller.util.CommonUtils;

public class ActivityFactoryBase<T extends Activity> implements ActivityFactory{
    private Class<T> entityClass;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected ActivityFactoryBase(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ( (ParameterizedType)genType ).getActualTypeArguments();
        entityClass = (Class)params[0];
    }
    
    protected void setProperties(T activity, Map<String, Object> activityConfig){
        String name = activityConfig.get("name").toString();
        activity.name = name;
        activity.prerequisiteJobs = loadAray(activityConfig.get("prerequisiteJobs"));
        activity.prerequisiteActivities = loadAray(activityConfig.get("prerequisiteActivities"));
        
        Object oneOff = activityConfig.get("oneOff");
        if(oneOff != null)
        {
        	activity.oneOff = Boolean.valueOf(oneOff.toString());
        }
        
        Object waitOne = activityConfig.get("waitOne");
        if(waitOne != null)
        {
        	activity.waitOne = Boolean.valueOf(waitOne.toString());
        }
        
        Object linkedActivitiesObj = activityConfig.get("linkedActivities");
        if(linkedActivitiesObj != null)
        {
        	activity.linkedActivities = new ArrayList<>(Arrays.asList(loadAray(linkedActivitiesObj)));
        }
    }
    
    @SuppressWarnings("unchecked")
	protected final String[] loadAray(Object rawData){
        if(rawData == null || !(rawData instanceof List<?>)){
            return null;
        }
		List<String> list = (List<String>)rawData;
        return list.toArray(new String[]{});
    }
    
    @SuppressWarnings("unchecked")
	protected final <T> Map<String, T> loadMap(Object rawData)
    {
    	if(rawData == null || !(rawData instanceof Map<?, ?>))
    	{
    		return null;
    	}
    	return (Map<String, T>)rawData;
    }
    
    public  Map<String, Integer> loadStringIntMap(Object rawData)
    {
    	if(rawData == null || !(rawData instanceof Map<?, ?>))
    	{
    		return null;
    	}
    	Map<String, Integer> result = new HashMap<>();    	
		if(rawData instanceof Map<?, ?>)
		{
			@SuppressWarnings("unchecked")
			Map<String, Object> tempMap = (Map<String, Object>)rawData;
			for(Entry<String, Object> item : tempMap.entrySet())
			{
				result.put(item.getKey(), CommonUtils.getIntValue(item.getValue()));
			}
		}
    	return result;
    }
    
    public Activity create(Map<String, Object> activityConfig){
        T activity = null;
        try {
            activity = entityClass.newInstance();
            setProperties(activity, activityConfig);
        } catch (Exception e) {
            Logger.get().error("fail to create activity", e);
        }
        return activity;
    }
}

