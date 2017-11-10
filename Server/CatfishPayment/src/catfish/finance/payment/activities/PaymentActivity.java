package catfish.finance.payment.activities;

import java.util.Map;

import catfish.finance.payment.PaymentChannelProvider;
import catfish.finance.payment.api.PaymentChannel;
import catfish.finance.payment.api.PaymentRequest;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.ActivityFactoryBase;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class PaymentActivity extends Activity{
    private String paymentChannel;

    @Override
    protected void init(Application application, IServiceProvider sp) {
        PaymentChannelProvider channelProvider = sp.getService(PaymentChannelProvider.class);
        PaymentChannel channel = channelProvider.get(paymentChannel);
        
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAppId(application.appId);
        channel.pay(paymentRequest);
    }

    @Override
    protected void process(Application application, IServiceProvider sp) {
        this.done = true;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

}

class PaymentActivityFactory extends ActivityFactoryBase<PaymentActivity>{
    @Override
    protected void setProperties(PaymentActivity activity, Map<String, Object> activityConfig){
        super.setProperties(activity, activityConfig);
        
        Object rawObj = activityConfig.get("paymentChannel");
        if(rawObj != null){
            activity.setPaymentChannel(rawObj.toString());
        }
    }
}
