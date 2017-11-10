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
import engine.rule.model.creator.cashloan.InvestigationModelCreator;
import engine.rule.model.creator.cashloan.OutModelCreator;
import engine.rule.model.creator.cashloan.ThirdpartyDataModelCreator;
import engine.rule.model.inout.cashloan.DecisionInOutForm;

public class SecurityCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
	    String appId = message.getAppId();
        String jobName = message.getJobName();
        int checkCount = message.getJobDataInt();

		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobStatus("3");
			return message;
		}
		/***************************************************/

        try {
            RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.CashLoan, jobName);

            CompositeModelCreator reCheckCreator = new CompositeModelCreator();
            reCheckCreator.addCreator(new InvestigationModelCreator(appId));
            reCheckCreator.addCreator(new ThirdpartyDataModelCreator(appId));

            Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
                reCheckCreator, new OutModelCreator(appId, checkCount));

            DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
            Integer decisionResult = result.getDecisionResult();

            //TODO: keep the RuleEngineResultObjects.IsPassed as a boolean type to support the weixin rules for now.
            //We should write a migration to change the boolean type to an int type.
            processRuleResult(appId, jobName, decisionResult == RuleEngineDecisionResult.Approved.getValue(), 0, result);

            if (decisionResult == RuleEngineDecisionResult.Rejected.getValue()) {
                message.setJobStatus(JobStatus.Rejected);
                return message;
            }
            else if(decisionResult == RuleEngineDecisionResult.Approved.getValue()){
                message.setJobStatus(JobStatus.Approved);
                return message;
            }
            else if(decisionResult == RuleEngineDecisionResult.Canceled.getValue()){
                message.setJobStatus(JobStatus.Canceled);
                return message;
            }
            //we orginize the input msg to give the RE, and RE
            //give us a result, and we tell the rlt to the flowcontroller
            //need to adapter to tell flowcontroller what to do
            message.setJobStatus(JobStatus.RecheckingRequired);
            return message;
        } catch (Exception e) {
        	Logger.get().warn("Execute rule error of AppId: " + appId, e);
        }

        return null;
	}

}
