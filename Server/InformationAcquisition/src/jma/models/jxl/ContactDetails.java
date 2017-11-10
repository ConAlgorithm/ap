/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈联系人详情〉
 *
 * @author hwei
 * @version ContactDetails.java, V1.0 2017年2月6日 下午2:24:15
 */
public class ContactDetails {
    // 电话号码
    private String phoneNum;
    // 号码归属地
    private String phoneNumLoc;
    // 呼叫次数
    private Integer callCnt;
    // 呼叫时长
    private Float callLen;
    // 呼出次数
    private Integer callOutCnt;
    // 呼入次数
    private Integer callInCnt;
    // 短信条数
    private Integer smsCnt;
    // 最早沟通时间
    private String transStart;
    // 最早沟通时间
    private String transEnd;
    
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
    public Integer getCallInCnt() {
        return callInCnt;
    }
    public void setCallInCnt(Integer callInCnt) {
        this.callInCnt = callInCnt;
    }
    public Integer getSmsCnt() {
        return smsCnt;
    }
    public void setSmsCnt(Integer smsCnt) {
        this.smsCnt = smsCnt;
    }
    public String getTransStart() {
        return transStart;
    }
    public void setTransStart(String transStart) {
        this.transStart = transStart;
    }
    public String getTransEnd() {
        return transEnd;
    }
    public void setTransEnd(String transEnd) {
        this.transEnd = transEnd;
    }

}
