package engine.rule.cashloan;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.queue.QueueMessager;
import engine.main.QueueMessagerExtensionForCL;
import engine.rule.RuleHandler;
import engine.rule.execute.RuleExecutor;
import engine.rule.execute.RuleManager;
import engine.rule.model.creator.CompositeModelCreator;
import engine.rule.model.creator.cashloan.CreditCheckPercentModelCreator;
import engine.rule.model.creator.cashloan.OutModelCreator;
import engine.rule.model.inout.cashloan.DecisionInOutForm;

public class CreditCheckPercentHandler extends RuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		Logger.get().info("execute credit check percent handler!");

		String appId = message.getAppId();
		String jobName = message.getJobName();
		QueueMessagerExtensionForCL resultMessage = new QueueMessagerExtensionForCL(appId, jobName);
		resultMessage.setCreditCheckPercent(0.0);// 执行异常返回0.0%，无需提额

		try {

			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.CashLoan, jobName);

			CompositeModelCreator inModelCreator = new CompositeModelCreator();
			inModelCreator.addCreator(new CreditCheckPercentModelCreator(appId));

			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(inModelCreator, new OutModelCreator(appId));

			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			
			
			Logger.get().info("The response from HuaTeng RuleEngine = " + result.getCreditCheckPercent());
			resultMessage.setCreditCheckPercent(result.getCreditCheckPercent());

		} catch (Exception e) {
			Logger.get().warn("get credit check percent exception!", e);
		}

		return resultMessage;
	}
}
