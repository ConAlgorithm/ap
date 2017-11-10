package engine.main;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import catfish.framework.IPlugin;
import catfish.servers.Server;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = Server.Create();		
		PersistenceConfig.initialize();
		Configuration.initialize();
		//OnsConfig.initialize();
		//MessageNotificationUtil.start();
		HttpClientConfig.initialize();
	
		IPlugin[] plugins = new IPlugin[]{
				new QueuePlugin(),
				new HttpPlugin(),
				new WorkflowPlugin()			
		};
		server.run(plugins, new ServiceFactory());	
		Logger.get().info("RuleEngine-Common is running ...");		
	}
}
