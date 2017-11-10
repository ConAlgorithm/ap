/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈联系人区域整理〉
 *
 * @author hwei
 * @version ContactRegion.java, V1.0 2017年2月6日 上午11:50:15
 */
public class ContactRegion {
    private String regionLoc;// 地区名称
    private Integer regionUniqNumCnt;// 号码数量
    private Integer regionCallInCnt;// 电话呼入次数
    private Integer regionCallOutCnt;// 电话呼出次数
    private Float regionCallInTime;// 电话呼入时间
    private Float regionCallOutTime;// 电话呼出时间
    private Float regionAvgCallInTime;// 平均电话呼入时间
    private Float regionAvgCallOutTime;// 平均电话呼出时间
    private Float regionCallInCntPct;// 电话呼入次数百分比
    private Float regionCallOutCntPct;// 电话呼出次数百分比
    private Float regionCallInTimePct;// 电话呼入时间百分比
    private Float regionCallOutTimePct;// 电话呼出时间百分比
    
    public String getRegionLoc() {
        return regionLoc;
    }
    public void setRegionLoc(String regionLoc) {
        this.regionLoc = regionLoc;
    }
    public Integer getRegionUniqNumCnt() {
        return regionUniqNumCnt;
    }
    public void setRegionUniqNumCnt(Integer regionUniqNumCnt) {
        this.regionUniqNumCnt = regionUniqNumCnt;
    }
    public Integer getRegionCallInCnt() {
        return regionCallInCnt;
    }
    public void setRegionCallInCnt(Integer regionCallInCnt) {
        this.regionCallInCnt = regionCallInCnt;
    }
    public Integer getRegionCallOutCnt() {
        return regionCallOutCnt;
    }
    public void setRegionCallOutCnt(Integer regionCallOutCnt) {
        this.regionCallOutCnt = regionCallOutCnt;
    }
    public Float getRegionCallInTime() {
        return regionCallInTime;
    }
    public void setRegionCallInTime(Float regionCallInTime) {
        this.regionCallInTime = regionCallInTime;
    }
    public Float getRegionCallOutTime() {
        return regionCallOutTime;
    }
    public void setRegionCallOutTime(Float regionCallOutTime) {
        this.regionCallOutTime = regionCallOutTime;
    }
    public Float getRegionAvgCallInTime() {
        return regionAvgCallInTime;
    }
    public void setRegionAvgCallInTime(Float regionAvgCallInTime) {
        this.regionAvgCallInTime = regionAvgCallInTime;
    }
    public Float getRegionAvgCallOutTime() {
        return regionAvgCallOutTime;
    }
    public void setRegionAvgCallOutTime(Float regionAvgCallOutTime) {
        this.regionAvgCallOutTime = regionAvgCallOutTime;
    }
    public Float getRegionCallInCntPct() {
        return regionCallInCntPct;
    }
    public void setRegionCallInCntPct(Float regionCallInCntPct) {
        this.regionCallInCntPct = regionCallInCntPct;
    }
    public Float getRegionCallOutCntPct() {
        return regionCallOutCntPct;
    }
    public void setRegionCallOutCntPct(Float regionCallOutCntPct) {
        this.regionCallOutCntPct = regionCallOutCntPct;
    }
    public Float getRegionCallInTimePct() {
        return regionCallInTimePct;
    }
    public void setRegionCallInTimePct(Float regionCallInTimePct) {
        this.regionCallInTimePct = regionCallInTimePct;
    }
    public Float getRegionCallOutTimePct() {
        return regionCallOutTimePct;
    }
    public void setRegionCallOutTimePct(Float regionCallOutTimePct) {
        this.regionCallOutTimePct = regionCallOutTimePct;
    }

}
