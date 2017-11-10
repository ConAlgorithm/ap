package catfish.flowcontroller.activities.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ActivityBuilder {
    public static List<String> getTestFlows(){
        File testcasesFolder= new File("testflows");
        File[] list = testcasesFolder.listFiles();
        List<String> flows = new ArrayList<String>();
        for(File file:list){
            flows.add(file.getName().replaceAll(".js", ""));
        }
        return flows;
    }
    
    public static List<Activity> load(String name){
        ArrayList<Activity> list = new ArrayList<Activity>();
        try {
            InputStream stream = new FileInputStream("testflows/"+name+".js");
            List<HashMap<String, Object>> configList = new Gson().fromJson(new InputStreamReader(stream), new TypeToken<List<HashMap<String, Object>>>() { }.getType());
            for(HashMap<String, Object> activityConfig : configList){
                if(activityConfig != null){
                    Activity activity = create(activityConfig);
                    list.add(activity);
                }
            }
        } catch (FileNotFoundException e) {
            Logger.get().error("can not find config file for "+ name, e);
        } catch (JsonSyntaxException e) {
            Logger.get().error("invalid json format for config " + name, e);
        }
        return list;
    }
    
    private static Activity create(HashMap<String, Object> activityConfig){
        Object tempObj = activityConfig.get("type");
        if(tempObj != null){
            String type = tempObj.toString();
            if(type.equals("message")){
                return createMessageActivity(activityConfig);
            }
        }
        return createQueueActivity(activityConfig);
    }
    
    private static Activity createMessageActivity(HashMap<String, Object> activityConfig){
        MessageActivity activity = new MessageActivity();

        activity.name = activityConfig.get("name").toString();
        activity.notificationName = activityConfig.get("notificationName").toString();
        Object tempObj = activityConfig.get("extraInfo");
        if(tempObj != null){
            activity.extraInfo = tempObj.toString();
        }
        return activity;
    }
    
    private static Activity createQueueActivity(HashMap<String, Object> activityConfig){
        QueueActivity step = new QueueActivity();

        step.name = activityConfig.get("name").toString();
        Object tempObj = activityConfig.get("job");
        if(tempObj != null){
            step.job = tempObj.toString();
        }
        tempObj = activityConfig.get("message");
        if(tempObj != null){
            Gson gson = new Gson();
            String jsonStr= gson.toJson(tempObj);
            try{
                step.message = QueueMessager.fromString(jsonStr);
            }catch(Exception ex){
                Logger.get().error("invalid json format for message " + jsonStr, ex);
            }
        }
        
        tempObj = activityConfig.get("expectedQueue");
        if(tempObj != null){
            step.expectedQueue = tempObj.toString();
        }
        step.expectedJobs = loadArray(activityConfig.get("expectedJobs"));
        tempObj = activityConfig.get("responseExpectedJob");
        if(tempObj != null){
            step.responseExpectedJob = Boolean.parseBoolean(tempObj.toString());
        }
        return step;
    }
    
    protected static final String[] loadArray(Object rawData){
        if(rawData == null || !(rawData instanceof List<?>)){
            return null;
        }
        List<String> list = (List<String>)rawData;
        return list.toArray(new String[]{});
    }
}
