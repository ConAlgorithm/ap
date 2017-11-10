package catfish.services.queue.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.services.queue.QueueMessage;

public class Queue implements IQueue{
    private String queueName;
    private List<IQueueListener> defaultListeners = new ArrayList<IQueueListener>();
    private Map<String, List<IQueueListener>> msgMap = new HashMap<String, List<IQueueListener>>();
    
    public Queue(String queueName){
        this.queueName = queueName;
    }
    
    public <T> void postMessage(final String message, final T data){
        new Thread(new Runnable(){
            public void run(){
                sendMessage(message, data);
            }
        }).start();
    }
    
    public <T> void sendMessage(final String message, final T data){
        sendMessage(message, data, QueueConfig.QUEUE_PRIORITY_NORMAL, 0);
    }
    
    public <T> void sendMessage(final String message, final T data, String type){
        sendMessage(message, data, QueueConfig.QUEUE_PRIORITY_NORMAL, 0);
    }
    
    public <T> void sendMessage(final String message, T data, int priority, final int delaySeconds){
        Logger.get().info(this.queueName + " sendMessage " + message);
        final QueueMessage queueMessage = new QueueMessage();
        if(data instanceof QueueMessager){
            QueueMessager qm = (QueueMessager)data;
            queueMessage.setData(qm.toString());
            queueMessage.setMessage(qm.getJobName());
            //PersistenceQueueApi.writeMessager(targetQueueName, (QueueMessager)data, priority, delaySeconds, this.consumer);
        } else {
            queueMessage.setMessage(message);
            queueMessage.setData(data.toString());
            //QueueApi.writeMessage(targetQueueName, queueMessage);
        }
        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(delaySeconds);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                handleMessage(queueMessage);
            }
        }).start();
    }
    public void register(IQueueListener listener){
        if(listener==null){
            return;
        }
        if(defaultListeners.contains(listener)){
            return;
        }
        defaultListeners.add(listener);
    }
    
    public void register(String message, IQueueListener listener){
        if(message==null || listener==null){
            return;
        }
        
        if(!msgMap.containsKey(message)){
            msgMap.put(message, new ArrayList<IQueueListener>());
        }
        List<IQueueListener> list = msgMap.get(message);
        list.add(listener);
    }
    
    private List<IQueueListener> getListener(String message){
        List<IQueueListener> result =null;
        if(msgMap.containsKey(message)){
            result = msgMap.get(message);
        }
        if(defaultListeners.size()>0){
            if(result == null){
                result = defaultListeners;
            } else {
                List<IQueueListener> temp = new ArrayList<IQueueListener>();
                temp.addAll(result);
                temp.addAll(defaultListeners);
                result = temp;
            }
        }
        return result;
    }
    
    private synchronized void handleMessage(QueueMessage queueMessage){
        String message = queueMessage.getMessage();
        List<IQueueListener> listeners = getListener(message);
        if(listeners==null || listeners.size() ==0){
            return;
        }
        Logger.get().info(this.queueName + " queue listeners begin handle message " + message);
        //start thread to loop queue
        
        int threadCount = listeners.size();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        //for(IQueueListener listener: listeners){
        for(int i=0; i<listeners.size(); i++){
            IQueueListener listener = listeners.get(i);
            Runnable worker = new QueueMessageReceiver(queueMessage, listener);
            executor.execute(worker);
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
          ThreadUtils.sleepInMilliseconds(100);
        }
        Logger.get().info(this.queueName + " queue listeners handle end " + message);
    }
}

class QueueMessageReceiver implements Runnable{
    private QueueMessage queueMessage;
    private IQueueListener listener;
    public QueueMessageReceiver(QueueMessage queueMessage, IQueueListener listener){
        this.queueMessage = queueMessage;
        this.listener = listener;
    }
    @Override
    public void run() {
        if(this.queueMessage == null || listener ==null){
            return;
        }
        String data = queueMessage.getData();
        String message = queueMessage.getMessage();
        try {
            listener.onMessage(message, data);
        } catch (Exception e) {
            Logger.get().error("Listener for "+message+" exception", e);
        }
    }
}
