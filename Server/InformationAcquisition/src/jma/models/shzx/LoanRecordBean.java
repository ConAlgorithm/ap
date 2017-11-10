package jma.models.shzx;

public class LoanRecordBean {
    private String  loanProject ;// 贷款项目
    private String  orgName ;// 机构名称
    private String  creditLine  ;// 授信额度
    private String  guarantType ;// 担保方式（质押（含保证金）、 抵押、自然人保证、 信用/免担保、组合（含自然人保证）、组合（不含自然人保证）、农户联保、其他）
    private String  loanTime    ;// 开户日期，格式为YYYY.MM.DD
    private String  currencyType    ;// 币种
    private String  loanCity    ;// 发生地
    private String  shareCreditLine ;// 共享授信额度
    private String  maxDebtAmount   ;// 最大负债额
    private String  repayFrequency  ;// 还款频率（日、周、 月、 季、 半年、 年、 一次性、 不定期（ 还款日之间的时间间隔不是固定周期）、 其他）
    private String  finalLoanAmount ;// 期末贷款余额
    private String  repayRemainMounthNums   ;// 剩余还款月数
    private String  repayDateOfThisMounth   ;// 本月应还款日期，格式为YYYY.MM.DD
    private String  repayMoneyOfThisMounth  ;// 本月应还款金额
    private String  accountStatus   ;// 帐户状态（正常、 逾期、 结清、呆账、转出）
    private String  actualRepayDate ;// 实际还款日期，格式为YYYY.MM.DD
    private String  actualRepayMoney    ;// 实际还款金额
    private String  currentLoanOverdueAmount    ;// 当前逾期总额
    private String  currentLoanOverdueNums  ;// 当前逾期期数
    private String  totalLoanOverdueNums    ;// 累计逾期期数
    private String  maxLoanOverdueNums  ;// 最高逾期期数
    private String  repayStatusOf24Mounth   ;// 二十四月内各月还款状况
    private String  repayOverdueDays31To60  ;// 逾期31-60天未归还贷款本金
    private String  repayOverdueDays61To90  ;// 逾期61-90天未归还贷款本金
    private String  repayOverdueDays91To180 ;// 逾期91-180天未归还贷款本金
    private String  repayOverdueDaysOver180 ;// 逾期180天以上未归还贷款本金
    private String  loanRepayInfoDateTime   ;// 信息获取日期，格式为YYYY.MM.DD
    public String getLoanProject() {
        return loanProject;
    }
    public void setLoanProject(String loanProject) {
        this.loanProject = loanProject;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getCreditLine() {
        return creditLine;
    }
    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }
    public String getGuarantType() {
        return guarantType;
    }
    public void setGuarantType(String guarantType) {
        this.guarantType = guarantType;
    }
    public String getLoanTime() {
        return loanTime;
    }
    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }
    public String getCurrencyType() {
        return currencyType;
    }
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
    public String getLoanCity() {
        return loanCity;
    }
    public void setLoanCity(String loanCity) {
        this.loanCity = loanCity;
    }
    public String getShareCreditLine() {
        return shareCreditLine;
    }
    public void setShareCreditLine(String shareCreditLine) {
        this.shareCreditLine = shareCreditLine;
    }
    public String getMaxDebtAmount() {
        return maxDebtAmount;
    }
    public void setMaxDebtAmount(String maxDebtAmount) {
        this.maxDebtAmount = maxDebtAmount;
    }
    public String getRepayFrequency() {
        return repayFrequency;
    }
    public void setRepayFrequency(String repayFrequency) {
        this.repayFrequency = repayFrequency;
    }
    public String getFinalLoanAmount() {
        return finalLoanAmount;
    }
    public void setFinalLoanAmount(String finalLoanAmount) {
        this.finalLoanAmount = finalLoanAmount;
    }
    public String getRepayRemainMounthNums() {
        return repayRemainMounthNums;
    }
    public void setRepayRemainMounthNums(String repayRemainMounthNums) {
        this.repayRemainMounthNums = repayRemainMounthNums;
    }
    public String getRepayDateOfThisMounth() {
        return repayDateOfThisMounth;
    }
    public void setRepayDateOfThisMounth(String repayDateOfThisMounth) {
        this.repayDateOfThisMounth = repayDateOfThisMounth;
    }
    public String getRepayMoneyOfThisMounth() {
        return repayMoneyOfThisMounth;
    }
    public void setRepayMoneyOfThisMounth(String repayMoneyOfThisMounth) {
        this.repayMoneyOfThisMounth = repayMoneyOfThisMounth;
    }
    public String getAccountStatus() {
        return accountStatus;
    }
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    public String getActualRepayDate() {
        return actualRepayDate;
    }
    public void setActualRepayDate(String actualRepayDate) {
        this.actualRepayDate = actualRepayDate;
    }
    public String getActualRepayMoney() {
        return actualRepayMoney;
    }
    public void setActualRepayMoney(String actualRepayMoney) {
        this.actualRepayMoney = actualRepayMoney;
    }
    public String getCurrentLoanOverdueAmount() {
        return currentLoanOverdueAmount;
    }
    public void setCurrentLoanOverdueAmount(String currentLoanOverdueAmount) {
        this.currentLoanOverdueAmount = currentLoanOverdueAmount;
    }
    public String getCurrentLoanOverdueNums() {
        return currentLoanOverdueNums;
    }
    public void setCurrentLoanOverdueNums(String currentLoanOverdueNums) {
        this.currentLoanOverdueNums = currentLoanOverdueNums;
    }
    public String getTotalLoanOverdueNums() {
        return totalLoanOverdueNums;
    }
    public void setTotalLoanOverdueNums(String totalLoanOverdueNums) {
        this.totalLoanOverdueNums = totalLoanOverdueNums;
    }
    public String getMaxLoanOverdueNums() {
        return maxLoanOverdueNums;
    }
    public void setMaxLoanOverdueNums(String maxLoanOverdueNums) {
        this.maxLoanOverdueNums = maxLoanOverdueNums;
    }
    public String getRepayStatusOf24Mounth() {
        return repayStatusOf24Mounth;
    }
    public void setRepayStatusOf24Mounth(String repayStatusOf24Mounth) {
        this.repayStatusOf24Mounth = repayStatusOf24Mounth;
    }
    public String getRepayOverdueDays31To60() {
        return repayOverdueDays31To60;
    }
    public void setRepayOverdueDays31To60(String repayOverdueDays31To60) {
        this.repayOverdueDays31To60 = repayOverdueDays31To60;
    }
    public String getRepayOverdueDays61To90() {
        return repayOverdueDays61To90;
    }
    public void setRepayOverdueDays61To90(String repayOverdueDays61To90) {
        this.repayOverdueDays61To90 = repayOverdueDays61To90;
    }
    public String getRepayOverdueDays91To180() {
        return repayOverdueDays91To180;
    }
    public void setRepayOverdueDays91To180(String repayOverdueDays91To180) {
        this.repayOverdueDays91To180 = repayOverdueDays91To180;
    }
    public String getRepayOverdueDaysOver180() {
        return repayOverdueDaysOver180;
    }
    public void setRepayOverdueDaysOver180(String repayOverdueDaysOver180) {
        this.repayOverdueDaysOver180 = repayOverdueDaysOver180;
    }
    public String getLoanRepayInfoDateTime() {
        return loanRepayInfoDateTime;
    }
    public void setLoanRepayInfoDateTime(String loanRepayInfoDateTime) {
        this.loanRepayInfoDateTime = loanRepayInfoDateTime;
    }

}
