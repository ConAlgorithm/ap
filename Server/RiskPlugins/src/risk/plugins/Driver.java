package risk.plugins;

import catfish.base.Logger;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver {
  private static Server server;

	public static void main(String[] args) {
	    server = Server.Create();
		ServerConfig serverConfig = new ServerConfig();
		IPlugin[] plugins = new IPlugin[] {
			    new MerchantUserActionPlugin()};

		Logger.get().info("Catfish Plugin Server is running ...");;
		server.run(serverConfig, plugins, new ServiceFactory());
	}

	public static Server getServer() {
	  return server;
	}
}
