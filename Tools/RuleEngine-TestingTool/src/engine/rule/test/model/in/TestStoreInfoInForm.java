package engine.rule.test.model.in;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import engine.rule.model.BaseForm;
import engine.rule.model.in.app.IStoreInfoInForm;

public class TestStoreInfoInForm extends BaseForm implements IStoreInfoInForm {

	private String storeId;
	private String merchantId;
	private String locatedCity;
	private String locatedProvince;
	private String locatedDistrict;
	private String s1IdCardNumber;
	private int firstOverDue7DayCount;
	private int firstOverDueLessThan7DayCount;
	private int completedAppCount;
	private int completedAppCountByMonth;
	private int overDueCount;
	private BigDecimal statedRiskExposure;
	private BigDecimal factualRiskExposure;
	private int nDaysStatedQuantityLimitNum;
	private int nDaysFactualQuantityLimitNum;
	
	private Integer getOneDaysFactualQuantityLimitNum;
	private Integer getOneDaysStatedQuantityLimitNum;
	private Integer getThreeDaysFactualQuantityLimitNum;
	private Integer getThreeDaysStatedQuantityLimitNum;
	private Integer getSevenDaysFactualQuantityLimitNum;
	private Integer getSevenDaysStatedQuantityLimitNum;
	private Integer getFifteenDaysFactualQuantityLimitNum;
	private Integer getFifteenDaysStatedQuantityLimitNum;
	private Map<Integer, Integer> factualQuantityLimitNum = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> statedQuantityLimitNum = new HashMap<Integer, Integer>();
	
	private Integer storeCategory;
	private Integer commodityCategories;
	private int hasSmuggledGoods;
	private int hasSecondHandGoods;
	private int hasRepairedGoods;
	private int numOfOpenDay;
	
	
	public String getStoreId() {
		return storeId == null ? "" : storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getMerchantId() {
		return merchantId == null ? "" : merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getLocatedCity() {
		return locatedCity == null ? "" : locatedCity;
	}
	public void setLocatedCity(String locatedCity) {
		this.locatedCity = locatedCity;
	}
	public String getLocatedProvince() {
		return locatedProvince == null ? "" : locatedProvince;
	}
	public void setLocatedProvince(String locatedProvince) {
		this.locatedProvince = locatedProvince;
	}
	public String getS1IdCardNumber() {
		return s1IdCardNumber == null ? "" : s1IdCardNumber;
	}
	public void setS1IdCardNumber(String s1IdCardNumber) {
		this.s1IdCardNumber = s1IdCardNumber;
	}
	public int getFirstOverDue7DayCount() {
		return firstOverDue7DayCount;
	}
	public void setFirstOverDue7DayCount(int firstOverDue7DayCount) {
		this.firstOverDue7DayCount = firstOverDue7DayCount;
	}
	public int getFirstOverDueLessThan7DayCount() {
		return firstOverDueLessThan7DayCount;
	}
	public void setFirstOverDueLessThan7DayCount(int firstOverDueLessThan7DayCount) {
		this.firstOverDueLessThan7DayCount = firstOverDueLessThan7DayCount;
	}
	public int getCompletedAppCount() {
		return completedAppCount;
	}
	public void setCompletedAppCount(int completedAppCount) {
		this.completedAppCount = completedAppCount;
	}
	public int getCompletedAppCountByMonth() {
		return completedAppCountByMonth;
	}
	public void setCompletedAppCountByMonth(int completedAppCountByMonth) {
		this.completedAppCountByMonth = completedAppCountByMonth;
	}
	public int getOverDueCount() {
		return overDueCount;
	}
	public void setOverDueCount(int overDueCount) {
		this.overDueCount = overDueCount;
	}
	public BigDecimal getStatedRiskExposure() {
		return statedRiskExposure == null ? new BigDecimal(1000000) : statedRiskExposure;
	}
	public void setStatedRiskExposure(BigDecimal statedRiskExposure) {
		this.statedRiskExposure = statedRiskExposure;
	}
	public BigDecimal getFactualRiskExposure() {
		return factualRiskExposure  == null ? new BigDecimal(0) : factualRiskExposure;
	}
	public void setFactualRiskExposure(BigDecimal factualRiskExposure) {
		this.factualRiskExposure = factualRiskExposure;
	}
	public void setNDaysStatedQuantityLimitNum(int nDaysStatedQuantityLimitNum) {
		this.nDaysStatedQuantityLimitNum = nDaysStatedQuantityLimitNum;
	}
	public void setNDaysFactualQuantityLimitNum(int nDaysFactualQuantityLimitNum) {
		this.nDaysFactualQuantityLimitNum = nDaysFactualQuantityLimitNum;
	}
	public int getNDaysStatedQuantityLimitNum(int dayCount) {
		Integer result = this.statedQuantityLimitNum.get(dayCount);
		return result == null ? 10000 : result;
	}
	public int getNDaysFactualQuantityLimitNum(int dayCount) {
		Integer result = this.factualQuantityLimitNum.get(dayCount);
		return result == null ? 0 : result;
	}
	
	
	public Integer getGetOneDaysFactualQuantityLimitNum() {
		return getOneDaysFactualQuantityLimitNum;
	}
	public void setGetOneDaysFactualQuantityLimitNum(
			Integer getOneDaysFactualQuantityLimitNum) {
		this.getOneDaysFactualQuantityLimitNum = getOneDaysFactualQuantityLimitNum;
		this.factualQuantityLimitNum.put(1, this.getOneDaysFactualQuantityLimitNum);
	}
	public Integer getGetOneDaysStatedQuantityLimitNum() {
		return getOneDaysStatedQuantityLimitNum;
	}
	public void setGetOneDaysStatedQuantityLimitNum(
			Integer getOneDaysStatedQuantityLimitNum) {
		this.getOneDaysStatedQuantityLimitNum = getOneDaysStatedQuantityLimitNum;
		this.statedQuantityLimitNum.put(1, this.getOneDaysStatedQuantityLimitNum);
	}
	public Integer getGetThreeDaysFactualQuantityLimitNum() {
		return getThreeDaysFactualQuantityLimitNum;
	}
	public void setGetThreeDaysFactualQuantityLimitNum(
			Integer getThreeDaysFactualQuantityLimitNum) {
		this.getThreeDaysFactualQuantityLimitNum = getThreeDaysFactualQuantityLimitNum;
		this.factualQuantityLimitNum.put(3, this.getThreeDaysFactualQuantityLimitNum);
	}
	public Integer getGetThreeDaysStatedQuantityLimitNum() {
		return getThreeDaysStatedQuantityLimitNum;
	}
	public void setGetThreeDaysStatedQuantityLimitNum(
			Integer getThreeDaysStatedQuantityLimitNum) {
		this.getThreeDaysStatedQuantityLimitNum = getThreeDaysStatedQuantityLimitNum;
		this.statedQuantityLimitNum.put(3, this.getThreeDaysStatedQuantityLimitNum);
	}
	public Integer getGetSevenDaysFactualQuantityLimitNum() {
		return getSevenDaysFactualQuantityLimitNum;
	}
	public void setGetSevenDaysFactualQuantityLimitNum(
			Integer getSevenDaysFactualQuantityLimitNum) {
		this.getSevenDaysFactualQuantityLimitNum = getSevenDaysFactualQuantityLimitNum;
		this.factualQuantityLimitNum.put(7, this.getSevenDaysFactualQuantityLimitNum);
	}
	public Integer getGetSevenDaysStatedQuantityLimitNum() {
		return getSevenDaysStatedQuantityLimitNum;
	}
	public void setGetSevenDaysStatedQuantityLimitNum(
			Integer getSevenDaysStatedQuantityLimitNum) {
		this.getSevenDaysStatedQuantityLimitNum = getSevenDaysStatedQuantityLimitNum;
		this.statedQuantityLimitNum.put(7, this.getSevenDaysStatedQuantityLimitNum);
	}
	public Integer getGetFifteenDaysFactualQuantityLimitNum() {
		return getFifteenDaysFactualQuantityLimitNum;
	}
	public void setGetFifteenDaysFactualQuantityLimitNum(
			Integer getFifteenDaysFactualQuantityLimitNum) {
		this.getFifteenDaysFactualQuantityLimitNum = getFifteenDaysFactualQuantityLimitNum;
		this.factualQuantityLimitNum.put(15, this.getFifteenDaysFactualQuantityLimitNum);
	}
	public Integer getGetFifteenDaysStatedQuantityLimitNum() {
		return getFifteenDaysStatedQuantityLimitNum;
	}
	public void setGetFifteenDaysStatedQuantityLimitNum(
			Integer getFifteenDaysStatedQuantityLimitNum) {
		this.getFifteenDaysStatedQuantityLimitNum = getFifteenDaysStatedQuantityLimitNum;
		this.statedQuantityLimitNum.put(15, this.getFifteenDaysStatedQuantityLimitNum);
	}
    @Override
    public String getLocatedDistrict() {
        return  locatedDistrict == null ? "" : locatedDistrict;
    }
    public void setLocatedDistrict(String locatedDistrict) {
        this.locatedDistrict = locatedDistrict;
    }
    @Override
    public Integer getStorecategory() {
        return storeCategory == null ? 0 : storeCategory;
    }
    @Override
    public Integer getCommoditycategories() {
        return commodityCategories == null ? 0 : commodityCategories;
    }
    @Override
    public int getHasSmuggledGoods() {
        return hasSmuggledGoods;
    }
    @Override
    public int getHasSecondHandGoods() {
        return hasSecondHandGoods;
    }
    @Override
    public int getHasRepairedGoods() {
        return hasRepairedGoods;
    }
    @Override
    public int GetNumOfOpenday() {
        return numOfOpenDay;
    }
    public int getnDaysStatedQuantityLimitNum() {
        return nDaysStatedQuantityLimitNum;
    }
    public void setnDaysStatedQuantityLimitNum(int nDaysStatedQuantityLimitNum) {
        this.nDaysStatedQuantityLimitNum = nDaysStatedQuantityLimitNum;
    }
    public int getnDaysFactualQuantityLimitNum() {
        return nDaysFactualQuantityLimitNum;
    }
    public void setnDaysFactualQuantityLimitNum(int nDaysFactualQuantityLimitNum) {
        this.nDaysFactualQuantityLimitNum = nDaysFactualQuantityLimitNum;
    }
    public Map<Integer, Integer> getFactualQuantityLimitNum() {
        return factualQuantityLimitNum;
    }
    public void setFactualQuantityLimitNum(Map<Integer, Integer> factualQuantityLimitNum) {
        this.factualQuantityLimitNum = factualQuantityLimitNum;
    }
    public Map<Integer, Integer> getStatedQuantityLimitNum() {
        return statedQuantityLimitNum;
    }
    public void setStatedQuantityLimitNum(Map<Integer, Integer> statedQuantityLimitNum) {
        this.statedQuantityLimitNum = statedQuantityLimitNum;
    }
    public Integer getStoreCategory() {
        return storeCategory;
    }
    public void setStoreCategory(Integer storeCategory) {
        this.storeCategory = storeCategory;
    }
    public Integer getCommodityCategories() {
        return commodityCategories;
    }
    public void setCommodityCategories(Integer commodityCategories) {
        this.commodityCategories = commodityCategories;
    }
    public int getNumOfOpenDay() {
        return numOfOpenDay;
    }
    public void setNumOfOpenDay(int numOfOpenDay) {
        this.numOfOpenDay = numOfOpenDay;
    }
    public void setHasSmuggledGoods(int hasSmuggledGoods) {
        this.hasSmuggledGoods = hasSmuggledGoods;
    }
    public void setHasSecondHandGoods(int hasSecondHandGoods) {
        this.hasSecondHandGoods = hasSecondHandGoods;
    }
    public void setHasRepairedGoods(int hasRepairedGoods) {
        this.hasRepairedGoods = hasRepairedGoods;
    }	
    
    
}
