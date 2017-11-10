package thirdparty.reg007;

import java.util.List;

public class ResponseData {
	public class RegisterWebInfo {
		
		private String desc;
		
		private String name;
		
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	private List<RegisterWebInfo> data;
	
	private String status;
	
	private String info;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<RegisterWebInfo> getData() {
		return data;
	}

	public void setData(List<RegisterWebInfo> data) {
		this.data = data;
	}
}


