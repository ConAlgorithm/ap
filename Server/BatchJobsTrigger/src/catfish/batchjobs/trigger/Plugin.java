package catfish.batchjobs.trigger;


import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueueService;
import catfish.services.coordination.CoordinationService;


class Plugin implements IPlugin{
    
    @Override
    public void init(IServiceProvider sp) {
        IQueueService queueService = sp.getService(IQueueService.class);
        CoordinationService cs = sp.getService(CoordinationService.class);
        
        Trigger trigger=new Trigger(queueService,cs);
        
    }
   
}
