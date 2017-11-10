/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈服务详情〉
 *
 * @author hwei
 * @version ServiceDetails.java, V1.0 2017年2月6日 下午12:04:08
 */
public class ServiceDetails {
    //月互动次数
    private Integer interactCnt;
    //互动月份
    private String interactMth;
    
    public Integer getInteractCnt() {
        return interactCnt;
    }
    public void setInteractCnt(Integer interactCnt) {
        this.interactCnt = interactCnt;
    }
    public String getInteractMth() {
        return interactMth;
    }
    public void setInteractMth(String interactMth) {
        this.interactMth = interactMth;
    }
    
}
