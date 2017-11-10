package catfish.finance.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yiji.YijiChannel;
import cmb.CMBChannel;
import catfish.finance.payment.api.PaymentChannel;
import catfish.finance.payment.api.PaymentController;
import catfish.framework.IServiceProvider;

public class PaymentChannelProvider {
    private List<PaymentChannel> paymentChannels = new ArrayList<PaymentChannel>();
    private Map<String, PaymentChannel> channelMap = new HashMap<String, PaymentChannel>();
    
    public PaymentChannelProvider(PaymentController controller, IServiceProvider serviceProvider){
        paymentChannels.add(new CMBChannel());
        paymentChannels.add(new YijiChannel());
        
        for(PaymentChannel channel: paymentChannels){
            channel.init(controller, serviceProvider);
            channelMap.put(channel.getName(), channel);
        }
    }

    public List<PaymentChannel> getPaymentChannels() {
        return paymentChannels;
    }
    
    public PaymentChannel get(String channelName){
        return channelMap.get(channelName);
    }
}
