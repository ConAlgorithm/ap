package catfish.jobschedulerepeater;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.queue.QueueConfig;
import catfish.jobschedulerepeater.RepeatersInfo.RepeaterInfo;

public class Driver {

  public static void main(String[] args) {
    StartupConfig.initialize();
    Logger.initialize();
    QueueConfig.initialize();
    Configuration.readConfiguration();

    schedule();
    Logger.get().info("System is running ...");
  }

  private static void schedule() {
    for (RepeaterInfo repeaterInfo : Configuration.getRepeatersInfo().getRepeaters()) {
      for (int i = 0; i < repeaterInfo.getWorkers(); i++) {
        new Thread(new Repeater(repeaterInfo)).start();
      }
    }
  }
}
