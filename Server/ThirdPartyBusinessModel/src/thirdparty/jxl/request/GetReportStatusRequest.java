package thirdparty.jxl.request;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

//finished
public class GetReportStatusRequest extends JXLBaseRequest{

	@SerializedName("idcard")
	private String idCardNumber;
	
	@catfish.base.httpclient.annotation.Chinese()
	@SerializedName("name")
	private String name;
	
	@SerializedName("phone")
	private String phone;
	
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

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
