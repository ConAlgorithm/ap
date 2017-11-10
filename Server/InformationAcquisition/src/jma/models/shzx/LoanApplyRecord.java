package jma.models.shzx;

public class LoanApplyRecord {
    private String  applyOrganization   ;// 申请机构
    private String  applyTime   ;// 申请时间
    private String  applyMoney  ;// 申请金额
    private String  applyMonth  ;// 申请月数
    private String  applyStatus ;// 申请状态（申请中、 已批准、 未通过）
    private String  applyType   ;// 申请类型（个人住房贷款、个人商用房（包括商住两用）贷款、 个人住房公积金贷款、 个人汽车消费贷款、 个人助学贷款、 个人经营性贷款、农户贷款、个人消费贷款、 其他）
    private String  applyInfoDateTime   ;// 申请信息获取时间，格式为YYYY.MM.DD
    public String getApplyOrganization() {
        return applyOrganization;
    }
    public void setApplyOrganization(String applyOrganization) {
        this.applyOrganization = applyOrganization;
    }
    public String getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
    public String getApplyMoney() {
        return applyMoney;
    }
    public void setApplyMoney(String applyMoney) {
        this.applyMoney = applyMoney;
    }
    public String getApplyMonth() {
        return applyMonth;
    }
    public void setApplyMonth(String applyMonth) {
        this.applyMonth = applyMonth;
    }
    public String getApplyStatus() {
        return applyStatus;
    }
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }
    public String getApplyType() {
        return applyType;
    }
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
    public String getApplyInfoDateTime() {
        return applyInfoDateTime;
    }
    public void setApplyInfoDateTime(String applyInfoDateTime) {
        this.applyInfoDateTime = applyInfoDateTime;
    }
}
