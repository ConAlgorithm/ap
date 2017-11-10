package thirdparty.ylzh.response.cardownerverify;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import thirdparty.ylzh.response.YLZHBaseResponse;

public class CardOwnerIdentityVerifyResponse extends YLZHBaseResponse{

	@SerializedName("account")
	private String account;
	
	@SerializedName("stat")
	private String verifyState;
	
	@SerializedName("statDesc")
	private String verifyStatDesc;
	
	@SerializedName("cardCode")
	private String cardCode;
	
	@SerializedName("trans")
	private List<String> transDetail;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}

	public String getVerifyStatDesc() {
		return verifyStatDesc;
	}

	public void setVerifyStatDesc(String verifyStatDesc) {
		this.verifyStatDesc = verifyStatDesc;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public List<String> getTransDetail() {
		return transDetail;
	}

	public void setTransDetail(List<String> transDetail) {
		this.transDetail = transDetail;
	}
}
