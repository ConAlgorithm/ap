package catfish.plugins.sales.rest.handler;

import catfish.base.Logger;
import catfish.plugins.sales.model.ProductBriefUsageModel;
import catfish.plugins.sales.model.ProductQuotaErrorCode;
import catfish.plugins.sales.model.ZeroFeeProductAvailabilityModel;
import catfish.plugins.sales.service.ApplicationDataService;
import catfish.plugins.sales.service.DealerUserDataService;
import catfish.plugins.sales.service.ProductQuotaDataService;

public class ProductQuotaZeroFeeProductAvailabilityHandler extends BaseHandler {

  private String productId;
  private String d1Id;

  public ProductQuotaZeroFeeProductAvailabilityHandler(String productId, String d1Id) {
    super();
    this.productId = productId;
    this.d1Id = d1Id;
  }

  @Override
  public void handle() {
    ZeroFeeProductAvailabilityModel result = new ZeroFeeProductAvailabilityModel();

    String d3Id = DealerUserDataService.getD3IdByD1Id(this.d1Id);
    if (d3Id == null) {
      Logger.get().info("Log in ProductQuotaZeroFeeProductAvailabilityHandler---d3Id=null");
      result.availability = false;
      setSuccessResponse(result);
      return;
    }

    int quota = ProductQuotaDataService.getQuotaByProductIdAndD3Id(this.productId, d3Id);
    if (quota < 0) {
      Logger.get().info("Log in ProductQuotaZeroFeeProductAvailabilityHandler---quota<0");
      result.availability = false;
      setSuccessResponse(result);
      return;
    }

    ProductBriefUsageModel model =
        ApplicationDataService.getProductBriefUsageByProductIdAndD3Id(this.productId, d3Id);
    if (model == null) {
      setErrorResponse(
          "call application service failed: get product brief usage.",
          ProductQuotaErrorCode.EXTERNAL_SERVICE_INVOCATION_ERROR);
      return;
    }
    Logger.get().info("Log in ProductQuotaZeroFeeProductAvailabilityHandler---handle"
            +" D1Id = " + this.d1Id + " ProductId = " + this.productId + " D3Id = "+d3Id
            + " quota = " + quota + " and used = " + model.used + " and inuse = " + model.inUse);
    
    result.availability = (quota - model.used - model.inUse > 0);
    setSuccessResponse(result);
    return;
  }

}
