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
import jma.models.BRModel;
import jma.models.DataSourceResponseBase;
import jma.util.DSPApiUtils;

/**
 * 〈百融黑名单检查〉
 *
 * @author hwei
 * @version CheckUserOnBrHandler.java, V1.0 2016年11月21日 下午8:29:17
 */
public class CheckUserOnBrHandler extends NonBlockingJobHandler {

    /**
     * <p>〈调用百融数据源获取结果落地〉</p>
     * 
     * @param appId
     * @throws RetryRequiredException
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute(String appId) throws RetryRequiredException {
        if(DynamicConfig.read("BRSwitch",JobHandlerSwitch.Off.getValue())
                .equals(JobHandlerSwitch.Off.getValue())) {
                    return;
        }
        Logger.get().info("CheckUserOnBrHandler execute appId:" + appId);
        UserBaseInfoModel requestModel = getUserBaseInfoModel(appId);
        String url = StartupConfig.get("dsp.api.resource.br");
        try {
            //请求数据转换
            Map<String, Object> param = BeanUtils.describe(requestModel);
            //调用dsp百融接口
            DataSourceResponseBase<BRModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<BRModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<BRModel> data=res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            BRModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.BR_UNITED_RAW_DATA,new Gson().toJson(model)));
            //写mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_BAD, model.getX_BR_SpecialListIdBankBad())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_OVERDUE, model.getX_BR_SpecialListIdBankOverdue())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_FRAUD, model.getX_BR_SpecialListIdBankFraud())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_LOST, model.getX_BR_SpecialListIdBankLost())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_BANK_REFUSE, model.getX_BR_SpecialListIdBankRefuse())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_CREDIT_BAD, model.getX_BR_SpecialListIdCreditBad())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_BAD, model.getX_BR_SpecialListIdP2PBad())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_OVERDUE, model.getX_BR_SpecialListIdP2POverdue())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_FRAUD, model.getX_BR_SpecialListIdP2PFraud())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_LOST, model.getX_BR_SpecialListIdP2PLost())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_P2P_REFUSE, model.getX_BR_SpecialListIdP2PRefuse())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_PHONE_OVERDUE, model.getX_BR_SpecialListIdPhoneOverdue())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_INS_FRAUD, model.getX_BR_SpecialListIdInsFraud())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_COURT_BAD, model.getX_BR_SpecialListIdCourtBad())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_ID_COURT_EXECUTED, model.getX_BR_SpecialListIdCourtExecuted())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_BAD, model.getX_BR_SpecialListCellBankBad())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_OVERDUE, model.getX_BR_SpecialListCellBankOverdue())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_FRAUD, model.getX_BR_SpecialListCellBankFraud())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_LOST, model.getX_BR_SpecialListCellBankLost())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_BANK_REFUSE, model.getX_BR_SpecialListCellBankRefuse())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_BAD, model.getX_BR_SpecialListCellP2PBad())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_OVERDUE, model.getX_BR_SpecialListCellP2POverdue())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_FRAUD, model.getX_BR_SpecialListCellP2PFraud())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_LOST, model.getX_BR_SpecialListCellP2PLost())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_P2P_REFUSE, model.getX_BR_SpecialListCellP2PRefuse())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_PHONE_OVERDUE, model.getX_BR_SpecialListCellPhoneOverdue())
                .isNotNullAdd(AppDerivativeVariableNames.BR_SPECIALLIST_CELL_INS_FRAUD, model.getX_BR_SpecialListCellInsFraud())
                .isNotNullAdd(AppDerivativeVariableNames.BR_RJ_Rule, model.getX_BR_RJ_Rule())
                .isNotNullAdd(AppDerivativeVariableNames.BR_sl_id, model.getX_BR_sl_id())
                .isNotNullAdd(AppDerivativeVariableNames.BR_sl_cell, model.getX_BR_sl_cell())
                .build());

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
