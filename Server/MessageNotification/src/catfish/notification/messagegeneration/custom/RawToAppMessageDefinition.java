package catfish.notification.messagegeneration.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.notification.Message;
import catfish.notification.message.LeanCloudMessage;
import catfish.notification.messagegeneration.MessageDefinition;

public class RawToAppMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String userId = (String) receivedMessage.get("userId");
    String content = (String) receivedMessage.get("content");
    return Arrays.<Message>asList(new LeanCloudMessage(receivedMessage, userId, content));
  }

}
