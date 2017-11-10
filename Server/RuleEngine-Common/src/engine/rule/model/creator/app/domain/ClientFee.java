/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.app.domain;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉
 *
 * @author liucj
 * @version ClientFee.java, V1.0 2016年10月17日 下午8:12:14
 */
public class ClientFee {
    //客户服务费月利率
    
    private BigDecimal rate;

    //服务费总期数
    
    private Integer nper;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getNper() {
        return nper;
    }

    public void setNper(Integer nper) {
        this.nper = nper;
    }
    
    
}
