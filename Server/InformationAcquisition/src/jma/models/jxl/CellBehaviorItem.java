/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈运营商数据整理〉
 *
 * @author hwei
 * @version CellBehaviorItem.java, V1.0 2017年2月6日 上午10:59:33
 */
public class CellBehaviorItem {
    private String cellOperator;// 运营商
    private String cellOperatorZh;// 运营商（中文）
    private String cellPhoneNum;// 号码
    private String cellLoc;// 归属地
    private String cellMth;// 月份
    private Integer callCnt;// 呼叫次数
    private Integer callOutCnt;// 主叫次数
    private Float callOutTime;// 主叫时间
    private Integer callInCnt;// 被叫次数
    private Float callInTime;// 被叫时间
    private Float netFlow;// 流量
    private Integer smsCnt;// 短信数目
    private Float totalAmount;// 话费消费
    
    public String getCellOperator() {
        return cellOperator;
    }
    public void setCellOperator(String cellOperator) {
        this.cellOperator = cellOperator;
    }
    public String getCellOperatorZh() {
        return cellOperatorZh;
    }
    public void setCellOperatorZh(String cellOperatorZh) {
        this.cellOperatorZh = cellOperatorZh;
    }
    public String getCellPhoneNum() {
        return cellPhoneNum;
    }
    public void setCellPhoneNum(String cellPhoneNum) {
        this.cellPhoneNum = cellPhoneNum;
    }
    public String getCellLoc() {
        return cellLoc;
    }
    public void setCellLoc(String cellLoc) {
        this.cellLoc = cellLoc;
    }
    public String getCellMth() {
        return cellMth;
    }
    public void setCellMth(String cellMth) {
        this.cellMth = cellMth;
    }
    public Integer getCallCnt() {
        return callCnt;
    }
    public void setCallCnt(Integer callCnt) {
        this.callCnt = callCnt;
    }
    public Integer getCallOutCnt() {
        return callOutCnt;
    }
    public void setCallOutCnt(Integer callOutCnt) {
        this.callOutCnt = callOutCnt;
    }
    public Float getCallOutTime() {
        return callOutTime;
    }
    public void setCallOutTime(Float callOutTime) {
        this.callOutTime = callOutTime;
    }
    public Integer getCallInCnt() {
        return callInCnt;
    }
    public void setCallInCnt(Integer callInCnt) {
        this.callInCnt = callInCnt;
    }
    public Float getCallInTime() {
        return callInTime;
    }
    public void setCallInTime(Float callInTime) {
        this.callInTime = callInTime;
    }
    public Float getNetFlow() {
        return netFlow;
    }
    public void setNetFlow(Float netFlow) {
        this.netFlow = netFlow;
    }
    public Integer getSmsCnt() {
        return smsCnt;
    }
    public void setSmsCnt(Integer smsCnt) {
        this.smsCnt = smsCnt;
    }
    public Float getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

}
