/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈联系人通话详情〉
 *
 * @author hwei
 * @version ContactList.java, V1.0 2017年2月6日 上午11:52:31
 */
public class ContactList {
    private String phoneNum;// 号码
    private String phoneNumLoc;// 号码归属地
    private String contactName;// 号码标注
    private String needsType;// 需求类别
    private Integer callCnt;// 通话次数
    private Float callLen;// 通话时长
    private Integer callOutCnt;// 呼出次数
    private Float callOutLen;// 呼出时间
    private Integer callInCnt;// 呼入次数
    private Float callInLen;// 呼入时间
    private String pRelation;// 关系推测
    private Integer contact1w;// 最近一周联系次数
    private Integer contact1m;// 最近一月联系次数
    private Integer contact3m;// 最近三月联系次数
    private Integer contact3mPlus;// 三个月以上联系次数
    private Integer contactEarlyMorning;// 凌晨联系次数
    private Integer contactMorning;// 上午联系次数
    private Integer contactNoon;// 中午联系次数
    private Integer contactAfternoon;// 下午联系次数
    private Integer contactNight;// 晚上联系次数
    private Boolean contactAllDay;// 是否全天联系
    private Integer contactWeekday;// 周中联系次数
    private Integer contactWeekend;// 周末联系次数
    private Integer contactHoliday;// 节假日联系次数
    
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getPhoneNumLoc() {
        return phoneNumLoc;
    }
    public void setPhoneNumLoc(String phoneNumLoc) {
        this.phoneNumLoc = phoneNumLoc;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getNeedsType() {
        return needsType;
    }
    public void setNeedsType(String needsType) {
        this.needsType = needsType;
    }
    public Integer getCallCnt() {
        return callCnt;
    }
    public void setCallCnt(Integer callCnt) {
        this.callCnt = callCnt;
    }
    public Float getCallLen() {
        return callLen;
    }
    public void setCallLen(Float callLen) {
        this.callLen = callLen;
    }
    public Integer getCallOutCnt() {
        return callOutCnt;
    }
    public void setCallOutCnt(Integer callOutCnt) {
        this.callOutCnt = callOutCnt;
    }
    public Float getCallOutLen() {
        return callOutLen;
    }
    public void setCallOutLen(Float callOutLen) {
        this.callOutLen = callOutLen;
    }
    public Integer getCallInCnt() {
        return callInCnt;
    }
    public void setCallInCnt(Integer callInCnt) {
        this.callInCnt = callInCnt;
    }
    public Float getCallInLen() {
        return callInLen;
    }
    public void setCallInLen(Float callInLen) {
        this.callInLen = callInLen;
    }
    public String getpRelation() {
        return pRelation;
    }
    public void setpRelation(String pRelation) {
        this.pRelation = pRelation;
    }
    public Integer getContact1w() {
        return contact1w;
    }
    public void setContact1w(Integer contact1w) {
        this.contact1w = contact1w;
    }
    public Integer getContact1m() {
        return contact1m;
    }
    public void setContact1m(Integer contact1m) {
        this.contact1m = contact1m;
    }
    public Integer getContact3m() {
        return contact3m;
    }
    public void setContact3m(Integer contact3m) {
        this.contact3m = contact3m;
    }
    public Integer getContact3mPlus() {
        return contact3mPlus;
    }
    public void setContact3mPlus(Integer contact3mPlus) {
        this.contact3mPlus = contact3mPlus;
    }
    public Integer getContactEarlyMorning() {
        return contactEarlyMorning;
    }
    public void setContactEarlyMorning(Integer contactEarlyMorning) {
        this.contactEarlyMorning = contactEarlyMorning;
    }
    public Integer getContactMorning() {
        return contactMorning;
    }
    public void setContactMorning(Integer contactMorning) {
        this.contactMorning = contactMorning;
    }
    public Integer getContactNoon() {
        return contactNoon;
    }
    public void setContactNoon(Integer contactNoon) {
        this.contactNoon = contactNoon;
    }
    public Integer getContactAfternoon() {
        return contactAfternoon;
    }
    public void setContactAfternoon(Integer contactAfternoon) {
        this.contactAfternoon = contactAfternoon;
    }
    public Integer getContactNight() {
        return contactNight;
    }
    public void setContactNight(Integer contactNight) {
        this.contactNight = contactNight;
    }
    public Boolean getContactAllDay() {
        return contactAllDay;
    }
    public void setContactAllDay(Boolean contactAllDay) {
        this.contactAllDay = contactAllDay;
    }
    public Integer getContactWeekday() {
        return contactWeekday;
    }
    public void setContactWeekday(Integer contactWeekday) {
        this.contactWeekday = contactWeekday;
    }
    public Integer getContactWeekend() {
        return contactWeekend;
    }
    public void setContactWeekend(Integer contactWeekend) {
        this.contactWeekend = contactWeekend;
    }
    public Integer getContactHoliday() {
        return contactHoliday;
    }
    public void setContactHoliday(Integer contactHoliday) {
        this.contactHoliday = contactHoliday;
    }

}
