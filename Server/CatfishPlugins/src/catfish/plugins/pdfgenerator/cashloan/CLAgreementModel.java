package catfish.plugins.pdfgenerator.cashloan;

import java.math.BigDecimal;
import java.util.Date;

public class CLAgreementModel {
	private String appId;
	private String userId;

	// user
	private String name;
	private String idNumber;
	private String address;
	private String installmentPurpose;

	// bank account
	private String bankName;
	private String bankAccount;

	// repayment & application
	private int repayments;
	private BigDecimal principal;
	private String principalCapital;
	private BigDecimal monthlyRepayment; // 每月总还款额
	private String monthlyRepaymentCapital; // 每月总还款额中文大写
	private BigDecimal principalInterestPerMonth; // 每月本息金额
	private BigDecimal feePerMonth; // 每月服务费
	private String feePerMonthCapital; // 每月服务费中文大写
	private BigDecimal fee; // 总服务费
	private BigDecimal monthlyFeeRate; // 每月服务费占本金比例
	private BigDecimal monthlyTechMainFee; // tech & maintain fee per month
	private BigDecimal monthlyInterestRate; // 月利率
	private BigDecimal penaltyPerDay;
	private BigDecimal prePaymentFee; // 提前还款费
	private Date firstPaybackDate;
	private int monthlyPaybackDay;
	private Date endTime; // 借款截止日期
	private Date moneyTransferredOn;
	private String repaymentSchedule; // 还款计划表html
	private BigDecimal techMaintenFee; // 总技术维护费
	private BigDecimal monthlyTechMaintenFeeRate; // 每月技术维护费占本金比例
	private BigDecimal valueAddedPkgFee;
	private BigDecimal packageAdvanceFee;
	private BigDecimal packageInsuranceFee;
	private String valueAddedPkgFlag;
	private BigDecimal consultFee;
	private BigDecimal customAndTechFee; // custom service and tech maintain fee
	private BigDecimal annualRate; // 年化利率
	private String monthlyRepaymentString = ""; // 月还款金额显示字符串
	private String techConsultFeeString = ""; // 技术咨询费显示字符串
	private BigDecimal formerDiversePaybackPackageFee = BigDecimal.ZERO; // 技术咨询费前x期收取费用
	private BigDecimal latterDiversePaybackPackageFee = BigDecimal.ZERO; // 技术咨询费后y期收取费用
	private int formerDiversePaybackPackagePeriods;  // 技术咨询费前x期期数
	private int latterDiversePaybackPackagePeriods;  // 技术咨询费后y期期数
	private BigDecimal formerPeriodsTotalMonthlyPay = BigDecimal.ZERO;  // 前x期收取总费用
	private BigDecimal latterPeriodsTotalMonthlyPay = BigDecimal.ZERO;  // 后y期收取总费用 
	private String monthlyTotalFeeString = "";  // 每月所有服务费显示字符串
	private BigDecimal formerPeriodsTotalMonthlyFee = BigDecimal.ZERO;  // 前x期总服务费
	private BigDecimal latterPeriodsTotalMonthlyFee = BigDecimal.ZERO;  // 后y期总服务费
	private boolean isDiverseApp;	// 是否是多样还款申请，包含正常还和轻松还
	private String monthlyRepaymentCapitalString = "";    // 月还款金额（字符串）条目

	public BigDecimal getMonthlyTechMainFee() {
		return monthlyTechMainFee;
	}

	public void setMonthlyTechMainFee(BigDecimal monthlyTechMainFee) {
		this.monthlyTechMainFee = monthlyTechMainFee;
	}

	public BigDecimal getAnnualRate() {
		return annualRate;
	}

	public void setAnnualRate(BigDecimal annualRate) {
		this.annualRate = annualRate;
	}

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

	public String getInstallmentPurpose() {
		return installmentPurpose;
	}

	public void setInstallmentPurpose(String installmentPurpose) {
		this.installmentPurpose = installmentPurpose;
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

	public BigDecimal getValueAddedPkgFee() {
		return valueAddedPkgFee;
	}

	public void setValueAddedPkgFee(BigDecimal valueAddedPkgFee) {
		this.valueAddedPkgFee = valueAddedPkgFee;
	}

	public String getValueAddedPkgFlag() {
		return valueAddedPkgFlag;
	}

	public void setValueAddedPkgFlag(String valueAddedPkgFlag) {
		this.valueAddedPkgFlag = valueAddedPkgFlag;
	}

	public BigDecimal getPackageAdvanceFee() {
		return packageAdvanceFee;
	}

	public void setPackageAdvanceFee(BigDecimal packageAdvanceFee) {
		this.packageAdvanceFee = packageAdvanceFee;
	}

	public BigDecimal getPackageInsuranceFee() {
		return packageInsuranceFee;
	}

	public void setPackageInsuranceFee(BigDecimal packageInsuranceFee) {
		this.packageInsuranceFee = packageInsuranceFee;
	}

	public BigDecimal getConsultFee() {
		return consultFee;
	}

	public void setConsultFee(BigDecimal consultFee) {
		this.consultFee = consultFee;
	}

	public BigDecimal getCustomAndTechFee() {
		return customAndTechFee;
	}

	public void setCustomAndTechFee(BigDecimal customAndTechFee) {
		this.customAndTechFee = customAndTechFee;
	}

	public String getMonthlyRepaymentString() {
		return monthlyRepaymentString;
	}

	public void setMonthlyRepaymentString(String monthlyRepaymentString) {
		this.monthlyRepaymentString = monthlyRepaymentString;
	}

	public String getTechConsultFeeString() {
		return techConsultFeeString;
	}

	public void setTechConsultFeeString(String techConsultFeeString) {
		this.techConsultFeeString = techConsultFeeString;
	}

	public BigDecimal getFormerDiversePaybackPackageFee() {
		return formerDiversePaybackPackageFee;
	}

	public void setFormerDiversePaybackPackageFee(BigDecimal formerDiversePaybackPackageFee) {
		this.formerDiversePaybackPackageFee = formerDiversePaybackPackageFee;
	}

	public BigDecimal getLatterDiversePaybackPackageFee() {
		return latterDiversePaybackPackageFee;
	}

	public void setLatterDiversePaybackPackageFee(BigDecimal latterDiversePaybackPackageFee) {
		this.latterDiversePaybackPackageFee = latterDiversePaybackPackageFee;
	}

	public int getFormerDiversePaybackPackagePeriods() {
		return formerDiversePaybackPackagePeriods;
	}

	public void setFormerDiversePaybackPackagePeriods(int formerDiversePaybackPackagePeriods) {
		this.formerDiversePaybackPackagePeriods = formerDiversePaybackPackagePeriods;
	}

	public int getLatterDiversePaybackPackagePeriods() {
		return latterDiversePaybackPackagePeriods;
	}

	public void setLatterDiversePaybackPackagePeriods(int latterDiversePaybackPackagePeriods) {
		this.latterDiversePaybackPackagePeriods = latterDiversePaybackPackagePeriods;
	}

	public java.lang.String toString() {
		return "CLAgreementModel{" + "appId='" + appId + '\'' + ", userId='" + userId + '\'' + ", name='" + name + '\''
				+ ", idNumber='" + idNumber + '\'' + ", address='" + address + '\'' + ", installmentPurpose='"
				+ installmentPurpose + '\'' + ", bankName='" + bankName + '\'' + ", bankAccount='" + bankAccount + '\''
				+ ", repayments=" + repayments + ", principal=" + principal + ", principalCapital='" + principalCapital
				+ '\'' + ", monthlyRepayment=" + monthlyRepayment + ", monthlyRepaymentCapital='"
				+ monthlyRepaymentCapital + '\'' + ", principalInterestPerMonth=" + principalInterestPerMonth
				+ ", feePerMonth=" + feePerMonth + ", feePerMonthCapital='" + feePerMonthCapital + '\'' + ", fee=" + fee
				+ ", monthlyFeeRate=" + monthlyFeeRate + ", monthlyTechMainFee=" + monthlyTechMainFee
				+ ", monthlyInterestRate=" + monthlyInterestRate + ", penaltyPerDay=" + penaltyPerDay
				+ ", prePaymentFee=" + prePaymentFee + ", firstPaybackDate=" + firstPaybackDate + ", monthlyPaybackDay="
				+ monthlyPaybackDay + ", endTime=" + endTime + ", moneyTransferredOn=" + moneyTransferredOn
				+ ", repaymentSchedule='" + repaymentSchedule + '\'' + ", techMaintenFee=" + techMaintenFee
				+ ", monthlyTechMaintenFeeRate=" + monthlyTechMaintenFeeRate + ", valueAddedPkgFee=" + valueAddedPkgFee
				+ ", packageAdvanceFee=" + packageAdvanceFee + ", packageInsuranceFee=" + packageInsuranceFee
				+ ", valueAddedPkgFlag='" + valueAddedPkgFlag + '\'' + ", consultFee=" + consultFee
				+ ", customAndTechFee=" + customAndTechFee + ", annualRate=" + annualRate + ", monthlyRepaymentString="
				+ monthlyRepaymentString + ", techConsultFeeString=" + techConsultFeeString + '}';
	}

	public BigDecimal getFormerPeriodsTotalMonthlyPay() {
		return formerPeriodsTotalMonthlyPay;
	}

	public void setFormerPeriodsTotalMonthlyPay(BigDecimal formerPeriodsTotalMonthlyPay) {
		this.formerPeriodsTotalMonthlyPay = formerPeriodsTotalMonthlyPay;
	}

	public BigDecimal getLatterPeriodsTotalMonthlyPay() {
		return latterPeriodsTotalMonthlyPay;
	}

	public void setLatterPeriodsTotalMonthlyPay(BigDecimal latterPeriodsTotalMonthlyPay) {
		this.latterPeriodsTotalMonthlyPay = latterPeriodsTotalMonthlyPay;
	}

	public String getMonthlyTotalFeeString() {
		return monthlyTotalFeeString;
	}

	public void setMonthlyTotalFeeString(String monthlyTotalFeeString) {
		this.monthlyTotalFeeString = monthlyTotalFeeString;
	}

	public BigDecimal getFormerPeriodsTotalMonthlyFee() {
		return formerPeriodsTotalMonthlyFee;
	}

	public void setFormerPeriodsTotalMonthlyFee(BigDecimal formerPeriodsTotalMonthlyFee) {
		this.formerPeriodsTotalMonthlyFee = formerPeriodsTotalMonthlyFee;
	}

	public BigDecimal getLatterPeriodsTotalMonthlyFee() {
		return latterPeriodsTotalMonthlyFee;
	}

	public void setLatterPeriodsTotalMonthlyFee(BigDecimal latterPeriodsTotalMonthlyFee) {
		this.latterPeriodsTotalMonthlyFee = latterPeriodsTotalMonthlyFee;
	}

	public boolean isDiverseApp() {
		return isDiverseApp;
	}

	public void setDiverseApp(boolean isDiverseApp) {
		this.isDiverseApp = isDiverseApp;
	}

	public String getMonthlyRepaymentCapitalString() {
		return monthlyRepaymentCapitalString;
	}

	public void setMonthlyRepaymentCapitalString(String monthlyRepaymentCapitalString) {
		this.monthlyRepaymentCapitalString = monthlyRepaymentCapitalString;
	}
	
	
}
