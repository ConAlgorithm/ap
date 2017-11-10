package engine.main;




import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.restful.IRESTfulService;
import engine.rest.resource.CLCreditCheckRestImpl;



public class RestfulPlugin implements IPlugin {

	@Override
	public void init(IServiceProvider sp) {
		IRESTfulService rest = sp.getService(IRESTfulService.class);
        rest.register(new CLCreditCheckRestImpl());
	}
}
