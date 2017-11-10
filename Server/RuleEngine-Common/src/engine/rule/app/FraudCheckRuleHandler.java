package engine.rule.app;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.app.ApplicationModelCreator;
import engine.rule.model.creator.app.FraudCheckModelCreator;
import engine.rule.model.creator.app.OutModelCreator;
import engine.rule.model.inout.app.DecisionInOutForm;

public class FraudCheckRuleHandler extends ApplicationRuleHandler<QueueMessager>{

	@Override
	public QueueMessager handle(QueueMessager message) {
        String appId = message.getAppId();
		
		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobStatus(JobStatus.Approved);
			return message;
		}
		/***************************************************/
				
		if (!DatabaseApiUtil.isNormalOrTestUser(appId)) {
			Logger.get().info(String.format("The user of AppId: %s is not a normal or test user.", appId));
			message.setJobStatus(JobStatus.Approved);
			return message;
		}
	
		boolean isContinuable = false;
		try{
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.App, message.getJobName());
			
			CompositeModelCreator composeModelCreator = new CompositeModelCreator();
			FraudCheckModelCreator fraudCheckModelCreator = new FraudCheckModelCreator(appId);
			ApplicationModelCreator applicationModelCreator = new ApplicationModelCreator(appId);
			
			composeModelCreator.addCreator(fraudCheckModelCreator);
			composeModelCreator.addCreator(applicationModelCreator);
			
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(composeModelCreator, new OutModelCreator(appId, message.getJobName()));
			DecisionInOutForm result = (DecisionInOutForm)out.get("inout_Decision");
			
			Integer decisionResult = result.getDecisionResult();
					
			isContinuable = (decisionResult == RuleEngineDecisionResult.Approved.getValue());

			recordReasonIfRejected(appId, result);
			ProcessRuleResult(appId, message.getJobName(), isContinuable, 0, result);
			
			message.setJobStatus(ResultStatusMap.get(decisionResult));
						
			return message;
		}catch(Exception e){
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
		return null;
	}
}
