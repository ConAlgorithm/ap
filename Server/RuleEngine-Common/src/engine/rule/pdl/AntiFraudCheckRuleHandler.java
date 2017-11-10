package engine.rule.pdl;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.JobStatus;
import catfish.base.business.ruleengine.FrozenInfoData;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.DatabaseApiUtil;
import catfish.base.queue.QueueMessager;
import catfish.base.queue.UserDefinedQueueMessager;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.pdl.HistoricalPerformanceCreator;
import engine.rule.model.creator.pdl.LoanMoneyOutCreator;
import engine.rule.model.inout.pdl.LoanMoneyDecisionOutForm;

public class AntiFraudCheckRuleHandler extends ApplicationRuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		
		String appId = message.getAppId();
		/***************just for testing********************/
		if(isForTesting)
		{
			//message.setJobStatus(JobStatus.Approved);
			message.setJobStatus(JobStatus.Rejected);
			save(appId, new FrozenInfoData(new Date(), 1, "A"));
			return message;
		}
		/***************************************************/
		
		if (!DatabaseApiUtil.isNormalOrTestUser(appId)) {
			Logger.get().info(String.format("The user of AppId: %s is not a normal or test user.", appId));
			message.setJobStatus(JobStatus.Approved);
			return message;
		}
		boolean isContinuable = false;
		try {
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.PayDayLoanApp, message.getJobName());
			
			CompositeModelCreator creator = new CompositeModelCreator();
			creator.addCreator(new HistoricalPerformanceCreator(appId));
			
			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(creator, new LoanMoneyOutCreator(appId));
			LoanMoneyDecisionOutForm result = (LoanMoneyDecisionOutForm) out.get(LoanMoneyDecisionOutForm.Key);
			isContinuable = result.getFrozenDays() <= 0;
			
			Set<String> decisionRejectReasonSet = new HashSet<String>();
			decisionRejectReasonSet.add(result.getFrozenReason());
			recordReasonIfRejected(appId, decisionRejectReasonSet);
			
			FrozenInfoData frozenInfoData = new FrozenInfoData(new Date(), result.getFrozenDays(), result.getFrozenReason());
			save(appId, frozenInfoData);
			
			return new UserDefinedQueueMessager<FrozenInfoData>(appId, "AntiFraudCheck", isContinuable ? JobStatus.Approved : JobStatus.Rejected, frozenInfoData);
		} catch (Exception e) {
			Logger.get().warn("Execute rule error of AppId: " + appId, e);
		}
		return null;
	}
	
	private static void save(String appId, FrozenInfoData frozenInfoData){
		AppDerivativeVariableManager.addVariables(
				new AppDerivativeVariable(appId, AppDerivativeVariableNames.HistoricalPerformanceFrozenStartDate, frozenInfoData.getStartDate()),
				new AppDerivativeVariable(appId, AppDerivativeVariableNames.HistoricalPerformanceFrozenDays, frozenInfoData.getDays()),
				new AppDerivativeVariable(appId, AppDerivativeVariableNames.HistoricalPerformanceFrozenReason, frozenInfoData.getReason()));
	}
}
