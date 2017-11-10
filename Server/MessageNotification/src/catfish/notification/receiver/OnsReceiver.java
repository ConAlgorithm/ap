package catfish.notification.receiver;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import catfish.base.Logger;
import catfish.notification.Configuration;
import catfish.notification.sender.MessagesSender;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

public class OnsReceiver {

  private static final int RETRYING_INTERVAL = 3;          // seconds

  public static void trigger() {
    Properties properties = new Properties();
    properties.put(PropertyKeyConst.ConsumerId, Configuration.getOnsConsumerId());
    properties.put(PropertyKeyConst.AccessKey, Configuration.getOnsAccessKey());
    properties.put(PropertyKeyConst.SecretKey, Configuration.getOnsSecretKey());
    Consumer consumer = ONSFactory.createConsumer(properties);

    consumer.subscribe(Configuration.getOnsTopic(), "*", new MessageListener() {
      @Override
      public Action consume(Message message, ConsumeContext context) {
        try {
          Logger.get().info("Got ONS message ID " + message.getMsgID());
          MessagesSender.handle(
              new String(message.getBody(), Configuration.UTF_8), RETRYING_INTERVAL);
        } catch (RuntimeException | UnsupportedEncodingException e) {
          Logger.get().warn("handle ons notification error", e);
          return Action.ReconsumeLater;
        }
        return Action.CommitMessage;
      }
    });

    consumer.start();
  }
}
