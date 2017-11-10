package catfish.plugins.sales.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.plugins.sales.model.ApplicationPOSModel;
import catfish.plugins.sales.model.ProductBriefUsageModel;

public class ApplicationDataService {

  private static String APPLICATION_SERVICE_HOST = StartupConfig.get("catfish.service.application.host");

  public static String getProductIdByAppId(String appId) {
    String uri = String.format("%s/application/%s", APPLICATION_SERVICE_HOST, appId);
    ApplicationPOSModel model = getResponse(uri, ApplicationPOSModel.class);
    if (model == null) {
      return null;
    }
    return model.productId;
  }

  public static ProductBriefUsageModel getProductBriefUsageByProductIdAndD3Id(
      String productId, String d3Id) {
    String uri = String.format("%s/application/productCount?productId=%s&d3Id=%s",
        APPLICATION_SERVICE_HOST, productId, d3Id);
    return getResponse(uri, ProductBriefUsageModel.class);
  }

  private static <TModel> TModel getResponse(String uri, Class<TModel> clazz) {
    String response = HttpClientApi.get(uri);
    if (response == null) {
      return null;
    }

    TModel model;
    try {
      model = new Gson().fromJson(response, clazz);
    }
    catch (JsonSyntaxException ex) {
      Logger.get().error("parsing response error! response: " + response, ex);
      return null;
    }

    return model;
  }

}
