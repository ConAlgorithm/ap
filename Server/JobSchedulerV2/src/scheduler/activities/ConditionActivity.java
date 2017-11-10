package scheduler.activities;

import java.util.LinkedHashMap;
import java.util.Map;

import scheduler.Activity;
import scheduler.FinishedHandler;
import scheduler.Predicate;

public class ConditionActivity extends BaseActivity {

  private Activity checkActivity;

  public ConditionActivity(
      Activity checkActivity,
      final LinkedHashMap<Predicate, Activity> bodyActivities,
      final Activity defaultActivity) {
    this.checkActivity = checkActivity;

    checkActivity.setFinishedHandler(new FinishedHandler() {
      @Override
      public void execute(String result) {
        Activity winActivity = defaultActivity;
        for (Map.Entry<Predicate, Activity> entry : bodyActivities.entrySet()) {
          if (entry.getKey().apply(result)) {
            winActivity = entry.getValue();
            break;
          }
        }

        winActivity.setFinishedHandler(finishedHandler);
        winActivity.trigger();
      }
    });
  }

  @Override
  public void trigger() {
    checkActivity.trigger();
  }
}
