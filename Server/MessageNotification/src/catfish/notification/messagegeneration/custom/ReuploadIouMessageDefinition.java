package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
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

public class ReuploadIouMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);
    String mobile = (String) MessageDatabaseApi.getMerchantMobileBy(appId);
    
    String customerContent = ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER);
    
    String merchantContent = String.format(
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.S1, Channel.WECHAT),
      MessageDatabaseApi.getCustomerNameBy(appId),
      String.format(Configuration.getGuidedIouUrlFormat(), appId));
    
    String merchantSMSContent = String.format(
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.S1, Channel.SHORTMESSAGE),
      MessageDatabaseApi.getCustomerNameBy(appId),
      String.format(Configuration.getGuidedIouUrlFormat(), appId));
          
    String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
    
    Logger.get().info("Send short message to :" + mobile+ "-------" + merchantSMSContent); 
    SmsMessage message = 
	        new SmsMessage(receivedMessage, mobile, merchantSMSContent, sign, SmsSourceEnums.PSL_TAG.getValue());
	    
	CommonServiceSmsSender.sendSingleTextMessage(message);
    
    List<Message> managerList = new ArrayList<Message>();
    managerList.add(MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.S1, merchantContent));
    if(!MessageDatabaseApi.isApplicationFromAgentApp(appId)) {
      managerList.add(MessagesFactory.createMessage(receivedMessage, Channel.WECHATIMAGE, 
        Receiver.CUSTOMER, ImageEnum.CUSTOMER_IOU.toString()));
    }
    managerList.addAll(new SimpleMessageDefinition(Receiver.CUSTOMER, null, customerContent).apply(receivedMessage));
    return managerList;
  }
}
