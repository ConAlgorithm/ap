package catfish.notification.messagegeneration.custom;

import java.util.List;
import java.util.Map;

import catfish.base.Logger;
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

public class SendValidationCodeMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    
    List<String> queryResult = MessageDatabaseApi
      .getMobileAndValidationCodeByUserId((String)receivedMessage.get("UserId"));
    
    if (queryResult == null) {
		Logger.get().warn(
				"there is no validation code record in db to send for mobile:"
						+ receivedMessage.get("ExtraInfo"));
		return null;
	}
    
    String content = String.format(ResourceConfig.getResourceByName(this.getClass().getSimpleName(),
      Receiver.CUSTOMER, Channel.SHORTMESSAGE), queryResult.get(1));
    String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
    
    Logger.get().info("Send short message to :" + queryResult.get(0)+ "-------" + content);
    SmsMessage message = 
	        new SmsMessage(receivedMessage, queryResult.get(0), content, sign, SmsSourceEnums.PSL_TAG.getValue());
	    
	return CommonServiceSmsSender.sendSingleTextMessage(message);    
  }
}
