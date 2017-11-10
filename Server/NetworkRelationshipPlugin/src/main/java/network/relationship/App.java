package network.relationship;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientConfig;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

public class App 
{
    public static void main( String[] args )
    {
    	Server server = Server.Create();
		ServerConfig serverConfig = new ServerConfig();
		HttpClientConfig.initialize();
		Configuration.initialize();
		
		IPlugin[] plugins = new IPlugin[] {
			    new NetworkRelationshipPlugin(),
			    new RestPlugin()
			    //new NetworkRebuildingPlugin()
			    };

		Logger.get().info("NetworkRelationshipPlugin Server is running ...");;
		server.run(serverConfig, plugins,  new ServiceFactory());
    }
}
