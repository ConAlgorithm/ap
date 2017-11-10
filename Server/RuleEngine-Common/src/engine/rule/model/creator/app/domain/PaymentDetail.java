/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.app.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈一句话功能简述〉
 *
 * @author liucj
 * @version RepaymentTableResult.java, V1.0 2016年10月28日 下午2:27:42
 */
public class PaymentDetail {

    //约定还款日
    private Date dueDate;
    //约定还款额
    private BigDecimal totalValue;
    //已还款额
    private BigDecimal payedValue;
    //实际还款日
    private Date payDate;
    //已免除金额
    private BigDecimal waivedValue;
    //待还款金额
    private BigDecimal owingValue;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getPayedValue() {
        return payedValue;
    }

    public void setPayedValue(BigDecimal payedValue) {
        this.payedValue = payedValue;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getWaivedValue() {
        return waivedValue;
    }

    public void setWaivedValue(BigDecimal waivedValue) {
        this.waivedValue = waivedValue;
    }

    public BigDecimal getOwingValue() {
        return owingValue;
    }

    public void setOwingValue(BigDecimal owingValue) {
        this.owingValue = owingValue;
    }

}
