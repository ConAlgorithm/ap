/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package omni.model;

import java.math.BigDecimal;

/**
 * 请求时间之前的还款计划表欠钱情况
 *
 * @author baowzh
 * @version PaymentInfoResponse.java, V1.0 2016年9月14日 上午12:33:27
 */
public class PaymentInfoResponse {

    /**
     * 应还金额
     */
    private BigDecimal owningTotal;
    
    /**
     * 当前期期数
     */
    private Integer instalmentNum;

    /**
     * 应还款日
     */
    private Long dueDate;

    /**
     * 实际还款日
     */
    private Long payDate;

    public BigDecimal getOwningTotal() {
        return owningTotal;
    }

    public void setOwningTotal(BigDecimal owningTotal) {
        this.owningTotal = owningTotal;
    }

    public Integer getInstalmentNum() {
        return instalmentNum;
    }

    public void setInstalmentNum(Integer instalmentNum) {
        this.instalmentNum = instalmentNum;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Long getPayDate() {
        return payDate;
    }

    public void setPayDate(Long payDate) {
        this.payDate = payDate;
    }
}
