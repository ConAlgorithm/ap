package engine.main;

import java.util.Date;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.business.common.JobStatus;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.queue.QueueMessager;
import catfish.framework.IServiceProvider;
import catfish.services.coordination.CoordinationService;
import catfish.services.coordination.DataCoordinator;
import engine.rule.RuleHandlerConfigManager;
import engine.rule.coordinate.DefaultStrategy;
import engine.rule.coordinate.Message;
import engine.rule.coordinate.Strategy;
import engine.rule.coordinate.Workflow;
import engine.util.RiskPersistenceService;

public class POSRuleEngineWorker implements RuleEngineWorker{

//	private Storage storage;

	private Strategy strategy = new DefaultStrategy();
	
	private IServiceProvider sp;
	private DataCoordinator coordinate;
	
	private static final String FluentCheck = "FluentCheck";
	private static final String DownpaymentCheck = "DownpaymentCheck";	
	
	public POSRuleEngineWorker(IServiceProvider sp)
	{
		this.sp = sp;
		this.coordinate = sp.getService(CoordinationService.class).registerData("ruleengine");
//		this.storage = sp.getService(Storage.class);
	}
	public QueueMessager execute(QueueMessager messager, Integer channel)
	{
		if(messager.getJobName().equals(FluentCheck) && DynamicConfig.read(FluentCheck, "ON").equalsIgnoreCase("OFF"))
		{
			Logger.get().info("FluentCheck is off, won't do it");
			return null;
		}
		String appId = messager.getAppId();
		Message msg = adaptMessage(messager);
		try{  
			this.coordinate.lock(appId);
			boolean needRunRule = true;
			QueueMessager resultMsg = null;
			Workflow flow = RiskPersistenceService.getRuleengineCollection(appId);
			if(flow == null)
			{
				flow = createWorkflow(messager);
			}
			if(!strategy.isCheckKeyPointPass(flow, msg))
			{
				Logger.get().info("Key point check do not pass: " + messager.toString());
				needRunRule = false;
			}
			if(!strategy.isCheckFlowPass(flow, msg))
			{
				Logger.get().info("Flow check do not pass: " + messager.toString());
				needRunRule = false;
			}
			flow.addReived(msg);
			if(needRunRule)
			{
		        if (channel == null) {
		           Logger.get().error("Get application channel failed, QueueMessager: " + messager.toString());
		           return null;
		        }
		        Object result = RuleHandlerConfigManager.getRuleHandler(messager.getJobName(), channel)
		                .handle(messager);
		        if( null == result) {
		        	Logger.get().warn("Ruleengine execute result is null! QueueMessage is " + messager.toString());
		        	return null;
		        }
		        if(messager.getJobName().equals(DownpaymentCheck)){
			        resultMsg = (QueueMessagerExtension)result;		        	
		        }else{
			        resultMsg = (QueueMessager)result;		        	
		        }
		        resultMsg = (QueueMessager)updateQueueMessageResultWhenObserverTag((QueueMessager)result);

	            flow.addSent(adaptMessage(resultMsg));            
		        Logger.get().debug("The result of RuleEngine: " + result);   
			}
			RiskPersistenceService.saveToRuleEngine4Workflow(flow);
			return resultMsg;
		}catch(Exception e){
			Logger.get().error("Execute RuleEngine error, QueueMessager: " + messager.toString(), e);
			return null;
		}finally{
			this.coordinate.releaseLock(appId);
		}
	}
	
	/*
	 * update queue message when applicaiton has observerTag
	 * this function will modifiy result from rejected to approved
	 * author malong
	 */
	private QueueMessager updateQueueMessageResultWhenObserverTag(QueueMessager message) {
		Boolean observerTag = AppDerivativeVariableManager.getAsBoolean(message.getAppId(), "X_TraditionObserveTag", false);
		if(observerTag && message.getJobStatus() == JobStatus.Rejected) {
			Logger.get().info("update rule result from rejected to approved, detail info : " + message.toString());
			message.setJobStatus(JobStatus.Approved);
		}
		return message;
	}

	private Workflow createWorkflow(QueueMessager msg)
	{
		Workflow flow = new Workflow();
		flow.setAppId(msg.getAppId());
		return flow;
	}
	private Message adaptMessage(QueueMessager messager)
	{
		Message msg = new Message();
		msg.setName(messager.getJobName());
		msg.setStatus(messager.getJobStatus());
		msg.addExtraInfo("dataInt", messager.getJobDataInt());
		msg.setTime(new Date());
		return msg;
	}
}
