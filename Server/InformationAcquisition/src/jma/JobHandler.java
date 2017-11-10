package jma;

import catfish.base.DynamicConfig;
import catfish.base.StringUtils;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.object.InstallmentApplicationObject;
import catfish.base.business.util.EnumUtils;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;

public abstract class JobHandler {

  protected QueueMessager requestMessager;

  protected Boolean sendsResponse;
  protected String responseQueue = Configuration.getJobResponseQueue();
  protected QueueMessager responseMessager = new QueueMessager(null, null, JobStatus.CONTINUABLE);

  protected String appId;
  protected String userId;
  protected InstalmentChannel channel;

  public void initialize(QueueMessager requestMessager) {
    this.requestMessager = requestMessager;
    responseMessager.setAppId(requestMessager.getAppId());
    responseMessager.setJobName(requestMessager.getJobName());

    this.userId = requestMessager.getUserId();
    this.appId = requestMessager.getAppId();
    this.channel = getApplicationChannel(requestMessager);
    if (channel == InstalmentChannel.CashLoan) {
      this.responseQueue = Configuration.JOB_CL_STATUS_QUEUE_NAME;
    }

    // dynamic > config
    if (!StringUtils.isNullOrWhiteSpaces(requestMessager.getCallbackQueue())) {
      this.responseQueue = requestMessager.getCallbackQueue();
    }

    preprocess();
  }

  public void preprocess() {}

  public abstract void execute(String appId) throws RetryRequiredException;

  public void writeJobResult(boolean isSuccess) {
    if (sendsResponse != null && sendsResponse.booleanValue()
        || sendsResponse == null && isSuccess) {
      PersistenceQueueApi.writeMessager(
          responseQueue, responseMessager, MessageSource.InformationAcquisition);
    }

    if (!isSuccess) {
      PersistenceQueueApi.writeMessager(
          Configuration.JOB_FAILURE_QUEUE_NAME,
          requestMessager,
          MessageSource.InformationAcquisition);
    }
  }

  private InstalmentChannel getApplicationChannel(QueueMessager messager) {
    String appId = messager.getAppId();
    InstallmentApplicationObject app = new InstallmentApplicationDao(appId).getSingle();
    if (app != null) {
      return EnumUtils.parse(InstalmentChannel.class, app.InstalmentChannel);
    }

    String channel = messager.getChannel();
    if (channel.equalsIgnoreCase("cashloan")) {
      return InstalmentChannel.CashLoan;
    }

    throw new RuntimeException("Cannot get application channel.");
  }
  
  //dsp动态开关抽取公共资源
  public Boolean isOpenSwitch(String switchName){
	  if (DynamicConfig.read(switchName, JobHandlerSwitch.Off.getValue())
				.equals(JobHandlerSwitch.Off.getValue())) {
			return false;
		}else{
			return true;
		}
  }
}
