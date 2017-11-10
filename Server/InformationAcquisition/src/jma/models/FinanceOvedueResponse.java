package jma.models;

public class FinanceOvedueResponse {
    private int    overdueTimes;
    private int    longestOverdueDays;
    private String msg;

    public int getOverdueTimes() {
        return overdueTimes;
    }

    public void setOverdueTimes(int overdueTimes) {
        this.overdueTimes = overdueTimes;
    }

    public int getLongestOverdueDays() {
        return longestOverdueDays;
    }

    public void setLongestOverdueDays(int longestOverdueDays) {
        this.longestOverdueDays = longestOverdueDays;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
