package thirdparty.ylzh.request;

import java.util.Map;

import thirdparty.config.YLZHConfiguration;
import catfish.base.CollectionUtils;
import catfish.base.EncryptUtils;
import catfish.base.httpclient.annotation.Chinese;
import catfish.base.httpclient.annotation.Necessary;

import com.google.gson.annotations.SerializedName;

public class CorrespondingRelationVerifyRequest extends YLZHBaseRequest{

	
	
	@Chinese()
	@SerializedName("name")
	private String userName;
	
	@SerializedName("mobile")
	private String mobile;
	
	@SerializedName("cid")
	private String idCardNumber;
	
	@Necessary()
	@SerializedName("type")
	private int type;
	
	@Override
	protected Map<String, Object> customizeGetParam() {
		StringBuilder builder = new StringBuilder();
		builder.append("account").append(this.getAccount());
		builder.append("card").append(this.getCardNumber());
		builder.append("mobile").append(this.getMobile());
		builder.append("type").append(this.getType());
		builder.append(YLZHConfiguration.getYlzhPrivateKey());
		System.out.println(builder.toString());
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
