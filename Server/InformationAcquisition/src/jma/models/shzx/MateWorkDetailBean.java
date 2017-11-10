package jma.models.shzx;

public class MateWorkDetailBean {
    private String  mateWorkDetail  ;// 配偶工作单位明细
    private String  mateWorkInfoDateTime    ;// 信息获取日期，格式为YYYY.MM.DD
    public String getMateWorkDetail() {
        return mateWorkDetail;
    }
    public void setMateWorkDetail(String mateWorkDetail) {
        this.mateWorkDetail = mateWorkDetail;
    }
    public String getMateWorkInfoDateTime() {
        return mateWorkInfoDateTime;
    }
    public void setMateWorkInfoDateTime(String mateWorkInfoDateTime) {
        this.mateWorkInfoDateTime = mateWorkInfoDateTime;
    }

}
