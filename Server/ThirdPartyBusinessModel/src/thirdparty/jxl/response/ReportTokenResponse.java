package thirdparty.jxl.response;

import com.google.gson.annotations.SerializedName;

//finished
public class ReportTokenResponse extends JXLBaseResponse{
	
	@SerializedName("expires_in")
	private String expiresIn;
	
	@SerializedName("success")
	private boolean successFlag;
	
	@SerializedName("access_token")
	private String accessToken;
	
	@SerializedName("note")
	private String note;

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
