package catfish.flowcontroller.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.flowcontroller.util.CommonUtils;

public class StateHelper {
	
	public static void save(Map<String, Object> dataMap, String key, String value)
	{
		dataMap.put(key, value);
	}
	
    public static void save(Map<String, Object> dataMap, String key, int value){
        dataMap.put(key, String.valueOf(value));
    }
    
    @SuppressWarnings("unchecked")
	public static <T> void  save(Map<String, Object> dataMap, Map<String, T> stateToSave)
    {
    	dataMap.putAll((Map<String, Object>)stateToSave);
    }
    
    public static <T> void save(Map<String, Object> dataMap, String key, Map<String, T> value)
    {
    	dataMap.put(key, value);
    }
    
    public static <T> void save(Map<String, Object> dataMap, String key, Set<T> value)
    {
       Map<String, Object> valueMap = new HashMap<>();
       valueMap.put(key, value);
       save(dataMap, valueMap);
    }
    
    public static <T> void save(Map<String, Object> dataMap, String key, List<T> value)
    {
    	Map<String, Object> valueMap = new HashMap<>();
    	valueMap.put(key, value);
    	save(dataMap, valueMap);
    }
    
    public static void save(Map<String, Object> dataMap, String key, long value){
        dataMap.put(key, String.valueOf(value));
    }
    public static int loadInt(Map<String, Object> dataMap, String key, int defaultValue){
        if(dataMap.containsKey(key)){
        	return CommonUtils.getIntValue(dataMap.get(key));
        } else {
            return defaultValue;
        }
    }
    
    
	public static  Map<String, Integer> loadStringIntMap(Map<String, Object> dataMap, String key)
    {
    	Map<String, Integer> stateMap = new HashMap<>();
    	
    	if(dataMap.containsKey(key)){
    		Object state = dataMap.get(key);
    		if(state instanceof Map<?, ?>)
    		{
    			@SuppressWarnings("unchecked")
				Map<String, Object> tempMap = (Map<String, Object>)state;
    			for(Entry<String, Object> item : tempMap.entrySet())
    			{
    				stateMap.put(item.getKey(), CommonUtils.getIntValue(item.getValue()));
    			}
    		}
    	}
    	return stateMap;
    }

    public static long loadLong(Map<String, Object> dataMap, String key, long defaultValue){
        if(dataMap.containsKey(key)){
            return Long.parseLong(dataMap.get(key).toString());
        } else {
            return defaultValue;
        }
    }
    
    public static List<String> loadArray(Map<String, Object> dataMap, String key)
    {    	
    	if(dataMap.containsKey(key)){
    		Object array = dataMap.get(key);
    		if(array instanceof List<?>){
    			@SuppressWarnings("unchecked")
				List<String> list = (List<String>)array;
                return list;
            }           
    	}
    	return new ArrayList<>();
    }
    
    public static Set<String> loadSet(Map<String, Object> dataMap, String key)
    {
    	if(dataMap.containsKey(key)){
    		Object value = dataMap.get(key);
    		if(value instanceof Set<?>)
    		{
    			@SuppressWarnings("unchecked")
				Set<String> set = (Set<String>)value;
    			return set;
    		}
    		if(value instanceof List<?>){
    			@SuppressWarnings("unchecked")
    			List<String> list = (List<String>)value;
    			return new HashSet<>(list);
    		}
    	}
    	return new HashSet<>();
    }
    
    public static Map<String, Object> loadMap(String stateJson)
	{
		if(stateJson !=null){
	        try{
	           return new Gson().fromJson(stateJson, new TypeToken<HashMap<String, Object>>() { }.getType());
	        } catch (JsonSyntaxException e) {
	            Logger.get().error("Can't load state map ", e);
	        }
	    }
		return null;
	}
    
	public static boolean loadActivityStates(Activity activity, String stateJson)
	{
		if(stateJson !=null){
            Map<String, Object> stateMap = loadMap(stateJson);
            
            if(stateMap == null)
            {
            	Logger.get().error("Can't parse activity state for " + activity.name);
            	return false;
            }
            else if(stateMap.size()>0){
                activity.loadState(stateMap);
                return true;
            }          	            
	    }
		return false;
	}  
	
	public static String loadString(Map<String, Object> dataMap, String key)
	{
		if(dataMap.containsKey(key))
		{
			return dataMap.get(key).toString();
		}
		return null;
	}
}
