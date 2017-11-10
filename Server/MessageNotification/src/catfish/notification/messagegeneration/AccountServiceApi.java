package catfish.notification.messagegeneration;

import grasscarp.account.model.MobileHistory;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;

import catfish.base.JsonParseUtil;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.notification.Configuration;

public class AccountServiceApi {

  public static List<MobileHistory> getUserMobiles(String userId) {
    List<MobileHistory> response = new ArrayList<MobileHistory>();

    try {
      URIBuilder builder = new URIBuilder().setPath(Configuration.getGrasscarpUrlPrefix()
        + String.format("/account/%s/mobile", userId));

      String literal = HttpClientApi.get(builder.toString());
      Logger.get().info("Call " + builder.toString());

      response = JsonParseUtil.parseToCollection(literal, ArrayList.class, MobileHistory.class);

    } catch (Exception e) {
      Logger.get().warn("Error happens: " + userId, e);
    }

    return response;
  }
}
