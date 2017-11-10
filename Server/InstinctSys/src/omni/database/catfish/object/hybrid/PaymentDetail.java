package omni.database.catfish.object.hybrid;

import java.math.BigDecimal;

public class PaymentDetail {
    //约定还款日
    private Long dueDate;
    //约定还款额
    private BigDecimal totalValue;
    //已还款额
    private BigDecimal payedValue;
    //实际还款日
    private Long payDate;
    //已免除金额
    private BigDecimal waivedValue;
    //待还款金额
    private BigDecimal owingValue;

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
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

    public Long getPayDate() {
        return payDate;
    }

    public void setPayDate(Long payDate) {
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
