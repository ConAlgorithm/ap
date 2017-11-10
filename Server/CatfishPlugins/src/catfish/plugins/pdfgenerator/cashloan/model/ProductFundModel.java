package catfish.plugins.pdfgenerator.cashloan.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductFundModel {
	private Integer id;
	private String productId;
	private String fundTag;
	private String fundId;
	private Integer priority;
	private Integer status;
	private Date dateAdded;
	private Date lastModified;
	private BigDecimal interestValue;
	private BigDecimal frontServiceRate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFundTag() {
		return fundTag;
	}

	public void setFundTag(String fundTag) {
		this.fundTag = fundTag;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public BigDecimal getInterestValue() {
		return interestValue;
	}

	public void setInterestValue(BigDecimal interestValue) {
		this.interestValue = interestValue;
	}

	public BigDecimal getFrontServiceRate() {
		return frontServiceRate;
	}

	public void setFrontServiceRate(BigDecimal frontServiceRate) {
		this.frontServiceRate = frontServiceRate;
	}

	@Override
	public String toString() {
		return "ProductFundModel [id=" + id + ", productId=" + productId + ", fundTag=" + fundTag + ", fundId=" + fundId
				+ ", priority=" + priority + ", status=" + status + ", dateAdded=" + dateAdded + ", lastModified="
				+ lastModified + ", interestValue=" + interestValue + ", frontServiceRate=" + frontServiceRate + "]";
	}
}
