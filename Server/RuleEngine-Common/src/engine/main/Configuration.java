package engine.main;

import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.StartupConfig;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;
import engine.rule.config.RuleCategory;

public class Configuration {

	public static final String NOTIFICATION_NAME = "NotificationName";
	public static final String APP_ID = "AppId";

	public static final String JOB_STATUS_QUEUEV2 = 
			StartupConfig.get("catfish.flowcontroller.manualJobStatusQueue", "StatusQueueV2");
	public static final String JOB_STATUS_QUEUE = 
			StartupConfig.get("catfish.flowcontroller.autoJobQueue", "JobStatusQueue");
	public static final String CL_STATUS_QUEUE = "CLStatusQueue";  // cash loan flow controller

	public static final String TOPRULES_DECISION_QUEUE = 
			StartupConfig.get("catfish.ruleengine.queuename", "TopRulesDecisionJobRequestQueue");
	public static final String UTF_8 = "UTF-8";
	
	public static final int PIC_REUPLOAD_MAX_COUNT = StartupConfig.getAsInt("pic.reupload.maxcount",6);

	private static String httpHost;
	private static int httpPort;
	private static int threadCount;

	private static String onsTopic;
	private static String onsProducerId;
	private static String onsAccessKey;
	private static String onsSecretKey;
	private static String reportLogPath;

	private static String rulePostFix;
    private static String defaultSegmentation;
    private static String riskPluginUrl;

    //ApplicationService
    private static String appServiceUrl;
    private static int appServiceMaxRetries;

    private static int retrySleepySeconds;
    private static String fundServiceUrl;
	private static String cowfishServiceUrl;
    
	private static final Map<String, String> QUEUEMAP = CollectionUtils
			.<String, String> newMapBuilder()
			.add(RuleCategory.PRECHECK.getValue(), JOB_STATUS_QUEUE)
			.add(RuleCategory.CHECK_NAME_ID_MATCH.getValue(), JOB_STATUS_QUEUE)
			.add(RuleCategory.FIRSTCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(RuleCategory.RECHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(RuleCategory.LOANDECISION.getValue(), JOB_STATUS_QUEUEV2)
			.add(RuleCategory.LOANCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(RuleCategory.PHOTO_CHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(RuleCategory.IOUCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(RuleCategory.POSTAPPROVALEVIDENCEREQUIREMENTCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(RuleCategory.POSTAPPROVALEVIDENCECHECK.getValue(), JOB_STATUS_QUEUEV2)
			.build();

	private static final Map<String, String> QUEUEMAPFORAPP = CollectionUtils
			.<String, String> newMapBuilder()
			.add(engine.rule.config.app.RuleCategory.PRECHECK.getValue(), JOB_STATUS_QUEUE)
			.add(engine.rule.config.app.RuleCategory.BLACKCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(engine.rule.config.app.RuleCategory.RECHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(engine.rule.config.app.RuleCategory.SEGMENTATION.getValue(), JOB_STATUS_QUEUEV2)
			.add(engine.rule.config.app.RuleCategory.FINALCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(engine.rule.config.app.RuleCategory.APPROVALEVIDENCEREQUIREMENTCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(engine.rule.config.app.RuleCategory.FRAUDCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(engine.rule.config.app.RuleCategory.MONITORCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(engine.rule.config.app.RuleCategory.LOANCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.add(engine.rule.config.pdl.RuleCategory.ANTIFRAUDCHECK.getValue(), JOB_STATUS_QUEUEV2)
			.build();

	private static final Map<String, String> CL_QUEUE_MAP = CollectionUtils.<String, String>newMapBuilder()
	    .add(engine.rule.config.cashloan.RuleCategory.PRECHECK.getValue(), CL_STATUS_QUEUE)
	    .add(engine.rule.config.cashloan.RuleCategory.SECURITYCHECK.getValue(), CL_STATUS_QUEUE)
	    .add(engine.rule.config.cashloan.RuleCategory.FINALCHECK.getValue(), CL_STATUS_QUEUE)
	    .build();

	public static String getQueueName(QueueMessager messager){
        String jobName = messager.getJobName();
	    // cash loan
	    if (jobName.startsWith("CL")) {
	        return CL_QUEUE_MAP.get(jobName);
	    }
	    
		if(messager.getCallbackQueue()!=null)
			return messager.getCallbackQueue();
		
		if(QueueConfig.getQueuePrefix().contains("App"))
			return QUEUEMAPFORAPP.get(jobName);
		
		
		return QUEUEMAP.get(jobName);
	}

	public static void initialize() {
		httpHost = StartupConfig.get("catfish.http.host");
		httpPort = StartupConfig.getAsInt("catfish.http.port");
		threadCount = StartupConfig.getAsInt("catfish.queue.thread.count");
		rulePostFix = StartupConfig.get("catfish.ruleengine.postfix", "");
		String onsPrefix = StartupConfig.get("catfish.ons.prefix");
		setOnsTopic(String.format("%sNotification", onsPrefix));
		onsProducerId = String.format("PID-%sNotification", onsPrefix);
		onsAccessKey = StartupConfig.get("catfish.ons.accesskey");
		onsSecretKey = StartupConfig.get("catfish.ons.secretkey");
		reportLogPath = StartupConfig.get("catfish.ruleengine.csvpath");
		defaultSegmentation = StartupConfig.get("catfish.ruleengine.defaultSegmentation", "F1");
		riskPluginUrl = StartupConfig.get("catfish.ruleengine.riskpluginurl");
		appServiceUrl = StartupConfig.get("catfish.application.service.url");
		appServiceMaxRetries = StartupConfig.getAsInt("catfish.application.service.maxRetries", 3);
		retrySleepySeconds = StartupConfig.getAsInt("catfish.ruleengine.retrySleepySeconds", 50);
		fundServiceUrl = StartupConfig.get("fundService.URL");
		cowfishServiceUrl = StartupConfig.get("cowfishService.URL");
	}
	
	
	public static String getFundServiceUrl() {
		return fundServiceUrl;
	}

	public static String getHttpHost() {
		return httpHost;
	}

	public static int getHttpPort() {
		return httpPort;
	}

	public static int getThreadCount() {
		return threadCount;
	}

	public static void setThreadCount(int threadCount) {
		Configuration.threadCount = threadCount;
	}

	public static String getOnsTopic() {
		return onsTopic;
	}

	public static void setOnsTopic(String onsTopic) {
		Configuration.onsTopic = onsTopic;
	}

	public static String getOnsProducerId() {
		return onsProducerId;
	}

	public static void setOnsProducerId(String onsProducerId) {
		Configuration.onsProducerId = onsProducerId;
	}

	public static String getOnsAccessKey() {
		return onsAccessKey;
	}

	public static void setOnsAccessKey(String onsAccessKey) {
		Configuration.onsAccessKey = onsAccessKey;
	}

	public static String getOnsSecretKey() {
		return onsSecretKey;
	}

	public static void setOnsSecretKey(String onsSecretKey) {
		Configuration.onsSecretKey = onsSecretKey;
	}

	public static String getReportLogPath() {
		return reportLogPath;
	}

	public static void setReportLogPath(String reportLogPath) {
		Configuration.reportLogPath = reportLogPath;
	}

	public static String getRulePostFix() {
		return rulePostFix;
	}

	public static void setRulePostFix(String rulePostFix) {
		Configuration.rulePostFix = rulePostFix;
	}

	public static String getDefaultSegmentation() {
		return defaultSegmentation;
	}

	public static String getRiskPluginUrl() {
		return riskPluginUrl;
	}

	public static String getAppServiceUrl() {
		return appServiceUrl;
	}

	public static int getAppServiceMaxRetries() {
		return appServiceMaxRetries;
	}

	public static int getRetrySleepySeconds() {
		return retrySleepySeconds;
	}

	public static void setRetrySleepySeconds(int retrySleepySeconds) {
		Configuration.retrySleepySeconds = retrySleepySeconds;
	}

	public static String getCowfishServiceUrl() {
		return cowfishServiceUrl;
	}
}
