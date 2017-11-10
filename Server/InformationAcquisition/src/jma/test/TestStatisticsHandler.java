package jma.test;

import jma.Configuration;
import jma.RetryRequiredException;
import jma.handlers.CalculatePDLStatisticsHandler;
import thirdparty.config.YLZHConfiguration;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;

public class TestStatisticsHandler {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        StartupConfig.initialize();
        Logger.initialize();
        Configuration.readConfiguration();
        YLZHConfiguration.readConfiguration();
        HttpClientConfig.initialize();
        PersistenceConfig.initialize();
        
        String appId = "68B08508-6654-E511-87D5-80DB2F14945F";
        
        CalculatePDLStatisticsHandler handler = new CalculatePDLStatisticsHandler();
        try {
            handler.execute(appId);
            //handler.execute(args[0]);
        } catch (RetryRequiredException e) {
            e.printStackTrace();
        }
    }

}
