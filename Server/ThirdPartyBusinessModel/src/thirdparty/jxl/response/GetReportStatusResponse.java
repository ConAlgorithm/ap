package thirdparty.jxl.response;


import thirdparty.jxl.response.reportstatus.ParserInfo;

import com.google.gson.annotations.SerializedName;

public class GetReportStatusResponse extends JXLBaseResponse{

	@SerializedName("note")
	private String note;
	
	@SerializedName("success")
	private boolean successFlag;
	
	@SerializedName("paserInfo")
	private ParserInfo parserInfo;

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public ParserInfo getParserInfo() {
		return parserInfo;
	}

	public void setParserInfo(ParserInfo parserInfo) {
		this.parserInfo = parserInfo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
