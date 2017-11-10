package catfish.plugins.finance;

import org.junit.Before;

import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class MockLoadActivityRepaymentSchedulePlugin {
		
	
	public static void main(String[] args) {

		Server server = Server.Create();
		
		ServerConfig serverConfig = new ServerConfig();
		
		//serverConfig.enableCluster = true;
		//serverConfig.zookeeperConnection = "localhost:2181,localhost:2182,localhost:2183";
		
		IPlugin[] plugins = new IPlugin[] {
			    new ActivityFinancePlugin()
		    };

		
		server.run(serverConfig, plugins);
		
    }
    
	

}
