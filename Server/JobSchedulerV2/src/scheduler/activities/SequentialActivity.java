package scheduler.activities;

import scheduler.Activity;
import scheduler.FinishedHandler;

public class SequentialActivity extends BaseActivity {

  private Activity firstActivity;

  public SequentialActivity(final Activity... subActivities) {
    firstActivity = subActivities[0];

    for (int i = 0; i < subActivities.length - 1; i++) {
      final Activity nextActivity = subActivities[i + 1];
      subActivities[i].setFinishedHandler(new FinishedHandler() {
        @Override
        public void execute(String result) {
          nextActivity.trigger();
        }
      });
    }
    subActivities[subActivities.length - 1].setFinishedHandler(lazyFinishedHandler);
  }

  @Override
  public void trigger() {
    firstActivity.trigger();
  }
}
