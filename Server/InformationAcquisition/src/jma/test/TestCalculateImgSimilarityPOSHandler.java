package jma.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import jma.Configuration;
import jma.JobHandler;
import jma.RetryRequiredException;
import jma.handlers.CalculateImgSimilarityPOSHandler;

public class TestCalculateImgSimilarityPOSHandler {
    private JobHandler handler ;
//    @Before
    public void before(){
        HttpClientConfig.initialize();
        StartupConfig.initialize();
        Configuration.readConfiguration();
        Logger.initialize();
        PersistenceConfig.initialize();
        
        handler = new  CalculateImgSimilarityPOSHandler();
    }
//    @Test
    public void testExecutor(){
        
        try {
            handler.execute("219AF83E-7A49-E611-B04C-D89D67298EA4");
//            handler.execute("D4F0DF9F-0D47-E611-B04C-D89D67298EA4");
//            handler.execute("4538466B-0E06-E611-B04C-D89D67298EA4");
        } catch (RetryRequiredException e) {
            e.printStackTrace();
        }
    }
}
