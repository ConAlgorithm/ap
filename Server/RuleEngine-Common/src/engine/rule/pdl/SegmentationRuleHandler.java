package engine.rule.pdl;

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
import engine.rule.model.creator.pdl.ApplicationModelCreator;
import engine.rule.model.creator.pdl.ConsistencyCheckModelCreator;
import engine.rule.model.creator.pdl.FInfoModelCreator;
import engine.rule.model.creator.pdl.FraudCheckModelCreator;
import engine.rule.model.creator.pdl.OutModelCreator;
import engine.rule.model.creator.pdl.PersonalInfoModelCreator;
import engine.rule.model.creator.pdl.SocialRelationModelCreator;
import engine.rule.model.creator.pdl.ThirdpartyDataModelCreator;
import engine.rule.model.inout.pdl.DecisionInOutForm;
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
		
		try {
			//如果不是正常用户，默认流程
			if (! DatabaseApiUtil.isNormalOrTestUser(appId)) {				
				message.setJobStatus(Configuration.getDefaultSegmentation());
				return message;
			}
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.PayDayLoanApp,message.getJobName());
			
			PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
			ConsistencyCheckModelCreator consistencyCheckModelCreator = new ConsistencyCheckModelCreator(appId, personalInfoCreator);
			
			CompositeModelCreator autoLoanDecisionCreator = new CompositeModelCreator();
			autoLoanDecisionCreator.addCreator(personalInfoCreator);
			autoLoanDecisionCreator.addCreator(new SocialRelationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new FraudCheckModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new ApplicationModelCreator(appId));
			autoLoanDecisionCreator.addCreator(consistencyCheckModelCreator);
			autoLoanDecisionCreator.addCreator(new ThirdpartyDataModelCreator(appId));
			autoLoanDecisionCreator.addCreator(new FInfoModelCreator(appId));
			
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
					autoLoanDecisionCreator, new OutModelCreator(appId));
			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			boolean isContinuable = (result.getDecisionResult() == RuleEngineDecisionResult.Approved.getValue());
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
			Logger.get().error("SegmentationCode is null of appId: " + appId);
		}
	}
}
