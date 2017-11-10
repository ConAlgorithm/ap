package engine.rule.analyzer;

import catfish.base.Logger;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;
import engine.rule.analyzer.restful.RestfulPlugin;
import engine.rule.analyzer.restful.ServiceFactory;

public class Driver {

	public static void main(String[] args)
	{			
		 Server server = Server.Create();
		 
	     ServerConfig serverConfig = new ServerConfig();

         IPlugin[] plugins = new IPlugin[] {
             new RestfulPlugin()
         };
	     
	     server.run(serverConfig, plugins, new ServiceFactory());
	     Logger.get().info("RuleEngine-Analyzer Server is running ...");
	}
}
