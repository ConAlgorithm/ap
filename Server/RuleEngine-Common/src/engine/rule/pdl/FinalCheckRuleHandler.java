package engine.rule.pdl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;

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
import engine.rule.model.creator.pdl.ApplicationModelCreator;
import engine.rule.model.creator.pdl.BehaviorModelCreator;
import engine.rule.model.creator.pdl.ConsistencyCheckModelCreator;
import engine.rule.model.creator.pdl.CreditReferenceModelCreator;
import engine.rule.model.creator.pdl.DynamicApplicationModelCreator;
import engine.rule.model.creator.pdl.FInfoModelCreator;
import engine.rule.model.creator.pdl.FraudCheckModelCreator;
import engine.rule.model.creator.pdl.InvestigationModelCreator;
import engine.rule.model.creator.pdl.OutModelCreator;
import engine.rule.model.creator.pdl.PersonalInfoModelCreator;
import engine.rule.model.creator.pdl.SocialRelationModelCreator;
import engine.rule.model.creator.pdl.ThirdpartyDataModelCreator;
import engine.rule.model.inout.pdl.DecisionInOutForm;
import engine.rule.model.inout.pdl.StoreInOutForm;
import engine.util.ApplicationHelper;

public class FinalCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {

		/***************just for testing********************/
		if(isForTesting)
		{
			//InstallmentApplicationAction.Approve(message.getAppId());
			message.setJobStatus(JobStatus.Approved + "WithTransactionMonitor");
			message.setJobDataInt(ApplicationHelper.getPDLProductSelectType(message.getAppId()));
			return message;
		}
		/***************************************************/
		
		String appId = message.getAppId();
		if (!DatabaseApiUtil.isNormalOrTestUser(appId)) {
			Logger.get().info(String.format("The user of AppId: %s is not a normal or test user.", appId));
			message.setJobStatus(JobStatus.Approved + "WithoutTransactionMonitor");
			return message;
		}
		boolean isContinuable = false;
		try {
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.PayDayLoanApp, message.getJobName());
			
			PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
			SocialRelationModelCreator socialRelationCreator = new SocialRelationModelCreator(appId);
			BehaviorModelCreator behaviorModelCreator = new BehaviorModelCreator(appId);
			FraudCheckModelCreator fraudCheckModelCreator = new FraudCheckModelCreator(appId);
			DynamicApplicationModelCreator dynamicApplicationModelCreator = new DynamicApplicationModelCreator(appId);
			CreditReferenceModelCreator creditReferenceModelCreator = new CreditReferenceModelCreator(appId);
			ApplicationModelCreator applicationModelCreator = new ApplicationModelCreator(appId);
			ConsistencyCheckModelCreator consistencyCheckModelCreator = new ConsistencyCheckModelCreator(appId, personalInfoCreator);
			InvestigationModelCreator investigationModelCreator = new InvestigationModelCreator(appId);
			ThirdpartyDataModelCreator thirdpartyDataModelCreator = new ThirdpartyDataModelCreator(appId);
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
			autoLoanDecisionCreator.addCreator(new FInfoModelCreator(appId));
			autoLoanDecisionCreator.addCreator(thirdpartyDataModelCreator);
			
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
					autoLoanDecisionCreator, new OutModelCreator(appId));

			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			Logger.get().debug("DecisionInOutForm_result is" + new Gson().toJson(result));
			Integer decisionResult = result.getDecisionResult();
			Logger.get().debug("decisionResult is" + decisionResult);
			StoreInOutForm storeForm = (StoreInOutForm) out.get("inout_Store");
			Logger.get().debug("storeForm is" + storeForm);

			//According the workflow, the precheck should not return the "RecheckingRequired", so we just take it as a "reject".
			if(decisionResult == RuleEngineDecisionResult.RecheckingRequired.getValue())
				Logger.get().error(String.format("Unexpected result returned from toprules of AppId: %s, please check the rules again.", appId));
			
			isContinuable = (decisionResult == RuleEngineDecisionResult.Approved.getValue());
			
			recordReasonIfRejected(appId, result);
			
			ProcessRuleResult(appId, message.getJobName(), isContinuable, 0, result, storeForm);
			FraudRuleResultDao.updateRuleResult(appId, result.getFraudCheckScore().intValue());
			ApplicationHelper.RecordStrategyCode(appId, result.getStrategyCode());
			message.setJobStatus(getJobStatus(result));
			message.setJobDataInt(ApplicationHelper.getPDLProductSelectType(appId));
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
	        resultStr += result.isTransactionMonitorRequired() 
	                ? "WithTransactionMonitor" 
	                : "WithoutTransactionMonitor";
	    }
	    return resultStr;
	}
	
}
