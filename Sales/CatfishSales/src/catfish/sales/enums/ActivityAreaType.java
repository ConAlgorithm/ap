package catfish.sales.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum ActivityAreaType implements CatfishEnum<Integer> {

	@Description(text = "中国行政区域ID")
	CNAreaID(1);

	private int value;
	
	private ActivityAreaType(int value){
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

}
