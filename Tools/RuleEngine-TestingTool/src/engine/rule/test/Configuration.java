package engine.rule.test;

import catfish.base.StartupConfig;

public class Configuration {

	public static final String UTF_8 = "UTF-8";
	private static String analyzerUrl;	
	private static int threadCount;
    
	private static String rulePostFix;
    private static String defaultSegmentation;
    

	public static void initialize() {
		analyzerUrl = StartupConfig.get("catfish.ruleanalyzer.url");
		threadCount = StartupConfig.getAsInt("catfish.queue.thread.count");
		rulePostFix = StartupConfig.get("catfish.ruleengine.postfix", "");
		defaultSegmentation = StartupConfig.get("catfish.ruleengine.defaultSegmentation", "F1");
	}

	public static int getThreadCount() {
		return threadCount;
	}

	public static void setThreadCount(int threadCount) {
		Configuration.threadCount = threadCount;
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

	public static String getAnalyzerUrl() {
		return analyzerUrl;
	}

	public static void setAnalyzerUrl(String analyzerUrl) {
		Configuration.analyzerUrl = analyzerUrl;
	}
	
}