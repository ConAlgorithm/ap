package jma.models.shzx;

public class LoanGuarantRecord {
    private String  guarantProject  ;// 担保项目
    private String  guarantTime ;// 担保日期，格式为YYYY.MM.DD
    private String  guarantMoney    ;// 担保金额
    private String  guarantRelation ;// 担保关系
    private String  guarantInfoDateTime ;// 信息获取时间，格式为YYYY.MM.DD
    public String getGuarantProject() {
        return guarantProject;
    }
    public void setGuarantProject(String guarantProject) {
        this.guarantProject = guarantProject;
    }
    public String getGuarantTime() {
        return guarantTime;
    }
    public void setGuarantTime(String guarantTime) {
        this.guarantTime = guarantTime;
    }
    public String getGuarantMoney() {
        return guarantMoney;
    }
    public void setGuarantMoney(String guarantMoney) {
        this.guarantMoney = guarantMoney;
    }
    public String getGuarantRelation() {
        return guarantRelation;
    }
    public void setGuarantRelation(String guarantRelation) {
        this.guarantRelation = guarantRelation;
    }
    public String getGuarantInfoDateTime() {
        return guarantInfoDateTime;
    }
    public void setGuarantInfoDateTime(String guarantInfoDateTime) {
        this.guarantInfoDateTime = guarantInfoDateTime;
    }
}
