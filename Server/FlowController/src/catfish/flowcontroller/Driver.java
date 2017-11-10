package catfish.flowcontroller;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.flowcontroller.rest.RestPlugin;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver {
    public static void main(String[] args) {
        Server server = Server.Create();
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.name="flowcontroller";
        serverConfig.zookeeperConnection = StartupConfig.get("catfish.flowcontroller.zookeeperConnection");

        int executorCount = StartupConfig.getAsInt("catfish.flowcontroller.executor.count");
        IPlugin[] plugins = new IPlugin[] {
                new FlowControllerPlugin(executorCount),
                new RestPlugin()
                };

        Logger.get().info("Flow controller Server is running ...");
        server.run(serverConfig, plugins, new ServiceFactory());
    }
}
