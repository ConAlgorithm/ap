package catfish.notification.messagegeneration.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.base.StartupConfig;
import catfish.base.business.dao.ActivityLuckDrawRecordDao;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Product;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;

public class LuckDrawMsgDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
    String url = StartupConfig.get("catfish.luckdraw.url");
    String recordId = ActivityLuckDrawRecordDao.getLuckDrawRecordByAppId(appId);
    String customerContent = String.format(
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER,
        Channel.WECHAT, Product.POS), url, recordId);

    return Arrays.<Message> asList(
      MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.CUSTOMER, customerContent));

  }
}
