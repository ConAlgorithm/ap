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
import jma.models.IdentityInforResponsemodel;
import jma.models.RawDataVariableNames;
import jma.util.DSPApiUtils;
/**
 * 考拉身份回查
 * @author yeyb
 * @date 2017年8月10日
 */
public class CheckUserOnKaoLaIdentityHandler extends NonBlockingJobHandler{
	static final String url = StartupConfig.get("dsp.api.resource.kaola.queryIdentityInfor");
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("KaoLaIdentitySwitch")){
			return;
		}
		Logger.get().info("CheckUserOnKaoLaIdentityHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		try {
			//调用考拉考拉身份回查接口
            DataSourceResponseBase<IdentityInforResponsemodel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<IdentityInforResponsemodel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<IdentityInforResponsemodel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            IdentityInforResponsemodel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.KAOLA_IDENTIRYINFOR,new Gson().toJson(model)));
            //写入mongo
            String toTalprdGrpNum=model.getStatisticsList()==null?"":String.valueOf(model.getStatisticsList().size());
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_RESULT,model.getResult())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_MSG,model.getMessage())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_IDCARD_NAME,model.getIdCardName())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_IDCARD_CODE,model.getIdCardCode())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_TIME_INTERVAL,model.getTimeInterval())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_TOTAL_TRANSNUM,model.getToTalTransNum())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_LAST_TRANSTIME,model.getLastTransTime())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_LAST_INDUSTRY,model.getLastIndustry())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_LAST_PRDGRP_NAME,model.getLastPrdGrpName())
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_IDENTIRY_TOTAL_PRDGRP_NUM,toTalprdGrpNum)
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
		return param;
	}
}
