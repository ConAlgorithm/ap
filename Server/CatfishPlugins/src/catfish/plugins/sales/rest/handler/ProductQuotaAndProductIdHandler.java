package catfish.plugins.sales.rest.handler;

import java.util.ArrayList;
import java.util.List;
import catfish.plugins.sales.dbobject.ProductQuotaObject;
import catfish.plugins.sales.model.ProductIdAndQuotaModel;
import catfish.plugins.sales.service.ProductQuotaDataService;

public class ProductQuotaAndProductIdHandler extends BaseHandler {

  private String d3Id;

  public ProductQuotaAndProductIdHandler(String d3Id) {
    super();
    this.d3Id = d3Id;
  }

  @Override
  public void handle() {
    List<ProductIdAndQuotaModel> model = new ArrayList<>();
    List<ProductQuotaObject> data = ProductQuotaDataService.getQuotaByD3Id(d3Id);
    if (data != null) {
      for (ProductQuotaObject d : data) {
        model.add(new ProductIdAndQuotaModel(d.ProductId, d.Quota));
      }
    }
    setSuccessResponse(model);
  }

}
