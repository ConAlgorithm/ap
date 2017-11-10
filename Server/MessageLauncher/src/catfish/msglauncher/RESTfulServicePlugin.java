package catfish.msglauncher;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.restful.IRESTfulService;
import catfish.msglauncher.service.MessageTemplateServiceImpl;

//import catfish.information.logger.service.InfoLogger;

public class RESTfulServicePlugin implements IPlugin{
	public void init(IServiceProvider sp){
		
		IRESTfulService rest = sp.getService(IRESTfulService.class);
        rest.register(new MessageTemplateServiceImpl());
	}

}
