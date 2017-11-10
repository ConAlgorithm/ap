package scheduler;

import java.util.HashMap;
import java.util.Map;

public class EventWaiterRepository {

  private static Map<String, EventWaiter> repository = new HashMap<>();

  public static void book(String appId, String activityName, EventWaiter waiter) {
    repository.put(String.format("%s:%s", appId.toUpperCase(), activityName), waiter);
  }

  public static EventWaiter pop(String appId, String activityName) {
    return repository.remove(String.format("%s:%s", appId.toUpperCase(), activityName));
  }
}
