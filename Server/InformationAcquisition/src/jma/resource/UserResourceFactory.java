package jma.resource;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import net.sf.json.JSONObject;

public class UserResourceFactory {

  private static String grasscarpHost = StartupConfig.get("catfish.grasscarp.host");

    public static User getUser(String userId) {
    return HttpClientApi.getGson(String.format(
        "%s/user/%s", grasscarpHost, userId), User.class);
  }

  public static Account getUserAccount(String userId) {
    return HttpClientApi.getGson(String.format(
        "%s/account?type=userId&value=%s", grasscarpHost, userId), Account.class);
  }

    public static String getLatestPosAppId(String userId) {
        String grasscarpValidPosIdUrl = grasscarpHost + "/application/valid-posId?userId=" + userId;
        try {
        	String latestPosId = JSONObject.fromObject(HttpClientApi.get(grasscarpValidPosIdUrl))
        			.getString("id");
        	Logger.get().info("get valid-PosId:" + latestPosId + ",url:" + grasscarpValidPosIdUrl);
        	return latestPosId;
		} catch (Exception e) {
			Logger.get().warn("获取pos上次申请appId失败,可能分期信息不存在!");
		}
        return null;
    }

}
