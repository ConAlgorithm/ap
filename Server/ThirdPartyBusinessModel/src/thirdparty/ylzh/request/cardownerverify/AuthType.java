package thirdparty.ylzh.request.cardownerverify;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum AuthType implements CatfishEnum<Integer>{

	@Description(text = "短信认证")
	ShortMsg(1),
	
	@Description(text = "历史交易")
	HistoryTrade(2),
	
	@Description(text = "未来交易")
	FutureTrade(3),
	
	@Description(text = "小额打款")
	SmallAmountPaid(4);
	
	private int value;

   private AuthType(int value)
   {
       this.value = value;
   }

   @Override
   public Integer getValue() {
       return this.value;
   }
}
