package scheduler.activities;

import scheduler.DomainConsts;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;

public class CheckAdditionalContactActivity extends BaseEventWaitingActivity {

  private String jobNameWithoutIndex;
  private int delaySeconds;

  public CheckAdditionalContactActivity(String appId, String name, int index, int delay) {
    super(appId, name + index);

    this.jobNameWithoutIndex = name;
    this.delaySeconds = delay;
  }

  @Override
  public void trigger() {
    bookThisActivity();
    PersistenceQueueApi.writeMessager(
        DomainConsts.ACTIVITY_QUEUE_MAPPING.get(jobNameWithoutIndex),
        new QueueMessager(appId, name),
        QueueConfig.QUEUE_PRIORITY_HIGH,
        delaySeconds,MessageSource.JobScheduler_V2);
  }
}
