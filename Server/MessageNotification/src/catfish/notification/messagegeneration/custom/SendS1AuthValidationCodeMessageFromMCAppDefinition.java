package catfish.notification.messagegeneration.custom;

import java.io.IOException;
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
import catfish.notification.messagegeneration.MemcachedApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.sender.CommonServiceSmsSender;
import net.spy.memcached.MemcachedClient;

public class SendS1AuthValidationCodeMessageFromMCAppDefinition implements
		MessageDefinition {

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {

		List<Object> cacheLists = null;
		String mobile = (String) receivedMessage.get("ExtraInfo");
		try {
			MemcachedClient cache = MemcachedApi.getMemcachedClient();
			cacheLists = (List<Object>) (cache.get(mobile));

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (cacheLists == null || cacheLists.size() == 0) {
			Logger.get().warn(
					"there is no authValidation code record in memcached to send for mobile:"
							+ mobile);
			return null;
		}

		String code = (String) cacheLists.get(cacheLists.size() - 1);

		String content = String.format(ResourceConfig.getResourceByName(this
				.getClass().getSimpleName(), Receiver.CUSTOMER,
				Channel.SHORTMESSAGE), code);
		String sign = ResourceProperties.getProperty("CHINAMOBILE_SHORTMESSAGE_SIGN");
		
		
		SmsMessage message = 
				new SmsMessage(receivedMessage, mobile, content, sign, SmsSourceEnums.CMPOS.getValue() + SmsSourceEnums.CODE.getValue());
		
		return CommonServiceSmsSender.sendSingleTextMessage(message);
	}
}
