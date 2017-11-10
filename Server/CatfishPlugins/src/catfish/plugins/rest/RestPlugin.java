package catfish.plugins.rest;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.restful.IRESTfulService;
import catfish.plugins.finance.Repayment;
import catfish.plugins.pdfgenerator.Agreement;

public class RestPlugin implements IPlugin {

	@Override
	public void init(IServiceProvider sp) {
		IRESTfulService rest = sp.getService(IRESTfulService.class);
		// register your rest service here
        rest.register(new Agreement());
        rest.register(new Repayment());
	}
}
