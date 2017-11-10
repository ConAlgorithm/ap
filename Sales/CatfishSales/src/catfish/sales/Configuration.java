package catfish.sales;

import catfish.base.StartupConfig;

public class Configuration {

	private static String AppStatusRestUrl=StartupConfig.get("catfish.app.status.rest.url","http://localhost:9101/status");

	public static String getAppStatusRestUrl() {
		return AppStatusRestUrl;
	}

	public static void setAppStatusRestUrl(String appStatusRestUrl) {
		AppStatusRestUrl = appStatusRestUrl;
	}
}