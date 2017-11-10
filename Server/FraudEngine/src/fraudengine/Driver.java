package fraudengine;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.mongo.CatfishMongoClient;
import catfish.base.persistence.queue.PersistenceConfig;
import catfish.servers.Server;

public class Driver {

  public static void main(String[] args) {
	Server server = Server.Create();
    StartupConfig.initialize();
    Logger.initialize();
    PersistenceConfig.initialize();
    //DatabaseConfig.initialize();
    //OnsConfig.initialize();
    //MessageNotificationUtil.start();
    Configuration.initialize();
    HttpClientConfig.initialize();

    Logger.get().info("FraudEngine System is running ...");
    FraudEngine.trigger(Configuration.getThreadCount());
    
    HttpHandler.run(server);

    //MessageNotificationUtil.stop();
  }
}
