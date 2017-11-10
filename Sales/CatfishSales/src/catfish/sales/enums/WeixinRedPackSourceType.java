package catfish.sales.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum WeixinRedPackSourceType implements CatfishEnum<Integer> {
	@Description(text = "做单成功")
	ApplicationComplete(1),
	
	@Description(text = "排行奖励")
	RankReward(2);

	private int value;
	
	private WeixinRedPackSourceType(int value){
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

}
