package engine.main;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.persistence.queue.MessageDirection;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;

public class QueuePlugin implements IQueueListener, IPlugin{
	
	private Map<Integer, RuleEngineWorker> workerMap = new HashMap<>();
	
	@Override
	public void init(IServiceProvider sp) {	
		workerMap.put(InstalmentChannel.App.getValue(), new POSRuleEngineWorker(sp));
		workerMap.put(InstalmentChannel.PayDayLoanApp.getValue(), new OtherRuleEngineWorker());
		workerMap.put(InstalmentChannel.CashLoan.getValue(), new OtherRuleEngineWorker());
		IQueue queue = sp.getService(IQueueService.class).getQueue(Configuration.TOPRULES_DECISION_QUEUE);
		queue.register(this);
	}
	
	@Override
	public void onMessage(String message, String data) {
		
		QueueMessager messager = this.getMessager(Configuration.TOPRULES_DECISION_QUEUE, data);
		Integer channel = this.getApplicationChannel(messager);
		if(channel == null)
		{
			Logger.get().error("Can not get channel, data: " + data);
			return;
		}
		try{
			QueueMessager result = workerMap.get(channel).execute(messager, channel);
	        if (result != null)
	        {
	        	String queueName = Configuration.getQueueName(messager);
	        	Logger.get().debug("Response rule engine result into Queue: " + queueName);
	        	PersistenceQueueApi.writeMessager(queueName, result, MessageSource.RuleEngine);
	        }
	            
		}catch(Exception e){
			Logger.get().error("Response RuleEngine result error, result is null, Data: " + data, e);
		}
	}
	

	
	private QueueMessager getMessager(String queueName, String data)
	{
		QueueMessager messager = null;
		try{
			messager = QueueMessager.fromString(data);		
			PersistenceQueueApi.RecordMessager(queueName, messager, null, MessageSource.RuleEngine, MessageDirection.Get, null);
		}catch(Exception e){
			Logger.get().error("Get or record QueueMessger faile,data: " + data, e);
		}
		return messager;
	}
	//TODO
	//因为目前三个产品线是一起的，所以需要单独处理
	private Integer getApplicationChannel(QueueMessager messager) {
	      String appId = messager.getAppId();
	      InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
	      if (app != null) {
	        return app.InstalmentChannel;
	      }
	
	      String channel = messager.getChannel();
	      if (channel.equalsIgnoreCase("cashloan")) {
	        return InstalmentChannel.CashLoan.getValue();
	      }	
	      return null;
    }
}
