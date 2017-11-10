package catfish.flowcontroller.rest;

import catfish.base.Logger;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.http.IHTTPService;;

public class RestPlugin implements IPlugin{
    public void init(IServiceProvider sp){
        IHTTPService restService = sp.getService(IHTTPService.class);
        if(restService == null){
            Logger.get().warn("IRESTService is not enabled");
            return;
        }
        
        restService.register("status", new StatusService(sp));
        restService.register("application", new ApplicationService(sp));
        restService.register("overview", new OverviewService(sp));
        restService.register("workflow", new WorkFlowService());
        restService.register("flowui", new FlowUIService());
        restService.register("health", new HatlthService());
        
        restService.addContext("/html", "index.html", "flowinspector");
        restService.addContext("/config", "index.html", "config");
    }
}
