package engine.rule.cashloan;

import java.util.Map;

import catfish.base.DescriptionParser;
import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.util.EnumUtils;
import catfish.base.queue.QueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.cashloan.InvestigationModelCreator;
import engine.rule.model.creator.cashloan.OutModelCreator;
import engine.rule.model.creator.cashloan.ThirdpartyDataModelCreator;
import engine.rule.model.inout.cashloan.DecisionInOutForm;
import engine.util.ApplicationHelper;

public class FinalCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {

		String appId = message.getAppId();

		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobStatus(JobStatus.Approved + "WithTransactionMonitor");
			return message;
		}
		/***************************************************/

		boolean isContinuable = false;
		try {
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.CashLoan, message.getJobName());

			CompositeModelCreator autoLoanDecisionCreator = new CompositeModelCreator();
			autoLoanDecisionCreator.addCreator(new InvestigationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new ThirdpartyDataModelCreator(appId));

			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
					autoLoanDecisionCreator, new OutModelCreator(appId));
			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			Integer decisionResult = result.getDecisionResult();

			//According the workflow, the precheck should not return the "RecheckingRequired", so we just take it as a "reject".
			if(decisionResult == RuleEngineDecisionResult.RecheckingRequired.getValue())
				Logger.get().error(String.format("Unexpected result returned from toprules of AppId: %s, please check the rules again.", appId));

			isContinuable = (decisionResult == RuleEngineDecisionResult.Approved.getValue());
			processRuleResult(appId, message.getJobName(), isContinuable, 0, result);
			ApplicationHelper.RecordStrategyCode(appId, result.getStrategyCode());

			RuleEngineDecisionResult decisionResultEnum = EnumUtils.parse(RuleEngineDecisionResult.class, result.getDecisionResult());
	        String resultStr = DescriptionParser.getDescription(decisionResultEnum);
			message.setJobStatus(resultStr);
			return message;
		} catch (Exception e) {
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
		return null;
	}

}
