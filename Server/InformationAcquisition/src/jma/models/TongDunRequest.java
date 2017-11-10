package jma.models;

public class TongDunRequest {
	private String name;

	private String idNo;

	private String mobile;
	  public TongDunRequest(){
		  
	  }
	public TongDunRequest(String name, String idNo, String mobile) {		
		this.name = name;
		this.idNo = idNo;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	  
	  
}
