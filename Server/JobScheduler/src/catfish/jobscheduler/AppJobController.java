package catfish.jobscheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;


public class AppJobController {

  private String id;
  private Map<String, String> statusMapping = new HashMap<>();
  private Map<String, Integer> waitingCountMapping = new HashMap<>();

  public AppJobController(String id) {
    this.id = id;

    for (JobInfo job: JobConfig.getAllJobs()) {
      statusMapping.put(job.name, JobStatus.NOT_STARTED);
      waitingCountMapping.put(job.name, 0);
    }
  }

  public void addJobStatusAndTryTriggering(String jobName, String status) {
    updateJobStatus(jobName, status);
    waitingCountMapping.put(jobName, Math.max(0, waitingCountMapping.get(jobName) - 1));

    tryTriggeringJobsOrTerminating(JobConfig.getJobInfo(jobName).successiveJobs);
  }

  private void updateJobStatus(String jobName, String status) {
    statusMapping.put(jobName, status);
    for (JobInfo previousJob : JobConfig.getJobInfo(jobName).previousJobs) {
      statusMapping.put(previousJob.name, JobStatus.CONTINUABLE);
    }
  }

  private void tryTriggeringJobsOrTerminating(List<JobInfo> jobs) {
    boolean triggered = false;
    for (JobInfo job : jobs) {
      if (job.type == JobType.EVENT) {
        continue;
      }

      triggered = tryTriggering(job) || triggered;
    }

    if (!triggered) {
      checkTermination();
    }
  }

  private boolean tryTriggering(JobInfo job) {
    for (JobInfo previousJob : job.previousJobs) {
      if (!statusMapping.get(previousJob.name).equals(JobStatus.CONTINUABLE)
          || waitingCountMapping.get(previousJob.name) > 0) {
        return false;    // has unfinished previous jobs
      }
    }

    triggerJob(job);
    return true;
  }

  private void triggerJob(JobInfo job) {
    waitingCountMapping.put(job.name, waitingCountMapping.get(job.name) + 1);
    PersistenceQueueApi.writeMessager(
        Configuration.JOB_REQUEST_QUEUE_MAPPING.get(job.type),
        new QueueMessager(id, job.name),
        MessageSource.JobScheduler);
  }

  private void checkTermination() {
    for (int count : waitingCountMapping.values()) {
      if (count > 0) {
        return;
      }
    }

    if (statusMapping.containsValue(JobStatus.STOPPED)) {
      Scheduler.untrackApp(id);
    }
  }
}
