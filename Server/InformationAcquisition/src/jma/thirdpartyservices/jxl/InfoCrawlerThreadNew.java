/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.thirdpartyservices.jxl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.business.dao.ContactDao;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.RawData;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.httpclient.object.BaseObject;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;
import jma.AppDerivativeVariablesBuilder;
import jma.Configuration;
import jma.JobStatus;
import jma.models.DataSourceResponseBase;
import jma.models.jxl.ContactList;
import jma.models.jxl.ContactRegion;
import jma.models.jxl.JXLV4ResponseModel;
import jma.models.jxl.UserCheckBlackInfo;
import jma.models.jxl.UserCheckSearchInfo;
import jma.models.jxl.UserInfoCheck;
import jma.util.DSPApiUtils;
import thirdparty.config.JXLConfiguration;

/**
 * 〈聚信立V4.2获取信息〉
 *
 * @author hwei
 * @version InfoCrawlerThreadNew.java, V1.0 2017年2月4日 下午2:26:38
 */
public class InfoCrawlerThreadNew implements Runnable {
    private static final String JobFinishedName = "JXLInfoCrawlFinished";
    private static final int sleepSeconds = 2 * 60;
    //重试时间间隔
    private static final int retryIntervalSeconds = 60;
    private String appId;

    public InfoCrawlerThreadNew(String appId) {
        this.appId = appId;
    }

    @Override
    public void run() {
        QueueMessager messager = new QueueMessager(appId, JobFinishedName, JobStatus.SUCCEESS);
        try {
            //先sleep2分钟，以保证能够更大几率收集到数据
            ThreadUtils.sleepInSeconds(sleepSeconds);
            Logger.get().info("InfoCrawlerThreadNew execute appId:" + appId);
            int retryCount = JXLConfiguration.getJxlMaxRetries();
            Map<String, Object> param = getUserBaseInfoModel();
            if (param == null) {
                Logger.get().info("getUserBaseInfoModel param is null");
                return;
            }
            //设置聚信立版本号
            String version = Configuration.getJxlNewVersion();
            if(version!=null&&"V4.2".equals(version)){
                param.put("interfaceVersion", version);                
            }
            Logger.get().info("request  jxldsp param is " + new Gson().toJson(param));
            JXLV4ResponseModel model = null;
            while (retryCount > 0) {
                ThreadUtils.sleepInSeconds(retryIntervalSeconds);
                model = getJXLData(param);
                if (model == null) {
                    Logger.get().info(String.format("data is null from JXL by appId = %s , retry count : %s", appId, retryCount));
                    retryCount--;
                } else {
                    break;
                }
            }
            if (model == null) {
                AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(appId, AppDerivativeVariableNames.IS_JXLREPROT_EXIST, false));
                messager.setJobStatus(JobStatus.ERROR);
            } else {
                //相关数据写入mogodb
                Map<String, Integer> map = (getUserCheck_SearchInfo(model) != null ? getUserCheck_SearchInfo(model) : new HashMap<>());
                AppDerivativeVariableManager.addVariables(new AppDerivativeVariablesBuilder(appId)
                    .isNotNullAdd(AppDerivativeVariableNames.IS_JXLREPROT_EXIST, true)
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_REGION_COUNT, model.getX_JXL_ReportData_ContactRegion_Count())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_COUNT, model.getX_JXL_ReportData_Contact_Count())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_LENGTH, model.getX_JXL_ReportData_CallOut_Length())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALL_OUT_COUNT, model.getX_JXL_ReportData_CallOut_Count())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_LENGTH, model.getX_JXL_ReportData_CallIn_Length())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALL_IN_COUNT, model.getX_JXL_ReportData_CallIn_Count())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_DATA_EXIST_MONTH_COUNT, model.getX_JXL_ReportData_DataExistMonth_Count())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_NUMBER_COUNT, model.getX_JXL_ReportData_ContactNumber_Count())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_IS_REAL_AUTHENTICATED, model.getX_JXL_ReportData_IsRealAuthenticated())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_IS_PROVIDER_INFO_MATCH, model.getX_JXL_ReportData_IsProviderInfoMatch())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_IS_ALWAYS_POWEROFF, model.getX_JXL_ReportData_IsAlwaysPowerOff())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALLlOANPHONE, model.getX_JXL_ReportData_CallLoanPhone())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALLFINANCEPHONE, model.getX_JXL_ReportData_CallFinancePhone())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CALLJIEXINPHONE, model.getX_JXL_ReportData_CallJieXinPhone())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_REG_CONTACTPHONE_IN_JXL_NUM, model.getX_JXL_ReportData_regContactPhoneInJXLNum())
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_SEARCHEDORGCNT, getSearched_OrgCnt(model))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_IDCARDWITHOTHERNAMESCOUNT, map.get("IdcardWithOtherNames"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_IDCARDWITHOTHERPHONESCOUNT, map.get("IdcardWithOtherPhones"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_PHONEWITHOTHERNAMESCOUNT, map.get("PhoneWithOtherNames"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_PHONEWITHOTHERIDCARDSCOUNT, map.get("PhoneWithOtherIdcards"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_ARISEDOPENWEBCOUNT, map.get("ArisedOpenWeb"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REGISTERORGCNT, map.get("RegisterOrgCnt"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_PHONEGRAYSCORE, map.get("PhoneGrayScore"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_CONTACTSCLASS1BLACKLISTCNT, map.get("ContactsClass1BlacklistCnt"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_CONTACTSCLASS2BLACKLISTCNT, map.get("ContactsClass2BlacklistCnt"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_CONTACTSCLASS1CNT, map.get("ContactsClass1Cnt"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_CONTACTSROUTERCNT, map.get("ContactsRouterCnt"))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_CONTACTSROUTERRATIO, getContactsRouterRatio(model))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_CONTACTORCITIESNUMBER, getContactorCitiesNumber(model))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_CONTACTNUMBERCOUNT, getContactNumberCount(model))
                    .isNotNullAdd(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_LIST, BaseObject.toJson(model.getX_JXL_ReportData_ContactList()))
                    .build());

            }
        } catch (Exception e) {
            messager.setJobStatus(JobStatus.ERROR);
            Logger.get().error(String.format("CrawlingNew run 聚信力 Info of AppId: %s error!", appId), e);
        } finally {
            //发消息通知flow
            PersistenceQueueApi.writeMessager(Configuration.getJobResponseQueue(), messager, MessageSource.InformationAcquisition);
        }
    }

    /**
     * <p>〈获取请求的用户相关信息姓名，手机号，身份证号〉</p>
     * 
     * @param appId
     * @return
     */
    protected Map<String, Object> getUserBaseInfoModel() {
        Map<String, Object> param = new HashMap<String, Object>();
        EndUserExtensionObject userObj = new EndUserExtentionDao(appId).getSingle();
        ContactObject contactObj = new ContactDao(appId).getSingle();
        if (userObj != null) {
            param.put("name", userObj.IdName);
            param.put("idNo", userObj.IdNumber);
        }
        if (contactObj != null) {
            param.put("mobile", contactObj.Content);
        }
        return param;
    }

    /**
     * <p>〈调用聚信立dsp数据源获取数据〉</p>
     * 
     * @return
     */
    protected JXLV4ResponseModel getJXLData(Map<String, Object> map) {
        JXLV4ResponseModel model = null;
        String url = Configuration.getJxlNewUrl();
        try {
            //调用dsp
            DataSourceResponseBase<JXLV4ResponseModel> res = DSPApiUtils.invokeDspApi(appId, url, map,
                new TypeToken<DataSourceResponseBase<JXLV4ResponseModel>>() {
                }.getType());
            RawDataStorageManager.addRawDatas(new RawData(appId, RawDataVariableNames.JXL_REPORTDATA_RAW_DATA, new Gson().toJson(res)));
            if (res != null && res.getCode() != 200) {
                Logger.get().warn(String.format("request doesnot success,retry. url=%s, result=%s", url, new Gson().toJson(res)));
                return null;
            }
            List<JXLV4ResponseModel> data = res.getData();
            if (data == null || data.size() == 0) {
                Logger.get().warn("response data is null.");
                return null;
            }
            model = data.get(0);
        } catch (Exception e) {
            Logger.get().warn(String.format("CrawlingNew getJXLData of AppId: %s error!", appId), e);
        }
        return model;
    }

    public String getAppId() {
        return appId;
    }
    
    /**
    * <p>〈获取查询过该用户的相关企业数量〉</p>
    * 
    * @return
    */
    public Integer getSearched_OrgCnt(JXLV4ResponseModel model) {
        if (model != null && model.getX_JXL_ReportData_UserInfoCheck() != null) {
            UserInfoCheck userInfoCheck = model.getX_JXL_ReportData_UserInfoCheck();
            if(userInfoCheck.getCheckSearchInfo()!=null){
                return userInfoCheck.getCheckSearchInfo().getSearchedOrgCnt();
            }
        }
        return null;
    }
    
    /**
     * <p>〈获取用户信息检测的信息〉</p>
     * 
     * @return
     */
    public Map<String, Integer> getUserCheck_SearchInfo(JXLV4ResponseModel model) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (model != null && model.getX_JXL_ReportData_UserInfoCheck() != null) {
            UserInfoCheck userInfoCheck = model.getX_JXL_ReportData_UserInfoCheck();
            if (userInfoCheck.getCheckSearchInfo() != null) {
                UserCheckSearchInfo searchInfo = userInfoCheck.getCheckSearchInfo();
                if (searchInfo.getIdcardWithOtherNames() != null) {
                    map.put("IdcardWithOtherNames", new HashSet<String>(searchInfo.getIdcardWithOtherNames()).size());
                }
                if (searchInfo.getIdcardWithOtherPhones() != null) {
                    map.put("IdcardWithOtherPhones", new HashSet<String>(searchInfo.getIdcardWithOtherPhones()).size());
                }
                if (searchInfo.getPhoneWithOtherNames() != null) {
                    map.put("PhoneWithOtherNames", new HashSet<String>(searchInfo.getPhoneWithOtherNames()).size());
                }
                if (searchInfo.getPhoneWithOtherIdcards() != null) {
                    map.put("PhoneWithOtherIdcards", new HashSet<String>(searchInfo.getPhoneWithOtherIdcards()).size());
                }
                if (searchInfo.getArisedOpenWeb() != null) {
                    map.put("ArisedOpenWeb", new HashSet<String>(searchInfo.getArisedOpenWeb()).size());
                }
                if (searchInfo.getRegisterOrgCnt() != null) {
                    map.put("RegisterOrgCnt", searchInfo.getRegisterOrgCnt());
                }
            }
            if (userInfoCheck.getCheckBlackInfo() != null) {
                UserCheckBlackInfo userCheckBlackInfo = userInfoCheck.getCheckBlackInfo();
                if(userCheckBlackInfo.getPhoneGrayScore()!=null){
                    map.put("PhoneGrayScore", userCheckBlackInfo.getPhoneGrayScore());
                }
                if(userCheckBlackInfo.getContactsClass1BlacklistCnt()!=null){
                    map.put("ContactsClass1BlacklistCnt", userCheckBlackInfo.getContactsClass1BlacklistCnt());
                }
                if(userCheckBlackInfo.getContactsClass2BlacklistCnt()!=null){
                    map.put("ContactsClass2BlacklistCnt", userCheckBlackInfo.getContactsClass2BlacklistCnt());
                }
                if(userCheckBlackInfo.getContactsClass1Cnt()!=null){
                    map.put("ContactsClass1Cnt", userCheckBlackInfo.getContactsClass1Cnt());
                }
                if(userCheckBlackInfo.getContactsRouterCnt()!=null){
                    map.put("ContactsRouterCnt", userCheckBlackInfo.getContactsRouterCnt());
                }
            }

        }
        return map;
    }

    
    /**
     * <p>〈获取直接联系人中引起间接黑名单占比〉</p>
     * 
     * @return
     */
    public Float getContactsRouterRatio(JXLV4ResponseModel model){
        if (model != null && model.getX_JXL_ReportData_UserInfoCheck() != null) {
            UserInfoCheck userInfoCheck = model.getX_JXL_ReportData_UserInfoCheck();
            if (userInfoCheck.getCheckBlackInfo() != null){
                return userInfoCheck.getCheckBlackInfo().getContactsRouterRatio();
            }
        }
        return null;
    }
   
    /**
     * <p>〈获取联系范围城市数〉</p>
     * 
     * @return
     */
    public Integer getContactorCitiesNumber(JXLV4ResponseModel model) {
        if (model != null && model.getX_JXL_ReportData_ContactRegion() != null) {
            List<ContactRegion> list = model.getX_JXL_ReportData_ContactRegion();
            Set<String> regionLoc = new HashSet<>();
            for (ContactRegion contactRegion : list) {
                regionLoc.add(contactRegion.getRegionLoc());
            }
            return regionLoc.size();
        }
        return null;
    }
    
    
    /**
     * <p>〈获取联系人数〉</p>
     * 
     * @return
     */
    public Integer getContactNumberCount(JXLV4ResponseModel model) {
        if (model != null && model.getX_JXL_ReportData_ContactList() != null) {
            List<ContactList> list = model.getX_JXL_ReportData_ContactList();
            Set<String> phoneNum = new HashSet<>();
            for (ContactList contactList : list) {
                phoneNum.add(contactList.getPhoneNum());
            }
            return phoneNum.size();
        }
        return null;
    }
}
