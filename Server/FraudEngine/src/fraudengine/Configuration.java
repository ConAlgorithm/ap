package fraudengine;

import catfish.base.StartupConfig;

public class Configuration {

  public static final String JOB_STATUS_QUEUE = "StatusQueueV2";
  public static final String FRAUD_DECISION_QUEUE = "FraudDecisionJobRequestQueue";
  private static int         threadCount;
  private static String hostname;
  private static int port;

  public static void initialize() {
    threadCount = StartupConfig.getAsInt("catfish.queue.thread.count");
    hostname = StartupConfig.get("catfish.fraueengine.host");
    port = StartupConfig.getAsInt("catfish.fraudengine.port");
  }

  public static int getThreadCount() {
    return threadCount;
  }

  public static void setThreadCount(int threadCount) {
    Configuration.threadCount = threadCount;
  }

	public static String getHostname() {
		return hostname;
	}
	
	public static void setHostname(String hostname) {
		Configuration.hostname = hostname;
	}
	
	public static int getPort() {
		return port;
	}
	
	public static void setPort(int port) {
		Configuration.port = port;
	}
  
  
  
}
