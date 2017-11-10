package engine.rule.app;

import java.util.Map;

import catfish.base.DescriptionParser;
import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.dao.FraudRuleResultDao;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.business.util.EnumUtils;
import catfish.base.queue.QueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.app.AppStoreInfoModelCreator;
import engine.rule.model.creator.app.ApplicationModelCreator;
import engine.rule.model.creator.app.BehaviorModelCreator;
import engine.rule.model.creator.app.ConsistencyCheckModelCreator;
import engine.rule.model.creator.app.CreditReferenceModelCreator;
import engine.rule.model.creator.app.DynamicApplicationModelCreator;
import engine.rule.model.creator.app.FraudCheckModelCreator;
import engine.rule.model.creator.app.InvestigationModelCreator;
import engine.rule.model.creator.app.OutModelCreator;
import engine.rule.model.creator.app.PersonalInfoModelCreator;
import engine.rule.model.creator.app.SocialRelationModelCreator;
import engine.rule.model.creator.app.ThirdpartyDataModelCreator;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.rule.model.inout.app.StoreInOutForm;
import engine.util.ApplicationHelper;

public class FinalCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		
		String appId = message.getAppId();
		
		//先清除掉重传标志位
		ApplicationHelper.ClearReuploadFlag(appId);
		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobStatus(JobStatus.Approved + "WithTransactionMonitor");
			return message;
		}
		/***************************************************/
				
		if (!DatabaseApiUtil.isNormalOrTestUser(appId)) {
			Logger.get().info(String.format("The user of AppId: %s is not a normal or test user.", appId));
			message.setJobStatus(JobStatus.Approved + "WithoutTransactionMonitor");
			return message;
		}
		boolean isContinuable = false;
		try {
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.App, message.getJobName());
			
			PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
			SocialRelationModelCreator socialRelationCreator = new SocialRelationModelCreator(appId);
			BehaviorModelCreator behaviorModelCreator = new BehaviorModelCreator(appId);
			FraudCheckModelCreator fraudCheckModelCreator = new FraudCheckModelCreator(appId);
			DynamicApplicationModelCreator dynamicApplicationModelCreator = new DynamicApplicationModelCreator(appId);
			CreditReferenceModelCreator creditReferenceModelCreator = new CreditReferenceModelCreator(appId);
			ApplicationModelCreator applicationModelCreator = new ApplicationModelCreator(appId);
			ConsistencyCheckModelCreator consistencyCheckModelCreator = new ConsistencyCheckModelCreator(appId, personalInfoCreator);
			InvestigationModelCreator investigationModelCreator = new InvestigationModelCreator(appId);
			AppStoreInfoModelCreator appStoreInfoModelCreator = new AppStoreInfoModelCreator(appId);
			
			CompositeModelCreator autoLoanDecisionCreator = new CompositeModelCreator();
			autoLoanDecisionCreator.addCreator(personalInfoCreator);
			autoLoanDecisionCreator.addCreator(socialRelationCreator);
			autoLoanDecisionCreator.addCreator(behaviorModelCreator);
			autoLoanDecisionCreator.addCreator(fraudCheckModelCreator);
			autoLoanDecisionCreator.addCreator(dynamicApplicationModelCreator);
			autoLoanDecisionCreator.addCreator(creditReferenceModelCreator);
			autoLoanDecisionCreator.addCreator(applicationModelCreator);
			autoLoanDecisionCreator.addCreator(consistencyCheckModelCreator);
			autoLoanDecisionCreator.addCreator(investigationModelCreator);
			autoLoanDecisionCreator.addCreator(appStoreInfoModelCreator);
			autoLoanDecisionCreator.addCreator(new ThirdpartyDataModelCreator(appId));
			
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
					autoLoanDecisionCreator, new OutModelCreator(appId, message.getJobName()));
			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			Integer decisionResult = result.getDecisionResult();
			StoreInOutForm storeForm = (StoreInOutForm) out.get("inout_Store");

			//According the workflow, the precheck should not return the "RecheckingRequired", so we just take it as a "reject".
			if(decisionResult == RuleEngineDecisionResult.RecheckingRequired.getValue())
				Logger.get().error(String.format("Unexpected result returned from toprules of AppId: %s, please check the rules again.", appId));
			
			isContinuable = (decisionResult == RuleEngineDecisionResult.Approved.getValue());

			recordReasonIfRejected(appId, result);
			ProcessRuleResult(appId, message.getJobName(), isContinuable, 0, result, storeForm);
			FraudRuleResultDao.updateRuleResult(appId, result.getFraudCheckScore().intValue());
			ApplicationHelper.RecordStrategyCode(appId, result.getStrategyCode());
			message.setJobStatus(getJobStatus(result));
			return message;
		} catch (Exception e) {
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
		return null;
	}
	
	private String getJobStatus(DecisionInOutForm result) {
        RuleEngineDecisionResult decisionResultEnum = EnumUtils.parse(RuleEngineDecisionResult.class, result.getDecisionResult());
        String resultStr = DescriptionParser.getDescription(decisionResultEnum);
        if(decisionResultEnum == RuleEngineDecisionResult.Approved) {
            resultStr += result.isInstinctMonitorRequired() ? "WithInstinctMonitor" : 
                result.isTransactionMonitorRequired() ? "WithTransactionMonitor" : "WithoutTransactionMonitor";
        }
        return resultStr;
    }
	
}
