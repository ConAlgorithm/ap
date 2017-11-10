package catfish.notification.messagegeneration.custom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.msglauncher.model.ShortMessageProvider;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.SmsSourceEnums;
import catfish.notification.message.LeanCloudMessage;
import catfish.notification.message.ShortMessage;
import catfish.notification.message.SmsMessage;
import catfish.notification.message.WeChatText;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.sender.CommonServiceSmsSender;

public class FreeContentMessageDefinition implements MessageDefinition {

	@Override
	public List<Message> apply(final Map<String, ?> receivedMessage) {
		final String mobile = (String) receivedMessage.get(NotificationKeys.MOBILE);
		String content = (String) receivedMessage.get(NotificationKeys.CONTENT);
		Logger.get().info("Send short message to :" + mobile+ "-------" + content);
		
		if (Configuration.getCommonServiceSwitchOn()) {
		  
		  SmsMessage message = 
	        new SmsMessage(receivedMessage, mobile, content, null, SmsSourceEnums.PSL_TAG.getValue());
	    return CommonServiceSmsSender.sendSingleTextMessage(message);
	    
		} 
		// 原来的代码发送较复杂，开关保留原代码逻辑
		else {
		  
	    MessageSender sender = new MessageSender() {
	      @Override
	      public Boolean execute(String message) {
	        return catfish.notification.sender.MessagesSender.send(
	            Arrays.<Message>asList(new ShortMessage(receivedMessage, mobile, message)), 1);
	      }

	      @Override
	      public Boolean execute(String message, int provider) {
	        return catfish.notification.sender.MessagesSender.send(Arrays
	            .<Message>asList(new ShortMessage(receivedMessage, mobile, message, provider)), 1);
	      }
	      
	      
	      @Override
	      public Boolean executePush(String message) {
              return null;
          }
	      
	      @Override
          public Boolean executeWechat(String message, String openId) {
              return null;
          }
	      
	    };

	    MessageHandler(sender,content);
	    return Arrays.<Message>asList();
		}
	}
	
	public static void MessageHandler(MessageSender sender,String message) {
		int provider = ShortMessageProvider.DaHanSanTong.getValue();			
		sender.execute(message, provider);				
	}

}
