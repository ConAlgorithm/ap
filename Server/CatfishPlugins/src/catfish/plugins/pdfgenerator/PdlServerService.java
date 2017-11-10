package catfish.plugins.pdfgenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;

import com.google.gson.Gson;

public class PdlServerService {
	public static String getPdlOpenAccountPayDay(String appId) {
		return PdlServerService.getOpenAccountPayDay(appId);
	}
	
	public static String getWithDrawalPayday(Date payDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(payDate);
		return calendar.get(Calendar.DAY_OF_MONTH) + "";
	}
	
	public static String getInterestRate() {
		return StartupConfig.get("catfish.pdl.interestrate", "");
	}
	
	public static String getServiceFeeRate() {
		return StartupConfig.get("catfish.pdl.servicefeerate", "");
	}
	
	public static boolean isOldWithdrawal(Date withdrawalDate) {
		String splitDateStr = StartupConfig.get("catfish.pdl.withdrawalsplitdate");
		try {  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    Date date = sdf.parse(splitDateStr);
		    return withdrawalDate.before(date);
		}  
		catch (ParseException e) {  
			Logger.get().error("获取最低还款上线时间失败！", e);
		}  
		return false;
	}
	
	private static String getOpenAccountPayDay(String appId) {
		String url = getPdlBusinessServerURL() + "/application/openaccountapplications/" + appId;
	    try {
	    	String pldBusinessServerResponse = HttpClientApi.get(url);
	    	if(null != pldBusinessServerResponse) {
				HashMap<String, String>  openAccountApplicationModel= new Gson().fromJson(pldBusinessServerResponse, HashMap.class);
				String factoryId = openAccountApplicationModel.get("factoryId");
				
				if(null != factoryId) {
					url = getCatfishSalesPDLServerURL() + "/factory/" + factoryId + "/payday";
					String catfishSalesPdlServerResponse = HttpClientApi.get(url);
					return catfishSalesPdlServerResponse == null ? "" : catfishSalesPdlServerResponse;
				}
	    	}
	    	return "";
	    } catch(Exception e) {
	    	Logger.get().error("获取开卡payDay失败！      appId = " + appId + ", ExceptionMessage: " + e.getMessage());
	    	return "";
	    }
	}
	
	private static String getPdlBusinessServerURL() {
	  String host = StartupConfig.get("pdlbusiness.server.restful.host");
	  String port = StartupConfig.get("pdlbusiness.server.restful.port");
	  String url = "http://" + host + ":" + port;
	  return url;
	}
	
	private static String getCatfishSalesPDLServerURL() {
		String host = StartupConfig.get("catfishsalespdl.server.host");
		String port = StartupConfig.get("catfishsalespdl.server.port");
		String url = "http://" + host + ":" + port;
		return url;
	}
}
