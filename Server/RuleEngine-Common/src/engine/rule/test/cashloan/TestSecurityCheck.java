package engine.rule.test.cashloan;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.database.DatabaseConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.ons.OnsConfig;
import catfish.base.queue.QueueConfig;
import catfish.base.queue.QueueMessager;
import engine.main.Configuration;
import engine.rule.RuleHandlerConfigManager;

public class TestSecurityCheck {

  public static void main(String[] args) {
    StartupConfig.initialize();
    Logger.initialize();
    QueueConfig.initialize();
    DatabaseConfig.initialize();
    Configuration.initialize();
    OnsConfig.initialize();
    HttpClientConfig.initialize();
    Configuration.initialize();

    try {
      String appId = "A2AFC335-5882-41D2-801A-73E535B8981D";
      QueueMessager messager = new QueueMessager(appId, "CLPreCheck", 1);
      Object result = RuleHandlerConfigManager
          .getRuleHandler(messager.getJobName(), InstalmentChannel.CashLoan.getValue())
          .handle(messager);
      System.out.println(result);
    }
    catch(Exception e) {
        Logger.get().error(e);
    }
  }

}
