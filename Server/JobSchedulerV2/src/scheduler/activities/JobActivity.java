package scheduler.activities;

import scheduler.DomainConsts;
import catfish.base.queue.QueueMessager;
import catfish.base.persistence.queue.*;

public class JobActivity extends BaseEventWaitingActivity {

  protected String message;

  public JobActivity(String appId, String name) {
    super(appId, name);
  }

  public JobActivity(String appId, String name, String message) {
    this(appId, name);
    this.message = message;
  }

  @Override
  public void trigger() {
    bookThisActivity();
    sendRequestJob();
  }

  @Override
  protected void sendRequestJob() {
    PersistenceQueueApi.writeMessager(
        DomainConsts.ACTIVITY_QUEUE_MAPPING.get(name), new QueueMessager(appId, name, message),MessageSource.JobScheduler_V2);
  }
}
