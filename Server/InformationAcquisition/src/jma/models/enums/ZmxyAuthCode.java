package jma.models.enums;

/**
 * 芝麻积分授权码
 * @author yeyb
 * @date 2017年8月28日
 */
public enum ZmxyAuthCode {

	PC("M_APPPC_CERT"),

	H5("M_H5"),

	SDK("M_APPSDK");

	private String value;

	ZmxyAuthCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
