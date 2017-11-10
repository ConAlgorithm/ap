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
import jma.models.BlackDetails;
import jma.models.DataSourceResponseBase;
import jma.models.DerivativeVariableNames;
import jma.models.FuShuBlackListInfo;
import jma.models.FuShuBlackResponseModel;
import jma.models.RawDataVariableNames;
import jma.util.DSPApiUtils;
/**
 * 富数黑名单接口
 * @author yeyb
 * @date 2017年8月10日
 */
public class CheckUserOnFuShuHandler extends NonBlockingJobHandler{
	static final String url = StartupConfig.get("dsp.api.resource.fushu.getBlackList");
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("FuShuSwitch")){
			return;
		}
		Logger.get().info("CheckUserOnFuShuHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		try {
			//调用富数黑名单接口
            DataSourceResponseBase<FuShuBlackResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<FuShuBlackResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<FuShuBlackResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            FuShuBlackResponseModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.FUSHU_BLACKLIST,new Gson().toJson(model)));
            
          //写mongo
            if(model==null){
            	Logger.get().info("response data.get(0) is null.");
            	return ;
            }
            String returncode=model.getReturn_code();
            String msg=model.getMsg()==null?"":model.getMsg();
            
            FuShuBlackListInfo fushuData = model.getData();
            String hitCondition=fushuData.getHitCondition();
            Boolean inBlackList=fushuData.isInBlackList();
            String type=fushuData.getType()==null?"":fushuData.getType();
            Boolean inBlackList_court = false;
            Boolean inBlackList_finance = false;
            if(inBlackList == true){
            	if("court".equals(type)){
            		inBlackList_court = true;
            	}else if ("finance".equals(type)){
            		inBlackList_finance = true;
            	}else if ("court/finance".equals(type)){
            		inBlackList_court = true;
            		inBlackList_finance = true;
            	}
            }
            BlackDetails details = fushuData.getDetails();
            String duty="";
            String register_date="";
            String date="";
            String level="";
            String detail="";
            String updatetime="";
            if(details!=null){
            	duty=details.getCourt()==null?"":details.getCourt().get(0).getDuty();
            	register_date=details.getCourt()==null?"":details.getCourt().get(0).getRegister_date();
            	date=details.getFinance()==null?"":details.getFinance().get(0).getDate();
            	level=details.getFinance()==null?"":details.getFinance().get(0).getLevel();
            	detail=details.getFinance()==null?"":details.getFinance().get(0).getDetail();
            	updatetime=details.getFinance()==null?"":details.getFinance().get(0).getUpdatetime();
            }
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_RETURN_CODE,returncode)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_MSG,msg)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_HIT_CONDITION,hitCondition)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_INBLACKLIST,inBlackList)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_TYPE,type)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_INBLACKLIST_COURT,inBlackList_court)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_INBLACKLIST_FINANCE,inBlackList_finance)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_DUTY,duty)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_REGISTER_DATE,register_date)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_DATE,date)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_LEVEL,level)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_DETAIL,detail)
            		.isNotNullAdd(DerivativeVariableNames.FUSHU_UPDATETIME,updatetime)
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
