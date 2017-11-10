package catfish.sales.synchronizer;

import catfish.base.Logger;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver {
    public static void main(String[] args) {
        Server server = Server.Create();
        ServerConfig serverConfig = new ServerConfig();

        IPlugin[] plugins = new IPlugin[] {
                new Plugin()};
        server.run(serverConfig, plugins, new ServiceFactory());
        Logger.get().info("SalesDataSynchronizer Server is running ...");
    }
}
