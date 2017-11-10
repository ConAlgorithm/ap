package catfish.finance.payment.api;

import catfish.framework.IServiceProvider;

public abstract class PaymentChannel {
    protected PaymentController controller;
    protected IServiceProvider serviceProvider;
    
    public void init(PaymentController controller, IServiceProvider serviceProvider){
        this.controller = controller;
        this.serviceProvider = serviceProvider;
        this.onInit();
    }
    
    protected void onInit(){
        
    }
    
    public abstract String getName();
    public abstract void pay(PaymentRequest paymentRequest);
}
