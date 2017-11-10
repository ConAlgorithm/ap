package thirdparty.config;

import catfish.base.StartupConfig;

public class YLZHConfiguration {
	  
	  /******************银联智惠**************/
	  private static String ylzhAccount;
	  private static String ylzhUrl;
	  private static String ylzhPrivateKey;
	  private static String ylzhCorrespondingRelationUrl;
	  private static String ylzhTradeReportUrl;
	  private static String ylzhCardOwnerUrl;
	  private static int ylzhMaxRetries;
	  /****************************************/
	    
	  public static void readConfiguration()
	  {		  	    
		    /******************银联智惠**************/
		    ylzhAccount = StartupConfig.get("catfish.ylzh.account");
		    ylzhUrl = StartupConfig.get("catfish.ylzh.url");
		    ylzhPrivateKey = StartupConfig.get("catfish.ylzh.privateKey");
		    ylzhCorrespondingRelationUrl = ylzhUrl + StartupConfig.get("catfish.ylzh.correspondingRelationVerifyURL");
		    ylzhTradeReportUrl = ylzhUrl + StartupConfig.get("catfish.ylzh.tradeReportURL");
		    ylzhCardOwnerUrl = ylzhUrl + StartupConfig.get("catfish.ylzh.cardOwnerVerifyURL");
		    ylzhMaxRetries = StartupConfig.getAsInt("catfish.ylzh.maxRetries");
		    /****************************************/		   		    
	  }
	 	
  public static String getYlzhAccount() {
		return ylzhAccount;
	}

	public static String getYlzhUrl() {
		return ylzhUrl;
	}

	public static String getYlzhPrivateKey() {
		return ylzhPrivateKey;
	}

  public static String getYlzhCorrespondingRelationUrl() {
		return ylzhCorrespondingRelationUrl;
	}
 
  public static int getYlzhMaxRetries() {
		return ylzhMaxRetries;
	}
  
  public static String getYlzhTradeReportUrl() {
		return ylzhTradeReportUrl;
	}

	public static String getYlzhCardOwnerUrl() {
		return ylzhCardOwnerUrl;
	}
}
