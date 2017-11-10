package catfish.plugins.luckdraw;

import org.junit.Before;
import org.junit.Test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.database.DatabaseConfig;
import catfish.base.queue.QueueMessager;
import catfish.framework.messageservice.IMessageService;
import catfish.framework.messageservice.Message;
import catfish.framework.queue.IQueueService;
import catfish.services.ServiceProvider;
import catfish.test.mocks.MockQueueService;

public class ActivityLuckdrawTest {
    private ServiceProvider serviceProvider;
    private IQueueService queueServcie;
    private MockMessageService messageService;

    @Before
    public void setUp() throws Exception {
    	StartupConfig.initialize();
		Logger.initialize();
		DatabaseConfig.initialize();
		queueServcie = new MockQueueService();
		messageService = new MockMessageService();
        serviceProvider = new ServiceProvider();
        serviceProvider.register(IQueueService.class, queueServcie);
        serviceProvider.register(IMessageService.class, messageService);
    }
    
    @Test
    public void testActivity() {
    	ActivityLuckDrawPlugin plugin = new ActivityLuckDrawPlugin();
    	plugin.init(serviceProvider);
        String message = "AppMoneyTransferred";
        String appId = "5EE9656E-29F8-E311-B348-E0DB5516D568";
        QueueMessager qm = new QueueMessager(appId,message);
        queueServcie.getQueue("CatfishServerQueue").postMessage(message, qm.toString());
    }
    
    class MockMessageService implements IMessageService{
        public boolean isTriggered = false;
        
        public void sendMessage(Message message){
            isTriggered = true;
        }
        
        public void sendMessage(String notificationName, String appId, String extraInfo){
            
        }
    }
}