package omni.database.catfish.object.hybrid;

public class AddressInfoBean {
    private String  addressNoId ;// 地址序号
    private String  addressDetail   ;// 地址明细
    private String  addressInfoDateTime ;// 信息获取日期，格式为YYYY.MM.DD
    public String getAddressNoId() {
        return addressNoId;
    }
    public void setAddressNoId(String addressNoId) {
        this.addressNoId = addressNoId;
    }
    public String getAddressDetail() {
        return addressDetail;
    }
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
    public String getAddressInfoDateTime() {
        return addressInfoDateTime;
    }
    public void setAddressInfoDateTime(String addressInfoDateTime) {
        this.addressInfoDateTime = addressInfoDateTime;
    }

}
