package omni.database.catfish.object.hybrid.constant;

public enum PaymentStatus {
                           //还款中
                           paymenting("10"),

                           //逾期
                           overdue("20"),

                           //正常结清
                           normalPayment("30"),

                           //提前结清
                           aheadPayment("40"),

                           //犹豫期提前结清
                           hesitationPeriodPayment("50"),

                           //当前日期为还款日
                           todayIsPaymentDate("60")

    ;
    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
