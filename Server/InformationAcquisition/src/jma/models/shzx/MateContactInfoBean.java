package jma.models.shzx;

public class MateContactInfoBean {
    private String  mateContactPhone    ;// 配偶联系电话明细
    private String  mateContactInfoDateTime ;// 信息获取日期，格式为YYYY.MM.DD
    public String getMateContactPhone() {
        return mateContactPhone;
    }
    public void setMateContactPhone(String mateContactPhone) {
        this.mateContactPhone = mateContactPhone;
    }
    public String getMateContactInfoDateTime() {
        return mateContactInfoDateTime;
    }
    public void setMateContactInfoDateTime(String mateContactInfoDateTime) {
        this.mateContactInfoDateTime = mateContactInfoDateTime;
    }

}
