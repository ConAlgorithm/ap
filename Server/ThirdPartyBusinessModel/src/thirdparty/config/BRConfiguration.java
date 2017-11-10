package thirdparty.config;

import catfish.base.StartupConfig;

public class BRConfiguration {
	private static final String PREFIX = "catfish.br";
	
	private static String loginId;
	private static String loginPassword;
	private static String zookeeperConnection;
	private static int zookeeperTimeout;
	
	public static void initialize(){
		loginId=get("loginId");
		loginPassword=get("loginPassword");
		zookeeperConnection=StartupConfig.get("catfish.server.zookeeperConnection");
		zookeeperTimeout=StartupConfig.getAsInt("catfish.server.zookeeperTimeout");
	}
	
	public static int getZookeeperTimeout(){
		return zookeeperTimeout;
	}
	
	public static String getZookeeperConnection(){
		return zookeeperConnection;
	}
	
	public static String getLoginId(){
		return loginId;
	}
	
	public static String getLoginPassword(){
		return loginPassword;
	}
	
	private static String get(String key){
		return StartupConfig.get(String.format("%s.%s", PREFIX, key));
	}
}
