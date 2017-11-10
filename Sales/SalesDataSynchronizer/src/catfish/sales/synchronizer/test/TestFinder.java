package catfish.sales.synchronizer.test;

import catfish.base.Logger;
import catfish.framework.IPlugin;
import catfish.sales.synchronizer.Plugin;
import catfish.sales.synchronizer.ServiceFactory;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class TestFinder {
	public static void main(String[] args) {
        Server server = Server.Create();
        ServerConfig serverConfig = new ServerConfig();

        Plugin plugin=new Plugin();
        IPlugin[] plugins = new IPlugin[] {
        		plugin};
        server.run(serverConfig, plugins, new ServiceFactory());
        Logger.get().info("SalesDataSynchronizer Server is running ...");
        //plugin.onMessage("", "AttachmentObject");
    }
}
