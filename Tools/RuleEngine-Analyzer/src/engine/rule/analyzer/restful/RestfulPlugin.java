package engine.rule.analyzer.restful;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.restful.IRESTfulService;
import engine.rule.analyzer.restful.filter.CORSResponseFilter;
import engine.rule.analyzer.restful.resource.SalesResource;
import engine.rule.analyzer.restful.resource.impl.ModelResourceImpl;

public class RestfulPlugin implements IPlugin{

	@Override
	public void init(IServiceProvider sp) {
		IRESTfulService rest = sp.getService(IRESTfulService.class);
		rest.register(new ModelResourceImpl());
		rest.addContext("", "index.html", "web");
		rest.register(new SalesResource());
		rest.register(CORSResponseFilter.class);
		rest.register(MultiPartFeature.class);
	}

}
