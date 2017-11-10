package engine.rule.cashloan;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.queue.QueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.cashloan.ConsistencyCheckModelCreator;
import engine.rule.model.creator.cashloan.CurrentWhiteListModelCreator;
import engine.rule.model.creator.cashloan.OutModelCreator;
import engine.rule.model.creator.cashloan.SHZXDataModelCreator;
import engine.rule.model.creator.cashloan.ThirdpartyDataModelCreator;
import engine.rule.model.inout.cashloan.DecisionInOutForm;

public class PreCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {

		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobStatus(JobStatus.Approved);
			return message;
		}
		/***************************************************/

		String appId = message.getAppId();
		String jobName = message.getJobName();

		Integer decisionResult = null;

		try {
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.CashLoan, jobName);

			CompositeModelCreator preCheckCreator = new CompositeModelCreator();

			preCheckCreator.addCreator(new ThirdpartyDataModelCreator(appId));
			preCheckCreator.addCreator(new CurrentWhiteListModelCreator(appId));
			preCheckCreator.addCreator(new ConsistencyCheckModelCreator(appId));
			preCheckCreator.addCreator(new SHZXDataModelCreator(appId));

			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
					preCheckCreator, new OutModelCreator(appId));
			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			decisionResult = result.getDecisionResult();
			processRuleResult(appId, jobName, decisionResult == RuleEngineDecisionResult.Approved.getValue(), 0, result);

			//According the workflow, the precheck should not return the "RecheckingRequired", so we just take it as a "reject".
			if(decisionResult == RuleEngineDecisionResult.RecheckingRequired.getValue())
				Logger.get().error(String.format("Unexpected result returned from toprules of AppId: %s, please check the rules again.", appId));

			message.setJobStatus(decisionResult == RuleEngineDecisionResult.Approved.getValue() ? JobStatus.Approved : JobStatus.Rejected);
			return message;
		} catch (Exception e) {
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
		return null;
	}

}
