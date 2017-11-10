package thirdparty.ylzh.response;

import thirdparty.ylzh.response.tradereport.Data;

import com.google.gson.annotations.SerializedName;

public class TradeReportResponse extends YLZHBaseResponse{

	@SerializedName("data")
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
