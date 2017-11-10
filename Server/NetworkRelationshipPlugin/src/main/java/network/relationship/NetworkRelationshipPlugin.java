package network.relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;

public class NetworkRelationshipPlugin implements IPlugin, IQueueListener {
	private static final String QUEUE = "GraphServiceRequestQueue";
	private List<String> msgTypes = new ArrayList<String>();
	ThreadPoolExecutor executor = null;

	public NetworkRelationshipPlugin(){
		
		msgTypes.add("CalculateNetworkRelationship");
		msgTypes.add("UpdateGraphByApplication");
		msgTypes.add("UserInfomationUpdate");
		
		int blockingSize = StartupConfig.getAsInt("catfish.network.thread.blockingsize", 16);
		int corePoolSize = StartupConfig.getAsInt("catfish.network.thread.corepoolsize", 16);
		int maxPoolSize = StartupConfig.getAsInt("catfish.network.thread.maxpoolsize", 16);
		int keepAliveTime = StartupConfig.getAsInt("catfish.network.thread.keepalivetime", 24*14);
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(blockingSize);
        this.executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());  
	}
	
	@Override
	public void onMessage(String message, String data) {
		
		if(message.equalsIgnoreCase("CalculateNetworkRelationship")){
			this.executor.execute(new Thread(new NewApplicationHandler(data)));
		}else if(message.equalsIgnoreCase("UpdateGraphByApplication")){
			this.executor.execute(new Thread(new NewApplicationHandler(data)));
		}else /*if(message.equalsIgnoreCase("UserInfomationUpdate")){
			new UserInfoUpdateHandler().handle(data);
		}else*/ {
			Logger.get().fatal("Unexpected exception");
		}
	}

	@Override
	public void init(IServiceProvider sp) {
		IQueueService queueService = sp.getService(IQueueService.class);
		IQueue queue = queueService.getQueue(QUEUE);
		
		this.msgTypes.forEach((k)-> queue.register(k, this));
	}
}
