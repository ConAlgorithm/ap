package thirdparty.ylzh.request;

import java.util.Map;

import thirdparty.config.YLZHConfiguration;
import catfish.base.CollectionUtils;
import catfish.base.EncryptUtils;
import catfish.base.httpclient.annotation.Chinese;

import com.google.gson.annotations.SerializedName;

public class TradeReportRequest extends YLZHBaseRequest{
	
	@Chinese()
	@SerializedName("name")
	private String userName;
	
	@SerializedName("mobile")
	private String mobile;
	
	@SerializedName("cid")
	private String idCardNumber;
	
	@SerializedName("uid")
	private String userId;

	
	@Override
	protected Map<String, Object> customizeGetParam() {
		StringBuilder builder = new StringBuilder();
		builder.append("account").append(this.getAccount());
		builder.append("card").append(this.getCardNumber());
		builder.append(YLZHConfiguration.getYlzhPrivateKey());
		return CollectionUtils.mapOf("sign", (Object)EncryptUtils.md5Encode(builder.toString()));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
