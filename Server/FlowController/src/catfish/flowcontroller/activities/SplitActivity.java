package catfish.flowcontroller.activities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class SplitActivity extends Activity{

	public static final int MAX = 1000;
	
	public Map<String, Double> branches;
	public String job;
	
	private Map<Integer, String> percentJobs = new HashMap<>();
	
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
	}
	
	@Override
	protected void init(Application application, IServiceProvider sp) {
		
		String linkedActivity = new Gson().toJson(buildLinkedActivityMap(Arrays.asList(new String[]{this.name})));
		
		Random random = new Random();
		int result = random.nextInt(MAX);
		String activityName = percentJobs.get(result);
		if(activityName != null)
		{
			application.setState(activityName, linkedActivity);
		}
	}

	@Override
	protected void process(Application application, IServiceProvider sp) {
		
	}

	public static void main(String[] args)
	{
		for(int i = 0; i < 1000; i ++)
		{
			Random random = new Random();
			System.out.println(random.nextInt(5));
		}
		
	}
}

class SplitActivityFactory extends ActivityFactoryBase<SplitActivity>
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
    protected void setProperties(SplitActivity activity, Map<String, Object> activityConfig){
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
        		Logger.get().warn("The sum of percents in SplitActivity should be 1(means 1000% cover), but now it is " + sum+ ", We will use default strategy.");
        	}
        	activity.initPercentJobs();  	
        }
    }
}
