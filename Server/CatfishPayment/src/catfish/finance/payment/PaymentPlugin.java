package catfish.finance.payment;

import catfish.base.queue.QueueMessager;
import catfish.finance.payment.api.PaymentController;
import catfish.framework.IPlugin;
import catfish.framework.IServiceProvider;
import catfish.framework.queue.IQueueListener;

public class PaymentPlugin implements IPlugin, Runnable{
    private IQueueListener queueListener;
    
    public PaymentPlugin(IQueueListener queueListener){
        this.queueListener = queueListener;
    }

    @Override
    public void init(IServiceProvider sp) {
        // TODO Auto-generated method stub
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
//        QueueMessager qm;
//        qm = new QueueMessager("aaa", "startPayment");
//        queueListener.onMessage(qm.getJobName(), qm.toString());
        
//        qm = new QueueMessager("aaa", "queryPayment");
//        queueListener.onMessage(qm.getJobName(), qm.toString());
    }

}
