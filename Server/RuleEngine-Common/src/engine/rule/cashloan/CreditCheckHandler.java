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
import engine.rule.model.creator.cashloan.CreditCheckModelCreator;
import engine.rule.model.creator.cashloan.OutModelCreator;
import engine.rule.model.inout.cashloan.DecisionInOutForm;
import engine.util.CowfishService;

public class CreditCheckHandler extends RuleHandler<QueueMessager> {

	@Override
	public QueueMessager handle(QueueMessager message) {
		Logger.get().info("execute credit check handler!");

		String appId = message.getAppId();
		String jobName = message.getJobName();
		QueueMessagerExtensionForCL resultMessage = new QueueMessagerExtensionForCL(appId, jobName);
		resultMessage.setNeedCreditCheck(false);// 执行异常返回false，无需增信

		try {

			/* 获取用户的信用评分 */
			Integer userCreditRating = CowfishService.getUserCreditRating(message.getUserId());

			/* 获取用户成功申请CL次数 */
			Integer times = CowfishService.getAppSuccessTimes(message.getUserId());

			if (userCreditRating == null) {
				userCreditRating = 0;
			}

			if (times == null) {
				times = 0;
			}

			RuleExecutor executor = RuleManager.GetRuleExecutor(InstalmentChannel.CashLoan, jobName);

			CompositeModelCreator inModelCreator = new CompositeModelCreator();
			inModelCreator.addCreator(new CreditCheckModelCreator(appId, userCreditRating, times));

			Map<String, Object> out = executor.ExecuteRuleAndGetResponse(inModelCreator, new OutModelCreator(appId));

			DecisionInOutForm result = (DecisionInOutForm) out.get("inout_Decision");
			
			Logger.get().info("The response from HuaTeng RuleEngine = " + result.getNeedCreditCheck());
			resultMessage.setNeedCreditCheck(result.getNeedCreditCheck());

		} catch (Exception e) {
			Logger.get().warn("credit check exception!", e);
		}

		return resultMessage;
	}

}
