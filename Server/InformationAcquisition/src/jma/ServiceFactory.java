package jma;

import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.http.IHTTPService;
import catfish.framework.queue.IQueueService;
import catfish.framework.storage.IStorageService;
import catfish.services.http.HTTPServiceConfig;
import catfish.services.http.HTTPServiceFactory;
import catfish.services.queue.QueueServiceFactory;
import catfish.services.storage.StorageServiceFactory;

public class ServiceFactory implements IServiceFactory{
	private static IServiceProvider provider = null;
	
    @Override
    public void createServices(IServiceRegister serviceRegister){
        serviceRegister.register(IQueueService.class, QueueServiceFactory.create());

        HTTPServiceConfig httpConfig = new HTTPServiceConfig();
        httpConfig.host = Configuration.getHostname();
        httpConfig.port = Configuration.getPort();

        serviceRegister.register(IHTTPService.class, HTTPServiceFactory.create(httpConfig));
        serviceRegister.register(IStorageService.class, StorageServiceFactory.create());
    }

    @Override
    public void initServices(IServiceProvider serviceProvider){
    	provider = serviceProvider;
    }
    
    public static IServiceProvider getServiceProvider(){
    	return provider;
    }
}