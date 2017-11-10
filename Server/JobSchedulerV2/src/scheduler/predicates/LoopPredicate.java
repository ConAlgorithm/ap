package scheduler.predicates;


public class LoopPredicate extends ResultMatchPredicate {
  private int maxCount;

  public LoopPredicate(String expected, int maxCount) {
    super(expected);
    this.maxCount = maxCount;
  }

  @Override
  public boolean apply(String result) {
    return --maxCount > 0 && super.apply(result);
  }
}
