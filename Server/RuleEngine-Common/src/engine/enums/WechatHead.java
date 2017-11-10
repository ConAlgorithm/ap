package engine.enums;

import catfish.base.Description;
import catfish.base.business.common.CatfishEnum;

public enum  WechatHead implements CatfishEnum<String>{
	
	@Description(text = "无")
	none("none"),

    @Description(text = "无数据")
	NoData("NoData"),

    @Description(text = "有")
    exist("exist");
	
	private String value;
	WechatHead(String value)
    {
        this.value = value;
    }	
	@Override
	public String getValue() {
		return value;
	}
}
