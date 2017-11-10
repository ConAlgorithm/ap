package catfish.plugins.sales;

import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.restful.IRESTfulService;
import catfish.plugins.sales.rest.ProductQuota;

public class SalesPlugin implements IPlugin {

  @Override
  public void init(IServiceProvider sp) {
    IRESTfulService restService = sp.getService(IRESTfulService.class);
    restService.register(new ProductQuota());
  }

}
