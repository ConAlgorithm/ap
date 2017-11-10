package jma.models.shzx;

public class MobileInfoBean {
    private String  mobile  ;// 手机号码明细，格式为YYYY.MM.DD
    private String  mobileInfoDateTime  ;// 信息获取日期，格式为YYYY.MM.DD
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobileInfoDateTime() {
        return mobileInfoDateTime;
    }
    public void setMobileInfoDateTime(String mobileInfoDateTime) {
        this.mobileInfoDateTime = mobileInfoDateTime;
    }

}
