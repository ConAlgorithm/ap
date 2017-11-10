/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.dataservice.AppDataService;
import jma.models.DataSourceResponseBase;
import jma.models.DerivativeVariableNames;
import jma.models.RawDataVariableNames;
import jma.models.SuanHuaResponseModel;
import jma.util.DSPApiUtils;

/**
 * 〈算话征信〉
 *
 * @author dengw
 * @version CheckUserOnSuanHuaandler.java, V1.0 2017年8月21日 下午5:34:46
 */
public class CheckUserOnSuanHuaHandler extends NonBlockingJobHandler{

	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("SuanHuaSwitch")){
			return;
		}
		Logger.get().info("CheckUserOnSuanHuaHandler execute appId:" + appId);
		String url = StartupConfig.get("dsp.api.resource.shzx.singleApply");
		Map<String, Object> param = getUserBaseInfoModel(appId);
		try {
			//调用算话征信
            DataSourceResponseBase<SuanHuaResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<SuanHuaResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<SuanHuaResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            SuanHuaResponseModel model = data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.SUANHUA_UNITED_RAW_DATA,new Gson().toJson(model)));
            //写mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPNUM, model.getAppNum())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_FRDCHECKTYPE, model.getFrdCheckType())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPDATE, model.getAppDate())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPNO, model.getAppNo())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPVALID, model.getAppValid())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPWARN, model.getAppWarn())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPRST, model.getAppRst())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPRSTREMARK, model.getAppRstRemark())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPFACERST, model.getAppFaceRst())
        		.isNotNullAdd(DerivativeVariableNames.SUANHUA_APPBLACKRST, model.getAppBlackRst())
        		.build());
		}catch(Exception e){
			Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()), e);
            throw new RetryRequiredException();
		}
	}
	
	public Map<String, Object> getUserBaseInfoModel(String appId) {
		EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
		ContactObject contactObj = new ContactDao(appId).getSingle();
		Date startedOn = AppDataService.getInstallmentStartedOnBy(appId);
		Map<String, Object> param = new HashMap<String, Object>();
		if (userObj != null) {
			param.put("custName", userObj.IdName);
			param.put("idNum", userObj.IdNumber);
		}
		if (contactObj != null) {
			param.put("cellPhone", contactObj.Content);
		}
		param.put("appNum", appId);
		if(startedOn != null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); 
			param.put("appDate", sdf.format(startedOn));
		}
		return param;
	}

}
