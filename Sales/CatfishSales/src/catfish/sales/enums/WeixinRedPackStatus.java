package catfish.sales.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum WeixinRedPackStatus implements CatfishEnum<Integer> {
	@Description(text = "创建")
	Created(0),
	
	@Description(text = "发送成功")
	SentSuccess(10),
	
	@Description(text = "发送失败")
	SentFailed(11);

	private int value;
	
	private WeixinRedPackStatus(int value){
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

}
