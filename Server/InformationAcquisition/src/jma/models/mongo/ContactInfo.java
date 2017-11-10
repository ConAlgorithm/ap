/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.mongo;

/**
 * 〈通讯录联系信息〉
 *
 * @author hwei
 * @version ContactInfo.java, V1.0 2017年7月11日 下午3:08:11
 */
public class ContactInfo {
    private String name;
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
