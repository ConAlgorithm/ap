package jma.test;

import jma.Configuration;
import jma.RetryRequiredException;
import jma.handlers.CalculatePDLStatisticsHandler;
import jma.handlers.FreezeApplicationHandler;
import jma.thirdpartyservices.jxl.JXLThreadWatchdog;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;

public class TestFreezeApplication {

	public static void main(String[] args)
	{
		HttpClientConfig.initialize();
		StartupConfig.initialize();
		Configuration.readConfiguration();
		JXLThreadWatchdog.initialize();
		Logger.initialize();
		PersistenceConfig.initialize();

	    //new FreezeApplicationHandler().execute("5b412ce6-4e5c-e511-87d5-80db2f14945f");
		try {
			new CalculatePDLStatisticsHandler().execute("5b412ce6-4e5c-e511-87d5-80db2f14945f");
		} catch (RetryRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
