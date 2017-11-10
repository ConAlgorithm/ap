package scheduler.activities;

import scheduler.DomainConsts;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;

public class NotificationActivity extends InteractiveActivity {

  public NotificationActivity(String appId, String name) {
    super(appId, name);
  }

  @Override
  protected void sendRequestJob() {
	    PersistenceQueueApi.writeMessager(
	        DomainConsts.ACTIVITY_QUEUE_MAPPING.get(name), new QueueMessager(appId, name),MessageSource.JobScheduler_V2,"Notification");
	  }

  @Override
  public void trigger() {
    sendRequestJob();
    finishedHandler.execute("NotificationSent");
  }
}
