package thirdparty.jxl.response;

import thirdparty.jxl.response.reportdata.ReportData;

import com.google.gson.annotations.SerializedName;

public class ReportDataResponse extends JXLBaseResponse{

	//finished!
	@SerializedName("note")
	private String note;
	
	@SerializedName("report_data")
	private ReportData reportData;

	//finished!
	@SerializedName("success")
	private boolean successFlag;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ReportData getReportData() {
		return reportData;
	}

	public void setReportData(ReportData reportData) {
		this.reportData = reportData;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
}
