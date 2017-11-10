package thirdparty.ylzh.request.cardownerverify;

import java.util.Map;

import thirdparty.config.YLZHConfiguration;
import thirdparty.ylzh.request.YLZHBaseRequest;
import catfish.base.CollectionUtils;
import catfish.base.EncryptUtils;
import catfish.base.httpclient.annotation.Chinese;
import catfish.base.httpclient.annotation.Necessary;

import com.google.gson.annotations.SerializedName;

public class CardOwnerIdentityVerifyRequest extends YLZHBaseRequest{

	//认证方式
	@Necessary()
	@SerializedName("authType")
	private int authType;
	
	//接入方自定义的用户Id
	@Necessary()
	@SerializedName("userId")
	private String userId;
	
	//银行卡识别码
	@SerializedName("cardCode")
	private String cardCode;
	
	//持卡人姓名
	@Chinese()
	@Necessary()
	@SerializedName("name")
	private String cardOwnerName;
	
	//预留手机号码
	@SerializedName("mobile")
	private String mobile;
	
	//开户所用身份证号码
	@SerializedName("cid")
	private String idCardNumber;
	
	@SerializedName("email")
	private String email;
	
	//手机串号
	@SerializedName("imei")
	private String imei;

	
	@Override
	protected Map<String, Object> customizeGetParam() {
		StringBuilder builder = new StringBuilder();
		builder.append("account").append(this.getAccount());
		builder.append("authType").append(this.getAuthType());
		builder.append("card").append(this.getCardNumber());
		builder.append("name").append(this.getCardOwnerName());
		builder.append("userId").append(this.getUserId());
		builder.append(YLZHConfiguration.getYlzhPrivateKey());
		System.out.println(builder.toString());
		return CollectionUtils.mapOf("sign", (Object)EncryptUtils.md5Encode(builder.toString()));
	}

	public int getAuthType() {
		return authType;
	}

	public void setAuthType(int authType) {
		this.authType = authType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardOwnerName() {
		return cardOwnerName;
	}

	public void setCardOwnerName(String cardOwnerName) {
		this.cardOwnerName = cardOwnerName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
}
