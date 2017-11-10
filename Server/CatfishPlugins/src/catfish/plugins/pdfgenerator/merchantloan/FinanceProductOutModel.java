package catfish.plugins.pdfgenerator.merchantloan;

import java.math.BigDecimal;

public class FinanceProductOutModel {
	private Integer id;
	private String name;
	private String description;
	private BigDecimal minPrincipal;
	private BigDecimal maxPrincipal;
	private Integer repaymentMonths;
	private BigDecimal interestRate;
	private String bearingType;

	public FinanceProductOutModel() {
		super();
	}

	public FinanceProductOutModel(Integer id, String name, String description, BigDecimal minPrincipal,
			BigDecimal maxPrincipal, Integer repaymentMonths, BigDecimal interestRate, String bearingType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.minPrincipal = minPrincipal;
		this.maxPrincipal = maxPrincipal;
		this.repaymentMonths = repaymentMonths;
		this.interestRate = interestRate;
		this.bearingType = bearingType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getRepaymentMonths() {
		return repaymentMonths;
	}

	public void setRepaymentMonths(Integer repaymentMonths) {
		this.repaymentMonths = repaymentMonths;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getBearingType() {
		return bearingType;
	}

	public void setBearingType(String bearingType) {
		this.bearingType = bearingType;
	}
}
