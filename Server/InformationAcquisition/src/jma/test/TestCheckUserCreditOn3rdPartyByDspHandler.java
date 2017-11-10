package jma.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import catfish.base.queue.QueueMessager;
import jma.Configuration;
import jma.handlers.CheckUserCreditOn3rdPartyByDspHandler;
import jma.handlers.CheckUserCreditOn3rdPartyHandler;
import jma.handlers.CheckUserCreditOnDspTdHandler;
import jma.handlers.CheckUserCreditOnTdHandler;
import thirdparty.config.QhzxConfiguration;
import thirdparty.config.TdConfiguration;

/**
 * 
 * 〈LTV前海征信数据查询测试用例〉
 *
 * @author wuwj
 * @version TestCheckUserCreditOn3rdPartyByDspHandler.java, V1.0 2017年5月8日 下午2:06:57
 */
public class TestCheckUserCreditOn3rdPartyByDspHandler {

    public static void main(String[] args) {
        loadConfig();
//        testCheckUserCreditOn3rdPartyByDspHandler();
        testCheckUserCreditOn3rdPartyHandler();
//        testCheckUserCreditOnTdHandler();
//        testCheckUserCreditOnDspTdHandler();
    }

    /**
     * <p>〈加载配置文件〉</p>
     *
     */
    private static void loadConfig() {
      StartupConfig.initialize();
      Logger.initialize();
      PersistenceConfig.initialize();
      Configuration.readConfiguration();
      HttpClientConfig.initialize();
    }
    
    /**
     * 
     * <p>〈TestCheckUserCreditOn3rdPartyByDspHandler〉</p>
     *
     */
    private static void testCheckUserCreditOn3rdPartyByDspHandler() {
        CheckUserCreditOn3rdPartyByDspHandler handler = new CheckUserCreditOn3rdPartyByDspHandler();
        handler.initialize(buildQueueMessager(buildAppId()));
        try {
            handler.execute(buildAppId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * <p>〈TestCheckUserCreditOn3rdPartyHandler描述〉</p>
     *
     */
    private static void testCheckUserCreditOn3rdPartyHandler() {
        QhzxConfiguration.initialize();
        CheckUserCreditOn3rdPartyHandler handler = new CheckUserCreditOn3rdPartyHandler();
        handler.initialize(buildQueueMessager(buildAppId()));
        try{
            handler.execute(buildAppId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * <p>〈TestCheckUserCreditOnTdHandler〉</p>
     *
     */
    private static void testCheckUserCreditOnTdHandler() {
        TdConfiguration.initialize();
        CheckUserCreditOnTdHandler handler = new CheckUserCreditOnTdHandler();
        handler.initialize(buildQueueMessager(buildAppId()));
        try {
            handler.execute(buildAppId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * <p>〈TestCheckUserCreditOnDspTdHandler〉</p>
     *
     */
    private static void testCheckUserCreditOnDspTdHandler() {
        CheckUserCreditOnDspTdHandler handler = new CheckUserCreditOnDspTdHandler();
        handler.initialize(buildQueueMessager(buildAppId()));
        try {
            handler.execute(buildAppId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * <p>〈构建测试AppId〉</p>
     * 
     * @return
     */
    private static String buildAppId() {
        return "308D6AF4-AD30-4C8E-8BC1-8BE3F54AEC68";
    }
    
    /**
     * 
     * <p>〈构建测试Message〉</p>
     * 
     * @param appId
     * @return
     */
    private static QueueMessager buildQueueMessager(String appId){
        QueueMessager queueMessager = new QueueMessager(appId, "JobRequestQueue");
        queueMessager.setChannel("cashloan");
        return queueMessager;
    }
}
