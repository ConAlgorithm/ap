package scheduler.predicates;

import scheduler.Predicate;

public class AlwaysTruePredicate implements Predicate {
  @Override
  public boolean apply(String result) {
    return true;
  }
}
