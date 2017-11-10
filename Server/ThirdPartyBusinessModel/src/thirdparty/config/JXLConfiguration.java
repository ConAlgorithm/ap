package thirdparty.config;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.DefaultClient;
import catfish.base.httpclient.DefaultRequestBuilder;
import catfish.base.httpclient.object.BaseObject;
import thirdparty.jxl.request.ReportTokenRequest;
import thirdparty.jxl.response.ReportTokenResponse;

public class JXLConfiguration {

	  /******************聚信力****************/
	  private static String jxlClientSecret;	
	  private static String jxlOrgName;		
	  private static String jxlUrl;	
	  //获取token
	  private static String jxlReportTokenUrl;		
	  //获取用户信息报告
	  private static String jxlReportDataUrl;
	  //获取用户抓取报告状态
	  private static String jxlReportStatusUrl;
	  private static String jxlExpireHours;
	  //抓取超时秒数
	  private static int jxlTimeoutSec;
    private static int jxlMaxRetries;
	  private static int jxlRequestInterval;
	  private static String jxlAccessToken;
	  
	  //cl配置
	private static int jxlThreadWaitingSecondsForCL;
	private static int jxlRetryIntervalSecondsForCL;
	private static String jxlVersion;
	  /****************************************/
	  
	  public static void readConfiguration()
	  {		  		    
		    /***************聚信力*******************/
		    jxlUrl = StartupConfig.get("catfish.juxinli.URL");
			jxlReportTokenUrl = jxlUrl + StartupConfig.get("catfish.juxinli.reportTokenURL");
			jxlReportDataUrl = jxlUrl + StartupConfig.get("catfish.juxinli.reportDataURL");
			jxlReportStatusUrl = jxlUrl + StartupConfig.get("catfish.juxinli.reportStatusURL");
			
			jxlClientSecret = StartupConfig.get("catfish.juxinli.clientSecret");
			jxlOrgName = StartupConfig.get("catfish.juxinli.orgName");
		    jxlExpireHours = StartupConfig.get("catfish.juxinli.expireHours");
		    jxlTimeoutSec = StartupConfig.getAsInt("catfish.juxinli.timeoutSec");
		    
		    jxlMaxRetries = StartupConfig.getAsInt("catfish.juxinli.maxRetries");
		    jxlRequestInterval = StartupConfig.getAsInt("catfish.juxinli.requestInterval");
		jxlThreadWaitingSecondsForCL = StartupConfig.getAsInt("catfish.juxinli.threadWaitingSeconds.cl");
		jxlRetryIntervalSecondsForCL = StartupConfig.getAsInt("catfish.juxinli.retryIntervalSeconds.cl");
		jxlVersion = StartupConfig.get("catfish.juxinli.version");
		    
		    initAccessToken();
		    /***************************************/
	  }
	  private static void initAccessToken()
	  {		
			try {
				ReportTokenRequest request = new ReportTokenRequest();
				String param = request.toGetParam();
				Logger.get().info(param);
				DefaultClient client = new DefaultClient(JXLConfiguration.getJxlReportTokenUrl(), new DefaultRequestBuilder(request));
				ReportTokenResponse response;
				String result = client.httpGet();
				response = BaseObject.fromString(result, ReportTokenResponse.class);
				Logger.get().info(response.getAccessToken());
				jxlAccessToken = response.getAccessToken();
			} catch(Exception e)
			{
				Logger.get().error("Cannot get AccessToken, the server would not startup!", e);
				System.exit(-1);
			}
	  }
 
	public static String getJxlClientSecret() {
		return jxlClientSecret;
	}
	public static String getJxlOrgName() {
		return jxlOrgName;
	}
	public static String getJxlUrl() {
		return jxlUrl;
	}
	public static String getJxlReportTokenUrl() {
		return jxlReportTokenUrl;
	}
	public static String getJxlReportDataUrl() {
		return jxlReportDataUrl;
	}
	public static String getJxlReportStatusUrl() {
		return jxlReportStatusUrl;
	}
	public static String getJxlExpireHours() {
		return jxlExpireHours;
	}
	public static int getJxlTimeoutSec() {
		return jxlTimeoutSec;
	}
	public static int getJxlMaxRetries() {
		return jxlMaxRetries;
	}
	public static int getJxlRequestInterval() {
		return jxlRequestInterval;
	}
	public static String getJxlAccessToken() {
		return jxlAccessToken;
	}

	public static int getJxlThreadWaitingSecondsForCL() {
		return jxlThreadWaitingSecondsForCL;
	}

	public static int getJxlRetryIntervalSecondsForCL() {
		return jxlRetryIntervalSecondsForCL;
	}

	public static String getJxlVersion() {
		return jxlVersion;
	}

}
