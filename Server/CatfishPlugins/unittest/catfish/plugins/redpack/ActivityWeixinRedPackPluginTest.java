package catfish.plugins.redpack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import catfish.base.business.object.ActivityWeixinRedPackObject;
import catfish.base.queue.QueueMessager;
import catfish.framework.queue.IQueueService;
import catfish.framework.messageservice.*;
import catfish.services.ServiceProvider;
import catfish.framework.messageservice.Message;
import catfish.test.mocks.MockQueueService;

public class ActivityWeixinRedPackPluginTest {
    private ServiceProvider serviceProvider;
    private IQueueService queueServcie;
    private MockMessageService messageService;

    @Before
    public void setUp() throws Exception {
        serviceProvider = new ServiceProvider();
        queueServcie = new MockQueueService();
        serviceProvider.register(IQueueService.class, queueServcie);
        messageService = new MockMessageService();
        serviceProvider.register(IMessageService.class, messageService);
    }
    
    @Test
    public void testNoActivity() {
        MockActivityService mockActiviyServcie = new MockActivityService();
        ActivityWeixinRedPackPlugin redPackPlugin = new ActivityWeixinRedPackPlugin(mockActiviyServcie);
        redPackPlugin.init(serviceProvider);
        String message = "AppMoneyTransferred";
        QueueMessager qm = new QueueMessager("436CF5DB-55C1-E411-B762-005056C00008",message);
        queueServcie.getQueue("CatfishServerQueue").postMessage(message, qm.toString());
        assertFalse("Should not create redpack", mockActiviyServcie.isTriggered);
        assertFalse("Should not send message to S1", messageService.isTriggered);
    }
    
    @Test
    public void testActivity() {
        ActivityWeixinRedPackObject redPackActivity = new ActivityWeixinRedPackObject();
        redPackActivity.MaxReward = 100;
        redPackActivity.MinReward = 10;
        MockActivityService mockActiviyServcie = new MockActivityService(redPackActivity);
        ActivityWeixinRedPackPlugin redPackPlugin = new ActivityWeixinRedPackPlugin(mockActiviyServcie);
        redPackPlugin.init(serviceProvider);
        String message = "AppMoneyTransferred";
        String appId = "436CF5DB-55C1-E411-B762-005056C00008";
        QueueMessager qm = new QueueMessager(appId,message);
        queueServcie.getQueue("CatfishServerQueue").postMessage(message, qm.toString());
        assertTrue("should create redpack", mockActiviyServcie.isTriggered);
        assertSame(redPackActivity, mockActiviyServcie.activity);
        assertEquals(appId, mockActiviyServcie.appId);
        assertTrue("reward should in the specified range", 
                mockActiviyServcie.reward >=redPackActivity.MinReward && 
                mockActiviyServcie.reward <=redPackActivity.MaxReward );
        assertTrue("send message to S1", messageService.isTriggered);
    }

}

class MockMessageService implements IMessageService{
    public boolean isTriggered = false;
    
    public void sendMessage(Message message){
        isTriggered = true;
    }
    
    public void sendMessage(String notificationName, String appId, String extraInfo){
        
    }
}

class MockActivityService implements ActivityService{
    private ActivityWeixinRedPackObject redPackActivity;
    public boolean isTriggered = false;
    public String appId;
    public ActivityWeixinRedPackObject activity;
    public double reward =0;
    
    public MockActivityService(){
        this(null);
    }
    
    public MockActivityService(ActivityWeixinRedPackObject redPackActivity){
        this.redPackActivity = redPackActivity;
    }
    @Override
    public ActivityWeixinRedPackObject getActivity(String appId){
        return this.redPackActivity;
    }
    
    @Override
    public String createRedPackRecord(String appId, String activityId ,String mchBillNo, double reward){
        isTriggered = true;
        this.appId = appId;
//        this.activity = activity;
        this.reward = reward;
        return "record_Id";
    }

}