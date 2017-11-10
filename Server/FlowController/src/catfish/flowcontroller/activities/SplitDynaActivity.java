
package catfish.flowcontroller.activities;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import catfish.flowcontroller.util.FlowControllerDynamicConfig;


public class SplitDynaActivity extends Activity{
	
	public static final int MAX = 1000;
	public Map<String, Double> branches;
	public String job;
	private Map<Integer, String> percentJobs = new HashMap<>();
	private Map<Integer, String> machinePercentJobs = new HashMap<>();
	private static Map<Integer,Map<Integer,String>> cacheMap=new HashMap<Integer,Map<Integer,String>>();
	
	private  Double turnString2Double(String ikey,String ivalue){
		if(StringUtils.isNullOrWhiteSpaces(ivalue)){
			return null;
		}
		Double doubleValue=null;
		try{
			doubleValue = Double.parseDouble(ivalue.toString()); 
		}catch(NumberFormatException e){
			String str="dynamic.properties, key：%s。value：%s。 ";
			str=str.format(str, ikey,ivalue);
			Logger.get().error(str);
		}
		return doubleValue;
	}
	private Map<Integer,String> dynaGetPercentJobs(Map<String,String> map){
		int key=map.toString().hashCode();
		Map<Integer,String> cache=cacheMap.get(key);
		if(cache!=null && !cache.isEmpty()){
			Logger.get().info(String.format("SplitDynaActivity cache: %s", map.toString()));
			return cache;
		}else{
			Map<String,Double> randomMap=new HashMap<String,Double>();
			double sum=0;
			for(Entry<String,String> item : map.entrySet()){
				String ikey=item.getKey();
				String ivalue=item.getValue();
				Double dval=turnString2Double(ikey,ivalue);
				if(dval==null){
					//配置文件中的值不符合，还用app.js中的配置
					return percentJobs;
				}
				sum+=dval;
				randomMap.put(ikey, dval);
			}
			if(sum > 1){
				//配置文件中的值不符合，还用app.js中的配置
				return percentJobs;
			}
			cache = fillPercentJobs(randomMap);
			cacheMap.clear();
			cacheMap.put(key, cache);
			return cache;
		}
	}
	private Map<Integer, String> fillPercentJobs(Map<String, Double> branches){
		machinePercentJobs.clear();
		int preIndex = 0;
		int followIndex = 0;
		for(Entry<String, Double> item : branches.entrySet())
		{
			int percent = (int) (item.getValue()*1000);
			followIndex = preIndex + percent;
			if(followIndex > MAX) followIndex = MAX;
						
			for(int i = preIndex; i < followIndex; i ++)
			{
				machinePercentJobs.put(i, item.getKey());
			}
			preIndex = followIndex;
			
			if(followIndex == MAX)
				break;		
		}
		return machinePercentJobs;
	}
	
	public void initPercentJobs()
	{
		int preIndex = 0;
		int followIndex = 0;
		for(Entry<String, Double> item : branches.entrySet())
		{
			int percent = (int) (item.getValue()*1000);
			followIndex = preIndex + percent;
			if(followIndex > MAX) followIndex = MAX;
						
			for(int i = preIndex; i < followIndex; i ++)
			{
				percentJobs.put(i, item.getKey());
			}
			preIndex = followIndex;
			
			if(followIndex == MAX)
				break;		
		}
		cacheMap.put(branches.entrySet().hashCode(),percentJobs);
	}

	@Override
	protected void init(Application application, IServiceProvider sp) {
		Logger.get().info("SplitDynaActivity is running ...");
		String linkedActivity = new Gson().toJson(buildLinkedActivityMap(Arrays.asList(new String[]{this.name})));
		
		Random random = new Random();
		int result = random.nextInt(MAX);
		//读取配置文件dynamic.properties，key值为branches的key
		Iterator<String> iterator=branches.keySet().iterator();
		Map<String,String> map=FlowControllerDynamicConfig.readSplitDyna(iterator);
		Logger.get().info(String.format("SplitDynaActivity values：：：：%s", map.toString()));
		if(map!=null && !map.isEmpty()){
			percentJobs = dynaGetPercentJobs(map);
		}
		String activityName = percentJobs.get(result);
		Logger.get().info(String.format("SplitDynaActivity get value：：：：%s", activityName));
		if(activityName != null)
		{
			application.setState(activityName, linkedActivity);
		}	
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		
	}

}
class SplitDynaActivityFactory extends ActivityFactoryBase<SplitDynaActivity>
{
	private double getPercentSum(Map<String, Double> branches)
	{
		double sum = 0;
		for(Entry<String, Double> item : branches.entrySet())
		{
			sum += item.getValue();
		}
        return sum;
	}
	
	@Override
    protected void setProperties(SplitDynaActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);
                    
        Object job = activityConfig.get("job");
        if(job != null){
        	activity.job = job.toString();
        }
        
        activity.branches = this.loadMap(activityConfig.get("branches"));
        
        if(activity.branches != null)
        {
        	double sum = getPercentSum(activity.branches); 
        	if(sum*1000 != 1000)
        	{
        		Logger.get().warn("The sum of percents in SplitDynaActivity should be 1(means 1000% cover), but now it is " + sum+ ", We will use default strategy.");
        	}
        	activity.initPercentJobs();
        }
    }
}

