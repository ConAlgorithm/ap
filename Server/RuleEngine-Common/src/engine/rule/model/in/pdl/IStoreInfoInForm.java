package engine.rule.model.in.pdl;

import java.math.BigDecimal;

public interface IStoreInfoInForm {

	public String getStoreId();

	public String getMerchantId();

	public String getLocatedCity();
	
	public String getLocatedProvince();

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
}
