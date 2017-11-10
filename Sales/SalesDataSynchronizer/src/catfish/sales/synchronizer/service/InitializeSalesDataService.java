package catfish.sales.synchronizer.service;

import catfish.framework.IServiceProvider;
import catfish.framework.database.IDatabaseService;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.sales.synchronizer.Finder;

public class InitializeSalesDataService implements IQueueListener {

	private Finder finder;
	private IQueue queue;
	
	public InitializeSalesDataService(IServiceProvider sp){
		finder =new Finder(sp.getService(IDatabaseService.class));
		IQueueService queueService = sp.getService(IQueueService.class);
        this.queue = queueService.getQueue("","withholdingcontroller");
	}

	@Override
	public void onMessage(String message, String data) {
        finder.initializeData();	
	}

}
