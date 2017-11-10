package engine.main;

import com.google.gson.Gson;

import catfish.base.queue.QueueMessager;

public class QueueMessagerExtensionForCL extends QueueMessager {

	private Boolean needCreditCheck;
	private Double creditCheckPercent;

	public Boolean getNeedCreditCheck() {
		return needCreditCheck;
	}

	public void setNeedCreditCheck(Boolean needCreditCheck) {
		this.needCreditCheck = needCreditCheck;
	}

	public Double getCreditCheckPercent() {
		return creditCheckPercent;
	}

	public void setCreditCheckPercent(Double creditCheckPercent) {
		this.creditCheckPercent = creditCheckPercent;
	}

	public QueueMessagerExtensionForCL(String appId, String jobName) {
		super(appId, jobName);
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
