/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models;

/**
 * 〈百度黑名单结果〉
 *
 * @author hwei
 * @version BlackResult.java, V1.0 2016年12月6日 下午6:58:40
 */
public class BlackResult {
    //黑名单等级 A/B/C/D；未命中-9999
    private String blackLevel;
    //黑名单原因
    private String blackReason;
    public String getBlackLevel() {
        return blackLevel;
    }
    public void setBlackLevel(String blackLevel) {
        this.blackLevel = blackLevel;
    }
    public String getBlackReason() {
        return blackReason;
    }
    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason;
    }
    
}
