package catfish.plugins.pdfgenerator.merchantloan;

import java.math.BigDecimal;
import java.util.List;

public class ProductModel {
	private String id;

	private String financeProductId;

	private String name;

	private BigDecimal minPrincipal;

	private BigDecimal maxPrincipal;

	private BigDecimal principalStep;

	private BigDecimal interestRate;
	
	private List<Integer> repaymentMonths;

	private List<String> funds;

	private List<BigDecimal> downpaymentRatios;

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public List<BigDecimal> getDownpaymentRatios() {
		return downpaymentRatios;
	}

	public void setDownpaymentRatios(List<BigDecimal> downpaymentRatios) {
		this.downpaymentRatios = downpaymentRatios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMinPrincipal() {
		return minPrincipal;
	}

	public void setMinPrincipal(BigDecimal minPrincipal) {
		this.minPrincipal = minPrincipal;
	}

	public BigDecimal getMaxPrincipal() {
		return maxPrincipal;
	}

	public void setMaxPrincipal(BigDecimal maxPrincipal) {
		this.maxPrincipal = maxPrincipal;
	}

	public BigDecimal getPrincipalStep() {
		return principalStep;
	}

	public void setPrincipalStep(BigDecimal principalStep) {
		this.principalStep = principalStep;
	}

	public List<Integer> getRepaymentMonths() {
		return repaymentMonths;
	}

	public void setRepaymentMonths(List<Integer> repaymentMonths) {
		this.repaymentMonths = repaymentMonths;
	}

	public List<String> getFunds() {
		return funds;
	}

	public void setFunds(List<String> funds) {
		this.funds = funds;
	}

	public String getFinanceProductId() {
		return financeProductId;
	}

	public void setFinanceProductId(String financeProductId) {
		this.financeProductId = financeProductId;
	}
}
