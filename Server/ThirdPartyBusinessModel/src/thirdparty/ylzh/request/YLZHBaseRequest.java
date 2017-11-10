package thirdparty.ylzh.request;

import java.util.Map;

import thirdparty.config.YLZHConfiguration;
import catfish.base.httpclient.annotation.Necessary;
import catfish.base.httpclient.object.BaseRequest;

import com.google.gson.annotations.SerializedName;

public class YLZHBaseRequest extends BaseRequest{

	@Necessary()
	@SerializedName("account")
	private String account = YLZHConfiguration.getYlzhAccount();
	
	@Necessary()
	@SerializedName("sign")
	private String sign;
	
	@SerializedName("card")
	private String cardNumber;
	
	@Override
	protected Map<String, Object> customizeGetParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, Object> customizePostParam() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
