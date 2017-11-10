package engine.main;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;
import engine.rule.RuleHandlerConfigManager;

public class OtherRuleEngineWorker implements RuleEngineWorker{
	
	public QueueMessager execute(QueueMessager messager, Integer channel)
	{
		try{
			Logger.get().info("The result of RuleEngine: " + RuleHandlerConfigManager.getRuleHandler(messager.getJobName(), channel));
	        Object result = RuleHandlerConfigManager.getRuleHandler(messager.getJobName(), channel)
	                .handle(messager);
	        QueueMessager resultMsg = (QueueMessager)result;        
	        Logger.get().debug("The result of RuleEngine: " + result); 
			return resultMsg;
		}catch(Exception e){
			Logger.get().error("Execute RuleEngine error, QueueMessager: " + messager.toString(), e);
			return null;
		}
	}

}
