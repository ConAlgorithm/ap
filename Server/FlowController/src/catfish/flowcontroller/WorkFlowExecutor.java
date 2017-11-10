package catfish.flowcontroller;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.storage.Storage;
import catfish.framework.IServiceProvider;
import catfish.services.coordination.CoordinationService;
import catfish.services.coordination.DataCoordinator;

public class WorkFlowExecutor implements Runnable{
    private WorkflowProvider workflowProvider;
    private IServiceProvider serviceProvider;
    private DataCoordinator dataCoordinator;
    private BlockingQueue<QueueMessager> messageQueue;
    private String name;
    
    public WorkFlowExecutor(WorkflowProvider workflowProvider, 
            IServiceProvider serviceProvider, 
            BlockingQueue<QueueMessager> messageQueue, 
            String name,
            String dataType){
        this.workflowProvider = workflowProvider;
        this.serviceProvider = serviceProvider;
        this.messageQueue = messageQueue;
        this.name = name;
        
        CoordinationService cs = serviceProvider.getService(CoordinationService.class);
        dataCoordinator = cs.registerData(dataType);
    }
    
    public void run(){
        while(true){
            try {
                QueueMessager messager = this.messageQueue.take();
                Logger.get().info(String.format("%s got message %s@%s", this.name, messager.getJobName(), messager.getAppId()));
                handleMessage(messager);
            } catch(InterruptedException ex){
                Logger.get().warn("return from WorkFlowExecutor InterruptedException", ex);
                return;
            }catch (Exception e) {
                Logger.get().error("WorkFlowExecutor exception", e);
            }
        }
    }
    
    private void handleMessage(QueueMessager messager){
        String appId = messager.getAppId();
        try
        {
            dataCoordinator.lock(appId.toUpperCase());
            Storage storage= this.serviceProvider.getService(Storage.class);
            Application app = storage.load(appId);
            WorkFlow workflow = null;
            if(app == null){
                app = new Application();
                app.appId = appId;
                app.create_time = new Date();
                app.startTime = app.create_time.getTime();
                if(messager.getJobStatus() != null){
                    workflow = workflowProvider.get(messager.getJobStatus());
                }
                if(workflow == null){
                    workflow = workflowProvider.getByAppId(appId);
                }
                app.workflow = workflow.getName();
            } else {
                workflow = workflowProvider.get(app.workflow);
            }
            app.workflowProvider = this.workflowProvider;
            app.addMessager(messager);
            workflow.onChange(messager, app, serviceProvider);
            
            app.workflowProvider = null;
            storage.save(app);
        }
        finally{
            dataCoordinator.releaseLock(appId.toUpperCase());
        }
    }
}
