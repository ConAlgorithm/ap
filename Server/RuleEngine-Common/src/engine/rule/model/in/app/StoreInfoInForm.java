package engine.rule.model.in.app;

import java.math.BigDecimal;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import catfish.base.Logger;
import catfish.base.business.common.RepairedGoodsType;
import catfish.base.business.common.SecondHandGoodsType;
import catfish.base.business.common.SmuggledGoodsType;
import engine.rule.model.BaseForm;

@ModelInstance(description = "门店评级材料")
public class StoreInfoInForm extends BaseForm {

    private IStoreInfoInForm proxyForm = null;

    public StoreInfoInForm() {
    }

    public StoreInfoInForm(IStoreInfoInForm form) {
        this.proxyForm = form;
    }

    @ModelMethod(name = "(this)的门店号")
    public String getStoreId() {
        return this.proxyForm.getStoreId();
    }

    @ModelMethod(name = "(this)的商户号")
    public String getMerchantId() {
        return this.proxyForm.getMerchantId();
    }

    // TODO!!!!
    @ModelMethod(name = "(this)的门店所在城市")
    public String getLocatedCity() {
        return this.proxyForm.getLocatedCity();
    }

    @ModelMethod(name = "(this)的门店所在省份")
    public String getLocatedProvince() {
        return this.proxyForm.getLocatedProvince();
    }

    @ModelMethod(name = "(this)的门店所在区")
    public String getLocatedDistrict() {
        return this.proxyForm.getLocatedDistrict();
    }

    @ModelMethod(name = "(this)的S1身份证号")
    public String getS1IdCardNumber() {
        return this.proxyForm.getS1IdCardNumber();
    }

    @ModelMethod(name = "(this)的首逾超过7天用户数量")
    public int getFirstOverDue7DayCount() {
        return this.proxyForm.getFirstOverDue7DayCount();
    }

    @ModelMethod(name = "(this)的首逾未超过7天用户数量")
    public int getFirstOverDueLessThan7DayCount() {
        return this.proxyForm.getFirstOverDueLessThan7DayCount();
    }

    @ModelMethod(name = "(this)的完成单数")
    public int getCompletedAppCount() {
        return this.proxyForm.getCompletedAppCount();
    }

    @ModelMethod(name = "(this)的一个月内完成单数")
    public int getCompletedAppCountByMonth() {
        return this.proxyForm.getCompletedAppCountByMonth();
    }

    @ModelMethod(name = "(this)的逾期单数")
    public int getOverDueCount() {
        return this.proxyForm.getOverDueCount();
    }

    @ModelMethod(name = "(this)的预设当月放款总额")
    public BigDecimal getStatedRiskExposure() {
        return this.proxyForm.getStatedRiskExposure();
    }

    @ModelMethod(name = "(this)的实际当月放款总额")
    public BigDecimal getFactualRiskExposure() {
        return this.proxyForm.getFactualRiskExposure();
    }

    @ModelMethod(name = "(this)的预设(#1,<天数>)天内进件量", paramDomains = "engine.rule.domain.QuantityLimitDays")
    public int getNDaysStatedQuantityLimitNum(int dayCount) {
        try {
            return this.proxyForm.getNDaysStatedQuantityLimitNum(dayCount);
        } catch (Exception e) {
            Logger.get().error(String.format("get stated daycount: %d from proxy error.", dayCount),
                e);
        }
        return 0;
    }

    @ModelMethod(name = "(this)的实际(#1,<天数>)天内进件量")
    public int getNDaysFactualQuantityLimitNum(int dayCount) {
        try {
            return this.proxyForm.getNDaysFactualQuantityLimitNum(dayCount);
        } catch (Exception e) {
            Logger.get()
                .error(String.format("get factual daycount: %d from proxy error.", dayCount), e);
        }
        return 0;
    }

    @ModelMethod(name = "(this)的门店所在区县")
    public String getDistrict() {
        return this.proxyForm.getLocatedDistrict();
    }

    @ModelField(name = "(this)的门店类型 ",bindDomain = "engine.rule.domain.StoreType")
    public int storecategory;

    @ModelMethod(name = "(this)门店商品范围")
    public int getCommoditycategories() {
        return this.proxyForm.getCommoditycategories();
    }

    @ModelField(name = "(this)是否有水货", bindDomain = "engine.rule.domain.SmuggledGoodsType")
    public int smuggledGoods = SmuggledGoodsType.None.getValue();

    @ModelField(name = "(this)是否有二手货", bindDomain = "engine.rule.domain.SecondHandGoodsType")
    public int hasSecondHandGoods = SecondHandGoodsType.None.getValue();

    @ModelField(name = "(this)否有返修货", bindDomain = "engine.rule.domain.RepairedGoodsType")
    public int hasRepairedGoods = RepairedGoodsType.None.getValue();

    @ModelMethod(name = "(this)的做单至今的天数")
    public int getNumOfOpenday() {
        return this.proxyForm.GetNumOfOpenday();
    };
    //    @ModelMethod(name = "(this)的门店评级")
    //    public String getPosRank(){
    //        return this.proxyForm.GetPosRank();
    //    };

    public IStoreInfoInForm getProxyForm() {
        return proxyForm;
    }

    public void setProxyForm(IStoreInfoInForm proxyForm) {
        this.proxyForm = proxyForm;
    }

    public int getSmuggledGoods() {
        return this.proxyForm.getHasSmuggledGoods();
    }

    public int getHasSecondHandGoods() {
        return this.proxyForm.getHasSecondHandGoods();
    }

    public int getHasRepairedGoods() {
        return this.proxyForm.getHasRepairedGoods();
    }

    public int getStorecategory() {
        return this.proxyForm.getStorecategory();
    }

}
