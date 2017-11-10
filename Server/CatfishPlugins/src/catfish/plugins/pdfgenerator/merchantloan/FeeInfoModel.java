package catfish.plugins.pdfgenerator.merchantloan;

import java.math.BigDecimal;

public class FeeInfoModel {
	private FeeNperModel clientFee;
	private BigDecimal techFeeRate;
	private BigDecimal advancedFee;
	private BigDecimal penaltyFee;

	public FeeInfoModel() {
		super();
	}

	public FeeInfoModel(FeeNperModel clientFee, BigDecimal techFeeRate, BigDecimal advancedFee, BigDecimal penaltyFee) {
		super();
		this.clientFee = clientFee;
		this.techFeeRate = techFeeRate;
		this.advancedFee = advancedFee;
		this.penaltyFee = penaltyFee;
	}

	public FeeNperModel getClientFee() {
		return clientFee;
	}

	public void setClientFee(FeeNperModel clientFee) {
		this.clientFee = clientFee;
	}

	public BigDecimal getTechFeeRate() {
		return techFeeRate;
	}

	public void setTechFeeRate(BigDecimal techFeeRate) {
		this.techFeeRate = techFeeRate;
	}

	public BigDecimal getAdvancedFee() {
		return advancedFee;
	}

	public void setAdvancedFee(BigDecimal advancedFee) {
		this.advancedFee = advancedFee;
	}

	public BigDecimal getPenaltyFee() {
		return penaltyFee;
	}

	public void setPenaltyFee(BigDecimal penaltyFee) {
		this.penaltyFee = penaltyFee;
	}

}
