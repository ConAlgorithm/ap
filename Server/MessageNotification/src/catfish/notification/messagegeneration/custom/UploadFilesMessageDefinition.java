package catfish.notification.messagegeneration.custom;

import grasscarp.application.model.POSApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catfish.base.DescriptionParser;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.common.WordsTemplate;
import catfish.base.business.util.EnumUtils;
import catfish.base.ons.MessageNotificationUtil;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.common.Channel;
import catfish.notification.common.ImageEnum;
import catfish.notification.common.Receiver;
import catfish.notification.common.SmsSourceEnums;
import catfish.notification.config.ResourceConfig;
import catfish.notification.config.ResourceProperties;
import catfish.notification.message.SmsMessage;
import catfish.notification.messagegeneration.JDTestApi;
import catfish.notification.messagegeneration.MessageDataRequestApi;
import catfish.notification.messagegeneration.MessageDatabaseApi;
import catfish.notification.messagegeneration.MessageDefinition;
import catfish.notification.messagegeneration.MessagesFactory;
import catfish.notification.sender.CommonServiceSmsSender;

public class UploadFilesMessageDefinition implements MessageDefinition {

  @Override
  public List<Message> apply(Map<String, ?> receivedMessage) {
    List<Message> messageList = new ArrayList<Message>();

    String appId = (String) receivedMessage.get(MessageNotificationUtil.NotificationKeys.APP_ID);
    String userName = MessageDatabaseApi.getCustomerNameBy(appId);
    int uploadStatus = Integer.parseInt((String) receivedMessage
      .get(MessageNotificationUtil.NotificationKeys.EXTRAINFO));

    String merchantTextMessage = null, merchantSMSTextMessage = null;
    boolean isReupload = MessageDatabaseApi.isReupload(uploadStatus);
    boolean isHeadOrIdOrBankCardPhoto = MessageDatabaseApi.isHeadOrIdOrBankCardNeeded(uploadStatus);
    boolean isIOUNeeded = MessageDatabaseApi.isIOUNeeded(uploadStatus);
    boolean isBuckleNeeded = MessageDatabaseApi.isBuckleNeeded(uploadStatus);
    String merchantUpload, merchantIOUBuckleWC, merchantIOUWC, merchantIOUBuckleSM, merchantIOUSM;
    if(isReupload) {
      merchantUpload = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName() + "1", Receiver.S1, Channel.WECHAT);
      merchantIOUBuckleWC = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName() + "5", Receiver.S1, Channel.WECHAT);
      merchantIOUWC = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName() + "3", Receiver.S1, Channel.WECHAT);
      merchantIOUBuckleSM = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName() + "3", Receiver.S1, Channel.SHORTMESSAGE);
      merchantIOUSM = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName() + "1", Receiver.S1, Channel.SHORTMESSAGE);
    } else {
      merchantUpload = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName(), Receiver.S1, Channel.WECHAT);
      merchantIOUBuckleWC = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName() + "4", Receiver.S1, Channel.WECHAT);
      merchantIOUWC = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName() + "2", Receiver.S1, Channel.WECHAT);
      merchantIOUBuckleSM = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName() + "2", Receiver.S1, Channel.SHORTMESSAGE);
      merchantIOUSM = ResourceConfig.getResourceByName(
        this.getClass().getSimpleName(), Receiver.S1, Channel.SHORTMESSAGE);
    }
    
    if (isHeadOrIdOrBankCardPhoto) {
      
      List<String> userWordsList = MessageDatabaseApi.getHeadOrIdOrBankCardWords(uploadStatus);
      merchantTextMessage = String.format(merchantUpload, userName, 
        MessageDatabaseApi.toSpaceSeparatedString(userWordsList));
      
    } else {
      if (isIOUNeeded && isBuckleNeeded) {
        
        merchantTextMessage = String.format(merchantIOUBuckleWC, userName, 
          String.format(Configuration.getGuidedIouUrlFormat(), appId));
        merchantSMSTextMessage = String.format(merchantIOUBuckleSM,userName, 
          String.format(Configuration.getGuidedIouUrlFormat(), appId));

      } else if (isIOUNeeded && !isBuckleNeeded) {
        
        merchantTextMessage = String.format(merchantIOUWC, userName, 
          String.format(Configuration.getGuidedIouUrlFormat(), appId));
        merchantSMSTextMessage = String.format(merchantIOUSM, userName, 
          String.format(Configuration.getGuidedIouUrlFormat(), appId));
        
      } else if (!isIOUNeeded && isBuckleNeeded) {
        
        merchantTextMessage = String.format(merchantUpload, userName, 
          WordsTemplate.BuckleWords);
        
      } else {
        Logger.get().warn("Invalid Message : " + receivedMessage);
      }
    }

    Receiver receiver = JDTestApi.getReceiver(appId);
    if(receiver == null) return null;
    
    messageList.add(
      MessagesFactory.createMessage(receivedMessage, Channel.APP, Receiver.CUSTOMER, getMsgByAppAction(uploadStatus)));
    
    if (!isHeadOrIdOrBankCardPhoto || isReupload) {
      messageList.add(
        MessagesFactory.createMessage(receivedMessage, Channel.WECHAT, receiver, merchantTextMessage));
    }
    if (isIOUNeeded) {
      String mobile = "";     
	  boolean salesAdminServiceSwitchOn = Boolean.FALSE;
	  salesAdminServiceSwitchOn = DynamicConfig.readAsBool("salesAdminServiceSwitchOn");// 获取smsa开关
	  boolean isposApp = MessageDatabaseApi.isPOSApplication(appId);//是否是pos用户申请
		
	  switch (receiver) {	  
	  case S1:
		mobile = MessageDatabaseApi.getMerchantMobileBy(appId);
	    break;
	  case D1:
		if (salesAdminServiceSwitchOn && isposApp) {
		  mobile = MessageDataRequestApi.getD1MobileByAppId(appId);
		  } else {
		    mobile = MessageDatabaseApi.getD1MobileBy(appId);
		  }
		break;
	  default:
		break;
	  }
      
      String sign = ResourceProperties.getProperty("POS_SHORTMESSAGE_SIGN");
        
      Logger.get().info("Send short message to :" + mobile+ "-------" + merchantSMSTextMessage);
      SmsMessage message = 
        new SmsMessage(receivedMessage, mobile, merchantSMSTextMessage, sign, SmsSourceEnums.PSL_TAG.getValue());
    	    
      CommonServiceSmsSender.sendSingleTextMessage(message);
    	
      if(!isReupload){
        messageList.add(
          MessagesFactory.createMessage(receivedMessage, Channel.WECHATIMAGE, receiver, ImageEnum.S1_IOU.toString()));
      }
    }
    return messageList;
  }

  public String getMsgByAppAction(int action) {
    String uploadWords = "";
    final String REUPLOAD_WORDS = "重传";
    final String UPLOAD_WORDS = "上传";
    boolean reuploadFlag = (UploadFileStatus.ReUploaded.getValue() & action) != 0;
    int mask = 1;
    
    for(int i=0; i<32; i++)
    {
        if ((action & mask) > 0 && mask != UploadFileStatus.ReUploaded.getValue()) {
            UploadFileStatus us = null;
            try {
                us = EnumUtils.parse(UploadFileStatus.class, mask); 
            } catch (Exception e) {
                Logger.get().warn("can not parse enum value", e);
            }
            
            if(us != null) {
                uploadWords += ", "
                        + String.format(
                          ResourceConfig.getResourceByName(this.getClass().getSimpleName(), Receiver.CUSTOMER, Channel.APP),  
                          reuploadFlag ? REUPLOAD_WORDS : UPLOAD_WORDS,
                            DescriptionParser.getDescription(us));
            }
        }
        mask <<= 1;
    }

    return uploadWords.substring(2);
  }

}
