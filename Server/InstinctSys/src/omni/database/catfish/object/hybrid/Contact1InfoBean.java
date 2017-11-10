package omni.database.catfish.object.hybrid;

public class Contact1InfoBean {
    private String  contact1Name    ;// 联系人姓名
    private String  contact1Relation    ;// 联系人关系（父子、母子、配偶、子女、其他亲属、同事、朋友、其他）
    private String  contact1Phone   ;// 联系电话
    private String  contact1InfoDateTime    ;// 信息获取日期，格式为YYYY.MM.DD
    public String getContact1Name() {
        return contact1Name;
    }
    public void setContact1Name(String contact1Name) {
        this.contact1Name = contact1Name;
    }
    public String getContact1Relation() {
        return contact1Relation;
    }
    public void setContact1Relation(String contact1Relation) {
        this.contact1Relation = contact1Relation;
    }
    public String getContact1Phone() {
        return contact1Phone;
    }
    public void setContact1Phone(String contact1Phone) {
        this.contact1Phone = contact1Phone;
    }
    public String getContact1InfoDateTime() {
        return contact1InfoDateTime;
    }
    public void setContact1InfoDateTime(String contact1InfoDateTime) {
        this.contact1InfoDateTime = contact1InfoDateTime;
    }

}
