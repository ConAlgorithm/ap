package catfish.jobschedulerepeater;

import java.util.Random;
import java.util.UUID;

import catfish.base.Logger;
import catfish.base.queue.QueueApi;
import catfish.base.queue.QueueMessager;
import catfish.jobschedulerepeater.RepeatersInfo.OutputInfo;
import catfish.jobschedulerepeater.RepeatersInfo.RepeaterInfo;

public class Repeater implements Runnable {

  private Random random = new Random();
  private RepeaterInfo info;

  public Repeater(RepeaterInfo info) {
    this.info = info;
  }

  @Override
  public void run() {
    while (true) {
      try {
        QueueMessager messager = QueueApi.consumeMessager(info.getInputQueue());
        QueueApi.writeMessager(selectOutputQueue(generateValue(messager.getAppId())), messager);
      } catch (RuntimeException e) {
        Logger.get().error("Got unexpected exception", e);
      }
    }
  }

  private int generateValue(String appId) {
    UUID id = UUID.fromString(appId);
    random.setSeed(id.getMostSignificantBits() ^ id.getLeastSignificantBits());
    return 1 + random.nextInt(1000);
  }

  private String selectOutputQueue(int value) {
    for (OutputInfo out : info.getOutputsInfo()) {
      if (out.getLowerBound() <= value && value <= out.getUpperBound()) {
        return out.getOutputQueue();
      }
    }
    throw new RuntimeException("No queue found for value " + value);
  }
}
