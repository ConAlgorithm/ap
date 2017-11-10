package jma.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.cowfish.application.model.ApplicationModel;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DataSourceResponseBase;
import jma.models.DerivativeVariableNames;
import jma.models.RawDataVariableNames;
import jma.models.ShuWeiRiskResponseModel;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;
import jma.util.DSPApiUtils;

/**
 * LTV接入数维黑名单查询
 * @author yeyb
 * @date 2017年8月24日
 */
public class CheckUserOnShuWeiForCLHandler extends NonBlockingJobHandler{

	static final String url = StartupConfig.get("dsp.api.resource.shuwei.queryRisk");
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("ShuWeiSwitch")){
			return;
		}
		Logger.get().info("CheckUserOnShuWeiForCLHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		if(param==null){
			Logger.get().info("CheckUserOnShuWeiForCLHandler not execute,because the dsp param is null, appId:" + appId);
			return;
		}
		try {
			//调用数维黑名单查询
            DataSourceResponseBase<ShuWeiRiskResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<ShuWeiRiskResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<ShuWeiRiskResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            ShuWeiRiskResponseModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.SHUWEI_QUERYRISK,new Gson().toJson(model)));
           if(model.getData()==null){
        	   Logger.get().info("response data.get(0).ShuWeiData is null.");
        	   return ;
           }
          //写mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            		.isNotNullAdd(DerivativeVariableNames.SHUWEIRISK_SPECIALLEVEL,model.getData().getSpecialLevel())
            		.build());
		} catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()), e);
            throw new RetryRequiredException();
        }
		
	}
	
	public Map<String, Object> getUserBaseInfoModel(String appId) {
        ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
        if(clApp==null){
        	Logger.get().warn(String.format("***get null ApplicationModel from cowfishHost,appId=%s",appId));
        	return null;
        }
        User user = UserResourceFactory.getUser(clApp.userId);
        Account userAccount = UserResourceFactory.getUserAccount(clApp.userId);
        Map<String, Object> param = new HashMap<String, Object>();
        if(user!=null){
        	param.put("name", user.getIdName());
        	param.put("idNo", user.getIdNumber());
        }
        if(userAccount!=null){
        	param.put("mobile", userAccount.getMobile());
        }
		return param;
	}

}
