package catfish.notification.messagegeneration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;

public class SimpleMessageDefinition implements MessageDefinition {

  private Channel channel;
  private Receiver receiver;
  private String message;

  public SimpleMessageDefinition(Receiver receiver, Channel channel, String message) {
    this.channel = channel;
    this.receiver = receiver;
    this.message = message;
  }
  
  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
    if(channel == null) {
      if (MessageDatabaseApi.isApplicationFromAgentApp(appId)) {
        return Arrays.<Message>asList(MessagesFactory.createMessage(receivedMessage, Channel.APP, receiver, message));
      } else {
        return Arrays.<Message>asList(MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, receiver, message));
      }
    }
    return Arrays.<Message>asList(MessagesFactory.createMessage(receivedMessage, channel, receiver, message));
  }
}
