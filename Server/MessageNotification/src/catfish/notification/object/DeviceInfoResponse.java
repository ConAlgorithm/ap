package catfish.notification.object;

import java.util.ArrayList;

public class DeviceInfoResponse {
	public int code;
	public String msg;
	public ArrayList<DeviceInfo> data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<DeviceInfo> getData() {
		return data;
	}
	public void setData(ArrayList<DeviceInfo> data) {
		this.data = data;
	}
	
	
}
