package thirdparty.jxl.request;

import java.util.Map;

import thirdparty.config.JXLConfiguration;

import com.google.gson.annotations.SerializedName;

//finished
public class ReportTokenRequest extends JXLBaseRequest{

	@SerializedName("hours")
	private String hours = JXLConfiguration.getJxlExpireHours();

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

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}
}
