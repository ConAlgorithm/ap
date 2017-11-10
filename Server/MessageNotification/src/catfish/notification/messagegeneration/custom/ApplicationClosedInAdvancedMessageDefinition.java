package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Product;
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

public class ApplicationClosedInAdvancedMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
    String mobile = (String) MessageDatabaseApi.getCustomerMobileBy(appId);
    String customerName = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.EXTRAINFO);
    String customerContent;
    
    if (MessageDatabaseApi.isPDLApplication(appId)) {
      customerContent = String.format(
        ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Product.PDL), customerName);
      List<Message> messageList = new ArrayList<Message>();
      messageList.addAll(new SimpleMessageDefinition(Receiver.CUSTOMER, null,
        customerContent).apply(receivedMessage));
      messageList.add(MessagesFactory.createMessage(receivedMessage, Channel.SHORTMESSAGE,
        Receiver.CUSTOMER, customerContent));
      return messageList;
      
    } else {
      customerContent = String.format(
        ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Product.POS), customerName);
      String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
      
      Logger.get().info("Send short message to :" + mobile+ "-------" + customerContent);      
      SmsMessage message = 
  	        new SmsMessage(receivedMessage, mobile, customerContent, sign, SmsSourceEnums.PSL_TAG.getValue());
  	    
  	  return CommonServiceSmsSender.sendSingleTextMessage(message); 
    }
  }
}
