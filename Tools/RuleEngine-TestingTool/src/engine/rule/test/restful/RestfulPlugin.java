package engine.rule.test.restful;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.restful.IRESTfulService;
import engine.rule.test.restful.resource.impl.TestingResourceImpl;

public class RestfulPlugin implements IPlugin{

	@Override
	public void init(IServiceProvider sp) {
		IRESTfulService rest = sp.getService(IRESTfulService.class);
		rest.register(new TestingResourceImpl());
		//rest.addContext("/demo", "index.html", "web/ext-5.0.0.736/build");
		rest.addContext("", "index.html", "web");
	}
}
