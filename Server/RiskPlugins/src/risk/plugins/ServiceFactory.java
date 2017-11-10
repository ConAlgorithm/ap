package risk.plugins;

import catfish.base.StartupConfig;
import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.httprequest.IHttpRequestService;
import catfish.framework.queue.IQueueService;
import catfish.framework.restful.IRESTfulService;
import catfish.services.http.HTTPServiceFactory;
import catfish.services.queue.QueueServiceFactory;
import catfish.services.restful.RESTfulServiceFactory;

class ServiceFactory implements IServiceFactory{

	@Override
  public void createServices(IServiceRegister serviceRegister){
    serviceRegister.register(IQueueService.class, QueueServiceFactory.create());
		serviceRegister.register(IRESTfulService.class, RESTfulServiceFactory.create(StartupConfig.getSystemProperty()));
		//serviceRegister.register(IHttpRequestService.class, HTTPServiceFactory.create(StartupConfig.getSystemProperty()));
	}

	@Override
  public void initServices(IServiceProvider serviceProvider){

	}
}
