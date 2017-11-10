package catfish.services.queue.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueService;
import catfish.services.coordination.CoordinationService;
import catfish.services.queue.test.Queue;

public class QueueService implements IQueueService, Runnable  {
public static final String DefaultQueueName = "CatfishServerQueue";
    
    private Map<String, Queue> queueMap = new HashMap<String, Queue>();
    private CoordinationService coordinationService;
    
    public QueueService(){
        this(null);
    }
    
    public QueueService(CoordinationService coordinationService){
        this.coordinationService = coordinationService;
    }
    
    public IQueue getQueue(String queueName){
        return getQueue(queueName, "catfishServer");
    }
    
    public IQueue getQueue(String queueName,String consumer){
        if(!queueMap.containsKey(queueName)){
            queueMap.put(queueName, new Queue(queueName));
        }
        return queueMap.get(queueName);
    }
    
    public void run(){
//        int threadCount = queueMap.size();
//        if(threadCount ==0){
//            return;
//        }
//        
//        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
//        for(Queue queue: queueMap.values()){
//            executor.execute(queue);
//        }
//        executor.shutdown();
    }
}
