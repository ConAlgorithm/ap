package scheduler;

import catfish.base.StartupConfig;

public class Configuration {

  private static String consumingQueueSuffix;

  public static void readConfiguration() {
    consumingQueueSuffix = StartupConfig.get("catfish.queue.consumingqueuesuffix");
  }

  public static String getConsumingQueueSuffix() {
    return consumingQueueSuffix;
  }

  public static boolean readIsD1CheckNessesaryConfiguration() {
	  return StartupConfig.getAsBoolean("IsD1CheckNessesary");
  }

}
