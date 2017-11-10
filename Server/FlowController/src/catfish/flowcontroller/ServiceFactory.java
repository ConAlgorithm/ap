package catfish.flowcontroller;

import catfish.base.StartupConfig;
import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.http.IHTTPService;
import catfish.framework.httprequest.IHttpRequestService;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.queue.IQueueService;
import catfish.services.http.HTTPServiceConfig;
import catfish.services.http.HTTPServiceFactory;
import catfish.services.httprequest.HttpRequestServiceFactory;
import catfish.services.message.MessageServiceFactory;
import catfish.services.queue.QueueServiceFactory;

class ServiceFactory implements IServiceFactory{
    public void createServices(IServiceRegister serviceRegister){
        serviceRegister.register(IQueueService.class, QueueServiceFactory.create());
        serviceRegister.register(IMessageService.class, MessageServiceFactory.create());
        
        HTTPServiceConfig httpConfig = new HTTPServiceConfig();
        httpConfig.host = StartupConfig.get("catfish.flowcontroller.rest.host");
        httpConfig.port = StartupConfig.getAsInt("catfish.flowcontroller.rest.port");
        
        serviceRegister.register(IHTTPService.class, HTTPServiceFactory.create(httpConfig));
        
        serviceRegister.register(IHttpRequestService.class, HttpRequestServiceFactory.create(StartupConfig.getSystemProperty()));
    }
    
    public void initServices(IServiceProvider serviceProvider){
        
    }
}
