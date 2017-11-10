package catfish.manualjobarranger;

import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;
import com.aliyun.mns.model.Message;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MessageStealer implements Runnable {
  private static final int STEAL_DURATION = 12 * 60 * 60;   // 12 hours, max queue invisible time
  private List<String> queues;

  public MessageStealer(List<String> queues) {
    this.queues = queues;
  }

  @Override
  public void run() {
    while (true) {
      try {
        boolean succeed = false;
        for (String queue : queues) {
          succeed = succeed | steelMessage(queue);
        }
        if (!succeed) {
          ThreadUtils.sleepInSeconds(1);
        }
      } catch (RuntimeException e) {
        //Logger.get().error("Unexpected exception occurred.", e);
      }
    }
  }

  private static boolean steelMessage(String queue) {
    try {
      Message message = GeneralQueueApi.pop(queue);
      if (message == null) {
        return false;
      }
      Logger.get().debug(String.format(
          "Stole message %s %s", message.getMessageId(), message.getMessageBodyAsString()));
      String msgHandle = GeneralQueueApi.ChangeTimeout(queue, message.getReceiptHandle(), STEAL_DURATION);
	  message.setReceiptHandle(msgHandle);
      try {
    	  MessageEntity msgEntity = toEntity(queue, message);
    	  if(!checkAppId(msgEntity.getAppId())){
    	      Logger.get().info("The appId is illegal ,delete this message : " + message.toString());
    	      GeneralQueueApi.deleteMsg(queue, message.getReceiptHandle());    	      
    	      return false;
    	  }
          //如果是移动项目申请，且不需要该job，则退出
          if(ChinaMobileHandler.doNotNeedJob(msgEntity)) return true;
        ManualJobDatabaseApi.recordMessage(msgEntity);
      } catch (Exception e) {      // stolen, but failed to keep, so return it
        Logger.get().warn(String.format("Record message %s error.", message.getMessageBodyAsString()), e);
        GeneralQueueApi.ChangeTimeout(queue, message.getReceiptHandle(), 1);
        return false;
      }
      Logger.get().info("Recorded message " + message.getMessageId());
      return true;
    } catch (RuntimeException e) {
      //Logger.get().error("Unexpected exception occurred.", e);
      return false;
    }
  }

  private static MessageEntity toEntity(String queue, Message message) {
    MessageEntity entity = new MessageEntity();
    entity.setQueueName(queue);
    entity.setMessageBody(message.getMessageBodyAsString());
    entity.setMessageHandle(message.getReceiptHandle());

    @SuppressWarnings("serial")
    Map<String, String> messageMap = new Gson().fromJson(
        message.getMessageBodyAsString(),
        new TypeToken<Map<String, String>>() {}.getType());
    entity.setAppId(messageMap.get("appId"));
    entity.setJobName(messageMap.get("jobName"));

    long millis = message.getEnqueueTime().getTime();
    if (messageMap.containsKey("delaySeconds")) {
      millis += Long.parseLong(messageMap.get("delaySeconds")) * 1000;
    }
    entity.setJobGeneratedDate(new Date(millis));

    return entity;
  }
  
  private static boolean checkAppId(String appId){
      try {
          UUID.fromString(appId);        
    } catch (Exception e) {
        Logger.get().error("The appId is not a uuid !", e);
        return false;
    }
      return true;
  }
}
