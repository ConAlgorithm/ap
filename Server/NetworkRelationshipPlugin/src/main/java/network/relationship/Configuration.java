package network.relationship;


import catfish.base.StartupConfig;

public class Configuration {
	
	  private static String hostname;
	  private static int port;
	  public static void initialize() {

		    hostname = StartupConfig.get("catfish.relationship.host");
		    port = StartupConfig.getAsInt("catfish.relationship.port");
	  }
	public static String getHostname() {
		return hostname;
	}
	public static void setHostname(String hostname) {
		Configuration.hostname = hostname;
	}
	public static int getPort() {
		return port;
	}
	public static void setPort(int port) {
		Configuration.port = port;
	}
	  
	  

}
