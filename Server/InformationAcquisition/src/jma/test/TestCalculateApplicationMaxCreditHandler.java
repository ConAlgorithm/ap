package jma.test;

import jma.Configuration;
import jma.RetryRequiredException;
import jma.handlers.CalculateApplicationMaxCreditHandler;
import thirdparty.config.YLZHConfiguration;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;

public class TestCalculateApplicationMaxCreditHandler {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        StartupConfig.initialize();
        Logger.initialize();
        Configuration.readConfiguration();
        YLZHConfiguration.readConfiguration();
        HttpClientConfig.initialize();
        PersistenceConfig.initialize();
        
        //List<String> ids = InstallmentApplicationDao.getAppIdsForTest();
        CalculateApplicationMaxCreditHandler handler = new CalculateApplicationMaxCreditHandler();
        try {
            handler.execute("2addc05b-343c-e511-87d5-80db2f14945f");
            //handler.execute(args[0]);
        } catch (RetryRequiredException e) {
            e.printStackTrace();
        }
    }

}
