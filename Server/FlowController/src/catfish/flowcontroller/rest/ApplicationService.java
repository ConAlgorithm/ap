package catfish.flowcontroller.rest;

import catfish.base.Logger;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.storage.Storage;
import catfish.framework.IListener;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;

import com.google.gson.Gson;

class ApplicationService implements IListener<HttpData>{
    private IServiceProvider serviceProvider;
    
    public ApplicationService(IServiceProvider serviceProvider){
        this.serviceProvider = serviceProvider;
    }
    
    public void onMessage(String representation, HttpData data) {
        String[] paths = data.getPaths();
        if(paths != null && paths.length>1){
            String appId= paths[1];
            if(appId == null || appId.length() ==0){
                Logger.get().warn("[ApplicationService] appId is empty");
                data.setResponseData("");
                return;
            }

            Storage storage= this.serviceProvider.getService(Storage.class);
            Application app = storage.load(appId);
            if(app == null){
                Logger.get().warn("[ApplicationService] can not get application from storage for "+ appId);
                data.setResponseData("");
            } else {
                data.setResponseData(new Gson().toJson(app));
            }
        } else {
            Logger.get().warn("[ApplicationService] invalid request.");
            data.setResponseData("");
        }
    }
}
