package catfish.flowcontroller.rest;

import java.util.*;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;
import catfish.flowcontroller.WorkFlow;
import catfish.flowcontroller.DefaultWorkflowProvider;
import catfish.flowcontroller.activities.ActivityDescriptorManager;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.storage.Storage;
import catfish.framework.IListener;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

class StatusService implements IListener<HttpData>{
    private static final String POST="post";
    private static final String GET="get";
    private static final String emptyJson = "{}";
    private IServiceProvider serviceProvider;
    private List<String> appFields = new ArrayList<String>();
    
    public StatusService(IServiceProvider serviceProvider){
        this.serviceProvider = serviceProvider;
        
        appFields.add("appId");
        appFields.add("workflow");
        appFields.add("finishedActivities");
        appFields.add("states");
        appFields.add("messages");
    }
    
    public void onMessage(String representation, HttpData data) {
        String method = data.getMethod().toLowerCase();
        if(POST.equals(method)){
            String requestData = data.getRequestData();
            data.setResponseData(getApplications(requestData));
        } else if(GET.equals(method)){
            String[] paths = data.getPaths();
            if(paths != null && paths.length>1){
                String appId= paths[1];
                Storage storage= this.serviceProvider.getService(Storage.class);
                Application app = storage.load(appId);
                Map<String, String> statusMap = getStatus(app);
                String jsonString = new Gson().toJson(statusMap);
                data.setResponseData(jsonString);
            } else {
                data.setResponseData(emptyJson);
            }
        } else {
            Logger.get().warn("[StatusService] Not supported method in status" + method);
            data.setResponseData(emptyJson);
        }
    }
    
    private String getApplications(String idsJson){
        
        if(idsJson == null || idsJson.length() ==0){
            return emptyJson;
        }
        
        try {
            List<String> ids = new Gson().fromJson(idsJson, new TypeToken<List<String>>() { }.getType());
            if(ids == null || ids.size() ==0){
                return emptyJson;
            }
            Map<String, Map<String, String>> appStatus = new HashMap<String, Map<String, String>>();
            Storage storage= this.serviceProvider.getService(Storage.class);
            List<Application> apps = storage.load(ids, appFields);
            if(apps == null || apps.size() ==0){
                Logger.get().warn("AppId is not empty, but app status is empty.");
                return emptyJson;
            }
            for(Application app: apps){
                appStatus.put(app.appId, getStatus(app));
            }
            return new Gson().toJson(appStatus);
        } catch (JsonSyntaxException e) {
            Logger.get().error("[StatusService] Can't parse message:" + idsJson, e);
            return emptyJson;
        }
    }
    
    private Map<String, String> getStatus(Application app){
        Map<String, String> result = new HashMap<String, String>();
        if(app == null){
            Logger.get().warn("[StatusService] Application is null");
            return result;
        }

        if(app.finishedActivities.containsKey("CheckD1FeedbackPass")){
        	result.put("hasD1Checked", "1");
        }else{
        	result.put("hasD1Checked", "0");
        }
        String description = checkAppStatus(app.appId);
        if (StringUtils.isNullOrWhiteSpaces(description)) {
            result.put("description", ActivityDescriptorManager.instance.getDescription(app));
        } else {
            result.put("description", description);
        }
        WorkFlow wf = DefaultWorkflowProvider.instance.get(app.workflow);
        
        if(wf == null){
            Logger.get().error("[StatusService] Can't get workflow:" + app.workflow);
            return result;
        }
        result.put("progress", String.valueOf(wf.getProgress(app)));
        return result;
    }

    private String checkAppStatus(final String appId) {
        String appServiceHost = StartupConfig.get("catfish.service.application.host");
        Objects.requireNonNull(appServiceHost, "null appService host");
        final String appServiceUrl = appServiceHost + "/application/"+appId;
        Integer appStatus = HttpClientApi.CallService(3, new ServiceHandler<Map<String, String>, Integer>() {
            @Override
            public String createUrl() {
                return appServiceUrl + appId;
            }

            @Override
            public Map<String, String> OnRequest(String url) {
                String response = HttpClientApi.get(url);
                Map<String, String> appInfo = new Gson().fromJson(response, new TypeToken<HashMap<String, String>>() {
                }.getType());
                return appInfo;
            }

            @Override
            public Integer OnSuccess(Map<String, String> result) {
                Integer status = Integer.valueOf(result.get("status"));
                return status;
            }

            @Override
            public Integer OnError(Map<String, String> result) {
                Logger.get().warn("Call AppService error, url = " + appServiceUrl);
                return null;
            }

        });

        if (appStatus == null) {
            return "";
        }

        if (ApplicationStatus.Canceled.getValue() == appStatus) {
            return "已取消";
        } else if (ApplicationStatus.Rejected.getValue() == appStatus) {
            return "已拒绝";
        }

        return "";
    }
    
}
