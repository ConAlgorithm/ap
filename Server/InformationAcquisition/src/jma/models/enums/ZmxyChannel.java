package jma.models.enums;

/**
 * 渠道类型,每个授权码支持不同的渠道类型
 * 
 * @author yeyb
 * @date 2017年8月28日
 */
public enum ZmxyChannel {
	// sdk接入
	appsdk("appsdk"),
	// 商户pc页面接入
	apppc("apppc"),
	// 后台api接入
	api("api"),
	// 商户app接入
	app("app");

	private String value;

	ZmxyChannel(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
