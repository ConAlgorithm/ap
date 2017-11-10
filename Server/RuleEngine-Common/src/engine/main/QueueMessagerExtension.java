package engine.main;

import java.math.BigDecimal;
import java.util.Date;

import catfish.base.Logger;
import catfish.base.queue.QueueMessager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class QueueMessagerExtension extends QueueMessager{


	private String rewriteDecision;
	private BigDecimal downPaymentRate;
	
	public String getRewriteDecision() {
		return rewriteDecision;
	}

	public void setRewriteDecision(String rewriteDecision) {
		this.rewriteDecision = rewriteDecision;
	}

	public BigDecimal getDownPaymentRate() {
		return downPaymentRate;
	}

	public void setDownPaymentRate(BigDecimal downPaymentRate) {
		this.downPaymentRate = downPaymentRate;
	}
	

	public QueueMessagerExtension(String appId, String jobName) {
		super(appId,jobName);
	}


	@Override
	public String toString() {
		return new Gson().toJson(this);
	}


}
