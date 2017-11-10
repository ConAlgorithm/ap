package catfish.msglauncher;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.database.DatabaseConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.queue.QueueConfig;
import catfish.framework.IPlugin;
import catfish.msglauncher.model.MessageTemplate;
import catfish.msglauncher.receiver.MqsReceiver;
import catfish.msglauncher.util.MongoDBApi;
import catfish.servers.Server;

public class Driver {

  public static void main(String[] args) {
//    StartupConfig.initialize();
//    Logger.initialize();
    
    Server server = Server.Create();
    IPlugin[] plugins = new IPlugin[] {
            new RESTfulServicePlugin()
    };
    server.run(plugins);
    
    
    QueueConfig.initialize();
    Configuration.initialize();
    HttpClientConfig.initialize();
    //MongoDBApi.initialize();
    
    //MessageTemplateManager.init();
    
    MqsReceiver.trigger();
    Logger.get().info("System is running ...");
  }
}
