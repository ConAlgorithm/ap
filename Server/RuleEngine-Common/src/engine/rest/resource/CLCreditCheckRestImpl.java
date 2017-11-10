package engine.rest.resource;

import java.util.Map;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.queue.QueueMessager;
import engine.rest.model.CreditModel;
import engine.rule.RuleHandlerConfigManager;
import engine.util.CowfishService;

public class CLCreditCheckRestImpl implements CLCreditCheckRest {

	private static final String RULENAME_ISNEEDCREDITCHECK = "isneedcreditcheck";
	
	private static final String RULENAME_GETCREDITCHECKPERCENT = "getcreditcheckpercent";

	@Override
	@SuppressWarnings("unchecked")
	public CreditModel<Boolean> isNeedCreditCheck(String userId, String appId) {

		Logger.get().info("isNeedCreditCheck >>> Rule requsets: userId = " + userId + ", appId=" + appId);

		CreditModel<Boolean> creditModel = new CreditModel<Boolean>();
		creditModel.setResult(false);

		try {
			
			/* 获取用户的信用评分，并存入mongo */
			int userCreditRating = CowfishService.getUserCreditRating(userId);
			AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableNames.CL_CREDITRATING, userCreditRating));
			
			/* 获取用户成功申请CL次数，并存入mongo */
			int times = CowfishService.getAppSuccessTimes(userId);
			AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableNames.CL_APPSUCCESSTIMES, times));
			
			/* 同盾数据是FlowContoller执行JOB CheckUserCreditOnTd时，由IA保存到mongo */
			/* 执行规则引擎 */
			QueueMessager queueMessager = new QueueMessager(appId, RULENAME_ISNEEDCREDITCHECK);
			
			Map<String,Object> result = (Map<String,Object>)RuleHandlerConfigManager.getRuleHandler("creditcheck", InstalmentChannel.CashLoan.getValue()).handle(queueMessager);
			
			
			creditModel.setResult((Boolean)result.get("isneedcreditcheck"));
			
			Logger.get().info(
					"isNeedCreditCheck >>> Rule Result = " + new Gson().toJson(creditModel) + "; Rule requsets: userId = " + userId + ", appId=" + appId);
			
		} catch (Exception e) {
			Logger.get().warn("isNeedCreditCheck >>> Execute rule exception", e);
		}
		
		return creditModel;
	}

	@Override
	@SuppressWarnings("unchecked")
	public CreditModel<Double> getCreditCheckPercent(String userId, String appId) {

		Logger.get().info("getCreditCheckPercent >>> Rule requsets: userId = " + userId + ", appId=" + appId);

		/* 发生异常，默认返回0.0 */
		CreditModel<Double> creditModel = new CreditModel<Double>();
		creditModel.setResult(0.0);

		try {
			
			/* 聚信力数据是FlowContoller执行JOB CheckUserCreditOn3rdParty时，由IA保存到mongo */
			/* 执行规则引擎 */
			QueueMessager queueMessager = new QueueMessager(appId, RULENAME_GETCREDITCHECKPERCENT);
			
			Map<String,Object> result = (Map<String,Object>)RuleHandlerConfigManager.getRuleHandler("creditcheck", InstalmentChannel.CashLoan.getValue()).handle(queueMessager);
			
			
			creditModel.setResult((Double)result.get("creditcheckpercent"));
			
			Logger.get().info(
					"getCreditCheckPercent >>> Rule Result = " + new Gson().toJson(creditModel) + "; Rule requsets: userId = " + userId + ", appId=" + appId);
			
		} catch (Exception e) {
			Logger.get().warn("getCreditCheckPercent >>> Execute rule exception", e);
		}
		
		return creditModel;
	}

}
