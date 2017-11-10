package scheduler.activities;

import scheduler.DomainConsts;
import catfish.base.persistence.queue.*;
import catfish.base.queue.QueueMessager;

public abstract class InteractiveActivity extends BaseActivity {

  protected String appId;
  protected String name;

  public InteractiveActivity(String appId, String name) {
    this.appId = appId;
    this.name = name;
  }

  protected void sendRequestJob() {
    PersistenceQueueApi.writeMessager(
        DomainConsts.ACTIVITY_QUEUE_MAPPING.get(name), new QueueMessager(appId, name),MessageSource.JobScheduler_V2);
  }
}
