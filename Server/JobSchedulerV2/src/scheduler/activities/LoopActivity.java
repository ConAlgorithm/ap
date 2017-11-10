package scheduler.activities;

import scheduler.Activity;
import scheduler.FinishedHandler;
import scheduler.Predicate;

public class LoopActivity extends BaseActivity {

  private Activity checkActivity;

  public LoopActivity(
      final Activity checkActivity,
      final Predicate predicate,
      final Activity bodyActivity) {
    this.checkActivity = checkActivity;

    checkActivity.setFinishedHandler(new FinishedHandler() {
      @Override
      public void execute(String result) {
        if (predicate.apply(result)) {
          bodyActivity.trigger();
        } else {
          finishedHandler.execute(result);
        }
      }
    });

    bodyActivity.setFinishedHandler(new FinishedHandler() {
      @Override
      public void execute(String result) {
        checkActivity.trigger();
      }
    });
  }

  @Override
  public void trigger() {
    checkActivity.trigger();
  }
}
