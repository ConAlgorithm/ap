package catfish.funcontroller.rest;

import catfish.base.Logger;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.http.IHTTPService;


public class RestPlugin implements IPlugin{
    public void init(IServiceProvider sp){
        IHTTPService restService = sp.getService(IHTTPService.class);
        if(restService == null){
            Logger.get().warn("IRESTService is not enabled");
            return;
        }
       
        restService.register("setFundTag", new FundService(sp));

    }
}
