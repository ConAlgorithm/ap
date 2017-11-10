package catfish.notification.messagegeneration.custom;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.Product;
import catfish.notification.common.Receiver;
import catfish.notification.common.SmsSourceEnums;
import catfish.notification.config.ResourceConfig;
import catfish.notification.config.ResourceProperties;
import catfish.notification.message.SmsMessage;
import catfish.notification.messagegeneration.InstallmentApplicationApi;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;
import catfish.notification.sender.CommonServiceSmsSender;

public class DsfWithholdingMsgDefinition implements MessageDefinition {
  private String message;
  private final String DSFWITHHOLDINGFAILONCE = "FailOnce";
  private final String DSFWITHHOLDINGFAILTWICE = "FailTwice";
  private final String DSFWITHHOLDINGOVERDUESUCCESS = "OverdueSuccess";
  private final String DSFWITHHOLDINGSUCCESS = "Success";

  public DsfWithholdingMsgDefinition(String message) {
    this.message = message;
  }

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    String appId = (String) receivedMessage
      .get(MessageNotificationUtil.NotificationKeys.APP_ID);
    String mobile = (String) MessageDatabaseApi.getCustomerMobileBy(appId);
    String userName = (String) receivedMessage.get("UserName");
    String appserialperiod = (String) receivedMessage.get("Appserialperiod");
    String amount = (String) receivedMessage.get("Amount");

    InstallmentApplicationObject application = MessageDatabaseApi
      .getInstallmentApplicationById(appId);
    String customerContent, content;
    if (MessageDatabaseApi.isPDLApplication(appId)) {
      content = ResourceConfig.getResourceByName(this.getClass().getSimpleName() + "_"
        + message, Receiver.CUSTOMER, Channel.SHORTMESSAGE, Product.PDL);
      int month = application.FirstPaybackDate.getMonth();
      switch (message) {
        case DSFWITHHOLDINGFAILONCE:
        case DSFWITHHOLDINGFAILTWICE:
          double penalty = application.Principal.doubleValue() * 0.007;
          penalty = penalty < 5 ? 5 : penalty;
          customerContent = String.format(content, userName, month, amount, penalty);
          break;
        case DSFWITHHOLDINGSUCCESS:
          customerContent = String.format(content, userName, month, amount);
          break;
        case DSFWITHHOLDINGOVERDUESUCCESS:
          customerContent = String.format(content, userName, month,
            (String) receivedMessage.get("TotalAmount"), amount,
            (String) receivedMessage.get("Fine"));
          break;
        default:
          customerContent = "";
      }
      return Arrays.<Message> asList(MessagesFactory.createMessage(receivedMessage,
    	Channel.SHORTMESSAGE, Receiver.CUSTOMER, customerContent));
    } 
    else {
      content = ResourceConfig.getResourceByName(this.getClass().getSimpleName() + "_"
        + message, Receiver.CUSTOMER, Channel.SHORTMESSAGE, Product.POS);
      if (message == DSFWITHHOLDINGOVERDUESUCCESS) {
        customerContent = String.format(content, userName, appserialperiod,
          (String) receivedMessage.get("TotalAmount"), amount,
          (String) receivedMessage.get("Fine"));
      } else if(message == DSFWITHHOLDINGFAILONCE || message == DSFWITHHOLDINGFAILTWICE){
    	  BigDecimal penalty = InstallmentApplicationApi.getAppModel(appId).getPenaltyFee();
    	  customerContent = String.format(content, userName, appserialperiod, amount, penalty);
      }else {
        customerContent = String.format(content, userName, appserialperiod, amount);
      }
      
      String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
      Logger.get().info("Send short message to :" + mobile+ "-------" + customerContent);

      SmsMessage message = 
  	        new SmsMessage(receivedMessage, mobile, customerContent, sign, SmsSourceEnums.PSL_TAG.getValue());
  	    
  	  return CommonServiceSmsSender.sendSingleTextMessage(message);      
    }
  }
}
