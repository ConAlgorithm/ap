package catfish.flowcontroller.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.flowcontroller.FlowControllerPlugin;
import catfish.flowcontroller.rest.RestPlugin;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver {
    public static void main(String[] args) {
        Server server = Server.Create();
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.zookeeperConnection = "localhost:2181";

        IPlugin[] plugins = new IPlugin[] {
                new TestPlugin(),
                new FlowControllerPlugin(4),
                new RestPlugin()
                };

        Logger.get().info("Flow controller test Server is running ...");;
        server.run(serverConfig, plugins ,new ServiceFactory());
    }
}
