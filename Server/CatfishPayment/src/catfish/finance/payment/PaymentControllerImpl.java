package catfish.finance.payment;

import catfish.base.queue.QueueMessager;
import catfish.finance.payment.api.PaymentController;
import catfish.finance.payment.api.PaymentResponse;
import catfish.framework.queue.IQueueListener;

public class PaymentControllerImpl implements PaymentController{
    private IQueueListener queueListener;
    
    public PaymentControllerImpl(IQueueListener queueListener){
        this.queueListener = queueListener;
    }

    @Override
    public void payDone(String channelName, PaymentResponse response) {
        // TODO Auto-generated method stub
        QueueMessager qm;
        qm = new QueueMessager(response.getAppId(), WorkflowBuilder.getQueryJobName(channelName));
        queueListener.onMessage(qm.getJobName(), qm.toString());
    }

}
