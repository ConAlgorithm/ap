package scheduler.activities;

public class EventWaitingActivity extends BaseEventWaitingActivity {

  public EventWaitingActivity(String appId, String name) {
    super(appId, name);
  }

  @Override
  public void trigger() {
    bookThisActivity();
  }
}
