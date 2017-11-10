package jma.thirdpartyservices;

import java.util.Date;
import java.util.Map;

import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import jma.Configuration;
import jma.models.AuthDataSourceResponse;
import jma.models.DataSourceResponseBase;

public class DataSourceAuthorizationServices {
	public static String accountId=null;
	public static String tokenId=null;
	public static Long overdue=0L;
	public static Map<String,String> getToken() {
		try{
			Long clientTime = new Date().getTime()/1000;
			if(clientTime>overdue)
			{
				Gson gson = new Gson();
				String baseUrl = Configuration.getDataSourceUrl()+":"+Configuration.getDataSourcePort();
				String authUrl = baseUrl + "/dsp/api/auth/login";
				Map<String,String> params = CollectionUtils.mapOf("accountName", "OmniPrime_DSP", "accountPwd", "e110985a77182e020eac0adf93246d1c");
				Map<String,String> authHeader = CollectionUtils.mapOf("content-type", "application/json");
				DataSourceResponseBase<AuthDataSourceResponse> authResult =  gson
						.fromJson(HttpClientApi.post(authUrl, new StringEntity(gson.toJson(params)), authHeader)
						,new TypeToken<DataSourceResponseBase<AuthDataSourceResponse>>() {}.getType());
				
				tokenId = authResult.getData().get(0).getTokenId();
				accountId = authResult.getData().get(0).getAccountId();
				overdue = clientTime+290;//默认5分钟有效
			}
		}catch(Exception ex)
		{
			Logger.get().warn(String.format("Data Source Service Authorization Error!"));
		}
		return CollectionUtils.mapOf("tokenId", tokenId,"accountId",accountId);
	}
}
