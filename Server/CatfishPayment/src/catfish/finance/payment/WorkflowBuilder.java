package catfish.finance.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.finance.payment.activities.Creator;
import catfish.finance.payment.api.PaymentChannel;
import catfish.flowcontroller.ActivityBuilder;
import catfish.flowcontroller.WorkFlow;
import catfish.flowcontroller.WorkFlowFactory;
import catfish.flowcontroller.activities.Activity;

public class WorkflowBuilder {

    public static List<HashMap<String, Object>> build(List<PaymentChannel> channels){
        List<HashMap<String, Object>> configList = new ArrayList<HashMap<String, Object>>();
        String lastActivityName = null;
        for(PaymentChannel channel: channels){
            String channelName = channel.getName();
            HashMap<String, Object> paymentActivityConfig = new HashMap<String, Object>();
            String activityName = channelName + "paymentActivity";
            paymentActivityConfig.put("name", activityName);
            paymentActivityConfig.put("type", "paymentActivity");
            paymentActivityConfig.put("paymentChannel", channelName);
            if(lastActivityName != null){
                paymentActivityConfig.put("prerequisiteActivities", toList(lastActivityName));
            } else {
                paymentActivityConfig.put("prerequisiteJobs", toList("startPayment"));
            }
            configList.add(paymentActivityConfig);
            lastActivityName = activityName;
            
            HashMap<String, Object> queryActivityConfig = new HashMap<String, Object>();
            activityName = channelName + "queryActivity";
            queryActivityConfig.put("name", activityName);
            queryActivityConfig.put("type", "queryActivity");
            queryActivityConfig.put("prerequisiteJobs", toList(getQueryJobName(channelName)));
            queryActivityConfig.put("prerequisiteActivities", toList(lastActivityName));
            configList.add(queryActivityConfig);
            
            lastActivityName = activityName;
        }
        
        return configList;
    }
    
    public static String getQueryJobName(String channelName){
        return channelName + "QueryPayment";
    }
    
    private static List<String> toList(String...items){
        ArrayList<String> result= new ArrayList<String>();
        for(String item: items){
            result.add(item);
        }
        return result;
    }
    
    public static WorkFlow build(String appId, List<HashMap<String, Object>> activityConfigs){
//        String json = new Gson().toJson(activityConfigs);
//        activityConfigs = new Gson().fromJson(json, new TypeToken<List<HashMap<String, Object>>>() { }.getType());
        List<Activity> activities = ActivityBuilder.create(activityConfigs, new Creator());
        return WorkFlowFactory.createWorkflow(appId, activities);
    }
}
