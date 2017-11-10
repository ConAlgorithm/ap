package catfish.flowcontroller;

import catfish.base.StartupConfig;

public class DomainConsts {
    // configurable variables
    public static final String RECHECK_CALL_DELAY = "RecheckCallDelay";
    public static final String MAX_CALL_CHECKS = "MaxCallChecks";
    public static final String WELCOME_CALL_DELAY = "WelcomeCallDelay";
    public static final String MAX_WELCOME_CALLS = "MaxWelcomeCalls";
    public static final String REPAYMENT_REMIND_DELAY = "RepaymentRemindDelay";
    public static final String MAX_REPAYMENT_REMINDS = "MaxRepaymentReminds";


    //default values for configurable variables
    public static final int DEFAULT_RECHECK_CALL_DELAY = 180;        // 3 minutes
    public static final int DEFAULT_WELCOME_CALL_DELAY = 14400;      // 4 hours
    public static final int DEFAULT_REPAYMENT_REMIND_DELAY = 14400;
    public static final int DEFAULT_MAX_CALL_CHECKS = 3;
    public static final int DEFAULT_MAX_WELCOME_CALLS = 3;
    public static final int DEFAULT_MAX_REPAYMENT_REMINDS = 3;
    

    //Ons消息名称，用于提示用户上传话术
  	public static final String PreApproveAndUploadFiles = "PreApprovedAndUploadFiles";
  	public static final String ReuploadFiles = "ReuploadFiles";
  	public static final String UploadFiles = "UploadFiles";

	public static final int BIT_REUPLOAD = 1 << 10;
	public static final String jobQueuename =  
			 StartupConfig.get("catfish.flowcontroller.jobqueue", "JobStatusQueue");

    // in queues
    public static final String[] IN_QUEUES = new String[] {
        "JobStatusQueue",
        "StatusQueueV2"
    };
}
