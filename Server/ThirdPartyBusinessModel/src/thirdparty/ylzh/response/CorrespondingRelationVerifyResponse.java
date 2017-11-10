package thirdparty.ylzh.response;

import com.google.gson.annotations.SerializedName;

public class CorrespondingRelationVerifyResponse extends YLZHBaseResponse{
	
	@SerializedName("stat")
	private String verifyState;	

	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}
}
