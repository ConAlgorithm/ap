package catfish.plugins.finance.handler;

import java.math.BigDecimal;
import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.httpclient.HttpClientApi;
import catfish.plugins.finance.object.ApplicationPosModel;
import catfish.plugins.finance.object.JsonDataBuilder;
import catfish.plugins.finance.object.PenaltyPerDayData;
import catfish.plugins.finance.object.ProductParamModel;
import catfish.plugins.finance.object.Status;

public class PenaltyPerDayHandler implements IHandler {

  private String appId;
  private static final BigDecimal DEFAULT_PENALTY = new BigDecimal(5);
  private static final BigDecimal PDL_PENALTY_FACTOR = new BigDecimal(0.007);
  private static final BigDecimal ERROR_PENALTY = new BigDecimal(-1);

  public PenaltyPerDayHandler(String appId) {
    this.appId = appId;
  }

  @Override
  public String handle() {
    BigDecimal penalty = calculate();
    if (penalty == null) {
      return JsonDataBuilder.build(Status.ERROR, "calculate penalty failed!", null).toString();
    }
    PenaltyPerDayData data = new PenaltyPerDayData();
    data.penaltyPerDay = penalty;
    return JsonDataBuilder.build(Status.SUCCESS, "success", data).toString();
  }

  public BigDecimal getPenaltyPerDay() {
    return calculate();
  }

  private BigDecimal calculate() {
    InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
    if (app == null) {
      Logger.get().error(String.format("App not exists. AppId:%s", appId));
      return null;
    }

    // PDL
    if (app.InstalmentChannel == InstalmentChannel.PayDayLoanApp.getValue()) {
      BigDecimal penalty = app.Principal.multiply(PDL_PENALTY_FACTOR).setScale(2, BigDecimal.ROUND_HALF_UP);
      return penalty.compareTo(DEFAULT_PENALTY) > 0
          ? penalty
          : DEFAULT_PENALTY;
    }

    //POS
    String ApplicationServiceHost = StartupConfig.get("catfish.service.application.host");
    String appURL = ApplicationServiceHost + "/application/" + appId;
    ApplicationPosModel appInfo  = HttpClientApi.getGson(appURL, ApplicationPosModel.class);
    if (appInfo == null) {
      Logger.get().error("call application service failed! uri: " + appURL);
      return DEFAULT_PENALTY;
    }

    String ProductServiceHost = StartupConfig.get("catfish.service.product.host");

    String pURL = String.format("%s/product/%s?type=MobileCredit&merchantStoreId=%s",
        ProductServiceHost, appInfo.productId, appInfo.merchantStoreId);
    ProductParamModel productParamModel = HttpClientApi.getGson(pURL,ProductParamModel.class);
    if (productParamModel == null) {
      Logger.get().error("call product service failed! uri: " + pURL);
     // return DEFAULT_PENALTY;
      return ERROR_PENALTY;
    }
    return productParamModel.penaltyFee;
  }

}
