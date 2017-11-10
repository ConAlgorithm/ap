package thirdparty.jxl.response.reportdata;

import com.google.gson.annotations.SerializedName;

public class BehaviorItem {

	/*
	 *  手机号码	cell_phone_num	string	手机号码
		手机运营商	cell_operator	string	手机运营商
		手机归属地	cell_loc	string	手机归属地
		月份	cell_mth	string	月份
		月被叫通话时间	call_in_time	double	月被叫通话时间
		月主叫通话时间	call_out_time	double	月主叫通话时间
		月短信条数	sms_cnt	int	月短信条数
		月流量	net_flow	int	月流量
	*/
	
	@SerializedName("cell_phone_num")
	private String cellPhoneNumber;
	
	@SerializedName("cell_operator")
	private String cellOperator;
	
	@SerializedName("cell_loc")
	private String cellLocation;
	
	@SerializedName("cell_mth")
	private String cellMonth;
	
	@SerializedName("call_in_time")
	private double callinTime;
	
	@SerializedName("call_out_time")
	private double calloutTime;
	
	@SerializedName("sms_cnt")
	private int smsCount;
	
	@SerializedName("net_flow")
	private double netFlow;

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getCellOperator() {
		return cellOperator;
	}

	public void setCellOperator(String cellOperator) {
		this.cellOperator = cellOperator;
	}

	public String getCellLocation() {
		return cellLocation;
	}

	public void setCellLocation(String cellLocation) {
		this.cellLocation = cellLocation;
	}

	public String getCellMonth() {
		return cellMonth;
	}

	public void setCellMonth(String cellMonth) {
		this.cellMonth = cellMonth;
	}

	public double getCallinTime() {
		return callinTime;
	}

	public void setCallinTime(double callinTime) {
		this.callinTime = callinTime;
	}

	public double getCalloutTime() {
		return calloutTime;
	}

	public void setCalloutTime(double calloutTime) {
		this.calloutTime = calloutTime;
	}

	public int getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}

	public double getNetFlow() {
		return netFlow;
	}

	public void setNetFlow(double netFlow) {
		this.netFlow = netFlow;
	}
	
	
}
