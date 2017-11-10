/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.app.domain;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉
 *
 * @author liucj
 * @version Fee.java, V1.0 2016年10月17日 下午8:09:11
 */
public class Fee {
    //客户服务费
    //#id-清结算apimodel-ClientFee
    private ClientFee clientFee;

    //技术维护费利率（月利率）
    
    private BigDecimal techFeeRate;
    //增值维护费利率（月利率）
    
    private BigDecimal valueFeeRate;

    //提前还款服务费
    
    private AdvancedFee advancedFee;

    //罚金值, x/天
    
    private BigDecimal penaltyFee;

    //服务费精度,默认值为2，表示小数点后两位
    private Integer scale;

    public ClientFee getClientFee() {
        return clientFee;
    }

    public void setClientFee(ClientFee clientFee) {
        this.clientFee = clientFee;
    }

    public BigDecimal getTechFeeRate() {
        return techFeeRate;
    }

    public void setTechFeeRate(BigDecimal techFeeRate) {
        this.techFeeRate = techFeeRate;
    }

    public BigDecimal getValueFeeRate() {
        return valueFeeRate;
    }

    public void setValueFeeRate(BigDecimal valueFeeRate) {
        this.valueFeeRate = valueFeeRate;
    }

    public AdvancedFee getAdvancedFee() {
        return advancedFee;
    }

    public void setAdvancedFee(AdvancedFee advancedFee) {
        this.advancedFee = advancedFee;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }
    
    
}
