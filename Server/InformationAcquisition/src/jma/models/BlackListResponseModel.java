package jma.models;

import java.util.List;

public class BlackListResponseModel {
	private String result;
	private String message;
	private List<BlacklistDetailList> blacklistDetailList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BlacklistDetailList> getBlacklistDetailList() {
		return blacklistDetailList;
	}

	public void setBlacklistDetailList(List<BlacklistDetailList> blacklistDetailList) {
		this.blacklistDetailList = blacklistDetailList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlackListResponseModel [result=");
		builder.append(result);
		builder.append(", message=");
		builder.append(message);
		builder.append(", blacklistDetailList=");
		builder.append(blacklistDetailList);
		builder.append("]");
		return builder.toString();
	}

}
