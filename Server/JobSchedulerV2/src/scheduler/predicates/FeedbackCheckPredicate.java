package scheduler.predicates;

import cache.FeedbackCheckHolder;
import scheduler.Predicate;

public class FeedbackCheckPredicate implements Predicate{

	private String appId;
	private String status;

	public FeedbackCheckPredicate(String appId, String status) {
		this.appId = appId;
		this.status = status;
	}

	@Override
	public boolean apply(String result) {
		return status.equalsIgnoreCase(FeedbackCheckHolder.getApplicationStatus(appId));
	}

}
