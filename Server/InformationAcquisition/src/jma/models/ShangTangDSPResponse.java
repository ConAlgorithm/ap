package jma.models;

public class ShangTangDSPResponse {
	//置信度
	private Double confidence;
	//身份证和姓名经过公安接口验证是否匹配
	private Boolean validity;
	//公安接口出错原因
	private String reason;
	public Double getConfidence() {
		return confidence;
	}
	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}
	public Boolean getValidity() {
		return validity;
	}
	public void setValidity(Boolean validity) {
		this.validity = validity;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
