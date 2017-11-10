/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.MarriageStatus;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.dao.PersonalInfoDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.database.DatabaseApi;
import jma.AppDerivativeVariablesBuilder;
import jma.Configuration;
import jma.JobHandlerSwitch;
import jma.NonBlockingJobHandler;
import jma.RetryRequiredException;
import jma.dataservice.PhoneUtils;
import jma.models.DataSourceResponseBase;
import jma.models.baiduScore.BAIDUResponseModel;
import jma.util.DSPApiUtils;

/**
 * 〈百度评分〉
 *
 * @author hwei
 * @version CheckUserOnBaiDuScoreHandler.java, V1.0 2017年1月10日 下午3:51:56
 */
public class CheckUserOnBaiDuScoreHandler extends NonBlockingJobHandler {
    public static final List<String> MUNICIPALITIES = Arrays.asList("上海", "北京", "天津", "重庆");

    @Override
    public void execute(String appId) throws RetryRequiredException {
        if (DynamicConfig.read("BDScoreSwitch", JobHandlerSwitch.Off.getValue()).equals(JobHandlerSwitch.Off.getValue())) {
            return;
        }
        Logger.get().info("CheckUserOnBaiDuScoreHandler execute appId:" + appId);
        String url = Configuration.getBaiduScoreUrl();
        try {
            Map<String, Object> param = getUserBaseInfoModel(appId);
            if (param == null) {
                Logger.get().info("getUserBaseInfoModel param is null");
                return;
            }
            param.putAll(getMongoVariables(appId));
            param.putAll(getMerchantTypeAndPrincipal(appId));
            param.putAll(getMarriageStatusAndWeiPhoto(appId));
            Logger.get().info("request dsp param is " + new Gson().toJson(param));
            //调用dsp接口
            DataSourceResponseBase<BAIDUResponseModel> res = DSPApiUtils.invokeDspApi(appId, url, param,
                new TypeToken<DataSourceResponseBase<BAIDUResponseModel>>() {
                }.getType());
            RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.BaiduScore_RAW_DATA, new Gson().toJson(res)));
            if (res != null && res.getCode() != 200) {
                Logger.get().warn(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                return;
            }
            List<BAIDUResponseModel> data = res.getData();
            if (data == null || data.size() == 0) {
                Logger.get().warn("response data is null.");
                return;
            }
            BAIDUResponseModel model = data.get(0);
            if (model != null && model.getModelScore() != null) {
                AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
                    .isNotNullAdd(AppDerivativeVariableNames.XBaiduScore_type, model.getModelScore().getScore_type())
                    .isNotNullAdd(AppDerivativeVariableNames.XBaiduMdx_usmbl, model.getModelScore().getMdx_usmbl()).build());
            }

        } catch (Exception e) {
            Logger.get().error(String.format("exception occurred!appId=%s, url=%s", appId, url), e);
            throw new RetryRequiredException();
        }
    }

    /**
     * <p>〈获取请求的用户相关信息姓名，手机号，身份证号, 公司电话类型〉</p>
     * 
     * @param appId
     * @return
     */
    protected Map<String, Object> getUserBaseInfoModel(String appId) {
        Map<String, Object> param = new HashMap<String, Object>();
        EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
        ContactObject contactObj = new ContactDao(appId).getSingle();
        String CompanyTel = PhoneUtils.getCompanyTel(appId);
        if (userObj != null) {
            param.put("name", userObj.IdName);
            param.put("idNo", userObj.IdNumber);
        }
        if (contactObj != null) {
            param.put("mobile", contactObj.Content);
        }
        if (CompanyTel != null) {
            if (CompanyTel.startsWith("1")) {
                param.put("comPhoneType", "phone");
            } else {
                param.put("comPhoneType", "tel");
            }
        }
        return param;
    }

    /**
     * <p>〈从mongo中获取数据〉</p>
     * 
     * @param appId
     * @return
     */
    protected Map<String, Object> getMongoVariables(String appId) {
        //从mongo中获取年龄，性别，出生省、市，申请省、市，手机号归属省、市
        Map<String, Object> param = new HashMap<String, Object>();
        AppDerivativeVariables derivativeVariables = null;
        try {
            derivativeVariables = AppDerivativeVariableManager.getVariables(appId, AppDerivativeVariableNames.ID_CARD_AGE,
                AppDerivativeVariableNames.ID_CARD_GENDER, AppDerivativeVariableNames.ID_CARD_PROVINCE, AppDerivativeVariableNames.ID_CARD_CITY,
                AppDerivativeVariableNames.MERCHANT_PROVINCE, AppDerivativeVariableNames.MERCHANT_CITY,
                AppDerivativeVariableNames.USER_MOBILE_PROVINCE, AppDerivativeVariableNames.USER_MOBILE_CITY);
        } catch (Exception e) {
            Logger.get().error("mongo getVariables error", e);
        }
        if (derivativeVariables != null) {
            param.put("age", String.valueOf(derivativeVariables.get(AppDerivativeVariableNames.ID_CARD_AGE)));
            param.put("sex", derivativeVariables.getAsString(AppDerivativeVariableNames.ID_CARD_GENDER));
            String bornProvince = getRemoveStr(derivativeVariables.getAsString(AppDerivativeVariableNames.ID_CARD_PROVINCE));
            param.put("bornProvince", bornProvince);
            if (MUNICIPALITIES.contains(bornProvince)) {
                param.put("bornCity", bornProvince);
            } else {
                param.put("bornCity", getRemoveStr(derivativeVariables.getAsString(AppDerivativeVariableNames.ID_CARD_CITY)));
            }
            String applyProvince = getRemoveStr(derivativeVariables.getAsString(AppDerivativeVariableNames.MERCHANT_PROVINCE));
            param.put("applyProvince", applyProvince);
            if (MUNICIPALITIES.contains(applyProvince)) {
                param.put("applyCity", applyProvince);
            } else {
                param.put("applyCity", getRemoveStr(derivativeVariables.getAsString(AppDerivativeVariableNames.MERCHANT_CITY)));
            }
            param.put("phoneProvince", derivativeVariables.getAsString(AppDerivativeVariableNames.USER_MOBILE_PROVINCE));
            param.put("phoneCity", derivativeVariables.getAsString(AppDerivativeVariableNames.USER_MOBILE_CITY));
        }
        return param;
    }

    /**
     * <p>〈去掉省市中最后的'省'，'市'〉</p>
     * 
     * @return
     */
    protected String getRemoveStr(String str) {
        if (!StringUtils.isNullOrWhiteSpaces(str)) {
            str=str.trim();
            if (str.substring(str.length() - 1).equals("省") || str.substring(str.length() - 1).equals("市")) {
                str = str.substring(0, str.length() - 1);
            }
            return str;
        }
        return null;
    }

    /**
     * <p>〈获取门店类型，借款金额〉</p>
     * 
     * @return
     */
    protected Map<String, Object> getMerchantTypeAndPrincipal(String appId) {
        Map<String, Object> param = new HashMap<String, Object>();
        String sql = "SELECT StoreCategory,Principal " + "FROM MerchantStoreObjects ms "
                     + "INNER JOIN InstallmentApplicationObjects im ON ms.Id=im.MerchantStoreId " + "WHERE im.Id=:AppId";
        RowMapper<List<String>> extractor = new RowMapper<List<String>>() {
            @Override
            public List<String> mapRow(ResultSet resultSet, int index) throws SQLException {
                return Arrays.asList(resultSet.getString("StoreCategory"), resultSet.getString("Principal"));
            }
        };
        List<String> result = DatabaseApi.querySingleResult(sql, CollectionUtils.mapOf("AppId", appId), extractor);
        if (result != null) {
            Double creditAmount = Double.valueOf(result.get(1)) * 100;
            param.put("storeCategory", result.get(0));
            param.put("creditAmount", creditAmount);
        }
        return param;
    }

    /**
     * <p>〈获取婚姻状况,及微信头像情况〉</p>
     * 
     * @param appId
     * @return
     */
    protected Map<String, Object> getMarriageStatusAndWeiPhoto(String appId) {
        Map<String, Object> param = new HashMap<String, Object>();
        Integer maritalStatus = PersonalInfoDao.getMarriageStatusById(appId);
        if (maritalStatus != null) {
            if (MarriageStatus.Married.getValue() == maritalStatus) {
                param.put("maritalStatus", "married");
            } else if (MarriageStatus.Others.getValue() == maritalStatus) {
                param.put("maritalStatus", "other");
            } else {
                param.put("maritalStatus", "unmarried");
            }
        }
        String sql = "select HeadImageUrl from WeiXinUserObjects wu " + "inner join UserObjects uo on wu.Id=uo.WeiXinUserId "
                     + "inner join InstallmentApplicationObjects im on uo.Id=im.UserId " + "where im.Id=:AppId";
        String isWhatsAppDisplayPhoto = DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId)) == null ? "nodata" : "yes";
        param.put("isWhatsAppDisplayPhoto", isWhatsAppDisplayPhoto);
        return param;
    }
}
