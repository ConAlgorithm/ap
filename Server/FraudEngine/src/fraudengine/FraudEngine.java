package fraudengine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.business.common.JobStatus;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;

public class FraudEngine implements Runnable {

  private String           name             = "";
  private FraudRuleManager fraudRuleManager = new FraudRuleManager();

  public static void trigger(int threadCount) {
    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < threadCount; i++) {
      Runnable worker = new FraudEngine("Fraud engine service - " + i);
      executor.execute(worker);
    }
    executor.shutdown();

    Logger.get().info("MQS server started ...");
    while (!executor.isTerminated()) {
      ThreadUtils.sleepInSeconds(3);
    }
  }

  public FraudEngine(String name) {
    this.name = name;
  }

  @Override
  public void run() {

    while (true) {
      QueueMessager messager = null;
      try {
        messager = PersistenceQueueApi.consumeMessager(Configuration.FRAUD_DECISION_QUEUE,MessageSource.FraudEngine);
        Logger.get().info(
            "Fraud engine worker " + this.name + " recevied a new job: "
                + messager.toString());

        runFraudRules(messager);

      } catch (Exception e) {
        Logger.get().error("run FraudRules failed for messager= " + new Gson().toJson(messager), e);
      }
    }
  }

  private void runFraudRules(QueueMessager messager) {
    boolean approved = this.fraudRuleManager.run(messager.getAppId());
    String callbackQueName = messager.getCallbackQueue();
    String outQueName = (callbackQueName == null) ? 
    		Configuration.JOB_STATUS_QUEUE : callbackQueName;
    PersistenceQueueApi.writeMessager(outQueName, new QueueMessager(
        messager.getAppId(), messager.getJobName(), JobStatus.Done),MessageSource.FraudEngine);
    Logger.get().info(
        "The result from all fraud rules for AppId="+messager.getAppId()+" is "
            + (approved ? JobStatus.Approved : JobStatus.Rejected));
  }
}
