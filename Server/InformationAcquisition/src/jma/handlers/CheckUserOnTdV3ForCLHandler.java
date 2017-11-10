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
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.cowfish.application.model.ApplicationModel;
import grasscarp.account.model.Account;
import grasscarp.user.model.User;
import jma.AppDerivativeVariablesBuilder;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.handlers.preprocess.model.UserBaseInfoModel;
import jma.models.DataSourceResponseBase;
import jma.models.DerivativeVariableNames;
import jma.models.RawDataVariableNames;
import jma.models.TongDunV3Model;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;
import jma.util.DSPApiUtils;

/**
 * 〈同盾V3〉
 *
 * @author dengw
 * @version CheckUserOnTdV3ForCLHandler.java, V1.0 2017年7月3日 下午3:09:52
 */
public class CheckUserOnTdV3ForCLHandler extends NonBlockingJobHandler{
	@SuppressWarnings("unchecked")
	@Override
	public void execute(String appId) throws RetryRequiredException {
		if(DynamicConfig.read("TdV3Switch",JobHandlerSwitch.Off.getValue())
                .equals(JobHandlerSwitch.Off.getValue())) {
                    return;
        }
		Logger.get().info("CheckUserOnTdV3ForCLHandler execute appId:" + appId);
        UserBaseInfoModel requestModel = getUserBaseInfo(appId);
        String url = StartupConfig.get("dsp.api.resource.tdv3");
        try {
        	//请求数据转换
            Map<String, Object> param = BeanUtils.describe(requestModel);
            //调用dsp同盾V3接口
            DataSourceResponseBase<TongDunV3Model> res=DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<TongDunV3Model>>() {
            }.getType());
            if(res.getCode() != 200){
                Logger.get().info(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                throw new RetryRequiredException();
            }
            List<TongDunV3Model> data=res.getData();
            if(data == null || data.size() == 0){
                Logger.get().info("response data is null.");
                return ;
            }
            TongDunV3Model model=data.get(0);
            RawDataStorageManager.addRawDatas(new RawData(appId,RawDataVariableNames.TDV3_UNITED_RAW_DATA,new Gson().toJson(model)));
            //写mongo
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
        		.isNotNullAdd(DerivativeVariableNames.FINALDECISION, model.getFinalDecision())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDISCOURTDISHONESTY, model.getIdCardIsCourtDishonesty())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDISCOURTEXECUTED, model.getIdCardIsCourtExecuted())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDISCOURTCLOSED, model.getIdCardIsCourtClosed())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDISCRIMEWANTED, model.getIdCardIsCrimeWanted())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDISSTUDENTLOAN, model.getIdCardIsStudentLoan())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDISLOANOVERDUE, model.getIdCardIsLoanOverdue())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDISLOANOVERDUEREPAY, model.getIdCardIsLoanOverdueRepay())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISFAKE, model.getMobileIsFake())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISSMALL, model.getMobileIsSmall())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISBILK, model.getMobileIsBilk())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISLOANBLACKMEDIUM, model.getMobileIsLoanBlackMedium())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISLOANOVERDUE, model.getMobileIsLoanOverdue())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISVEHICLEHIRE, model.getMobileIsVehicleHire())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISOVERDRAFTCORPORATE, model.getMobileIsOverdraftCorporate())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISLOST, model.getMobileIsLost())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEISLOANOVERDUEREPAY, model.getMobileIsLoanOverdueRepay())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDAPPLYS3MONTH, model.getIdCardApplys3Month())
        		.isNotNullAdd(DerivativeVariableNames.APPLYIDCARDS3MONTH, model.getApplyIdCards3Month())
        		.isNotNullAdd(DerivativeVariableNames.BANKNAMEIDCARDS3MONTH, model.getBankNameIdCards3Month())
        		.isNotNullAdd(DerivativeVariableNames.IDCARDASCONTACTNUM2, model.getIdCardAsContactNum2())
        		.isNotNullAdd(DerivativeVariableNames.MOBILEASCONTACTNUM2, model.getMobileAsContactNum2())
        		.isNotNullAdd(DerivativeVariableNames.APPLYLOANONPLATS7CNT, model.getApplyLoanOnPlats7Cnt())
        		.isNotNullAdd(DerivativeVariableNames.APPLYLOANONPLATS30CNT, model.getApplyLoanOnPlats30Cnt())
        		.isNotNullAdd(DerivativeVariableNames.APPLYLOANONPLATS3MONTHCNT, model.getApplyLoanOnPlats3MonthCnt())
        		.isNotNullAdd(DerivativeVariableNames.APPLYLOANONPLATS6MONTHCNT, model.getApplyLoanOnPlats6MonthCnt())
        		.isNotNullAdd(DerivativeVariableNames.APPLYLOANONPLATS12MONTHCNT, model.getApplyLoanOnPlats12MonthCnt())
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
    protected UserBaseInfoModel getUserBaseInfo(String appId) {
        UserBaseInfoModel userBaseInfoModel = new UserBaseInfoModel();
        ApplicationModel clApp = CLApplicationResourceFactory.getApplication(appId);
        User user = UserResourceFactory.getUser(clApp.userId);
        Account userAccount = UserResourceFactory.getUserAccount(clApp.userId);
        userBaseInfoModel.setName(user.getIdName());
        userBaseInfoModel.setIdNo(user.getIdNumber());
        userBaseInfoModel.setMobile(userAccount.getMobile());
        return userBaseInfoModel;
    }
}
