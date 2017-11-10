package jma.models.shzx;

public class EmailInfoBean {
    private String  email   ;// 电子邮箱明细
    private String  emailInfoDateTime   ;// 信息获取日期，格式为YYYY.MM.DD
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmailInfoDateTime() {
        return emailInfoDateTime;
    }
    public void setEmailInfoDateTime(String emailInfoDateTime) {
        this.emailInfoDateTime = emailInfoDateTime;
    }

}
