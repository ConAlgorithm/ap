package yiji;

import catfish.finance.payment.api.PaymentChannel;
import catfish.finance.payment.api.PaymentRequest;
import catfish.framework.restful.IRESTfulService;

public class YijiChannel extends PaymentChannel{
    @Override
    protected void onInit(){
        IRESTfulService rest = this.serviceProvider.getService(IRESTfulService.class);
        rest.register(new YijiResponse(this.controller));
    }

    @Override
    public String getName() {
        return "yiji";
    }

    @Override
    public void pay(PaymentRequest payInfo) {
        // TODO Auto-generated method stub
        
    }

}
