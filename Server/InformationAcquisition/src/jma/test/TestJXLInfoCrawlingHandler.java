package jma.test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import jma.thirdpartyservices.jxl.InfoCrawlerThread;
import jma.thirdpartyservices.jxl.JXLThreadWatchdog;
import thirdparty.config.JXLConfiguration;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;

public class TestJXLInfoCrawlingHandler {

	public static void main(String[] args)
	{
		StartupConfig.initialize();
		Logger.initialize();
		HttpClientConfig.initialize();
		
		JXLConfiguration.readConfiguration();
		
		JXLThreadWatchdog.initialize();

		PersistenceConfig.initialize();
		
        
		JXLThreadWatchdog.startJXLInfoCrawling("2d56ca2c-348f-e511-98e3-ac853da49bec");
	}
}
