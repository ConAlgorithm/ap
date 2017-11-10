package jma;

public abstract class JobV2Handler extends JobHandler {
  public JobV2Handler() {
    responseMessager.setJobStatus(JobStatus.DONE);
  }
}
