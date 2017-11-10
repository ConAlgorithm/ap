package catfish.manualjobarranger;


import catfish.base.Logger;
import catfish.base.queue.QueueConfig;

import com.aliyun.mns.model.Message;

public class GeneralQueueApi {

  public static Message pop(String queueName) {
    return QueueConfig.getExistingQueue(queueName).popMessage();
  }

  public static String ChangeTimeout(String queueName, String messageHandle, int timeoutInSeconds) {
    return QueueConfig
        .getExistingQueue(queueName)
        .changeMessageVisibilityTimeout(messageHandle, timeoutInSeconds);
  }

  public static Message sendMsg(String queueName, String msg){
    Message message = new Message();
    message.setMessageBody(msg);
    return QueueConfig.getExistingQueue(queueName).putMessage(message);
  }
  
  public static void deleteMsg(String queueName, String messageHandle){
	  try{
		  QueueConfig.getExistingQueue(queueName).deleteMessage(messageHandle);
	  }catch(Exception e){
		  Logger.get().error("Delete message error: " + messageHandle, e);
	  }
  }
}
