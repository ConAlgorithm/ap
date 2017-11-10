/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

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
import jma.handlers.preprocess.model.UserBaseInfoModel;
import jma.models.DataSourceResponseBase;
import jma.models.QHZXModel;
import jma.util.DSPApiUtils;

/**
 * 〈前海征信〉
 *
 * @author hwei
 * @version CheckUserOnQhzxHandler.java, V1.0 2016年11月21日 下午2:32:18
 */
public class CheckUserOnQhzxHandler extends NonBlockingJobHandler {

    /**
     * <p>〈将相关数据送入前海返回结果落地数据库〉</p>
     * 
     * @param appId
     * @throws RetryRequiredException
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute(String appId) throws RetryRequiredException {
        if (DynamicConfig.read("QHZXSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue())) {
            return;
        }
        Logger.get().info("CheckUserOnQhzxHandler execute appId:" + appId);
        UserBaseInfoModel requestModel = getUserBaseInfoModel(appId);
        String url = StartupConfig.get("dsp.api.resource.qhzx");
        try {
            //请求数据转换
            Map<String, Object> param = BeanUtils.describe(requestModel);
            //调用Dsp接口
            DataSourceResponseBase<QHZXModel> res = DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<QHZXModel>>() {
            }.getType());

            //如果前海结果存在将结果存入mongo衍伸变量
            if (res.getCode() == 200) {
                RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.QHZX_UNITED_RAW_DATA, new Gson().toJson(res)));
            }else{
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }

            List<QHZXModel> data = res.getData();
            if (data == null || data.size() == 0) {
                Logger.get().info("response data is null.");
                return;
            }
            QHZXModel model = data.get(0);

            //写mongo
            AppDerivativeVariableManager
                .addVariables(new AppDerivativeVariablesBuilder(appId)
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_DATA_LATEST_BUILDTIME, model.getX_QHZX_LatestDataBuildTime())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HASBLACKLIST, model.getX_QHZX_HasBlackList())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HASBEENEXECUTED, model.getX_QHZX_HasBeenExcuted())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_BREAKPROMISE, model.getX_QHZX_BreakPromise())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_BREAKPROMISEBEENEXECUTED, model.getX_QHZX_BreakPromiseBeenExecuted())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_MAXIMIZEOVERDUEDAY, model.getX_QHZX_MaximizeOverdueDay())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_MAXIMIZEMONEYBOUND, model.getX_QHZX_MoneyBound())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_CREDIT_OVERDUE_RISK, model.getX_QHZX_HasCreditOverdueRisk())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_ADMINISTRATION_NEGATIVE_RISK, model.getX_QHZX_HasAdministrationNegativeRisk())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_FRAUD_RISK, model.getX_QHZX_HasFraudRisk())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_RISK_SCORE, model.getX_QHZX_RiskScore())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_SERIOUS_TRAFFIC_VIOLATION_RISK, model.getX_QHZX_HasSeriousTrafficViolationRisk())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_MOBILE_FRAUD_RISK, model.getX_QHZX_HasMobileFraudRisk())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_BANK_CARD_FRAUD_RISK, model.getX_QHZX_HasBankCardFraudRisk())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_ID_CARD_FRAUD_RISK, model.getX_QHZX_HasIdCardFraudRisk())
                    .isNotNullAdd(AppDerivativeVariableNames.QHZX_DATA_STATUS, model.getX_QHZX_DataStatus()).build());
        } catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, requestModel.toString()));
            throw new RetryRequiredException();
        }

    }

    /**
     * <p>〈获取请求的用户相关信息〉</p>
     * 
     * @param appId
     * @return
     */
    protected UserBaseInfoModel getUserBaseInfoModel(String appId) {
        UserBaseInfoModel userBaseInfoModel = new UserBaseInfoModel();
        EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
        ContactObject contactObj = new ContactDao(appId).getSingle();
        userBaseInfoModel.setName(userObj.IdName);
        userBaseInfoModel.setIdNo(userObj.IdNumber);
        userBaseInfoModel.setMobile(contactObj.Content);
        return userBaseInfoModel;
    }
}
