package jma.test;

import jma.Configuration;
import jma.RetryRequiredException;
import jma.handlers.CheckPosPayInfoHandler;
import jma.thirdpartyservices.jxl.JXLThreadWatchdog;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;

public class TestCheckPosPayInfo {
    public static void main(String[] args) {
        HttpClientConfig.initialize();
        StartupConfig.initialize();
        Configuration.readConfiguration();
        JXLThreadWatchdog.initialize();
        Logger.initialize();
        PersistenceConfig.initialize();
        try {
//            new CheckPosPayInfoHandler().execute("E379ADEF-8C6A-4745-ADDE-D443B439AA48");
            new CheckPosPayInfoHandler().execute("285BBB54-5FF9-461B-ABDF-BB46E9C5A638");
        } catch (RetryRequiredException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
