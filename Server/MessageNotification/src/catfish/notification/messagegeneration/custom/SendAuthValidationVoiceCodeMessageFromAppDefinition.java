package catfish.notification.messagegeneration.custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.spy.memcached.MemcachedClient;
import catfish.base.Logger;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;
import catfish.notification.common.SmsSourceEnums;
import catfish.notification.config.ResourceConfig;
import catfish.notification.config.ResourceProperties;
import catfish.notification.message.ShortMessage;
import catfish.notification.message.SmsMessage;
import catfish.notification.messagegeneration.MemcachedApi;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.sender.CommonServiceSmsSender;

public class SendAuthValidationVoiceCodeMessageFromAppDefinition implements
    MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
	String mobile = (String) receivedMessage.get("ExtraInfo");
    List cacheLists = null;
    try {
		MemcachedClient cache = MemcachedApi.getMemcachedClient();
		cacheLists= (ArrayList)(cache.get((String) receivedMessage
            .get("ExtraInfo")));
		
	} catch (IOException e) {
		e.printStackTrace();
	}

    if (cacheLists == null || cacheLists.size() == 0) {
      Logger.get().warn(
          "there is no authValidation code record in memcached to send for mobile:"
              + receivedMessage.get("ExtraInfo"));
      return null;
    }
    
    String code = (String)cacheLists.get(cacheLists.size() -1 );
    String content = String.format(code);
    
    String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
    
    Logger.get().info("Send short message to :" + mobile + "-------" + content);   
    SmsMessage message = 
	        new SmsMessage(receivedMessage, mobile, content, sign, SmsSourceEnums.PSL_TAG.getValue());
	    
	return CommonServiceSmsSender.sendSingleVoiceMessage(message);

  }
}
