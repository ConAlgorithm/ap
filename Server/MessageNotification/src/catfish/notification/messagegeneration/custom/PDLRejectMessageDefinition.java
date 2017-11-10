package catfish.notification.messagegeneration.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.Product;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;

public class PDLRejectMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);
    String merchantContent = String.format(
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(),
        Receiver.S1, Channel.WECHAT, Product.PDL),  
      MessageDatabaseApi.getCustomerNameBy(appId));
    String customerContent =
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(),
        Receiver.CUSTOMER, Channel.APP, Product.PDL);
    
    return Arrays.<Message>asList(
      MessagesFactory.createMessage(receivedMessage, Channel.APP, Receiver.CUSTOMER, customerContent),
      MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.S1, merchantContent),
      MessagesFactory.createMessage(receivedMessage, Channel.SHORTMESSAGE, Receiver.CUSTOMER, customerContent));
  }

}
