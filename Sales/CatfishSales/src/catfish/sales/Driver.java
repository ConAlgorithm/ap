package catfish.sales;

import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver {
	
	public static void main(String[] args) {
		Server server = Server.Create();
        ServerConfig serverConfig = new ServerConfig();
        
        IPlugin[] plugins = new IPlugin[] {
                new SalesPlugin()
        };

        server.run(serverConfig, plugins, new ServiceFactory());
	}
}
