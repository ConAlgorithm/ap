package engine.rule.app;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;
import engine.main.Configuration;
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
import engine.rule.model.creator.app.OutModelCreator;
import engine.rule.model.creator.app.PersonalInfoModelCreator;
import engine.rule.model.creator.app.SHZXDataModelCreator;
import engine.rule.model.creator.app.SocialRelationModelCreator;
import engine.rule.model.creator.app.ThirdpartyDataModelCreator;
import engine.rule.model.inout.app.DecisionInOutForm;
import engine.util.ApplicationHelper;

public class SegmentationRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		/***************just for testing********************/
		if(isForTesting)
		{
			message.setJobStatus(Configuration.getDefaultSegmentation());
			return message;
		}
		/***************************************************/
		
		String appId = message.getAppId();
		// 如果出异常，默认流程
		message.setJobStatus(Configuration.getDefaultSegmentation());
		boolean isContinuable = false;
		try {
			//如果不是正常用户，默认流程
			if (! DatabaseApiUtil.isNormalOrTestUser(appId)) {				
				message.setJobStatus(Configuration.getDefaultSegmentation());
				return message;
			}
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.App, message.getJobName());
			
			PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
			ConsistencyCheckModelCreator consistencyCheckModelCreator = new ConsistencyCheckModelCreator(appId, personalInfoCreator);
			
			CompositeModelCreator autoLoanDecisionCreator = new CompositeModelCreator();
			autoLoanDecisionCreator.addCreator(personalInfoCreator);
			autoLoanDecisionCreator.addCreator(new SocialRelationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new BehaviorModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new FraudCheckModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new DynamicApplicationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new CreditReferenceModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new ApplicationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(consistencyCheckModelCreator);
			autoLoanDecisionCreator.addCreator(new AppStoreInfoModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new ThirdpartyDataModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new SHZXDataModelCreator(appId));  
			
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
					autoLoanDecisionCreator, new OutModelCreator(appId, message.getJobName()));
			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			isContinuable = (result.getDecisionResult() == RuleEngineDecisionResult.Approved.getValue());
			
			message.setJobStatus(result.getSegmentationCode());
			recordSegmentationCode(appId, result.getSegmentationCode());
			ProcessRuleResult(appId, message.getJobName(), isContinuable, 0, result);
			ApplicationHelper.RecordStrategyCode(appId, result.getStrategyCode());
		} catch (Exception e) {
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
	
		return message;
	}
	
	//记录分层策略结果
	public void recordSegmentationCode(String appId, String segmentationCode)
	{
		if(segmentationCode != null)
		{
			AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableConsts.SegmentationCode, segmentationCode));
		}else{
			Logger.get().warn("SegmentationCode is null of AppId: " + appId);
		}
	}	
}
