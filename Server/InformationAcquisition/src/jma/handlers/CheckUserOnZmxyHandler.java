package jma.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DataSourceResponseBase;
import jma.models.DerivativeVariableNames;
import jma.models.RawDataVariableNames;
import jma.models.ZMXYCreditScoreResponseModel;
import jma.models.enums.ZmxyAuthCode;
import jma.models.enums.ZmxyChannel;
import jma.models.enums.ZmxyInterfaceType;
import jma.util.DSPApiUtils;
/**
 * pos接入芝麻信用
 * @author yeyb
 * @date 2017年8月28日
 */
public class CheckUserOnZmxyHandler extends NonBlockingJobHandler{
	static final String url = StartupConfig.get("dsp.api.resource.zmxy");
	static final String zmxyAppId=StartupConfig.get("zmxyAppId");
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("ZmxySwitch")){
			return;
		}
		Logger.get().info("CheckUserOnZmxyHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		try {
			//芝麻信用分
            DataSourceResponseBase<ZMXYCreditScoreResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<ZMXYCreditScoreResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<ZMXYCreditScoreResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            ZMXYCreditScoreResponseModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.ZMXY_SCORE,new Gson().toJson(model)));
          //写mongo
            int score=("".equals(model.getZmScore())||model.getZmScore()==null)?-1:Integer.parseInt(model.getZmScore());
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            		.isNotNullAdd(DerivativeVariableNames.ZMXY_SCORE,score)
            		.build());

		} catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()), e);
            throw new RetryRequiredException();
        }
	}
	
	public Map<String, Object> getUserBaseInfoModel(String appId) {
		EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
		Map<String, Object> param = new HashMap<String, Object>();
		if (userObj != null) {
			param.put("name", userObj.IdName);
			param.put("idNo", userObj.IdNumber);
		}
		//芝麻信用dsp其他参数配置
		param.put("authCode", ZmxyAuthCode.SDK.getValue());
		param.put("channel", ZmxyChannel.appsdk.getValue());
		param.put("interfaceType", ZmxyInterfaceType.CreditScore.getValue());
		param.put("zmxyAppId", zmxyAppId);
		return param;
	}

}
