package engine.rule.model.in.pdl;

import java.math.BigDecimal;

import catfish.base.Logger;

import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.rule.model.BaseForm;

@ModelInstance(description = "门店评级材料")
public class StoreInfoInForm extends BaseForm {

	private IStoreInfoInForm proxyForm = null;
	
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
			Logger.get().error(String.format("get stated daycount: %d from proxy error.",dayCount), e);
		}
		return 0;
	}

	@ModelMethod(name = "(this)的实际(#1,<天数>)天内进件量")
	public int getNDaysFactualQuantityLimitNum(int dayCount) {
		try {

			return this.proxyForm.getNDaysFactualQuantityLimitNum(dayCount);
		} catch (Exception e) {
			Logger.get().error(String.format("get factual daycount: %d from proxy error.",dayCount), e);
		}
		return 0;
	}

	public IStoreInfoInForm getProxyForm() {
		return proxyForm;
	}

	public void setProxyForm(IStoreInfoInForm proxyForm) {
		this.proxyForm = proxyForm;
	}

//	public MerchantStoreData getStoreData() {
//		return storeData;
//	}
//
//	public void setStoreData(MerchantStoreData storeData) {
//		this.storeData = storeData;
//	}
//
//	public List<InstallmentApplicationObject> getAppList() {
//		return appList;
//	}
//
//	public void setAppList(List<InstallmentApplicationObject> appList) {
//		this.appList = appList;
//	}
//
//	public MerchantStoreRiskControlObject getRiskObj() {
//		return riskObj;
//	}
//
//	public void setRiskObj(MerchantStoreRiskControlObject riskObj) {
//		this.riskObj = riskObj;
//	}
//
//	public MerchantUserObject getS1Obj() {
//		return s1Obj;
//	}
//
//	public void setS1Obj(MerchantUserObject s1Obj) {
//		this.s1Obj = s1Obj;
//	}

}
