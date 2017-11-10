package catfish.plugins.sales.rest.handler;

import catfish.plugins.sales.model.ProductQuotaModel;
import catfish.plugins.sales.service.ProductQuotaDataService;

public class ProductQuotaHandler extends BaseHandler {

  private String productId;
  private String d3Id;

  public ProductQuotaHandler(String productId, String d3Id) {
    super();
    this.productId = productId;
    this.d3Id = d3Id;
  }

  @Override
  public void handle() {
    ProductQuotaModel model = new ProductQuotaModel();
    model.quota = ProductQuotaDataService.getQuotaByProductIdAndD3Id(productId, d3Id);
    setSuccessResponse(model);
  }

}
