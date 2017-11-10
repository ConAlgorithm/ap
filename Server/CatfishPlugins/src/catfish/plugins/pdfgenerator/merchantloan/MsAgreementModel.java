package catfish.plugins.pdfgenerator.merchantloan;

import java.math.BigDecimal;
import java.util.Date;

public class MsAgreementModel {
  private String appId;
  private String userId;

  // user
  private String name;
  private String idNumber;
  private String address;

  // bank account
  private String bankName;
  private String bankAccount;

  //repayment & application
  private int repayments;
  private BigDecimal principal;
  private String principalCapital;
  private BigDecimal monthlyRepayment;    // 每月总还款额
  private String monthlyRepaymentCapital;    // 每月总还款额中文大写
  private BigDecimal principalInterestPerMonth;    // 每月本息金额
  private BigDecimal feePerMonth;    // 每月服务费
  private String feePerMonthCapital;    // 每月服务费中文大写
  private BigDecimal fee;    // 总服务费
  private BigDecimal monthlyFeeRate;    // 每月服务费占本金比例
  private BigDecimal monthlyInterestRate;    // 月利率
  private BigDecimal penaltyPerDay;
  private BigDecimal prePaymentFee;    // 提前还款费
  private Date firstPaybackDate;
  private int monthlyPaybackDay;
  private Date endTime;    // 借款截止日期
  private Date moneyTransferredOn;
  private String repaymentSchedule;    // 还款计划表html
  private BigDecimal techMaintenFee;    // 总技术维护费
  private BigDecimal monthlyTechMaintenFeeRate;    // 每月技术维护费占本金比例

  public String getAppId() {
    return appId;
  }
  public void setAppId(String appId) {
    this.appId = appId;
  }
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getIdNumber() {
    return idNumber;
  }
  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getBankName() {
    return bankName;
  }
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
  public String getBankAccount() {
    return bankAccount;
  }
  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }
  public int getRepayments() {
    return repayments;
  }
  public void setRepayments(int repayments) {
    this.repayments = repayments;
  }
  public BigDecimal getPrincipal() {
    return principal;
  }
  public void setPrincipal(BigDecimal principal) {
    this.principal = principal;
  }
  public String getPrincipalCapital() {
    return principalCapital;
  }
  public void setPrincipalCapital(String principalCapital) {
    this.principalCapital = principalCapital;
  }
  public BigDecimal getMonthlyRepayment() {
    return monthlyRepayment;
  }
  public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
    this.monthlyRepayment = monthlyRepayment;
  }
  public String getMonthlyRepaymentCapital() {
    return monthlyRepaymentCapital;
  }
  public void setMonthlyRepaymentCapital(String monthlyRepaymentCapital) {
    this.monthlyRepaymentCapital = monthlyRepaymentCapital;
  }
  public BigDecimal getPrincipalInterestPerMonth() {
    return principalInterestPerMonth;
  }
  public void setPrincipalInterestPerMonth(BigDecimal principalInterestPerMonth) {
    this.principalInterestPerMonth = principalInterestPerMonth;
  }
  public BigDecimal getFeePerMonth() {
    return feePerMonth;
  }
  public void setFeePerMonth(BigDecimal feePerMonth) {
    this.feePerMonth = feePerMonth;
  }
  public String getFeePerMonthCapital() {
    return feePerMonthCapital;
  }
  public void setFeePerMonthCapital(String feePerMonthCapital) {
    this.feePerMonthCapital = feePerMonthCapital;
  }
  public BigDecimal getFee() {
    return fee;
  }
  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }
  public BigDecimal getMonthlyFeeRate() {
    return monthlyFeeRate;
  }
  public void setMonthlyFeeRate(BigDecimal monthlyFeeRate) {
    this.monthlyFeeRate = monthlyFeeRate;
  }
  public BigDecimal getMonthlyInterestRate() {
    return monthlyInterestRate;
  }
  public void setMonthlyInterestRate(BigDecimal monthlyInterestRate) {
    this.monthlyInterestRate = monthlyInterestRate;
  }
  public BigDecimal getPenaltyPerDay() {
    return penaltyPerDay;
  }
  public void setPenaltyPerDay(BigDecimal penaltyPerDay) {
    this.penaltyPerDay = penaltyPerDay;
  }
  public BigDecimal getPrePaymentFee() {
    return prePaymentFee;
  }
  public void setPrePaymentFee(BigDecimal prePaymentFee) {
    this.prePaymentFee = prePaymentFee;
  }
  public Date getFirstPaybackDate() {
    return firstPaybackDate;
  }
  public void setFirstPaybackDate(Date firstPaybackDate) {
    this.firstPaybackDate = firstPaybackDate;
  }
  public int getMonthlyPaybackDay() {
    return monthlyPaybackDay;
  }
  public void setMonthlyPaybackDay(int monthlyPaybackDay) {
    this.monthlyPaybackDay = monthlyPaybackDay;
  }
  public Date getEndTime() {
    return endTime;
  }
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }
  public String getRepaymentSchedule() {
    return repaymentSchedule;
  }
  public void setRepaymentSchedule(String repaymentSchedule) {
    this.repaymentSchedule = repaymentSchedule;
  }
  public Date getMoneyTransferredOn() {
    return moneyTransferredOn;
  }
  public void setMoneyTransferredOn(Date moneyTransferredOn) {
    this.moneyTransferredOn = moneyTransferredOn;
  }
  public BigDecimal getTechMaintenFee() {
    return techMaintenFee;
  }
  public void setTechMaintenFee(BigDecimal techMaintenFee) {
    this.techMaintenFee = techMaintenFee;
  }
  public BigDecimal getMonthlyTechMaintenFeeRate() {
    return monthlyTechMaintenFeeRate;
  }
  public void setMonthlyTechMaintenFeeRate(BigDecimal monthlyTechMaintenFeeRate) {
    this.monthlyTechMaintenFeeRate = monthlyTechMaintenFeeRate;
  }

}
