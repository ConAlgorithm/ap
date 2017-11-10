/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package catfish.plugins.pdfgenerator.cashloan;

import java.math.BigDecimal;
import java.util.Date;

public class FundFinanceProduct {

    private Integer id;
    private String description;
    private Integer minPrincipal;
    private Integer maxPrincipal;
    private Integer principalStep;
    private BigDecimal annualInterestRate;
    private Integer repaymentMonths;
    private Byte loanType;
    private Byte hesitateDays;
    private BigDecimal interestRate;
    private BigDecimal techFee;
    private BigDecimal customFee;
    private BigDecimal fine;
    private BigDecimal prepayment;
    private BigDecimal preRate;
    private Date createTime;
    private Date updateTime;
    private String updateBy;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    public Integer getMinPrincipal() {
        return minPrincipal;
    }
    public void setMinPrincipal(Integer minPrincipal) {
        this.minPrincipal = minPrincipal;
    }
    public Integer getMaxPrincipal() {
        return maxPrincipal;
    }
    public void setMaxPrincipal(Integer maxPrincipal) {
        this.maxPrincipal = maxPrincipal;
    }
    public Integer getPrincipalStep() {
        return principalStep;
    }
    public void setPrincipalStep(Integer principalStep) {
        this.principalStep = principalStep;
    }
    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }
    public void setAnnualInterestRate(BigDecimal annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }
    public Integer getRepaymentMonths() {
        return repaymentMonths;
    }
    public void setRepaymentMonths(Integer repaymentMonths) {
        this.repaymentMonths = repaymentMonths;
    }
    public Byte getLoanType() {
        return loanType;
    }
    public void setLoanType(Byte loanType) {
        this.loanType = loanType;
    }
    public Byte getHesitateDays() {
        return hesitateDays;
    }
    public void setHesitateDays(Byte hesitateDays) {
        this.hesitateDays = hesitateDays;
    }
    public BigDecimal getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
    public BigDecimal getTechFee() {
        return techFee;
    }
    public void setTechFee(BigDecimal techFee) {
        this.techFee = techFee;
    }
    public BigDecimal getCustomFee() {
        return customFee;
    }
    public void setCustomFee(BigDecimal customFee) {
        this.customFee = customFee;
    }
    public BigDecimal getFine() {
        return fine;
    }
    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }
    public BigDecimal getPrepayment() {
        return prepayment;
    }
    public void setPrepayment(BigDecimal prepayment) {
        this.prepayment = prepayment;
    }
    public BigDecimal getPreRate() {
        return preRate;
    }
    public void setPreRate(BigDecimal preRate) {
        this.preRate = preRate;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    @Override
    public String toString() {
        return "FundFinanceProduct{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", minPrincipal=" + minPrincipal +
                ", maxPrincipal=" + maxPrincipal +
                ", principalStep=" + principalStep +
                ", annualInterestRate=" + annualInterestRate +
                ", repaymentMonths=" + repaymentMonths +
                ", loanType=" + loanType +
                ", hesitateDays=" + hesitateDays +
                ", interestRate=" + interestRate +
                ", techFee=" + techFee +
                ", customFee=" + customFee +
                ", fine=" + fine +
                ", prepayment=" + prepayment +
                ", preRate=" + preRate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}