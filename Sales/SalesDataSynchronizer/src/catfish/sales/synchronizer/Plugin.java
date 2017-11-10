package catfish.sales.synchronizer;

import catfish.base.Logger;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.database.IDatabaseService;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;
import catfish.sales.synchronizer.service.InitializeSalesDataService;
import catfish.sales.synchronizer.service.TableUpdateService;

public class Plugin implements IPlugin{
    private Finder finder;
    
    @Override
    public void init(IServiceProvider sp) {
        IQueueService queueService = sp.getService(IQueueService.class);
        IQueue queue = queueService.getQueue("SalesDataSynchronizer");
        InitializeSalesDataService initializeSalesDataService=new InitializeSalesDataService(sp);
        TableUpdateService tableUpdateService=new TableUpdateService(sp);
        queue.register("InitializeSalesData",initializeSalesDataService);
        queue.register(tableUpdateService);
        
        finder =new Finder(sp.getService(IDatabaseService.class));
    }
}
