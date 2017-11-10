package engine.rule.domain.app;

import java.util.Date;
import java.util.List;

public class AddressBook {
    private String userId;
    private String deviceId;
    private List<ContactInfo> contents;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public List<ContactInfo> getContents() {
        return contents;
    }
    public void setContents(List<ContactInfo> contents) {
        this.contents = contents;
    }    

}
