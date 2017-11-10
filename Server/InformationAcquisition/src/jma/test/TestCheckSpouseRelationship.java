package jma.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import jma.Configuration;
import jma.RetryRequiredException;
import jma.handlers.CheckSpouseRelationshipHandler;
import jma.thirdpartyservices.jxl.JXLThreadWatchdog;

public class TestCheckSpouseRelationship {

	public static void main(String[] args)
	{
		HttpClientConfig.initialize();
		StartupConfig.initialize();
		Configuration.readConfiguration();
		JXLThreadWatchdog.initialize();
		Logger.initialize();
		PersistenceConfig.initialize();
		try {
			new CheckSpouseRelationshipHandler().execute("faf8c6ac-ec59-e511-98e3-ac853da49bec");
		} catch (RetryRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
