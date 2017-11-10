package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Receiver;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.SimpleMessageDefinition;

public class ReuploadFileUserOnlyMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
    int uploadStatus = Integer.parseInt((String) receivedMessage
      .get(MessageNotificationUtil.NotificationKeys.EXTRAINFO));
    int needReupload = (~uploadStatus & 7);

    List<String> userWordsList = MessageDatabaseApi
      .getReuploadFileSendWordsByUploadFileStatus(needReupload, appId);
    String userWords = combineWords(userWordsList);

    List<Message> managerList = new ArrayList<Message>();
    managerList.addAll(new SimpleMessageDefinition(Receiver.CUSTOMER, null, userWords)
      .apply(receivedMessage));
    return managerList;
  }

  private String combineWords(List<String> wordsList) {
    StringBuilder builder = new StringBuilder();
    for (String item : wordsList) {
      builder.append(item);
      builder.append(" ");
    }
    return builder.toString();
  }
}
