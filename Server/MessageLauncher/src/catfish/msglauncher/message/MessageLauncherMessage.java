package catfish.msglauncher.message;

import catfish.msglauncher.Message;
import catfish.msglauncher.util.MessageType;

public class MessageLauncherMessage {
	public static final String MESSAGE_TYPE = "messageType";
	public static final String MESSAGE = "message";
	MessageType messageType;
	Message message;
	
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}
