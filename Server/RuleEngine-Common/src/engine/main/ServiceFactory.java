package engine.main;

import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.http.IHTTPService;
import catfish.framework.queue.IQueueService;
import catfish.services.http.HTTPServiceConfig;
import catfish.services.http.HTTPServiceFactory;
import catfish.services.queue.QueueServiceFactory;
//import engine.rule.coordinate.Storage;

public class ServiceFactory implements IServiceFactory{

	@Override
	public void createServices(IServiceRegister serviceRegister) {
		
		serviceRegister.register(IQueueService.class, QueueServiceFactory.create());




        //RESTfulServiceConfig restConfig = new RESTfulServiceConfig();
		//restConfig.host = Configuration.getHttpHost();
        //restConfig.port = StartupConfig.getAsInt("ruleengine.restful.port");
        //serviceRegister.register(IRESTfulService.class, RESTfulServiceFactory.create(restConfig));	
		HTTPServiceConfig config = new HTTPServiceConfig();
		config.host = Configuration.getHttpHost();
		config.port = Configuration.getHttpPort();		
		serviceRegister.register(IHTTPService.class, HTTPServiceFactory.create(config));
		
//		serviceRegister.register(Storage.class, new Storage());
	}

	@Override
	public void initServices(IServiceProvider serviceProvider) {
		
	}

}
