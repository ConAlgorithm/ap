package omni.database.catfish.object.hybrid;

public class PersonWorkDetailBean {
    private String  workNoId    ;// 工作序号
    private String  workAddress ;// 工作明细
    private String  workProfession  ;// 职业
    private String  workInfoDateTime    ;// 信息获取日期，格式为YYYY.MM.DD
    public String getWorkNoId() {
        return workNoId;
    }
    public void setWorkNoId(String workNoId) {
        this.workNoId = workNoId;
    }
    public String getWorkAddress() {
        return workAddress;
    }
    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }
    public String getWorkProfession() {
        return workProfession;
    }
    public void setWorkProfession(String workProfession) {
        this.workProfession = workProfession;
    }
    public String getWorkInfoDateTime() {
        return workInfoDateTime;
    }
    public void setWorkInfoDateTime(String workInfoDateTime) {
        this.workInfoDateTime = workInfoDateTime;
    }

}
