package catfish.jobscheduler;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.StartupConfig;

public class Configuration {

  public static final String JOB_CONFIG_FILE = "job.config";

  public static final String JOB_STATUS_QUEUE = "JobStatusQueue";
  public static final String JOB_STATUS_BACKUP_QUEUE = "JobStatusBackupQueue";
  public static final Map<JobType, String> JOB_REQUEST_QUEUE_MAPPING = CollectionUtils.mapOf(
      JobType.AUTOMATIC, "JobRequestQueue",
      JobType.DECISION, "TopRulesDecisionJobRequestQueue");

  private static String consumingQueueSuffix;

  public static void readConfiguration() {
    consumingQueueSuffix = StartupConfig.get("catfish.queue.consumingqueuesuffix");
  }

  public static String getConsumingQueueSuffix() {
    return consumingQueueSuffix;
  }
}
