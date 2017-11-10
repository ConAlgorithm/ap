package jma;

import java.util.Date;

public class JobInstance {
  private String jobType;
  private int remainingTries;
  private Date expirationTime;

  private String appId;   // other information

  public JobInstance(String jobType, String appId) {
    this.jobType = jobType;
    this.appId = appId;
  }

  public String getJobType() {
    return jobType;
  }

  public String getAppId() {
    return appId;
  }

  public int getRemainingTries() {
    return remainingTries;
  }

  public void setRemainingTries(int remainingTries) {
    this.remainingTries = remainingTries;
  }

  public int getAndDecreaseRemainingTries() {
    return remainingTries--;
  }

  public Date getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(Date expirationTime) {
    this.expirationTime = expirationTime;
  }

  @Override
  public String toString() {
    return String.format("Job appId=[%s] type=[%s]", appId, jobType);
  }
}
