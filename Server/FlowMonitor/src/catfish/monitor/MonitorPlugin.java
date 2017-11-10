package catfish.monitor;

import java.util.List;
import java.util.Set;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;

public class MonitorPlugin implements IPlugin, Runnable{
    private static final Long MS_IN_MINUTE= 60000l;
    
    private String url;
    private Long interval;
    private Long maxWaiting;
    private Config config;
    
    public MonitorPlugin(String url, int intervalMinutes, Config config){
        this.url = url;
        this.interval = Math.max(1,intervalMinutes) * MS_IN_MINUTE;
        this.maxWaiting = Math.max(1,config.maxMinutes) * MS_IN_MINUTE;
        this.config = config;
    }
    
    @Override
    public void init(IServiceProvider sp) {
        new Thread(this).start();
    }
    
    public void run(){
        if(this.url == null){
            Logger.get().error("url is null");
            return;
        }
        while(true){
            try{
                //Thread.sleep(interval);
                Logger.get().info("get app info");
                String response = HttpClientApi.get(url);
                if(response == null || response.length()==0){
                    Logger.get().warn("response is empty");
                    continue;
                }
                List<Application> apps = new Gson().fromJson(response, new TypeToken<List<Application>>() { }.getType());
                if(apps ==null || apps.size() == 0){
                    Logger.get().warn("apps is empty");
                    continue;
                }
                Logger.get().info("total app count " + apps.size());

                int total = 0;
                for(int i=0; i<apps.size(); i++){
                    Application app = apps.get(i);
                    Set<String> activities = app.waitingTime.keySet();
                    boolean exceed = false;
                    StringBuilder sb = new StringBuilder();
                    for(String activity: activities){
                        if( isIgnored(activity) ){
                            continue;
                        }
                        Long waitingTime = app.waitingTime.get(activity);
                        if( isTimeout(activity, waitingTime)){
                            exceed = true;
                            sb.append(String.format("%s takes %s minutes;", activity, waitingTime/1000/60));
                        }
                    }
                    if(exceed){
                        Logger.get().error(String.format("App %s : %s", app.appId, sb.toString()));
                        total ++;
                    }
                }
                if(total >0){
                    Logger.get().error("total exceed app count is "+total);
                } else {
                    Logger.get().info("no app exceed max time");
                }
                Thread.sleep(interval);
            }catch(Exception ex){
                Logger.get().error("MonitorPlugin exception", ex);
            }
        }
    }
    
    private boolean isTimeout(String activity, Long waitingTime){
        Long maxMS = maxWaiting;
        if(config.activities.containsKey(activity)){
            int minutes = config.activities.get(activity);
            maxMS = (minutes * MS_IN_MINUTE);
        }
        return waitingTime >= maxMS;
    }
    
    private boolean isIgnored(String activity){
        if(config.ignored ==null){
            return false;
        }
        return config.ignored.contains(activity);
    }
}
