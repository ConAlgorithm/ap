package jma.thirdpartyservices;

import java.util.HashSet;
import java.util.Set;

import thirdparty.config.YLZHConfiguration;
import thirdparty.ylzh.request.CorrespondingRelationVerifyRequest;
import thirdparty.ylzh.request.TradeReportRequest;
import thirdparty.ylzh.request.cardownerverify.CardOwnerIdentityVerifyRequest;
import thirdparty.ylzh.response.CorrespondingRelationVerifyResponse;
import thirdparty.ylzh.response.ResponseCode;
import thirdparty.ylzh.response.TradeReportResponse;
import thirdparty.ylzh.response.cardownerverify.CardOwnerIdentityVerifyResponse;
import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.business.common.ylzh.ResponseState;
import catfish.base.httpclient.DefaultClient;
import catfish.base.httpclient.DefaultRequestBuilder;
import catfish.base.httpclient.object.BaseObject;

//收费项目不进行retry，免费的情况进行retry
public class YLZHBankCardChecker {

	private static Set<String> errorCodeSet = new HashSet<>();
	static{
		errorCodeSet.add(ResponseCode.AcountNotExist.getValue());
		errorCodeSet.add(ResponseCode.AcessDenied.getValue());
		errorCodeSet.add(ResponseCode.ResourceNotExit.getValue());
		errorCodeSet.add(ResponseCode.ParamError.getValue());
		errorCodeSet.add(ResponseCode.SignCheckFailed.getValue());
		errorCodeSet.add(ResponseCode.ServerError.getValue());
	}
	public static CorrespondingRelationVerifyResponse correspondingRelationVerify(CorrespondingRelationVerifyRequest request) throws Exception
	{
		int retryTimes = YLZHConfiguration.getYlzhMaxRetries();
		CorrespondingRelationVerifyResponse response = null;
	    while(retryTimes > 0)
	    {
		   DefaultClient client = new DefaultClient(YLZHConfiguration.getYlzhCorrespondingRelationUrl(), new DefaultRequestBuilder(request));
		   String responseStr = client.httpGet();
		   Logger.get().info("get CorrespondingRelationVerifyResponse :"+responseStr);
		   response = BaseObject.fromString(responseStr, CorrespondingRelationVerifyResponse.class);
		   //如果提交成功并且验证成功或验证失败才收费，否则不收费，执行重试
	       if(response.getResCode().equals(ResponseCode.SubmitSuccess.getValue())
			   && (response.getVerifyState().equals(ResponseState.VerifySuccess.getValue()) || response.getVerifyState().equals(ResponseState.VerifyFailed.getValue()) )
		   )
			{
			    break;
			}
	       else{
	    	    /*if(errorCodeSet.contains(response.getResCode()))
	    	       Logger.get().warn(String.format("Get correspondingRelationVerify Result of 银联智惠  error, the responseCode is %s, the responseMessage is %s, the responseState is %s", response.getResCode(), response.getResMsg(), response.getVerifyState()));
	    	    else*/
	    	   retryTimes --;
    	       Logger.get().warn(String.format("Get correspondingRelationVerify Result of 银联智惠  warning, the responseCode is %s, the responseMessage is %s, the responseState is %s,the remaining retryTimes is %d,the next request uri is :%s", response.getResCode(), response.getResMsg(), response.getVerifyState(),retryTimes,YLZHConfiguration.getYlzhCorrespondingRelationUrl()));
    	       ThreadUtils.sleepInSeconds(1);
	       }
	    }
	    if(retryTimes<=0){
	    	Logger.get().error(String.format("Retrytimes exhausted ! Get correspondingRelationVerify Result of 银联智惠  error, the responseCode is %s, the responseMessage is %s, the responseState is %s", response.getResCode(), response.getResMsg(), response.getVerifyState()));
	    }
	    return response;
	}
	
	public static TradeReportResponse getTradeReport(TradeReportRequest request) throws Exception
	{
		int retryTimes = YLZHConfiguration.getYlzhMaxRetries();
		TradeReportResponse response = null;
		while(retryTimes > 0)
		{
			DefaultClient client = new DefaultClient(YLZHConfiguration.getYlzhTradeReportUrl(), new DefaultRequestBuilder(request));
			String responseStr = client.httpGet();
			Logger.get().info(String.format("get TradeReportResponse:",responseStr));
			response = BaseObject.fromString(responseStr, TradeReportResponse.class);
			//提交成功时才收费，否则不收费，重试
			if(response.getResCode().equals(ResponseCode.SubmitSuccess.getValue()))
			{
				break;
			}
			else{
				/*if(errorCodeSet.contains(response.getResCode()))
				    Logger.get().error(String.format("Get TradeReport Result of 银联智惠  error, the responseCode is %s, the responseMessage is %s", response.getResCode(), response.getResMsg()));
				else*/
					Logger.get().warn(String.format("Get TradeReport Result of 银联智惠  warning, the responseCode is %s, the responseMessage is %s, the remaining retryTimes is %d,the next request uri is : %s", response.getResCode(), response.getResMsg(),retryTimes,YLZHConfiguration.getYlzhTradeReportUrl()));
				retryTimes --;
				ThreadUtils.sleepInSeconds(1);
			}
		}
		if(retryTimes<=0){
	    	Logger.get().error(String.format("Retrytimes exhausted ! Get TradeReport Result of 银联智惠  error, the responseCode is %s, the responseMessage is %s", response.getResCode(), response.getResMsg()));
	    }
		return response;
	}
	
	public static CardOwnerIdentityVerifyResponse cardOwnerIdentityVerify(CardOwnerIdentityVerifyRequest request) throws Exception
	{
		CardOwnerIdentityVerifyResponse response = null;
		DefaultClient client = new DefaultClient(YLZHConfiguration.getYlzhCardOwnerUrl(), new DefaultRequestBuilder(request));
		String responseStr = client.httpGet();
		Logger.get().info("get CardOwnerIdentityVerifyResponse:"+responseStr);
		response = BaseObject.fromString(responseStr, CardOwnerIdentityVerifyResponse.class);
		return response;
	}
}
