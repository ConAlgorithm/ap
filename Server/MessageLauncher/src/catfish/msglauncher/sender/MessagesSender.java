package catfish.msglauncher.sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.StringUtil;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.ThreadUtils;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.Message;
import catfish.msglauncher.MessageTemplateManager;
import catfish.msglauncher.message.MessageLauncherMessage;
import catfish.msglauncher.message.ShortMessage;
import catfish.msglauncher.model.ShortMessageProvider;
import catfish.msglauncher.util.MessageType;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import freemarker.template.TemplateException;

public class MessagesSender {
	
  private static final Map<Class<? extends Message>, List<MessageSender>> MESSAGE_SENDER_MAPPING;
//      CollectionUtils.mapOf(
//    	  /*
//    	   * the following commented code is from project MessageNotification
//    	   * it is reserved for support other types of message in later modification
//    	   * please consider the message datagram over MQS if someone wants to support other message types
//    	   *
//          *WeChatText.class, Arrays.<MessageSender>asList(new WeChatTextSender()),
//          *WeChatImage.class, Arrays.<MessageSender>asList(new WeChatImageSender()),
//          *LeanCloudMessage.class, Arrays.<MessageSender>asList(new LeanCloudMessageSender()),
//           */
//          ShortMessage.class, Arrays.<MessageSender>asList(
//        		  //new MengWangMessageSender(),
//	              new HuYiShortMessageSender(),
//	              //new iShortMessageSender(),
//	              new JianZhouMessageSender(),
//	              new YunPianShortMessageSender()));
  static {
	  MESSAGE_SENDER_MAPPING = new HashMap<Class<? extends Message>, List<MessageSender>>();
	  MESSAGE_SENDER_MAPPING.put(ShortMessage.class, Arrays.<MessageSender>asList(
                  //new MengWangMessageSender(),
			      new DaHanSanTongShortMessageSender(),
	              new HuYiShortMessageSender(),
	              //new iShortMessageSender(),
	              new JianZhouMessageSender(),
	              new YunPianShortMessageSender(),
	              new DaHanSanTongCollectionSender()
	              ));
	  
  }
  
  //special 
  //private static final AliBaiChuanMessageSender aliBaiChuanMessageSender = new AliBaiChuanMessageSender();
  //private static final DaHanSanTongShortMessageSender daHanSanTongShortMessageSender = new DaHanSanTongShortMessageSender();
  public static boolean handle(String notificationLiteral, int retryingIntervalInSeconds) {
    Logger.get().info("Got notification literal " + notificationLiteral);
    
    Gson gson = new Gson();
    @SuppressWarnings("unchecked") Map<String, Object> notification =
        (Map<String, Object>) gson.fromJson(notificationLiteral, Map.class);
    
    //get message type
    String messageTypeString = (String) notification.get(MessageLauncherMessage.MESSAGE_TYPE);
    if(StringUtil.isBlank(messageTypeString)){
    	Logger.get().warn("message type is missing:"+notificationLiteral);
    	return false;
    }
    
    //get message body
    String messageString = (String) gson.toJson(notification.get(MessageLauncherMessage.MESSAGE));
    if(StringUtil.isBlank(messageString)){
    	Logger.get().warn("message body is missing:"+notificationLiteral);
    	return false;
    }
    
    Message message = null;
    //generate message with different message type 
    //SHORTVOICEMESSAGE
    if(MessageType.SHORTVOICEMESSAGE.toString().equals(messageTypeString)){
    	message = (ShortMessage) gson.fromJson(messageString, ShortMessage.class);
    	return sendVoiceMessage(message);
    }    
    
    //generate message with different message type
    //SHORTMESSAGE
    if(MessageType.SHORTMESSAGE.toString().equals(messageTypeString)){
    	message = (ShortMessage) gson.fromJson(messageString, ShortMessage.class);    	
    }
    //filter null message
    if(null == message){
    	Logger.get().warn("cannot convert message:"+notificationLiteral);
    	return false;
    }
    //generate information(such as templateId, content) from database configuration
    //prepareMessage(message);  去除从mongdb中获取短信模板信息
    //send generated message
    return send(Arrays.<Message>asList(message),
        retryingIntervalInSeconds);
  }
  
  
  public static boolean send(List<Message> messages, int retryingIntervalInSeconds) {
    boolean allSucceeded = true;
    
    for (Message message : messages) {
      boolean isFirst = true;
      boolean isSucceeded = false;
      for (MessageSender sender : generateSenders(message)) {
        try {
          if (!isFirst && retryingIntervalInSeconds > 0) {
            ThreadUtils.sleepInSeconds(retryingIntervalInSeconds);
          }
          isFirst = false;
          if(ShortMessage.class.isInstance(message)){
        	  ShortMessage smg = (ShortMessage)message;
        	  if(StringUtils.isNullOrWhiteSpaces(smg.getMobile()) || StringUtils.isNullOrWhiteSpaces(smg.getContent())){
        		  throw new RuntimeException(String.format("Error empty mobile or content, message: %s",message.toString()));
        	  }
          }
          
          sender.send(message);
          isSucceeded = true;
          break;
        } catch (RuntimeException e) {
          Logger.get().error("error to send:"+message, e);
          if(e.toString().contains("Timeout waiting for connection")){ //TODO: refactor this code.
        	  throw new RuntimeException(e);  
          }          
        }
      }

      if (isSucceeded) {
        Logger.get().info(String.format("Sent %s.", message));
      } else {
        allSucceeded = false;
        Logger.get().error(String.format("Failed to send %s.", message));
      }
    }

    return allSucceeded;
  }
  
  public static boolean sendVoiceMessage(Message message) {
	    boolean isSucceeded = false;        
	    try{
		    DaHanSanTongVoiceMessageSender daHanSanTongVoiceMessageSender = new DaHanSanTongVoiceMessageSender();
	  	    ShortMessage smg = (ShortMessage)message;
	  	    if(StringUtils.isNullOrWhiteSpaces(smg.getMobile()) || StringUtils.isNullOrWhiteSpaces(smg.getContent())){
	  		   throw new RuntimeException(String.format("Error empty mobile or content, message: %s",message.toString()));
  	    
	  	    }
	  	    daHanSanTongVoiceMessageSender.send(message);
	  	    isSucceeded = true;
	  	    Logger.get().info(String.format("Sent %s.", message));
	    }catch (RuntimeException e) {
	          Logger.get().error("error to send:"+message, e);
	          if(e.toString().contains("Timeout waiting for connection")){ //TODO: refactor this code.
	        	  throw new RuntimeException(e);  
	          }          
	    }

	    return isSucceeded;
  }  

  private static List<MessageSender> generateSenders(Message message) {
    List<MessageSender> availableSenders = MESSAGE_SENDER_MAPPING.get(message.getClass());
    List<MessageSender> selectedSenders = new ArrayList<>();
	ShortMessage smg = (ShortMessage)message;
    if(ShortMessage.class.isInstance(message)){
    	/*//短信验证码
    	if(null != smg.getTemplateCode() && null != smg.getContentJson() && DynamicConfig.readAsBool("daHanSanTongShortMsgSwitchOn") == true){
    		selectedSenders.add(daHanSanTongShortMessageSender);
    	}*/
    	// select specific sms provider given by the Enum
    	ShortMessageProvider provider = ShortMessageProvider.getProviderByValue(smg.getProvider());
    	if (provider!= null) {
    		Class<? extends MessageSender> providerClass = provider.getProviderClass();
    		for (MessageSender sender : availableSenders) {
    			if (providerClass.isInstance(sender)) {
    				selectedSenders.add(sender);
    				break;
    			}
    		}
    	}
    }
    int leftRetryTimes = Configuration.getMaxRetries() - selectedSenders.size();
    for (int startIndex = 0, i = 0; i < leftRetryTimes; i++) {
		if(smg.getProvider() == ShortMessageProvider.DaHanSanTong.getValue() 
    			&& availableSenders.get((startIndex + i) % availableSenders.size()) instanceof DaHanSanTongShortMessageSender
    			){
    		continue;
    	}
    	selectedSenders.add(availableSenders.get((startIndex + i) % availableSenders.size()));
    }
    return selectedSenders;
  }
  
  private static void prepareMessage(Message message){
	  if(message instanceof ShortMessage){
		  ShortMessage sm = (ShortMessage) message;
		  //null templateCode is illegal
		  if(null == sm.getTemplateCode()){
			  return;
		  }
		  
		  //existed templateId is not necessary
		  if(null != sm.getTemplateCode() && null != sm.getTemplateId()){
			  return;
		  }
		  
		  try {
			sm.setContent(MessageTemplateManager.generateContent(sm.getTemplateCode(), sm.getContentJson()));
			sm.setTemplateId(MessageTemplateManager.generateTemplateId(sm.getTemplateCode()));
		  } catch (JsonSyntaxException | IOException | TemplateException e) {
			  // TODO Auto-generated catch block
			  Logger.get().error("prepareMessage ERROR",e);
		  }
	  }
  }
}
