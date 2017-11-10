package catfish.manualjobarranger;

import catfish.base.Logger;
import catfish.base.httpclient.HttpClientConfig;
import catfish.servers.Server;

public class Driver {
  public static void main(String[] args) {
    Server server = Server.Create();
    Configuration.initialize();
    HttpClientConfig.initialize();
    StealController.run();
    HttpHandler.run(server);
    Logger.get().info("System is running ...");
  }
}
