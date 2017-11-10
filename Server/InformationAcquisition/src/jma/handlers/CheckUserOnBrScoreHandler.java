/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.BRScoreModel;
import jma.models.DataSourceResponseBase;
import jma.util.DSPApiUtils;

/**
 * 〈百融黑名单检查〉
 *
 * @author dengw
 * @version CheckUserOnBrScoreHandler.java, V1.0 2017年5月5日
 */
public class CheckUserOnBrScoreHandler extends NonBlockingJobHandler {

	@Override
	public void execute(String appId) throws RetryRequiredException {
		if (DynamicConfig.read("BRScoreSwitch", JobHandlerSwitch.Off.getValue())
				.equals(JobHandlerSwitch.Off.getValue())) {
			return;
		}
		Logger.get().info("CheckUserOnBrScoreHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		String url = StartupConfig.get("dsp.api.resource.br.score");
		try {
			//调用dsp百融接口
            DataSourceResponseBase<BRScoreModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<BRScoreModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<BRScoreModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            BRScoreModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.BR_SCORE_RAW_DATA,new Gson().toJson(model)));
            //写mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        		.isNotNullAdd(AppDerivativeVariableNames.BR_SCORE_CODE, model.getCode())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_SCORE_SWIFT_NUMBER, model.getSwift_number())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_SCORE_FLAG_SCORE, model.getFlag_score())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_SCORE_BRSCORE, model.getBrScore())
        		.build());
		} catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()));
            throw new RetryRequiredException();
        }

	}

	/**
	 * <p>
	 * 〈获取请求的用户相关信息〉
	 * </p>
	 * 
	 * @param appId
	 * @return
	 */
	protected Map<String, Object> getUserBaseInfoModel(String appId) {
		EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
		ContactObject contactObj = new ContactDao(appId).getSingle();
		Map<String, Object> param = new HashMap<String, Object>();
		if (userObj != null) {
			param.put("name", userObj.IdName);
			param.put("idNo", userObj.IdNumber);
		}
		if (contactObj != null) {
			param.put("mobile", contactObj.Content);
		}
		return param;
	}

}
