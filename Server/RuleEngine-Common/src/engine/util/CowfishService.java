package engine.util;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import engine.main.Configuration;
import engine.rule.model.IntModel;

public class CowfishService {

	private static final String CowfishService_URL = Configuration.getCowfishServiceUrl();

	public static Integer getUserCreditRating(String userId) {

		String url = CowfishService_URL + "user/" + userId + "/credit";
		try {
			Integer creditRating = HttpClientApi.getGson(url, IntModel.class).getVal();
			Logger.get().info("Get user credit rating (信用等级) from CowfishService ! rating = " + creditRating);
			return creditRating;
		} catch (Exception e) {
			Logger.get().error("Http request exception ! url = " + url, e);
		}

		return null;
	}

	public static Integer getAppSuccessTimes(String userId) {

		String url = CowfishService_URL + "application/count?userId=" + userId + "&status=100&status=200&status=500&status=600";
		try {
			Integer times = HttpClientApi.getGson(url, IntModel.class).getVal();
			Logger.get().info("Get user successful app times (成功申请次数) from CowfishService ! times = " + times);
			return times;
		} catch (Exception e) {
			Logger.get().error("Http request exception ! url = " + url, e);
		}

		return null;
	}
}
