package jma.thirdpartyservices;

import java.util.Date;
import java.util.Map;
import catfish.base.httpclient.HttpClientApi;
import org.apache.axiom.om.util.Base64;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StringUtils;
import jma.Configuration;
import jma.RetryRequiredException;
import jma.models.DataSourceResponseBase;
import jma.models.IdentifierDataSourceResponse;

public class ZCXYIdCardDataSourceChecker extends IdCardPoliceChecker {
    /*调用新数据源平台访问第三方服务
     * URL: http://<serveradress>:<port>/dsp/api/resource/zcxy
     * ZCXYRequestModel（中诚信源数据源请求Model）
     * 属性字段	类型	是否必填	描述
     * name	String	是	姓名
     * idNo	String	是	身份证号
     * 接口 输出参数
     * 属性字段	类型	描述
     * X_IdCardIdentificationResult	String	一致/不一致
     * X_IdCardIdentificationPhotoPath	String	用户身份证头像路径
     * */

	protected IdCardPoliceChecker.Result doCheck(String name, String number,String appId)
	    throws RetryRequiredException {
		try {
			Gson gson = new Gson();
			String baseUrl = Configuration.getDataSourceUrl()+":"+Configuration.getDataSourcePort();
            
			Map<String,String> authResult = DataSourceAuthorizationServices.getToken();
			String tokenId = authResult.get("tokenId");
			String accountId = authResult.get("accountId");
			Long clientTime = new Date().getTime()/1000;
			
			String checkUrl = baseUrl +"/dsp/api/resource/zcxy";
			Map<String,String> checkHeader = CollectionUtils.mapOf("content-type", "application/json");
			checkHeader.put("tokenId", tokenId);
			checkHeader.put("timeStamp",clientTime+"");
			checkHeader.put("accountId",accountId);
			String SignStr = accountId + clientTime +"OMNIPRIME_DSP";
			String sign =  catfish.base.EncryptUtils.md5Encode(SignStr);
			checkHeader.put("sign",sign);
			Map<String,String> req = CollectionUtils.mapOf("name",name,"idNo",number,"appId",appId);
			String postResult  = HttpClientApi.post(checkUrl, new StringEntity(gson.toJson(req),"UTF-8"), checkHeader);
			DataSourceResponseBase<IdentifierDataSourceResponse> res = gson.fromJson(postResult,new TypeToken<DataSourceResponseBase<IdentifierDataSourceResponse>>() {}.getType() );
			if(res.getCode()==200)
			{
				String result = StringUtils.parseNullToEmpty(res.getData().get(0).getX_IdCardIdentificationResult());
				String path = StringUtils.parseNullToEmpty(res.getData().get(0).getX_IdCardIdentificationPhotoPath());
				return new Result(name,number,result, Base64.decode(path));
			}
			else
			{
				throw new RetryRequiredException();
			}
			
		}catch (NullPointerException re) {
	    	Logger.get().warn(String.format("Check name and number exception occurred ! name=%s,number=%s",name,number));
	        throw new RetryRequiredException();
	    }catch (Exception re){
	    	Logger.get().warn(String.format("Check name and number exception occurred(type 2) ! name=%s,number=%s",name,number));
	        throw new RetryRequiredException();
	    }
		
		
		
	}

	@Override
	protected Result doCheck(String name, String number) throws RetryRequiredException {
		return doCheck(name, number,"00000000-0000-0000-0000-000000000000");
	}
}
