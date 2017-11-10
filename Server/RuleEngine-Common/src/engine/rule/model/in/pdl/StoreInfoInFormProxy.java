package engine.rule.model.in.pdl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.object.MerchantStoreRiskControlObject;
import catfish.base.business.object.MerchantUserObject;
import catfish.base.business.object.context.MerchantStoreData;

import com.huateng.toprules.core.annotation.ModelMethod;

import engine.rule.model.BaseForm;

public class StoreInfoInFormProxy extends BaseForm implements IStoreInfoInForm {

	// 门店上下文信息
	private MerchantStoreData storeData;

	// 申请列表
	private List<InstallmentApplicationObject> appList;

	// 门店风险控制对象
	private MerchantStoreRiskControlObject riskObj;

	// 门店S1对象
	private MerchantUserObject s1Obj;

	@ModelMethod(name = "(this)的门店号")
	public String getStoreId() {
		return (storeData.storeObj.Code == null ? "" : storeData.storeObj.Code);
	}

	@ModelMethod(name = "(this)的商户号")
	public String getMerchantId() {
		return (storeData.storeObj.MerchantCompanyId == null ? ""
				: storeData.storeObj.MerchantCompanyId);
	}

	 
	@ModelMethod(name = "(this)的门店所在城市")
	public String getLocatedCity() {
		return (storeData.locatedCity == null ? "" : storeData.locatedCity);
	}
	
	@ModelMethod(name = "(this)的门店所在省份")
	public String getLocatedProvince() {
		return (storeData.locatedProvince == null ? "" : storeData.locatedProvince);
	}

	@ModelMethod(name = "(this)的S1身份证号")
	public String getS1IdCardNumber() {
		return (s1Obj == null ? "" : s1Obj.IdNumber);
	}

	@ModelMethod(name = "(this)的首逾超过7天用户数量")
	public int getFirstOverDue7DayCount() {
		return storeData.overDueNdaysUserCount;
	}

	@ModelMethod(name = "(this)的首逾未超过7天用户数量")
	public int getFirstOverDueLessThan7DayCount() {
		return storeData.notOverDueNdaysUserCount;
	}

	@ModelMethod(name = "(this)的完成单数")
	public int getCompletedAppCount() {
		return storeData.completedApplicationCount;
	}

	@ModelMethod(name = "(this)的一个月内完成单数")
	public int getCompletedAppCountByMonth() {
		return storeData.completedApplicationCountByMonth;
	}

	@ModelMethod(name = "(this)的逾期单数")
	public int getOverDueCount() {
		return storeData.overDueCount;
	}

	@ModelMethod(name = "(this)的预设当月放款总额")
	public BigDecimal getStatedRiskExposure() {
		boolean flag = (riskObj == null || riskObj.RiskExposure == null);
		// 如果没有获取到，默认为10000000，表示没有上限
		return (flag ? new BigDecimal(10000000) : riskObj.RiskExposure);
	}

	@ModelMethod(name = "(this)的实际当月放款总额")
	public BigDecimal getFactualRiskExposure() {
		Calendar cal = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		BigDecimal sum = new BigDecimal(0);
		for (InstallmentApplicationObject obj : appList) {
			cal.setTime(obj.InstallmentStartedOn);
			if (cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)
					&& cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
				sum.add(obj.Principal);
			}
		}
		return sum;
	}

	@ModelMethod(name = "(this)的预设(#1,<天数>)天内进件量", paramDomains = "engine.rule.domain.QuantityLimitDays")
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

	@ModelMethod(name = "(this)的实际(#1,<天数>)天内进件量")
	public int getNDaysFactualQuantityLimitNum(int dayCount) {
		int count = 0;
		LocalDate time = DateTime.now().plusDays(1 - dayCount).toLocalDate();
		for (InstallmentApplicationObject obj : appList) {
			if (new DateTime(obj.InstallmentStartedOn).toLocalDate().compareTo(
					time) >= 0)
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

	public List<InstallmentApplicationObject> getAppList() {
		return appList;
	}

	public void setAppList(List<InstallmentApplicationObject> appList) {
		this.appList = appList;
	}

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

}
