package catfish.flowcontroller.test;

import java.util.ArrayList;
import java.util.List;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.test.Activity;
import catfish.flowcontroller.activities.test.ActivityBuilder;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.messageservice.Message;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.services.ServiceProvider;
import catfish.services.queue.test.QueueService;

public class TestPlugin implements IPlugin, Runnable{
    private IServiceProvider serviceProvider;
    private List<TestFlow> testflows = new ArrayList<TestFlow>();
    
    public TestPlugin(){
    }
    
    @Override
    public void init(IServiceProvider sp) {
        ServiceProvider newSp = (ServiceProvider)(sp);
        newSp.register(IMessageService.class, new IMessageService(){
            @Override
            public void sendMessage(String notificationName, String appId, String extraInfo){
                onMessage(notificationName, appId, extraInfo);
            }
            
            @Override
            public void sendMessage(Message message){
            }
        });
        
        this.serviceProvider = newSp;
        
        QueueService queueService = new QueueService();
        newSp.register(IQueueService.class, queueService);
        
        String[] queueNames = new String[] {
                "JobRequestQueue",
                "TopRulesDecisionJobRequestQueue", 
                "CatfishServerQueue",
                "TransactionMonitorJobRequestQueue",
                "PersonalInfoQueueV2",
                "ContactPhoneQueueV2",
                "CustomPhoneQueueV2",
                "CompanyPhoneQueueV2",
                "PhotoClassifyQueueV2",
                "ImageQueueV2",
                "HeadPhotoQueueV2",
                "IDCardPhotoQueueV2",
                "BankCardQueueV2",
                "CourtExecutedQueueV2",
                "HomeCreditForMaleQueueV2",
                "HomeCreditForFemaleQueueV2",
                "CompetitorSituationQueueV2",
                "FraudDecisionJobRequestQueue",
                "UbtDBWritingQueue",
                "InfoEnteringQueueV2",
                "ImageComparisonQueueV2",
                "CheckCourtExecutedExist",
                "CompetitorSituationQueueV2",
                "MachineDecisionJobRequestQueue",
                "MerchantPhoneQueueV2",
                "CheckD1FeedbackQueueV2",
                "IOUQueueV2",
                "BuckleQueueV2",
                "AutoPaymentRequestQueue",
                "LoanQueueV2"
                };
        for(int i=0; i<queueNames.length; i++){
            final String queueName = queueNames[i];
            IQueue queue = queueService.getQueue(queueName, "FlowController");
            queue.register(new IQueueListener(){
                public void onMessage(String message, String data) {
                    onQueueMessage(queueName, message, data);
                }
            });
        }
        loadTestFlows();
        new Thread(this).start();
    }
    
    private void loadTestFlows(){
        List<String> flows = ActivityBuilder.getTestFlows();
//        List<String> flows = new ArrayList<String>();
//        flows.add("app.reupload");
        for(String flow : flows){
            List<Activity> activitySteps = ActivityBuilder.load(flow);
            TestFlow testflow = new TestFlow(flow, activitySteps, this.serviceProvider);
            testflows.add(testflow);
        }
    }
    
    public void run(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i=0; i<testflows.size(); i++){
            TestFlow testflow = testflows.get(i);
            Logger.get().info(String.format("FlowController.test: test flow %s begin.", testflow.getName()));
            testflow.start();
        }
    }
    
    private void onQueueMessage(String queue, String message, String data){
        QueueMessager messager = QueueMessager.fromString(data);
        TestFlow testflow= getTestFlow(messager.getAppId());
        
        if(testflow ==null){
            Logger.get().warn("FlowController.test can not get test flow for app:"+messager.getAppId());
            return;
        }
        
        testflow.onQueueMessage(queue, message, data);
    }
    
    public void onMessage(final String notificationName, final String appId, final String extraInfo){
        Logger.get().info(String.format("MessageService Send message: %s, %s, %s", notificationName, appId, extraInfo));
        
        final TestFlow testflow= getTestFlow(appId);
        if(testflow ==null){
            Logger.get().warn("FlowController.test can not get test flow for app:"+ appId);
            return;
        }
        new Thread(new Runnable(){
            public void run(){
                testflow.onMessage(notificationName, extraInfo);
            }
        }).start();
        
    }

    private TestFlow getTestFlow(String appId){
        for(int i=0; i<testflows.size(); i++){
            TestFlow testflow = testflows.get(i);
            if(testflow.appId().equals(appId)){
                return testflow;
            }
        }
        return null;
    }

}
