package catfish.flowcontroller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import catfish.flowcontroller.activities.ActivityDescriptorManager;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.storage.Storage;
import catfish.framework.IListener;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;

class OverviewService implements IListener<HttpData>{
    private IServiceProvider serviceProvider;
    private List<String> appFields = new ArrayList<String>();
    
    OverviewService(IServiceProvider serviceProvider){
        this.serviceProvider = serviceProvider;
        
        appFields.add("appId");
        appFields.add("startTime");
        appFields.add("states");
        appFields.add("messages");
    }
    
    public void onMessage(String representation, HttpData data) {
        String[] paths = data.getPaths();
        if(paths != null && paths.length>1){
            String workflowName= paths[1];
            data.setResponseData(this.getWorkflowOverview(workflowName));
        } else {
            data.setResponseData("");
        }
    }
    
    private String getWorkflowOverview(String workflowName){
        if( workflowName==null ){
            return "";
        }
        Storage storage= this.serviceProvider.getService(Storage.class);
        List<Application> apps = storage.queryPendingApps(workflowName, appFields);
        if(apps ==null ){
            return "";
        }
        List<Map<String,?>> result = new ArrayList<Map<String,?>>();
        for(Application app: apps){
            Map<String, Object> appInfo =new HashMap<String, Object>();
            appInfo.put("appId", app.appId);
            appInfo.put("startTime", app.startTime);
            appInfo.put("states", app.getState());
            appInfo.put("waitingTime", ActivityDescriptorManager.instance.getWaitingTimes(app));
            
            result.add(appInfo);
        }
        return new Gson().toJson(result);
    }
}
