package catfish.notification;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.database.DatabaseConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.queue.QueueConfig;
import catfish.notification.receiver.HttpReceiver;
import catfish.notification.receiver.MqsReceiver;
import catfish.notification.receiver.OnsReceiver;

public class Driver {

  private static final String validationQueueProperty = "catfish.validation.queuename";

  public static void main(String[] args) {
    StartupConfig.initialize();
    Logger.initialize();
    DatabaseConfig.initialize();

    QueueConfig.initialize();
    Configuration.initialize();
    MessageConfig.initialize();
    HttpClientConfig.initialize();
    
    OnsReceiver.trigger();
    HttpReceiver.trigger();
    MqsReceiver.trigger();
    MqsReceiver.trigger(StartupConfig.get(validationQueueProperty, "ValidationCodeQueue"));
    Logger.get().info("System is running ...");
  }
}
