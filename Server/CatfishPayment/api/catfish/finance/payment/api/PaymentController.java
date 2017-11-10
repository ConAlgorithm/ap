package catfish.finance.payment.api;

public interface PaymentController {
    void payDone(String channelName, PaymentResponse response);
}
