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
import jma.Configuration;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.handlers.preprocess.model.UserBaseInfoModel;
import jma.models.BaiDuBlackListModel;
import jma.models.DataSourceResponseBase;
import jma.util.DSPApiUtils;

/**
 * 〈百度黑名单检查〉
 *
 * @author hwei
 * @version CheckUserOnBaiDuBLHandler.java, V1.0 2016年12月6日 下午5:57:03
 */
public class CheckUserOnBaiDuBLHandler extends NonBlockingJobHandler {

    @SuppressWarnings({ "unchecked" })
    @Override
    public void execute(String appId) throws RetryRequiredException {
        if (DynamicConfig.read("BDBlackListSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue())) {
            return;
        }
        Logger.get().info("CheckUserOnBaiDuBLHandler execute appId:" + appId);
        UserBaseInfoModel requestModel = getUserBaseInfoModel(appId);
        String url = Configuration.getBaiduBLUrl();
        try {
            //请求数据转换
            Map<String, Object> param = BeanUtils.describe(requestModel);
            //调用dsp接口
            DataSourceResponseBase<BaiDuBlackListModel> res = DSPApiUtils.invokeDspApi(appId, url, param, new TypeToken<DataSourceResponseBase<BaiDuBlackListModel>>() {
            }.getType());
            RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.BaiduBL_RAW_DATA, new Gson().toJson(res))); 
            if (res.getCode() != 200) {
                Logger.get().warn(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                return;
            }
            List<BaiDuBlackListModel> data = res.getData();
            if (data == null || data.size() == 0) {
                Logger.get().warn("response data is null.");
                return;
            }
            BaiDuBlackListModel model = data.get(0);
            AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId).isNotNullAdd(AppDerivativeVariableNames.XBaiduSignType, model.getSignType())
                .isNotNullAdd(AppDerivativeVariableNames.XBaiduSign, model.getSign()).isNotNullAdd(AppDerivativeVariableNames.XBaiduRetCode, model.getRetCode())
                .isNotNullAdd(AppDerivativeVariableNames.XBaiduRetMsg, model.getRetMsg()).isNotNullAdd(AppDerivativeVariableNames.XBaiduTimestamp, model.getTimestamp())
                .isNotNullAdd(AppDerivativeVariableNames.XBaiduBlackLevel, model.getResult().getBlackLevel())
                .isNotNullAdd(AppDerivativeVariableNames.XBaiduBlackReason, model.getResult().getBlackReason()).build());

        } catch (Exception e) {
            Logger.get().error(String.format("exception occurred!appId=%s, url=%s, param=%s", appId, url, new Gson().toJson(requestModel)), e);
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
        if(userObj!=null){
            userBaseInfoModel.setName(userObj.IdName);
            userBaseInfoModel.setIdNo(userObj.IdNumber);            
        }
        if(contactObj!=null){
            userBaseInfoModel.setMobile(contactObj.Content);            
        }
        return userBaseInfoModel;
    }
}
