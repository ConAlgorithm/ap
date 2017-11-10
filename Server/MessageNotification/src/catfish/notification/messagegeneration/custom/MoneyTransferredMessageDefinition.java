package catfish.notification.messagegeneration.custom;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.common.Channel;
import catfish.notification.common.Product;
import catfish.notification.common.Receiver;
import catfish.notification.common.SmsSourceEnums;
import catfish.notification.config.ResourceConfig;
import catfish.notification.message.ShortMessage;
import catfish.notification.message.SmsMessage;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;
import catfish.notification.sender.CommonServiceSmsSender;

public class MoneyTransferredMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage.get(NotificationKeys.APP_ID);
    String cid = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.EXTRAINFO);
    
    String wcCustomerContent = 
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Channel.WECHAT, Product.POS);
    String wcShortMsgContent = 
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Channel.SHORTMESSAGE, Product.POS);
    String wcPaybackContent = String.format(
      ResourceConfig.getResourceByName(this.getClass().getSimpleName() + "1", Receiver.CUSTOMER, Channel.WECHAT, Product.POS),
      Configuration.getWeiXinComsumerUrlPrefix(), cid);
    String pdlCustomerContent = 
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Channel.APP, Product.PDL);
    String posCustomerContent = 
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Channel.APP, Product.POS);
    String paybackContent = 
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Channel.APP);
    String shortMsgContent =
      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Channel.SHORTMESSAGE);
    
    ShortMessage sms = (ShortMessage)
        MessagesFactory.createMessage(receivedMessage, Channel.SHORTMESSAGE, Receiver.CUSTOMER, shortMsgContent);
    SmsMessage message = SmsMessage.fromShortMessage(sms);
    message.setSource(SmsSourceEnums.PSL_TAG.getValue());
    List<Message> result;
    
    if (MessageDatabaseApi.isPDLApplication(appId)) {
      message.setSign(SmsSourceEnums.DIRECT.getValue());
      result = Arrays.<Message> asList(
        MessagesFactory.createMessage(receivedMessage, Channel.APP, Receiver.CUSTOMER, pdlCustomerContent),
        MessagesFactory.createMessage(receivedMessage, Channel.APP, Receiver.CUSTOMER, paybackContent));
      
    } else if (MessageDatabaseApi.isApplicationFromAgentApp(appId)) {
      result = Arrays.<Message> asList(
        MessagesFactory.createMessage(receivedMessage, Channel.APP, Receiver.CUSTOMER, posCustomerContent),
        MessagesFactory.createMessage(receivedMessage, Channel.APP, Receiver.CUSTOMER, paybackContent));
    } else {
      message.setContent(wcShortMsgContent);
      result = Arrays.<Message> asList(
        MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.CUSTOMER, wcCustomerContent),
        MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, Receiver.CUSTOMER, wcPaybackContent));
    }

    result.addAll(CommonServiceSmsSender.sendSingleTextMessage(message));
    return result;
  }
}
