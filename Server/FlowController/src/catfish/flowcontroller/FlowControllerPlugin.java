package catfish.flowcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.storage.MongoDBStorage;
import catfish.flowcontroller.storage.Storage;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.IStoppable;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.services.ServiceProvider;

public class FlowControllerPlugin implements IPlugin,  IQueueListener, IStoppable{
    private IServiceProvider serviceProvider;
    private WorkflowProvider workflowProvider;
    private String name;
    private String dataType;
    private String[] inputQueues;
    private Storage storage;
    private ArrayBlockingQueue<QueueMessager> messageQueue;
    private List<Thread> threads = new ArrayList<Thread>();
    private int executorCount = 2;

    public FlowControllerPlugin(int executorCount){
        this(
            executorCount,
            "FlowController",
            "application",
            DomainConsts.IN_QUEUES,
            DefaultWorkflowProvider.instance,
            new MongoDBStorage());
    }

    public FlowControllerPlugin(int executorCount, String name, String dataType, String[] inputQueues, WorkflowProvider workflowProvider, Storage storage){
        this.name = name;
        this.dataType = dataType;
        this.executorCount = executorCount>1 ? executorCount : 2;
        this.workflowProvider = workflowProvider;
        this.inputQueues = inputQueues;
        this.storage = storage;
        messageQueue = new ArrayBlockingQueue<QueueMessager>(executorCount);
    }

    @Override
    public void init(IServiceProvider sp) {
        serviceProvider = sp;
        IQueueService queueService = sp.getService(IQueueService.class);
        for(String queueName: inputQueues){
            IQueue queue = queueService.getQueue(queueName, this.name);
            queue.register(this);
        }
//        IQueue jsQueue = queueService.getQueue("JobStatusQueue","FlowController");
//        jsQueue.register(this);
//        IQueue jsv2Queue = queueService.getQueue("StatusQueueV2","FlowController");
//        jsv2Queue.register(this);

        ServiceProvider spInstance = (ServiceProvider)sp;
        spInstance.register(Storage.class, storage);

        for(int i=1; i<= executorCount; i++){
            String name ="WorkFlowExecutor - " + i;
            WorkFlowExecutor wfe = new WorkFlowExecutor(workflowProvider, serviceProvider, messageQueue, name, dataType);
            Thread thread = new Thread(wfe);
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public void onMessage(String message, String data) {
        Logger.get().info("FlowController: got message " + message);
        QueueMessager messager = QueueMessager.fromString(data);
        if(messager==null){
            Logger.get().warn("FlowController: messager is null");
            return;
        }
        String appId = messager.getAppId();
        if(appId ==null){
            Logger.get().warn("FlowController: appId is null");
            return;
        }

        handleMessage(messager);
    }

    private void handleMessage(QueueMessager messager){
        try {
            messageQueue.put(messager);
        } catch (InterruptedException e) {
            Logger.get().error("FlowController: put message exception",e);
        }
    }

    @Override
    public void stop(){
        if(threads==null ){
            Logger.get().warn("FlowControllerPlugin stop: no thread.");
            return;
        }
        Logger.get().info("FlowControllerPlugin stop begin, queue message count is " + messageQueue.size());
        for(int i=0; i< threads.size(); i++){
            Thread thread = threads.get(i);
            try {
                thread.interrupt();
                thread.join();
            } catch (Exception e) {
                Logger.get().error("FlowController: stop exception",e);
            }
        }
        threads = null;
        Logger.get().info("FlowControllerPlugin stopped, queue message count is " + messageQueue.size());
    }

}
