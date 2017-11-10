package scheduler;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.queue.QueueMessages;

public class DomainConsts {

  // configurable variables
  public static final String RECHECK_CALL_DELAY = "RecheckCallDelay";
  public static final String MAX_CALL_CHECKS = "MaxCallChecks";
  public static final String WELCOME_CALL_DELAY = "WelcomeCallDelay";
  public static final String MAX_WELCOME_CALLS = "MaxWelcomeCalls";
  public static final String REPAYMENT_REMIND_DELAY = "RepaymentRemindDelay";
  public static final String MAX_REPAYMENT_REMINDS = "MaxRepaymentReminds";
  public static final String OVERDUE_REPAYMENT_REMIND_DELAY = "OverdueRepaymentRemindDelay";
  public static final String MAX_OVERDUE_REPAYMENT_REMINDS = "MaxOverdueRepaymentReminds";

  // default values for configurable variables
  public static final int DEFAULT_RECHECK_CALL_DELAY = 180;        // 3 minutes
  public static final int DEFAULT_WELCOME_CALL_DELAY = 14400;      // 4 hours
  public static final int DEFAULT_REPAYMENT_REMIND_DELAY = 14400;
  public static final int DEFAULT_OVERDUE_REPAYMENT_REMIND_DELAY = 14400;
  public static final int DEFAULT_MAX_CALL_CHECKS = 3;
  public static final int DEFAULT_MAX_WELCOME_CALLS = 3;
  public static final int DEFAULT_MAX_REPAYMENT_REMINDS = 3;
  public static final int DEFAULT_MAX_OVERDUE_REPAYMENT_REMINDS = 3;

  // bits
  public static final int BIT_IOU = 1 << 4;
  public static final int BIT_BUCKLE = 1 << 5;
  public static final int BIT_REUPLOAD = 1 << 10;

  // events
  public static final String HEAD_PHOTO_SUBMITTED = "HeadPhotoSubmitted";
  public static final String ID_PHOTO_SUBMITTED = "IdPhotoSubmitted";
  public static final String BANK_CARD_PHOTO_SUBMITTED = "BankCardPhotoSubmitted";
  public static final String ALL_FILES_SUBMITTED = "AllFilesSubmitted";
  public static final String SWEAR_VOICE_SUBMITTED = "SwearVoiceSubmitted";
  public static final String IOU_SUBMITTED = "IOUSubmitted";
  public static final String BUCKLE_SUBMITTED = "BuckleSubmitted";
  public static final String ADDITIONAL_CONTACTS_SUBMITTED = "AdditionalContactsSubmitted";
  public static final String NAME_IDCARD_MATCH_CHECK_APPROVED = "NameIDCardMatchCheckApproved";
  public static final String MANUAL_FIRST_CHECK_DONE = "ManualFirstCheckDone";
  public static final String MANUAL_RECHECK_DONE = "ManualRecheckDone";
  public static final String WELCOME_CALL_EVENT = "WelcomeCallEvent";
  public static final String REPAYMENT_REMIND_EVENT = "RepaymentRemindEvent";
  public static final String WELCOME_CALL_EVENT_FOR_PDL = "WelcomeCallEventForPDL";
  public static final String REPAYMENT_REMIND_EVENT_FOR_PDL = "RepaymentRemindEventForPDL";
  public static final String OVERDUE_REPAYMENT_REMIND_EVENT = "OverdueRepaymentRemindEvent";
  public static final String PAYBACK_ENTRY_QUESTION_SUBMITTED = "PaybackEntryQuestionSubmitted";
  // automatic activities
  public static final String CALCULATE_MERCHANT_VIEW_STATISTICS = "CalculateMerchantViewStatistics";
  public static final String CALCULATE_NETWORK_RELATIONSHIP = "UpdateGraphByApplication";
  public static final String CONVERT_SWEAR_VOICE = "ConvertSwearVoice";
  public static final String UBT_DATABASE_WRITING = "UbtDBWriting";
  public static final String CHECK_COURT_EXECUTED_EXIST = "CheckCourtExecutedExist";
  public static final String CHECK_USER_GENDER = "CheckUserGender";

  // manual activities
  public static final String CLASSIFY_PHOTO = "ClassifyPhoto";
  public static final String CHECK_IMAGE = "CheckImage";
  public static final String CHECK_BANK_CARD = "CheckBankCard";
  public static final String CHECK_VOICE = "CheckVoice";
  public static final String CHECK_IOU = "CheckIOU";
  public static final String CHECK_BANK_REFERENCE = "CheckBankReference";
  public static final String CHECK_PERSONAL_INFO = "CheckPersonalInfo";
//  public static final String CHECK_HOME_CREDIT_FOR_MALE =
//      "CheckHomeCreditAndCourtExecuted";
  public static final String CHECK_HOME_CREDIT_FOR_MALE = "CheckHomeCreditForMale";
  public static final String CHECK_HOME_CREDIT_FOR_FEMALE = "CheckHomeCreditForFemale";
  public static final String CHECK_COURT_EXECUTED = "CheckCourtExecuted";
  public static final String CHECK_FIRST_CONTACT = "CheckFirstContact";
  public static final String CHECK_SECOND_CONTACT = "CheckSecondContact";
  public static final String CHECK_THIRD_CONTACT = "CheckThirdContact";
  public static final String CHECK_COMPANY = "CheckCompany";
  public static final String RECHECK_IMAGE = "RecheckImage";
  public static final String RECHECK_BANK_CARD = "RecheckBankCard";
  public static final String RECHECK_VOICE = "RecheckVoice";
  public static final String CHECK_VOICE_REPEATEDLY = "CheckVoiceRepeatedly";

  public static final String CHECK_USER = "CheckUser";
  public static final String CHECK_ADDITIONAL_CONTACT = "CheckAdditionalContact";
  public static final String INPUT_ID_CARD_INFO = "InputIdCardInfo";
  public static final String CHECK_D1_FEEDBACK="CheckD1Feedback";
  public static final String CHECK_D1_FEEDBACK_PASS="CheckD1FeedbackPass";
  public static final String CHECK_FRAUD = "CheckFraud";
  public static final String TRANSACTION_MONITOR = "TransactionMonitor";
  public static final String CHECK_WHOLE_PROCESS = "CheckWholeProcess";
  public static final String CHECK_BUCKLE = "CheckBuckle";
  public static final String CHECK_MERCHANT = "CheckMerchant";
  public static final String LOAN_MONEY = "LoanMoney";
  public static final String AUTO_PAYMENT = "AutoPayment";
  public static final String APPMONEY_TRANSFERRED = "AppMoneyTransferred";
  public static final String FINISH_APPLICATION = "FinishApplication";

  public static final String CHECK_RATE_BY_MACHINE = "CheckRateByMachine";
  public static final String CHECK_WHOLE_PROCESS_BY_MACHINE = "CheckWholeProcessByMachine";
  public static final String RECORD_WHOLE_PROCESS_BY_MACHINE = "RecordWholeProcessByMachine";

  public static final String WELCOME_CALL = "WelcomeCall";
  public static final String REPAYMENT_REMIND = "RepaymentRemind";
  public static final String WELCOME_CALL_FOR_PDL = "WelcomeCallForPDL";
  public static final String REPAYMENT_REMIND_FOR_PDL = "RepaymentRemindForPDL";
  public static final String OVERDUE_REPAYMENT_REMIND = "OverdueRepaymentRemind";

  public static final String PAYBACK_ENTRY_QUESTION = "PaybackEntryQuestion";
  // ons notifications
  public static final String UPLOAD_FILES = "UploadFiles";
  public static final String PRE_APPROVE_AND_UPLOAD_FILES = "PreApprovedAndUploadFiles";
  public static final String REUPLOAD_FILES = "ReuploadFiles";
  public static final String APPROVE_AND_UPLOAD_IOU = "ApproveAndUploadIou";
  public static final String REUPLOAD_IOU = "ReuploadIou";
  public static final String IOU_CHECK_DONE = "IOUCheckDone";
  public static final String UPLOAD_BUCKLE = "UploadBuckle";
  public static final String BUCKLE_SUBMIT_SUCCESS = "BuckleSubmitSuccess";
  public static final String UPLOAD_POST_APPROVAL_EVIDENCES = "UploadPostApprovalEvidences";

  // IA image ready message
  public static final String SET_IMAGE_UPLOAD_FIRST_STAGE_READY = "SetImageUploadFirstStageReady";
  public static final String SET_IMAGE_UPLOAD_SECOND_STAGE_READY = "SetImageUploadSecondStageReady";
  public static final String SET_IMAGE_UPLOAD_FIRST_STAGE_UNREADY = "SetImageUploadFirstStageUnReady";

  // activity results
  public static final String APPROVED = "Approved";
  public static final String REJECTED = "Rejected";
  public static final String FAILED = "Failed";
  public static final String RECHECKING_REQUIRED = "RecheckingRequired";
  public static final String MANUALPAYMENT_REQUIRED = "MaunalPaymentRequired";

  // job/message to rule engine
  public static final String ENGINE_PHOTO_CHECK = "PhotoCheck";
  public static final String ENGINE_FIRST_CHECK = "FirstCheck";
  public static final String ENGINE_RECHECK = "ReCheck";
  public static final String ENGINE_LOAN_CHECK = "LoanCheck";
  public static final String ENGINE_IOU_CHECK = "IOUCheck";
  public static final String ENGINE_POST_APPROVAL_EVIDENCE_REQUIREMENT_CHECK =
      "PostApprovalEvidenceRequirementCheck";
  public static final String ENGINE_POST_APPROVAL_EVIDENCE_CHECK = "PostApprovalEvidenceCheck";

  // queue names
  private static final String PERSONAL_INFO_QUEUE = "PersonalInfoQueueV2";
  private static final String PHOTO_CLASSIFY_QUEUE = "PhotoClassifyQueueV2";
  private static final String IMAGE_QUEUE = "ImageQueueV2";
  private static final String BANK_CARD_QUEUE = "BankCardQueueV2";
  private static final String VOICE_QUEUE = "VoiceQueueV2";
  private static final String IOU_QUEUE = "IOUQueueV2";
  private static final String BUCKLE_QUEUE = "BuckleQueueV2";
  private static final String BANK_REFERENCE_QUEUE = "BankReferenceQueueV2";
  //private static final String HOME_CREDIT_FOR_MALE_QUEUE = "HomeCreditAndCourtExecutedQueueV2";
  private static final String HOME_CREDIT_FOR_MALE_QUEUE = "HomeCreditForMaleQueueV2";
  private static final String HOME_CREDIT_FOR_FEMALE_QUEUE = "HomeCreditForFemaleQueueV2";
  private static final String COURT_EXECUTED_QUEUE = "CourtExecutedQueueV2";
  private static final String CUSTOM_PHONE_QUEUE = "CustomPhoneQueueV2";   // expire in 20 minutes
  private static final String CONTACT_PHONE_QUEUE = "ContactPhoneQueueV2";
  private static final String COMPANY_PHONE_QUEUE = "CompanyPhoneQueueV2";
  private static final String INFO_ENTERING_QUEUE = "InfoEnteringQueueV2";
  public static final String MANUAL_DECISION_QUEUE = "ManualDecisionQueueV2";
  private static final String MERCHANT_PHONE_QUEUE = "MerchantPhoneQueueV2";
  private static final String LOAN_QUEUE = "LoanQueueV2";
  private static final String FINISH_APPLICATION_QUEUE = "FinishApplicationQueueV2";
  private static final String AUTOPAYMENT_QUEUE = "AutoPaymentRequestQueue";
  private static final String WELCOME_CALL_QUEUE = "WelcomeCallQueueV2";
  private static final String WELCOME_CALL_PDL_QUEUE = "WelcomeCallQueueForPDLV2";
  private static final String REPAYMENT_REMIND_QUEUE = "RepaymentRemindQueueV2";
  private static final String OVERDUE_REPAYMENT_REMIND_QUEUE = "OverdueRepaymentRemindQueueV2";
  private static final String PAYBACK_ENTRY_QUESTION_QUEUE = "PaybackEntryQuestionQueueV2";
  private static final String CATFISHSERVER_QUEUE = "CatfishServerQueue";

  private static final String FRAUD_DECISION_QUEUE = "FraudDecisionJobRequestQueue";
  private static final String TRANSACTION_MONITOR_QUEUE = "TransactionMonitorJobRequestQueue";
  private static final String TOPRULES_DECISION_QUEUE = "TopRulesDecisionJobRequestQueue";
  private static final String MACHINE_DECISION_QUEUE = "MachineDecisionJobRequestQueue";

  public static final String STATUS_QUEUE = "StatusQueueV2";

  public static final String  JOB_REQUEST_QUEUE = "JobRequestQueue";

  public static final String UBT_DBWRITING_QUEUE = "UbtDBWritingQueue";

  public static final String CHECK_D1_FEEDBACK_QUEUE="CheckD1FeedbackQueueV2";

  public static final String  NETWORK_RELATIONSHIP_QUEUE = "GraphServiceRequestQueue";

  public static final Map<String, String> ACTIVITY_QUEUE_MAPPING =
      CollectionUtils.<String, String>newMapBuilder()
          .add(CHECK_PERSONAL_INFO,                  PERSONAL_INFO_QUEUE)
          .add(CLASSIFY_PHOTO + "1",                 PHOTO_CLASSIFY_QUEUE)
          .add(CLASSIFY_PHOTO + "2",                 PHOTO_CLASSIFY_QUEUE)
          .add(CLASSIFY_PHOTO + "4",                 PHOTO_CLASSIFY_QUEUE)
          .add(CLASSIFY_PHOTO + String.valueOf(BIT_IOU),    PHOTO_CLASSIFY_QUEUE)
          .add(CLASSIFY_PHOTO + String.valueOf(BIT_BUCKLE), PHOTO_CLASSIFY_QUEUE)
          .add(CHECK_IMAGE,                          IMAGE_QUEUE)
          .add(CHECK_BANK_CARD,                      BANK_CARD_QUEUE)
          .add(CHECK_VOICE,                          VOICE_QUEUE)
          .add(RECHECK_IMAGE,                        IMAGE_QUEUE)
          .add(RECHECK_BANK_CARD,                    BANK_CARD_QUEUE)
          .add(RECHECK_VOICE,                        VOICE_QUEUE)
          .add(CHECK_VOICE_REPEATEDLY,               VOICE_QUEUE)
          .add(CHECK_IOU,                            IOU_QUEUE)
          .add(CHECK_BANK_REFERENCE,                 BANK_REFERENCE_QUEUE)
          .add(CHECK_HOME_CREDIT_FOR_FEMALE,         HOME_CREDIT_FOR_FEMALE_QUEUE)
          .add(CHECK_HOME_CREDIT_FOR_MALE,           HOME_CREDIT_FOR_MALE_QUEUE)
          .add(CHECK_COURT_EXECUTED,                 COURT_EXECUTED_QUEUE)
          .add(CHECK_COURT_EXECUTED_EXIST,           JOB_REQUEST_QUEUE)
          .add(CHECK_USER_GENDER,                    JOB_REQUEST_QUEUE)
          .add(CHECK_COMPANY,                        COMPANY_PHONE_QUEUE)
          .add(CHECK_FIRST_CONTACT,                  CONTACT_PHONE_QUEUE)
          .add(CHECK_SECOND_CONTACT,                 CONTACT_PHONE_QUEUE)
          .add(CHECK_THIRD_CONTACT,                  CONTACT_PHONE_QUEUE)
          .add(CHECK_ADDITIONAL_CONTACT,             CONTACT_PHONE_QUEUE)
          .add(CHECK_USER,                           CUSTOM_PHONE_QUEUE)
          .add(INPUT_ID_CARD_INFO,                   INFO_ENTERING_QUEUE)
          .add(CHECK_FRAUD,                          FRAUD_DECISION_QUEUE)
          .add(CALCULATE_MERCHANT_VIEW_STATISTICS,   JOB_REQUEST_QUEUE)
          .add(CALCULATE_NETWORK_RELATIONSHIP,   NETWORK_RELATIONSHIP_QUEUE)
          .add(TRANSACTION_MONITOR,                  TRANSACTION_MONITOR_QUEUE)
          .add(CHECK_WHOLE_PROCESS,                  TOPRULES_DECISION_QUEUE)
          .add(CHECK_MERCHANT,                       MERCHANT_PHONE_QUEUE)
          .add(LOAN_MONEY,                           LOAN_QUEUE)
          .add(FINISH_APPLICATION,                   FINISH_APPLICATION_QUEUE)
          .add(AUTO_PAYMENT,                         AUTOPAYMENT_QUEUE)
          .add(APPMONEY_TRANSFERRED,                 CATFISHSERVER_QUEUE)
          .add(QueueMessages.APPLICATION_SUBMITTED,  CATFISHSERVER_QUEUE)
          .add(QueueMessages.APPLICATION_COMPLETED,  CATFISHSERVER_QUEUE)
          .add(ENGINE_PHOTO_CHECK,                   TOPRULES_DECISION_QUEUE)
          .add(ENGINE_FIRST_CHECK,                   TOPRULES_DECISION_QUEUE)
          .add(ENGINE_RECHECK,                       TOPRULES_DECISION_QUEUE)
          .add(ENGINE_LOAN_CHECK,                    TOPRULES_DECISION_QUEUE)
          .add(CHECK_D1_FEEDBACK,                    CHECK_D1_FEEDBACK_QUEUE)
          .add(ENGINE_POST_APPROVAL_EVIDENCE_REQUIREMENT_CHECK, TOPRULES_DECISION_QUEUE)
          .add(ENGINE_POST_APPROVAL_EVIDENCE_CHECK,  TOPRULES_DECISION_QUEUE)
          .add(CHECK_WHOLE_PROCESS_BY_MACHINE,       MACHINE_DECISION_QUEUE)
          .add(RECORD_WHOLE_PROCESS_BY_MACHINE,      MACHINE_DECISION_QUEUE)
          .add(CONVERT_SWEAR_VOICE,                  JOB_REQUEST_QUEUE)
          .add(WELCOME_CALL,                         WELCOME_CALL_QUEUE)
          .add(REPAYMENT_REMIND,                     REPAYMENT_REMIND_QUEUE)
          .add(WELCOME_CALL_FOR_PDL,                 WELCOME_CALL_PDL_QUEUE)
          .add(REPAYMENT_REMIND_FOR_PDL,             REPAYMENT_REMIND_QUEUE)
          .add(OVERDUE_REPAYMENT_REMIND,             OVERDUE_REPAYMENT_REMIND_QUEUE)
          .add(UBT_DATABASE_WRITING,                 UBT_DBWRITING_QUEUE)
          .add(ENGINE_IOU_CHECK,                     TOPRULES_DECISION_QUEUE)
          .add(CHECK_BUCKLE,                         BUCKLE_QUEUE)
          .add(PAYBACK_ENTRY_QUESTION, PAYBACK_ENTRY_QUESTION_QUEUE)
          .add(SET_IMAGE_UPLOAD_FIRST_STAGE_READY, JOB_REQUEST_QUEUE)
          .add(SET_IMAGE_UPLOAD_SECOND_STAGE_READY, JOB_REQUEST_QUEUE)
          .add(SET_IMAGE_UPLOAD_FIRST_STAGE_UNREADY, JOB_REQUEST_QUEUE)
          .build();
}
