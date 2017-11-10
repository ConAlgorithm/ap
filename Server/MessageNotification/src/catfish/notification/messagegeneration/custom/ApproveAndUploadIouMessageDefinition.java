package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.DynamicConfig;
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
import catfish.notification.messagegeneration.JDTestApi;
import catfish.notification.messagegeneration.MessageDataRequestApi;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;
import catfish.notification.messagegeneration.SimpleMessageDefinition;
import catfish.notification.sender.CommonServiceSmsSender;

public class ApproveAndUploadIouMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);
    String mobile = "";    
    
    boolean salesAdminServiceSwitchOn = Boolean.FALSE;
	salesAdminServiceSwitchOn = DynamicConfig.readAsBool("salesAdminServiceSwitchOn");// 获取smsa开关
	boolean isposApp = MessageDatabaseApi.isPOSApplication(appId);
    
    String customerContent = ResourceConfig.getResourceByName(this.getClass().getSimpleName(),
      Receiver.CUSTOMER);
    String merchantContent = String.format(ResourceConfig.getResourceByName(this
      .getClass().getSimpleName(), Receiver.S1, Channel.WECHAT), MessageDatabaseApi
      .getCustomerNameBy(appId), String.format(Configuration.getGuidedIouUrlFormat(),
      appId));

    String merchantSMSContent = String.format(ResourceConfig.getResourceByName(this
      .getClass().getSimpleName(), Receiver.S1, Channel.SHORTMESSAGE), MessageDatabaseApi
      .getCustomerNameBy(appId), String.format(Configuration.getGuidedIouUrlFormat(),
      appId));

    Receiver receiver = JDTestApi.getReceiver(appId);
    if(receiver == null)
    	return null;
    switch (receiver) {
	case S1:
		mobile = MessageDatabaseApi.getMerchantMobileBy(appId);
		break;
	case D1:
		if (salesAdminServiceSwitchOn && isposApp) {
			mobile = MessageDataRequestApi.getD1MobileByAppId(appId);
		} else {
			mobile = MessageDatabaseApi.getD1MobileBy(appId);
		}
		break;
	default:
		break;
	}
    String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
    
    Logger.get().info("Send short message to :" + mobile+ "-------" + merchantSMSContent);
    SmsMessage message = 
	        new SmsMessage(receivedMessage, mobile, merchantSMSContent, sign, SmsSourceEnums.PSL_TAG.getValue());
	    
	CommonServiceSmsSender.sendSingleTextMessage(message);
    
    List<Message> messageList = new ArrayList<Message>();
    messageList.add(MessagesFactory.createMessage(receivedMessage, Channel.WECHAT,
    		receiver, merchantContent));
    messageList.add(MessagesFactory.createMessage(receivedMessage, Channel.WECHATIMAGE,
    		receiver, ImageEnum.S1_IOU.toString()));
    if (!MessageDatabaseApi.isApplicationFromAgentApp(appId)) {
      messageList.add(MessagesFactory.createMessage(receivedMessage, Channel.WECHATIMAGE,
        Receiver.CUSTOMER, ImageEnum.CUSTOMER_IOU.toString()));
    }
    messageList.addAll(new SimpleMessageDefinition(Receiver.CUSTOMER, null,
      customerContent).apply(receivedMessage));
    return messageList;
  }
}
