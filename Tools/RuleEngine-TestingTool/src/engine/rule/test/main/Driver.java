package engine.rule.test.main;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientConfig;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;
import engine.rule.test.restful.RestfulPlugin;
import engine.rule.test.restful.ServiceFactory;

public class Driver {

	public static void main(String[] args)
	{			
			
		 Server server = Server.Create();
		 engine.main.Configuration.initialize();
		 engine.rule.test.Configuration.initialize();
		 HttpClientConfig.initialize();
		 
	     ServerConfig serverConfig = new ServerConfig();

         IPlugin[] plugins = new IPlugin[] {
             new RestfulPlugin()
         };
	     
	     server.run(serverConfig, plugins, new ServiceFactory());
	     Logger.get().info("RuleEngine-TestingTool Server is running ...");
	}
}
