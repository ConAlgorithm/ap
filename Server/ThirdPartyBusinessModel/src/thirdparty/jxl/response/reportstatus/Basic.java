package thirdparty.jxl.response.reportstatus;

import com.google.gson.annotations.SerializedName;

public class Basic {

	@SerializedName("phone")
	private String phone;
	
	@SerializedName("idcard")
	private String idCardNumber;
	
	//eg: 张三
	@SerializedName("name")
	private String name;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}
