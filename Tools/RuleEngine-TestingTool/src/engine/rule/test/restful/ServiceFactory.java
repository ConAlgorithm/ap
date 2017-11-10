package engine.rule.test.restful;

import catfish.base.StartupConfig;
import catfish.framework.IServiceFactory;
import catfish.framework.IServiceProvider;
import catfish.framework.IServiceRegister;
import catfish.framework.restful.IRESTfulService;
import catfish.services.restful.RESTfulServiceFactory;

public class ServiceFactory implements IServiceFactory{

	@Override
	public void createServices(IServiceRegister register) {
		register.register(IRESTfulService.class, RESTfulServiceFactory.create(StartupConfig.getSystemProperty()));
	}

	@Override
	public void initServices(IServiceProvider arg0) {
		
	}

}
