package catfish.plugins.sales.model;

import catfish.plugins.sales.rest.handler.BaseHandler;
import catfish.plugins.sales.service.ProductQuotaDataService;

public class ProductQuotaAddHandler extends BaseHandler {

  private ProductQuotaDetailModel model;

  public ProductQuotaAddHandler(ProductQuotaDetailModel model) {
    super();
    this.model = model;
  }

  @Override
  public void handle() {
    ProductQuotaBoolModel result = new ProductQuotaBoolModel();
    if (ProductQuotaDataService.exists(model.productId, model.d3Id)) {
      result.result = false;
      setSuccessResponse(result);
      return;
    }

    result.result = ProductQuotaDataService.add(model.productId, model.d3Id, model.quota);
    setSuccessResponse(result);
  }

}
