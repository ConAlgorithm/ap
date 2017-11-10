package catfish.test.mocks;

import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;

public class MockQueueService implements IQueueService {
private MockQueue queue = new MockQueue();
	
	public IQueue getQueue(String queueName){
		return queue;
	}
	
	public IQueue getQueue(String queueName,String consumer){
        return queue;
    }
}

class MockQueue implements IQueue{
	private IQueueListener listener;
	public <T> void postMessage(String message, T data){
		this.listener.onMessage(message, data.toString());
	}
	public <T> void sendMessage(final String message, T data){
	    this.listener.onMessage(message, data.toString());
	}
	public <T> void sendMessage(final String message, T data, String type){
	    this.listener.onMessage(message, data.toString());
    }
	public <T> void sendMessage(final String message, T data, int priority, int delaySeconds){
	    this.listener.onMessage(message, data.toString());
    }
	public void register(String message, IQueueListener listener){
		this.listener = listener;
	}
	
	public void register(IQueueListener listener){
        
    }
}
