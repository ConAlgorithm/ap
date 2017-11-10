package catfish.plugins.sales.rest.handler;

import java.util.ArrayList;
import java.util.List;

import catfish.plugins.sales.dbobject.ProductQuotaObject;
import catfish.plugins.sales.model.ProductQuotaDetailModel;
import catfish.plugins.sales.service.ProductQuotaDataService;

public class ProductQuotaGetAllHandler extends BaseHandler {

  @Override
  public void handle() {
    List<ProductQuotaDetailModel> model = new ArrayList<>();
    List<ProductQuotaObject> all = ProductQuotaDataService.getAll();
    for (ProductQuotaObject item : all) {
      model.add(new ProductQuotaDetailModel(item.ProductId, item.D3Id, item.Quota));
    }
    setSuccessResponse(model);
  }

}
