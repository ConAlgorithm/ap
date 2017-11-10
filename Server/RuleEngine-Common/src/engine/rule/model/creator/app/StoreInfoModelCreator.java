package engine.rule.model.creator.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.LocalDate;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.CNAreaType;
import catfish.base.business.common.StoreType;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.MerchantStoreDao;
import catfish.base.business.dao.MerchantStoreRiskControlDao;
import catfish.base.business.object.CNAreaObject;
import catfish.base.business.object.InstallmentApplicationObject2;
import catfish.base.business.object.MerchantStoreObject;
import catfish.base.business.object.context.MerchantStoreData;
import engine.rule.model.creator.AbstractModelCreator;
import engine.rule.model.in.app.StoreInfoInForm;
import engine.rule.model.in.app.StoreInfoInFormProxy;
import engine.util.SettlementService;
import engine.util.SettlementService.PaymentInfo;

public class StoreInfoModelCreator extends AbstractModelCreator {

    private static ConcurrentMap<String, MerchantStoreData> storeDataMap = new ConcurrentHashMap<String, MerchantStoreData>();
    private String storeId;

    public StoreInfoModelCreator(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public Map<String, Object> createModelForm() {
        LocalDate date = LocalDate.now();

        StoreInfoInFormProxy storeForm = new StoreInfoInFormProxy();
        form = new StoreInfoInForm(storeForm);

        MerchantStoreObject storeObj = new MerchantStoreDao(this.storeId).getSingle();

        // String storeId = storeObj.Id;
        MerchantStoreData storeData = storeDataMap.get(this.storeId);

        if (storeData == null || storeData.analyzeTime.compareTo(date.toDate()) < 0) {
            storeData = new MerchantStoreData();
            storeData.analyzeTime = date.toDate();
            storeData.storeObj = storeObj;
            // 设置总完成单数
            storeData.completedApplicationCount = MerchantStoreDao
                .getLoanCompletedAppCountByTime(storeId, null);

            LocalDate lastDate = date.plusDays(-30);
            // 设置一个月内完成单数
            storeData.completedApplicationCountByMonth = MerchantStoreDao
                .getLoanCompletedAppCountByTime(storeId, lastDate.toDate());
            // 设置拒绝单数
            storeData.rejectedApplicationCount = MerchantStoreDao
                .getAppCountByStatusAndTime(storeId, ApplicationStatus.Rejected, null);
            // 设置一个月内拒绝单数
            storeData.rejectedApplicationCountByMonth = MerchantStoreDao
                .getAppCountByStatusAndTime(storeId, ApplicationStatus.Rejected, lastDate.toDate());
            // 设置总单数
            storeData.applicationCount = MerchantStoreDao.getAppCountByTime(storeObj.Id, null);
            // 设置一个月总单数
            storeData.applicationCountByMonth = MerchantStoreDao.getAppCountByTime(storeId,
                lastDate.toDate());

            // 查询门店下app数据
            List<String> appIdList = MerchantStoreDao.getOverdueAppId(storeId);
            Map<String, List<PaymentInfo>> paymentInfo = SettlementService
                .queryPaymentInfo(appIdList);
            // 设置逾期单数
            storeData.overDueCount = SettlementService.getOverDueAppCount(paymentInfo, 5);
//            storeData.overDueCount = SettlementService.getNewOverDueAppCount(appIdList);
            Logger.get().info("OverDueCount = " + storeData.overDueCount);
//            Map<String, Integer> map = SettlementService.getOverDueNdaysId(appIdList);
            // 设置首逾>=7天用户数
            List<String> overdueAppIdList = SettlementService
                .getOverDueAppIdWithInstalmentNum(paymentInfo, 7, 1);
//            List<String> overdueAppIdList = this.getOverdueAppIdListInfo(map, 1);
            if (overdueAppIdList == null || overdueAppIdList.size() == 0) {
                storeData.overDueNdaysUserCount = 0;
            } else {
                Logger.get().info("overdueAppIdList:");
                for (String appId : overdueAppIdList) {
                    Logger.get().info(appId + ",");
                }
                storeData.overDueNdaysUserCount = MerchantStoreDao
                    .userCountByAppId(overdueAppIdList);
            }
            Logger.get().info("OverDueNdaysUserCount = " + storeData.overDueNdaysUserCount);
            // 设置首逾未超过7天用户数量
            List<String> notOverdueAppIdList = SettlementService
                .getNotOverDueAppIdWithInstalmentNum(paymentInfo, 7, 1);
//            List<String> notOverdueAppIdList = this.getOverdueAppIdListInfo(map, 0);
            if (notOverdueAppIdList == null || notOverdueAppIdList.size() == 0) {
                storeData.notOverDueNdaysUserCount = 0;
            } else {
                Logger.get().info("notOverdueAppIdList:");
                for (String appId : notOverdueAppIdList) {
                    Logger.get().info(appId + ",");
                }
                storeData.notOverDueNdaysUserCount = MerchantStoreDao
                    .userCountByAppId(notOverdueAppIdList);
            }
            Logger.get().info("NotOverDueNdaysUserCount = " + storeData.notOverDueNdaysUserCount);
            // 设置门店所在城市，省份
            CNAreaObject area = new CNAreaDao(storeObj.CNAreaId).getSingle();
            storeData.locatedCity = MerchantStoreDao.getLocatedCityByAreaCode(area.Code);
            storeData.locatedProvince = MerchantStoreDao.getLocatedProvinceByAreaCode(area.Code);
            String locatedDistrict = MerchantStoreDao.getLocatedInfoByAreaCodeAndAreaType(area.Code, CNAreaType.Area);
            storeData.locatedDistrict = locatedDistrict == null ? "" : locatedDistrict;
            storeData.storeCategory= storeObj.StoreCategory  == null ? StoreType.None.getValue() : storeObj.StoreCategory;
            //--门店商品范围
            storeData.commodityCategories = storeObj.CommodityCategories == null ? -1
                : storeObj.CommodityCategories;
            //--是否有水货
            storeData.hasSmuggledGoods = storeObj.HasSmuggledGoods == null ? -1
                : storeObj.HasSmuggledGoods;
            // --是否有二手货
            storeData.hasSecondHandGoods = storeObj.HasSecondHandGoods == null ? -1
                : storeObj.HasSecondHandGoods;
            // --否有返修货
            storeData.hasRepairedGoods = storeObj.HasRepairedGoods == null ? -1
                : storeObj.HasRepairedGoods;

            Integer installmentStartedOn4FirstPosByStoreId = MerchantStoreDao
                .getInstallmentStartedOn4FirstPosByStoreId(storeId);

            storeData.numOfOpenday = (installmentStartedOn4FirstPosByStoreId == null ? -1
                : installmentStartedOn4FirstPosByStoreId);

            storeDataMap.put(storeId, storeData);
        }
        // 设置门店上下文信息
        storeForm.setStoreData(storeData);

        // 设置门店所有已经放款申请信息
        setFactualRiskExposure(storeForm);

        // 设置门店风险信息
        storeForm.setRiskObj(new MerchantStoreRiskControlDao(storeId).getSingle());

        StoreInfoInForm e = (StoreInfoInForm) form;

        e.getHasRepairedGoods();

        return CollectionUtils.mapOf("in_Store", (Object) form);
    }

    @Override
    public String createBusinessNo() {
        // TODO Auto-generated method stub
        return null;
    }

    public static ConcurrentMap<String, MerchantStoreData> getStoreDataMap() {
        return storeDataMap;
    } 
    
    //得到实际放款额并且得到申请对应的时间列表
    public void setFactualRiskExposure(StoreInfoInFormProxy storeForm) {
        List<InstallmentApplicationObject2> appList=MerchantStoreDao.getAllAppsOfMoneyTransferred2(storeId, ApplicationStatus.Completed);
        List<Date> appListDate=new ArrayList<>();
        for(InstallmentApplicationObject2 a:appList){
            appListDate.add(a.InstallmentStartedOn);
        }
        storeForm.setAppListDate(appListDate); 
        
        storeForm.setSumMoneyForLoan(getFactualRiskExposure(appList)); 
    } 
    
    public BigDecimal getFactualRiskExposure(List<InstallmentApplicationObject2> appList){
        Calendar cal = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        BigDecimal sum = new BigDecimal(0);
        for (InstallmentApplicationObject2 obj : appList) {
            cal.setTime(obj.InstallmentStartedOn);
            if (cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
                sum = sum.add(obj.Principal);
            }
        }
        return sum;
    }
    
    public List<String> getOverdueAppIdListInfo( Map<String, Integer> map , int flag){
        List<String> list = new ArrayList<>();
        try {
            if(map!=null&&map.size()>0){
                for(Map.Entry<String, Integer>  m : map.entrySet()){
                    if(m.getValue()!=null&&m.getValue().intValue()==flag){
                        list.add(m.getKey());
                    }
                }
            }
        } catch (Exception e) {
            Logger.get().error(String.format("getOverdueAppIdListInfo error! "), e);
        }
        return list;
        
    }
    
}
