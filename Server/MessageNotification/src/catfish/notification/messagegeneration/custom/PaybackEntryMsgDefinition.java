package catfish.notification.messagegeneration.custom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
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
import catfish.notification.messagegeneration.SimpleMessageDefinition;
import catfish.notification.sender.CommonServiceSmsSender;

public class PaybackEntryMsgDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
	
	String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
	String mobile = (String) MessageDatabaseApi.getCustomerMobileBy(appId);
    String extra = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.EXTRAINFO);
    String[] extras = extra.split("&");
    String tagIsDelayed = extras[0];
    String amount = extras[1];
    String paybackAmount = extras[2];

    String paybackContent = "";
    String paybackSmsContent = "";

    if(MessageDatabaseApi.isPDLApplication(appId)){
    	if (tagIsDelayed.equals("NO")) {
    	    paybackContent = String.format(
    	      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER), amount);
    	}else {
    	paybackContent = String.format(
    	  ResourceConfig.getResourceByName(this.getClass().getSimpleName() + "1", 
    	  Receiver.CUSTOMER), amount, paybackAmount);    	    	   	
      }
    }else {
    	if (tagIsDelayed.equals("NO")) {
    		paybackSmsContent = String.format(
    	      ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER), amount);
    	  }
        else {
    	BigDecimal penalty = InstallmentApplicationApi.getAppModel(appId).getPenaltyFee();
    	paybackSmsContent = String.format(
		  ResourceConfig.getResourceByName(this.getClass().getSimpleName() + "1", Receiver.CUSTOMER, Product.POS),
		  amount, paybackAmount, penalty);
    	}
    	        
        String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
        
        Logger.get().info("Send short message to :" + mobile+ "-------" + paybackSmsContent);
        SmsMessage message = 
    	        new SmsMessage(receivedMessage, mobile, paybackSmsContent, sign, SmsSourceEnums.PSL_TAG.getValue());
    	    
    	CommonServiceSmsSender.sendSingleTextMessage(message);    	      
    }
    
    List<Message> messages = new ArrayList<Message>();
    messages.addAll(
      new SimpleMessageDefinition(Receiver.CUSTOMER, null, paybackContent).apply(receivedMessage));
    messages.add(
      MessagesFactory.createMessage(receivedMessage, Channel.SHORTMESSAGE, Receiver.CUSTOMER, paybackContent));
    return messages;   
  }
}