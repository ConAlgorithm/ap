package thirdparty.jxl.response.reportstatus;

import com.google.gson.annotations.SerializedName;

public class Member {

	//eg: fenqi
	@SerializedName("Company")
	private String company;
	
	@SerializedName("reportNumber")
	private String reportNumber;
	
	@SerializedName("basic")
	private Basic basic;

	@SerializedName("status")
	private String status;
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public Basic getBasic() {
		return basic;
	}

	public void setBasic(Basic basic) {
		this.basic = basic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
