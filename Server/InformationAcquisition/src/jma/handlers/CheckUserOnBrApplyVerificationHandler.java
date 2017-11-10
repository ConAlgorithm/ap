/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.math.BigDecimal;
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
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.models.BR4ApplyVerificationResponseModel;
import jma.models.DataSourceResponseBase;
import jma.util.DSPApiUtils;

/**
 * 〈一句话功能简述〉
 *
 * @author dengw
 * @version CheckUserOnBrApplyVerificationHandler.java, V1.0 2017年5月8日 上午11:46:15
 */
public class CheckUserOnBrApplyVerificationHandler extends NonBlockingJobHandler {
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if (DynamicConfig.read("BRApplyVerificationSwitch", JobHandlerSwitch.Off.getValue())
				.equals(JobHandlerSwitch.Off.getValue())) {
			return;
		}
		Logger.get().info("CheckUserOnBrApplyVerificationHandler execute appId:" + appId);
		Map<String, Object> param = getUserBaseInfoModel(appId);
		String url = StartupConfig.get("dsp.api.resource.br.apply");
		try {
			//调用dsp百融接口
            DataSourceResponseBase<BR4ApplyVerificationResponseModel> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<BR4ApplyVerificationResponseModel>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<BR4ApplyVerificationResponseModel> data = res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            BR4ApplyVerificationResponseModel model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.BR_APPLY_RAW_DATA,new Gson().toJson(model)));
            //写mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_CODE, model.getCode())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SWIFT_NUMBER, model.getSwift_number())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_FLAGSPECIALLIST_C, model.getFlag_specialList_c())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_ABNORMAL, model.getSl_id_abnormal())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_PHONE_OVERDUE, model.getSl_id_phone_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_COURT_BAD, model.getSl_id_court_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_COURT_EXECUTED, model.getSl_id_court_executed())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_BANK_BAD, model.getSl_id_bank_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_BANK_OVERDUE, model.getSl_id_bank_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_BANK_FRAUD, model.getSl_id_bank_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_BANK_LOST,model.getSl_id_bank_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_BANK_REFUSE,model.getSl_id_bank_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_P2P_BAD,model.getSl_id_p2p_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_P2P_OVERDUE,model.getSl_id_p2p_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_P2P_FRAUD,model.getSl_id_p2p_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_P2P_LOST,model.getSl_id_p2p_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_P2P_REFUSE,model.getSl_id_p2p_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_P2P_BAD,model.getSl_id_nbank_p2p_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_P2P_OVERDUE,model.getSl_id_nbank_p2p_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_P2P_FRAUD,model.getSl_id_nbank_p2p_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_P2P_LOST,model.getSl_id_nbank_p2p_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_P2P_REFUSE,model.getSl_id_nbank_p2p_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_MC_BAD,model.getSl_id_nbank_mc_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_MC_OVERDUE,model.getSl_id_nbank_mc_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_MC_FRAUD,model.getSl_id_nbank_mc_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_MC_LOST,model.getSl_id_nbank_mc_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_MC_REFUSE,model.getSl_id_nbank_mc_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CA_BAD,model.getSl_id_nbank_ca_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CA_OVERDUE,model.getSl_id_nbank_ca_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CA_FRAUD,model.getSl_id_nbank_ca_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CA_LOST,model.getSl_id_nbank_ca_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CA_REFUSE,model.getSl_id_nbank_ca_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_COM_BAD,model.getSl_id_nbank_com_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_COM_OVERDUE,model.getSl_id_nbank_com_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_COM_FRAUD,model.getSl_id_nbank_com_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_COM_LOST,model.getSl_id_nbank_com_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_COM_REFUSE,model.getSl_id_nbank_com_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CF_BAD,model.getSl_id_nbank_cf_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CF_OVERDUE,model.getSl_id_nbank_cf_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CF_FRAUD,model.getSl_id_nbank_cf_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CF_LOST,model.getSl_id_nbank_cf_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_CF_REFUSE,model.getSl_id_nbank_cf_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_OTHER_BAD,model.getSl_id_nbank_other_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_OTHER_OVERDUE,model.getSl_id_nbank_other_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_OTHER_FRAUD,model.getSl_id_nbank_other_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_OTHER_LOST,model.getSl_id_nbank_other_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_ID_NBANK_OTHER_REFUSE,model.getSl_id_nbank_other_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_ABNORMAL,model.getSl_cell_abnormal())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_PHONE_OVERDUE,model.getSl_cell_phone_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_BANK_BAD,model.getSl_cell_bank_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_BANK_OVERDUE,model.getSl_cell_bank_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_BANK_FRAUD,model.getSl_cell_bank_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_BANK_LOST,model.getSl_cell_bank_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_BANK_REFUSE,model.getSl_cell_bank_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_P2P_BAD,model.getSl_cell_p2p_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_P2P_OVERDUE,model.getSl_cell_p2p_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_P2P_FRAUD,model.getSl_cell_p2p_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_P2P_LOST,model.getSl_cell_p2p_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_P2P_REFUSE,model.getSl_cell_p2p_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_P2P_BAD,model.getSl_cell_nbank_p2p_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_P2P_OVERDUE,model.getSl_cell_nbank_p2p_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_P2P_FRAUD,model.getSl_cell_nbank_p2p_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_P2P_LOST,model.getSl_cell_nbank_p2p_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_P2P_REFUSE,model.getSl_cell_nbank_p2p_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_MC_BAD,model.getSl_cell_nbank_mc_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_MC_OVERDUE,model.getSl_cell_nbank_mc_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_MC_FRAUD,model.getSl_cell_nbank_mc_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_MC_LOST,model.getSl_cell_nbank_mc_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_MC_REFUSE,model.getSl_cell_nbank_mc_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CA_BAD,model.getSl_cell_nbank_ca_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CA_OVERDUE,model.getSl_cell_nbank_ca_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CA_FRAUD,model.getSl_cell_nbank_ca_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CA_LOST,model.getSl_cell_nbank_ca_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CA_REFUSE,model.getSl_cell_nbank_ca_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_COM_BAD,model.getSl_cell_nbank_com_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_COM_OVERDUE,model.getSl_cell_nbank_com_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_COM_FRAUD,model.getSl_cell_nbank_com_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_COM_LOST,model.getSl_cell_nbank_com_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_COM_REFUSE,model.getSl_cell_nbank_com_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CF_BAD,model.getSl_cell_nbank_cf_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CF_OVERDUE,model.getSl_cell_nbank_cf_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CF_FRAUD,model.getSl_cell_nbank_cf_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CF_LOST,model.getSl_cell_nbank_cf_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_CF_REFUSE,model.getSl_cell_nbank_cf_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_OTHER_BAD,model.getSl_cell_nbank_other_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_OTHER_OVERDUE,model.getSl_cell_nbank_other_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_OTHER_FRAUD,model.getSl_cell_nbank_other_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_OTHER_LOST,model.getSl_cell_nbank_other_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_CELL_NBANK_OTHER_REFUSE,model.getSl_cell_nbank_other_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_ABNORMAL,model.getSl_lm_cell_abnormal())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_PHONE_OVERDUE,model.getSl_lm_cell_phone_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_BANK_BAD,model.getSl_lm_cell_bank_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_BANK_OVERDUE,model.getSl_lm_cell_bank_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_BANK_FRAUD,model.getSl_lm_cell_bank_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_BANK_LOST,model.getSl_lm_cell_bank_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_BANK_REFUSE,model.getSl_lm_cell_bank_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_P2P_BAD,model.getSl_lm_cell_nbank_p2p_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_P2P_OVERDUE,model.getSl_lm_cell_nbank_p2p_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_P2P_FRAUD,model.getSl_lm_cell_nbank_p2p_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_P2P_LOST,model.getSl_lm_cell_nbank_p2p_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_P2P_REFUSE,model.getSl_lm_cell_nbank_p2p_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_MC_BAD,model.getSl_lm_cell_nbank_mc_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_MC_OVERDUE,model.getSl_lm_cell_nbank_mc_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_MC_FRAUD,model.getSl_lm_cell_nbank_mc_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_MC_LOST,model.getSl_lm_cell_nbank_mc_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_MC_REFUSE,model.getSl_lm_cell_nbank_mc_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CA_BAD,model.getSl_lm_cell_nbank_ca_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CA_OVERDUE,model.getSl_lm_cell_nbank_ca_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CA_FRAUD,model.getSl_lm_cell_nbank_ca_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CA_LOST,model.getSl_lm_cell_nbank_ca_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CA_REFUSE,model.getSl_lm_cell_nbank_ca_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_COM_BAD,model.getSl_lm_cell_nbank_com_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_COM_OVERDUE,model.getSl_lm_cell_nbank_com_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_COM_FRAUD,model.getSl_lm_cell_nbank_com_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_COM_LOST,model.getSl_lm_cell_nbank_com_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_COM_REFUSE,model.getSl_lm_cell_nbank_com_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CF_BAD,model.getSl_lm_cell_nbank_cf_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CF_OVERDUE,model.getSl_lm_cell_nbank_cf_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CF_FRAUD,model.getSl_lm_cell_nbank_cf_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CF_LOST,model.getSl_lm_cell_nbank_cf_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_CF_REFUSE,model.getSl_lm_cell_nbank_cf_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_OTHER_BAD,model.getSl_lm_cell_nbank_other_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_OTHER_OVERDUE,model.getSl_lm_cell_nbank_other_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_OTHER_FRAUD,model.getSl_lm_cell_nbank_other_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_OTHER_LOST,model.getSl_lm_cell_nbank_other_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_LM_CELL_NBANK_OTHER_REFUSE,model.getSl_lm_cell_nbank_other_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_PHONE_OVERDUE,model.getSl_gid_phone_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_BANK_BAD,model.getSl_gid_bank_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_BANK_OVERDUE,model.getSl_gid_bank_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_BANK_FRAUD,model.getSl_gid_bank_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_BANK_LOST,model.getSl_gid_bank_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_BANK_REFUSE,model.getSl_gid_bank_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_P2P_BAD,model.getSl_gid_p2p_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_P2P_OVERDUE,model.getSl_gid_p2p_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_P2P_FRAUD,model.getSl_gid_p2p_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_P2P_LOST,model.getSl_gid_p2p_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_P2P_REFUSE,model.getSl_gid_p2p_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_P2P_BAD,model.getSl_gid_nbank_p2p_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_P2P_OVERDUE,model.getSl_gid_nbank_p2p_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_P2P_FRAUD,model.getSl_gid_nbank_p2p_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_P2P_LOST,model.getSl_gid_nbank_p2p_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_P2P_REFUSE,model.getSl_gid_nbank_p2p_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_MC_BAD,model.getSl_gid_nbank_mc_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_MC_OVERDUE,model.getSl_gid_nbank_mc_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_MC_FRAUD,model.getSl_gid_nbank_mc_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_MC_LOST,model.getSl_gid_nbank_mc_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_MC_REFUSE,model.getSl_gid_nbank_mc_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CA_BAD,model.getSl_gid_nbank_ca_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CA_OVERDUE,model.getSl_gid_nbank_ca_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CA_FRAUD,model.getSl_gid_nbank_ca_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CA_LOST,model.getSl_gid_nbank_ca_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CA_REFUSE,model.getSl_gid_nbank_ca_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_COM_BAD,model.getSl_gid_nbank_com_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_COM_OVERDUE,model.getSl_gid_nbank_com_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_COM_FRAUD,model.getSl_gid_nbank_com_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_COM_LOST,model.getSl_gid_nbank_com_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_COM_REFUSE,model.getSl_gid_nbank_com_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CF_BAD,model.getSl_gid_nbank_cf_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CF_OVERDUE,model.getSl_gid_nbank_cf_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CF_FRAUD,model.getSl_gid_nbank_cf_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CF_LOST,model.getSl_gid_nbank_cf_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_CF_REFUSE,model.getSl_gid_nbank_cf_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_OTHER_BAD,model.getSl_gid_nbank_other_bad())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_OTHER_OVERDUE,model.getSl_gid_nbank_other_overdue())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_OTHER_FRAUD,model.getSl_gid_nbank_other_fraud())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_OTHER_LOST,model.getSl_gid_nbank_other_lost())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_SL_GID_NBANK_OTHER_REFUSE,model.getSl_gid_nbank_other_refuse())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_FLAG_APPLYLOANSTR,model.getFlag_applyloanstr())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_BANK_SELFNUM,model.getAls_d7_id_bank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_BANK_ALLNUM,model.getAls_d7_id_bank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_BANK_ORGNUM,model.getAls_d7_id_bank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_SELFNUM,model.getAls_d7_id_nbank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_ALLNUM,model.getAls_d7_id_nbank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_P2P_ALLNUM,model.getAls_d7_id_nbank_p2p_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_MC_ALLNUM,model.getAls_d7_id_nbank_mc_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_CA_ALLNUM,model.getAls_d7_id_nbank_ca_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_CF_ALLNUM,model.getAls_d7_id_nbank_cf_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_COM_ALLNUM,model.getAls_d7_id_nbank_com_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_OTH_ALLNUM,model.getAls_d7_id_nbank_oth_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_ORGNUM,model.getAls_d7_id_nbank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_P2P_ORGNUM,model.getAls_d7_id_nbank_p2p_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_MC_ORGNUM,model.getAls_d7_id_nbank_mc_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_CA_ORGNUM,model.getAls_d7_id_nbank_ca_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_CF_ORGNUM,model.getAls_d7_id_nbank_cf_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_COM_ORGNUM,model.getAls_d7_id_nbank_com_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_ID_NBANK_OTH_ORGNUM,model.getAls_d7_id_nbank_oth_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_BANK_SELFNUM,model.getAls_d7_cell_bank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_BANK_ALLNUM,model.getAls_d7_cell_bank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_BANK_ORGNUM,model.getAls_d7_cell_bank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_SELFNUM,model.getAls_d7_cell_nbank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_ALLNUM,model.getAls_d7_cell_nbank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_P2P_ALLNUM,model.getAls_d7_cell_nbank_p2p_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_MC_ALLNUM,model.getAls_d7_cell_nbank_mc_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_CA_ALLNUM,model.getAls_d7_cell_nbank_ca_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_CF_ALLNUM,model.getAls_d7_cell_nbank_cf_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_COM_ALLNUM,model.getAls_d7_cell_nbank_com_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_OTH_ALLNUM,model.getAls_d7_cell_nbank_oth_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_ORGNUM,model.getAls_d7_cell_nbank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_P2P_ORGNUM,model.getAls_d7_cell_nbank_p2p_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_MC_ORGNUM,model.getAls_d7_cell_nbank_mc_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_CA_ORGNUM,model.getAls_d7_cell_nbank_ca_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_CF_ORGNUM,model.getAls_d7_cell_nbank_cf_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_COM_ORGNUM,model.getAls_d7_cell_nbank_com_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D7_CELL_NBANK_OTH_ORGNUM,model.getAls_d7_cell_nbank_oth_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_BANK_SELFNUM,model.getAls_d15_id_bank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_BANK_ALLNUM,model.getAls_d15_id_bank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_BANK_ORGNUM,model.getAls_d15_id_bank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_SELFNUM,model.getAls_d15_id_nbank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_ALLNUM,model.getAls_d15_id_nbank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_P2P_ALLNUM,model.getAls_d15_id_nbank_p2p_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_MC_ALLNUM,model.getAls_d15_id_nbank_mc_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_CA_ALLNUM,model.getAls_d15_id_nbank_ca_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_CF_ALLNUM,model.getAls_d15_id_nbank_cf_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_COM_ALLNUM,model.getAls_d15_id_nbank_com_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_OTH_ALLNUM,model.getAls_d15_id_nbank_oth_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_ORGNUM,model.getAls_d15_id_nbank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_P2P_ORGNUM,model.getAls_d15_id_nbank_p2p_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_MC_ORGNUM,model.getAls_d15_id_nbank_mc_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_CA_ORGNUM,model.getAls_d15_id_nbank_ca_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_CF_ORGNUM,model.getAls_d15_id_nbank_cf_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_COM_ORGNUM,model.getAls_d15_id_nbank_com_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_ID_NBANK_OTH_ORGNUM,model.getAls_d15_id_nbank_oth_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_BANK_SELFNUM,model.getAls_d15_cell_bank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_BANK_ALLNUM,model.getAls_d15_cell_bank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_BANK_ORGNUM,model.getAls_d15_cell_bank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_SELFNUM,model.getAls_d15_cell_nbank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_ALLNUM,model.getAls_d15_cell_nbank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_P2P_ALLNUM,model.getAls_d15_cell_nbank_p2p_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_MC_ALLNUM,model.getAls_d15_cell_nbank_mc_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_CA_ALLNUM,model.getAls_d15_cell_nbank_ca_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_CF_ALLNUM,model.getAls_d15_cell_nbank_cf_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_COM_ALLNUM,model.getAls_d15_cell_nbank_com_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_OTH_ALLNUM,model.getAls_d15_cell_nbank_oth_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_ORGNUM,model.getAls_d15_cell_nbank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_P2P_ORGNUM,model.getAls_d15_cell_nbank_p2p_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_MC_ORGNUM,model.getAls_d15_cell_nbank_mc_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_CA_ORGNUM,model.getAls_d15_cell_nbank_ca_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_CF_ORGNUM,model.getAls_d15_cell_nbank_cf_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_COM_ORGNUM,model.getAls_d15_cell_nbank_com_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_D15_CELL_NBANK_OTH_ORGNUM,model.getAls_d15_cell_nbank_oth_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_BANK_SELFNUM,model.getAls_m1_id_bank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_BANK_ALLNUM,model.getAls_m1_id_bank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_BANK_ORGNUM,model.getAls_m1_id_bank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_SELFNUM,model.getAls_m1_id_nbank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_ALLNUM,model.getAls_m1_id_nbank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_P2P_ALLNUM,model.getAls_m1_id_nbank_p2p_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_MC_ALLNUM,model.getAls_m1_id_nbank_mc_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_CA_ALLNUM,model.getAls_m1_id_nbank_ca_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_CF_ALLNUM,model.getAls_m1_id_nbank_cf_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_COM_ALLNUM,model.getAls_m1_id_nbank_com_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_OTH_ALLNUM,model.getAls_m1_id_nbank_oth_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_ORGNUM,model.getAls_m1_id_nbank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_P2P_ORGNUM,model.getAls_m1_id_nbank_p2p_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_MC_ORGNUM,model.getAls_m1_id_nbank_mc_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_CA_ORGNUM,model.getAls_m1_id_nbank_ca_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_CF_ORGNUM,model.getAls_m1_id_nbank_cf_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_COM_ORGNUM,model.getAls_m1_id_nbank_com_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_ID_NBANK_OTH_ORGNUM,model.getAls_m1_id_nbank_oth_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_BANK_SELFNUM,model.getAls_m1_cell_bank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_BANK_ALLNUM,model.getAls_m1_cell_bank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_BANK_ORGNUM,model.getAls_m1_cell_bank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_SELFNUM,model.getAls_m1_cell_nbank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_ALLNUM,model.getAls_m1_cell_nbank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_P2P_ALLNUM,model.getAls_m1_cell_nbank_p2p_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_MC_ALLNUM,model.getAls_m1_cell_nbank_mc_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_CA_ALLNUM,model.getAls_m1_cell_nbank_ca_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_CF_ALLNUM,model.getAls_m1_cell_nbank_cf_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_COM_ALLNUM,model.getAls_m1_cell_nbank_com_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_OTH_ALLNUM,model.getAls_m1_cell_nbank_oth_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_ORGNUM,model.getAls_m1_cell_nbank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_P2P_ORGNUM,model.getAls_m1_cell_nbank_p2p_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_MC_ORGNUM,model.getAls_m1_cell_nbank_mc_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_CA_ORGNUM,model.getAls_m1_cell_nbank_ca_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_CF_ORGNUM,model.getAls_m1_cell_nbank_cf_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_COM_ORGNUM,model.getAls_m1_cell_nbank_com_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M1_CELL_NBANK_OTH_ORGNUM,model.getAls_m1_cell_nbank_oth_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_BANK_SELFNUM,model.getAls_m3_id_bank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_BANK_ALLNUM,model.getAls_m3_id_bank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_BANK_ORGNUM,model.getAls_m3_id_bank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_SELFNUM,model.getAls_m3_id_nbank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_ALLNUM,model.getAls_m3_id_nbank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_P2P_ALLNUM,model.getAls_m3_id_nbank_p2p_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_MC_ALLNUM,model.getAls_m3_id_nbank_mc_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_CA_ALLNUM,model.getAls_m3_id_nbank_ca_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_CF_ALLNUM,model.getAls_m3_id_nbank_cf_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_COM_ALLNUM,model.getAls_m3_id_nbank_com_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_OTH_ALLNUM,model.getAls_m3_id_nbank_oth_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_ORGNUM,model.getAls_m3_id_nbank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_P2P_ORGNUM,model.getAls_m3_id_nbank_p2p_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_MC_ORGNUM,model.getAls_m3_id_nbank_mc_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_CA_ORGNUM,model.getAls_m3_id_nbank_ca_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_CF_ORGNUM,model.getAls_m3_id_nbank_cf_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_COM_ORGNUM,model.getAls_m3_id_nbank_com_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_ID_NBANK_OTH_ORGNUM,model.getAls_m3_id_nbank_oth_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_BANK_SELFNUM,model.getAls_m3_cell_bank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_BANK_ALLNUM,model.getAls_m3_cell_bank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_BANK_ORGNUM,model.getAls_m3_cell_bank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_SELFNUM,model.getAls_m3_cell_nbank_selfnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_ALLNUM,model.getAls_m3_cell_nbank_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_P2P_ALLNUM,model.getAls_m3_cell_nbank_p2p_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_MC_ALLNUM,model.getAls_m3_cell_nbank_mc_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_CA_ALLNUM,model.getAls_m3_cell_nbank_ca_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_CF_ALLNUM,model.getAls_m3_cell_nbank_cf_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_COM_ALLNUM,model.getAls_m3_cell_nbank_com_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_OTH_ALLNUM,model.getAls_m3_cell_nbank_oth_allnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_ORGNUM,model.getAls_m3_cell_nbank_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_P2P_ORGNUM,model.getAls_m3_cell_nbank_p2p_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_MC_ORGNUM,model.getAls_m3_cell_nbank_mc_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_CA_ORGNUM,model.getAls_m3_cell_nbank_ca_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_CF_ORGNUM,model.getAls_m3_cell_nbank_cf_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_COM_ORGNUM,model.getAls_m3_cell_nbank_com_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M3_CELL_NBANK_OTH_ORGNUM,model.getAls_m3_cell_nbank_oth_orgnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_ID_TOT_MONS,model.getAls_m6_id_tot_mons())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_ID_AVG_MONNUM,model.getAls_m6_id_avg_monnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_ID_MAX_MONNUM,model.getAls_m6_id_max_monnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_ID_MIN_MONNUM,model.getAls_m6_id_min_monnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_ID_MAX_INTEDAY,model.getAls_m6_id_max_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_ID_MIN_INTEDAY,model.getAls_m6_id_min_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_CELL_TOT_MONS,model.getAls_m6_cell_tot_mons())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_CELL_AVG_MONNUM,model.getAls_m6_cell_avg_monnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_CELL_MAX_MONNUM,model.getAls_m6_cell_max_monnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_CELL_MIN_MONNUM,model.getAls_m6_cell_min_monnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_CELL_MAX_INTEDAY,model.getAls_m6_cell_max_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_M6_CELL_MIN_INTEDAY,model.getAls_m6_cell_min_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_FST_ID_BANK_INTEDAY,model.getAls_fst_id_bank_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_FST_ID_NBANK_INTEDAY,model.getAls_fst_id_nbank_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_FST_CELL_BANK_INTEDAY,model.getAls_fst_cell_bank_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_FST_CELL_NBANK_INTEDAY,model.getAls_fst_cell_nbank_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_ID_BANK_INTEDAY,model.getAls_lst_id_bank_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_ID_BANK_CONSNUM,model.getAls_lst_id_bank_consnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_ID_BANK_CSINTEDAY,model.getAls_lst_id_bank_csinteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_ID_NBANK_INTEDAY,model.getAls_lst_id_nbank_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_ID_NBANK_CONSNUM,model.getAls_lst_id_nbank_consnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_ID_NBANK_CSINTEDAY,model.getAls_lst_id_nbank_csinteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_CELL_BANK_INTEDAY,model.getAls_lst_cell_bank_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_CELL_BANK_CONSNUM,model.getAls_lst_cell_bank_consnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_CELL_BANK_CSINTEDAY,model.getAls_lst_cell_bank_csinteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_CELL_NBANK_INTEDAY,model.getAls_lst_cell_nbank_inteday())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_CELL_NBANK_CONSNUM,model.getAls_lst_cell_nbank_consnum())
        		.isNotNullAdd(AppDerivativeVariableNames.BR_APPLY_ALS_LST_CELL_NBANK_CSINTEDAY,model.getAls_lst_cell_nbank_csinteday())
        		.build());
            
		} catch (Exception e) {
            Logger.get().warn(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, param.toString()));
            throw new RetryRequiredException();
        }

	}

	/**
	 * <p>
	 * 〈获取请求的用户相关信息〉
	 * </p>
	 * 
	 * @param appId
	 * @return
	 */
	protected Map<String, Object> getUserBaseInfoModel(String appId) {
		EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
		ContactObject contactObj = new ContactDao(appId).getSingle();
		String firstPhone = PhoneUtils.getFirstContactMobile(appId);
		String secondPhone = PhoneUtils.getSecondContactMobile(appId);
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer linkmanCell = new StringBuffer();
		if (userObj != null) {
			param.put("name", userObj.IdName);
			param.put("idNo", userObj.IdNumber);
		}
		if (contactObj != null) {
			param.put("mobile", contactObj.Content);
		}
		if(firstPhone != null && !"".equals(firstPhone)){
			linkmanCell.append(firstPhone);
			if(secondPhone != null && !"".equals(secondPhone)){
				linkmanCell.append(",");
				linkmanCell.append(secondPhone);
			}
		}else{
			linkmanCell.append(secondPhone);
		}
		param.put("linkmanCell", linkmanCell.toString());
		param.put("productCode", "SpecialList_c,ApplyLoanStr");
		return param;
	}
}
