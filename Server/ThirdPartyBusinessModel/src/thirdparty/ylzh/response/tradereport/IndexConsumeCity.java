package thirdparty.ylzh.response.tradereport;

import com.google.gson.annotations.SerializedName;

public class IndexConsumeCity {

	@SerializedName("name")
	private String name;
	
	//消费金额
	@SerializedName("amount")
	private Double consumeAmount;
	
	//消费总笔数
	@SerializedName("count")
	private Integer consumeCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(Double consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public Integer getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(Integer consumeCount) {
		this.consumeCount = consumeCount;
	}
}
