package catfish.notification.messagegeneration;

import java.util.List;
import java.util.Map;

import catfish.notification.Message;

public interface MessageDefinition {
  List<Message> apply(Map<String, ?> receivedMessage);
}
