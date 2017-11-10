package catfish.plugins.sales.rest.handler;

import java.util.List;

import catfish.plugins.sales.model.ProductQuotaAndD3Model;
import catfish.plugins.sales.service.ProductQuotaDataService;

public class ProductQuotaGetAllDetailHandler extends BaseHandler {

  @Override
  public void handle() {
    List<ProductQuotaAndD3Model> model = ProductQuotaDataService.getAllDetail();
    setSuccessResponse(model);
  }

}
