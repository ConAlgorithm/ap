package jma.models.shzx;

public class Contact2InfoBean {
    private String  contact2Name    ;// 联系人姓名
    private String  contact2Relation    ;// 联系人关系（父子、母子、配偶、子女、其他亲属、同事、朋友、其他）
    private String  contact2Phone   ;// 联系电话
    private String  contact2InfoDateTime    ;// 信息获取日期，格式为YYYY.MM.DD
    public String getContact2Name() {
        return contact2Name;
    }
    public void setContact2Name(String contact2Name) {
        this.contact2Name = contact2Name;
    }
    public String getContact2Relation() {
        return contact2Relation;
    }
    public void setContact2Relation(String contact2Relation) {
        this.contact2Relation = contact2Relation;
    }
    public String getContact2Phone() {
        return contact2Phone;
    }
    public void setContact2Phone(String contact2Phone) {
        this.contact2Phone = contact2Phone;
    }
    public String getContact2InfoDateTime() {
        return contact2InfoDateTime;
    }
    public void setContact2InfoDateTime(String contact2InfoDateTime) {
        this.contact2InfoDateTime = contact2InfoDateTime;
    }

}
