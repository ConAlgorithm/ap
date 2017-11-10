package catfish.notification;

import java.util.Map;

public abstract class Message {
  private Map<String, ?> receivedMessage;             // for logging

  public Message(Map<String, ?> receivedMessage) {
    this.receivedMessage = receivedMessage;
  }

  @Override
  public String toString() {
    return receivedMessage.toString();
  }

  public Map<String, ?> getReceivedMessage() {
		return receivedMessage;
  }
	
  public void setReceivedMessage(Map<String, ?> receivedMessage) {
		this.receivedMessage = receivedMessage;
  }
  
  
}
