package scheduler;

import cache.FeedbackCheckHolder;
import cache.FeedbackPassHolder;
import cache.FraudManualCheckCacheHolder;
import catfish.base.Logger;
import catfish.base.persistence.queue.MessageSource;
import catfish.base.persistence.queue.PersistenceQueueApi;
import catfish.base.queue.QueueMessager;

public class EventTracker {

  @SuppressWarnings("null")
  public static void track() {
    while (true) {
      try {
      final QueueMessager messager = PersistenceQueueApi.consumeMessager(
          DomainConsts.STATUS_QUEUE + Configuration.getConsumingQueueSuffix(),
          MessageSource.JobScheduler_V2);

      if (messager.getJobName().equals(DomainConsts.NAME_IDCARD_MATCH_CHECK_APPROVED)) {
        ActivitiesBuilder.buildHavingAutoPreApproved(
            messager.getAppId(),
            messager.getJobDataInt(),
            messager.getJobScene(),
            new FinishedHandler() {
              @Override
              public void execute(String result) {
                Logger.get().info(messager.getAppId() + ": tracked to BEFORE MANUAL JOB CHECK");
              }
            }).trigger();
      } else if (messager.getJobName().equals(DomainConsts.MANUAL_FIRST_CHECK_DONE) && messager.getJobDataInt() != 0) {
        ActivitiesBuilder.buildHavingManualRecheckNeeded(
            messager.getAppId(),
            messager.getJobDataInt(),
            messager.getJobScene(),
            new FinishedHandler() {
              @Override
              public void execute(String result) {
                Logger.get().info(messager.getAppId() + ": tracked to BEFORE MANUAL JOB RECHECK");
              }
            }).trigger();
      } else if ((messager.getJobName().equals(DomainConsts.MANUAL_FIRST_CHECK_DONE) && messager.getJobDataInt() == 0)
          || messager.getJobName().equals(DomainConsts.MANUAL_RECHECK_DONE)) {
        ActivitiesBuilder.buildHavingManualPreCheckPassed(
            messager.getAppId(),
            messager.getJobScene(),
            new FinishedHandler() {
              @Override
              public void execute(String result) {
                Logger.get().info(messager.getAppId() + ": FINISHED tracking");
              }
            }).trigger();
      } else if (messager.getJobName().equals(DomainConsts.WELCOME_CALL_EVENT)
          || messager.getJobName().equals(DomainConsts.WELCOME_CALL_EVENT_FOR_PDL)) {
        ActivitiesBuilder.buildWelcomeCallActivity(
            messager.getAppId(),
            messager.getJobName().equals(DomainConsts.WELCOME_CALL_EVENT)
                ? DomainConsts.WELCOME_CALL
                : DomainConsts.WELCOME_CALL_FOR_PDL,
            new FinishedHandler() {
              @Override
              public void execute(String result) {
                Logger.get().info(messager.getAppId() + ": WELCOME CALL done");
              }
            }).trigger();
      } else if (messager.getJobName().equals(DomainConsts.REPAYMENT_REMIND_EVENT)
          || messager.getJobName().equals(DomainConsts.REPAYMENT_REMIND_EVENT_FOR_PDL)) {
        ActivitiesBuilder.buildRepaymentRemindActivity(
            messager.getAppId(),
            messager.getJobName().equals(DomainConsts.REPAYMENT_REMIND_EVENT)
                ? DomainConsts.REPAYMENT_REMIND
                : DomainConsts.REPAYMENT_REMIND_FOR_PDL,
            new FinishedHandler() {
              @Override
              public void execute(String result) {
                Logger.get().info(messager.getAppId() + ": REPAYMENT REMIND done");
              }
            }).trigger();
      } else if (messager.getJobName().equals(DomainConsts.OVERDUE_REPAYMENT_REMIND_EVENT)) {
          ActivitiesBuilder.buildOverdueRepaymentRemindActivity(messager.getAppId(), new FinishedHandler() {
              @Override
              public void execute(String result) {
                Logger.get().info(messager.getAppId() + ": OVERDUE REPAYMENT REMIND done");
              }
        }).trigger();
      } else if(messager.getJobName().equals(DomainConsts.PAYBACK_ENTRY_QUESTION_SUBMITTED)){
    	  ActivitiesBuilder.buildPaybackEntryQuestionActivity(messager, new FinishedHandler(){

			@Override
			public void execute(String result) {
				Logger.get().info(messager.getAppId() + ": PaybackEntryQuestion Job Done");
			}

    	  }).trigger();
      } else {
    	  // update the status of fraud manual check
    	  if(messager.getJobName().equals(DomainConsts.TRANSACTION_MONITOR)){
    		  FraudManualCheckCacheHolder.addApplicationStatus(messager.getAppId(), messager.getJobStatus());
    	  }
    	  if(messager.getJobName().equals(DomainConsts.CHECK_D1_FEEDBACK)){
    		  FeedbackCheckHolder.addApplicationStatus(messager.getAppId(), messager.getJobStatus());
    	  }
    	  if(messager.getJobName().equals(DomainConsts.CHECK_D1_FEEDBACK_PASS)){
          FeedbackPassHolder.addApplicationStatus(messager.getAppId(), messager.getJobStatus());
        }
        EventWaiter waiter = EventWaiterRepository.pop(messager.getAppId(), messager.getJobName());
        //The waiter is not necessary in case of check fraud manually, since the job status was saved in the cache and queried later
        if (waiter == null) {
        	if(!messager.getJobName().equals(DomainConsts.TRANSACTION_MONITOR)
        	    && !messager.getJobName().equals(DomainConsts.CHECK_D1_FEEDBACK)
        	    && !messager.getJobName().equals(DomainConsts.CHECK_D1_FEEDBACK_PASS)){
            Logger.get().fatal(String.format("ERROR no waiter for job %s %s", messager.getAppId(), messager.getJobName()));
          }
        } else {
          waiter.receive(messager.getJobStatus());
        }
      }

      } catch (Exception e) {
        Logger.get().fatal("Unexpected exception occurred.", e);
      }
    }
  }
}
