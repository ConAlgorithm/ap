package catfish.notification.receiver;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.ons.MessageNotificationUtil;
import catfish.base.queue.QueueApi;
import catfish.notification.Configuration;
import catfish.notification.sender.MessagesSender;

import com.google.gson.Gson;

public class MqsReceiver implements Runnable{
    public static void trigger(){
        MqsReceiver mqsReceiver = new MqsReceiver(MessageNotificationUtil.MessageQueue);
        new Thread(mqsReceiver).start();
        
        MqsReceiver mqsAppReceiver = new MqsReceiver(Configuration.getProductPrefix() + MessageNotificationUtil.MessageQueue);
        new Thread(mqsAppReceiver).start();
    }

    public static void trigger(String queueName) {
        MqsReceiver mqsReceiver = new MqsReceiver(queueName);
        new Thread(mqsReceiver).start();

        MqsReceiver mqsAppReceiver = new MqsReceiver(Configuration.getProductPrefix() + queueName);
        new Thread(mqsAppReceiver).start();
    }
    
    private String queueName;
    private MqsReceiver(String queueName){
        this.queueName = queueName;
    }
    
    public void run(){
        try{
            //QueueApi.ensureQueueExist(this.queueName, 30L);
        }catch(Exception ex){
            Logger.get().warn("MqsReceiver: ensureQueueExist error", ex);
        }

        while (true) {
            try{
                @SuppressWarnings("unchecked") Map<String, Object> notification = QueueApi.consumeMessage(Map.class, this.queueName);
                if(notification == null){
                    Logger.get().warn("MqsReceiver: messageData is null from "+ this.queueName);
                    continue;
                }
                
                String jsonStr = new Gson().toJson(notification);
                Logger.get().info("Got MQS message: " + jsonStr);
                MessagesSender.handle(jsonStr, 0);
            }catch(Exception ex){
                Logger.get().warn("MqsReceiver: handle mqs notification error", ex);
            }
        }
    }
}
