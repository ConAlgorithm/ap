/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈用户申请表检测〉
 *
 * @author hwei
 * @version ApplicationCheckPointItem.java, V1.0 2017年2月6日 上午10:21:23
 */
public class ApplicationCheckPointItem {
    //申请表数据点
    private String appPoint;
    //数据检查点
    private ApplicationCheckPoint checkPoints;
    
    public String getAppPoint() {
        return appPoint;
    }
    public void setAppPoint(String appPoint) {
        this.appPoint = appPoint;
    }
    public ApplicationCheckPoint getCheckPoints() {
        return checkPoints;
    }
    public void setCheckPoints(ApplicationCheckPoint checkPoints) {
        this.checkPoints = checkPoints;
    }
    
    
}
