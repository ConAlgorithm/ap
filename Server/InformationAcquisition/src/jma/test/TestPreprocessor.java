package jma.test;

import thirdparty.config.YLZHConfiguration;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import catfish.base.queue.QueueMessager;
import jma.Configuration;
import jma.JobHandler;

public class TestPreprocessor {

  public static void main(String[] args) {
    StartupConfig.initialize();
    Logger.initialize();
    Configuration.readConfiguration();
    YLZHConfiguration.readConfiguration();
    HttpClientConfig.initialize();
    PersistenceConfig.initialize();

    JobHandler handler = Configuration.createJobHandler("CheckUserCreditOn3rdParty");
    QueueMessager messager = new QueueMessager("9697DC6B-2CEA-E411-87D5-80DB2F14945F", "CheckUserCreditOn3rdParty");
    messager.setChannel("CashLoan");
    handler.initialize(messager);
  }

}
