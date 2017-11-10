package catfish.notification.messagegeneration.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.notification.Message;
import catfish.notification.message.LeanCloudMessage;
import catfish.notification.messagegeneration.MessageDefinition;

/**
 * funtion: send operation msg via app. only for cashloan.<p>
 * date： 2017-05-02
 * @author jiaoh
 *
 */
public class RawToAppOperationMessageDefinition implements MessageDefinition{
	
	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {
	    String userId = (String) receivedMessage.get("userId");
	    String content = (String) receivedMessage.get("content");
	    String type = (String) receivedMessage.get("type");
	    return Arrays.<Message>asList(new LeanCloudMessage(receivedMessage, userId, content, type));
	}

}
