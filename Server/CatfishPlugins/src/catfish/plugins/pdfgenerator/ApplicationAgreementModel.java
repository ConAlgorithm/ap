package catfish.plugins.pdfgenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import catfish.plugins.finance.RepaymentItem;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class ApplicationAgreementModel {
	private String userId;
	private String appId;
	private String license;
	private String name;
	private String idNumber;
	private Date agreementStartTime;
	private BigDecimal principal;
	private String principalString;
	private BigDecimal totalPayback;
	private String totalPaybackStr;
	private BigDecimal fee;
	private String feeStr;
	private BigDecimal monthlyPay;
	private BigDecimal firstPayback;
	private String firstPaybackStr;
	private BigDecimal otherPayback;
	private String otherPaybackStr;
	private int repayments;
	private Date firstPaybackDate;
	private int monthlyPackbackDay;
	private Date today;
	private Date endTime;
	private Date installmentStartedOn;
	private Date moneyTransferredOn;
	private BigDecimal interestPerMonth;
	private BigDecimal feeInMonthlyPay;
	private String feeInMonthlyPayStr;
	private BigDecimal principalInMonthlyPay;
	private BigDecimal interestInMonthlyPay;
	private BigDecimal principalInterestPerMonth;
	private String bankName;
	private String bankAccount;
	private String bankAccountName;
	private String address;
	private String merchantName;
	private Integer InstallmentChannel;
	private String fundTag;
	private String mobile;

	private String merchantAddress;
	private String productName;
	private List<RepaymentItem> repaymentSchedule;

	private BigDecimal penalty;
	
	private String outFileName;
	private static String chineseNumber[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	
	private String changeNumber2Chinese(double value) {
		int number = (int)value;
		double decimal = value - number + 0.001;
		int num[] = new int[20];
		for (int i=0; i < num.length; i++)
			num[i] = 0;
		String s = "";
		int count = 0;
		while (number>0) {
			num[count++] = number%10;
			number /= 10;
		}
		for (int i=count-1; i>=0; i--) {
			switch(i) {
            	case 0: s += num[i] == 0 ? "圆" : chineseNumber[num[i]] + "圆"; break;
            	case 1: s += num[i] == 0 ? (s.endsWith("零") ? "" : "零") : chineseNumber[num[i]] + "拾"; break;
            	case 2: s += num[i] == 0 ? (s.endsWith("零") ? "" : "零") : chineseNumber[num[i]] + "佰"; break;
            	case 3: s += num[i] == 0 ? (s.endsWith("零") ? "" : "零") : chineseNumber[num[i]] + "仟"; break;
            	case 4: s += num[i] == 0 ? "万" : chineseNumber[num[i]] + "万"; break;
            	case 5: s += num[i] == 0 ? (s.endsWith("零") ? "" : "零") : chineseNumber[num[i]] + "拾"; break;
            	case 6: s += chineseNumber[num[i]] + "佰"; break;
			}
		}
		s = s.replace("零圆", "圆");
		s = s.replace("零万", "万");
		
		int d = 0;
		if (decimal < 0.01) {
			return s;
		} else {
			d = (int)(decimal*10);
			decimal = decimal*10 - d + 0.001;
			if(d > 9) {
				s += chineseNumber[9]+"角";
			} else {
				s += chineseNumber[d]+"角";
			}
			d = (int)(decimal*10);
			if(d > 9) {
				s += chineseNumber[9]+"分";
			} else {
				s += chineseNumber[d]+"分";
			}
		}
		
		return s;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
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
	public Date getAgreementStartTime() {
		return agreementStartTime;
	}
	public void setAgreementStartTime(Date agreementStartTime) {
		this.agreementStartTime = agreementStartTime;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getPrincipalInterestPerMonth() {
		return principalInterestPerMonth;
	}

	public void setPrincipalInterestPerMonth(BigDecimal principalInterestPerMonth) {
		this.principalInterestPerMonth = principalInterestPerMonth.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getPrincipalString() {
		this.principalString = changeNumber2Chinese(this.principal.doubleValue());
		return principalString;
	}
	public BigDecimal getMonthlyPay() {
//		monthlyPay = principalInMonthlyPay.add(interestInMonthlyPay);
		return monthlyPay;
	}
	public void setMonthlyPay(BigDecimal monthlyPay) {
		this.monthlyPay = monthlyPay.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public int getRepayments() {
		return repayments;
	}
	public void setRepayments(int repayments) {
		this.repayments = repayments;
	}
	public Date getFirstPaybackDate() {
		return firstPaybackDate;
	}
	public void setFirstPaybackDate(Date firstPaybackDate) {
		this.firstPaybackDate = firstPaybackDate;
	}
	public int getMonthlyPackbackDay() {
		return monthlyPackbackDay;
	}
	public void setMonthlyPackbackDay(int monthlyPackbackDay) {
		this.monthlyPackbackDay = monthlyPackbackDay;
	}
	public Date getToday() {
		if (moneyTransferredOn != null)
		{
			return moneyTransferredOn;
		}
		if(today == null) {
			today = new Date();
		}
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public Date getEndTime() {
		Calendar cal = Calendar.getInstance();
//		cal.setTime(installmentStartedOn);
		cal.setTime(getToday());
		cal.add(Calendar.MONTH, repayments);
		if (firstPayback != null && repayments > 1) {
			cal.setTime(firstPaybackDate);
			cal.add(Calendar.MONTH, repayments-1);
			return cal.getTime();
		}
		if (moneyTransferredOn != null) {
			cal.setTime(moneyTransferredOn);
			cal.add(Calendar.MONTH, repayments);
		}
		if ((cal.get(Calendar.DAY_OF_MONTH) > 28) && repayments >1 ) {
			cal.add(Calendar.MONTH, 1);
		}
		if (monthlyPackbackDay != 0) {
			cal.set(Calendar.DAY_OF_MONTH, monthlyPackbackDay);
		}
		endTime = cal.getTime();
		return endTime;
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

	public BigDecimal getInterestPerMonth() {
		return interestPerMonth;
	}
	public void setInterestPerMonth(BigDecimal interestPerMonth) {
		this.interestPerMonth = interestPerMonth.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getFeeInMonthlyPay() {
		return feeInMonthlyPay;
	}
	public void setFeeInMonthlyPay(BigDecimal feeInMonthlyPay) {
		this.feeInMonthlyPay = feeInMonthlyPay.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public String getFeeInMonthlyPayStr() {
		feeInMonthlyPayStr = changeNumber2Chinese(feeInMonthlyPay.doubleValue());
		return feeInMonthlyPayStr;
	}
	public BigDecimal getPrincipalInMonthlyPay() {
		return principalInMonthlyPay;
	}
	public void setPrincipalInMonthlyPay(BigDecimal principalInMonthlyPay) {
		this.principalInMonthlyPay = principalInMonthlyPay.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getInterestInMonthlyPay() {
		return interestInMonthlyPay;
	}
	public void setInterestInMonthlyPay(BigDecimal interestInMonthlyPay) {
		this.interestInMonthlyPay = interestInMonthlyPay.setScale(2, BigDecimal.ROUND_HALF_UP);
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
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		address = address;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public String getMerchantAddress() {
		return merchantAddress;
	}
	
	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getOutFileName() {
		return outFileName;
	}
	
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}
	
	public List<RepaymentItem> getRepaymentSchedule() {
		return repaymentSchedule;
	}

	public void setRepaymentSchedule(List<RepaymentItem> repaymentSchedule) {
		this.repaymentSchedule = repaymentSchedule;
	}

	public Integer getInstallmentChannel() {
		return InstallmentChannel;
	}

	public void setInstallmentChannel(Integer installmentChannel) {
		InstallmentChannel = installmentChannel;
	}

	public String getFundTag() {
		return fundTag;
	}

	public void setFundTag(String fundTag) {
		this.fundTag = fundTag;
	}

	public BigDecimal getTotalPayback() {
		return totalPayback;
	}

	public void setTotalPayback(BigDecimal totalPayback) {
		this.totalPayback = totalPayback.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getTotalPaybackStr() {
		return changeNumber2Chinese(totalPayback.doubleValue());
	}


	public String getFeeStr() {
		return changeNumber2Chinese(fee.doubleValue());
	}

	public BigDecimal getFirstPayback() {
		return firstPayback;
	}

	public void setFirstPayback(BigDecimal firstPayback) {
		this.firstPayback = firstPayback.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getFirstPaybackStr() {
		firstPaybackStr = changeNumber2Chinese(firstPayback.doubleValue());
		return firstPaybackStr;
	}

	public BigDecimal getOtherPayback() {
		return otherPayback;
	}

	public void setOtherPayback(BigDecimal otherPayback) {
		this.otherPayback = otherPayback.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getOtherPaybackStr() {
		if(otherPayback == null)
			return null;
		otherPaybackStr = changeNumber2Chinese(otherPayback.doubleValue());
		return otherPaybackStr;
	}

	public  void setPenalty(BigDecimal p) {
		this.penalty = p;
	}

	public BigDecimal getPenalty() {
		return  penalty.setScale(2, BigDecimal.ROUND_HALF_UP);
	}


}
