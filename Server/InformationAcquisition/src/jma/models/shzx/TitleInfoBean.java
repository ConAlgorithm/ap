package jma.models.shzx;

public class TitleInfoBean {
    private String  titleDetails    ;// 职称明细 （无，高级，中级，初级，未知）
    private String  titleInfoDateTime   ;// 信息获取日期，格式为YYYY.MM.DD
    public String getTitleDetails() {
        return titleDetails;
    }
    public void setTitleDetails(String titleDetails) {
        this.titleDetails = titleDetails;
    }
    public String getTitleInfoDateTime() {
        return titleInfoDateTime;
    }
    public void setTitleInfoDateTime(String titleInfoDateTime) {
        this.titleInfoDateTime = titleInfoDateTime;
    }

}
