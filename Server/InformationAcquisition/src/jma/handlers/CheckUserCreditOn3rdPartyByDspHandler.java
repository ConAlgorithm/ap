package jma.handlers;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.handlers.preprocess.PreprocessorFactory;
import jma.handlers.preprocess.model.CheckUserCreditOn3rdPartyByDspPreModel;
import jma.models.DataSourceResponseBase;
import jma.models.QHZXModel;
import jma.util.DSPApiUtils;

/**
 * 
 * 〈前海征信查询DSP数据源Job〉
 *
 * @author wuwj
 * @version CheckUserCreditOn3rdPartyByDspHandler.java, V1.0 2017年5月8日 下午12:08:26
 */
public class CheckUserCreditOn3rdPartyByDspHandler extends NonBlockingJobHandler {

    private CheckUserCreditOn3rdPartyByDspPreModel preModel;

    @Override
    public void preprocess() {
        this.preModel = PreprocessorFactory
            .getProcessor(channel, CheckUserCreditOn3rdPartyByDspPreModel.class).getPreModel(appId);
    }

    /**
     * 查询前海征信数据
     * 
     * @param appId 申请ID
     * @throws RetryRequiredException
     */
    @Override
    public void execute(String appId) throws RetryRequiredException {
        // 前海征信开关
        if (DynamicConfig.read("QHZXSwitch", JobHandlerSwitch.Off.getValue())
            .equals(JobHandlerSwitch.Off.getValue())) {
            Logger.get().info("QHZXSwitch is off , job end!");
            return;
        }

        Logger.get().info("CheckUserCreditOn3rdPartyByDspHandler exeucte by AppId:" + appId);
        String url = StartupConfig.get("dsp.api.resource.qhzx");
        try {
            //  调用DSP接口
            @SuppressWarnings("unchecked")
            DataSourceResponseBase<QHZXModel> res = DSPApiUtils.invokeDspApi(appId, url,
                BeanUtils.describe(preModel), new TypeToken<DataSourceResponseBase<QHZXModel>>() {
                }.getType());

            //  请求失败后重试
            if (res.getCode() != 200) {
                Logger.get()
                    .info(String.format(
                        "CheckUserCreditOn3rdPartyByDspHandler request doesnot success,retry. url=%s, result=%s",
                        url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }

            // 前海征信原始数据落地mongo
            RawDataStorageManager.addRawDatas(new RawData(appId,
                RawDataVariableNames.QHZX_UNITED_RAW_DATA, new Gson().toJson(res)));

            List<QHZXModel> data = res.getData();
            if (data == null || data.size() == 0) {
                Logger.get()
                    .info("CheckUserCreditOn3rdPartyByDspHandler response data is null. parameter："
                          + preModel.toString());
                return;
            }
            QHZXModel model = data.get(0);

            //  前海征信业务数据落地mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_DATA_LATEST_BUILDTIME,
                    model.getX_QHZX_LatestDataBuildTime())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HASBLACKLIST,
                    model.getX_QHZX_HasBlackList())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HASBEENEXECUTED,
                    model.getX_QHZX_HasBeenExcuted())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_BREAKPROMISE,
                    model.getX_QHZX_BreakPromise())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_BREAKPROMISEBEENEXECUTED,
                    model.getX_QHZX_BreakPromiseBeenExecuted())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_MAXIMIZEOVERDUEDAY,
                    model.getX_QHZX_MaximizeOverdueDay())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_MAXIMIZEMONEYBOUND,
                    model.getX_QHZX_MoneyBound())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_CREDIT_OVERDUE_RISK,
                    model.getX_QHZX_HasCreditOverdueRisk())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_ADMINISTRATION_NEGATIVE_RISK,
                    model.getX_QHZX_HasAdministrationNegativeRisk())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_FRAUD_RISK,
                    model.getX_QHZX_HasFraudRisk())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_RISK_SCORE,
                    model.getX_QHZX_RiskScore())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_SERIOUS_TRAFFIC_VIOLATION_RISK,
                    model.getX_QHZX_HasSeriousTrafficViolationRisk())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_MOBILE_FRAUD_RISK,
                    model.getX_QHZX_HasMobileFraudRisk())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_BANK_CARD_FRAUD_RISK,
                    model.getX_QHZX_HasBankCardFraudRisk())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_ID_CARD_FRAUD_RISK,
                    model.getX_QHZX_HasIdCardFraudRisk())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_DATA_STATUS,
                    model.getX_QHZX_DataStatus())
                .isNotNullAdd(AppDerivativeVariableNames.QHZX_HAS_IP_ADDRESS_FRAUD_RISK,
                    model.getX_QHZX_HasIPAddressFraudRisk())
                .build());
        } catch (Exception e) {
            Logger.get()
                .error(String.format(
                    "CheckUserCreditOn3rdPartyByDspHandler exception occurred!appId=%s, url=%s, param=%s",
                    appId, url, preModel.toString()));
            throw new RetryRequiredException();
        }
    }

    public CheckUserCreditOn3rdPartyByDspPreModel getPreModel() {
        return preModel;
    }

    public void setPreModel(CheckUserCreditOn3rdPartyByDspPreModel preModel) {
        this.preModel = preModel;
    }
}
