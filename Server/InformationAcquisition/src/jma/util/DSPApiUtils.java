package jma.util;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;
import jma.Configuration;
import jma.RetryRequiredException;
import jma.thirdpartyservices.DataSourceAuthorizationServices;

/**
 * 调用新数据源平台访问第三方服务
 * @ClassName: DSPApiUtils 
 * @Description: apiUrl:请求url 如:/dsp/api/resource/yt   param:接口需要参数       type:结果类型
 * @author: zhangll
 * @date: 2016年7月13日 上午11:16:50
 */
public class DSPApiUtils {

    public static<T> T invokeDspApi(String appId, String apiUrl, Map<String, Object> param,Type type) throws RetryRequiredException{
        if(apiUrl == null || "".equals(apiUrl)){
            Logger.get().info("wrong parameter. request url is null");
            return null;
        }
        if(appId == null || "".equals(appId)){
            Logger.get().info("wrong parameter.appId is null");
            return null;
        }
        if(param == null){
            Logger.get().info("wrong parameter.param is null");
            return null;
        }
        param.put("appId", appId);
        try {
            Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
            String requestUrl = String.format("%s:%s%s",Configuration.getDataSourceUrl(), Configuration.getDataSourcePort(), apiUrl);
            
            Map<String,String> authResult = DataSourceAuthorizationServices.getToken();
            String tokenId = authResult.get("tokenId");
            String accountId = authResult.get("accountId");
            Long clientTime = new Date().getTime()/1000;
            
            Map<String,String> checkHeader = CollectionUtils.mapOf("content-type", "application/json");
            checkHeader.put("tokenId", tokenId);
            checkHeader.put("timeStamp",clientTime+"");
            checkHeader.put("accountId",accountId);
            String SignStr = accountId + clientTime + StartupConfig.get("catfish.datasource.seed");
            String sign =  catfish.base.EncryptUtils.md5Encode(SignStr);
            checkHeader.put("sign",sign);
            String postResult  = HttpClientApi.post(requestUrl, new StringEntity(gson.toJson(param),"UTF-8"), checkHeader);
            T res = gson.fromJson(postResult,type);//new TypeToken<DataSourceResponseBase<T>>(){}.getType());
            Logger.get().info(String.format("DspApi url=%s, param=%s, result=%s", requestUrl,gson.toJson(param), gson.toJson(res)));
            return res;
        }catch (NullPointerException re) {
            Logger.get().warn(String.format("exception occurred! url=%s, param=%s", apiUrl, new Gson().toJson(param)), re);
            throw new RetryRequiredException();
        }catch (Exception re){
            Logger.get().warn(String.format("exception occurred! url=%s, param=%s", apiUrl, new Gson().toJson(param)), re);
            throw new RetryRequiredException();
        }
    }
}
