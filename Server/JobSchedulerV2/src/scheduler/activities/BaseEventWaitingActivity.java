package scheduler.activities;

import scheduler.EventWaiter;
import scheduler.EventWaiterRepository;

public abstract class BaseEventWaitingActivity extends InteractiveActivity implements EventWaiter {

  public BaseEventWaitingActivity(String appId, String name) {
    super(appId, name);
  }

  @Override
  public void receive(String result) {
    finishedHandler.execute(result);
  }

  protected void bookThisActivity() {
    EventWaiterRepository.book(appId, name, this);
  }
}
