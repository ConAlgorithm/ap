package engine.rule.model.in.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import catfish.base.business.common.StoreType;
import catfish.base.business.object.MerchantStoreRiskControlObject;
import catfish.base.business.object.MerchantUserObject;
import catfish.base.business.object.context.MerchantStoreData;
import engine.rule.model.BaseForm;

public class StoreInfoInFormProxy extends BaseForm implements IStoreInfoInForm {

    // 门店上下文信息
    private MerchantStoreData storeData;

    // 申请列表
//    private List<InstallmentApplicationObject> appList;

    // 门店风险控制对象
    private MerchantStoreRiskControlObject riskObj;

    // 门店S1对象
    private MerchantUserObject s1Obj;
    
    //放款总额
    private BigDecimal sumMoneyForLoan=new BigDecimal(0);

    //放款时间(为了能够减小对象大小)
    private List<Date> appListDate;

    public String getStoreId() {
        return (storeData.storeObj.Code == null ? "" : storeData.storeObj.Code);
    }

    public String getMerchantId() {
        return (storeData.storeObj.MerchantCompanyId == null ? ""
            : storeData.storeObj.MerchantCompanyId);
    }

    public String getLocatedDistrict() {
        return (storeData.locatedDistrict == null ? "" : storeData.locatedDistrict);
    }

    
    public String getLocatedCity() {
        return (storeData.locatedCity == null ? "" : storeData.locatedCity);
    }

    public String getLocatedProvince() {
        return (storeData.locatedProvince == null ? "" : storeData.locatedProvince);
    }

    //得到门店类型
    @Override
    public Integer getStorecategory() {
        return storeData.storeCategory == null ? StoreType.None.getValue() : storeData.storeCategory;
    }

    @Override
    public Integer getCommoditycategories() {

        return storeData.commodityCategories;
    }

    @Override
    public int getHasSmuggledGoods() {

        return storeData.hasSmuggledGoods;
    }

    @Override
    public int getHasSecondHandGoods() {

        return storeData.hasSecondHandGoods;
    }

    @Override
    public int getHasRepairedGoods() {

        return storeData.hasRepairedGoods;
    }

    @Override
    public int GetNumOfOpenday() {

        return storeData.numOfOpenday;
    }

    //    @Override
    //    public String GetPosRank() {
    //
    //        return (storeData.PosRank == null ? "" : storeData.PosRank);
    //    }

    public String getS1IdCardNumber() {
        return (s1Obj == null ? "" : s1Obj.IdNumber);
    }

    public int getFirstOverDue7DayCount() {
        return storeData.overDueNdaysUserCount;
    }

    public int getFirstOverDueLessThan7DayCount() {
        return storeData.notOverDueNdaysUserCount;
    }

    public int getCompletedAppCount() {
        return storeData.completedApplicationCount;
    }

    public int getCompletedAppCountByMonth() {
        return storeData.completedApplicationCountByMonth;
    }

    public int getOverDueCount() {
        return storeData.overDueCount;
    }

    public BigDecimal getStatedRiskExposure() {
        boolean flag = (riskObj == null || riskObj.RiskExposure == null);
        // 如果没有获取到，默认为10000000，表示没有上限
        return (flag ? new BigDecimal(10000000) : riskObj.RiskExposure);
    }

    public BigDecimal getFactualRiskExposure() {
        return sumMoneyForLoan;
    }

    public int getNDaysStatedQuantityLimitNum(int dayCount) {
        // 默认为一个很大的数，因为现在数据库设计的只存了当天，3天，7天，15天进件量，如果dayCount不为这几天，则默认没有超出额度
        int count = 10000;
        switch (dayCount) {
            case 1:
                count = ((riskObj == null || riskObj.QuantityLimitOneDay == null) ? count
                    : riskObj.QuantityLimitOneDay);
                break;
            case 3:
                count = ((riskObj == null || riskObj.QuantityLimitThreeDays == null) ? count
                    : riskObj.QuantityLimitThreeDays);
                break;
            case 7:
                count = ((riskObj == null || riskObj.QuantityLimitSevenDays == null) ? count
                    : riskObj.QuantityLimitSevenDays);
                break;
            case 15:
                count = ((riskObj == null || riskObj.QuantityLimitFifteenDays == null) ? count
                    : riskObj.QuantityLimitFifteenDays);
                break;
            default:
                ;
        }
        return count;
    }

    public int getNDaysFactualQuantityLimitNum(int dayCount) {
        int count = 0;
        LocalDate time = DateTime.now().plusDays(1 - dayCount).toLocalDate();
        for (Date  installmentStartedOn: appListDate) {
            if (new DateTime(installmentStartedOn).toLocalDate().compareTo(time) >= 0)
                count++;
        }
        return count;
    }

    public MerchantStoreData getStoreData() {
        return storeData;
    }

    public void setStoreData(MerchantStoreData storeData) {
        this.storeData = storeData;
    }

//    public List<InstallmentApplicationObject> getAppList() {
//        return appList;
//    }
//
//    public void setAppList(List<InstallmentApplicationObject> appList) {
//        this.appList = appList;
//    }

    public MerchantStoreRiskControlObject getRiskObj() {
        return riskObj;
    }

    public void setRiskObj(MerchantStoreRiskControlObject riskObj) {
        this.riskObj = riskObj;
    }

    public MerchantUserObject getS1Obj() {
        return s1Obj;
    }

    public void setS1Obj(MerchantUserObject s1Obj) {
        this.s1Obj = s1Obj;
    }

    public BigDecimal getSumMoneyForLoan() {
        return sumMoneyForLoan;
    }

    public void setSumMoneyForLoan(BigDecimal sumMoneyForLoan) {
        this.sumMoneyForLoan = sumMoneyForLoan;
    }

    public List<Date> getAppListDate() {
        return appListDate;
    }

    public void setAppListDate(List<Date> appListDate) {
        this.appListDate = appListDate;
    } 
    

}
