package catfish.notification.messagegeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Receiver;

import com.google.gson.Gson;

/*******
 * 不推荐使用这个方法，即将弃用
 * @author PC
 *
 */
public class GenericTextMsgDefinition implements MessageDefinition {

	@Override
	public List<Message> apply(Map<String, ?> receivedMessage) {
		// Message sent via ONS puts extra information in ExtraInfo key
		if (receivedMessage.containsKey("ExtraInfo")) {
			String extraInfo = (String) receivedMessage.get("ExtraInfo");
		    receivedMessage.remove("ExtraInfo");
		    @SuppressWarnings("unchecked") Map<String, Object> extraMap =
		            (Map<String, Object>) new Gson().fromJson(extraInfo, Map.class);
		    
		    return realApply(extraMap);
		}
		else return realApply(receivedMessage);
	}
	
	private List<Message> realApply(Map<String, ?> receivedMessage) {
	    String ObjectType = (String) receivedMessage.get("ObjectType");
	    String MessageType = (String) receivedMessage.get("MessageType");
	    
	    if (ObjectType != null && MessageType != null) {
	    	if (ObjectType.equals("CUSTOMER")) {
	    		if (MessageType.equals("WECHAT")) {
	    			return (new SimpleMessageDefinition(Receiver.CUSTOMER, Channel.WECHAT, (String) receivedMessage.get("Content")).apply(receivedMessage));
	    		}
	    		else if (MessageType.equals("SHORTMESSAGE")) {
	    			return (new SimpleMessageDefinition(Receiver.CUSTOMER, Channel.SHORTMESSAGE, (String) receivedMessage.get("Content")).apply(receivedMessage));
	    		}
	    	}
	    	else if (ObjectType.equals("MERCHANT")) {
	    		if (MessageType.equals("WECHAT")) {
	    			return (new SimpleMessageDefinition(Receiver.S1, Channel.WECHAT, (String) receivedMessage.get("Content")).apply(receivedMessage));
	    		}
	    		else if (MessageType.equals("SHORTMESSAGE")) {
	    			return (new SimpleMessageDefinition(Receiver.S1, Channel.SHORTMESSAGE, (String) receivedMessage.get("Content")).apply(receivedMessage));
	    		}
	    	}
	    	else if (ObjectType.equals("DEALER")) {
	    		if (MessageType.equals("WECHAT")) {
	    			return (new SimpleMessageDefinition(Receiver.D1, Channel.WECHAT, (String) receivedMessage.get("Content")).apply(receivedMessage));
	    		}
	    		else if (MessageType.equals("SHORTMESSAGE")) {
	    			return (new SimpleMessageDefinition(Receiver.D1, Channel.SHORTMESSAGE, (String) receivedMessage.get("Content")).apply(receivedMessage));
	    		}
	    	}
	    }
	    
	    // No valid object and message type found, return an empty list to prevent exception from Foreach statement
	    return new ArrayList<Message>();
	}
}
