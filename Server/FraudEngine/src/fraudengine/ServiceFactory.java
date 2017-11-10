package fraudengine;

import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.http.IHTTPService;
import catfish.framework.queue.IQueueService;
import catfish.services.http.HTTPServiceConfig;
import catfish.services.http.HTTPServiceFactory;
import catfish.services.queue.QueueServiceFactory;

public class ServiceFactory implements IServiceFactory{
    @Override
    public void createServices(IServiceRegister serviceRegister){
        serviceRegister.register(IQueueService.class, QueueServiceFactory.create());

        HTTPServiceConfig httpConfig = new HTTPServiceConfig();
        httpConfig.host = Configuration.getHostname();
        httpConfig.port = Configuration.getPort();

        serviceRegister.register(IHTTPService.class, HTTPServiceFactory.create(httpConfig));
    }

    @Override
    public void initServices(IServiceProvider serviceProvider){

    }
}