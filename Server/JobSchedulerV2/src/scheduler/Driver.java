package scheduler;

import java.util.Arrays;

import catfish.base.BaseConfiguration;
import catfish.base.Logger;
import catfish.base.ons.MessageNotificationUtil;
import catfish.base.ons.OnsConfig;
import catfish.base.persistence.queue.*;
import catfish.base.StartupConfig;

public class Driver {

  public static void main(String[] args) {
    BaseConfiguration.setIsRecoveryRequired(true);
    StartupConfig.initialize();
    Logger.initialize();
    PersistenceConfig.initialize();
    OnsConfig.initialize();
    MessageNotificationUtil.start();

    if (Arrays.asList(args).contains("--recovery")) {
      BaseConfiguration.setInRecoveryMode("recovery.txt");
    }

    Configuration.readConfiguration();
    Logger.get().info("System is running ...");
    EventTracker.track();   // will not return
  }
}
