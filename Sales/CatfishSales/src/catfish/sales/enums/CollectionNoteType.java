package catfish.sales.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum CollectionNoteType implements CatfishEnum<Integer> {
	@Description(text = "记录")
	Record(1),
	
	@Description(text = "退案")
	Withdraw(2);

	private int value;
	
	private CollectionNoteType(int value){
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

}
