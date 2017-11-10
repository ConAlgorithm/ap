package scheduler.activities;

import scheduler.Activity;
import scheduler.FinishedHandler;

public class ParallelActivity extends BaseActivity {

  private Activity[] subActivities;
  private int finished = 0;
  private FinishedHandler subActivityFinishedHandler = new FinishedHandler() {
    @Override
    public void execute(String result) {
      if (++finished == subActivities.length) {
        finishedHandler.execute("ParallelActivityResult");
      }
    }
  };

  public ParallelActivity(Activity... subActivities) {
    this.subActivities = subActivities;

    for (Activity activity : subActivities) {
      activity.setFinishedHandler(subActivityFinishedHandler);
    }
  }

  @Override
  public void trigger() {
    for (Activity activity : subActivities) {
      activity.trigger();
    }
  }
}
