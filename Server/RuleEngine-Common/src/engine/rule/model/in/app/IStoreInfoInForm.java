package engine.rule.model.in.app;

import java.math.BigDecimal;

public interface IStoreInfoInForm {

    public String getStoreId();

    public String getMerchantId();

    public String getLocatedCity();

    public String getLocatedProvince();

    public String getLocatedDistrict();

    public String getS1IdCardNumber();

    public int getFirstOverDue7DayCount();

    public int getFirstOverDueLessThan7DayCount();

    public int getCompletedAppCount();

    public int getCompletedAppCountByMonth();

    public int getOverDueCount();

    public BigDecimal getStatedRiskExposure();

    public BigDecimal getFactualRiskExposure();

    public int getNDaysStatedQuantityLimitNum(int dayCount);

    public int getNDaysFactualQuantityLimitNum(int dayCount);

    public Integer getStorecategory();//得到门店类型

    //门店范围
    public Integer getCommoditycategories();

    //是否有水货
    public int getHasSmuggledGoods();

    //是否有二手货
    public int getHasSecondHandGoods();

    //否有返修货
    public int getHasRepairedGoods();

    //得到门店做单至今天数
    public int GetNumOfOpenday();
    //    public String GetPosRank();
}
