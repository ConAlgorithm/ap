package catfish.flowcontroller.activities.cashloan;


import java.util.Map;
import catfish.base.Logger;
import catfish.base.business.common.JobStatus;
import catfish.base.queue.QueueApi;
import catfish.base.queue.QueueMessager;
import catfish.flowcontroller.activities.Activity;
import catfish.flowcontroller.activities.MessagerHelper;
import catfish.flowcontroller.activities.StateHelper;
import catfish.flowcontroller.models.Application;
import catfish.framework.IServiceProvider;

public class CLSecurityCheckActivity extends Activity {

  private static final String securityCheckCount = "securityCheckCount";

  // jobs
  private static final String securityCheck = "CLSecurityCheck";
  private static final String headPhotoSubmitted = "HeadPhotoSubmitted";
  private static final String reuploadHeadPhotoMessage = "ReuploadHeadPhoto";
  private static final String securityCheckHeadPhotoReuploadNeeded =
      "SecurityCheckHeadPhotoReuploadNeeded";
  private static final String securityCheckApproved = "SecurityCheckApproved";
  private static final String securityCheckRejected = "SecurityCheckRejected";
  private static final String manualCheckHeadPhoto = "CheckHeadPhotoForCL";
  private static final String manualTransactionMonitor = "TransactionMonitorForCL";
  private static final String securityCheckCanceled = "SecurityCheckCanceled";
  
  // queues
  private static final String cowfishServiceQueue = "CLCowfishServiceQueue";
  private static final String cowfishMessageQueue = "CLCowfishMessageServiceQueue";
  private static final String headPhotoCheckQueue = "CLHeadPhotoQueue";
  private static final String ruleEngineQueue = "TopRulesDecisionJobRequestQueue";
  private static final String transactionMonitorQueue = "CLTransactionMonitorQueue";

  private int currentSecurityCheckCount = 1;

  @Override
  protected void init(Application application, IServiceProvider sp) {
    // do nothing
  }

  @Override
  protected void process(Application application, IServiceProvider sp) {
    QueueMessager messager = MessagerHelper.findQueueMessager(this, application, securityCheck);
    if (messager != null) {
      processSecurityCheckDone(messager);
      return;
    }

    messager = MessagerHelper.findQueueMessager(this, application, headPhotoSubmitted);
    if (messager != null) {
      processHeadPhotoSubmitted(messager);
      return;
    }

    messager = MessagerHelper.findQueueMessager(this, application, manualCheckHeadPhoto);
    if (messager != null) {
      processHeadPhotoManualCheckDone(messager);
      return;
    }
    
    messager = MessagerHelper.findQueueMessager(this, application, manualTransactionMonitor);
    if (messager != null) {
      processTransactionMonitorCheckDone(messager);
      return;
    }
  }

  @Override
  public void loadState(Map<String, Object> dataMap) {
    super.loadState(dataMap);
    currentSecurityCheckCount = StateHelper.loadInt(dataMap, securityCheckCount, 1);
  }

  @Override
  public void saveState(Map<String, Object> dataMap) {
    super.saveState(dataMap);
    StateHelper.save(dataMap, securityCheckCount, currentSecurityCheckCount);
  }

  private void processHeadPhotoSubmitted(QueueMessager messager) {
    sendHeadPhotoManualJob(messager.getAppId());
  }

  /**
   * function: when transaction monitor manual job has been done, process it
   * date: 2016-07-18
   * @author jiaoh
   * @param messager
   * @return void
   */
  private void processTransactionMonitorCheckDone(QueueMessager messager) {
      sendSecurityCheck(messager.getAppId());
  }
  
  private void processHeadPhotoManualCheckDone(QueueMessager messager) {
      String status = messager.getJobStatus();
      String appId = messager.getAppId();
    
      Logger.get().info("process head photo manual check done. appId: " + appId + " status: " + status);
      
      switch(status) {
          case JobStatus.Approved:
          case JobStatus.Done:
              sendSecurityCheck(appId);
              break;
          case JobStatus.RecheckingRequired:
              sendTransactionMonitorManualJob(appId);
              break;
          default:
              break;
      }

  }

  private void processSecurityCheckDone(QueueMessager messager) {
    String status = messager.getJobStatus();
    String appId = messager.getAppId();
    
    Logger.get().info("get security check output info. appId: " + appId + ", status: " + status);
    
    switch(status) {
      case JobStatus.Approved:
          sendEvent(appId, securityCheckApproved);
          this.done = true;
          break;
      case JobStatus.Rejected:
          sendEvent(appId, securityCheckRejected);
          this.done = true;
          break;
      case JobStatus.RecheckingRequired:
          currentSecurityCheckCount++;
          sendEvent(appId, securityCheckHeadPhotoReuploadNeeded);
          sendReuploadHeadPhotoMessage(messager.getAppId());
          break;
      case JobStatus.Canceled:
          sendEvent(appId, securityCheckCanceled);
          this.done = true;
          break;    
      default:
          break;
    }
  }

  private void sendEvent(String appId, String event) {
    QueueApi.writeMessage(
        cowfishServiceQueue, new QueueMessager(appId, event));
  }

  private void sendReuploadHeadPhotoMessage(String appId) {
    QueueApi.writeMessage(
        cowfishMessageQueue, new QueueMessager(appId, reuploadHeadPhotoMessage));
  }

  private void sendHeadPhotoManualJob(String appId) {
    QueueApi.writeMessage(
        headPhotoCheckQueue, new QueueMessager(appId, manualCheckHeadPhoto));
  }

  private void sendSecurityCheck(String appId) {
    QueueMessager messager = new QueueMessager(appId, securityCheck, currentSecurityCheckCount);
    messager.setChannel("cashloan");
    QueueApi.writeMessage(ruleEngineQueue, messager);
  }

  /**
   * function: send transaction monitor to ManualJob
   * date: 2016-07-18
   * @author jiaoh
   * @param appId
   * @return void
   */
  private void sendTransactionMonitorManualJob(String appId) {
      Logger.get().info("send transaction monitor to ManualJob. appId: " + appId);
      QueueApi.writeMessage(
          transactionMonitorQueue, new QueueMessager(appId, manualTransactionMonitor));
  }
}
