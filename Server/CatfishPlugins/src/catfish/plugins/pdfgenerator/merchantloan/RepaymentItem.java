package catfish.plugins.pdfgenerator.merchantloan;

import java.math.BigDecimal;
import java.util.Date;

public class RepaymentItem {
    public int index;

    public BigDecimal principal;
    public BigDecimal interest;
    public BigDecimal fee;
    public BigDecimal techMaintainFee;
    public Date dateDue;

    public RepaymentItem() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public BigDecimal getTechMaintainFee() {
        return techMaintainFee;
    }

    public void setTechMaintainFee(BigDecimal techMaintainFee) {
        this.techMaintainFee = techMaintainFee;
    }
}
