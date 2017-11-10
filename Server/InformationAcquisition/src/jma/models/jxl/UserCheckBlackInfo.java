/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈用户黑名单信息〉
 *
 * @author hwei
 * @version UserCheckBlackInfo.java, V1.0 2017年2月6日 上午10:15:29
 */
public class UserCheckBlackInfo {
    // 用户号码联系黑中介分数
    private Integer phoneGrayScore;
    // 直接联系人中黑名单人数
    private Integer contactsClass1BlacklistCnt;
    // 间接联系人中黑名单人数
    private Integer contactsClass2BlacklistCnt;
    // 直接联系人人数
    private Integer contactsClass1Cnt;
    // 引起间接黑名单人数
    private Integer contactsRouterCnt;
    // 直接联系人中引起间接黑名单占比
    private Float contactsRouterRatio;
    
    public Integer getPhoneGrayScore() {
        return phoneGrayScore;
    }
    public void setPhoneGrayScore(Integer phoneGrayScore) {
        this.phoneGrayScore = phoneGrayScore;
    }
    public Integer getContactsClass1BlacklistCnt() {
        return contactsClass1BlacklistCnt;
    }
    public void setContactsClass1BlacklistCnt(Integer contactsClass1BlacklistCnt) {
        this.contactsClass1BlacklistCnt = contactsClass1BlacklistCnt;
    }
    public Integer getContactsClass2BlacklistCnt() {
        return contactsClass2BlacklistCnt;
    }
    public void setContactsClass2BlacklistCnt(Integer contactsClass2BlacklistCnt) {
        this.contactsClass2BlacklistCnt = contactsClass2BlacklistCnt;
    }
    public Integer getContactsClass1Cnt() {
        return contactsClass1Cnt;
    }
    public void setContactsClass1Cnt(Integer contactsClass1Cnt) {
        this.contactsClass1Cnt = contactsClass1Cnt;
    }
    public Integer getContactsRouterCnt() {
        return contactsRouterCnt;
    }
    public void setContactsRouterCnt(Integer contactsRouterCnt) {
        this.contactsRouterCnt = contactsRouterCnt;
    }
    public Float getContactsRouterRatio() {
        return contactsRouterRatio;
    }
    public void setContactsRouterRatio(Float contactsRouterRatio) {
        this.contactsRouterRatio = contactsRouterRatio;
    }

}
