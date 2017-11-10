/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈出行信息〉
 *
 * @author hwei
 * @version TripInfo.java, V1.0 2017年2月6日 下午2:27:07
 */
public class TripInfo {
    // 出发地
    private String tripLeave;
    // 目的地
    private String tripDest;
    // 出行时间类型
    private String tripType;
    // 出行开始时间
    private String tripStartTime;
    // 出行结束时间
    private String tripEndTime;
    
    public String getTripLeave() {
        return tripLeave;
    }
    public void setTripLeave(String tripLeave) {
        this.tripLeave = tripLeave;
    }
    public String getTripDest() {
        return tripDest;
    }
    public void setTripDest(String tripDest) {
        this.tripDest = tripDest;
    }
    public String getTripType() {
        return tripType;
    }
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }
    public String getTripStartTime() {
        return tripStartTime;
    }
    public void setTripStartTime(String tripStartTime) {
        this.tripStartTime = tripStartTime;
    }
    public String getTripEndTime() {
        return tripEndTime;
    }
    public void setTripEndTime(String tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

}
