package thirdparty.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.StartupConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class REG007Configuration {
	private static Map<String, Object> scoreProperties = new HashMap<String, Object>();
	private static Map<String, Object> blacklistProperties = new HashMap<String, Object>();
	
	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
	  
	private static String reg007VisitorUrl;
	private static String reg007AjaxRequestUrl;
	private static String req007SearchUrl;
	private static int maxRetryCount;
	private static int maxThread;
	
	public static void initialize() {
		try {
			blacklistProperties = initialize("reg007Blacklist.properties");
			scoreProperties = initialize("reg007Score.properties");
			
			reg007VisitorUrl = StartupConfig.get("catfish.reg007.reg007VisitorUrl");
			reg007AjaxRequestUrl = StartupConfig.get("catfish.reg007.reg007AjaxRequestUrl");
			req007SearchUrl = StartupConfig.get("catfish.reg007.reg007SearchUrl");
			maxRetryCount = StartupConfig.getAsInt("catfish.reg007.maxRetryCount");
			maxThread = StartupConfig.getAsInt("catfish.reg007.maxThread");
		} catch (Exception ex) {
			Logger.get().error(String.format("initialize configuration file error!"), ex);
		}
	}
	
//	public static void readConfiguration() {
//		reg007VisitorUrl = StartupConfig.get("catfish.reg007.reg007VisitorUrl");
//		reg007AjaxRequestUrl = StartupConfig.get("catfish.reg007.reg007AjaxRequestUrl");
//		req007SearchUrl = StartupConfig.get("catfish.reg007.reg007SearchUrl");
//		maxRetryCount = StartupConfig.getAsInt("catfish.reg007.maxRetryCount");
//		maxThread = StartupConfig.getAsInt("catfish.reg007.maxThread");
//	}
//	
	@SuppressWarnings({ "unchecked"})
	public static Map<String, Object> initialize(String path) {
	    try {
	    	return GSON.fromJson(new FileReader(path), Map.class);
		} catch (Exception ex) {
			Logger.get().error("Read Configuration file Error!", ex);
			return null;
		}
	}
	
	public static int getMaxThread() {
		return maxThread;
	}
	
	public static String getScore(String key) {	    
		return  scoreProperties.containsKey(key) ?  scoreProperties.get(key).toString() : null;
	}
	
	public static String getBlacklist(String key) {
		return blacklistProperties.containsKey(key) ? blacklistProperties.get(key).toString() : null;
	}
	
	public static String getReg007VisitorUrl() {
		return reg007VisitorUrl;
	}

	public static String getReg007AjaxRequestUrl() {
		return reg007AjaxRequestUrl;
	}

	public static String getReq007SearchUrl() {
		return req007SearchUrl;
	}

	public static int getMaxRetryCount() {
		return maxRetryCount;
	}
}