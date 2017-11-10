package catfish.plugins.pdfgenerator.merchantloan;

import java.math.BigDecimal;
import java.util.List;

public class RepaymentInfo {
    public int periods;
    public BigDecimal amount;
    public BigDecimal rate;

    public List<RepaymentItem> items;


    public RepaymentInfo() {
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public List<RepaymentItem> getItems() {
        return items;
    }

    public void setItems(List<RepaymentItem> items) {
        this.items = items;
    }
}
