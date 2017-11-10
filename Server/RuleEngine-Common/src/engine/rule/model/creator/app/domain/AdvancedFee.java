/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.app.domain;

import java.math.BigDecimal;

/**
 * 〈〉
 *
 * @author yinqs
 * @version AdvancedFee.java, V1.0 2016年10月31日 下午5:11:56
 */
public class AdvancedFee {
    /** 提前还款服务费按固定值计算 */
    private BigDecimal value;
    /** 提前还款服务费按比例计算 */
    private BigDecimal rate;
    /** 提前还款服务费的收取规则 */
    private String strategy;
    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    /**
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }
    /**
     * @param rate the rate to set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    /**
     * @return the strategy
     */
    public String getStrategy() {
        return strategy;
    }
    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

}
