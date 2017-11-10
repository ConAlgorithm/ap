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
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.BR4CreditResponseModel;
import jma.models.DataSourceResponseBase;
import jma.models.RawDataVariableNames;
import jma.util.DSPApiUtils;
/**
 * 百融信用评估接入PSL
 * @author yeyb
 *
 */
public class CheckUserOnBrCreditHandler extends NonBlockingJobHandler{

	@Override
	public void execute(String appId) throws RetryRequiredException {
		if (DynamicConfig.read("BRCreditSwitch", JobHandlerSwitch.Off.getValue())
				.equals(JobHandlerSwitch.Off.getValue())) {
			return;
		}
		Logger.get().info("CheckUserOnBrCreditHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		String url = StartupConfig.get("dsp.api.resource.br.credit");
		try {
			//调用dsp百融信用接口
            DataSourceResponseBase<BR4CreditResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<BR4CreditResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<BR4CreditResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            BR4CreditResponseModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.BR_CREDIT,new Gson().toJson(model)));
		} catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()));
            throw new RetryRequiredException();
        }
	}
	
	public Map<String, Object> getUserBaseInfoModel(String appId) {
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
		param.put("creditType", "Stability_c,Consumption_c,Media_c,SocietyRelation");
		return param;
	}

}
