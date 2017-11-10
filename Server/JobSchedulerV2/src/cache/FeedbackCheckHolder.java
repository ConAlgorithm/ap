package cache;

import java.util.Map;


import catfish.base.CollectionUtils;



public class FeedbackCheckHolder {

	private static Map<String, String> statusMap = CollectionUtils.<String, String> newMapBuilder().build();

	public static void addApplicationStatus(String appId, String status) {
		//ignore other status

			statusMap.put(appId, status);

	}

	public static String getApplicationStatus(String appId) {
		return statusMap.get(appId);
	}
}
