package jma;

import java.util.Date;

import catfish.base.Logger;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.execution.ExecutionController;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;

/** A thread of job execution. */
public class JobThread implements Runnable {
  private int threadIndex;

  public JobThread(int threadIndex) {
    this.threadIndex = threadIndex;
  }

  @Override
  public void run() {
    while (true) {
      try {
        QueueMessager messager = readMessager();
        JobInstance job = convertToJob(messager);

        JobHandler handler = Configuration.createJobHandler(job.getJobType());

        //用于通过不同的申请环境对运行的Job进行过滤
        try {
      	  if(!isCashLoan(messager) &&
      	      ! ExecutionController.ifMatchExecution(job.getJobType(), InstallmentApplicationDao.getApplicationTypeById(job.getAppId())))
            {
      	      handler.initialize(messager);
          	  handler.writeJobResult(true);
          	  continue;
            }
        } catch(Exception e) {
      	  Logger.get().error(
      	      String.format("Unexcepted exception from job handler when separate weixin or app %s", job), e);
        }

        if (Thread.interrupted()) {
          // interruptions from elsewhere than workers-checking.
        	Logger.get().warn(
              String.format("Job thread %d is interrupted", threadIndex));
          throw new RuntimeException(
              String.format("Job thread %d is interrupted", threadIndex));
        }

        startTracking(job);
        boolean needRetrying = false;
        try {
          handler.initialize(messager);
          handler.execute(job.getAppId());
          handler.writeJobResult(true);
          Logger.get().info("Succeed in executing job " + job);
        } catch (RetryRequiredException e) {
          needRetrying = true;
          Logger.get().warn("Retry required for job " + job);
        } catch (RuntimeException e) {
          needRetrying = true;
          Logger.get().warn(
              String.format("Unexcepted exception from job handler when executing job %s", job), e);
        }
        finishTracking();
        Thread.interrupted();    // ignore extra interruptions from workers-checking

        if (needRetrying) {
          if (job.getAndDecreaseRemainingTries() >= 1) {
        	  Logger.get().warn(String.format("Will execute next try ! The job %s remaingingTries is %d",job,job.getAndDecreaseRemainingTries()));
            messager.setJobDataInt(job.getRemainingTries());
            PersistenceQueueApi.writeMessager(
                Configuration.getJobRequestQueue(),
                messager,
                QueueConfig.QUEUE_PRIORITY_HIGH,
                Configuration.JOB_RETRY_INTERVAL,MessageSource.InformationAcquisition);
          } else {
          	handler.writeJobResult(false);
            Logger.get().warn(String.format("RemainingTries exhausted ! Failed to execute job :%s" ,job));
          }
        }
      } catch (RuntimeException e) {
        Logger.get().error("Unexpected exception occurred in Job Thread :", e);
      }
    }
  }

  private void startTracking(JobInstance job) {
    JMSharedData.locks[threadIndex].lock();
    long expirationInMillis = new Date().getTime()
        + Configuration.getExpirationSeconds(job.getJobType()) * 1000;
    job.setExpirationTime(new Date(expirationInMillis));
    JMSharedData.jobs[threadIndex] = job;
    JMSharedData.locks[threadIndex].unlock();
  }

  private void finishTracking() {
    JMSharedData.locks[threadIndex].lock();
    JMSharedData.jobs[threadIndex] = null;
    JMSharedData.locks[threadIndex].unlock();
  }

  private static QueueMessager readMessager() {
    return PersistenceQueueApi.consumeMessager(
        Configuration.getJobRequestQueue(), MessageSource.InformationAcquisition);
  }

  private static JobInstance convertToJob(QueueMessager messager) {
    JobInstance job = new JobInstance(messager.getJobName(), messager.getAppId());
    job.setRemainingTries(messager.getJobDataInt() != null
        ? messager.getJobDataInt()
        : Configuration.getJobMaxRetries() - 1);
    return job;
  }

  private static boolean isCashLoan(QueueMessager messager) {
    return messager.getChannel() != null && messager.getChannel().equalsIgnoreCase("cashloan");
  }
}
