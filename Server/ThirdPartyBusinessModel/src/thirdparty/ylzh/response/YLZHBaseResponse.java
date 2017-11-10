package thirdparty.ylzh.response;

import com.google.gson.annotations.SerializedName;

import catfish.base.httpclient.object.BaseResponse;

public class YLZHBaseResponse extends BaseResponse{

	@SerializedName("resCode")
	private String resCode;
	
	@SerializedName("resMsg")
	private String resMsg;
	
	@SerializedName("sign")
	private String sign;
	
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
