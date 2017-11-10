package jma.models;

public class FuShuBlackResponseModel {

	private String return_code;

	private FuShuBlackListInfo data;

	private String msg;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public FuShuBlackListInfo getData() {
		return data;
	}

	public void setData(FuShuBlackListInfo data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
