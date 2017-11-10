package scheduler.activities;

import scheduler.DomainConsts;
import catfish.base.persistence.queue.*;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;

public class DelayJobActivity extends JobActivity {
  private int delaySeconds;

  public DelayJobActivity(String appId, String name, int delay) {
    super(appId, name);
    this.delaySeconds = delay;
  }

  @Override
  protected void sendRequestJob() {
    PersistenceQueueApi.writeMessager(
        DomainConsts.ACTIVITY_QUEUE_MAPPING.get(name),
        new QueueMessager(appId, name),
        QueueConfig.QUEUE_PRIORITY_NORMAL,
        delaySeconds,MessageSource.JobScheduler_V2);
  }
}
