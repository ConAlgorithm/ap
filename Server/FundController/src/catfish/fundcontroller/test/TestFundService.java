package catfish.fundcontroller.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.framework.IPlugin;
import catfish.funcontroller.rest.RestPlugin;
import catfish.fundcontroller.ServiceFactory;
import catfish.servers.Server;
import catfish.servers.ServerConfig;



public class TestFundService {
	public static void main(String[] args) {
		Server server = Server.Create();
	    ServerConfig serverConfig = new ServerConfig();
        
	    IPlugin[] plugins = new IPlugin[] {
	            new RestPlugin()
	            };
	
	    Logger.get().info("P2P Server is running ...");
	    server.run(serverConfig, plugins, new ServiceFactory());
	}
}