package jma.resource;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import catfish.cowfish.application.model.ApplicationModel;
import net.sf.json.JSONObject;

public class CLApplicationResourceFactory {

  private static String cowfishHost = StartupConfig.get("catfish.cowfish.host");

    public static ApplicationModel getApplication(String appId) {
    return HttpClientApi.getGson(
        String.format("%s/application/%s", cowfishHost, appId), ApplicationModel.class);
  }

    public static String getUserId(String appId) {
        String appInfoUrl = cowfishHost + "/application/" + appId;

        String userId = JSONObject.fromObject(HttpClientApi.get(appInfoUrl)).getString("userId");
        Logger.get().info("get userId:" + userId + ",url:" + appInfoUrl);

        return userId;
    }

}
