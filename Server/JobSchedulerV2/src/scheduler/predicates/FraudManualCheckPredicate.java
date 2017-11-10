package scheduler.predicates;

import cache.FraudManualCheckCacheHolder;
import scheduler.Predicate;

public class FraudManualCheckPredicate implements Predicate{

	private String appId;
	private String status;

	public FraudManualCheckPredicate(String appId, String status) {
		this.appId = appId;
		this.status = status;
	}

	@Override
	public boolean apply(String result) {
		return status.equalsIgnoreCase(FraudManualCheckCacheHolder.getApplicationStatus(appId));
	}

}
