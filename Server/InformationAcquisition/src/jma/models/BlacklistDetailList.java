package jma.models;

public class BlacklistDetailList {
    private String borrowDate;   //借款日期 yyyy 年 mm 月
    private String borrowAmount; //借款金额 反馈值为区间值,例： 3-5（单位： 万元）
    private String borrowPeriod; //借款期限 单位:期， 每 30 天为 1 期
    private String overdueDate;  //逾期日期 yyyy 年 mm 月
    private String overdueLevel; //逾期级别 每 30 天 1M，分为(M1,M2,M3,M4,M5,M6,M6+)
    private String overdueAmount;//逾期金额  反馈值为区间值（单位： 万元）
    public String getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
    public String getBorrowAmount() {
        return borrowAmount;
    }
    public void setBorrowAmount(String borrowAmount) {
        this.borrowAmount = borrowAmount;
    }
    public String getBorrowPeriod() {
        return borrowPeriod;
    }
    public void setBorrowPeriod(String borrowPeriod) {
        this.borrowPeriod = borrowPeriod;
    }
    public String getOverdueDate() {
        return overdueDate;
    }
    public void setOverdueDate(String overdueDate) {
        this.overdueDate = overdueDate;
    }
    public String getOverdueLevel() {
        return overdueLevel;
    }
    public void setOverdueLevel(String overdueLevel) {
        this.overdueLevel = overdueLevel;
    }
    public String getOverdueAmount() {
        return overdueAmount;
    }
    public void setOverdueAmount(String overdueAmount) {
        this.overdueAmount = overdueAmount;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BlacklistDetailList [borrowDate=");
        builder.append(borrowDate);
        builder.append(", borrowAmount=");
        builder.append(borrowAmount);
        builder.append(", borrowPeriod=");
        builder.append(borrowPeriod);
        builder.append(", overdueDate=");
        builder.append(overdueDate);
        builder.append(", overdueLevel=");
        builder.append(overdueLevel);
        builder.append(", overdueAmount=");
        builder.append(overdueAmount);
        builder.append("]");
        return builder.toString();
    }
}
