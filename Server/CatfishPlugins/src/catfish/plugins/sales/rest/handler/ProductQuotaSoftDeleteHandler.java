package catfish.plugins.sales.rest.handler;

import catfish.plugins.sales.model.ProductQuotaBoolModel;
import catfish.plugins.sales.service.ProductQuotaDataService;

public class ProductQuotaSoftDeleteHandler extends BaseHandler {

  private String id;

  public ProductQuotaSoftDeleteHandler(String id) {
    super();
    this.id = id;
  }

  @Override
  public void handle() {
    ProductQuotaBoolModel model = new ProductQuotaBoolModel();
    model.result = ProductQuotaDataService.softDelete(id);
    setSuccessResponse(model);
  }

}
