package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.ImageEnum;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;
import catfish.notification.messagegeneration.SimpleMessageDefinition;

public class BuckleUploadMessageDefinition implements MessageDefinition {
  // messages

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {

    String BUCKLE_FIRST_UPLOAD = ResourceConfig.getResourceByName(this.getClass()
      .getSimpleName(), Receiver.CUSTOMER);
    String REUPLOAD_AFTER_IOU = ResourceConfig.getResourceByName(this.getClass()
      .getSimpleName() + "1", Receiver.CUSTOMER);
    String REUPLOAD = ResourceConfig.getResourceByName(this.getClass().getSimpleName() + "2",
      Receiver.CUSTOMER);
    String FIRST_UPLOAD_MERCHANT = ResourceConfig.getResourceByName(this.getClass()
      .getSimpleName(), Receiver.S1);
    String REUPLOAD_MERCHANT = ResourceConfig.getResourceByName(this.getClass().getSimpleName()
      + "1", Receiver.S1);

    String count = null;
    String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);
    String customerName = MessageDatabaseApi.getCustomerNameBy(appId);
    if (receivedMessage.containsKey(MessageNotificationUtil.NotificationKeys.EXTRAINFO)) {
      count = (String) receivedMessage
        .get(MessageNotificationUtil.NotificationKeys.EXTRAINFO);
    }
    List<Message> messageList = new ArrayList<Message>();
    if (count.equals("1")) {
      messageList.add(MessagesFactory.createMessage(receivedMessage, Channel.WECHAT,
        Receiver.S1, String.format(FIRST_UPLOAD_MERCHANT, customerName)));
      messageList.addAll(new SimpleMessageDefinition(Receiver.CUSTOMER, null,
        BUCKLE_FIRST_UPLOAD).apply(receivedMessage));
      if (!MessageDatabaseApi.isApplicationFromAgentApp(appId)) {
        messageList.add(MessagesFactory.createMessage(receivedMessage,
          Channel.WECHATIMAGE, Receiver.CUSTOMER, ImageEnum.CUSTOMER_BUCKLE.toString()));
      }
    } else {
      messageList.add(MessagesFactory.createMessage(receivedMessage, Channel.WECHAT,
        Receiver.S1, String.format(REUPLOAD_MERCHANT, customerName)));
      messageList.addAll(new SimpleMessageDefinition(Receiver.CUSTOMER, null, count
        .equals("2") ? REUPLOAD_AFTER_IOU : REUPLOAD).apply(receivedMessage));
    }
    return messageList;
  }

}
