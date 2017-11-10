package network.relationship.businessdomain;

public class PaymentTag {
    private PaymentTagType fpd1Tag;
    
    private PaymentTagType fpd7Tag;
    
    private PaymentTagType fpd30Tag;
    
    private PaymentTagType spd30Tag;
    
    private PaymentTagType tpd30Tag;
    
    private PaymentTagType qpd30Tag;
    
    private int overdueCount;

    public PaymentTagType getFpd1Tag() {
        return fpd1Tag;
    }

    public void setFpd1Tag(PaymentTagType fpd1Tag) {
        this.fpd1Tag = fpd1Tag;
    }

    public PaymentTagType getFpd7Tag() {
        return fpd7Tag;
    }

    public void setFpd7Tag(PaymentTagType fpd7Tag) {
        this.fpd7Tag = fpd7Tag;
    }

    public PaymentTagType getFpd30Tag() {
        return fpd30Tag;
    }

    public void setFpd30Tag(PaymentTagType fpd30Tag) {
        this.fpd30Tag = fpd30Tag;
    }

    public PaymentTagType getSpd30Tag() {
        return spd30Tag;
    }

    public void setSpd30Tag(PaymentTagType spd30Tag) {
        this.spd30Tag = spd30Tag;
    }

    public PaymentTagType getTpd30Tag() {
        return tpd30Tag;
    }

    public void setTpd30Tag(PaymentTagType tpd30Tag) {
        this.tpd30Tag = tpd30Tag;
    }

    public PaymentTagType getQpd30Tag() {
        return qpd30Tag;
    }

    public void setQpd30Tag(PaymentTagType qpd30Tag) {
        this.qpd30Tag = qpd30Tag;
    }

    public int getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(int overdueCount) {
        this.overdueCount = overdueCount;
    }

}
