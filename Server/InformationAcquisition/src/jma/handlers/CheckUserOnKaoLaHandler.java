package jma.handlers;

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
import jma.models.BlackListResponseModel;
import jma.models.BlacklistDetailList;
import jma.models.DataSourceResponseBase;
import jma.models.DerivativeVariableNames;
import jma.models.RawDataVariableNames;
import jma.util.DSPApiUtils;
/**
 * 考拉黑名单
 * @author yeyb
 * @date 2017年8月10日
 */
public class CheckUserOnKaoLaHandler extends NonBlockingJobHandler{
	static final String url = StartupConfig.get("dsp.api.resource.kaola.queryBlaskList");
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("KaoLaSwitch")){
			return;
		}
		Logger.get().info("CheckUserOnKaoLaHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		try {
			//调用考拉黑名单接口
            DataSourceResponseBase<BlackListResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<BlackListResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<BlackListResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            BlackListResponseModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.KAOLA_BLACKLIST,new Gson().toJson(model)));
            String result=model.getResult();
            String msg=model.getMessage();
            List<BlacklistDetailList> blacklistDetailList = model.getBlacklistDetailList();
            String borrowDate=blacklistDetailList==null?"":blacklistDetailList.get(0).getBorrowDate();
            String borrowAmount=blacklistDetailList==null?"":blacklistDetailList.get(0).getBorrowAmount();
            String borrowPeriod=blacklistDetailList==null?"":blacklistDetailList.get(0).getBorrowPeriod();
            String overdueDate=blacklistDetailList==null?"":blacklistDetailList.get(0).getOverdueDate();
            String overdueLevel=blacklistDetailList==null?"":blacklistDetailList.get(0).getOverdueLevel();
            String overdueAmount=blacklistDetailList==null?"":blacklistDetailList.get(0).getOverdueAmount();
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_RESULT,result)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_MSG,msg)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_BORROW_DATE,borrowDate)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_BORROW_AMOUNT,borrowAmount)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_BORROW_PERIOD,borrowPeriod)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_OVERDUE_DATE,overdueDate)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_OVERDUE_LEVER,overdueLevel)
            		.isNotNullAdd(DerivativeVariableNames.KAOLA_OVERDUE_AMOUNT,overdueAmount)
            		.build());
		} catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()), e);
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
		
		return param;
	}
}
