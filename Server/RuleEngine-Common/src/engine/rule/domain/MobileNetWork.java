package engine.rule.domain;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum MobileNetWork implements CatfishEnum<Integer> {
	@Description(text = "数据不存在")
	NoneRecord(-1),

	@Description(text = "（0,3]")
	Less3Month(1),

	@Description(text = "(3,6]")
	Less6Month(2),

	@Description(text = "(6,12]")
	Less1Year(3),

	@Description(text = "(12,24]")
	Less2Year(4),

	@Description(text = "(24,+)")
	Less3Year(5);

	private int value;

	private MobileNetWork(int value) {
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
}
