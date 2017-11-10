package catfish.sales.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum MerchantUserRole implements CatfishEnum<Integer> {
	
	@Description(text ="无")
    NoRole(0),
    //S1
    @Description(text ="S1")
    S1(1 << 0),

    //S2
    @Description(text ="S2")
    S2(1 << 1),

    //S3
    @Description(text ="S3")
    S3(1 << 2),

    //D3
    @Description(text ="D3")
    D3(1 << 3),

    //D1
    @Description(text ="D1")
    D1(1 << 4),

    //D2
    @Description(text ="D2")
    D2(1 << 5),

    //D4
    @Description(text ="D4")
    D4(1 << 10),

    //BD1
    @Description(text ="BD1")
    BD1(1 << 6),

    //BD2
    @Description(text ="BD2")
    BD2(1 << 7),

    //BSSD3
    @Description(text ="BD3")
    BD3(1 << 8),

    //D3
    @Description(text ="BD4")
    BD4(1 << 9),
	
	@Description(text = "F1")
	F1(1 << 20),
	
	@Description(text = "F2")
	F2(1 << 21),
	
	@Description(text = "F3")
	F3(1 << 22),
	
	@Description(text = "Root")
	Root(1<<30);
	
	private int value;
	
	private MerchantUserRole(int value){
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

}
