package scheduler.predicates;

import cache.FeedbackPassHolder;
import scheduler.Configuration;
import scheduler.Predicate;

public class FeedbackPassPredicate implements Predicate{

  private String appId;
  private String status;

  public FeedbackPassPredicate(String appId, String status) {
    this.appId = appId;
    this.status = status;
  }

  @Override
  public boolean apply(String result) {

    if (!Configuration.readIsD1CheckNessesaryConfiguration()) return true;
    return status.equalsIgnoreCase(FeedbackPassHolder.getApplicationStatus(appId));
  }

}
