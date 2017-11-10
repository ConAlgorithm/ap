package scheduler;

import java.util.LinkedHashMap;

import scheduler.activities.CheckAdditionalContactActivity;
import scheduler.activities.CheckUserActivity;
import scheduler.activities.ConditionActivity;
import scheduler.activities.DelayJobActivity;
import scheduler.activities.DummyActivity;
import scheduler.activities.DynamicActivity;
import scheduler.activities.EventWaitingActivity;
import scheduler.activities.JobActivity;
import scheduler.activities.LoopActivity;
import scheduler.activities.NonBlockingActivity;
import scheduler.activities.NotificationActivity;
import scheduler.activities.OnsNotificationActivity;
import scheduler.activities.ParallelActivity;
import scheduler.activities.SequentialActivity;
import scheduler.activities.SplitActivity;
import scheduler.predicates.FeedbackCheckPredicate;
import scheduler.predicates.FeedbackPassPredicate;
import scheduler.predicates.FraudManualCheckPredicate;
import scheduler.predicates.LoopPredicate;
import scheduler.predicates.ResultMatchPredicate;
import catfish.base.DynamicConfig;
import catfish.base.business.common.Gender;
import catfish.base.business.common.JobStatus;
import catfish.base.business.execution.ExecutionType;
import catfish.base.queue.QueueMessager;
import catfish.base.queue.QueueMessages;

public class ActivitiesBuilder {

  public static Activity buildHavingAutoPreApproved(
      String appId, int additionalContacts, int jobScene, FinishedHandler handler) {
    int recheckCallDelay = DynamicConfig.readAsInt(
        DomainConsts.RECHECK_CALL_DELAY, DomainConsts.DEFAULT_RECHECK_CALL_DELAY);
    int maxCallRechecks = DynamicConfig.readAsInt(
        DomainConsts.MAX_CALL_CHECKS, DomainConsts.DEFAULT_MAX_CALL_CHECKS);

    Activity activity =
        new SequentialActivity(
            new ParallelActivity(
                new NotificationActivity(appId, QueueMessages.APPLICATION_SUBMITTED),
                new NotificationActivity(appId, DomainConsts.TRANSACTION_MONITOR), //transaction supervise
                new NotificationActivity(appId, DomainConsts.CALCULATE_NETWORK_RELATIONSHIP),
                new JobActivity(appId, DomainConsts.CHECK_PERSONAL_INFO),
                new JobActivity(appId, DomainConsts.CHECK_COMPANY),
                buildLoopActivityWithDelay(
                    appId, DomainConsts.CHECK_FIRST_CONTACT, recheckCallDelay, maxCallRechecks),
                buildLoopActivityWithDelay(
                    appId, DomainConsts.CHECK_SECOND_CONTACT, recheckCallDelay, maxCallRechecks),
                additionalContacts == 0
                    ? new DummyActivity()
                    : buildLoopActivityWithDelay(
                        appId, DomainConsts.CHECK_THIRD_CONTACT, recheckCallDelay, maxCallRechecks),
                additionalContacts <= 1
                    ? new DummyActivity()
                    : buildCheckAdditionalContactActivity(
                        appId, additionalContacts - 1, recheckCallDelay, maxCallRechecks),
                jobScene == ExecutionType.WeiXin.getValue().intValue()
                    ? new SequentialActivity(
                        buildRecursiveClassifyActivities(appId, 1),
                        new ParallelActivity(
                            new ConditionActivity(
                                    new JobActivity(appId,DomainConsts.CHECK_COURT_EXECUTED_EXIST),
                                    new LinkedMapBuilder()
                                      .add(new ResultMatchPredicate("ManualCheckRequired"),
                                    		  new JobActivity(appId, DomainConsts.CHECK_COURT_EXECUTED)).build(),
                                    new DummyActivity()),
                            new ConditionActivity(
                                    new JobActivity(appId,DomainConsts.CHECK_USER_GENDER),
                                    new LinkedMapBuilder()
                                      .add(new ResultMatchPredicate(Gender.Male.getValue()),
                                    		  new JobActivity(appId, DomainConsts.CHECK_HOME_CREDIT_FOR_MALE)).build(),
                                    new JobActivity(appId, DomainConsts.CHECK_HOME_CREDIT_FOR_FEMALE)),
                            new JobActivity(appId, DomainConsts.CHECK_IMAGE),
                            new JobActivity(appId, DomainConsts.CHECK_BANK_CARD)))
                    : buildAppFirstImageChecks(appId)),
            new NotificationActivity(appId, DomainConsts.ENGINE_FIRST_CHECK));

    activity.setFinishedHandler(handler);
    return activity;
  }

  private static Activity buildAppFirstImageChecks(final String appId) {
    return new DynamicActivity(
        new JobActivity(appId, DomainConsts.ENGINE_PHOTO_CHECK, "1" /* count */),
        new FollowingActivityCreator() {
          @Override
          public Activity create(String baseJobResult) {
            int uploadStatus = Integer.parseInt(baseJobResult);       // 4 or 7
            return new ParallelActivity(
                new SequentialActivity(
                    (uploadStatus & 3) > 0
                        ? new ParallelActivity(
                            new EventWaitingActivity(appId, DomainConsts.HEAD_PHOTO_SUBMITTED),
                            new EventWaitingActivity(appId, DomainConsts.ID_PHOTO_SUBMITTED))
                        : new DummyActivity(),
                    new ParallelActivity(
                    	new ConditionActivity(
                            new JobActivity(appId,DomainConsts.CHECK_COURT_EXECUTED_EXIST),
                            new LinkedMapBuilder()
                            .add(new ResultMatchPredicate("ManualCheckRequired"),
                                new JobActivity(appId, DomainConsts.CHECK_COURT_EXECUTED)).build(),
                            new DummyActivity()),
                        new ConditionActivity(
                            new JobActivity(appId,DomainConsts.CHECK_USER_GENDER),
                            new LinkedMapBuilder()
                            .add(new ResultMatchPredicate(Gender.Male.getValue()),
                                new JobActivity(appId, DomainConsts.CHECK_HOME_CREDIT_FOR_MALE)).build(),
                                new JobActivity(appId, DomainConsts.CHECK_HOME_CREDIT_FOR_FEMALE)),
                        new JobActivity(appId, DomainConsts.CHECK_IMAGE))),
                new SequentialActivity(
                    new EventWaitingActivity(appId, DomainConsts.BANK_CARD_PHOTO_SUBMITTED),
                    new JobActivity(appId, DomainConsts.CHECK_BANK_CARD)),
                new OnsNotificationActivity(
                    appId, DomainConsts.UPLOAD_FILES, String.valueOf(uploadStatus)));
          }
        });
  }

  public static Activity buildHavingManualPreCheckPassed(
      String appId, int jobScene, FinishedHandler handler) {
    int percentageOfCheckByMachine = DynamicConfig.readAsInt(DomainConsts.CHECK_RATE_BY_MACHINE, 0);

    Activity activity =
        new SequentialActivity(
            new NotificationActivity(appId, DomainConsts.UBT_DATABASE_WRITING),
            new ParallelActivity(
                new CheckUserActivity(appId, DomainConsts.CHECK_USER),
                new JobActivity(appId, DomainConsts.INPUT_ID_CARD_INFO)),
            new ConditionActivity(
                new SequentialActivity(
                	new ParallelActivity(
                			new JobActivity(appId, DomainConsts.CHECK_FRAUD),
                			new JobActivity(appId, DomainConsts.CALCULATE_MERCHANT_VIEW_STATISTICS)),
        			new ConditionActivity(
        	                new DummyActivity(),
        	                new LinkedMapBuilder()
        	                    .add(new FeedbackPassPredicate(appId, JobStatus.D1Feedbacked),
        	                      new DummyActivity())
        	                    .build(),
        	                new EventWaitingActivity(appId,DomainConsts.CHECK_D1_FEEDBACK_PASS)),
                    new SplitActivity(
                        appId,
                    		new JobActivity(appId, DomainConsts.CHECK_WHOLE_PROCESS_BY_MACHINE),
                    		new SequentialActivity(
                    				new NotificationActivity(appId, DomainConsts.RECORD_WHOLE_PROCESS_BY_MACHINE),
                                    new JobActivity(appId, DomainConsts.CHECK_WHOLE_PROCESS)), percentageOfCheckByMachine)),
                new LinkedMapBuilder()
                    .add(new ResultMatchPredicate(DomainConsts.APPROVED),
                        buildCheckPostApprovalEvidences(appId, jobScene))
                    .build(),
                new DummyActivity()));

    activity.setFinishedHandler(handler);
    return activity;
  }

  private static Activity buildCheckMerchantAndLoanActivity(String appId) {
    int recheckCallDelay = DynamicConfig.readAsInt(
        DomainConsts.RECHECK_CALL_DELAY, DomainConsts.DEFAULT_RECHECK_CALL_DELAY);
    int maxCallRechecks = DynamicConfig.readAsInt(
        DomainConsts.MAX_CALL_CHECKS, DomainConsts.DEFAULT_MAX_CALL_CHECKS);

    Activity loanActivity = new SequentialActivity(

    new ConditionActivity(
        new JobActivity(appId, DomainConsts.AUTO_PAYMENT),
        new LinkedMapBuilder()
            .add(new ResultMatchPredicate(DomainConsts.MANUALPAYMENT_REQUIRED),
            	 new ConditionActivity(
            	     new JobActivity(appId, DomainConsts.LOAN_MONEY),
            	     new LinkedMapBuilder()
            	         .add(new ResultMatchPredicate(DomainConsts.FAILED),
            	              new JobActivity(appId, DomainConsts.FINISH_APPLICATION))
            	         .build(),
            	     new SequentialActivity(
            	         new NotificationActivity(appId, DomainConsts.APPMONEY_TRANSFERRED),
            	         new NotificationActivity(appId, QueueMessages.APPLICATION_COMPLETED))))
            .build(),
        new SequentialActivity(
    	    new NotificationActivity(appId, DomainConsts.APPMONEY_TRANSFERRED),
    	    new NotificationActivity(appId, QueueMessages.APPLICATION_COMPLETED))),
        new DummyActivity());

    return new SequentialActivity(
    	new NonBlockingActivity(
    			new SequentialActivity(
             			new ConditionActivity(
            	                new DummyActivity(),
            	                new LinkedMapBuilder()
            	                    .add(new FeedbackCheckPredicate(appId, JobStatus.D1Feedbacked),
            	                      new DummyActivity())
            	                    .build(),
            	                new EventWaitingActivity(appId,DomainConsts.CHECK_D1_FEEDBACK)),
             			new JobActivity(appId, DomainConsts.CHECK_D1_FEEDBACK))
    			),
        buildLoopActivityWithDelay(
            appId, DomainConsts.CHECK_MERCHANT, recheckCallDelay, maxCallRechecks),
        new ConditionActivity(
            new JobActivity(appId, DomainConsts.ENGINE_LOAN_CHECK),
            new LinkedMapBuilder()
                .add(new ResultMatchPredicate(DomainConsts.APPROVED),
                    new SequentialActivity(

                        new ConditionActivity(
                            new DummyActivity(),
                            new LinkedMapBuilder()
                              .add(new FraudManualCheckPredicate(appId, JobStatus.Paused),
                          new ConditionActivity(
                              new EventWaitingActivity(appId,DomainConsts.TRANSACTION_MONITOR),
                              new LinkedMapBuilder()
                                .add(new ResultMatchPredicate(DomainConsts.APPROVED),
                                     loanActivity).build(),
                              new DummyActivity())
                          )
                              .build(),
                              loanActivity)))
                 .build(),
            new DummyActivity()));
  }

  public static Activity buildHavingManualRecheckNeeded(
      String appId, int uploadStatus, int jobScene, FinishedHandler handler) {
    Activity activity = new SequentialActivity(
        new ParallelActivity(
            (uploadStatus & 3) > 0
                ? new SequentialActivity(
                    new ParallelActivity(
                        (uploadStatus & 1) > 0
                            ? new EventWaitingActivity(appId, DomainConsts.HEAD_PHOTO_SUBMITTED)
                            : new DummyActivity(),
                        (uploadStatus & 2) > 0
                            ? new EventWaitingActivity(appId, DomainConsts.ID_PHOTO_SUBMITTED)
                            : new DummyActivity()),
                    new JobActivity(appId, DomainConsts.RECHECK_IMAGE))
                : new DummyActivity(),
            (uploadStatus & 4) > 0
                ? new SequentialActivity(
                    new EventWaitingActivity(appId, DomainConsts.BANK_CARD_PHOTO_SUBMITTED),
                    new JobActivity(appId, DomainConsts.RECHECK_BANK_CARD))
                : new DummyActivity(),
            jobScene == ExecutionType.WeiXin.getValue().intValue()
                ? new OnsNotificationActivity(
                    appId, DomainConsts.REUPLOAD_FILES, String.valueOf(uploadStatus))
                : new OnsNotificationActivity(
                    appId,
                    DomainConsts.UPLOAD_FILES,
                    String.valueOf(uploadStatus | DomainConsts.BIT_REUPLOAD))),
        new NotificationActivity(appId, DomainConsts.ENGINE_RECHECK));

    activity.setFinishedHandler(handler);
    return activity;
  }

  public static Activity buildLoopActivityWithDelay(
      String appId, String name, int delay, int times) {
    return new ConditionActivity(
        new JobActivity(appId, name),
        new LinkedMapBuilder()
            .add(new ResultMatchPredicate(DomainConsts.RECHECKING_REQUIRED),
                new LoopActivity(
                    new DelayJobActivity(appId, name, delay),
                    new LoopPredicate(DomainConsts.RECHECKING_REQUIRED, times - 1),
                    new DummyActivity()))
            .build(),
        new DummyActivity());
  }

  public static Activity buildCheckAdditionalContactActivity(
      String appId, int additionalContacts, int recheckCallDelay, int maxCallRechecks) {
    Activity[] activities = new Activity[additionalContacts];
    for (int i = 0; i < additionalContacts; i++) {
      activities[i] = new ConditionActivity(
          new CheckAdditionalContactActivity(appId, DomainConsts.CHECK_ADDITIONAL_CONTACT, i, 0),
          new LinkedMapBuilder()
              .add(new ResultMatchPredicate(DomainConsts.RECHECKING_REQUIRED),
                  new LoopActivity(
                      new CheckAdditionalContactActivity(
                          appId, DomainConsts.CHECK_ADDITIONAL_CONTACT, i, recheckCallDelay),
                      new LoopPredicate(DomainConsts.RECHECKING_REQUIRED, maxCallRechecks - 1),
                      new DummyActivity()))
              .build(),
          new DummyActivity());
    }
    return new ParallelActivity(activities);
  }

  public static Activity buildWelcomeCallActivity(
      String appId, String jobName, FinishedHandler handler) {
    int welcomeCallDelay = DynamicConfig.readAsInt(
        DomainConsts.WELCOME_CALL_DELAY, DomainConsts.DEFAULT_WELCOME_CALL_DELAY);
    int maxWelcomeCalls = DynamicConfig.readAsInt(
        DomainConsts.MAX_WELCOME_CALLS, DomainConsts.DEFAULT_MAX_WELCOME_CALLS);

    Activity activity = buildLoopActivityWithDelay(
        appId, jobName, welcomeCallDelay, maxWelcomeCalls);
    activity.setFinishedHandler(handler);
    return activity;
  }

  public static Activity buildRepaymentRemindActivity(
      String appId, String jobName, FinishedHandler handler) {
    int repaymentRemindDelay = DynamicConfig.readAsInt(
        DomainConsts.REPAYMENT_REMIND_DELAY, DomainConsts.DEFAULT_REPAYMENT_REMIND_DELAY);
    int maxRepaymentReminds = DynamicConfig.readAsInt(
        DomainConsts.MAX_REPAYMENT_REMINDS, DomainConsts.DEFAULT_MAX_REPAYMENT_REMINDS);

    Activity activity = buildLoopActivityWithDelay(
        appId, jobName, repaymentRemindDelay, maxRepaymentReminds);
    activity.setFinishedHandler(handler);
    return activity;
  }

  public static Activity buildOverdueRepaymentRemindActivity(String appId, FinishedHandler handler) {
    int overdueRepaymentRemindDelay = DynamicConfig.readAsInt(
        DomainConsts.OVERDUE_REPAYMENT_REMIND_DELAY, DomainConsts.DEFAULT_OVERDUE_REPAYMENT_REMIND_DELAY);
    int maxOverdueRepaymentReminds = DynamicConfig.readAsInt(
        DomainConsts.MAX_OVERDUE_REPAYMENT_REMINDS, DomainConsts.DEFAULT_MAX_OVERDUE_REPAYMENT_REMINDS);
    Activity activity = buildLoopActivityWithDelay(
        appId, DomainConsts.OVERDUE_REPAYMENT_REMIND, overdueRepaymentRemindDelay, maxOverdueRepaymentReminds);
    activity.setFinishedHandler(handler);
    return activity;
  }

  private static Activity buildRecursiveClassifyActivities(final String appId, final int count) {
    return new DynamicActivity(
        new SequentialActivity(
                count > 1
                ? new JobActivity(appId, DomainConsts.SET_IMAGE_UPLOAD_FIRST_STAGE_UNREADY)
                : new DummyActivity(),
                new JobActivity(appId, DomainConsts.ENGINE_PHOTO_CHECK, Integer.toString(count))
        ),
        new FollowingActivityCreator() {
          @Override
          public Activity create(String baseJobResult) {
            int uploadStatus = Integer.parseInt(baseJobResult);
            return uploadStatus == 0
                ? (count > 1
                   ? new JobActivity(appId, DomainConsts.SET_IMAGE_UPLOAD_FIRST_STAGE_READY)
                   : new DummyActivity())
                : new SequentialActivity(
                        (count > 1
                        ? new JobActivity(appId, DomainConsts.SET_IMAGE_UPLOAD_FIRST_STAGE_READY)
                        : new DummyActivity()),
                        buildClassifyActivities(appId, uploadStatus, count),
                        buildRecursiveClassifyActivities(appId, count + 1));
          }
        });
  }

  private static Activity buildClassifyActivities(String appId, int uploadStatus, int count) {
    return new ParallelActivity(
        (uploadStatus & 1) > 0
            ? buildDynamicClassifyActivity(appId, DomainConsts.HEAD_PHOTO_SUBMITTED, "1")
            : new DummyActivity(),
        (uploadStatus & 2) > 0
            ? buildDynamicClassifyActivity(appId, DomainConsts.ID_PHOTO_SUBMITTED, "2")
            : new DummyActivity(),
        (uploadStatus & 4) > 0
            ? buildDynamicClassifyActivity(appId, DomainConsts.BANK_CARD_PHOTO_SUBMITTED, "4")
            : new DummyActivity(),
        new SequentialActivity(
            count == 1
            ? new JobActivity(appId, DomainConsts.SET_IMAGE_UPLOAD_FIRST_STAGE_READY)
            : new DummyActivity(),
            new OnsNotificationActivity(
                appId,
                count == 1 ? DomainConsts.PRE_APPROVE_AND_UPLOAD_FILES : DomainConsts.REUPLOAD_FILES,
                String.valueOf(uploadStatus))
            )
        );
  }

  private static Activity buildDynamicClassifyActivity(
      final String appId, String event, final String identity) {
    return new DynamicActivity(
        new EventWaitingActivity(appId, event),
        new FollowingActivityCreator() {
          @Override
          public Activity create(String baseJobResult) {
            return new JobActivity(appId, DomainConsts.CLASSIFY_PHOTO + identity, baseJobResult);
          }
        });
  }

  private static Activity buildCheckPostApprovalEvidences(String appId, int jobScene) {
    int allStatus = DomainConsts.BIT_IOU | DomainConsts.BIT_BUCKLE;
    return new ConditionActivity(
        new JobActivity(
            appId, DomainConsts.ENGINE_POST_APPROVAL_EVIDENCE_REQUIREMENT_CHECK, "FirstCheck"),
        new LinkedMapBuilder()
            .add(new ResultMatchPredicate(String.valueOf(allStatus)),
                jobScene == ExecutionType.WeiXin.getValue().intValue()
                    ? buildCheckPostApprovalEvidences(appId, allStatus, allStatus, true)
                    : buildCheckAppPostAprovalEvidences(appId, allStatus, 1))
            .build(),
        buildCheckIouActivity(appId, 1, jobScene));
  }

  private static Activity buildCheckAppPostAprovalEvidences(
      final String appId, int status, final int count) {
    return new SequentialActivity(
        new ParallelActivity(
            (status & DomainConsts.BIT_IOU) > 0
                ? new SequentialActivity(
                    new EventWaitingActivity(appId, DomainConsts.IOU_SUBMITTED),
                    new JobActivity(appId, DomainConsts.CHECK_IOU))
                : new DummyActivity(),
            (status & DomainConsts.BIT_BUCKLE) > 0
                ? new SequentialActivity(
                    new EventWaitingActivity(appId, DomainConsts.BUCKLE_SUBMITTED),
                    new JobActivity(appId, DomainConsts.CHECK_BUCKLE))
                : new DummyActivity(),
            new OnsNotificationActivity(
                appId,
                DomainConsts.UPLOAD_FILES,
                count == 1
                    ? String.valueOf(status & ~DomainConsts.BIT_REUPLOAD)
                    : String.valueOf(status | DomainConsts.BIT_REUPLOAD))),
        new DynamicActivity(
            new JobActivity(appId, DomainConsts.ENGINE_POST_APPROVAL_EVIDENCE_CHECK),
            new FollowingActivityCreator() {
              @Override
              public Activity create(String result) {
                switch (result) {
                case DomainConsts.APPROVED:
                  return buildCheckMerchantAndLoanActivity(appId);
                case DomainConsts.REJECTED:
                  return new DummyActivity();
                default:    // e.g., RecheckingRequired_16
                  int failedStatus = Integer.parseInt(result.split("_")[1]);
                  return buildCheckAppPostAprovalEvidences(appId, failedStatus, count + 1);
                }
              }
            }));
  }

  private static Activity buildCheckPostApprovalEvidences(
      final String appId, final int checkStatus, int uploadStatus, final boolean firstCheck) {
    return new SequentialActivity(
        new ParallelActivity(
            (uploadStatus & DomainConsts.BIT_IOU) > 0
                ? buildDynamicClassifyActivity(
                    appId, DomainConsts.IOU_SUBMITTED, String.valueOf(DomainConsts.BIT_IOU))
                : new DummyActivity(),
            (uploadStatus & DomainConsts.BIT_BUCKLE) > 0
                ? buildDynamicClassifyActivity(
                    appId, DomainConsts.BUCKLE_SUBMITTED, String.valueOf(DomainConsts.BIT_BUCKLE))
                : new DummyActivity(),
            buildUploadPostApprovalEvidences(appId, uploadStatus, firstCheck)),
        new DynamicActivity(
            new JobActivity(
                appId,
                DomainConsts.ENGINE_POST_APPROVAL_EVIDENCE_REQUIREMENT_CHECK,
                "FollowingCheck"),
            new FollowingActivityCreator() {
              @Override
              public Activity create(String baseJobResult) {
                int newUploadStatus = Integer.parseInt(baseJobResult);
                return newUploadStatus == 0
                    ? buildCheckPostApprovalEvidenceManualJobs(appId, checkStatus)
                    : buildCheckPostApprovalEvidences(appId, checkStatus, newUploadStatus, false);
              }
            }));
  }

  private static Activity buildUploadPostApprovalEvidences(
		  final String appId,
		  int uploadStatus,
		  final boolean firstCheck)
  {
	  if (firstCheck)
	  {
		  return new SequentialActivity(
		          new JobActivity(appId, DomainConsts.SET_IMAGE_UPLOAD_SECOND_STAGE_READY),
		          new OnsNotificationActivity(appId, DomainConsts.APPROVE_AND_UPLOAD_IOU,  null));
	  }

	  if ((uploadStatus & DomainConsts.BIT_IOU) == 0 && (uploadStatus & DomainConsts.BIT_BUCKLE) > 0)
	  {
		  return new OnsNotificationActivity(appId,DomainConsts.UPLOAD_BUCKLE,Integer.toString(41));
	  }

	  return new OnsNotificationActivity(appId,DomainConsts.REUPLOAD_IOU,null);
  }

  private static Activity buildCheckPostApprovalEvidenceManualJobs(final String appId, int status) {
    return new SequentialActivity(
        new ParallelActivity(
            (status & DomainConsts.BIT_IOU) > 0
                ? new JobActivity(appId, DomainConsts.CHECK_IOU)
                : new DummyActivity(),
            (status & DomainConsts.BIT_BUCKLE) > 0
                ? new JobActivity(appId, DomainConsts.CHECK_BUCKLE)
                : new DummyActivity()),
        new DynamicActivity(
            new JobActivity(appId, DomainConsts.ENGINE_POST_APPROVAL_EVIDENCE_CHECK),
            new FollowingActivityCreator() {
              @Override
              public Activity create(String result) {
                switch (result) {
                case DomainConsts.APPROVED:
                  return buildCheckMerchantAndLoanActivity(appId);
                case DomainConsts.REJECTED:
                  return new DummyActivity();
                default:    // e.g., RecheckingRequired_16
                  int failedStatus = Integer.parseInt(result.split("_")[1]);
                  return buildCheckPostApprovalEvidences(appId, failedStatus, failedStatus, false);
                }
              }
            }));
  }

  private static Activity buildCheckIouActivity(
      final String appId, final int count, final int jobScene) {
    String notification;
    String status;

    if (jobScene == ExecutionType.WeiXin.getValue().intValue()) {
      notification = count == 1 ? DomainConsts.APPROVE_AND_UPLOAD_IOU : DomainConsts.REUPLOAD_IOU;
      status = null;
    } else {
      notification = DomainConsts.UPLOAD_FILES;
      status = count == 1
          ? String.valueOf(DomainConsts.BIT_IOU)
          : String.valueOf(DomainConsts.BIT_IOU | DomainConsts.BIT_REUPLOAD);
    }

    return new DynamicActivity(
        new SequentialActivity(
            count == 1 && jobScene == ExecutionType.WeiXin.getValue().intValue()
            ? new JobActivity(appId, DomainConsts.SET_IMAGE_UPLOAD_SECOND_STAGE_READY)
            : new DummyActivity(),
            new OnsNotificationActivity(appId, notification, status),
            new EventWaitingActivity(appId, DomainConsts.IOU_SUBMITTED),
            new JobActivity(appId, DomainConsts.CHECK_IOU),
            new JobActivity(appId, DomainConsts.ENGINE_IOU_CHECK)),
        new FollowingActivityCreator() {
          @Override
          public Activity create(String baseJobResult) {
            switch (baseJobResult) {
            case DomainConsts.RECHECKING_REQUIRED:
              return buildCheckIouActivity(appId, count + 1, jobScene);
            case DomainConsts.APPROVED:
              return buildCheckMerchantAndLoanActivity(appId);
            default:      // rejected
              return new DummyActivity();
            }
          }
        });
  }

  private static class LinkedMapBuilder {
    private LinkedHashMap<Predicate, Activity> map = new LinkedHashMap<>();

    public LinkedMapBuilder add(Predicate predicate, Activity activity) {
      map.put(predicate, activity);
      return this;
    }

    public LinkedHashMap<Predicate, Activity> build() {
      return map;
    }
  }

  public static Activity buildPaybackEntryQuestionActivity(QueueMessager messager, FinishedHandler handler)
  {
	  JobActivity job = new JobActivity(messager.getAppId(), DomainConsts.PAYBACK_ENTRY_QUESTION, messager.getJobStatus());
	  job.setFinishedHandler(handler);
	  return job;
  }
}
