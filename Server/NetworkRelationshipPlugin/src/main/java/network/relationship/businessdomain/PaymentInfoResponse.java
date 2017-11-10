package network.relationship.businessdomain;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentInfoResponse {
    private BigDecimal owningTotal;
    
    private Integer instalmentNum;
    
    private Long dueDate;
    
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
