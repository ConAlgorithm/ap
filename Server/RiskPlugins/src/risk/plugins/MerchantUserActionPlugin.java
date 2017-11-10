package risk.plugins;

import risk.services.Warnings;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.restful.IRESTfulService;

public class MerchantUserActionPlugin implements IPlugin {

	@Override
	public void init(IServiceProvider sp) {
        IRESTfulService rest = sp.getService(IRESTfulService.class);
        rest.register(new Warnings());
	}
}
