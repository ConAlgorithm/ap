package catfish.plugins.pdfgenerator.merchantloan;

import java.math.BigDecimal;

public class FeeNperModel {

	private BigDecimal rate;
	private int nper;

	public FeeNperModel() {
		super();
	}

	public FeeNperModel(BigDecimal rate, int nper) {
		super();
		this.rate = rate;
		this.nper = nper;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public int getNper() {
		return nper;
	}

	public void setNper(int nper) {
		this.nper = nper;
	}
}
