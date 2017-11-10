package catfish.notification.messagegeneration.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.dao.UploadFileApi;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.ImageEnum;
import catfish.notification.common.Receiver;
import catfish.notification.config.ResourceConfig;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;
import catfish.notification.messagegeneration.SimpleMessageDefinition;

public class ReuploadFileMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
    String userName = MessageDatabaseApi.getCustomerNameBy(appId);
    int needReupload = Integer.parseInt((String)receivedMessage.get(MessageNotificationUtil.NotificationKeys.EXTRAINFO));
    List<String> reminderWordsList = UploadFileApi.getWeixinSendWordsForMerchantUserReminderByUploadFileStatus(needReupload);
    String reminderWords = UploadFileApi.toSpaceSeparatedString(reminderWordsList);
    List<String> userWordsList = UploadFileApi.getReuploadFileSendWordsByUploadFileStatus(needReupload, appId);
    String userWords = UploadFileApi.toSpaceSeparatedString(userWordsList);
    List<Message> managerList = new ArrayList<Message>();
    
    managerList.addAll((new SimpleMessageDefinition(Receiver.CUSTOMER, null, userWords)).apply(receivedMessage));
    
    managerList.add(
      MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.S1, 
      String.format(ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.S1, Channel.WECHAT), 
        userName, reminderWords)));
    
    if( (needReupload & UploadFileStatus.IOUUploaded.getValue()) > 0)
    {
      if(!MessageDatabaseApi.isApplicationFromAgentApp(appId)) {
        managerList.add(
          MessagesFactory.createMessage(receivedMessage, Channel.WECHATIMAGE, Receiver.CUSTOMER, ImageEnum.CUSTOMER_IOU.toString()));
      }
    	managerList.add(
    	  MessagesFactory.createMessage(receivedMessage, Channel.WECHATIMAGE, Receiver.S1, ImageEnum.S1_IOU.toString()));
    }
    return managerList; 
  }
}
