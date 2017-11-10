package thirdparty.jxl.request;

import java.util.Map;

import thirdparty.config.JXLConfiguration;
import catfish.base.httpclient.object.BaseRequest;

import com.google.gson.annotations.SerializedName;

public abstract class JXLBaseRequest extends BaseRequest{

	protected abstract Map<String, Object> customizeGetParam();
	
	protected abstract Map<String, Object> customizePostParam();
		
	@SerializedName("org_name")
	private String orgName = JXLConfiguration.getJxlOrgName();
	
	@SerializedName("client_secret")
	private String clientSecret = JXLConfiguration.getJxlClientSecret();
	
	@SerializedName("access_token")
	private String accessToken = JXLConfiguration.getJxlAccessToken();


	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
