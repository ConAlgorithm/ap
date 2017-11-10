package catfish.fundcontroller;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.framework.IPlugin;
import catfish.funcontroller.rest.RestPlugin;
import catfish.fundcontroller.jimubox.JimuboxPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class Driver {
	public static void main(String[] args) {
        Server server = Server.Create();
        ServerConfig serverConfig = new ServerConfig();

        JimuboxPlugin plugin = new JimuboxPlugin();
        IPlugin[] plugins = new IPlugin[] {
        		plugin,
                new RestPlugin()
                };

        Logger.get().info("P2P Server is running ...");
        server.run(serverConfig, plugins, new ServiceFactory());


        
    }
}
