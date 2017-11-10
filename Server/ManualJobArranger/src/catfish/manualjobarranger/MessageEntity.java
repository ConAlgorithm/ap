package catfish.manualjobarranger;

import java.util.Date;

public class MessageEntity {
  private String id;
  private String appId;
  private Date appSubmittedDate;
  private String jobName;
  private Date jobGeneratedDate;
  private String messageHandle;
  private String messageBody;
  private String queueName;
  private int jobDuration;
  private int channel;
  private int jobCount;
  private boolean success;        // for HTTP request

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public Date getAppSubmittedDate() {
    return appSubmittedDate;
  }

  public void setAppSubmittedDate(Date appSubmittedDate) {
    this.appSubmittedDate = appSubmittedDate;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public Date getJobGeneratedDate() {
    return jobGeneratedDate;
  }

  public void setJobGeneratedDate(Date jobGeneratedDate) {
    this.jobGeneratedDate = jobGeneratedDate;
  }

  public String getMessageHandle() {
    return messageHandle;
  }

  public void setMessageHandle(String messageHandle) {
    this.messageHandle = messageHandle;
  }

  public String getMessageBody() {
    return messageBody;
  }

  public void setMessageBody(String messageBody) {
    this.messageBody = messageBody;
  }

  public String getQueueName() {
    return queueName;
  }

  public void setQueueName(String queueName) {
    this.queueName = queueName;
  }

  public int getJobDuration() {
    return jobDuration;
  }

  public void setJobDuration(int jobDuration) {
    this.jobDuration = jobDuration;
  }

  public int getChannel() {
    return channel;
  }

  public void setChannel(int channel) {
    this.channel = channel;
  }

  public int getJobCount() {
    return jobCount;
  }

  public void setJobCount(int jobCount) {
    this.jobCount = jobCount;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
