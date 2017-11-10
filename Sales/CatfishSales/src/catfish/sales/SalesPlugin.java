package catfish.sales;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.restful.IRESTfulService;
import catfish.sales.restful.BDUser;
import catfish.sales.restful.DUser;
import catfish.sales.restful.POS;

public class SalesPlugin implements IPlugin{

	public void init(IServiceProvider sp){
		IRESTfulService rest = sp.getService(IRESTfulService.class);
        rest.register(new POS(sp));
        rest.register(new DUser(sp));
        rest.register(new BDUser(sp));
	}
}
