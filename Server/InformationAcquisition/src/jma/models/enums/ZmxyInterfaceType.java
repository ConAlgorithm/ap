package jma.models.enums;

/**
 * 芝麻信用接口类型
 * @author yeyb
 * @date 2017年8月28日
 */
public enum ZmxyInterfaceType {
	// 芝麻信用评分
	CreditScore("CreditScore"),
	// 行业关注名单
	CreditWatchlist("CreditWatchlist"),
	// 反欺诈信息验证
	CreditIvsDetail("CreditIvsDetail"),
	// 加密加签
	SignAndEncrypt("SignAndEncrypt"),
	//回调参数解密
	CallBackDecrypt("CallBackDecrypt"),
	//获取授权的URL地址
	GeneratePageAuth("GeneratePageAuth");

	private String value;

	ZmxyInterfaceType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
