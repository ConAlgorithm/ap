package catfish.fundcontroller.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.framework.IPlugin;
import catfish.fundcontroller.ServiceFactory;
import catfish.fundcontroller.jimubox.JimuboxPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class TestJimuboxInterface {

	public static void main(String[] args){
		Server server = Server.Create();
        ServerConfig serverConfig = new ServerConfig();

        JimuboxPlugin plugin = new JimuboxPlugin();
        IPlugin[] plugins = new IPlugin[] {
        		plugin
                //new RestPlugin()
                };

        Logger.get().info("P2P Server is running ...");

        server.run(serverConfig, plugins, new ServiceFactory());
        String jsonString="{appId:F04E8A6E-CBFE-E411-87D5-80DB2F14945F}";

        plugin.onMessage("test", jsonString);
	}
}
