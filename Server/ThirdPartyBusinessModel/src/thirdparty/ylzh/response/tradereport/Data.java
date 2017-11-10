package thirdparty.ylzh.response.tradereport;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Data {

	//还贷能力区间，单位元，统计周期为12个月的数据，eg:4000,6000
	@SerializedName("repaymentAbility")
	private String repaymentAbility;
	
	//资产指标，统计周期为12个月数据
	@SerializedName("indexProperty")
	private IndexProperty indexProperty;
	
	//交易行为特征，统计时间为12个月的数据
	@SerializedName("indexTransBehavior")
	private IndexTransBehavior indexTransBehavior;
	
	//每月消费状况，统计周期为12个月的数据
	@SerializedName("indexMonthConsumes")
	private  List<IndexMonthConsume> indexMonthConsumeList;
	
	//消费地域分布，统计周期为12个月数据
	@SerializedName("indexConsumeCities")
	private List<IndexConsumeCity> indexConsumeCityList;
	
	//消费大类分布,统计周期为12个月数据
	@SerializedName("indexConsumeCategories")
	private List<IndexConsumeCategory> indexConsumeCotegoryList;
	
	//信用相关交易统计，统计周期为12个月数据
	@SerializedName("indexTransCredits")
	private List<IndexTransCredit> indexTransCreditList;

	public String getRepaymentAbility() {
		return repaymentAbility;
	}

	public void setRepaymentAbility(String repaymentAbility) {
		this.repaymentAbility = repaymentAbility;
	}

	public IndexProperty getIndexProperty() {
		return indexProperty;
	}

	public void setIndexProperty(IndexProperty indexProperty) {
		this.indexProperty = indexProperty;
	}

	public IndexTransBehavior getIndexTransBehavior() {
		return indexTransBehavior;
	}

	public void setIndexTransBehavior(IndexTransBehavior indexTransBehavior) {
		this.indexTransBehavior = indexTransBehavior;
	}

	public List<IndexMonthConsume> getIndexMonthConsumeList() {
		return indexMonthConsumeList;
	}

	public void setIndexMonthConsumeList(
			List<IndexMonthConsume> indexMonthConsumeList) {
		this.indexMonthConsumeList = indexMonthConsumeList;
	}

	public List<IndexConsumeCity> getIndexConsumeCityList() {
		return indexConsumeCityList;
	}

	public void setIndexConsumeCityList(List<IndexConsumeCity> indexConsumeCityList) {
		this.indexConsumeCityList = indexConsumeCityList;
	}

	public List<IndexConsumeCategory> getIndexConsumeCotegoryList() {
		return indexConsumeCotegoryList;
	}

	public void setIndexConsumeCotegoryList(
			List<IndexConsumeCategory> indexConsumeCotegoryList) {
		this.indexConsumeCotegoryList = indexConsumeCotegoryList;
	}

	public List<IndexTransCredit> getIndexTransCreditList() {
		return indexTransCreditList;
	}

	public void setIndexTransCreditList(List<IndexTransCredit> indexTransCreditList) {
		this.indexTransCreditList = indexTransCreditList;
	}
}
