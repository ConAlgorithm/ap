package engine.util;

import java.util.Map;
import java.util.Set;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.RejectedType;
import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.dao.RuleEngineDao;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import engine.main.Configuration;

// 对数学不自信的，请不要擅自修改状态下面的代码
public final class ApplicationHelper {

	private static String AppServiceUrl;
	private static String CMCCServiceUrl;
	
	private static int MaxRetry = Configuration.getAppServiceMaxRetries();
	static{
		AppServiceUrl = Configuration.getAppServiceUrl();
		if(!AppServiceUrl.endsWith("/")) AppServiceUrl += "/";
		AppServiceUrl += "application/";
		
		CMCCServiceUrl = Configuration.getAppServiceUrl();
		if(!CMCCServiceUrl.endsWith("/")) CMCCServiceUrl += "/";
		CMCCServiceUrl += "cmcc/";
	}
	
	//获取产品ID
	public static String GetProductId(final String appId)
	{
		return HttpClientApi.CallService(MaxRetry, new ServiceHandler<Map<String, Object>, String>(){

			@Override
			public String createUrl() {///application/{id}
				return AppServiceUrl + appId;
			}

			@Override
			public Map<String, Object> OnRequest(String url) {
				return HttpClientApi.getGson(url);
			}

			@Override
			public String OnSuccess(Map<String, Object> result) {
			    String appProductModel = result.get("productId").toString();
				return appProductModel;
			}

			@Override
			public String OnError(Map<String, Object> result) {
				throw new RuntimeException("Call GetProductId of AppId: " + appId + " error!");
			}
		});
	}
	
	
	public static String GetPrincipal(final String appId)
	{
		return HttpClientApi.CallService(MaxRetry, new ServiceHandler<Map<String, Object>, String>(){

			@Override
			public String createUrl() {///application/{id}
				return AppServiceUrl + appId;
			}

			@Override
			public Map<String, Object> OnRequest(String url) {
				return HttpClientApi.getGson(url);
			}

			@Override
			public String OnSuccess(Map<String, Object> result) {
			    String appProductModel = result.get("Principal").toString();
				return appProductModel;
			}

			@Override
			public String OnError(Map<String, Object> result) {
				throw new RuntimeException("Call GetProduct of AppId: " + appId + " error!");
			}
		});
	}	
	
	

	//获取申请的文件上传状态
	public static Integer GetUploadStatus(final String appId)
	{
		return HttpClientApi.CallService(MaxRetry, new ServiceHandler<Map<String, Object>, Integer>(){

			@Override
			public String createUrl() {
				return AppServiceUrl + appId;
			}

			@Override
			public Map<String, Object> OnRequest(String url) {
				return HttpClientApi.getGson(url);
			}

			@Override
			public Integer OnSuccess(Map<String, Object> result) {
				Double status = Double.parseDouble(result.get("uploadStatus").toString());
				return status.intValue();
			}

			@Override
			public Integer OnError(Map<String, Object> result) {
				throw new RuntimeException("Call GetUploadStatus of AppId: " + appId + " error!");
			}
		});
	}

	//将需要重传的文件标志位清0，同时加入重传位
	public static boolean ResetUploadedStatus(final String appId, int needReuploadFileFlags, boolean isReupload) {

		Integer oldStatus = GetUploadStatus(appId);
		int tempStatus = (oldStatus & ~needReuploadFileFlags);
		
		if(isReupload)
		{
			tempStatus |= UploadFileStatus.ReUploaded.getValue();
		}else{
			tempStatus &= ~UploadFileStatus.ReUploaded.getValue();
		}
        final int newStatus = tempStatus;
        Logger.get().info("ApplicationHelper oldStatus " + oldStatus + " tempStatus " + tempStatus + " newStatus " + newStatus + " appId " + appId);          

		return HttpClientApi.CallService(MaxRetry, new ServiceHandler<String, Boolean>(){

			@Override
			public String createUrl() {
				return AppServiceUrl + appId;
			}

			@Override
			public String OnRequest(String url) {
				return HttpClientApi.putJson(url, CollectionUtils.mapOf("uploadStatus", newStatus));
			}

			@Override
			public Boolean OnSuccess(String result) {
				return true;
			}

			@Override
			public Boolean OnError(String result) {
				throw new RuntimeException("Call ResetUploadedStatus of AppId: " + appId + " error!");
			}
		});
	}

	//将D1合影重传标志位清0
	public static boolean ResetD1GroupPhotoUploadedStatus(final String appId)
	{
		return HttpClientApi.CallService(MaxRetry, new ServiceHandler<String, Boolean>(){

			@Override
			public String createUrl() {
				return AppServiceUrl + appId + "/photos-status?key=d1group";
			}

			@Override
			public String OnRequest(String url) {
				return HttpClientApi.putJson(url, CollectionUtils.<String, Object>newMapBuilder().build());
			}

			@Override
			public Boolean OnSuccess(String result) {
				return true;
			}

			@Override
			public Boolean OnError(String result) {
				return false;
			}});
	}
	
	//清除重传标志位
	public static boolean ClearReuploadFlag(String appId)
	{
		return ResetUploadedStatus(appId, 0, false);
	}

	//记录策略代码结果
	public static void RecordStrategyCode(String appId, String strategyCode)
	{
		if(strategyCode != null && !strategyCode.equals(""))
		{
			AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableConsts.StrategyCode, strategyCode));
		}
	}

	//记录最终拒绝原因
    public static boolean RecordRejectReason(final String appId, Set<String> rejectedReason, final RejectedType rejectedType)
    {
    	final String reason = RuleEngineDao.getPriorityMaxRule(rejectedReason);
    	if(reason == null)
    	{
    		Logger.get().warn("RejectReason is null, appId: " + appId);
    		return false;
    	}
    	return HttpClientApi.CallService(MaxRetry, new ServiceHandler<String, Boolean>(){

			@Override
			public String createUrl() {
				return AppServiceUrl + appId + "/reject";
			}

			@Override
			public String OnRequest(String url) {
				return HttpClientApi.putJson(url, CollectionUtils.mapOf("rejectedType", rejectedType.getValue(), "rejectedReason", reason));
			}

			@Override
			public Boolean OnSuccess(String result) {
				return true;
			}

			@Override
			public Boolean OnError(String result) {
				return false;
			}});
    }

    //TODO 改成调用service方式
    //获取pdl选择产品方式
    public static int getPDLProductSelectType(String appId)
	{
		return InstallmentApplicationDao.getPDLSelectType(appId).getValue();
	}

    //是否报警
    public static Boolean UserWarned(String appId) {
        return getMerchantWarnActionType(appId) != null;
    }

	public static Integer getMerchantWarnActionType(final String appId){
		return HttpClientApi.CallService(MaxRetry, new ServiceHandler<Map<String, String>, Integer>(){

			@Override
			public String createUrl() {
				return Configuration.getRiskPluginUrl() + "/Risk/Warnings/MerchantUserActions/appId=" + appId;
			}

			@Override
			@SuppressWarnings("serial")
			public Map<String, String> OnRequest(String url) {
				return new Gson().fromJson(
				    HttpClientApi.get(url), new TypeToken<Map<String, String>>() {}.getType());
			}

			@Override
			public Integer OnSuccess(Map<String, String> result) {
				return result.get("status").equals("Success")
				    ? Integer.parseInt(result.get("warnActionType").toString())
				    : null;
			}

			@Override
			public Integer OnError(Map<String, String> result) {
				return null;
			}

		});
	}

	public static boolean ResetUploadedStatus(final String appId, int buckleUploadedValue) {
		Integer oldStatus = GetUploadStatus(appId);	
        int tempStatus = oldStatus | buckleUploadedValue;
        final int newStatus = tempStatus;
        Logger.get().info("ApplicationHelper ReSet Status oldStatus" + oldStatus + "newStatus" + newStatus);          

		return HttpClientApi.CallService(MaxRetry, new ServiceHandler<String, Boolean>(){

			@Override
			public String createUrl() {
				return AppServiceUrl + appId;
			}

			@Override
			public String OnRequest(String url) {
				return HttpClientApi.putJson(url, CollectionUtils.mapOf("uploadStatus", newStatus));
			}

			@Override
			public Boolean OnSuccess(String result) {
				return true;
			}

			@Override
			public Boolean OnError(String result) {
				throw new RuntimeException("Call ResetUploadedStatus of AppId: " + appId + " error!");
			}
		});		
	}

	//判断是否为移动项目的申请
	public static boolean isCMCC(final String appId){
		return HttpClientApi.CallService(MaxRetry, new ServiceHandler<Map<String, Object>, Boolean>() {
			@Override
			public String createUrl() {
				return String.format("%sapplication/isOrNotCMCC?appId=%s", CMCCServiceUrl, appId);
			}

			@Override
			public Map<String, Object> OnRequest(String url) {
				return HttpClientApi.getGson(url);
			}

			@Override
			public Boolean OnSuccess(Map<String, Object> result) {
				return Boolean.valueOf(result.get("data").toString());
			}

			@Override
			public Boolean OnError(Map<String, Object> result) {
				return true;
			}
		});
	}
}
