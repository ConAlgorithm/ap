/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.mongo;

import java.util.Date;
import java.util.List;



/**
 * 〈通讯录model〉
 *
 * @author hwei
 * @version AddressBook.java, V1.0 2017年7月11日 下午3:06:16
 */
public class AddressBook {
    private String userId;
    private String deviceId;
    private List<ContactInfo> contents;
    private Long create_time;
    private Long update_time;

    public List<ContactInfo> getContents() {
        return contents;
    }

    public void setContents(List<ContactInfo> contents) {
        this.contents = contents;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }
}
