package catfish.flowcontroller.test;

import catfish.base.StartupConfig;
import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.http.IHTTPService;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.queue.IQueueService;
import catfish.framework.restful.IRESTfulService;
import catfish.services.http.HTTPServiceConfig;
import catfish.services.http.HTTPServiceFactory;
import catfish.services.message.MessageServiceFactory;
import catfish.services.queue.QueueServiceFactory;
import catfish.services.queue.test.QueueService;
import catfish.services.restful.RESTfulServiceFactory;

class ServiceFactory implements IServiceFactory{
    public void createServices(IServiceRegister serviceRegister){
        HTTPServiceConfig httpConfig = new HTTPServiceConfig();
        httpConfig.host = StartupConfig.get("catfish.flowcontroller.rest.host");
        httpConfig.port = StartupConfig.getAsInt("catfish.flowcontroller.rest.port");
        
        serviceRegister.register(IHTTPService.class, HTTPServiceFactory.create(httpConfig));
    }
    
    public void initServices(IServiceProvider serviceProvider){
        
    }
}
