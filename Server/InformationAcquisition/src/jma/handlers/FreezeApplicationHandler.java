package jma.handlers;

import java.util.Map;

import jma.Configuration;
import jma.JobHandler;
import jma.dataservice.PDLAppService;
import jma.util.HttpUtils;
import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.httpclient.HttpClientApi;
import catfish.base.httpclient.ServiceHandler;

public class FreezeApplicationHandler extends JobHandler{
	private static int maxRetry = Configuration.getApplicationServiceMaxRetries();

	public FreezeApplicationHandler() {
    sendsResponse = true;
  }

	@Override
	public void execute(String appId){
		try{
			String userId = PDLAppService.getPDLModel(appId).getUserId();
			if(userId == null)
			{
				Logger.get().error(String.format("UserId is null of AppId: %, cannot freeze it!",appId));
				sendsResponse = false;
				return;
			}
			sendsResponse = freezeUserAccount(appId, userId);
		}catch(Exception e){
			//这里的异常很有可能是空指针异常
			Logger.get().error(String.format("Freeze Application of %s failed!", appId), e);
			sendsResponse = false;
		}

	}

	private Boolean freezeUserAccount(String appId, final String userId)
	{
		final int frozenDays = AppDerivativeVariableManager.getAsInt(appId, AppDerivativeVariableNames.HistoricalPerformanceFrozenDays, 0);
		final String frozenReason = AppDerivativeVariableManager.getAsString(appId, AppDerivativeVariableNames.HistoricalPerformanceFrozenReason);
		saveToMsAccount(userId, frozenDays, frozenReason);
		return HttpClientApi.CallService(maxRetry, new ServiceHandler<Map<String, Object>, Boolean>(){

			@Override
			public String createUrl() {
				return HttpUtils.userServiceUrl  + userId + "/frozen";
			}

			@Override
			public Map<String, Object> OnRequest(String url) {
				Map<String, ?> param = CollectionUtils.<String, Object>mapOf("frozenDay", frozenDays, "code", frozenReason);
				return HttpClientApi.postJson(url, param);
			}

			@Override
			public Boolean OnSuccess(Map<String, Object> result) {
				return true;
			}

			@Override
			public Boolean OnError(Map<String, Object> result) {
				return false;
			}
		});
	}

	private void saveToMsAccount(String userId, int frozenDays, String frozenReason) {
		try{
			HttpClientApi.CallService(maxRetry, new ServiceHandler<Map<String, Object>, Boolean>(){
	
				@Override
				public String createUrl() {
					return Configuration.getMerchantAccountUrl() + "/merchantaccount/user/" + userId + "/frozen";
				}
	
				@Override
				public Map<String, Object> OnRequest(String url) {
					Map<String, ?> param = CollectionUtils.<String, Object>mapOf("frozenDay", frozenDays, "frozenReason", frozenReason);
					return HttpClientApi.postJson(url, param);
				}
	
				@Override
				public Boolean OnSuccess(Map<String, Object> result) {
					return true;
				}
	
				@Override
				public Boolean OnError(Map<String, Object> result) {
					return false;
				}
			});
		}
		catch(Exception e){			
			Logger.get().warn("send frozen data to ms account error " + e);
		}
		
	}
}
