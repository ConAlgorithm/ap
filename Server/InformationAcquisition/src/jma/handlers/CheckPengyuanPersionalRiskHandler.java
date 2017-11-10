package jma.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DataSourceResponseBase;
import jma.models.PYResponseModel;
import jma.models.RawDataVariableNames;
import jma.util.DSPApiUtils;

public class CheckPengyuanPersionalRiskHandler extends NonBlockingJobHandler {
	
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if (DynamicConfig.read("PYCheckSwitch", JobHandlerSwitch.Off.getValue())
				.equals(JobHandlerSwitch.Off.getValue())) {
			return;
		}
		Logger.get().info("CheckPengyuanPersionalRiskHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		String url = StartupConfig.get("dsp.api.resource.py");
		try {
			//调用dsp百融信用接口
            DataSourceResponseBase<PYResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<PYResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<PYResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            PYResponseModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.PY,new Gson().toJson(model)));
            
          //写mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            		.add(AppDerivativeVariableNames.PY_JUSTICE_CASE_INFO_NUMBER, model.getX_PY_JusticeCaseInfoNumber())
                    .add(AppDerivativeVariableNames.PY_JUSTICE_EXECUTE_INFO_NUMBER, model.getX_PY_JusticeExecuteInfoNumber())
                    .add(AppDerivativeVariableNames.PY_JUSTICE_LOSE_TRACK_INFO_NUMBER, model.getX_PY_JusticeLoseTrackInfoNumber())
                    .add(AppDerivativeVariableNames.PY_TAX_ADMINISTRATION_LAW_INFO_NUMBER, model.getX_PY_TaxAdministrationLawInfoNumber())
                    .add(AppDerivativeVariableNames.PY_COLLECTION_NOTICE_INFO_NUMBER, model.getX_PY_CollectionNoticeInfoNumber())
                    .add(AppDerivativeVariableNames.PY_INTERNET_LOAN_OVERDUE_INFO_NUMBER, model.getX_PY_InternetLoanOverdueInfoNumber())
            		.build());
            
		} catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()));
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
