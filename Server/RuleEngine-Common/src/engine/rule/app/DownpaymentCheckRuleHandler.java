package engine.rule.app;

import java.math.BigDecimal;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.queue.QueueMessager;
import engine.main.QueueMessagerExtension;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.app.AppStoreInfoModelCreator;
import engine.rule.model.creator.app.ApplicationModelCreator;
import engine.rule.model.creator.app.ConsistencyCheckModelCreator;
import engine.rule.model.creator.app.OutModelCreator;
import engine.rule.model.creator.app.PersonalInfoModelCreator;
import engine.rule.model.inout.app.DecisionInOutForm;

public class DownpaymentCheckRuleHandler extends ApplicationRuleHandler<QueueMessager>{

	@Override
	public QueueMessager handle(QueueMessager messager) {
		QueueMessagerExtension message = new QueueMessagerExtension(messager.getAppId(), messager.getJobName());
		String appId = message.getAppId();
		String jobName = message.getJobName();
		Integer decisionResult = null;
		BigDecimal downPaymentRate = null;
		
		try{
			
			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.App, jobName);
			
			CompositeModelCreator downPaymentCheckCreator = new CompositeModelCreator();

			PersonalInfoModelCreator personalInfoCreator = new PersonalInfoModelCreator(appId);
			downPaymentCheckCreator.addCreator(new ConsistencyCheckModelCreator(appId, personalInfoCreator));
			downPaymentCheckCreator.addCreator(new ApplicationModelCreator(appId));
			downPaymentCheckCreator.addCreator(new AppStoreInfoModelCreator(appId));

			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(
					downPaymentCheckCreator, new OutModelCreator(appId));

			DecisionInOutForm result = (DecisionInOutForm) out
					.get("inout_Decision");

			decisionResult = result.getDecisionResult();
			
			//if there is no result downPaymentRate, then downPaymentRate was set as default value -1
			downPaymentRate = result.getDownPaymentRate();	
			downPaymentRate = downPaymentRate.setScale(4, BigDecimal.ROUND_HALF_UP);			
			
			message.setDownPaymentRate(downPaymentRate);
			if(decisionResult == RuleEngineDecisionResult.Rewrite.getValue()){
				message.setRewriteDecision("ReWrite");			
			}else if(decisionResult == RuleEngineDecisionResult.NORewrite.getValue()){
				message.setRewriteDecision("NOReWrite");		
			}
			return message;
	} catch (Exception e) {
		Logger.get().warn("Execute rule error of AppId: " + appId, e);
	}
	return null;
  }

}
