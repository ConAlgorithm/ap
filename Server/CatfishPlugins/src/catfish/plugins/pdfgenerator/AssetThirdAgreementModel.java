package catfish.plugins.pdfgenerator;

import java.math.BigDecimal;
import java.util.Date;

public class AssetThirdAgreementModel {
	private String appId;
	private String oldAppId;
	private BigDecimal principal;
	private long moneyTransferredOn;
	private int repayments;
	private BigDecimal consultFee;
	public BigDecimal getConsultFee() {
		return consultFee;
	}
	public void setConsultFee(BigDecimal consultFee) {
		this.consultFee = consultFee;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOldAppId() {
		return oldAppId;
	}
	public void setOldAppId(String oldAppId) {
		this.oldAppId = oldAppId;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public long getMoneyTransferredOn() {
		return moneyTransferredOn;
	}
	public void setMoneyTransferredOn(long moneyTransferredOn) {
		this.moneyTransferredOn = moneyTransferredOn;
	}
	public int getRepayments() {
		return repayments;
	}
	public void setRepayments(int repayments) {
		this.repayments = repayments;
	}
	
	

}
