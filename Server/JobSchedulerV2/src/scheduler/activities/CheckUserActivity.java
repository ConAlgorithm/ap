package scheduler.activities;

public class CheckUserActivity extends BaseEventWaitingActivity {

  public CheckUserActivity(String appId, String name) {
    super(appId, name);
  }

  @Override
  public void trigger() {
    bookThisActivity();
    sendRequestJob();
  }
}
