package catfish.flowcontroller.test;

import java.util.List;
import java.util.UUID;

import catfish.base.Logger;
import catfish.flowcontroller.activities.test.Activity;
import catfish.framework.IServiceProvider;

public class TestFlow {
    private IServiceProvider serviceProvider;
    private List<Activity> activitySteps;
    private Application application;
    private int currentStep = 0;
    public boolean done = false;
    private String name;
    
    public TestFlow(String name, List<Activity> activitySteps, IServiceProvider serviceProvider){
        this.name= name;
        this.activitySteps = activitySteps;
        this.serviceProvider = serviceProvider;
        
        application = new Application();
        UUID uuid = UUID.randomUUID();
        application.appId = uuid.toString();
    }
    
    public String getName(){
        return this.name;
    }
    
    public String appId(){
        return application.appId;
    }
    
    public void start(){
        activitySteps.get(currentStep).run(application, serviceProvider);
    }
    
    public synchronized void onMessage(String notificationName, String extraInfo){
        Logger.get().info(String.format("[%s] got message %s", this.name, notificationName));
        application.messageData = new MessageData(notificationName, extraInfo);
        runActivity();
    }
    
    public synchronized void onQueueMessage(String queue, String message, String data) {
        Logger.get().info(String.format("[%s] got queue message %s", this.name, message));
        
        MessageInfo info = new MessageInfo(queue, message);
        application.messages.add(info);
        
        runActivity();
    }
    
    private void runActivity(){
        if(currentStep >= activitySteps.size()){
            Logger.get().warn(this.name + " new message after test done.");
            return;
        }
        
        while( activitySteps.get(currentStep).complete(application, serviceProvider) ){
            Logger.get().info(String.format("[%s] test activity done: %s", this.name, activitySteps.get(currentStep).name));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            currentStep ++;
            if(currentStep < activitySteps.size()){
                Logger.get().info(String.format("[%s] test activity begin: %s", this.name, activitySteps.get(currentStep).name));
                activitySteps.get(currentStep).run(application, serviceProvider);
            } else {
                Logger.get().info(String.format("[%s] test flow done.", this.name));
                done = true;
                return;
            }
        }
    }
}
