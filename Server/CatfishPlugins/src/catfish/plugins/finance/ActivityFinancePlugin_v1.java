//package catfish.plugins.finance;
//
//
//import com.google.gson.Gson;
//
//import catfish.base.queue.QueueMessager;
//import catfish.framework.IPlugin;
//import catfish.framework.IServiceProvider;
//import catfish.framework.queue.IQueue;
//import catfish.framework.queue.IQueueListener;
//import catfish.framework.queue.IQueueService;
//
//public class ActivityFinancePlugin_v1 implements IPlugin, IQueueListener {
//
//
//  private static String inQueueName = "AutoPaymentUpdateDBJobQueue";
//  private static String outQueueName = "AutoPaymentRequestQueue";
//  private static String addedQueueName = "GeneratePDFQueue";  
//  
//  private IQueue inQueue;
//  private IQueue outQueue;
//  private IQueue addedQueue;
//  
//  private static String queueJobName = "AutoPaymentUpdate";
//  
//  
//  @Override
//  public void init(IServiceProvider sp) {
//
//    IQueueService qs = sp.getService(IQueueService.class);
//    inQueue = qs.getQueue(inQueueName);
//    inQueue.register(queueJobName, this);
//    addedQueue = qs.getQueue(addedQueueName);
//    outQueue = qs.getQueue(outQueueName); 
//  }
//  
//
//  @Override
//  ///
//  //接收df的消息
//  //生成还款计划表并写入数据库，成功则通知生成PDF
//  //通知df还款计划表是否生成成功
//  ///
//  public void onMessage(String message, String data) {
//    //Todo
//    QueueMessager inMsg = new Gson().fromJson(data, QueueMessager.class);
//    String appId = inMsg.getAppId();
//    //boolean result = true;
//    boolean result = RepaymentSchedule.generateAndUpdateDB(appId, "");
//
//    if(result)
//    {
//      addedQueue.sendMessage("GeneratePDF", new QueueMessager(appId, "GeneratePDF"));
//    }
//    QueueMessager outMsg = new QueueMessager(appId, "FinishedAutoPayment", result ? "UpdateSuccess" : "UpdateFail");
//    outQueue.sendMessage("FinishedAutoPayment", outMsg);
//  }
//
//  
//  
//}