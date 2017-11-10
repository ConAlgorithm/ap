package catfish.notification.messagegeneration.custom;

import java.util.List;
import java.util.Map;

import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Receiver;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.SimpleMessageDefinition;

public class RejectApplicationMessageDefinition implements MessageDefinition{

	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {
	  String content = (String)receivedMessage.get(MessageNotificationUtil.NotificationKeys.EXTRAINFO);
    return new SimpleMessageDefinition(Receiver.CUSTOMER, null, content).apply(receivedMessage);
	}
}
