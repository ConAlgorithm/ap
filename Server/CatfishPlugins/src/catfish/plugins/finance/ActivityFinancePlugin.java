package catfish.plugins.finance;


//import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

//import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.framework.IListener;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.http.HttpData;
import catfish.framework.http.IHTTPService;

public class ActivityFinancePlugin implements IPlugin,  IListener<HttpData>{

	public static class NotificationKeys {
	    public static final String NOTIFICATION_NAME = "NotificationName";
	    public static final String APP_ID = "AppId";
	}

//	private static String httpServer_GenerateSchedule = "RepaymentSchedule";
	private static String httpServer_GetSchedule = "GetRepaymentSchedule";
//	private static String httpServer_GetMonthlyPay = "GetMonthlyPay";



	@Override
	public void init(IServiceProvider sp) {
		IHTTPService restService = (IHTTPService) sp.getService(IHTTPService.class);
//		restService.register(httpServer_GenerateSchedule, this);
		restService.register(httpServer_GetSchedule, this);
	}

	@Override
	public void onMessage(String message, HttpData data) {
		 Logger.get().info("Http Request:" + data.getRequestData());

		 String respondData;
//		 if (httpServer_GenerateSchedule.equals(message))
//		 {
//			 respondData = handleGenerateScheduleRequest(data);
//		 }
//		 else 
		   if (httpServer_GetSchedule.equals(message))
		 {
			 respondData = handleGetScheduleRequest(data);
		 }
//		 else if (httpServer_GetMonthlyPay.equals(message))
//		 {
//			 respondData = handleGetMonthlyPayRequest(data);
//		 }
		 else
		 {
			 Logger.get().warn("Http Request's url not match any!");
			 return;
		 }

		 Logger.get().info("Http Respond:" + respondData);
		 data.setResponseData(respondData);
	}

	///
	// 生成还款计划表（不写入数据库）， 供三方协议 调用
	///
	public String handleGetScheduleRequest(HttpData data)
	{
		 @SuppressWarnings("unchecked")
		 Map<String, Object> request = (Map<String, Object>) new Gson().fromJson(data.getRequestData(), Map.class);
		 String appId = (String) request.get("AppId");

		 List<RepaymentItem> result = new ArrayList<RepaymentItem>();
		 if (appId == null || appId.length()==0){
			 Logger.get().info("appId is null");
		 }
		 else {
			 result = RepaymentSchedule.generateRepaymentSchedule(appId);
		 }
		 String respondData = new Gson().toJson(result);
		 return respondData;
	}

	///
	// 生成还款计划表（写入数据库）， 供手工放款 调用
	///
//	public String handleGenerateScheduleRequest(HttpData data)
//	{
//		 String respondData;
//		 @SuppressWarnings("unchecked")
//		 Map<String, Object> request = (Map<String, Object>) new Gson().fromJson(data.getRequestData(), Map.class);
//		 String appId = (String) request.get("AppId");
//		 String adminUserId = (String) request.get("AdminUserId");
//
//		 if (appId == null || appId.length()==0){
//			 Logger.get().info("appId is null");
//			 respondData = new Gson().toJson(CollectionUtils.mapOf("Status", "Failed"));
//		 }
//		 else {
//			 boolean result = RepaymentSchedule.generateAndUpdateDB(appId, adminUserId);
//			 respondData = new Gson().toJson(CollectionUtils.mapOf("Status", result ? "Success" : "Failed"));
//		 }
//		return respondData;
//	}

	///
	// 生成月平均还款额， 目前无人 调用
	///
//	public String handleGetMonthlyPayRequest(HttpData data)
//	{
//		 @SuppressWarnings("unchecked")
//		 Map<String, Object> request = (Map<String, Object>) new Gson().fromJson(data.getRequestData(), Map.class);
//		 String appId = (String) request.get("AppId");
//
//		 BigDecimal result = BigDecimal.ZERO;
//		 if (appId == null || appId.length()==0){
//			 Logger.get().info("appId is null");
//		 }
//		 else {
//			 result = RepaymentSchedule.generateMonthlyPay(appId);
//		 }
//		 String respondData = new Gson().toJson(CollectionUtils.mapOf("Monthlypay", result));
//		 return respondData;
//	}
}
