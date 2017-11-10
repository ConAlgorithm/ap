package catfish.notification.messagegeneration.custom;

import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;
import catfish.notification.common.SmsSourceEnums;
import catfish.notification.config.ResourceConfig;
import catfish.notification.config.ResourceProperties;
import catfish.notification.message.SmsMessage;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.sender.CommonServiceSmsSender;

public class LoanMoneySuccessMsgDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
	String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
	String mobile = (String) MessageDatabaseApi.getCustomerMobileBy(appId);
	  
    int instalmentChannel = Integer.parseInt((String) receivedMessage
      .get("InstalmentChannel"));

    String template = ResourceConfig.getResourceByName(this.getClass().getSimpleName(),
      Receiver.CUSTOMER, Channel.SHORTMESSAGE);
    if (instalmentChannel == InstalmentChannel.App.getValue()) {
      template = ResourceConfig.getResourceByName(this.getClass().getSimpleName() + "1",
        Receiver.CUSTOMER, Channel.SHORTMESSAGE);
    }
    String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
    
    Logger.get().info("Send short message to :" + mobile+ "-------" + template);        
    SmsMessage message = 
	        new SmsMessage(receivedMessage, mobile, template, sign, SmsSourceEnums.PSL_TAG.getValue());
	    
	return CommonServiceSmsSender.sendSingleTextMessage(message); 
  }
}
