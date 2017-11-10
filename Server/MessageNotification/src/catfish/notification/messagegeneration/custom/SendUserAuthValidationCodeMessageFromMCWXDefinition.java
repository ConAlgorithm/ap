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

public class SendUserAuthValidationCodeMessageFromMCWXDefinition implements
		MessageDefinition {

	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {
		String mobile = (String) receivedMessage.get("ExtraInfo");
		List<String> queryResult = MessageDatabaseApi
				.getMobileAndValidationCodeByMobile((String) receivedMessage
						.get("ExtraInfo"));

		if (queryResult == null) {
			Logger.get().warn(
					"there is no validation code record in db to send for mobile:"
							+ receivedMessage.get("ExtraInfo"));
			return null;
		}

		String content = String.format(ResourceConfig.getResourceByName(this
				.getClass().getSimpleName(), Receiver.CUSTOMER,
				Channel.SHORTMESSAGE), queryResult.get(1));
		String sign = ResourceProperties.getProperty("CHINAMOBILE_SHORTMESSAGE_SIGN");
		
		SmsMessage message = 
		        new SmsMessage(receivedMessage, mobile, content, sign, SmsSourceEnums.CMPOS.getValue() + SmsSourceEnums.CODE.getValue());
		    
		return CommonServiceSmsSender.sendSingleTextMessage(message);
	}

}
