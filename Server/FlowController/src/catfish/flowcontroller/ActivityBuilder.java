package catfish.flowcontroller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import catfish.base.Logger;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.ActivityManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ActivityBuilder {
    public static List<Activity> load(String name){
        try {
            InputStream stream = new FileInputStream("config/"+name+".js");
            List<HashMap<String, Object>> configList = new Gson().fromJson(new InputStreamReader(stream), new TypeToken<List<HashMap<String, Object>>>() { }.getType());
            return create(configList, ActivityManager.instance);
        } catch (FileNotFoundException e) {
            Logger.get().error("can not find config file for "+ name, e);
        } catch (JsonSyntaxException e) {
            Logger.get().error("invalid json format for config " + name, e);
        }
        return null;
    }
    
    public static List<Activity> create(List<HashMap<String, Object>> configList, ActivityCreator creator){
        ArrayList<Activity> list = new ArrayList<Activity>();
        for(HashMap<String, Object> activityConfig : configList){
            if(activityConfig != null){
                Activity activity = creator.create(activityConfig);
                if(activity!=null){
                    list.add(activity);
                }else{
                    Logger.get().warn("can not create activity:" + activityConfig.get("name"));
                }
            }
        }
        return list;
    }
}
