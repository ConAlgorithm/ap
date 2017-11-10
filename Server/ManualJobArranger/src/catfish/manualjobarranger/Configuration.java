package catfish.manualjobarranger;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import catfish.base.StartupConfig;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Configuration {
  private static ArrangerConfig arrangerConfig;
  private static String hostname;
  private static int port;
  private static int stealerCount;

  //for 移动项目
  private static String appServiceUrl;
  private static int appServiceMaxRetries;
  private static String flowQueue;
  
  //job lost fix
  private static boolean useNewStrategy;
  private static int jobReserveSeconds;
  
  //**********************

  public static void initialize() {
    try {
      arrangerConfig = new Gson().fromJson(
          FileUtils.readFileToString(new File("Arranger.config")), ArrangerConfig.class);
    } catch (JsonSyntaxException | IOException e) {
      throw new RuntimeException(e);
    }

    hostname = StartupConfig.get("catfish.arranger.host");
    port = StartupConfig.getAsInt("catfish.arranger.port");
    stealerCount = StartupConfig.getAsInt("catfish.arranger.stealerCount");
    flowQueue = StartupConfig.get("catfish.flow.queue");
    appServiceUrl = StartupConfig.get("catfish.application.service.url");
    appServiceMaxRetries = StartupConfig.getAsInt("catfish.application.service.maxRetries", 1);
    
    //是否使用新的调度策略和job刚刚被分配后的预留时间
    useNewStrategy = StartupConfig.getAsBoolean("job.fetch.newstrategy");
    jobReserveSeconds = StartupConfig.getAsInt("job.reserve.seconds");
  }

  public static ArrangerConfig getArrangerConfig() {
    return arrangerConfig;
  }

  public static String getHostname() {
    return hostname;
  }

  public static int getPort() {
    return port;
  }

  public static int getStealerCount() {
    return stealerCount;
  }

  public static String getFlowQueue() {
    return flowQueue;
  }

  public static void setFlowQueue(String flowQueue) {
    Configuration.flowQueue = flowQueue;
  }

  public static String getAppServiceUrl() {
    return appServiceUrl;
  }

  public static void setAppServiceUrl(String appServiceUrl) {
    Configuration.appServiceUrl = appServiceUrl;
  }

  public static int getAppServiceMaxRetries() {
    return appServiceMaxRetries;
  }

  public static void setAppServiceMaxRetries(int appServiceMaxRetries) {
    Configuration.appServiceMaxRetries = appServiceMaxRetries;
  }

public static boolean isUseNewStrategy()
{
	return useNewStrategy;
}

public static void setUseNewStrategy(boolean useNewStrategy)
{
	Configuration.useNewStrategy = useNewStrategy;
}

public static int getJobReserveSeconds()
{
	return jobReserveSeconds;
}

public static void setJobReserveSeconds(int jobReserveSeconds)
{
	Configuration.jobReserveSeconds = jobReserveSeconds;
}
}
