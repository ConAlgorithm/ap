package engine.rule.domain;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum MobileThree implements CatfishEnum<Integer> {
	@Description(text = "无法验证（数尊专有）")
	NoneRecord(-1),

	@Description(text = "三要素验证一致")
	VerifyApprove(0),

	@Description(text = "三要素验证不一致")
	VerifyNotApprove(1),

	@Description(text = "手机号身份证号验证一致；手机号姓名验证不一致")
	VerifyNotApproveName(2),

	@Description(text = "手机号身份证号验证不一致；手机号姓名验证一致（集奥专有）")
	VerifyNotApproveNameId(3);

	private int value;

	private MobileThree(int value) {
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
}
