package scheduler.activities;

import scheduler.Activity;
import scheduler.FollowingActivityCreator;
import scheduler.FinishedHandler;

public class DynamicActivity extends BaseActivity {

  private Activity baseActivity;

  public DynamicActivity(
      Activity baseActivity, final FollowingActivityCreator followingActivityCreator) {
    this.baseActivity = baseActivity;

    baseActivity.setFinishedHandler(new FinishedHandler() {
      @Override
      public void execute(String result) {
        Activity followingActivity = followingActivityCreator.create(result);
        followingActivity.setFinishedHandler(finishedHandler);
        followingActivity.trigger();
      }
    });
  }

  @Override
  public void trigger() {
    baseActivity.trigger();
  }
}
