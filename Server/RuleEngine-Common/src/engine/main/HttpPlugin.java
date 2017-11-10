package engine.main;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.exception.BaseExceptionCode;
import catfish.base.exception.CatfishException;
import catfish.base.queue.QueueMessager;
import catfish.framework.IListener;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;
import catfish.framework.http.IHTTPService;

public class HttpPlugin implements IListener<HttpData>, IPlugin {

	private static final String ERROR = "{\"error\": \"RuleEngine reesult is null!\"}";
	private static final String DownpaymentCheck = "DownpaymentCheck";
	private static final String JobName_IsNeedCreditCheck = "CLCreditCheck";
	private static final String JobName_GetCreditCheckPercent = "CLCreditPercent";
	private Map<Integer, RuleEngineWorker> workerMap = new HashMap<>();

	@Override
	public void init(IServiceProvider sp) {
		IHTTPService service = sp.getService(IHTTPService.class);
		service.register("ruleengine", this);
		service.register("health", new HealthService());
		workerMap.put(InstalmentChannel.App.getValue(), new POSRuleEngineWorker(sp));
		workerMap.put(InstalmentChannel.PayDayLoanApp.getValue(), new OtherRuleEngineWorker());
		workerMap.put(InstalmentChannel.CashLoan.getValue(), new OtherRuleEngineWorker());
	}

	@Override
	public void onMessage(String message, HttpData data) {
		QueueMessager messager = QueueMessager
				.fromString(data.getRequestData());
		
		Integer channel = this.getApplicationChannel(messager);
		if (channel == null) {
			Logger.get().error("Can not get channel, data: " + data);
			return;
		}
		QueueMessagerExtensionForCL CLResultExtension;
		QueueMessagerExtension resultExtension;
		QueueMessager result;
		if (messager.getJobName().equals(DownpaymentCheck)) {
			resultExtension = (QueueMessagerExtension) workerMap.get(channel)
					.execute(messager, channel);

			if (resultExtension != null) {
				Logger.get().debug(
						"Response RuleEngine result: "
								+ new Gson().toJson(resultExtension));
				data.setResponseData(((QueueMessagerExtension) resultExtension)
						.toString());
			} else {
				Logger.get().warn(
						"Response RuleEngine resultExtension error, result is null, Data: "
								+ new Gson().toJson(data));
				throw new CatfishException(BaseExceptionCode.SERVER_ERROR);
			}
		}else if(messager.getJobName().equals(JobName_IsNeedCreditCheck) || 
				messager.getJobName().equals(JobName_GetCreditCheckPercent)){
			
			Logger.get().info("request messager: " + new Gson().toJson(messager));
			
			CLResultExtension = (QueueMessagerExtensionForCL) workerMap.get(channel).execute(messager, channel);
			
			if (CLResultExtension != null) {
				
				Logger.get().debug("Response RuleEngine result: " + new Gson().toJson(CLResultExtension));
				data.setResponseData(CLResultExtension.toString());
				
			} else {
				
				Logger.get().warn("Response RuleEngine resultExtension error, result is null, Data: "
								+ new Gson().toJson(data));
				throw new CatfishException(BaseExceptionCode.SERVER_ERROR);
			}
			
		}else {
			result = workerMap.get(channel).execute(messager, channel);
			if (result != null) {
				Logger.get().debug(
						"Response RuleEngine result: "
								+ new Gson().toJson(result));
				data.setResponseData(((QueueMessager) result)
						.toString());
			} else {
				Logger.get().warn(
						"Response RuleEngine result error, result is null, Data: "
								+ new Gson().toJson(data));
				// data.setResponseData(ERROR);
				throw new CatfishException(BaseExceptionCode.SERVER_ERROR);
			}
		}

	}

	// TODO
	// 因为目前三个产品线是一起的，所以需要单独处理
	private Integer getApplicationChannel(QueueMessager messager) {
	      String appId = messager.getAppId();
	      InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
	      if (app != null) {
	        return app.InstalmentChannel;
	      }
	
	      String channel = messager.getChannel();
	      if (channel.equalsIgnoreCase("cashloan")) {
	        return InstalmentChannel.CashLoan.getValue();
	      }	
		return null;
	}
}
