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
import catfish.base.business.dao.PaymentInfoDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.object.PaymentObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import jma.AppDerivativeVariablesBuilder;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.models.DataSourceResponseBase;
import jma.models.DerivativeVariableNames;
import jma.models.RawDataVariableNames;
import jma.models.SmallCreditResponseModel;
import jma.util.DSPApiUtils;
/**
 * 泰融小额信贷接口
 * @author yeyb
 * @date 2017年8月10日
 */
public class CheckUserOnTaiRongHandler extends NonBlockingJobHandler{
	static final String url = StartupConfig.get("dsp.api.resource.tairong.smallCredit");
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(!isOpenSwitch("TaiRongSwitch")){
			return;
		}
		Logger.get().info("CheckUserOnTaiRongHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		try {
			//调用泰融小额信贷
            DataSourceResponseBase<SmallCreditResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<SmallCreditResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<SmallCreditResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            SmallCreditResponseModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.TAIRONG_SMALLCREDIT,new Gson().toJson(model)));
          //写mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
            		.isNotNullAdd(DerivativeVariableNames.SCORE,model.getResultData().getScore())
            		.isNotNullAdd(DerivativeVariableNames.MONTHLYINCOME,model.getResultData().getMonthlyIncome())
            		.isNotNullAdd(DerivativeVariableNames.JOBSTABILITY,model.getResultData().getJobStability())
            		.isNotNullAdd(DerivativeVariableNames.CASHSCORE,model.getResultData().getCashScore())
            		.isNotNullAdd(DerivativeVariableNames.CASHMODELCLASSIFICATION,model.getResultData().getCashModelClassIfication())
            		.isNotNullAdd(DerivativeVariableNames.CONSUMERTRENDS,model.getResultData().getConsumerTrends())
            		.isNotNullAdd(DerivativeVariableNames.SPENDINGPOWER,model.getResultData().getSpendingPower())
            		.isNotNullAdd(DerivativeVariableNames.CUSTOMERVALUE,model.getResultData().getCustomerValue())
            		.isNotNullAdd(DerivativeVariableNames.CONSUMPTIONSURPLUS,model.getResultData().getConsumptionSurplus())
            		.isNotNullAdd(DerivativeVariableNames.CONSUMPTIONSURPLUSCLASS,model.getResultData().getConsumptionSurplusClass())
            		.isNotNullAdd(DerivativeVariableNames.CONSUMPTIONPREFERENCES,model.getResultData().getConsumptionPreferences())
            		.isNotNullAdd(DerivativeVariableNames.CONSUMPTIONSTABILITY,model.getResultData().getConsumptionStability())
            		.isNotNullAdd(DerivativeVariableNames.KEEPCARD,model.getResultData().isKeepCard())
            		.isNotNullAdd(DerivativeVariableNames.CALLSTABILITY,model.getResultData().getCallStability())
            		.build());

		} catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()), e);
            throw new RetryRequiredException();
        }
	}
	
	public Map<String, Object> getUserBaseInfoModel(String appId) {
		EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
		ContactObject contactObj = new ContactDao(appId).getSingle();
		PaymentObject payment=new PaymentInfoDao(appId).getSingle();
		Map<String, Object> param = new HashMap<String, Object>();
		if (userObj != null) {
			param.put("name", userObj.IdName);
			param.put("idNo", userObj.IdNumber);
		}
		if (contactObj != null) {
			param.put("mobile", contactObj.Content);
		}
		if(payment!=null){
			param.put("bankNo",payment.BankAccount);
		}
		return param;
	}
}
