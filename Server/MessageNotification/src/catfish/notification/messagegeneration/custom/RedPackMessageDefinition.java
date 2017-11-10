package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.message.WeChatText;
//import catfish.notification.messagegeneration.AppMessageDefinition;
//import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;


public class RedPackMessageDefinition implements MessageDefinition {
	@Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
//		String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
	    String extra = (String)receivedMessage.get(MessageNotificationUtil.NotificationKeys.EXTRAINFO);

	    String[] extras = extra.split("&");
	    String openId = extras[0];
	    String content = extras[1];
	    String role = extras[2];

	    List<Message> managerList = new ArrayList<Message>();
	    if (role.equals("BD")) {
	      managerList.add(new WeChatText(receivedMessage, Configuration.getWeChatBDTokenManager(), openId, content));
	    } else if (role.equals("D")) {
	      managerList.add(new WeChatText(receivedMessage, Configuration.getWeChatDealerTokenManager(), openId, content));
	    }
	    return managerList;
	}
}
