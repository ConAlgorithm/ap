package catfish.notification.messagegeneration.custom;

import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.notification.Message;
import catfish.notification.common.SmsSourceEnums;
import catfish.notification.config.ResourceProperties;
import catfish.notification.message.SmsMessage;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.sender.CommonServiceSmsSender;

/**
 * function: collection short msg standardization.<p>
 * date: 2017-05-23
 * @author jiaoh
 *
 */
public class RawOverdueMessageDefinition implements MessageDefinition {

	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {
	    String mobile = (String) receivedMessage.get("mobile");
	    String content = (String) receivedMessage.get("content");	    	        
	    String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
	    
	    Logger.get().info("Send short message to :" + mobile+ "-------" + content);	
	    SmsMessage message = 
		        new SmsMessage(receivedMessage, mobile, content, sign, SmsSourceEnums.PSL_TAG.getValue());
		    
		return CommonServiceSmsSender.sendSingleTextMessage(message);
	}
}
