package catfish.fundcontroller;

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
import catfish.services.restful.RESTfulServiceFactory;

public class ServiceFactory implements IServiceFactory{
    public void createServices(IServiceRegister serviceRegister){
        serviceRegister.register(IQueueService.class, QueueServiceFactory.create());
        serviceRegister.register(IMessageService.class, MessageServiceFactory.create());
        
        HTTPServiceConfig httpConfig = new HTTPServiceConfig();
        httpConfig.host = StartupConfig.get("catfish.p2p.rest.host");
        httpConfig.port = StartupConfig.getAsInt("catfish.p2p.rest.port");
        
        serviceRegister.register(IHTTPService.class, HTTPServiceFactory.create(httpConfig));
    }
    
    public void initServices(IServiceProvider serviceProvider){
        
    }
}
