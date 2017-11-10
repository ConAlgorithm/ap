/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package core;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.dectechsolutions.instinct.InstinctActionWebServiceSoapImpl;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;

/**
 * This class is a SOAP server to accept asynchronized
 * judge result from Instinct server.
 * 
 * @author Guoqing
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class Driver {

    /**
     * @version 1.0.0
     * @throws Exception 
     * 
     */
    protected Driver() throws Exception {
        String serveraddr = StartupConfig.get("instinct.action.serveraddress");
        if (serveraddr == null) {
            throw new NullPointerException(
                "Please specify the instinct action server address in startup.properties!"
                                           + " Example:instinct.action.serveraddress = http://0.0.0.0:5050/InstinctAction/InstinctAction.asmx");
        }
        Logger.get().info("Starting action server..");
        InstinctActionWebServiceSoapImpl implementor = new InstinctActionWebServiceSoapImpl();
        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(InstinctActionWebServiceSoapImpl.class);
        svrFactory.setAddress(serveraddr);
        svrFactory.setServiceBean(implementor);
        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        svrFactory.create();
    }

    /**
     * @version 1.0.0
     * @throws Exception  
     * @param args command line parameters
     * 
     */
    public static void main(String[] args) throws Exception {
        StartupConfig.initialize();
        Logger.initialize();

        // v20160920 add start
        HttpClientConfig.initialize();
        // v20160920 add end

        int workerThreadCount = StartupConfig.getAsInt("instinct.workerthread.count");

        try {
            new Driver();
        } catch (Exception e) {
            // v20161025 mofity start 日志调整
//            Logger.get().fatal("Error happens during creating instinct action server!");
//            e.printStackTrace();
            Logger.get().error("Error happens during creating instinct action server!", e);
            // v20161025 mofity end 日志调整
            throw new Exception("Error happens during creating instinct action server!");
        }
        Logger.get().info("Action server ready...");

        SoapClientThreadPool.createWorkerThreadPool(workerThreadCount);

    }

}
