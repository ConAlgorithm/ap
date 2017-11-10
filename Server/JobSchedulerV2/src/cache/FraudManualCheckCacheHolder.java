package cache;

import java.util.concurrent.TimeUnit;

import catfish.base.business.common.JobStatus;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class FraudManualCheckCacheHolder {
	//the entry will be purged in 1 hour
	private static int TIMEOUT_SECONDS = 3600;
	private static Cache<String, String> statusMap = CacheBuilder.newBuilder()
			.expireAfterWrite(TIMEOUT_SECONDS, TimeUnit.SECONDS).build();

	public static void addApplicationStatus(String appId, String status) {
		//ignore other status
		if(JobStatus.Paused.equals(status) || JobStatus.Approved.equals(status)){
			statusMap.put(appId, status);
		}
	}

	public static String getApplicationStatus(String appId) {
		return statusMap.getIfPresent(appId);
	}

	public static void removeApplication(String appId) {
		statusMap.invalidate(appId);
	}

}
