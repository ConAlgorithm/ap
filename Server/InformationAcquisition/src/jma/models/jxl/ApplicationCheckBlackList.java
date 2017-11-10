/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package jma.models.jxl;

/**
 * 〈黑名单检查〉
 *
 * @author hwei
 * @version ApplicationCheckBlackList.java, V1.0 2017年2月6日 上午10:30:07
 */
public class ApplicationCheckBlackList {
    //是否出现
    private Boolean arised;
    //黑名单机构类型
    private String[] blackType;
    
    public Boolean getArised() {
        return arised;
    }
    public void setArised(Boolean arised) {
        this.arised = arised;
    }
    public String[] getBlackType() {
        return blackType;
    }
    public void setBlackType(String[] blackType) {
        this.blackType = blackType;
    }
    
}
