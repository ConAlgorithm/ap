/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.app.domain;

import java.math.BigDecimal; 

/**
 * 〈一句话功能简述〉
 *
 * @author liucj
 * @version Interest.java, V1.0 2016年10月17日 下午8:10:04
 */
public class Interest {
    
    //月利息 
    private BigDecimal rate;

    //利息计算方式，如"等额本金"，"等额本息"
    private String bearingType;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getBearingType() {
        return bearingType;
    }

    public void setBearingType(String bearingType) {
        this.bearingType = bearingType;
    }
    
    
}
