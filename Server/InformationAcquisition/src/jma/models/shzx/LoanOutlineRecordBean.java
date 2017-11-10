package jma.models.shzx;

public class LoanOutlineRecordBean {
    private String  loanNumsTotal   ;// 贷款笔数
    private String  firstLoanDateTime   ;// 首贷日
    private String  maxCreditLineTotal  ;// 最大授信额度
    private String  loanMoneyTotal  ;// 贷款总额
    private String  remainMoneyTotal    ;// 贷款余额
    private String  mounthRepayTotal    ;// 协定月还款
    private String  repayOverdueMoneyTotal  ;// 当前逾期总额
    private String  repayOverdueMaxMoney    ;// 最高逾期金额
    private String  repayOverdueMaxNums ;// 最高逾期期数
    public String getLoanNumsTotal() {
        return loanNumsTotal;
    }
    public void setLoanNumsTotal(String loanNumsTotal) {
        this.loanNumsTotal = loanNumsTotal;
    }
    public String getFirstLoanDateTime() {
        return firstLoanDateTime;
    }
    public void setFirstLoanDateTime(String firstLoanDateTime) {
        this.firstLoanDateTime = firstLoanDateTime;
    }
    public String getMaxCreditLineTotal() {
        return maxCreditLineTotal;
    }
    public void setMaxCreditLineTotal(String maxCreditLineTotal) {
        this.maxCreditLineTotal = maxCreditLineTotal;
    }
    public String getLoanMoneyTotal() {
        return loanMoneyTotal;
    }
    public void setLoanMoneyTotal(String loanMoneyTotal) {
        this.loanMoneyTotal = loanMoneyTotal;
    }
    public String getRemainMoneyTotal() {
        return remainMoneyTotal;
    }
    public void setRemainMoneyTotal(String remainMoneyTotal) {
        this.remainMoneyTotal = remainMoneyTotal;
    }
    public String getMounthRepayTotal() {
        return mounthRepayTotal;
    }
    public void setMounthRepayTotal(String mounthRepayTotal) {
        this.mounthRepayTotal = mounthRepayTotal;
    }
    public String getRepayOverdueMoneyTotal() {
        return repayOverdueMoneyTotal;
    }
    public void setRepayOverdueMoneyTotal(String repayOverdueMoneyTotal) {
        this.repayOverdueMoneyTotal = repayOverdueMoneyTotal;
    }
    public String getRepayOverdueMaxMoney() {
        return repayOverdueMaxMoney;
    }
    public void setRepayOverdueMaxMoney(String repayOverdueMaxMoney) {
        this.repayOverdueMaxMoney = repayOverdueMaxMoney;
    }
    public String getRepayOverdueMaxNums() {
        return repayOverdueMaxNums;
    }
    public void setRepayOverdueMaxNums(String repayOverdueMaxNums) {
        this.repayOverdueMaxNums = repayOverdueMaxNums;
    }

}
