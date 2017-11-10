package jma.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import jma.Configuration;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.handlers.CalculateImgSimilarityPDLHandler;

public class TestCalculateImgSimilarityPDLHandler {
    private JobHandler handler ;
//    @Before
    public void before(){
        HttpClientConfig.initialize();
        StartupConfig.initialize();
        Configuration.readConfiguration();
        Logger.initialize();
        PersistenceConfig.initialize();
        
        handler = new  CalculateImgSimilarityPDLHandler();
    }
//    @Test
    public void testExecutor(){
        
        try {
//            handler.execute("EF4B36BD-7349-E611-B04C-D89D67298EA4");
          handler.execute("D4F0DF9F-0D47-E611-B04C-D89D67298EA4");
        } catch (RetryRequiredException e) {
            e.printStackTrace();
        }
    }
}
