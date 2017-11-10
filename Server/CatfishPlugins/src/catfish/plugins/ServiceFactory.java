package catfish.plugins;

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
import catfish.services.restful.RESTfulServiceConfig;
import catfish.services.restful.RESTfulServiceFactory;

public class ServiceFactory implements IServiceFactory{
    @Override
    public void createServices(IServiceRegister serviceRegister){
        serviceRegister.register(IQueueService.class, QueueServiceFactory.create());
        serviceRegister.register(IMessageService.class, MessageServiceFactory.create());

        HTTPServiceConfig httpConfig = new HTTPServiceConfig();
        httpConfig.host = StartupConfig.get("catfish.bonuspoints.rest.host");
        httpConfig.port = StartupConfig.getAsInt("catfish.bonuspoints.rest.port");

        RESTfulServiceConfig restConfig = new RESTfulServiceConfig();
        restConfig.host = StartupConfig.get("catfish.bonuspoints.rest.host");
        restConfig.port = StartupConfig.getAsInt("catfish.plugin.rest.port");

        serviceRegister.register(IHTTPService.class, HTTPServiceFactory.create(httpConfig));
        serviceRegister.register(IRESTfulService.class, RESTfulServiceFactory.create(restConfig));
    }

    @Override
    public void initServices(IServiceProvider serviceProvider){

    }
}
