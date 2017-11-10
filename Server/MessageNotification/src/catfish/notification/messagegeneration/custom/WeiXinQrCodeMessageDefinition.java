package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.message.WeChatText;
//import catfish.notification.messagegeneration.AppMessageDefinition;
//import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
//import catfish.notification.messagegeneration.Receiver;
import catfish.notification.sender.wechat.TokenManager;


public class WeiXinQrCodeMessageDefinition implements MessageDefinition {
	
	public List<Message> apply(Map<String, ?> receivedMessage) {
//		String AppId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
	    String extra = (String)receivedMessage.get(MessageNotificationUtil.NotificationKeys.EXTRAINFO);
	    
	    String[] extras = extra.split("@"); 
	    String openId = extras[0];
	    String content = String.format(
	      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.S1, Channel.WECHAT),
	      Configuration.getQrCodeUrlFormat(),extras[1]);
	    TokenManager tokenManager = Configuration.getWeChatMerchantTokenManager();
	    
	    List<Message> managerList = new ArrayList<Message>();
	    managerList.add(new WeChatText(receivedMessage, tokenManager, openId, content));
	    return managerList; 
	  }
}
