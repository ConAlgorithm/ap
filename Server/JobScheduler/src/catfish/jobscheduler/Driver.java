package catfish.jobscheduler;

import java.util.Arrays;

import catfish.base.BaseConfiguration;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.persistence.queue.PersistenceConfig;

public class Driver {

  public static void main(String[] args) {
    BaseConfiguration.setIsRecoveryRequired(true);
    StartupConfig.initialize();
    Logger.initialize();
    PersistenceConfig.initialize();

    if (Arrays.asList(args).contains("--recovery")) {
      BaseConfiguration.setInRecoveryMode("recovery.txt");
    }

    Configuration.readConfiguration();
    JobConfig.readConfigFromFile();
    Logger.get().info("System is running ...");
    Scheduler.run();  // will not return
  }
}
