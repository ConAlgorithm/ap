package catfish.sales.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum MerchantUserTransactionType implements CatfishEnum<Integer> {
	@Description(text = "转入")
	TransferIn(90),
	
	@Description(text = "提现")
	WithDraw(700);

	private int value;
	
	private MerchantUserTransactionType(int value){
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

}
