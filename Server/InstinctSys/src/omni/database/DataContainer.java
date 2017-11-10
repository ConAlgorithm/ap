package omni.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.object.BaseObject;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.InstalmentApplicationSnapObject;
import omni.database.catfish.object.hybrid.AppContactObject;
import omni.database.catfish.object.hybrid.AppContactPersonObject;
import omni.database.catfish.object.hybrid.AppCountObject;
import omni.database.catfish.object.hybrid.AppEndUserExtensionObject;
import omni.database.catfish.object.hybrid.AppFuserObject;
import omni.database.catfish.object.hybrid.AppJobInfoObject;
import omni.database.catfish.object.hybrid.AppMerchantInfoObject;
import omni.database.catfish.object.hybrid.AppOtherInfoObject;
import omni.database.catfish.object.hybrid.AppPersonalInfoObject;
import omni.database.catfish.object.hybrid.AppProductObject;
import omni.database.catfish.object.hybrid.AppPurposeObject;
import omni.database.catfish.object.hybrid.AppRuleEngineScoreResultObject;
import omni.database.catfish.object.hybrid.AppUserObject;
import omni.database.catfish.object.hybrid.AppUserScanCountObject;
import omni.database.catfish.object.hybrid.AppUserScanRecordObject;
import omni.database.mongo.DerivativeVariables;

/**
 * This class provides data container for Omni data extracted from Catfish and Mongo, and 
 * thus is memory intensive. 
 *
 * @author guoqing
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public final class DataContainer {
    private DataContainer() {

    }

    private static Integer blocksize = StartupConfig.get("instinct.massivedata.blocksize") == null ? 10 : StartupConfig.getAsInt("instinct.massivedata.blocksize");

    public static Map<String, DerivativeVariables> mongodv = new HashMap<>(blocksize, 1);
    public static Map<String, InstallmentApplicationObject> appObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppPurposeObject> purposeObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppJobInfoObject> jobObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppPersonalInfoObject> personObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppEndUserExtensionObject> endUsrObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppUserObject> usrObj = new HashMap<>(blocksize, 1);
    public static Map<String, InstalmentApplicationSnapObject> appSnapObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppRuleEngineScoreResultObject> ruleEngineScoreObj = new HashMap<>(blocksize, 1);
    public static Map<String, List<AppContactPersonObject>> conPerObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppContactObject> conObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppOtherInfoObject> otherInfoObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppMerchantInfoObject> merchantInfoObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppFuserObject> fuserObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppProductObject> productObj = new HashMap<>(blocksize, 1);
    public static Map<String, List<AppUserScanRecordObject>> userScanRecordObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppCountObject> posCountObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppCountObject> d1CountObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppCountObject> s1CountObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppCountObject> userCountObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppUserScanCountObject> userScanPosCountObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppUserScanCountObject> userScanS1CountObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppUserScanCountObject> firstScanObj = new HashMap<>(blocksize, 1);
    public static Map<String, AppCountObject> pdObj = new HashMap<>(blocksize, 1);
//    public static Map<String, AppSecondCreditObject> secondObj = new HashMap<>(blocksize, 1);

    public static void initialize(List<String> appIds) {
        DataContainer.setInstallmentApplicationObject(appIds);
        DataContainer.setAppPurposeObject(appIds);
        DataContainer.setAppJobInfoObject(appIds);
        DataContainer.setAppPersonalInfoObject(appIds);
        DataContainer.setAppEndUserExtensionObject(appIds);
        DataContainer.setAppUserObject(appIds);
        DataContainer.setInstalmentApplicationSnapObject(appIds);
        DataContainer.setAppRuleEngineScoreResultObject(appIds);
        DataContainer.setContactPersonObject(appIds);
        DataContainer.setAppContactObject(appIds);
        DataContainer.setAppOtherInfoObject(appIds);
//        DataContainer.setAppMerchantInfoObject(appIds);
        DataContainer.setAppMerchantInfoObjectNew(appIds);
        
        DataContainer.setAppFuserObject(appIds);
        // v20160802 add start
        DataContainer.setAppProductObject(appIds);
        // v20160802 add end
        DataContainer.setMassiveMongoDv(appIds);
        // v20160809 add start
        DataContainer.setAppUserScanRecordObject(appIds);
        // v20160809 add end
        // v20160830 add start
        DataContainer.setAppPosCountObject(appIds);
        DataContainer.setAppD1CountObject(appIds);
        DataContainer.setAppS1CountObject(appIds);
        DataContainer.setAppUserCountObject(appIds);
        DataContainer.setAppUserScanPosCountObject(appIds);
        DataContainer.setAppUserScanS1CountObject(appIds);
        DataContainer.setAppUserFirstScanObject(appIds);
        DataContainer.setAppPDObject(appIds);
        // v20160830 add end
        // v20161230 add start
//        DataContainer.setSecondCreditObj(appIds); //账务接口下线，改用数仓提供
        // v20161230 add end
    }

    /**
     * This method sets necessary data container for precheck phase.<p>
     * Because some attributes from applications may not be available at this phase, only parts of the container are required.
     * 
     * @param appIds application ids to process.
     * @param instinctCall supports "precheck" only.
     * 
     */
    public static void initialize(List<String> appIds, String instinctCall) {
        if ("precheck".equalsIgnoreCase(instinctCall)) {
            DataContainer.setInstallmentApplicationObject(appIds);
            DataContainer.setAppEndUserExtensionObject(appIds);
            DataContainer.setAppContactObject(appIds);
            DataContainer.setContactPersonObject(appIds);
//            DataContainer.setSecondCreditObj(appIds);
        } else {
            DataContainer.initialize(appIds);
        }
    }

    public static void setAppFuserObject(List<String> appIds) {
        DataContainer.fuserObj.clear();
        //DataContainer.fuserObj = DatabaseClient.catfishDao.getMassiveAppFuserById(appIds);
    }

    /**
     * 设定门店、销售相关信息
     * @param appIds
     */
    /*public static void setAppMerchantInfoObject(List<String> appIds) {
        DataContainer.merchantInfoObj.clear();
        DataContainer.merchantInfoObj = DatabaseClient.catfishDao.getMassiveAppMerchantInfoById(appIds);
        // 通过AppId列表获取D1信息数据集合
        Map<String, AppMerchantInfoObject> sourceMap = DatabaseClient.catfishDao.getMassiveAppD1InfoById(appIds);
        expandDataMap(DataContainer.merchantInfoObj, sourceMap, new ExpandCallback<AppMerchantInfoObject>() {

            @Override
            public void expand(AppMerchantInfoObject target, AppMerchantInfoObject source) {
                target.d1Mobile = source.d1Mobile;
            }
        });
        // 通过AppId列表获取D2信息数据集合
        sourceMap = DatabaseClient.catfishDao.getMassiveAppD2InfoById(appIds);
        expandDataMap(DataContainer.merchantInfoObj, sourceMap, new ExpandCallback<AppMerchantInfoObject>() {

            @Override
            public void expand(AppMerchantInfoObject target, AppMerchantInfoObject source) {
                target.d2Name = source.d2Name;
            }
        });
        // 通过AppId列表获取门店信息数据集合
        sourceMap = DatabaseClient.catfishDao.getMassiveAppMerchantById(appIds);
        expandDataMap(DataContainer.merchantInfoObj, sourceMap, new ExpandCallback<AppMerchantInfoObject>() {

            @Override
            public void expand(AppMerchantInfoObject target, AppMerchantInfoObject source) {
                target.storeCategory = source.storeCategory;
            }
        });
    }*/
    
    public static void setAppMerchantInfoObjectNew(List<String> appIds) {
        DataContainer.merchantInfoObj.clear();
        DataContainer.merchantInfoObj = DatabaseClient.catfishDao.getAppMerchantInfoObject(appIds);
    }

    public static void setAppOtherInfoObject(List<String> appIds) {
        DataContainer.otherInfoObj.clear();
        DataContainer.otherInfoObj = DatabaseClient.catfishDao.getMassiveAppOtherInfoById(appIds);
    }

    public static void setAppContactObject(List<String> appIds) {
        DataContainer.conObj.clear();
        DataContainer.conObj = DatabaseClient.catfishDao.getMassiveAppContactById(appIds);
    }

    public static void setContactPersonObject(List<String> appIds) {
        DataContainer.conPerObj.clear();
        DataContainer.conPerObj = DatabaseClient.catfishDao.getMassiveAppContactPersonById(appIds);
    }

    public static void setAppRuleEngineScoreResultObject(List<String> appIds) {
        DataContainer.ruleEngineScoreObj.clear();
        DataContainer.ruleEngineScoreObj = DatabaseClient.catfishDao.getMassiveAppRuleEngineScoreResultById(appIds);
    }

    public static void setInstalmentApplicationSnapObject(List<String> appIds) {
        DataContainer.appSnapObj.clear();
        DataContainer.appSnapObj = DatabaseClient.catfishDao.getMassiveInstalmentApplicationSnapById(appIds);
    }

    public static void setAppUserObject(List<String> appIds) {
        DataContainer.usrObj.clear();
        DataContainer.usrObj = DatabaseClient.catfishDao.getMassiveAppUserById(appIds);
    }

    public static void setAppEndUserExtensionObject(List<String> appIds) {
        DataContainer.endUsrObj.clear();
        DataContainer.endUsrObj = DatabaseClient.catfishDao.getMassiveAppEndUserExtensionById(appIds);
    }

    public static void setAppPersonalInfoObject(List<String> appIds) {
        DataContainer.personObj.clear();
        DataContainer.personObj = DatabaseClient.catfishDao.getMassiveAppPersonalInfoById(appIds);
    }

    public static void setAppJobInfoObject(List<String> appIds) {
        DataContainer.jobObj.clear();
        DataContainer.jobObj = DatabaseClient.catfishDao.getMassiveAppJobInfoById(appIds);
    }

    public static void setMassiveMongoDv(List<String> appIds) {
        DataContainer.mongodv.clear();
        // v20160830 modifty start
        //		DataContainer.mongodv = DatabaseClient.mongo.load(appIds);
        //		Logger.get().info("Mongo DV's size is " + mongodv.size());
        HashMap<String, DerivativeVariables> mongo = DatabaseClient.mongo.load(appIds);
        if (mongo != null) {
            DataContainer.mongodv = mongo;
            Logger.get().info("Mongo DV's size is " + mongo.size());
        }
        // v20160830 modifty end
        //		if(DataContainer.mongodv==null)
        //		{
        //			Map<String, DerivativeVariables> tmp = new HashMap<>();
        //			appIds.forEach(appId->{
        //				tmp.put(appId, new DerivativeVariables(appIds.get(0)));
        //			});
        //			DataContainer.mongodv = tmp;
        //		}
        //		else if(DataContainer.mongodv.isEmpty())
        //		{
        //			Map<String, DerivativeVariables> tmp = new HashMap<>();
        //			appIds.forEach(appId->{
        //				tmp.put(appId, new DerivativeVariables(appIds.get(0)));
        //			});
        //			DataContainer.mongodv = tmp;
        //		}
    }

    /**
     * 设定分期申请表信息
     * @param appIds
     */
    public static void setInstallmentApplicationObject(List<String> appIds) {
        DataContainer.appObj.clear();
        DataContainer.appObj = DatabaseClient.catfishDao.getMassiveInstallmentApplicationById(appIds);
    }

    public static void setAppPurposeObject(List<String> appIds) {
        DataContainer.purposeObj.clear();
        DataContainer.purposeObj = DatabaseClient.catfishDao.getMassiveAppPurposeById(appIds);
    }

    /**
     * 设定金融产品信息
     * @param appIds
     */
    public static void setAppProductObject(List<String> appIds) {
        DataContainer.productObj.clear();
        DataContainer.productObj = DatabaseClient.catfishDao.getMassiveAppProductById(appIds);
    }

    /**
     * 设定用户扫码记录信息
     * @param appIds
     */
    public static void setAppUserScanRecordObject(List<String> appIds) {
        DataContainer.userScanRecordObj.clear();
        DataContainer.userScanRecordObj = DatabaseClient.catfishDao.getMassiveAppUserScanRecordById(appIds);
    }

    /**
     * 设定POS批核数、拒绝数、批核率信息
     * @param appIds
     */
    public static void setAppPosCountObject(List<String> appIds) {
        DataContainer.posCountObj.clear();
        DataContainer.posCountObj = DatabaseClient.catfishDao.getMassiveAppPosCountById(appIds);
    }

    /**
     * 设定D1批核数、拒绝数、批核率信息
     * @param appIds
     */
    public static void setAppD1CountObject(List<String> appIds) {
        DataContainer.d1CountObj.clear();
        DataContainer.d1CountObj = DatabaseClient.catfishDao.getMassiveAppD1CountById(appIds);
    }

    /**
     * 设定S1批核数、拒绝数、批核率信息
     * @param appIds
     */
    public static void setAppS1CountObject(List<String> appIds) {
        DataContainer.s1CountObj.clear();
        DataContainer.s1CountObj = DatabaseClient.catfishDao.getMassiveAppS1CountById(appIds);
    }

    /**
     * 设定用户总申请次数、用户总申请批准次数、用户总申请拒绝次数、用户总申请取消次数信息
     * @param appIds
     */
    public static void setAppUserCountObject(List<String> appIds) {
        DataContainer.userCountObj.clear();
        DataContainer.userCountObj = DatabaseClient.catfishDao.getMassiveAppUserCountById(appIds);
    }

    /**
     * 设定用户扫描POS门店次数信息
     * @param appIds
     */
    public static void setAppUserScanPosCountObject(List<String> appIds) {
        DataContainer.userScanPosCountObj.clear();
        DataContainer.userScanPosCountObj = DatabaseClient.catfishDao.getMassiveAppUserScanPOSCountById(appIds);
    }

    /**
     * 设定用户扫描S码次数信息
     * @param appIds
     */
    public static void setAppUserScanS1CountObject(List<String> appIds) {
        DataContainer.userScanS1CountObj.clear();
        DataContainer.userScanS1CountObj = DatabaseClient.catfishDao.getMassiveAppUserScanS1CountById(appIds);
    }

    /**
     * 设定用户首次扫码信息
     * @param appIds
     */
    public static void setAppUserFirstScanObject(List<String> appIds) {
        DataContainer.firstScanObj.clear();
        DataContainer.firstScanObj = DatabaseClient.catfishDao.getMassiveAppUserFirstScanInfoById(appIds);
    }

    /**
     * 设定PD信息
     * @param appIds
     */
    public static void setAppPDObject(List<String> appIds) {
        DataContainer.pdObj.clear();
//        // v20160928 账户系统接口性能原因下线
        DataContainer.pdObj = DatabaseClient.settlementService.getMassiveAppD2FPD7RateInfoById(appIds);
    }

    /**
     * <p>〈获取二次贷相关信息〉</p>
     * 
     * @param appId
     */
//    public static void setSecondCreditObj(List<String> appIds) {
//        DataContainer.secondObj.clear();
//        DataContainer.secondObj = DatabaseClient.settlementService.getSecondCreditInfoById(appIds);
//    }

    public static void clear() {
        DataContainer.mongodv.clear();
        DataContainer.appObj.clear();
        DataContainer.purposeObj.clear();
        DataContainer.jobObj.clear();
        DataContainer.personObj.clear();
        DataContainer.endUsrObj.clear();
        DataContainer.usrObj.clear();
        DataContainer.appSnapObj.clear();
        DataContainer.ruleEngineScoreObj.clear();
        DataContainer.conPerObj.clear();
        DataContainer.conObj.clear();
        DataContainer.otherInfoObj.clear();
        DataContainer.merchantInfoObj.clear();
        DataContainer.fuserObj.clear();
        // v20160802 add start
        DataContainer.productObj.clear();
        // v20160802 add end
        // v20160809 add start
        DataContainer.userScanRecordObj.clear();
        // v20160809 add end
        // v20160830 add start
        DataContainer.posCountObj.clear();
        DataContainer.d1CountObj.clear();
        DataContainer.s1CountObj.clear();
        DataContainer.userCountObj.clear();
        DataContainer.userScanPosCountObj.clear();
        DataContainer.userScanS1CountObj.clear();
        DataContainer.firstScanObj.clear();
        DataContainer.pdObj.clear();
        // v20160830 add end
        // v20161230 add start
//        DataContainer.secondObj.clear();
        // v20161230 add end
    }

    /**
     * 扩展数据容器
     * @param targetMap
     * @param sourceMap
     */
    @SuppressWarnings("unused")
    private static <T extends BaseObject> void expandDataMap(Map<String, T> targetMap, Map<String, T> sourceMap, ExpandCallback<T> callback) {
        if (targetMap == null || sourceMap == null)
            return;
        sourceMap.entrySet().forEach(sourceObject -> {
            if (targetMap.containsKey(sourceObject.getKey())) {
                callback.expand(targetMap.get(sourceObject.getKey()), sourceObject.getValue());
            } else {
                targetMap.put(sourceObject.getKey(), sourceObject.getValue());
            }
        });
    }

    /**
     * 扩展数据Map接口
     * @author baowzh
     *
     */
    interface ExpandCallback<T extends BaseObject> {
        void expand(T target, T source);
    }
}
