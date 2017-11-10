package jma.handlers;

import jma.JobHandler;
import jma.RetryRequiredException;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.queue.QueueConfig;

import com.aliyun.mns.model.QueueMeta;


public class CheckTransactionMonitorJobsHandler extends JobHandler {
  private static final String TRANSACTION_QUEUE_NAME = "TransactionMonitorJobRequestQueue";

  @Override
  public void execute(String appId) throws RetryRequiredException {
    QueueMeta meta = QueueConfig.getQueue(TRANSACTION_QUEUE_NAME).getAttributes();
    long count = meta.getActiveMessages() + meta.getInactiveMessages();
    AppDerivativeVariableManager.addVariables(new AppDerivativeVariable(
        appId, AppDerivativeVariableNames.TRANSACTION_MONITOR_JOB_COUNT, (int) count));
  }
}
