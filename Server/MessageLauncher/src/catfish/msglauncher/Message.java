package catfish.msglauncher;

import java.util.Map;

public abstract class Message {
  private Map<String, ?> receivedMessage;             // for logging

  public Message(Map<String, ?> receivedMessage) {
    this.receivedMessage = receivedMessage;
  }

  @Override
  public String toString() {
	if(null == receivedMessage){
		return "";
	}else{
		return receivedMessage.toString();
	}
    
  }
}
