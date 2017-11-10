package catfish.jobscheduler;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;

public class Scheduler {

  private static Map<String, AppJobController> appJobMapping = new HashMap<>();

  public static void run() {
    while (true) {
      try {
        QueueMessager messager = PersistenceQueueApi.consumeMessager(
            Configuration.JOB_STATUS_QUEUE + Configuration.getConsumingQueueSuffix(),
            Configuration.JOB_STATUS_BACKUP_QUEUE,
            MessageSource.JobScheduler);
        getOrCreateController(messager.getAppId()).addJobStatusAndTryTriggering(
            messager.getJobName(), messager.getJobStatus());
      } catch (Exception e) {
        Logger.get().fatal("Unexpected exception occurred.", e);
      }
    }
  }

  public static void untrackApp(String appId) {
    appJobMapping.remove(appId);
    Logger.get().info("Stop tracking application " + appId);
  }

  private static AppJobController getOrCreateController(String appId) {
    if (!appJobMapping.containsKey(appId)) {
      Logger.get().info("Start tracking application " + appId);
      appJobMapping.put(appId, new AppJobController(appId));
    }
    return appJobMapping.get(appId);
  }
}
