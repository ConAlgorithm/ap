package cmb;

import catfish.base.Logger;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;
import catfish.finance.payment.api.PaymentChannel;
import catfish.finance.payment.api.PaymentRequest;
import catfish.finance.payment.api.PaymentResponse;
import catfish.framework.queue.IQueue;
import catfish.framework.queue.IQueueListener;
import catfish.framework.queue.IQueueService;

public class CMBChannel extends PaymentChannel implements IQueueListener{
    private IQueue queryQueue;
    @Override
    protected void onInit(){
        IQueueService queueService = serviceProvider.getService(IQueueService.class);
        queryQueue = queueService.getQueue("PaymentQueryQueue");
        queryQueue.register("cmbQuery", this);
    }
    
    @Override
    public String getName() {
        return "cmb";
    }

    @Override
    public void pay(PaymentRequest payInfo) {
        QueueMessager data = new QueueMessager(payInfo.getAppId(),"cmbQuery");
        data.setJobDataInt(0);
        queryQueue.sendMessage(data.getJobName(), data, QueueConfig.QUEUE_PRIORITY_NORMAL, 5);
    }

    @Override
    public void onMessage(String message, String data) {
        QueueMessager messager = QueueMessager.fromString(data);
        if(messager==null){
            Logger.get().warn("CMBChannel: messager is null");
            return;
        }
        String appId = messager.getAppId();
        if(appId ==null){
            Logger.get().warn("CMBChannel: appId is null");
            return;
        }
        
        //TODO: check app status
        //TODO: query payment result;
        int retry = messager.getJobDataInt();
        if(retry<3){
            retry++;
            messager.setJobDataInt(retry);
            queryQueue.sendMessage(messager.getJobName(), messager, QueueConfig.QUEUE_PRIORITY_NORMAL, 30);
        } else {
            PaymentResponse response = new PaymentResponse();
            response.setAppId(appId);
            this.controller.payDone(this.getName(), response);
        }
    }

}
