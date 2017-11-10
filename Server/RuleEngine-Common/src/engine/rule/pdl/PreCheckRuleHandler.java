package engine.rule.pdl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import catfish.base.DescriptionParser;
import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RejectedType;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.common.UserType;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.BaseForm;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.pdl.ApplicationModelCreator;
import engine.rule.model.creator.pdl.ConsistencyCheckModelCreator;
import engine.rule.model.creator.pdl.CreditReferenceModelCreator;
import engine.rule.model.creator.pdl.FraudCheckModelCreator;
import engine.rule.model.creator.pdl.OutModelCreator;
import engine.rule.model.creator.pdl.PersonalInfoModelCreator;
import engine.rule.model.creator.pdl.SocialRelationModelCreator;
import engine.rule.model.creator.pdl.ThirdpartyDataModelCreator;
import engine.rule.model.inout.pdl.DecisionInOutForm;
import engine.util.ApplicationHelper;

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
		
		Set<String> rejectReason = null;
		Integer decisionResult = null;
		
		try {
			//如果是暗访用户，则拒绝
			if(EndUserExtentionDao.getUserTypeByAppId(appId) == UserType.SecretVisitor.getValue())
			{
				ProcessRuleResult(appId, jobName, false, 0, new BaseForm[0]);
				this.rejectSecretVisitor(appId);
				return new QueueMessager(appId, jobName, JobStatus.Rejected);
			}
			
			if (DatabaseApiUtil.isNormalOrTestUser(appId)) {
				RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.PayDayLoanApp, jobName);
				
				CompositeModelCreator preCheckCreator = new CompositeModelCreator();

				PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
				preCheckCreator.addCreator(new ConsistencyCheckModelCreator(appId, personalInfoCreator));
				preCheckCreator.addCreator(new ApplicationModelCreator(appId));
				preCheckCreator.addCreator(new SocialRelationModelCreator(appId));
				preCheckCreator.addCreator(new CreditReferenceModelCreator(appId));
				preCheckCreator.addCreator(new ThirdpartyDataModelCreator(appId));
				preCheckCreator.addCreator(new FraudCheckModelCreator(appId));
				preCheckCreator.addCreator(personalInfoCreator);				

				Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
						preCheckCreator, new OutModelCreator(appId));

				DecisionInOutForm result = (DecisionInOutForm) out
						.get("inout_Decision");

				decisionResult = result.getDecisionResult();

				rejectReason = result.getDecisionRejectReasonSet();
				
				this.recordReasonIfRejected(appId, result);
				ProcessRuleResult(appId, jobName, decisionResult == RuleEngineDecisionResult.Approved.getValue(), 0, result);
			}
			
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

	//拒绝暗访用户
	private void rejectSecretVisitor(String appId)
	{
		Set<String> reason = new HashSet<>();
		String desc = DescriptionParser.getDescription(RejectedType.SecretVisitorReject);
		if(desc != null)
		       reason.add(desc);
		ApplicationHelper.RecordRejectReason(appId, reason, RejectedType.SecretVisitorReject);
	}
}
