/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
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
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DataSourceResponseBase;
import jma.models.RawDataVariableNames;
import jma.models.jyzx.JYZXResponseModel;
import jma.util.DSPApiUtils;

/**
 * 〈91征信〉
 *
 * @author dengw
 * @version CheckUserOnJYZXandler.java, V1.0 2017年8月21日 下午5:28:53
 */
public class CheckUserOnJYZXHandler extends NonBlockingJobHandler{

	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("JYZXSwitch")){
			return;
		}
		Logger.get().info("CheckUserOnJYZXandler execute appId:" + appId);
		String url = StartupConfig.get("dsp.api.resource.jyzx");
		Map<String, Object> param = getUserBaseInfoModel(appId);
		try {
			//调用91征信
            DataSourceResponseBase<JYZXResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<JYZXResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<JYZXResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            JYZXResponseModel model = data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.JYZX_UNITED_RAW_DATA,new Gson().toJson(model)));
		}catch(Exception e){
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
		return param;
	}

}
