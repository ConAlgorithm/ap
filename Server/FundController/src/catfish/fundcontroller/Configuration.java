package catfish.fundcontroller;

import catfish.base.StartupConfig;

public class Configuration {
	public static final String UTF_8 = "UTF-8";
	
	private static String FundControllerQueue="FundLoanMoneyFinishedQueue";
	private static final int Retry_Times=Integer.parseInt(StartupConfig.get("catfish.jimubox.retrytimes","3"));
	private static String Interval_Times=StartupConfig.get("catfish.jimubox.intervaltimes","60,300,600");
	private static String JMBox_FundTag=StartupConfig.get("catfish.jimubox.fundtag","JMBox");
	private static String GS_URL=StartupConfig.get("grasscarp.restful.url");

	public static String getQueueName() {
		return FundControllerQueue;
	}
	
	public static int getRequestTime() {
		return Retry_Times;
	}
	
	public static String getIntervalTimes(){
		return Interval_Times;
	}
	
	public static String getJMBoxFundTag(){
		return JMBox_FundTag;
	}

  public static String getGS_URL() {
    return GS_URL;
  }

}