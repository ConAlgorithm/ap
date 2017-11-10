package catfish.notification.messagegeneration.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;

public class StartApplyMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);

    String D1Content = String.format(ResourceConfig.getResourceByName(this.getClass()
      .getSimpleName(), Receiver.D1, Channel.WECHAT), MessageDatabaseApi
      .getWeiXinUserNameBy(appId));

    return Arrays.<Message> asList(
      MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.D1, D1Content));

  }

}
