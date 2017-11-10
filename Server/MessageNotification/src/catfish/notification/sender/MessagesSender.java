package catfish.notification.sender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.StringUtils;
import catfish.base.ThreadUtils;
import catfish.base.queue.QueueApi;
import catfish.msglauncher.message.MessageLauncherMessage;
import catfish.msglauncher.util.MessageLauncherUtil;
import catfish.msglauncher.util.MessageType;
import catfish.notification.Configuration;
import catfish.notification.Message;
import catfish.notification.MessageConfig;
import catfish.notification.MessageConfig.NotificationKeys;
import catfish.notification.message.LeanCloudMessage;
import catfish.notification.message.ShortMessage;
import catfish.notification.message.WeChatImage;
import catfish.notification.message.WeChatText;
import catfish.notification.messagegeneration.MessageDefinition;

public class MessagesSender {

  @SuppressWarnings("serial")
  private static Set<String>  highPrioritySet = new HashSet<String>() {
    {
      add("SendValidationCode");
      add("SendValidationCodeFromApp");
      add("SendAuthValidationCodeFromApp");
      add("SendS1AuthValidationCodeFromMCApp");
      add("SendUserAuthValidationCodeFromMCApp");
      add("SendAuthValidationVoiceCodeFromApp");
    }
  };
  
  @SuppressWarnings("serial")
  private static Set<String>  batchPrioritySet = new HashSet<String>() {
	    {
	      add("SendDailyShortMessage");
	      add("RawOverdueMessage");  //CL-572
	    }
	  };

  private static final Map<Class<? extends Message>, List<MessageSender>> MESSAGE_SENDER_MAPPING =
      CollectionUtils.mapOf(
          WeChatText.class, Arrays.<MessageSender>asList(new WeChatTextSender()),
          WeChatImage.class, Arrays.<MessageSender>asList(new WeChatImageSender()),
          LeanCloudMessage.class, Arrays.<MessageSender>asList(new LeanCloudMessageSender()));

  public static boolean handle(String notificationLiteral, int retryingIntervalInSeconds) {
    Logger.get().info("Got notification literal " + notificationLiteral);
    @SuppressWarnings("unchecked") Map<String, Object> notification =
        (Map<String, Object>) new Gson().fromJson(notificationLiteral, Map.class);

    int priority = 10;
    String queueName = StartupConfig.get("catfish.normal.priority.msg.queue.name", MessageLauncherUtil.MESSAGE_LAUNCHER_QUEUE_NAME);;
    Logger.get().info("NotificationName=" + notification.get(NotificationKeys.NOTIFICATION_NAME));
    if (highPrioritySet.contains(notification.get(NotificationKeys.NOTIFICATION_NAME))) {
      priority = 1;
      queueName = StartupConfig.get("catfish.high.priority.msg.queue.name", "HighPriorityMessageLauncher");;
    }
    if (batchPrioritySet.contains(notification.get(NotificationKeys.NOTIFICATION_NAME))) {
    	queueName = StartupConfig.get("catfish.batch.priority.msg.queue.name", "BatchMessageLauncher");
    }

    String notificationName=(String) notification.get(NotificationKeys.NOTIFICATION_NAME);
    MessageDefinition messageDefinition=MessageConfig.getDefinitionBy(notificationName);
    Logger.get().info("MessageDefinition name=" + messageDefinition.getClass().getSimpleName());
    List<Message> messages=messageDefinition.apply(notification);
    return send(messages,retryingIntervalInSeconds, queueName, priority);
  }

    public static boolean send(List<Message> messages, int retryingIntervalInSeconds) {
        return send(messages, retryingIntervalInSeconds, MessageLauncherUtil.MESSAGE_LAUNCHER_QUEUE_NAME, 1);
    }


  public static boolean send(List<Message> messages, int retryingIntervalInSeconds, String queueName, int priority) {
    boolean allSucceeded = true;
    Gson gson = new Gson();
    for (Message message : messages) {
    	if(ShortMessage.class.isInstance(message)){
      	  ShortMessage smg = (ShortMessage)message;
      	  if(StringUtils.isNullOrWhiteSpaces(smg.getMobile()) || StringUtils.isNullOrWhiteSpaces(smg.getContent())){
      		  Logger.get().warn("Short Message has no mobile or content:"+gson.toJson(message));
      	  }else{
      		  String notificationName = (String)((Map<String, ?>)smg.getReceivedMessage()).get("NotificationName");
      		  MessageLauncherMessage mlMessage = new MessageLauncherMessage();      		  
      		  if(notificationName.contains("SendAuthValidationVoiceCodeFromApp")){
          		  mlMessage.setMessageType(MessageType.SHORTVOICEMESSAGE);      			  
      		  }else{
          		  mlMessage.setMessageType(MessageType.SHORTMESSAGE);      			  
      		  }

      		  mlMessage.setMessage(gson.fromJson(gson.toJson(smg),catfish.msglauncher.message.ShortMessage.class));
      		  String mlMessageString = gson.toJson(mlMessage);
      		  QueueApi.writeMessage(queueName, mlMessageString, priority, 0);
      		  continue;
      	  }
        }
    	
      boolean isFirst = true;
      boolean isSucceeded = false;
      for (MessageSender sender : generateSenders(message)) {
        try {
          if (!isFirst && retryingIntervalInSeconds > 0) {
            ThreadUtils.sleepInSeconds(retryingIntervalInSeconds);
          }
          isFirst = false;
          
          sender.send(message);
          isSucceeded = true;
          break;
         }catch(Exception e1){
            Logger.get().warn("send failed, "+message, e1.fillInStackTrace());
        }
      }

      if (isSucceeded) {
        Logger.get().info(String.format("Sent %s.", message));
      } else {
        allSucceeded = false;
        Logger.get().warn(String.format("Failed to send %s.", message));
      }
    }

    return allSucceeded;
  }

  private static List<MessageSender> generateSenders(Message message) {
    List<MessageSender> availableSenders = MESSAGE_SENDER_MAPPING.get(message.getClass());
    List<MessageSender> selectedSenders = new ArrayList<>();

    for (int startIndex = 0, i = 0; i < Configuration.getMaxRetries(); i++) {
      selectedSenders.add(availableSenders.get((startIndex + i) % availableSenders.size()));
    }
    return selectedSenders;
  }
}
