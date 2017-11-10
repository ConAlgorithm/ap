package catfish.manualjobarranger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StealController {
  public static void run() {
    List<String> queues = ManualJobDatabaseApi.getAllQueues();

    ExecutorService executor = Executors.newFixedThreadPool(Configuration.getStealerCount());
    for (int i = 0; i < Configuration.getStealerCount(); i++) {
      executor.execute(new MessageStealer(queues));
    }
    executor.shutdown();
  }
}
