package scheduler.predicates;

import scheduler.Predicate;

public class ResultMatchPredicate implements Predicate {

  private String expected;

  public ResultMatchPredicate(String expected) {
    this.expected = expected;
  }

  @Override
  public boolean apply(String result) {
    return result.equals(expected);
  }
}
