package jma;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import catfish.servers.Server;
import jma.thirdpartyservices.jxl.JXLThreadWatchdog;
import jma.thirdpartyservices.reg007.REG007ThreadService;
import thirdparty.config.BRConfiguration;
import thirdparty.config.JXLConfiguration;
import thirdparty.config.QhzxConfiguration;
import thirdparty.config.REG007Configuration;
import thirdparty.config.TdConfiguration;
import thirdparty.config.YLZHConfiguration;

public class Driver {
    public static void main(String[] args) {
    	Server server = Server.Create();
    	
        StartupConfig.initialize();
        Logger.initialize();
        PersistenceConfig.initialize();

        Configuration.readConfiguration();

        HttpClientConfig.initialize();

        QhzxConfiguration.initialize();

        TdConfiguration.initialize();
        YLZHConfiguration.readConfiguration();
        JXLConfiguration.readConfiguration();
        REG007Configuration.initialize();
        BRConfiguration.initialize();

        REG007ThreadService.initialize();
        JXLThreadWatchdog.initialize();
        //JXLThreadWatchdogForCL.initialize();
        JMSharedData.initialize();
        Logger.get().info("Information Acquisition System is running ...");
        HttpHandler.run(server);
        JobsManager.run(); // will not return

    }
}
