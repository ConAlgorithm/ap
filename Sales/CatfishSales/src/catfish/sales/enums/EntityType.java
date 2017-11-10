package catfish.sales.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum EntityType implements CatfishEnum<Integer> {
	@Description(text = "无")
	None(0),
	
	@Description(text = "seller")
	Seller(10),
	
	@Description(text = "POS")
	POS(20),
	
	@Description(text = "BDOrg")
	BDOrg(30),
	
	@Description(text = "DOrg")
	DOrg(40),
	
	@Description(text = "Factory")
	Factory(50),
	
	@Description(text = "FOrg")
	FOrg(60);

	private int value;
	
	private EntityType(int value){
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

}
