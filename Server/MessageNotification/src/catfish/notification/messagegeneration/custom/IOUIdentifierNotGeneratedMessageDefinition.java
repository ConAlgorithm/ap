package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.ImageEnum;
import catfish.notification.common.Receiver;
import catfish.notification.common.SmsSourceEnums;
import catfish.notification.config.ResourceConfig;
import catfish.notification.config.ResourceProperties;
import catfish.notification.message.SmsMessage;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;
import catfish.notification.messagegeneration.SimpleMessageDefinition;
import catfish.notification.sender.CommonServiceSmsSender;

public class IOUIdentifierNotGeneratedMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    List<Message> messageList = new ArrayList<Message>();
    String appId = (String) receivedMessage
      .get(MessageNotificationUtil.NotificationKeys.APP_ID);
    String mobile = (String) MessageDatabaseApi.getCustomerMobileBy(appId);
    
    String customerContent = ResourceConfig.getResourceByName(this.getClass().getSimpleName(),
      Receiver.CUSTOMER);
    String smsContent = ResourceConfig.getResourceByName(this.getClass().getSimpleName(),
      Receiver.CUSTOMER, Channel.SHORTMESSAGE);
    String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
    
    Logger.get().info("Send short message to :" + mobile+ "-------" + smsContent);
    SmsMessage message = 
	        new SmsMessage(receivedMessage, mobile, smsContent, sign, SmsSourceEnums.PSL_TAG.getValue());
    
    CommonServiceSmsSender.sendSingleTextMessage(message);
    
    if (!MessageDatabaseApi.isApplicationFromAgentApp(appId)) {
      messageList.add(MessagesFactory.createMessage(receivedMessage, Channel.WECHATIMAGE,
        Receiver.CUSTOMER, ImageEnum.CUSTOMER_IOU.toString()));
    }
    messageList.addAll(new SimpleMessageDefinition(Receiver.CUSTOMER, null,
      customerContent).apply(receivedMessage));

    return messageList;
  }
}
