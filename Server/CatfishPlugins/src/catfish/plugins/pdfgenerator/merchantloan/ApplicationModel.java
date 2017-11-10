package catfish.plugins.pdfgenerator.merchantloan;

import java.math.BigDecimal;

import java.util.Date;

public class ApplicationModel {
	private String id;

	private String userId;

	private String accountId;

	private BigDecimal principal;

	private BigDecimal downpayment;

	private int repayments;

	private int status;

	private Date installmentStartedOn;

	private Date moneyTransferredOn;
	
	private int repaymentStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getDownpayment() {
		return downpayment;
	}

	public void setDownpayment(BigDecimal downpayment) {
		this.downpayment = downpayment;
	}

	public int getRepayments() {
		return repayments;
	}

	public void setRepayments(int repayments) {
		this.repayments = repayments;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRepaymentStatus() {
		return repaymentStatus;
	}

	public void setRepaymentStatus(int repaymentStatus) {
		this.repaymentStatus = repaymentStatus;
	}

	public Date getInstallmentStartedOn() {
		return installmentStartedOn;
	}

	public void setInstallmentStartedOn(Date installmentStartedOn) {
		this.installmentStartedOn = installmentStartedOn;
	}

	public Date getMoneyTransferredOn() {
		return moneyTransferredOn;
	}

	public void setMoneyTransferredOn(Date moneyTransferredOn) {
		this.moneyTransferredOn = moneyTransferredOn;
	}
	
}
