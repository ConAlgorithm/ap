package jma.models.shzx;

public class ShzxNfcsMaritalInfoBean {
    private String  maritalStatus   ;// 婚姻明细(未婚、已婚、初婚、再婚、复婚、丧偶、离婚、未说明的婚姻状况)
    private String  maritalInfoDateTime ;// 信息获取日期，格式为YYYY.MM.DD
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getMaritalInfoDateTime() {
        return maritalInfoDateTime;
    }
    public void setMaritalInfoDateTime(String maritalInfoDateTime) {
        this.maritalInfoDateTime = maritalInfoDateTime;
    }
    

}
