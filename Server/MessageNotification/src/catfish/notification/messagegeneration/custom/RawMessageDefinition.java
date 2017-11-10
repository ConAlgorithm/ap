package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Receiver;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.SimpleMessageDefinition;

public class RawMessageDefinition implements MessageDefinition {

  public List<Message> apply(Map<String, ?> receivedMessage) {
    String userWords = (String) receivedMessage
      .get(MessageNotificationUtil.NotificationKeys.EXTRAINFO);
    List<Message> managerList = new ArrayList<Message>();
    
    managerList.addAll(new SimpleMessageDefinition(Receiver.CUSTOMER, null, userWords)
      .apply(receivedMessage));
    return managerList;
  }

}
